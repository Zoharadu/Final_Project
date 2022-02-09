# Final_Projectבס"ד
הסבר על הפרויקט גמר:
SPRING BOOT זה בעצם שרת HTTP העניין הוא שע"י אנוטיישנים אנחנו מממשים את השרת עצמו, יש לו דברים בסיסיים שיודע לענות לבקשות HTTP וגם יש את ה-PATH שאומר לאיפה אנחנו הולכים איזה שרות נקבל.
זה בעצם שרת שנותן לנו אופציה לכתוב צד שרת בצורה קלה יותר שמנהל לנו את האפליקצית וואב בצורה יעילה ובפחות עבודה.
Frameworks הן ספריות צד שלישי שפותחו ונבדקו, בדרך כלל, על ידי מפתחים מנוסים וניתנות לשימוש חוזר. שימוש ב-Framework מאפשר לנו להתרכז במשימה ולא בקוד המסגרת הנלווה אליו, ועוזר לנו להיצמד לסטנדרט בתעשייה. 
מצד שני, שימוש ב-Framework מחייב אותנו לכתוב אפליקציה בצורה מסוימת ולהשתמש בספריות ובשפות תכנות בגרסאות מסוימות.
אם כן, התשובה לשאלה באיזה Framework כדאי להשתמש (אם בכלל) היא – תלוי בהקשר.
למה להשתמש ב-Spring?
Spring היא אחת ה-Frameworks הכי שכיחות ב-Java והיא מאפשרת לנו לכתוב אפליקציות מורכבות בקלות. 
אחת היכולות המרכזיות שלה היא Dependency Injection(הזרקת תלות), שימוש האפליקציה באובייקטים מוזרקים בזמן ריצה. יכולת זו מאפשרת לכתוב בדיקות בפשטות (Spring מעודדת בכך Test Driven Development).
Spring מחולקת למודולים, כך שניתן להשתמש רק בפרויקטים הרלוונטיים לאפליקציה שלנו, היא מלווה בתיעוד ומדריכים רבים והיא נתמכת על ידי הקהילה. זהו קוד פתוח בעל היסטוריה ארוכה. Spring ממשיכה להתפתח ולהיות מתוחזקת, והיא הפכה להיות פתרון ברירת המחדל להרבה בעיות נפוצות שנתקלים בהן בעת פיתוח אפליקציה ב-scale גדול. 
Spring עוזר למפתחים לחבר את יישומי האינטרנט שלהם למספר מאגרי מידע. הוא תומך במסדי נתונים יחסיים ולא יחסיים, מסגרות להפחתת מפות ושירותי נתונים מבוססי ענן ועוד.
ה- SPRING BOOT בעצם זה שרת שרץ ואחראי על כל הבקשות בין האנוטיישנים לבין הפעולות שעשינו בלוגיקה
PATH-זו הבקשה עצמה לדוג' downlod מה שאחרי הסלש זה בעצם המסלול לשרות שנתנו פונקציה או פעולה לוגית.
ומה שלפני הסלש זה הפורט לדוג' 8080 זה הפורט TCP שמאזין לבקשות שממתין עד שייכנסו הבקשות, נכנסה בקשה אז הולך עם הפס הזה ובודק אם באמת יש מסלול ואז ה- SERVER עונה.

בס"ד

