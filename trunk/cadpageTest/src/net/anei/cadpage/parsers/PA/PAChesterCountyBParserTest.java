package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class PAChesterCountyBParserTest extends BaseParserTest {
  
  public PAChesterCountyBParserTest() {
    setParser(new PAChesterCountyBParser(), "CHESTER COUNTY", "PA");
  }
  
  @Test
  public void testBadMsg() {
    doBadTest("Initial Type: BSICK      Final Type: BSICK   (SICK PERSON - BLS *)\nLoc: 415 MEGAN CT ,62  btwn VICTORIA GARDENS DR & AZALEA LA (V)\nAKA");
    doBadTest("Initial Type: ACCINJA    Final Type:ACCINJA (ACCIDENT - INJURIES / ALS *)\nLoc: W BALTIMORE PK/SCARLETT RD ,60    (V) AKA:");
  }
    
  
  @Test
  public void testParser1() {

    doTest("T1",
        "OVERDOSE - ALS *\n300 CEDAR SPRINGS RD - LIBERTY KNOLL APTS\nW BALTIMORE PK & CEDAR WOODS CI\nAPT 205\nNGARDN\n3RD HAND INFO/RP NOT ON LOC/UNK AGE MALE SAID HE OD'S ON ON PILLS/RP PUT ME ON HOLD\n02/21/2011\n15:09\n610-388-7400",
        "CALL:OVERDOSE - ALS *",
        "ADDR:300 CEDAR SPRINGS RD",
        "APT:205",
        "X:W BALTIMORE PK & CEDAR WOODS CI",
        "INFO:3RD HAND INFO/RP NOT ON LOC/UNK AGE MALE SAID HE OD'S ON ON PILLS/RP PUT ME ON HOLD",
        "CITY:NEW GARDEN TWP",
        "PLACE:LIBERTY KNOLL APTS",
        "PHONE:610-388-7400");

    doTest("T2",
        "UNKNOWN TYPE FIRE *\n10 ALTEMUS DR\nWATSON MILL RD & DEAD END\nBROAD RUN KNOLL\nACROSS FROM ABV 1/4 MILE\nNGARDN\nSEES FIRE UNSURE IF ITS A BUILDING OR TRASH\n02/21/2011\n08:27\nRIOFSKI, LINDA P\n610-274-0906",
        "CALL:UNKNOWN TYPE FIRE *",
        "ADDR:10 ALTEMUS DR",
        "X:WATSON MILL RD & DEAD END",
        "INFO:BROAD RUN KNOLL / ACROSS FROM ABV 1/4 MILE / SEES FIRE UNSURE IF ITS A BUILDING OR TRASH",
        "CITY:NEW GARDEN TWP",
        "NAME:RIOFSKI, LINDA P",
        "PHONE:610-274-0906");

    doTest("T3",
        "HOUSE FIRE *\n109 GARDEN STATION RD\nE AVONDALE RD & WHITESTONE RD\nLGROVE\nSTOVE INSIDE BASMENT ON FIRE\n02/20/2011\n17:50\nA/F\n610-322-0944/C",
        "CALL:HOUSE FIRE *",
        "ADDR:109 GARDEN STATION RD",
        "X:E AVONDALE RD & WHITESTONE RD",
        "INFO:STOVE INSIDE BASMENT ON FIRE",
        "CITY:LONDON GROVE TWP",
        "NAME:A/F",
        "PHONE:610-322-0944/C");

    doTest("T4",
        "ALARM - FIRE *\n8822 GAP NEWPORT PK\nCROSSAN LA & PENN GREEN RD\nRESD - LAFFERTY 610-268-2861\nNGARDN\nSMOKE DETECTOR IN KITCHEN - ENTER THRU BACK DO OR\n02/12/2011\n19:57\nLIFE ALERT-645\n800-638-8222",
        "CALL:ALARM - FIRE *",
        "ADDR:8822 GAP NEWPORT PK",
        "X:CROSSAN LA & PENN GREEN RD",
        "INFO:RESD - LAFFERTY 610-268-2861 / SMOKE DETECTOR IN KITCHEN - ENTER THRU BACK DO OR",
        "CITY:NEW GARDEN TWP",
        "NAME:LIFE ALERT-645",
        "PHONE:800-638-8222");

    doTest("T5",
        "[Update]\nSMOKE / ODOR INVEST (OUTSIDE)\nRT 41 / RT 841\nLGROVE\nHEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM\n02/16/2011\n02:18\nROCHESTER, RANDOLF\n717-468-8174",
        "CALL:SMOKE / ODOR INVEST (OUTSIDE)",
        "ADDR:RT 41 & RT 841",
        "INFO:HEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM",
        "CITY:LONDON GROVE TWP",
        "NAME:ROCHESTER, RANDOLF",
        "PHONE:717-468-8174");

    doTest("T6",
        "SMOKE / ODOR INVEST (OUTSIDE)\nRT 41 / RT 841\nLGROVE\nHEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM\n02/16/2011\n02:18\nROCHESTER, RANDOLF\n717-468-8174",
        "CALL:SMOKE / ODOR INVEST (OUTSIDE)",
        "ADDR:RT 41 & RT 841",
        "INFO:HEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM",
        "CITY:LONDON GROVE TWP",
        "NAME:ROCHESTER, RANDOLF",
        "PHONE:717-468-8174");
  }
  
  @Test
  public void testParser2() {

    doTest("T1",
        "(Messenger 911) BSICK\n65 GOOD HOPE RD\nPARSONS RD & PEACH TREE LA\nLDNBRT\n80/F FEVER AND BEDSORES/HX: HYPERTENSION, ALZH EIMERS, DEMENTIA\n11:59",
        "CALL:BSICK",
        "ADDR:65 GOOD HOPE RD",
        "X:PARSONS RD & PEACH TREE LA",
        "CITY:LANDENBERG",
        "INFO:80/F FEVER AND BEDSORES/HX: HYPERTENSION, ALZH EIMERS, DEMENTIA");

    doTest("T2",
        "(Messenger 911) ACCINJ\n7440 LANCASTER PK, HOCKESSIN\nREQ AMB232----ASSIST NCC19 W/NUMEROUS PT'S IN ACCT\n14:07",
        "CALL:ACCINJ",
        "ADDR:7440 LANCASTER PK",
        "INFO:REQ AMB232----ASSIST NCC19 W/NUMEROUS PT'S IN ACCT",
        "CITY:HOCKESSIN");

    doTest("T3",
        "(Messenger 911) ACVA\n315 E LONDON GROVE RD - CHATHAM ACRES NH\nCHATHAM CHASE BL & HOWELL MOORE RD\nLGROVE\n56/M--CHANGE IN MENTAL STATUS--LETHARGIC--NUMB IN LEG\n20:0",
        "CALL:ACVA",
        "ADDR:315 E LONDON GROVE RD",
        "X:CHATHAM CHASE BL & HOWELL MOORE RD",
        "PLACE:CHATHAM ACRES NH",
        "INFO:56/M--CHANGE IN MENTAL STATUS--LETHARGIC--NUMB IN LEG",
        "CITY:LONDON GROVE TWP");

    doTest("T4",
        "(Messenger 911) HOUSE\n300 ELIZABETH DR\nPENNS MANOR DR & PENNS MANOR DR\nPENNS MNAOR\nKNTTWP\nDECK AND GRILL ON FIRE ATTACHED TO THE HOUSE\n09:26",
        "CALL:HOUSE",
        "ADDR:300 ELIZABETH DR",
        "X:PENNS MANOR DR & PENNS MANOR DR",
        "INFO:PENNS MNAOR / DECK AND GRILL ON FIRE ATTACHED TO THE HOUSE",
        "CITY:KENNETT TWP");

    doTest("T5",
        "(Messenger 911) BRUSH\n1027 NEWARK RD - CREATIVE PLAY DAY SCHOOL\nHILLENDALE RD & MAPLE LA\nNGARDN\nRP SEES BRUSH FIRE ACROSS FROM ABOVE---COULD S AY HOW BIG BUT APPE",
        "CALL:BRUSH",
        "ADDR:1027 NEWARK RD",
        "X:HILLENDALE RD & MAPLE LA",
        "PLACE:CREATIVE PLAY DAY SCHOOL",
        "INFO:RP SEES BRUSH FIRE ACROSS FROM ABOVE---COULD S AY HOW BIG BUT APPE",
        "CITY:NEW GARDEN TWP");

    doTest("T6",
        "(Messenger 911) BFALL\n115 E SUMMIT AV\nPARKWAY AV & MYRTLE AV\nWGROVE\n62 YO/F FELL DOWN 5 STEPS\n21:07",
        "CALL:BFALL",
        "ADDR:115 E SUMMIT AV",
        "X:PARKWAY AV & MYRTLE AV",
        "INFO:62 YO/F FELL DOWN 5 STEPS",
        "CITY:WEST GROVE");

    doTest("T7",
        "(Messenger 911) BMENTAL\n 202 E SECOND ST\n CHATHAM ST & HOOPES AL\n AVNDAL\n 20 YOA/M/, POSS ON ACT 64, SAID HE WANTED TO K ILL HIMSELF, BUT THEY DON'T THINK HE HAS DONE",
        "CALL:BMENTAL",
        "ADDR:202 E SECOND ST",
        "X:CHATHAM ST & HOOPES AL",
        "INFO:20 YOA/M/, POSS ON ACT 64, SAID HE WANTED TO K ILL HIMSELF, BUT THEY DON'T THINK HE HAS DONE",
        "CITY:AVONDALE");
 }
  
  public static void main(String[] args) {
    new PAChesterCountyBParserTest().generateTests("T7");
  }
}
