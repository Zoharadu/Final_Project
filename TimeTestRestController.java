package com.project.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.*;
import com.project.db.MySqlDBClient;
import com.project.xsl.XslFileReader;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.project.xsl.XslFileWriter.SCHEDULE_FILE_NAME;
@RestController
@CrossOrigin(origins = "*")
public class TimeTestRestController {

    private static final LoginObject LOGIN_OBJECT = new LoginObject("root", "root");
//    private static final String OK = "{\"status\":\"OK\"}";
//    private static final String ERROR = "{\"status\":\"ERROR\"}";


    @RequestMapping(value = "/GetExamsSchedule", method = RequestMethod.POST)
    @ResponseBody
    public String getExamsSchedule(@RequestBody String conditionJsonString) throws IOException {
        printAction("/GetExamsSchedule");
        System.out.println("Input: " + conditionJsonString);
        try {
            Condition condition = new ObjectMapper().readValue(conditionJsonString, Condition.class);
            ExamScheduleCalculator.getTheInstance().handleConditionDB(condition);
            ExamsSchedule examsSchedule = ExamScheduleCalculator.getTheInstance().calculateSchedule(condition);
            String result = new ObjectMapper().writeValueAsString(examsSchedule);
            System.out.println("/GetExamsSchedule RESPONSE:");
            OperationStatus response = new OperationStatus();
            response.setData(examsSchedule);
            return response.toResponse();
        } catch (JsonProcessingException e) {
            System.out.println("Parsing error: " + e.getMessage());
            return new OperationStatus("Parsing error: " + e.getMessage()).toString();
            //e.printStackTrace();
        } catch (ApplicationException e) {
            return new OperationStatus(e.getMessage()).toString();
        }
    }


    @RequestMapping(value = "/Login", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://3.16.158.163:8080")
    @ResponseBody
    public String login(@RequestBody String loginJson) {
        printAction("/Login");
        System.out.println("Input: " + loginJson);
        try {
            LoginObject loginObject = new ObjectMapper().readValue(loginJson, LoginObject.class);
            if (LOGIN_OBJECT.accept(loginObject)) {
                return OperationStatus.OK.toResponse();
            }
            return new OperationStatus("Login failed").toResponse();
        } catch (JsonProcessingException e) {
            return new OperationStatus("Illegal login format").toString();
        }
    }

    // http://10.10.10.10:8080/GetCondition?name=name1
    @RequestMapping(value = "/GetCondition")
    @ResponseBody
    public String getCondition(@RequestParam(value = "name") String conditionName ) {
        printAction("/GetCondition");
        System.out.println("Input: " + conditionName);
        // TODO integration patch
        //Condition condition = MySqlDBClient.getCondition(conditionName);
        Condition condition = ExamScheduleCalculator.getTheInstance().getCondition(conditionName);
        if (condition != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result =  objectMapper.writeValueAsString(condition);
                OperationStatus response = new OperationStatus();
                response.setData(result);
                return response.toResponse();
            } catch (JsonProcessingException e) {
                return new OperationStatus("Parsing error").toString();
            }
        } else {
            return new OperationStatus("Condition " + conditionName + " not found").toString();
        }
    }

    // http://10.10.10.10:8080/GetAllConditions
    @RequestMapping(value = "/GetAllConditions")
    @ResponseBody
    public String getAllConditions() {
        printAction("/GetAllConditions");
        // TODO integration patch
        //List<Condition> conditions = MySqlDBClient.getAllConditions();
        List<Condition> conditions = ExamScheduleCalculator.getTheInstance().getAllConditions();
        String[] result = new String[conditions.size()];
        for (int i = 0; i < conditions.size(); i ++) {
            result[i] = conditions.get(i).getConditionName();
        }
        OperationStatus response = new OperationStatus();
        response.setData(result);
        try {
            return response.toResponse();
        } catch (JsonProcessingException e) {
            return new OperationStatus("Parsing error").toString();
        }
    }

    @RequestMapping(value = "/UploadCoursesFile", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String uploadXslFile(@RequestPart(value = "file", required = true) MultipartFile mFile) {
        printAction("/UploadCoursesFile");
        File mediaFile = null;
        try {
            File dir = new File("xsl_file");
            dir.mkdirs();
            String fileName = mFile.getOriginalFilename();
            System.out.println("Request arrived with OriginalFilename " + fileName);
            if (fileName == null) {
                fileName = "courses.xlsx";
                System.out.println("Setting filename to " + fileName);
            }
            mediaFile = new File(dir, fileName);


            mediaFile.createNewFile();
            System.out.println("New file created");
            InputStream initialStream = mFile.getInputStream();
            FileOutputStream outStream = new FileOutputStream(mediaFile);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);

            System.out.println("Courses file saved at " + mediaFile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
            return new OperationStatus("File error: " + e.getMessage()).toString();
        }

        XslFileReader.getInstance().loadFile(mediaFile.getPath());
        System.out.println("Excel file reader loaded with new file");

        return OperationStatus.OK.toString();
    }

    // https://stackoverflow.com/questions/51684550/how-to-download-an-excel-file-in-spring-restcontroller
    @RequestMapping("/Download")
    @ResponseBody
    public HttpEntity<byte[]> downloadLastGeneratedFile() throws IOException {
        printAction("/Download");
        byte[] image = FileUtils.readFileToByteArray(new File(SCHEDULE_FILE_NAME));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "force-download"));
        String scheduleName = ExamScheduleCalculator.getTheInstance().getCurrentConditionName();
        String contentDisposition = "attachment; filename=" + SCHEDULE_FILE_NAME;
        //headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=schedule.xlsx");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
//        headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
//        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }

    private void printAction(String action) {
        System.out.println("**********************************");
        System.out.println(action);
    }
}
