package net.anei.cadpage.parsers.TN;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class TNHamiltonCountyParserTest extends BaseParserTest {
  
  public TNHamiltonCountyParserTest() {
    setParser(new TNHamiltonCountyParser(), "HAMILTON COUNTY", "TN");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "RT:ACC1HW-HWY ACCIDENT WITH INJURIES  Loc:INTERSTATE 75 S, HAMILTON COUNTY (BRADLEY COUNTY/MILE MARKER 13.2)  #[1320-1560]",
        "CALL:ACC1HW-HWY ACCIDENT WITH INJURIES",
        "ADDR:INTERSTATE 75 S",
        "MADDR:INTERSTATE 75 S & BRADLEY COUNTY",
        "X:BRADLEY COUNTY/MILE MARKER 13.2");

    doTest("T2",
        "RT:CHILOC-CHILD LOCKED IN VEHICLE  Loc:10433 E BRAINERD RD, HAMILTON COUNTY (BENTWOOD COVE DR/STEPPING ROCK DR)  #[10416-10449] [10416-10449] [0-0]",
        "CALL:CHILOC-CHILD LOCKED IN VEHICLE",
        "ADDR:10433 E BRAINERD RD",
        "X:BENTWOOD COVE DR/STEPPING ROCK DR");

    doTest("T3",
        "RT:AFA NURSING HOME  Loc:4586 FORSYTHIA WAY, COLLEGEDALE  (ILENE CT/LEYLAND DR) #[4500-4599]",
        "CALL:AFA NURSING HOME",
        "ADDR:4586 FORSYTHIA WAY",
        "CITY:COLLEGEDALE",
        "X:ILENE CT/LEYLAND DR");

    doTest("T4",
        "RT:AFA DORMITORY  Loc:4891 TAYLOR CIR, COLLEGEDALE  (UNIVERSITY DR/UNIVERSITY DR)  #[4800-4899]",
        "CALL:AFA DORMITORY",
        "ADDR:4891 TAYLOR CIR",
        "CITY:COLLEGEDALE",
        "X:UNIVERSITY DR/UNIVERSITY DR");

    doTest("T5",
        "RT:AFA RESIDENTIAL  Loc:4019 PATTON EDWARDS DR, EAST RIDGE  (S MISSION OAKS DR/EDWARDS TER)  #[4000-4099]",
        "CALL:AFA RESIDENTIAL",
        "ADDR:4019 PATTON EDWARDS DR",
        "CITY:EAST RIDGE",
        "X:S MISSION OAKS DR/EDWARDS TER");

    doTest("T6",
        "RT:ACC4-MOTOR VEHICLE ACCIDENT WITH ENTRAPMENT  Loc:INTERSTATE 75 N, HAMILTON COUNTY  (MILE MARKER 13.2/BRADLEY COUNTY)  #[1320-1560]",
        "CALL:ACC4-MOTOR VEHICLE ACCIDENT WITH ENTRAPMENT",
        "ADDR:INTERSTATE 75 N",
        "MADDR:INTERSTATE 75 N & MILE MARKER 13.2",
        "X:MILE MARKER 13.2/BRADLEY COUNTY");

    doTest("T7",
        "RT:SPILL-GASOLINE, FUEL, OR HAZARDOUS LIQUIDS SPILLED  Loc:8935 LEE HWY, CHATTANOOGA  (MOUNTAIN VIEW RD/INTERSTATE 75)  #[8900-8999]",
        "CALL:SPILL-GASOLINE, FUEL, OR HAZARDOUS LIQUIDS SPILLED",
        "ADDR:8935 LEE HWY",
        "CITY:CHATTANOOGA",
        "X:MOUNTAIN VIEW RD/INTERSTATE 75");

    doTest("T8",
        "RT:SMOKEINVES-INVESTIGATE SMOKE  Loc:LONDON LN, HAMILTON COUNTY  (SALEM RD/BILL JONES RD)  #[10932-10943]",
        "CALL:SMOKEINVES-INVESTIGATE SMOKE",
        "ADDR:LONDON LN",
        "MADDR:LONDON LN & SALEM RD",
        "X:SALEM RD/BILL JONES RD");

    doTest("T9",
        "RT:ACC1-MOTOR VEHICLE ACCIDENT WITH INJURIES  Loc:STANDIFER GAP RD, HAMILTON COUNTY  (CROSS GATE RD/OOLTEWAH RINGGOLD RD)  #[9243-9299]",
        "CALL:ACC1-MOTOR VEHICLE ACCIDENT WITH INJURIES",
        "ADDR:STANDIFER GAP RD",
        "MADDR:STANDIFER GAP RD & CROSS GATE RD",
        "X:CROSS GATE RD/OOLTEWAH RINGGOLD RD");

    doTest("T10",
        "RT:FTRASH-OUTSIDE TRASH FIRE  Loc:5205 ALABAMA RD, HAMILTON COUNTY  (BATES RD/BLAIR RD)  #[5000-5399]",
        "CALL:FTRASH-OUTSIDE TRASH FIRE",
        "ADDR:5205 ALABAMA RD",
        "X:BATES RD/BLAIR RD");
 
  }
  
  @Test
  public void testParser2() {

    doTest("T1",
        "RT:AFA RESIDENTIAL  Loc:2706 NILE RD, HAMILTON COUNTY  (CLIPPER DR/DANUBE DR)  #",
        "CALL:AFA RESIDENTIAL",
        "ADDR:2706 NILE RD",
        "X:CLIPPER DR/DANUBE DR");

    doTest("T2",
        "RT:FASEMS-FIRE DEPARTMENT ASSISTING EMS WITH MANPOWER  Loc:9407 CATHOWKEN DR, HAMILTON COUNTY  (FULLER RD/DEAD END)  #[9400-9499]",
        "CALL:FASEMS-FIRE DEPARTMENT ASSISTING EMS WITH MANPOWER",
        "ADDR:9407 CATHOWKEN DR",
        "X:FULLER RD/DEAD END");

    doTest("T3",
        "RT:FASCIT-FIRE DEPARTMENT ASSISTING A CITIZEN  Loc:8475 COMMUNITY PL, HAMILTON COUNTY  (PITTMAN LN/PATTENTOWN RD)  #[8400-8499]",
        "CALL:FASCIT-FIRE DEPARTMENT ASSISTING A CITIZEN",
        "ADDR:8475 COMMUNITY PL",
        "X:PITTMAN LN/PATTENTOWN RD");

    doTest("T4",
        "RT:FMUAID-FIRE DEPARTMENT MUTUAL AID ALARM  Loc:281 CLARK RD, DOGWOOD RD THE CROSS CATOOSA CO",
        "CALL:FMUAID-FIRE DEPARTMENT MUTUAL AID ALARM",
        "ADDR:281 CLARK RD",
        "CITY:CATOOSA COUNTY",
        "ST:GA",
        "X:DOGWOOD RD THE CROSS");

    doTest("T5",
        "RT:ANSBT-ANIMAL OR SNAKE BITE  Loc:@WAFFLE HOUSE 8912 LEE HWY  (8912 LEE HWY, CHATANOOGA)",
        "CALL:ANSBT-ANIMAL OR SNAKE BITE",
        "PLACE:WAFFLE HOUSE",
        "ADDR:8912 LEE HWY",
        "X:8912 LEE HWY",
        "CITY:CHATANOOGA");

  }
  
  @Test
  public void testDanny() {

    doTest("T1",
        "RT:STROKE-STROKE  Loc:10320 HAMBY RD, HAMILTON COUNTY  (SEQUOYAH ACCESS RD/PLES LN)  #[10206-10329]",
        "CALL:STROKE-STROKE",
        "ADDR:10320 HAMBY RD",
        "X:SEQUOYAH ACCESS RD/PLES LN");

  }
  

  public static void main(String[] args) {
    new TNHamiltonCountyParserTest().generateTests("T1");
  }
}
