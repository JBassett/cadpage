package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Chengango County, NY
Contact: Joshua VanSteenburg <jvansteenburg1985@gmail.com>
Sender: cad@co.chenango.ny.us

[911 EVENT]  BREATHING PROBLEMS|XTRA MART- GREENE|94 GENESEE ST  STA GREENE XS STATE HWY 12 /BIRDSALL ST|NARR PROQA DETAIL:DELTA 06D02 ABD PAIN| YOU ARE RESPON
[911 EVENT]  STANDBY|GREENE HIGH SCHOOL|40 S CANAL ST  STA GREENE XS PAGE ST/S CHENANGO ST EXT
[911 EVENT]  CHEST PAIN|GREENE FAMILY MEDICINE|29 N CHENANGO ST  STA GREENE XS SCOTT  AV/ELM ST|NARR PROQA DETAIL:CHARLIE 10C04 CHEST PAIN| YOU ARE RESPONDING
[911 EVENT]  HEMORRHAGE / LACERATION|NYS VETS HOME|4207 STATE HWY 220   STA OXFORD XS COUNTY RD 35 /COUNTY RD 32|NARR SPRUCE COTTAGE - 68YOM - NOSE BLEED - 21-
[911 EVENT]  STRUCTURE FIRE/RESIDENTIAL||103 CURTIS COURT   STA SMITHVILLE XS ROUND POND  RD/***DEAD END***|NARR PERSON: (COMPLAINANT) (FMLS) VICKY CURTIS  NO

Contact: support@active911.com
[911 EVENT] MVA- UNKNOWN||  |STA NEW BERLIN XS STATE HWY 8 |NARR 1 CAR MVA OVER THE GARD RAIL UNKNOWN ON INJURIES BY THE OLD ROOSTER TAIL  1 MILE 1/2 SOUTH OF THE VILLAGE  PERSON: (COMPLAINANT) (FMLS) TERESA  HYZER  DARK COLORED CAR\n
[911 EVENT] TRAUMATIC INJURIES (SPECIFIC)|UNADILLA VALLEY CENTRAL SCHOOL| 4238 STATE HWY 8,NEW BERLIN |STA NB/UVAC XS GOLF COURSE  RD/RAINBOW LN|NARR PROQA SUMMARY:BRAVO 30B01 FEMALE HIT IN THE FOREHEAD| 11 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. TRAUMATIC INJURIES (SPECIFIC).\n
[911 EVENT] UNCONSCIOUS/FAINTING (NEAR)|UNADILLA VALLEY CENTRAL SCHOOL| 4238 STATE HWY 8,NEW BERLIN |STA NB/UVAC XS GOLF COURSE  RD/RAINBOW LN|NARR PROQA SUMMARY:DELTA 31D03 UNRESPONSIVE| 45 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. UNCONSCIOUS / FAINTING (NEAR).\n

[911 EVENT] BREATHING PROBLEMS|| 153 COUNTY RD 23A,N NORWICH |STA N NORWICH XS SHERBURNE FOUR CORNERS  RD/COUNTY RD 23|NARR PROQA SUMMARY:DELTA 06D02 BACK PAIN| 41 YEAR OLD, MALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.\n
[911 EVENT] MUTUAL AID REQUEST|| 122 VALLEY VIEW RD,UNADILLA |STA BAINBRIDGE XS  |NARR REQUEST FROM SIDNEY FIRE, ONE TANKER TO THE SCENE OF A BUILDING FIRE AT WASTE RECOVERY IN SIDNEY FIRE DISTRICT. PERSON: (COMPLAINANT) (FMLS)   DELAWARE COUNTY  REQUEST FROM SIDNEY 1 ENGINE TO LOCATION FOR WATER SUPPLY FROM BAINBRIDGE\n
[911 EVENT] BREATHING PROBLEMS|GILMOUR HEALTH CARE FACILITY| 88 CALVARY DR,NORWICH CITY |STA NORWICH XS STATE HWY 12 /WALES DR|NARR PROQA SUMMARY:DELTA 06D01 STATES DIFF BREATHING| 94 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.\n
[911 EVENT] HEART PROBLEMS / A.I.C.D|| 15 GROVE AV,NORWICH CITY |APT 1 |STA NORWICH XS MAYDOLE  ST/E MAIN ST|NARR PROQA SUMMARY:DELTA 19D04 HEART IS RACING| 58 YEAR OLD, MALE, CONSCIOUS, BREATHING. HEART PROBLEMS / A.\n
[911 EVENT] FIRE ALARM|| 61 PLYMOUTH ST,NORWICH CITY |STA NORWICH XS BEECH  ST/FREDERICK B MIRABITO ST|NARR CALLER STATES THAT HER SMOKE DECTOR IS GOING OF AND SHE SEES NO SMOKE OR FIRE. PERSON: (COMPLAINANT) (FMLS) CLAIRE  QUATTROCCHI\n
[911 EVENT] SMOKE INV. - OUTSIDE|GUS STEAKHOUSE| 5698 STATE HWY 12,NORWICH |STA NORWICH XS ***CITY LINE*** /PORTELLI DR|NARR LARGE PLUME OF BLACK SMOKE - CAN SEE IT FROM GUS' STEAK HOUSE PERSON: (COMPLAINANT) (FMLS) TIM  BURGER\n

*/