איך המערכת TimeTest  בנויה?
יש לנו קלאסים מסוימים שיהיו לנו בתור אובייקטים והם:
1. Conditionהמכיל: שם תנאי,ימין בין המבנים,פער בין מועד א' ל-ב',שעת מינימום לתחילת המבחן בבוקר, שעת מינימום לתחילת המבחן אחה"צ,תאריך תחילת תקופת המבחנים,תאריך סיום תקופת המבחנים,אב"צ כן או לא.
2. Courseהמכיל: שם הקורס,קורס בוקר כן או לא, ימי לימוד נצרכים, משך הבחינה הנדרשת.
3. Examהמכיל: שם הקורס, מועד א' או ב', פער בין המועדים, תאריך מועד ב', בקשת ימי הלימוד הנדרשים למבחן זה, כמה ימי לימוד קיבל בפועל, תאריך המבחן, שעת המבחן.
4. LoginObjectהמכיל: שם משתמש וסיסמה ועושה שםבדיקה אם באמת שווה ליוזר שהגדרנו כיוזר כניסה למערכת.
5. ExamsScheduleהמכיל: רשימת המבחנים ללוח שנה, רשימת דיווח על דברים שלא יצאו לפועל.        בקלאס זה יש מתודה בשם generateReports המכילה בדיקה איזה מבחנים לא קיבלו את הימי לימוד הרצויים לכתחילה וכדי שנוכל להוסיף לדיווח.
6. OperationStatusהמכיל: בדיקה לצורך הצד לקוח של שגיאה אם יש כזו או דטה המחזירה טרו אם הכל בסדר וכך הצד לקוח יודע איך להציג את זה למשתמש.                                                           הראשון מבניהם אם יש שגיאה שלא אמורה לקרוא אבל ליתר ביטחון שזה אם האובייקט לא מצליח לעבור לג'ייסון אז זורק את השגיאה הזו.                                                                           השני מבינהם אם יש ארו בתהליך של החשוב לוח מבחנים אז זורק אקספשיין ומחזיר לו אובייקט מסוג סטטוס.                                                                                                                  כדי שיהיה פורמט זהה לאנגורל ויקבל אותו  ERROE או  DATE ושם יש גם שתי פונקציות אחת הופכת לאובייקט מסוג סטרינג ומודיע על התחברות בהצלחה והשניה בשביל הדפסות שלנו כדי שלא יעוף על ERRO
7. ExamCalendar המכיל: די שזה יום במיליסקנד, פורמט של אובייקט תאריך ואובייקט של לוח שנה.                                                                                                      getActualDateFrom זו מתודה הבודקת לי בעצם שתאריך ספציפי לא יצא לי על שישי שבת והיא בודקת ימים קדימה ואחורה ומחזירה אובייקט של תאריך.                                                getValidDateFrom פונקציה הזו מביא לי שלושים ימים קדימה בלי שישי שבת בשביל מועדי ב' וזה מחזיר יום ספציפי.                                                                                                              getNextMonth מתודה אשר מביא לך תאריך של החודש הבא נגיד 16.2                                          calculateNumberOfActualDays מתודה שלוקחת תקופת מבחנים מתאריך עד תאריך ומחשב כמה ימי עבודה יש שם  בלי שישי שבת וזה משמש אותנו שאנחנו מקבלים תקופת מבחנים אז מחשב לנו את ימי הלימוד כדי לחלק בין המבחנים כמה שמגיע פחות או יותר לכל מבחן.                                       Parse מתודה המקבל מהקלאינט סטרינג דייט והופכת את זה לתאריך של דייט לפי ג'אווה                 format מתודה העושה הפוך מחזיר את זה בצורה שהקלאיינט יוכל לקרוא.

