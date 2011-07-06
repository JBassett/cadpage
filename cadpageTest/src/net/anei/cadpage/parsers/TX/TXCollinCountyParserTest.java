package net.anei.cadpage.parsers.TX;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class TXCollinCountyParserTest extends BaseParserTest {
  
  public TXCollinCountyParserTest() {
    setParser(new TXCollinCountyParser(), "COLLIN COUNTY", "TX");
  }
  
  @Test
  public void testLCFDParser() {

    doTest("T1",
        "11048794  FIRE PUBLIC ASSIST  2701 ASPEN CT IN COLLIN COUNTY  COUNTY ROAD 392  [LCFD DIST: LCF1 GRID: 1322]  UNITS: LCF1  ST RMK: 7K4  CFS RMK 22:10 REQ FD TO CHECK BP...40 YRO FEMALE.   {CAD001 22:13}",
        "ID:11048794",
        "CALL:FIRE PUBLIC ASSIST",
        "ADDR:2701 ASPEN CT",
        "X:COUNTY ROAD 392",
        "SRC:LCFD",
        "MAP:1322",
        "UNIT:LCF1",
        "INFO:7K4 / 22:10 REQ FD TO CHECK BP...40 YRO FEMALE.");

    doTest("T2",
        "11031079 First Responders 3274 Almeta LN in Collin County FM 546 [LCFD DIST: LCF1 GRID: 1331]",
        "ID:11031079",
        "CALL:First Responders",
        "ADDR:3274 Almeta LN",
        "X:FM 546",
        "SRC:LCFD",
        "MAP:1331");

    doTest("T3",
        "11047502 INJURED PERSON 3610 FM 546 IN COLLIN COUNTY COUNTY ROAD 398 / COUNTY RO;16AD 394 [LCFD DIST: LCF1 GRID: 1322] UNITS: AMRP LCF1 ST RMK: <NONE> CFS RMK 19:16 MALE SUB BLEEDING FROM HIS THROAT {CAD004 19:17}",
        "ID:11047502",
        "CALL:INJURED PERSON",
        "ADDR:3610 FM 546",
        "X:COUNTY ROAD 398 / COUNTY RO;16AD 394",
        "SRC:LCFD",
        "MAP:1322",
        "UNIT:AMRP LCF1",
        "INFO:<NONE> / 19:16 MALE SUB BLEEDING FROM HIS THROAT");

    doTest("T4",
        "11047405 FISRT RESPONDERS 2897 COUNTY ROAD 914 IN COLLIN COUNTY COUNTY ROAD 392 [LCFD DIST: LCF1 GRID: 1322] UNITS: LCF1 AMRP ST RMK: DUTCH ACRES CFS RMK 12:59 68 YOA FALLEN IN FRONT YARD UNABLE T {CAD001 13:00}",
        "ID:11047405",
        "CALL:FISRT RESPONDERS",
        "ADDR:2897 COUNTY ROAD 914",
        "X:COUNTY ROAD 392",
        "SRC:LCFD",
        "MAP:1322",
        "UNIT:LCF1 AMRP",
        "INFO:DUTCH ACRES / 12:59 68 YOA FALLEN IN FRONT YARD UNABLE T");

    doTest("T5",
        "11046597 MAJOR ACCIDENT 10/50 COUNTY ROAD 393 / FM 546 IN COLLIN COUNTY [LCFD DIST: LCF1 GRID: 1322] UNITS: LCF1 ST RMK: 8J1 CFS RMK 21:40 SOMEONE HAS FALLEN OUT OF A TRUCK {CAD004 21:40}",
        "ID:11046597",
        "CALL:MAJOR ACCIDENT",
        "ADDR:10 & 50 COUNTY ROAD 393 & FM 546",
        "SRC:LCFD",
        "MAP:1322",
        "UNIT:LCF1",
        "INFO:8J1 / 21:40 SOMEONE HAS FALLEN OUT OF A TRUCK");

    doTest("T6",
        "11044587 FIRST RESPONDERS 730 CROSS TIMBERS DR IN LOWRY CROSSING CROSS TRAIL LN [LCFD DIST: LCF1 GRID: 3100] UNITS: LCF1 AMRP ST RMK: <NONE> CFS RMK 13:14 37 YOA MALE / FELL YESTERDAY AND IS {CAD004 13:14}",
        "ID:11044587",
        "CALL:FIRST RESPONDERS",
        "ADDR:730 CROSS TIMBERS DR",
        "CITY:LOWRY CROSSING",
        "X:CROSS TRAIL LN",
        "SRC:LCFD",
        "MAP:3100",
        "UNIT:LCF1 AMRP",
        "INFO:<NONE> / 13:14 37 YOA MALE / FELL YESTERDAY AND IS");

  }
  
  @Test
  public void testBRFDParser() {

    doTest("T1",
        "11056128  GRASS FIRE  10753 COUNTY ROAD 903 IN COLLIN COUNTY  COUNTY ROAD 902  UNITS: BRF1  ST RMK: <NONE>  CFS RMK 21:52 RIGHT ON",
        "ID:11056128",
        "CALL:GRASS FIRE",
        "ADDR:10753 COUNTY ROAD 903",
        "X:COUNTY ROAD 902",
        "UNIT:BRF1",
        "INFO:<NONE> / 21:52 RIGHT ON");
  
  }
  
  public static void main(String[] args) {
    new TXCollinCountyParserTest().generateTests("T1", "ID CALL ADDR CITY X SRC MAP UNIT INFO");
  }
}
