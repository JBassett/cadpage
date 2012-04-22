package net.anei.cadpage.parsers.TN;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class TNWilliamsonCountyParserTest extends BaseParserTest {
  
  public TNWilliamsonCountyParserTest() {
    setParser(new TNWilliamsonCountyParser(), "WILLIAMSON COUNTY", "TN");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "911-CENTER:MED >MEDICAL CALL 3939 PERKINS RD XS: SYCAMORE RD THOMPSONS STATION Map: Grids:N,15 Cad: 2012-0000017268",
        "ID:2012-0000017268",
        "CALL:MEDICAL CALL",
        "ADDR:3939 PERKINS RD",
        "X:SYCAMORE RD",
        "CITY:THOMPSONS STATION");

    doTest("T2",
        "911-CENTER:F >FIRE (UNKNOWN ORGIN) SNEED RD W XS: HILLSBORO RD FRANKLIN VERIZON WIRELESS Cad: 2012-0000017084",
        "ID:2012-0000017084",
        "CALL:FIRE (UNKNOWN ORGIN)",
        "ADDR:SNEED RD W",
        "MADDR:SNEED RD W & HILLSBORO RD",
        "X:HILLSBORO RD",
        "CITY:FRANKLIN",
        "NAME:VERIZON WIRELESS");

    doTest("T3",
        "911-CENTER:MED >MEDICAL CALL 326 VAUGHN RD XS: STRICKLAND DR NASHVILLE MALLOY, MITCH Map: Grids:U,2 Cad: 2012-0000017260",
        "ID:2012-0000017260",
        "CALL:MEDICAL CALL",
        "ADDR:326 VAUGHN RD",
        "X:STRICKLAND DR",
        "CITY:NASHVILLE",
        "NAME:MALLOY, MITCH");

    doTest("T4",
        "911-CENTER:FA >FIRE ALARM 330 LAKE VALLEY DR XS: GLEN LAKES CT FRANKLIN PETERSON,JAY & MYRA Map: Grids:W,6 Cad: 2012-0000017023",
        "ID:2012-0000017023",
        "CALL:FIRE ALARM",
        "ADDR:330 LAKE VALLEY DR",
        "X:GLEN LAKES CT",
        "CITY:FRANKLIN",
        "NAME:PETERSON,JAY & MYRA");

    doTest("T5",
        "911-CENTER:MED >MEDICAL CALL NATCHEZ TRACE PKY XS: NEW HIGHWAY 96 W FRANKLIN CENTERSTONE CRISIS CENTER Cad: 2012-0000017016",
        "ID:2012-0000017016",
        "CALL:MEDICAL CALL",
        "ADDR:NATCHEZ TRACE PKY",
        "MADDR:NATCHEZ TRACE PKY & NEW HIGHWAY 96 W",
        "X:NEW HIGHWAY 96 W",
        "CITY:FRANKLIN",
        "NAME:CENTERSTONE CRISIS CENTER");

    doTest("T6",
        "911-CENTER:MC >MEDICAL CALL 716 LEGENDS CREST DR XS: LEGENDS RIDGE DR FRANKLIN Map: Grids:W,5 Cad: 2012-0000016926",
        "ID:2012-0000016926",
        "CALL:MEDICAL CALL",
        "ADDR:716 LEGENDS CREST DR",
        "X:LEGENDS RIDGE DR",
        "CITY:FRANKLIN");

    doTest("T7",
        "911-CENTER:FA >FIRE ALARM 1201 BRADLEY DR XS: BOBBY DR FRANKLIN DAN WATERS Map: Grids:W,4 Cad: 2012-0000016906",
        "ID:2012-0000016906",
        "CALL:FIRE ALARM",
        "ADDR:1201 BRADLEY DR",
        "X:BOBBY DR",
        "CITY:FRANKLIN",
        "NAME:DAN WATERS");

    doTest("T8",
        "911-CENTER:MED >MEDICAL CALL 1786 MASTERS DR XS: SIGNATURE CT FRANKLIN NELSON, JACK Map: Grids:V,13 Cad: 2012-0000016869",
        "ID:2012-0000016869",
        "CALL:MEDICAL CALL",
        "ADDR:1786 MASTERS DR",
        "X:SIGNATURE CT",
        "CITY:FRANKLIN",
        "NAME:NELSON, JACK");
  }
  

  public static void main(String[] args) {
    new TNWilliamsonCountyParserTest().generateTests("T1", "ID CALL ADDR X CITY NAME PHONE MAP");
  }
}