בס"ד
8.TimeTestRestController המכיל:קריאות מהשרת ללקוח הפטנט של ספרינג בוט זה כשיש לי קלאס ורשום מעליו @RestController ואז כשמצא, מחפש @RequestMapping לפי מה שרשום URL נגיד LOGIN ואז מקבל סטרינג של גיי'סון של שם משתמש וסיסמה.                                                          getExamsSchedule פונקציה שמביאה את כל הלוח מבחנים הסופי אחרי החישוב קודם ממיר בעצם את התנאי לג'וואי ואז מחזיר את הרשימה.
getCondition בקשה זו מקבל שם של תנאי מעבירה אותו לבדיקה ומקבלת אובייקט מסוג תנאי שמעבירה אותו שוב לאופריישן סטטוס לבדיקה שחזר ערך תקין המכיל סטרינג שזה ארו שנכשל או סטרינג של הערך שאני מחזיר.
getAllConditions מתודה המחזיר את כל התנאים שנשמרו ע"י לחיצה על עיגול ירוק הוא מביא את כל התנאים שיש לו אנחנו מקבלים את זה בתור הריליסט וממירים את זה למערך כדי שהוא יוכל לקרוא את זה תצוגתי ואז מעבירים את זה לסטדטה ושם שם את זה בתור האובייקט ומעבירם את זה חזרה כדי להמיר את זה לאובייקשט שיהיה מוכן להעברה לצד לקוח.                                                       uploadXslFile מתודה שמעלה קובץ  XSL לקחנו אותה מגוגל ממש העתק הדבק זו בקשה מסוג MultipartFile שאפשר להכניס בתוכה כמה חלקים כגון קובץ ג'ייסון או סטרינג יש לבקשה הזו כמה חלקים היא מורכבת.                                                                                                        הוא מצפה לקבל קובץ מסוג FILE אחרת לא יתקבל הקובץ ואם לא התקבל קובץ אז השרת לא יוכל להיכנס לפונקציה הזו.                                                                                                הבקשה מקבלת בייתים ואז מקים תיקיה של קבצים ואז תיצור את אותה ספריה והשם שלה יהיה השם של הקובץ שהעלתי ואם הוא null אז תקרא לה בשם קורסים                                        בעצם הקובץ שקיבלנו לא מתאים למחשב אלא לגאווה אז אנחנו רושמים לו קרייט שזה יצירת הקובץ באמת במחשב וכל ההדפסות בין לבין זה לראות שעבר בהצלחה בשבילנו אח"כ בעצם הוא קורא מהקובץ שקיבלנו וכותב את זה בתוך קובץ ג'וואי שלנו ממיר אותו בעצם כדי שהג'אווה יבין ע"י העברת בייתים כאילו סוג של קריאה העתקה, ואז את אותו קובץ אנחנו מעלים לסרבר ואז שוב הדפסה שהצליח.
downloadLastGeneratedFile הורדה של הקובץ אקסל של המבחנים ע"י לחיצה על הכתפתור הכחול הועתק מאתר כלשהו.
printAction מדפיס את הכניסות של הלקוח בשבילנו כדי שנוכל לדעת איפה נופל או מה קורה בתוכנית מה עשה...
9. MySqlDBClientמכיל: את כל הקריאות בין הדטה בייס לבין האסקיואל הוספה הורדה וכד' כל זה עשינו דרך גוגל כי לא למדנו בתואר אז תוך כדי למדנו איך לעשות כל דבר ועשינו הדפסות קטנות לראות שבאמת עובד.
10. ExamScheduleCalculator המכיל בעצם עושה את כל החישוב של תקופת מבחנים לוקחים את הימים שיש בתוך תקופה המבחנים ומחלקים את זה למבחנים שיש בלוח לפי הבקשה של ימי לימוד לכל מבחן כמה שאפשר להתחשב, ז"א אם יש עשרה ימים בתקופה ואחד צריך לקבל 4 ואחד 2 אז זה שדורש ארבע ימים יקבל פי שניים ימים מזה שדורש יומיים אחד יקבל 6.6 והשני יקבל 3.3 ואז אנחנו מעגלים את הימים שישלים ל-10, ואם יש יום מיותר אז נביא את היום הזה למי שדורש וחסרים לו ימים ולפי מי שחסר יותר פחות ימים.                                          handleConditionDB    מתודה זו בודקת אם התנאי קיים אם כן מעדכן אותו בדטה בייס אם שינה משהו מעדכן ואם לא אז מוסיף אותו.                                                                                   initExams מתודה זו עושה אתחול של שתי רשימות של מבחנים של מועדי א' ומועדי ב' ומסדרת גם אם יוצאי צבא או לא, והיא גם סכמת כמה ימי לימוד צריך בסה"כ, בהמשך לוקח את תאריכי ההתחלה והסיום ובודק אם הם תקינים בהיררכיה, ואז מחשב כמה ימים לימוד יש לי בתקופה הזו ע"י שימוש בקלנדר ואחרי כל החישובים לעיל יצא קטן שווה לאפס תשלח שגיאה ללקוח, המבחנים מסודרים לפי ימי הלימוד שצריך לכל אחד פחות או יותר.
Compare מתודה זו עושה השוואה שלפיו מסדר את המבחנים, אח"כ אנחנו מחלקים את הימים למבחנים לפי חלק יחסי ועיגול של יום לא שלם לקטן, אנחנו הופכים את הפלוט לאינט וזה לשני המועדים, אח"כ זה החילוק של הימים שנותרו בין המבחנים לפי מי שחסר לו ימים רק להם מחלק ע"י פונקציה ב-Exam שבודקת את זה, בשורה 205 אנחנו עושים השמה לתאריכים ממש לכל אחד יש את הימים שהולך לקבל ביומן ומה שעושים מתחילים לחלק אותם בתאריכים מועדי א מההתחלה לאמצע ומועדי ב' מהסוף להתחלה הכל לפי הימי לימוד שקיבלו.
     
                           
בס"ד
התהליך:
תהליך העבודה של האלגוריתם מתחיל במעבר על כל הקורסים כך שכל קורס מקבל שני מועדים, לכל קורס נקצב זמן הלמידה הנדרש.
כל מבחן מקבל את החלק היחסי שלו מהימים המוקצבים לתקופת המבחנים.
total_days x days_to_study / total_days_to_study
כך קיבלנו קירוב מקסימלי לחלוקה הוגנת של הימים כך שאם חסרים ימים אז כל המבחנים חסרים באותה מידה, התוצאה מתקבלת כ-Float והמרנו אותה ל-Int, וכך המספר נעשה קטן יותר       (החלק העשרוני אבד ממנו), ואז יתכן שיישאר עודף של DAY_TO_STUDY, ואותו אנחנו נחלק בין המבחנים החל מהמבחן הצורך הכי הרבה ימים וכלה במבחן שצורך הכי מעט ימים.
חלק שני של העבודה הוא שיבוץ מועדי א' ביומן כלומר נותנים לכל מבחן תאריך, לפי סדר אקראי.
ולאחר שכל מועדי א' שובצו ניתן לתת תאריכים מועדפים למועדי ב'(כחודש אחר מועד א').
שיבוץ מועדי ב' נעשה בצורה כזו שמתחילים לשבץ מסוף תקופת המבחנים אחורה על מנת לקבל מרחק מקסימלי ממועד א' לצורך למידה.
לאחר בדיקות של מקרי קצה בהם לא היה מספיק ימים בתקופת המבחנים והבעיה שנוצרה היא שלא היו מספיק ימי לימוד לפי הדרישה או שבכלל מבחן מסוים לא קיבל מועד, ולכן יצרנו עבור בעיה זו פונקציה בשם generateReports שעוברת על כל הקורסים ומשווה בין כמה ימי לימוד התבקשו לאותו קורס לבין כמה ימי לימוד שניתנו בפועל.                                                                              מבחן שקיבל פחות ימי לימוד מקבל דיווח בטבלת אקסל שתודפס בסוף.
ואנחנו מאתחלים שני רשימות אחת למועד א ואחת למועד ב ואז מחלקים את הימים לכל המועדים יחד ואז שמים את כל מועדי א' במקום ואז אפשר לתת למועדי ב' את הזמן של מועדי ב' שזה 30 יום אח"כ ושמים את מועדי ב' מהסוף להתחלה ז"א בכיוון ההפוך לעומת מועד א' שזה מההתחלה לאמצע וזה כדי לתת מרווח כמה שיותר בין המועדים
וזה נותן לי 100% שלא יקרה מצב של התנגשויות ותקלות.


בס"ד
הרצת הפרויקט
הרצנו דרך האינטלג'י קובץ ג'אר של כל הפרויקט והעתקנו למחשב מרוחק באמזון ששם הרצנו את הכל כדי שיהיה יותר נח לעבור מול אלה שעושות את הצד לקוח וזה אחרי שראינו הסבר לפתיחת שרת באמזון דרך יוטיוב.
מחשב מרוחק נפתח דרך חיפוש במחשב שלנו נכתוב mstsc
הכתובת שנכניס: 3.145.5.70 
הסיסמה לשם זה: tdOeI4shnaVfEHKO(&8pL3x.dw32Kgin
המייל: dixisrooms@gmail.com -זה בשביל פתיחת השרת באמזון פתחנו מייל
EC2AMAZ-69IQRNJ\Administrator) זה משתמש של המחשב מרוחק שם)
לבסוף את כל הקבצים של האנגולר הבנות האלו לגיטהב ומשם הורדתי הכל בתור קובץ מקובץ שאותו פתחתי והרצתי דרך ה-CMD במחשב שלי וגם הרצנו את הג'אווה אצלי וככה בעצם השרת והלקוח עלו יחד במחשב ויכולנו לגשת לאתר עם הפורט שהגדרנו.
