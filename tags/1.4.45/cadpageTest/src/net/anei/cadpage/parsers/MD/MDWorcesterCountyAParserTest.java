package net.anei.cadpage.parsers.MD;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.MD.MDWorcesterCountyAParser;

import org.junit.Test;


public class MDWorcesterCountyAParserTest extends BaseParserTest {
  
  public MDWorcesterCountyAParserTest() {
    setParser(new MDWorcesterCountyAParser(), "WORCESTER COUNTY", "MD");
  }
  
  @Test
  public void testBad() {
    doBadTest("CAD:ABDOMINAL PAIN/BACK PAIN;33063 STONEY CREEK RD;ATLA;A27;C1;GENE WAYNE LN;FLEMING RD");
    doBadTest("CAD:RESIDENTIAL STRUCTURE FIRE;7255 FLEMING RD;ATLA;A27;D1;KATHRYN RD;VERNON RD");
    doBadTest("CAD:ABDOMINAL PAIN/BACK PAIN;33063 STONEY CREEK RD;ATLA;A27;C1;GENE WAYNE LN;FLEMING RD");

  }
  
  @Test
  public void testParser() {

   doTest("T1",
        "CAD:300;FALLS;5914 BOX IRON RD;GIRDLETREE;PINE ST;TAYLOR LANDING RD;11/23/2010 08:31:38",
        "SRC:300",
        "CALL:FALLS",
        "ADDR:5914 BOX IRON RD",
        "CITY:GIRDLETREE",
        "X:PINE ST & TAYLOR LANDING RD");

    doTest("T2",
        "CAD:300;SICK PERSON;5822 DUKES RD;GIRDLETREE;RAILROAD AVE;SNOW HILL RD;11/16/2010 09:21:04",
        "SRC:300",
        "CALL:SICK PERSON",
        "ADDR:5822 DUKES RD",
        "CITY:GIRDLETREE",
        "X:RAILROAD AVE & SNOW HILL RD");

    doTest("T3",
        "CAD:300;FALLS;5914 BOX IRON RD;GIRDLETREE;PINE ST;TAYLOR LANDING RD;11/23/2010 08:31:38",
        "SRC:300",
        "CALL:FALLS",
        "ADDR:5914 BOX IRON RD",
        "CITY:GIRDLETREE",
        "X:PINE ST & TAYLOR LANDING RD");

    doTest("T4",
        "CAD:100;TRAFFIC TRANSPORTATION ACC;3543 AYDELOTTE RD;DIST: 7.94 FT;POCOMOKE;BRANTLEY RD;SHEEPHOUSE RD;11/16/2010 00:31:42",
        "SRC:100",
        "CALL:TRAFFIC TRANSPORTATION ACC",
        "ADDR:3543 AYDELOTTE RD",
        "PLACE:DIST: 7.94 FT",
        "CITY:POCOMOKE",
        "X:BRANTLEY RD & SHEEPHOUSE RD");

    doTest("T5",
        "CAD:100;PROPANE OR NATURAL GAS LEAK;1010 CLARKE AVE;POCOMOKE;ANN ST;MCMICHAEL AVE;11/12/2010 10:31:09",
        "SRC:100",
        "CALL:PROPANE OR NATURAL GAS LEAK",
        "ADDR:1010 CLARKE AVE",
        "CITY:POCOMOKE",
        "X:ANN ST & MCMICHAEL AVE");

    doTest("T6",
        "CAD:100;FIRE ALARM;125 NEWTOWNE BLVD;HOLIDAY INN EXPRESS;POCOMOKE;OLD SNOW HILL RD;11/12/2010 08:15:38",
        "SRC:100",
        "CALL:FIRE ALARM",
        "ADDR:125 NEWTOWNE BLVD",
        "PLACE:HOLIDAY INN EXPRESS",
        "CITY:POCOMOKE",
        "X:OLD SNOW HILL RD");

    doTest("T7",
        "CAD:100;TRAFFIC TRANSPORTATION ACC;DIVIDING CREEK RD/WORTH RD;DIST: 25.70 FT;POCOMOKE;11/10/2010 15:20:42",
        "SRC:100",
        "CALL:TRAFFIC TRANSPORTATION ACC",
        "ADDR:DIVIDING CREEK RD & WORTH RD",
        "PLACE:DIST: 25.70 FT",
        "CITY:POCOMOKE");

    doTest("T8",
        "CAD:MUTUAL AID ASSIST OUTSIDE AGY;4264 FIREHOUSE ST;NEWCHURCH STATION 1;NEW CHURCH;DEPOT ST;LANKFORD LN;11/08/2010 20:34:48",
        "CALL:MUTUAL AID ASSIST OUTSIDE AGY",
        "ADDR:4264 FIREHOUSE ST",
        "PLACE:NEWCHURCH STATION 1",
        "CITY:NEW CHURCH",
        "X:DEPOT ST & LANKFORD LN");

    doTest("T9",
        "CAD:100;TRAFFIC TRANSPORTATION ACC;OCEAN HWY/WORCESTER HWY;PRIOR TO 113;POCOMOKE;11/08/2010 01:15:41",
        "SRC:100",
        "CALL:TRAFFIC TRANSPORTATION ACC",
        "ADDR:OCEAN HWY & WORCESTER HWY",
        "PLACE:PRIOR TO 113",
        "CITY:POCOMOKE");

    doTest("T10",
        "CAD:100;UNCONSCIOUS FAINTING;275 NEWTOWNE BLVD;BLDG LOWES;LOWES OF POCOMOKE;POCOMOKE;OLD SNOW HILL RD;11/07/2010 11:25:06",
        "SRC:100",
        "CALL:UNCONSCIOUS FAINTING",
        "ADDR:275 NEWTOWNE BLVD",
        "PLACE:BLDG LOWES",
        "CITY:LOWES OF POCOMOKE",
        "X:POCOMOKE & OLD SNOW HILL RD");

    doTest("T11",
        "CAD:100;BREATHING PROBLEMS;906-16 LYNNHAVEN DR;POCOMOKE;8TH ST;HALEYS WAY;10/24/2010 06:58:01",
        "SRC:100",
        "CALL:BREATHING PROBLEMS",
        "ADDR:906-16 LYNNHAVEN DR",
        "MADDR:906 LYNNHAVEN DR",
        "CITY:POCOMOKE",
        "X:8TH ST & HALEYS WAY");

    doTest("T12",
        "CAD:S5;MUTUAL AID ASSIST OUTSIDE AGY;8987 COURTHOUSE HILL RD;POCOMOKE;DIVIDING CREEK RD;WALLACE TAYLOR RD;10/22/2010 22:01:04",
        "SRC:S5",
        "CALL:MUTUAL AID ASSIST OUTSIDE AGY",
        "ADDR:8987 COURTHOUSE HILL RD",
        "CITY:POCOMOKE",
        "X:DIVIDING CREEK RD & WALLACE TAYLOR RD");

    doTest("T13",
        "CAD:100;UNCONSCIOUS FAINTING;1130 OLD VIRGINIA RD;POCOMOKE NAZARENE CHURCH;POCOMOKE;OCEAN HWY;SOUTHERN FIELDS DR;10/17/2010 11:32:35",
        "SRC:100",
        "CALL:UNCONSCIOUS FAINTING",
        "ADDR:1130 OLD VIRGINIA RD",
        "PLACE:POCOMOKE NAZARENE CHURCH",
        "CITY:POCOMOKE",
        "X:OCEAN HWY & SOUTHERN FIELDS DR");

    doTest("T14",
        "CAD:100;CARDIAC OR RESPIRATORY ARREST;2330 WORCESTER HWY;GRND FLR;POCOMOKE;OLD SNOW HILL RD;LAMBERTSON RD;10/18/2010 19:23:41",
        "SRC:100",
        "CALL:CARDIAC OR RESPIRATORY ARREST",
        "ADDR:2330 WORCESTER HWY",
        "PLACE:GRND FLR",
        "CITY:POCOMOKE",
        "X:OLD SNOW HILL RD & LAMBERTSON RD");

    doTest("T15",
        "CAD:100;RESIDENTIAL STRUCTURE FIRE;208 14TH ST;POCOMOKE;MARKET ST;CEDAR RUN;10/23/2010 07:12:12",
        "SRC:100",
        "CALL:RESIDENTIAL STRUCTURE FIRE",
        "ADDR:208 14TH ST",
        "CITY:POCOMOKE",
        "X:MARKET ST & CEDAR RUN");

    doTest("T16",
        "CAD:100;BREATHING PROBLEMS;906-16 LYNNHAVEN DR;POCOMOKE;8TH ST;HALEYS WAY;10/24/2010 06:58:01",
        "SRC:100",
        "CALL:BREATHING PROBLEMS",
        "ADDR:906-16 LYNNHAVEN DR",
        "MADDR:906 LYNNHAVEN DR",
        "CITY:POCOMOKE",
        "X:8TH ST & HALEYS WAY");

    doTest("T17",
        "CAD:400E;OUTSIDE FIRE;2476 BAYVIEW RD;GIRDLETREE;TAYLOR LANDING RD;BOX IRON RD;08/29/2011 08:58:46",
        "SRC:400E",
        "CALL:OUTSIDE FIRE",
        "ADDR:2476 BAYVIEW RD",
        "CITY:GIRDLETREE",
        "X:TAYLOR LANDING RD & BOX IRON RD");

    doTest("T18",
        "CAD:300;OUTSIDE FIRE;2476 BAYVIEW RD;GIRDLETREE;TAYLOR LANDING RD;BOX IRON RD;08/29/2011 08:58:11",
        "SRC:300",
        "CALL:OUTSIDE FIRE",
        "ADDR:2476 BAYVIEW RD",
        "CITY:GIRDLETREE",
        "X:TAYLOR LANDING RD & BOX IRON RD");
    
  }
  
  public static void main(String[] args) {
    new MDWorcesterCountyAParserTest().generateTests("T1");
  }
}
