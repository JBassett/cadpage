package net.anei.cadpage.parsers;

import org.junit.Test;


public class PABerksCountyParserTest extends BaseParserTest {
  
  public PABerksCountyParserTest() {
    setParser(new PABerksCountyParser(), "BERKS COUNTY", "PA");
  }
  
  @Test
  public void testParser() {
    doTest("T1",
        "CAD MSG: *D MVAUNK   E STATE ST / S KEMP ST 0082 2 VEH / COMP IS ANDREW BROOKS / INVOLVED // AT THE INTERSECTION B",
        "CALL:*D MVAUNK",
        "ADDR:E STATE ST & S KEMP ST",
        "CITY:LYONS",
        "INFO:2 VEH / COMP IS ANDREW BROOKS / INVOLVED / AT THE INTERSECTION B");

    doTest("T2",
        "CAD MSG: *D MVAWITH  102 BEAVER CREEK RD 0087 1 VEH / SPUN OUT / MALE OCC (COMP'S HUSBAND) BELIEVES HE FRACTURED H",
        "CALL:*D MVAWITH",
        "ADDR:102 BEAVER CREEK RD",
        "CITY:FLEETWOOD",
        "INFO:1 VEH / SPUN OUT / MALE OCC (COMP'S HUSBAND) BELIEVES HE FRACTURED H");
    
    doTest("T3",
        "CAD MSG: *D FTEST    STATION 35 @110 PARK AV 0082 BERKS TESING THE TEXT PAGING SYSTEM NO ACTION REQUIRED",
        "CALL:*D FTEST",
        "PLACE:STATION 35",
        "ADDR:110 PARK AV",
        "CITY:LYONS",
        "INFO:BERKS TESING THE TEXT PAGING SYSTEM NO ACTION REQUIRED");
    
    doTest("T4",
        "CAD MSG: *D MVAUNK   NOBLE ST / BASTIAN RD 0079 ONE CAR, OCCUPIED, INTO A FIELD // ONLY LANDMARK SHE COULD MEN",
        "CALL:*D MVAUNK",
        "ADDR:NOBLE ST & BASTIAN RD",
        "CITY:KUTZTOWN",
        "INFO:ONE CAR, OCCUPIED, INTO A FIELD / ONLY LANDMARK SHE COULD MEN");
    
    doTest("T5",
        "CAD MSG: *D SF       28 NOBLE ST 0081 SEES FLAMES IN BASEMENT //ADVISED TO EVAC /",
        "CALL:*D SF",
        "ADDR:28 NOBLE ST",
        "CITY:KUTZTOWN",
        "INFO:SEES FLAMES IN BASEMENT /ADVISED TO EVAC /");
    
    doTest("T6",
        "CAD MSG: *D MVAENT   FORGEDALE RD / DAVIDS DR 0087 2VEHS HEAD ON/2 VICTIMS ENTRAPPED /1 FEMALE SERIOUSLY INJ/BLEED",
        "CALL:*D MVAENT",
        "ADDR:FORGEDALE RD & DAVIDS DR",
        "CITY:FLEETWOOD",
        "INFO:2VEHS HEAD ON/2 VICTIMS ENTRAPPED /1 FEMALE SERIOUSLY INJ/BLEED");
  }
}
