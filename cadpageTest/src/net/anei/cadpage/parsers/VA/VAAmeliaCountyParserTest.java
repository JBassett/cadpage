package net.anei.cadpage.parsers.VA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Ameilia County, VA

Contact: Active911
Agency name: Amelia County Co. 1
Location: Amelia, VA, United States
Sender: MAILBOX@ameliasheriff.org

(AMELFD 09) MAILBOX:59 SICK PERSON 014541 MILLS CT CFS# 2012-011293\r\n
(AMELFD 23) MAILBOX:25 UNCONSCIOUS / FAINTING 015921 RAVENCREST CT CFS# 2012-011242\r\n
(AMELFD 10) MAILBOX:21 ALARM 001500 NAMOZINE RD CHU CFS# 2012-011216\r\n
(AMELFD 10) MAILBOX:18 STRUCTURE FIRE 000000 UPPY'S ON 360 CFS# 2012-011171\r\n
(AMELFD 23) MAILBOX:19 STRUCTURE FIRE 009051 ROCKY RUN LN CFS# 2012-011087\r\n
(AMELFD 07) MAILBOX:25 BREATHING PROBLEMS 017040 LEIDIG ST AME CFS# 2012-011051\r\n
(AMELFD 12) MAILBOX:37 UNCONSCIOUS / FAINTING 007701 CHEATHAMS RD CFS# 2012-010982 CROSS: FIVE FORKS RD/HW 360 EAST\r\n
(AMELFD 12) MAILBOX:36 ABDOMINAL PAIN / PROBLEMS 008500 WOODLAND DR AME CFS# 2012-010983\r\n
(AMELFD 08) MAILBOX:18 TRAFFIC VIOLATION/COMPLAINT/HA 000000 CRALLES RD/608 CFS# 2012-010971\r\n
(AMELFD 03) MAILBOX:11 ALLERGIES / ENVENOMATIONS 005350 CEDAR LN AME CFS# 2012-010967 CROSS: 5901 DENNISVILLE RD/5901 DENNISVILLE RD\r\n
(AMELFD 04) MAILBOX:12 BREATHING PROBLEMS 008830 VIRGINIA ST AME CFS# 2012-010937\r\n
(AMELFD 00) MAILBOX:56 SICK PERSON 015835 GOODES BRIDGE RD CFS# 2012-010936\r\n
(AMELFD 13) MAILBOX:20 HEMORRHAGE / LACERATIONS 015175 PATRICK HENRY HWY CFS# 2012-010889\r\n
(AMELFD 10) MAILBOX:08 STRUCTURE FIRE 009300 MILITARY RD AME CFS# 2012-010886\r\n
(AMELFD 05) MAILBOX:00 OUTSIDE FIRE 000000 MAPLEWOOD RD CFS# 2012-010878\r\n
(AMELFD 15) MAILBOX:50 BREATHING PROBLEMS 015105 PATRICK HENRY HWY AME CFS# 2012-010845\r\n
(AMELFD 10) MAILBOX:55 BREATHING PROBLEMS 017600 EGGLESTETTON RD AME CFS# 2012-010804\r\n
(AMELFD 19) MAILBOX:46 ABDOMINAL PAIN / PROBLEMS 008500 WOODLAND DR AME CFS# 2012-010751\r\n
(AMELFD 15) MAILBOX:58 OUTSIDE FIRE 000000 SOAP STONE RD CFS# 2012-010739\r\n
(AMELFD 11) MAILBOX:52 UNCONSCIOUS / FAINTING 000000 FLIPPEN LN CFS# 2012-010730\r\n
(AMELFD 12) MAILBOX:37 UNKNOWN PROBLEM / MAN DOWN 017041 LEIDIG ST AME CFS# 2012-010702\r\n
(AMELFD 16) MAILBOX:49 SPECIAL ASSIGNMENT 000000 DOWNTOWN AMELIA CFS# 2012-010588\r\n
(AMELFD 15) MAILBOX:56 TRAFFIC/TRANSPORTATION ACCIDEN CFS# 2012-010587\r\n
(AMELFD 10) MAILBOX:13 TRAUMATIC INJURIES 004706 MILLS LN AME CFS# 2012-010490\r\n
(AMELFD 09) MAILBOX:27 HEADACHE 007701 CHEATHAMS RD CFS# 2012-010466 CROSS: FIVE FORKS RD/HW 360 EAST\r\n
(AMELFD 00) MAILBOX:01 UNKNOWN PROBLEM / MAN DOWN 013341 WEST LN CFS# 2012-010420\r\n
(AMELFD 09) MAILBOX:52 BACK PAIN 009100 VIRGINIA ST AME CFS# 2012-010392\r\n
(AMELFD 05) MAILBOX:29 CHEST PAIN 013401 CHULA RD CFS# 2012-010388\r\n
(AMELFD 14) MAILBOX:15 HEMORRHAGE / LACERATIONS 015721 FIVE FORKS RD CFS# 2012-010333\r\n
(AMELFD 02) MAILBOX:31 STRUCTURE FIRE 001506 FOREST HILL LN AME CFS# 2012-010324\r\n
(AMELFD 10) MAILBOX:55 STRUCTURE FIRE 016540 GOODES BRIDGE RD AME CFS# 2012-009048\r\n
(AMELFD 10) MAILBOX:52 CARDIAC / RESPIRATORY ARREST 008910 OTTERBURN RD AME CFS# 2008-003418\r\n
(AMELFD 10) MAILBOX:49 AIRCRAFT EMERGENCY 008741 N FIVE FORKS RD AME CFS# 2007-007793\r\n

 */