public class NYChenangoCountyParserTest extends BaseParserTest {
  
  public NYChenangoCountyParserTest() {
    setParser(new NYChenangoCountyParser(), "CHENANGO COUNTY", "NY");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "1 of 10\n" +
        "FRM:CAD@otsegocounty.com\n" +
        "SUBJ:911 EVENT\n" +
        "MSG:HAZARD ALL|WILBER NATIONAL BANK - COOPERS|5378 ST HWY 28   STA COOP3 XS CO HWY 26\n" +
        "(Con't) 2 of 10\n" +
        "/WALNUT|09:04|NARR SOUTH OF BANK MVA PDAA NEED FIR POLICE  PERSON: (COMPLAINANT) (FMLS) TIM  DONLAN\n" +
        "Disclaimer:\n\n" +
        "This communication,\n" +
        "(Con't 3 of 10\n" +
        "including any attachments, may contain confidential information and is intended only for \n" +
        "the individual or entity to whom it is\n" +
        "(Con't) 4 of 10\n" +
        "addressed. Any review, dissemination, or copying of this communication \n" +
        "by anyone other than the intended recipient is strictly\n" +
        "(Con't) 5 of 10\n" +
        "prohibited. If you are not the intended recipient, please \n" +
        "contact the sender by reply e-mail, delete and destroy all copies of the origi\n" +
        "More?",

        "CALL:HAZARD ALL",
        "PLACE:WILBER NATIONAL BANK - COOPERS",
        "ADDR:5378 ST HWY 28",
        "MADDR:5378 NY 28",
        "SRC:COOP3",
        "X:CO HWY 26 /WALNUT",
        "TIME:09:04",
        "INFO:SOUTH OF BANK MVA PDAA NEED FIR POLICE  PERSON: (COMPLAINANT) (FMLS) TIM  DONLAN");

    doTest("T2",
        "1 of 10\n" +
        "FRM:CAD@otsegocounty.com\n" +
        "SUBJ:911 EVENT\n" +
        "MSG:MVA PD|GRASSLANDS|ST HWY 28   STA COUNTY XS  |09:00|NARR 1 CAR PDMVA PERSON: (COMPLAINANT)\n" +
        "(Con' 2 of 10\n" +
        "(FMLS) SUE  JENNINGS\n" +
        "Disclaimer:\n" +
        "This communication, including any attachments, may contain confidential information and is intended\n" +
        "(Con't) 3 of 10\n" +
        "only for the individual or entity to whom it is addressed. Any review, dissemination, or copying of this communication by anyone\n" +
        "(Con't) 4 of 10\n" +
        "other than the intended recipient is strictly prohibited. If you are not the intended recipient, please contact the sender by reply\n" +
        "(Con't) 5 of 10\n" +
        "e-mail, delete and destroy all copies of the original message. No responsibility is accepted by Otsego County Government for any loss or\n" +
        "More?",

        "CALL:MVA PD",
        "PLACE:GRASSLANDS",
        "ADDR:ST HWY 28",
        "MADDR:NY 28",
        "SRC:COUNTY",
        "TIME:09:00",
        "INFO:1 CAR PDMVA PERSON: (COMPLAINANT) (FMLS) SUE  JENNINGS");

