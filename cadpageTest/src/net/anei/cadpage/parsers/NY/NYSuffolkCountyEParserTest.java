package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NYSuffolkCountyEParserTest extends BaseParserTest {
  
  public NYSuffolkCountyEParserTest() {
    setParser(new NYSuffolkCountyEParser(), "SUFFOLK COUNTY", "NY");
  }
  
  @Test
  public void testParser1() {

    doTest("T1",
        "(FROM RELAY) 33 BAY RUN OFF OF HERRICKS LN JAMESPORT\nELDERLY MALE SICK",
        "ADDR:33 BAY RUN",
        "X:HERRICKS LN",
        "CITY:JAMESPORT",
        "INFO:ELDERLY MALE SICK");

    doTest("T2",
        "(FROM RELAY) 415 OSTRANDER AVE, INJURY FROM A FALL",
        "ADDR:415 OSTRANDER AVE",
        "INFO:INJURY FROM A FALL");

    doTest("T3",
        "(FROM RELAY) 95 OSTRANDER AVE, DIFFICULTY BREATHING",
        "ADDR:95 OSTRANDER AVE",
        "INFO:DIFFICULTY BREATHING");
  }
  
  @Test
  public void testParser2() {

    doTest("T1",
        "(FROM RELAY) 236 MAIN RD AQUEBOGUE; DIRECTLY ACROSS FROM FEDUN REALITY 92 Y/O FEMALE \nCHEST PAINS",
        "ADDR:236 MAIN RD AQUEBOGUE; DIRECTLY ACROSS FROM FEDUN REALITY 92 Y & O FEMALE",
        "MADDR:236 MAIN RD AQUEBOGUE; DIRECTLY ACROSS FROM FEDUN REALITY 92 Y",
        "INFO:CHEST PAINS");

    doTest("T2",
        "(FROM RELAY) 2185 SPLISH SPLASH DRIVE FEMALE POSSIBLE STROKE",
        "ADDR:2185 SPLISH SPLASH DRIVE",
        "INFO:FEMALE POSSIBLE STROKE");

    doTest("T3",
        "(FROM RELAY) 178  PECONIC BAY BLVD IN AQUEBOGUE   AT THE  REEVE RESIDENCE SUBJECT \nSEIZURES",
        "PLACE:178",
        "ADDR:PECONIC BAY BLVD IN",
        "MADDR:178,PECONIC BAY BLVD IN",
        "CITY:AQUEBOGUE",
        "INFO:AT THE - REEVE RESIDENCE SUBJECT - SEIZURES");

    doTest("T4",
        "(FROM RELAY) 1053 OLD COUNTRY RD PEARLE VISION CENTER  FEMALE HEAD INJURY FROM A FALL",
        "ADDR:1053 OLD COUNTRY RD PEARLE VISION CENTER",
        "INFO:FEMALE HEAD INJURY FROM A FALL");

    doTest("T5",
        "(FROM RELAY) 70 SCHULTZ RD MALE HEAD INJURY FROM A FALL ,, ACROSS FROM THE SPORTSMAN \nKENNEL IN MANORVILLE",
        "ADDR:70 SCHULTZ RD MALE HEAD INJURY FROM A FALL",
        "INFO:ACROSS FROM THE SPORTSMAN - KENNEL IN MANORVILLE");

    doTest("T6",
        "(FROM RELAY) 1272 EAST MAIN ST  DR. BROOK'S OFC  MALE  FAINTING EPISODES",
        "ADDR:1272 EAST MAIN ST",
        "INFO:DR BROOK'S OFC - MALE - FAINTING EPISODES");

    doTest("T7",
        "(MSG FROM RELAY) MICHAEL'S 1440 OLD COUNTRY RD. FEMALE. GENERAL WEAKNESS.",
        "PLACE:MICHAEL'S",
        "ADDR:1440 OLD COUNTRY RD",
        "INFO:FEMALE GENERAL WEAKNESS");
  }
  
  @Test
  public void testJimLull() {

    doTest("T1",
        "/RELAY /201 TROT BROOK LANE FEMALE FACIAL INJURY\n",
        "ADDR:201 TROT BROOK LANE",
        "INFO:FEMALE FACIAL INJURY");

    doTest("T2",
        "/FROM RELAY /1035 PARKWAY\nFEMALE WITH CHEST PAINS\n",
        "ADDR:1035 PARKWAY",
        "INFO:FEMALE WITH CHEST PAINS");

    doTest("T3",
        "/FROM RELAY /641 NORTHVILLE TPKE & FISHEL\nFEMALE IS SICK\n",
        "ADDR:641 NORTHVILLE TPKE & FISHEL",
        "MADDR:641 NORTHVILLE TPKE",
        "INFO:FEMALE IS SICK");

    doTest("T4",
        "/FROM RELAY /MERCY HIGH SCHOOL\nYOUNG ADULT HEAD INJURY\nWEIGHT ROOM\n",
        "ADDR:MERCY HIGH SCHOOL",
        "INFO:YOUNG ADULT HEAD INJURY - WEIGHT ROOM");

    doTest("T5",
        "/FROM RELAY /ATLANTIS MARINE WORLD\nGIFT SHOP  FEMALE INJURIES FROM A FALL\n",
        "ADDR:ATLANTIS MARINE WORLD",
        "INFO:GIFT SHOP - FEMALE INJURIES FROM A FALL");

    doTest("T6",
        "/FROM RELAY /819 E MAIN\nDEPT OF SOCIAL SVCS\nPOSSIBLE OVER DOSE\n",
        "ADDR:819 E MAIN",
        "INFO:DEPT OF SOCIAL SVCS - POSSIBLE OVER DOSE");

    doTest("T7",
        "/FROM RELAY /UNITED COMPREHENSIVE CARE\n170 OLD COUNTRY RD\nADULT MALE TROUBLE BREATHING\n",
        "PLACE:UNITED COMPREHENSIVE CARE",
        "ADDR:170 OLD COUNTRY RD",
        "INFO:ADULT MALE TROUBLE BREATHING");

    doTest("T8",
        "/FROM RELAY /TANGER 2 STE 101 NY&CO\nFEMALE SUBJECT ILL\n",
        "ADDR:TANGER 2 STE 101 NY & CO",
        "INFO:FEMALE SUBJECT ILL");

    doTest("T9",
        "/MSG TO RELAY /NORTHVILLE TURNPIKE & EAST AVE: INJURY NECK & BACK:MVA\n",
        "ADDR:NORTHVILLE TURNPIKE & EAST AVE",
        "INFO:INJURY NECK & BACK - MVA");

    doTest("T10",
        "/FROM RELAY /641 DOCTOR'S PATH APT 7  FEMALE SICK\n",
        "ADDR:641 DOCTOR'S PATH",
        "APT:7",
        "INFO:FEMALE SICK");

    doTest("T11",
        "/FROM RELAY /1145 MIDDLE RD  RIVERHEAD LANDINGS  APT 7A  ELDERLY FEMALE INJURY FROM A \nFALL\n",
        "ADDR:1145 MIDDLE RD",
        "INFO:RIVERHEAD LANDINGS - APT 7A - ELDERLY FEMALE INJURY FROM A - FALL");

    doTest("T12",
        "/FRTOM RELAY /20 EAST MAIN ST   CULINARY ART SCHOOL   FEMALE  FOOD ALLERGY\n",
        "ADDR:20 EAST MAIN ST",
        "INFO:CULINARY ART SCHOOL - FEMALE - FOOD ALLERGY");

  }
  
  public static void main(String[] args) {
    new NYSuffolkCountyEParserTest().generateTests("T9", "PLACE ADDR APT CITY INFO");
  }

}