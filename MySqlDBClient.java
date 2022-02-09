package com.project.db;

import com.project.Condition;
import com.project.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDBClient {

    private static final Object initLock = new Object();


    private static Connection dbConnection;

    public static boolean init() {
        if (dbConnection == null) {
            synchronized (initLock) {
                if (dbConnection == null) {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York","R
                        // oot","root");
                        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db?useLegacyDatetimeCode=false&serverTimezone=Asia/Jerusalem","root","Root");
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
        return dbConnection != null;

    }
    public static void insertCourse(
            String name,
            boolean isMorningCourse,
            int daysToStudy,
            int testDurationHours) {
        try {
            String sql = "INSERT INTO course VALUES (name, isMorningCourse,daysToStudy,testDurationHours)";
            dbConnection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateCondition(Condition condition) {
        String sql = "UPDATE project_db.conditions_table SET " +
                "daysBetweenExams=" + condition.getDaysBetweenExams() + "," +
                "gapFirstAndSecondExam=" + condition.getGapFirstAndSecondExam() + "," +
                "minMorningHour=" + condition.getMinMorningHour() + "," +
                "minAfternoonHour=" + condition.getMinAfternoonHour() + "," +
                "examsPeriodStartDate=" + "\"" + condition.getExamsPeriodStartDate() + "\"," +
                "examsPeriodEndDate=" + "\"" + condition.getExamsPeriodEndDate() + "\"," +
                "forSoldiers=" + "\"" + condition.isForSoldiers() + "\" WHERE " +
                "conditionName=" + "\"" + condition.getConditionName() + "\"";
        try {
            dbConnection.prepareStatement(sql).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertCondition(Condition condition) {
        insertCondition(
                condition.getConditionName(),
                condition.getDaysBetweenExams(),
                condition.getGapFirstAndSecondExam(),
                condition.getMinMorningHour(),
                condition.getMinAfternoonHour(),
                condition.getExamsPeriodStartDate(),
                condition.getExamsPeriodEndDate(),
                condition.isForSoldiers()
        );
    }
    public static void insertCondition (
            String conditionName,
            int daysBetweenExams,
            int gapFirstAndSecondExam,
            int minMorningHour,
            int minAfternoonHour,
            String examsPeriodStartDate,
            String examsPeriodEndDate,
            boolean forSoldiers){
        try {
            String sql = "insert into project_db.conditions_table " +
                    " (conditionName, daysBetweenExams, gapFirstAndSecondExam, minMorningHour, minAfternoonHour, examsPeriodStartDate, examsPeriodEndDate, forSoldiers )" +
                    " VALUES (" +
                    "\""+conditionName + "\"," +
                    daysBetweenExams + "," +
                    gapFirstAndSecondExam +  "," +
                    minMorningHour + "," +
                    minAfternoonHour +  "," +
                    "\""+examsPeriodStartDate + "\"," +
                    "\""+examsPeriodEndDate + "\"," +
                    "\""+forSoldiers + "\")";
            dbConnection.prepareStatement(sql).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Condition getCondition(String name){
        try {
            String sql = "select * from project_db.conditions_table where conditionName = "+ name;
            ResultSet rs= dbConnection.prepareCall(sql).getResultSet();
            Condition condition=new Condition();
            int minMorningHour= rs.getInt("minMorningHour");
            int minAfternoonHour= rs.getInt("minAfternoonHour");
            condition.setMinAfternoonHour(minAfternoonHour);
            condition.setMinMorningHour(minMorningHour);
            condition.setDaysBetweenExams(rs.getInt("daysBetweenExams"));
            condition.setGapFirstAndSecondExam(rs.getInt("gapFirstAndSecondExam"));
            condition.setExamsPeriodEndDate(rs.getString("examsPeriodEndDate"));
            condition.setExamsPeriodStartDate(rs.getString("examsPeriodStartDate"));
            condition.setConditionName(rs.getString("conditionName"));
            condition.setForSoldiers(Boolean.parseBoolean(rs.getString("forSoldiers")));
            return condition;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Condition> getAllConditions() {
        ArrayList<Condition> result = new ArrayList<>();
        try {
            String query = "select * from project_db.conditions_table";
            //ResultSet rs = dbConnection.prepareCall("select * from project_db.conditions_table").getResultSet();
            ResultSet rs = dbConnection.createStatement().executeQuery(query);
            while (rs.next()) {
                Condition condition = new Condition();
                int minMorningHour = rs.getInt("minMorningHour");
                int minAfternoonHour = rs.getInt("minAfternoonHour");
                condition.setMinAfternoonHour(minAfternoonHour);
                condition.setMinMorningHour(minMorningHour);
                condition.setDaysBetweenExams(rs.getInt("daysBetweenExams"));
                condition.setGapFirstAndSecondExam(rs.getInt("gapFirstAndSecondExam"));
                condition.setExamsPeriodEndDate(rs.getString("examsPeriodEndDate"));
                condition.setExamsPeriodStartDate(rs.getString("examsPeriodStartDate"));
                condition.setConditionName(rs.getString("conditionName"));
                condition.setForSoldiers(Boolean.parseBoolean(rs.getString("forSoldiers")));
                result.add(condition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