    doTest("T3",
        "1 of 10\n" +
        "FRM:CAD@otsegocounty.com\n" +
        "SUBJ:911 EVENT\n" +
        "MSG:TRAFFIC ACCIDENTS|| STA MIDD1 XS ST HWY 166 /CO HWY 52|08:22|NARR CAR OVER THE BANK -\n" +
        "(Con't) 2 of 10\n" +
        "ROLLOVER PERSON: (COMPLAINANT)\n" +
        "Disclaimer:\n" +
        "This communication, including any attachments, may contain confidential information and\n" +
        "(Con't) 3 of 10\n" +
        "is intended only for the individual or entity to whom it is addressed. Any review, dissemination, or copying of this communication\n" +
        "(Con't) 4 of 10\n" +
        "by anyone other than the intended recipient is strictly prohibited. If you are not the intended recipient, please contact the sender\n" +
        "(Con't) 5 of 10\n" +
        "by reply e-mail, delete and destroy all copies of the original message. No responsibility is accepted by Otsego County Government for an\n" +
        "More?",

        "CALL:TRAFFIC ACCIDENTS",
        "ADDR:ST HWY 166 & CO HWY 52",
        "MADDR:NY 166 & COUNTY ROAD 52",
        "SRC:MIDD1",
        "TIME:08:22",
        "INFO:CAR OVER THE BANK - ROLLOVER PERSON: (COMPLAINANT)");

    doTest("T4",
        "1 of 10\n" +
        "FRM:CAD@otsegocounty.com\n" +
        "SUBJ:911 EVENT\n" +
        "MSG:HAZARD ALL|| STA COOP4 XS CO HWY 52 /GATEWAY LN|08:14|NARR 2 CAR MVA NO\n" +
        "(Con't) 2 of 10\n" +
        "INJ\n" +
        "Disclaimer:\n" +
        "This communication, including any attachments, may contain confidential information and is intended only for the\n" +
        "(Con't) 3 of 10\n" +
        "individual or entity to whom it is addressed. Any review, dissemination, or copying of this communication by anyone other than the\n" +
        "(Con't) 4 of 10\n" +
        "intended recipient is strictly prohibited. If you are not the intended recipient, please contact the sender by reply e-mail, delete\n" +
        "(Con't) 5 of 10\n" +
        "and destroy all copies of the original message. No responsibility is accepted by Otsego County Government for any loss or damage arising\n" +
        "More?",

        "CALL:HAZARD ALL",
        "ADDR:CO HWY 52 & GATEWAY LN",
        "MADDR:COUNTY ROAD 52 & GATEWAY LN",
        "SRC:COOP4",
        "TIME:08:14",
        "INFO:2 CAR MVA NO INJ");

