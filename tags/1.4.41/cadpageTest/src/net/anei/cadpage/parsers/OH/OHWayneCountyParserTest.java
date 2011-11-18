package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHWayneCountyParserTest extends BaseParserTest {
  
  public OHWayneCountyParserTest() {
    setParser(new OHWayneCountyParser(), "WAYNE COUNTY", "OH");
  }
  
  @Test
  public void testParser() {
    doTest("T1",
        "taccad:SQUAD RUN 593 GATES ST CAROLYN RUNYON, AGE 64, POSSIBLE STROKE",
        "CALL:SQUAD RUN",
        "ADDR:593 GATES ST",
        "INFO:CAROLYN RUNYON, AGE 64, POSSIBLE STROKE");

    doTest("T2",
        "taccad:ACCIDENT EASTERN RD AKRON RD 911 CELL CALL FALINE JONES REF ACCIDENT UNK INJURIES. NORTON FIRE / POLICE RESPONDING.",
        "CALL:ACCIDENT",
        "ADDR:EASTERN RD & AKRON RD",
        "INFO:911 CELL CALL FALINE JONES REF ACCIDENT UNK INJURIES NORTON FIRE / POLICE RESPONDING");

    doTest("T3",
        "taccad:ASSAULT 351 COLLIER DR 911 CELL JESSICA KLATIK REQUEST OFFICER FOR AN ASSAULT",
        "CALL:ASSAULT",
        "ADDR:351 COLLIER DR",
        "INFO:911 CELL JESSICA KLATIK REQUEST OFFICER FOR AN ASSAULT");

    doTest("T4",
        "taccad:FIRE 12315 BLACK DIAMOND RD UNKNOWN TYPE OF FIRE CLOSE TO THE HOUSE.",
        "CALL:FIRE",
        "ADDR:12315 BLACK DIAMOND RD",
        "INFO:UNKNOWN TYPE OF FIRE CLOSE TO THE HOUSE");

    doTest("T5",
        "taccad:SQUAD RUN 142 HOMAN DR 68 YOF DIFF. BREATHING MARCELLA PRIDE",
        "CALL:SQUAD RUN",
        "ADDR:142 HOMAN DR",
        "INFO:68 YOF DIFF BREATHING MARCELLA PRIDE");

    doTest("T6",
        "taccad:ALARM 68 N PORTAGE ST CMS SECURITY FOR PHONE IN ALARM - HEAT PULL STATION",
        "CALL:ALARM",
        "ADDR:68 N PORTAGE ST",
        "INFO:CMS SECURITY FOR PHONE IN ALARM - HEAT PULL STATION");

    doTest("T7",
        "taccad:SQUAD RUN 176 STONE RIDGE CIR 911 CALL JUDITH DAKOSKI REQUEST SQUAD 75 YOM HEART CONDITION NOW VOMITING AND DIARRHEA",
        "CALL:SQUAD RUN",
        "ADDR:176 STONE RIDGE CIR",
        "INFO:911 CALL JUDITH DAKOSKI REQUEST SQUAD 75 YOM HEART CONDITION NOW VOMITING AND DIARRHEA");

    doTest("T8",
        "taccad:SQUAD RUN 490 E CLINTON 911 CALL - 57 YOF DIFF BREATHING",
        "CALL:SQUAD RUN",
        "ADDR:490 E CLINTON 911 CALL - 57 YOF DIFF BREATHING");

    doTest("T9",
        "taccad:SQUAD RUN 464 GATES ST WALKIN IN FOR CHEST PAINS",
        "CALL:SQUAD RUN",
        "ADDR:464 GATES ST",
        "INFO:WALKIN IN FOR CHEST PAINS");

    doTest("T10",
        "taccad:ACCIDENT GREAT LAKES BLVD GRILL RD MULTIPLE 911 CALLS PICK UP ON ITS TOP IN THE MEDIUM",
        "CALL:ACCIDENT GREAT",
        "ADDR:LAKES BLVD & GRILL RD",
        "INFO:MULTIPLE 911 CALLS PICK UP ON ITS TOP IN THE MEDIUM");

    doTest("T11",
        "taccad:OPEN BURNING CHIPPEWA FIRE OFFICER 19163 EDWARDS RD GENE YEAGER REPORTED NEIGHBOR IS BURNING LEAVES AND SMOKING UP THE NEIGHBORHOOD.",
        "CALL:OPEN BURNING CHIPPEWA FIRE OFFICER",
        "ADDR:19163 EDWARDS RD",
        "INFO:GENE YEAGER REPORTED NEIGHBOR IS BURNING LEAVES AND SMOKING UP THE NEIGHBORHOOD");

    doTest("T12",
        "taccad:SQUAD RUN 14740 OAK GROVE DR 28 NICOLE SHORTRIDGE REQUEST SQUAD 26 YOF WITHDRAWAL FROM MEDICATIONS",
        "CALL:SQUAD RUN",
        "ADDR:14740 OAK GROVE DR",
        "INFO:28 NICOLE SHORTRIDGE REQUEST SQUAD 26 YOF WITHDRAWAL FROM MEDICATIONS");

    doTest("T13",
        "taccad:SQUAD RUN 14083 HATFIELD RD 87 YO M FALL VICTIM POSSIBLE BROKEN HIP",
        "CALL:SQUAD RUN",
        "ADDR:14083 HATFIELD RD",
        "INFO:87 YO M FALL VICTIM POSSIBLE BROKEN HIP");
  }
  
  public static void main(String[] args) {
    new OHWayneCountyParserTest().generateTests("T1","CALL ADDR APT INFO");
  }
}