public class VAAmeliaCountyParserTest extends BaseParserTest {
  
  public VAAmeliaCountyParserTest() {
    setParser(new VAAmeliaCountyParser(), "AMELIA COUNTY", "VA");
  }
  
  @Test
  public void testAmeilia() {

    doTest("T1",
        "(AMELFD 09) MAILBOX:59 SICK PERSON 014541 MILLS CT CFS# 2012-011293\r\n",
        "SRC:AMELFD",
        "UNIT:09",
        "BOX:59",
        "CALL:SICK PERSON",
        "ADDR:14541 MILLS CT",
        "ID:2012-011293");

    doTest("T2",
        "(AMELFD 23) MAILBOX:25 UNCONSCIOUS / FAINTING 015921 RAVENCREST CT CFS# 2012-011242\r\n",
        "SRC:AMELFD",
        "UNIT:23",
        "BOX:25",
        "CALL:UNCONSCIOUS / FAINTING",
        "ADDR:15921 RAVENCREST CT", // Not mapping
        "ID:2012-011242");

    doTest("T3",
        "(AMELFD 10) MAILBOX:21 ALARM 001500 NAMOZINE RD CHU CFS# 2012-011216\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:21",
        "CALL:ALARM",
        "ADDR:1500 NAMOZINE RD",  // Not mapping
        "CITY:CHULA",
        "ID:2012-011216");

    doTest("T4",
        "(AMELFD 10) MAILBOX:18 STRUCTURE FIRE 000000 UPPY'S ON 360 CFS# 2012-011171\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:18",
        "CALL:STRUCTURE FIRE",
        "ADDR:UPPY'S ON 360",
        "ID:2012-011171");

    doTest("T5",
        "(AMELFD 23) MAILBOX:19 STRUCTURE FIRE 009051 ROCKY RUN LN CFS# 2012-011087\r\n",
        "SRC:AMELFD",
        "UNIT:23",
        "BOX:19",
        "CALL:STRUCTURE FIRE",
        "ADDR:9051 ROCKY RUN LN",
        "ID:2012-011087");

    doTest("T6",
        "(AMELFD 07) MAILBOX:25 BREATHING PROBLEMS 017040 LEIDIG ST AME CFS# 2012-011051\r\n",
        "SRC:AMELFD",
        "UNIT:07",
        "BOX:25",
        "CALL:BREATHING PROBLEMS",
        "ADDR:17040 LEIDIG ST",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-011051");

    doTest("T7",
        "(AMELFD 12) MAILBOX:37 UNCONSCIOUS / FAINTING 007701 CHEATHAMS RD CFS# 2012-010982 CROSS: FIVE FORKS RD/HW 360 EAST\r\n",
        "SRC:AMELFD",
        "UNIT:12",
        "BOX:37",
        "CALL:UNCONSCIOUS / FAINTING",
        "ADDR:7701 CHEATHAMS RD",
        "ID:2012-010982",
        "X:FIVE FORKS RD/HW 360 EAST");

    doTest("T8",
        "(AMELFD 12) MAILBOX:36 ABDOMINAL PAIN / PROBLEMS 008500 WOODLAND DR AME CFS# 2012-010983\r\n",
        "SRC:AMELFD",
        "UNIT:12",
        "BOX:36",
        "CALL:ABDOMINAL PAIN / PROBLEMS",
        "ADDR:8500 WOODLAND DR",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010983");

    doTest("T9",
        "(AMELFD 08) MAILBOX:18 TRAFFIC VIOLATION/COMPLAINT/HA 000000 CRALLES RD/608 CFS# 2012-010971\r\n",
        "SRC:AMELFD",
        "UNIT:08",
        "BOX:18",
        "CALL:TRAFFIC VIOLATION/COMPLAINT/HA",
        "ADDR:CRALLES RD & 608",  // According to Google, Cralles rd doesn't extend to 608
        "ID:2012-010971");

    doTest("T10",
        "(AMELFD 03) MAILBOX:11 ALLERGIES / ENVENOMATIONS 005350 CEDAR LN AME CFS# 2012-010967 CROSS: 5901 DENNISVILLE RD/5901 DENNISVILLE RD\r\n",
        "SRC:AMELFD",
        "UNIT:03",
        "BOX:11",
        "CALL:ALLERGIES / ENVENOMATIONS",
        "ADDR:5350 CEDAR LN",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010967",
        "X:5901 DENNISVILLE RD/5901 DENNISVILLE RD");

    doTest("T11",
        "(AMELFD 04) MAILBOX:12 BREATHING PROBLEMS 008830 VIRGINIA ST AME CFS# 2012-010937\r\n",
        "SRC:AMELFD",
        "UNIT:04",
        "BOX:12",
        "CALL:BREATHING PROBLEMS",
        "ADDR:8830 VIRGINIA ST",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010937");

    doTest("T12",
        "(AMELFD 00) MAILBOX:56 SICK PERSON 015835 GOODES BRIDGE RD CFS# 2012-010936\r\n",
        "SRC:AMELFD",
        "UNIT:00",
        "BOX:56",
        "CALL:SICK PERSON",
        "ADDR:15835 GOODES BRIDGE RD",
        "ID:2012-010936");

    doTest("T13",
        "(AMELFD 13) MAILBOX:20 HEMORRHAGE / LACERATIONS 015175 PATRICK HENRY HWY CFS# 2012-010889\r\n",
        "SRC:AMELFD",
        "UNIT:13",
        "BOX:20",
        "CALL:HEMORRHAGE / LACERATIONS",
        "ADDR:15175 PATRICK HENRY HWY",
        "ID:2012-010889");

    doTest("T14",
        "(AMELFD 10) MAILBOX:08 STRUCTURE FIRE 009300 MILITARY RD AME CFS# 2012-010886\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:08",
        "CALL:STRUCTURE FIRE",
        "ADDR:9300 MILITARY RD",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010886");

    doTest("T15",
        "(AMELFD 05) MAILBOX:00 OUTSIDE FIRE 000000 MAPLEWOOD RD CFS# 2012-010878\r\n",
        "SRC:AMELFD",
        "UNIT:05",
        "BOX:00",
        "CALL:OUTSIDE FIRE",
        "ADDR:MAPLEWOOD RD",
        "ID:2012-010878");

    doTest("T16",
        "(AMELFD 15) MAILBOX:50 BREATHING PROBLEMS 015105 PATRICK HENRY HWY AME CFS# 2012-010845\r\n",
        "SRC:AMELFD",
        "UNIT:15",
        "BOX:50",
        "CALL:BREATHING PROBLEMS",
        "ADDR:15105 PATRICK HENRY HWY",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010845");

    doTest("T17",
        "(AMELFD 10) MAILBOX:55 BREATHING PROBLEMS 017600 EGGLESTETTON RD AME CFS# 2012-010804\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:55",
        "CALL:BREATHING PROBLEMS",
        "ADDR:17600 EGGLESTETTON RD",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010804");

    doTest("T18",
        "(AMELFD 19) MAILBOX:46 ABDOMINAL PAIN / PROBLEMS 008500 WOODLAND DR AME CFS# 2012-010751\r\n",
        "SRC:AMELFD",
        "UNIT:19",
        "BOX:46",
        "CALL:ABDOMINAL PAIN / PROBLEMS",
        "ADDR:8500 WOODLAND DR",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010751");

    doTest("T19",
        "(AMELFD 15) MAILBOX:58 OUTSIDE FIRE 000000 SOAP STONE RD CFS# 2012-010739\r\n",
        "SRC:AMELFD",
        "UNIT:15",
        "BOX:58",
        "CALL:OUTSIDE FIRE",
        "ADDR:SOAP STONE RD",
        "ID:2012-010739");

    doTest("T20",
        "(AMELFD 11) MAILBOX:52 UNCONSCIOUS / FAINTING 000000 FLIPPEN LN CFS# 2012-010730\r\n",
        "SRC:AMELFD",
        "UNIT:11",
        "BOX:52",
        "CALL:UNCONSCIOUS / FAINTING",
        "ADDR:FLIPPEN LN",
        "ID:2012-010730");

    doTest("T21",
        "(AMELFD 12) MAILBOX:37 UNKNOWN PROBLEM / MAN DOWN 017041 LEIDIG ST AME CFS# 2012-010702\r\n",
        "SRC:AMELFD",
        "UNIT:12",
        "BOX:37",
        "CALL:UNKNOWN PROBLEM / MAN DOWN",
        "ADDR:17041 LEIDIG ST",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010702");

    doTest("T22",
        "(AMELFD 16) MAILBOX:49 SPECIAL ASSIGNMENT 000000 DOWNTOWN AMELIA CFS# 2012-010588\r\n",
        "SRC:AMELFD",
        "UNIT:16",
        "BOX:49",
        "CALL:SPECIAL ASSIGNMENT",
        "ADDR:DOWNTOWN AMELIA",
        "ID:2012-010588");

    doTest("T23",
        "(AMELFD 15) MAILBOX:56 TRAFFIC/TRANSPORTATION ACCIDEN CFS# 2012-010587\r\n",
        "SRC:AMELFD",
        "UNIT:15",
        "BOX:56",
        "CALL:TRAFFIC/TRANSPORTATION ACCIDEN",
        "ID:2012-010587");

    doTest("T24",
        "(AMELFD 10) MAILBOX:13 TRAUMATIC INJURIES 004706 MILLS LN AME CFS# 2012-010490\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:13",
        "CALL:TRAUMATIC INJURIES",
        "ADDR:4706 MILLS LN",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010490");

    doTest("T25",
        "(AMELFD 09) MAILBOX:27 HEADACHE 007701 CHEATHAMS RD CFS# 2012-010466 CROSS: FIVE FORKS RD/HW 360 EAST\r\n",
        "SRC:AMELFD",
        "UNIT:09",
        "BOX:27",
        "CALL:HEADACHE",
        "ADDR:7701 CHEATHAMS RD",
        "ID:2012-010466",
        "X:FIVE FORKS RD/HW 360 EAST");

    doTest("T26",
        "(AMELFD 00) MAILBOX:01 UNKNOWN PROBLEM / MAN DOWN 013341 WEST LN CFS# 2012-010420\r\n",
        "SRC:AMELFD",
        "UNIT:00",
        "BOX:01",
        "CALL:UNKNOWN PROBLEM / MAN DOWN",
        "ADDR:13341 WEST LN",
        "ID:2012-010420");

    doTest("T27",
        "(AMELFD 09) MAILBOX:52 BACK PAIN 009100 VIRGINIA ST AME CFS# 2012-010392\r\n",
        "SRC:AMELFD",
        "UNIT:09",
        "BOX:52",
        "CALL:BACK PAIN",
        "ADDR:9100 VIRGINIA ST",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010392");

    doTest("T28",
        "(AMELFD 05) MAILBOX:29 CHEST PAIN 013401 CHULA RD CFS# 2012-010388\r\n",
        "SRC:AMELFD",
        "UNIT:05",
        "BOX:29",
        "CALL:CHEST PAIN",
        "ADDR:13401 CHULA RD",
        "ID:2012-010388");

    doTest("T29",
        "(AMELFD 14) MAILBOX:15 HEMORRHAGE / LACERATIONS 015721 FIVE FORKS RD CFS# 2012-010333\r\n",
        "SRC:AMELFD",
        "UNIT:14",
        "BOX:15",
        "CALL:HEMORRHAGE / LACERATIONS",
        "ADDR:15721 FIVE FORKS RD",
        "ID:2012-010333");

    doTest("T30",
        "(AMELFD 02) MAILBOX:31 STRUCTURE FIRE 001506 FOREST HILL LN AME CFS# 2012-010324\r\n",
        "SRC:AMELFD",
        "UNIT:02",
        "BOX:31",
        "CALL:STRUCTURE FIRE",
        "ADDR:1506 FOREST HILL LN",  // Not mapping
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-010324");

    doTest("T31",
        "(AMELFD 10) MAILBOX:55 STRUCTURE FIRE 016540 GOODES BRIDGE RD AME CFS# 2012-009048\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:55",
        "CALL:STRUCTURE FIRE",
        "ADDR:16540 GOODES BRIDGE RD",
        "CITY:AMELIA COURT HOUSE",
        "ID:2012-009048");

    doTest("T32",
        "(AMELFD 10) MAILBOX:52 CARDIAC / RESPIRATORY ARREST 008910 OTTERBURN RD AME CFS# 2008-003418\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:52",
        "CALL:CARDIAC / RESPIRATORY ARREST",
        "ADDR:8910 OTTERBURN RD",
        "CITY:AMELIA COURT HOUSE",
        "ID:2008-003418");

    doTest("T33",
        "(AMELFD 10) MAILBOX:49 AIRCRAFT EMERGENCY 008741 N FIVE FORKS RD AME CFS# 2007-007793\r\n",
        "SRC:AMELFD",
        "UNIT:10",
        "BOX:49",
        "CALL:AIRCRAFT EMERGENCY",
        "ADDR:8741 N FIVE FORKS RD",
        "CITY:AMELIA COURT HOUSE",
        "ID:2007-007793");

  }
  
  public static void main(String args[]) {
    new VAAmeliaCountyParserTest().generateTests("T1");
  }
}
  