    doTest("T5",
        "1 of 10\n" +
        "FRM:CAD@otsegocounty.com\n" +
        "SUBJ:911 EVENT\n" +
        "MSG:TRAFFIC ACCIDENTS||4 GLEN AV  STA COOP1 XS CHESTNUT /BRUNLAR CT|13:06|NARR WPH2-S\n" +
        "(Con't) 2 of 10\n" +
        "VEH/BICYCLE -  PERSON: (COMPLAINANT) (FMLS) ZACK  MAHLUM\n" +
        "Disclaimer:\n" +
        "This communication, including any attachments, may contain\n" +
        "(Con't) 3 of 10\n" +
        "confidential information and is intended only for the individual or entity to whom it is addressed. Any review, dissemination, or\n" +
        "(Con't) 4 of 10\n" +
        "copying of this communication by anyone other than the intended recipient is strictly prohibited. If you are not the intended\n" +
        "(Con't) 5 of 10\n" +
        "recipient, please contact the sender by reply e-mail, delete and destroy all copies of the original message. No responsibility is acce\n" +
        "More?",

        "CALL:TRAFFIC ACCIDENTS",
        "ADDR:4 GLEN AV",
        "MADDR:4 GLEN AVE",
        "SRC:COOP1",
        "X:CHESTNUT /BRUNLAR CT",
        "TIME:13:06",
        "INFO:WPH2-S VEH/BICYCLE -  PERSON: (COMPLAINANT) (FMLS) ZACK  MAHLUM");
    
  }
  
  @Test
  public void testActive911() {

    doTest("T1",
        "[911 EVENT] MVA- UNKNOWN||  |STA NEW BERLIN XS STATE HWY 8 |NARR 1 CAR MVA OVER THE GARD RAIL UNKNOWN ON INJURIES BY THE OLD ROOSTER TAIL  1 MILE 1/2 SOUTH OF THE VILLAGE  PERSON: (COMPLAINANT) (FMLS) TERESA  HYZER  DARK COLORED CAR\n",
        "CALL:MVA- UNKNOWN",
        "SRC:NEW BERLIN",
        "ADDR:STATE HWY 8",
        "MADDR:STATE 8",
        "INFO:1 CAR MVA OVER THE GARD RAIL UNKNOWN ON INJURIES BY THE OLD ROOSTER TAIL  1 MILE 1/2 SOUTH OF THE VILLAGE  PERSON: (COMPLAINANT) (FMLS) TERESA  HYZER  DARK COLORED CAR");

    doTest("T2",
        "[911 EVENT] TRAUMATIC INJURIES (SPECIFIC)|UNADILLA VALLEY CENTRAL SCHOOL| 4238 STATE HWY 8,NEW BERLIN |STA NB/UVAC XS GOLF COURSE  RD/RAINBOW LN|NARR PROQA SUMMARY:BRAVO 30B01 FEMALE HIT IN THE FOREHEAD| 11 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. TRAUMATIC INJURIES (SPECIFIC).\n",
        "CALL:TRAUMATIC INJURIES (SPECIFIC)",
        "PLACE:UNADILLA VALLEY CENTRAL SCHOOL",
        "ADDR:4238 STATE HWY 8",
        "MADDR:4238 STATE 8",
        "SRC:NB/UVAC",
        "X:GOLF COURSE  RD/RAINBOW LN",
        "CITY:NEW BERLIN",
        "INFO:PROQA SUMMARY:BRAVO 30B01 FEMALE HIT IN THE FOREHEAD / 11 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. TRAUMATIC INJURIES (SPECIFIC).");

    doTest("T3",
        "[911 EVENT] UNCONSCIOUS/FAINTING (NEAR)|UNADILLA VALLEY CENTRAL SCHOOL| 4238 STATE HWY 8,NEW BERLIN |STA NB/UVAC XS GOLF COURSE  RD/RAINBOW LN|NARR PROQA SUMMARY:DELTA 31D03 UNRESPONSIVE| 45 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. UNCONSCIOUS / FAINTING (NEAR).\n",
        "CALL:UNCONSCIOUS/FAINTING (NEAR)",
        "PLACE:UNADILLA VALLEY CENTRAL SCHOOL",
        "ADDR:4238 STATE HWY 8",
        "MADDR:4238 STATE 8",
        "SRC:NB/UVAC",
        "X:GOLF COURSE  RD/RAINBOW LN",
        "CITY:NEW BERLIN",
        "INFO:PROQA SUMMARY:DELTA 31D03 UNRESPONSIVE / 45 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. UNCONSCIOUS / FAINTING (NEAR).");

    doTest("T4",
        "[911 EVENT] BREATHING PROBLEMS|| 153 COUNTY RD 23A,N NORWICH |STA N NORWICH XS SHERBURNE FOUR CORNERS  RD/COUNTY RD 23|NARR PROQA SUMMARY:DELTA 06D02 BACK PAIN| 41 YEAR OLD, MALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.\n",
        "CALL:BREATHING PROBLEMS",
        "ADDR:153 COUNTY RD 23A",
        "MADDR:153 COUNTY ROAD 23A",
        "SRC:N NORWICH",
        "X:SHERBURNE FOUR CORNERS  RD/COUNTY RD 23",
        "CITY:N NORWICH",
        "INFO:PROQA SUMMARY:DELTA 06D02 BACK PAIN / 41 YEAR OLD, MALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.");

    doTest("T5",
        "[911 EVENT] MUTUAL AID REQUEST|| 122 VALLEY VIEW RD,UNADILLA |STA BAINBRIDGE XS  |NARR REQUEST FROM SIDNEY FIRE, ONE TANKER TO THE SCENE OF A BUILDING FIRE AT WASTE RECOVERY IN SIDNEY FIRE DISTRICT. PERSON: (COMPLAINANT) (FMLS)   DELAWARE COUNTY  REQUEST FROM SIDNEY 1 ENGINE TO LOCATION FOR WATER SUPPLY FROM BAINBRIDGE\n",
        "CALL:MUTUAL AID REQUEST",
        "ADDR:122 VALLEY VIEW RD",
        "SRC:BAINBRIDGE",
        "CITY:UNADILLA",
        "INFO:REQUEST FROM SIDNEY FIRE, ONE TANKER TO THE SCENE OF A BUILDING FIRE AT WASTE RECOVERY IN SIDNEY FIRE DISTRICT. PERSON: (COMPLAINANT) (FMLS)   DELAWARE COUNTY  REQUEST FROM SIDNEY 1 ENGINE TO LOCATION FOR WATER SUPPLY FROM BAINBRIDGE");

    doTest("T6",
        "[911 EVENT] BREATHING PROBLEMS|GILMOUR HEALTH CARE FACILITY| 88 CALVARY DR,NORWICH CITY |STA NORWICH XS STATE HWY 12 /WALES DR|NARR PROQA SUMMARY:DELTA 06D01 STATES DIFF BREATHING| 94 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.\n",
        "CALL:BREATHING PROBLEMS",
        "PLACE:GILMOUR HEALTH CARE FACILITY",
        "ADDR:88 CALVARY DR",
        "SRC:NORWICH",
        "X:STATE HWY 12 /WALES DR",
        "CITY:NORWICH CITY",
        "INFO:PROQA SUMMARY:DELTA 06D01 STATES DIFF BREATHING / 94 YEAR OLD, FEMALE, CONSCIOUS, BREATHING. BREATHING PROBLEMS.");

    doTest("T7",
        "[911 EVENT] HEART PROBLEMS / A.I.C.D|| 15 GROVE AV,NORWICH CITY |APT 1 |STA NORWICH XS MAYDOLE  ST/E MAIN ST|NARR PROQA SUMMARY:DELTA 19D04 HEART IS RACING| 58 YEAR OLD, MALE, CONSCIOUS, BREATHING. HEART PROBLEMS / A.\n",
        "CALL:HEART PROBLEMS / A.I.C.D",
        "ADDR:15 GROVE AV",
        "MADDR:15 GROVE AVE",
        "APT:1",
        "CITY:NORWICH CITY",
        "INFO:STA NORWICH XS MAYDOLE  ST/E MAIN ST / PROQA SUMMARY:DELTA 19D04 HEART IS RACING / 58 YEAR OLD, MALE, CONSCIOUS, BREATHING. HEART PROBLEMS / A.");

    doTest("T8",
        "[911 EVENT] FIRE ALARM|| 61 PLYMOUTH ST,NORWICH CITY |STA NORWICH XS BEECH  ST/FREDERICK B MIRABITO ST|NARR CALLER STATES THAT HER SMOKE DECTOR IS GOING OF AND SHE SEES NO SMOKE OR FIRE. PERSON: (COMPLAINANT) (FMLS) CLAIRE  QUATTROCCHI\n",
        "CALL:FIRE ALARM",
        "ADDR:61 PLYMOUTH ST",
        "SRC:NORWICH",
        "X:BEECH  ST/FREDERICK B MIRABITO ST",
        "CITY:NORWICH CITY",
        "INFO:CALLER STATES THAT HER SMOKE DECTOR IS GOING OF AND SHE SEES NO SMOKE OR FIRE. PERSON: (COMPLAINANT) (FMLS) CLAIRE  QUATTROCCHI");

    doTest("T9",
        "[911 EVENT] SMOKE INV. - OUTSIDE|GUS STEAKHOUSE| 5698 STATE HWY 12,NORWICH |STA NORWICH XS ***CITY LINE*** /PORTELLI DR|NARR LARGE PLUME OF BLACK SMOKE - CAN SEE IT FROM GUS' STEAK HOUSE PERSON: (COMPLAINANT) (FMLS) TIM  BURGER\n",
        "CALL:SMOKE INV. - OUTSIDE",
        "PLACE:GUS STEAKHOUSE",
        "ADDR:5698 STATE HWY 12",
        "MADDR:5698 STATE 12",
        "SRC:NORWICH",
        "X:***CITY LINE*** /PORTELLI DR",
        "CITY:NORWICH",
        "INFO:LARGE PLUME OF BLACK SMOKE - CAN SEE IT FROM GUS' STEAK HOUSE PERSON: (COMPLAINANT) (FMLS) TIM  BURGER");

  }
  
  public static void main(String[] args) {
    new NYChenangoCountyParserTest().generateTests("T4");
  }
}