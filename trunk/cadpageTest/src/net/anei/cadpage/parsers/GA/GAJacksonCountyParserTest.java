package net.anei.cadpage.parsers.GA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class GAJacksonCountyParserTest extends BaseParserTest {
  
  public GAJacksonCountyParserTest() {
    setParser(new GAJacksonCountyParser(), "JACKSON COUNTY", "GA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "JACKSONE911:1070 >FIRE 1382 WILHITE RD XS: PAYNEVILLE RD JEFFERSON WIER, GILBERT L & SUE 7063678650 Map: Grids:0,0 Cad: 2012-0000049643",
        "CALL:FIRE",
        "ADDR:1382 WILHITE RD",
        "X:PAYNEVILLE RD",
        "CITY:JEFFERSON",
        "NAME:WIER, GILBERT L & SUE",
        "PHONE:7063678650",
        "ID:2012-0000049643");

    doTest("T2",
        "JACKSONE911:1050I >ACCIDENT - INJURIES 3835 APPLE VALLEY RD BILL Cad: 2012-0000049719",
        "CALL:ACCIDENT - INJURIES",
        "ADDR:3835 APPLE VALLEY RD",
        "NAME:BILL",
        "ID:2012-0000049719");

    doTest("T3",
        "JACKSONE911:SIG16 >STROKE/CVA PATIENT 87 BEND DR XS: COMMERCE RD JEFFERSON FLOYD PAULSON 7066148432 Map:H6 Grids:0,0 Cad: 2012-0000053376",
        "CALL:STROKE/CVA PATIENT",
        "ADDR:87 BEND DR",
        "X:COMMERCE RD",
        "CITY:JEFFERSON",
        "NAME:FLOYD PAULSON",
        "PHONE:7066148432",
        "MAP:H6",
        "ID:2012-0000053376");

    doTest("T4",
        "JACKSONE911:SIG3 >CHEST PAIN/RESPIRATORY DISTRES 95 W G LEGG RD XS: RAFORD WILSON RD COMMERCE LEGG, JERRY 7063675387 Cad: 2012-0000055115",
        "CALL:CHEST PAIN/RESPIRATORY DISTRES",
        "ADDR:95 W G LEGG RD",
        "X:RAFORD WILSON RD",
        "CITY:COMMERCE",
        "NAME:LEGG, JERRY",
        "PHONE:7063675387",
        "ID:2012-0000055115");

    doTest("T5",
        "JACKSONE911:SIG3 >CHEST PAIN/RESPIRATORY DISTRES 4513 S APPLE VALLEY RD XS: EDGEFIELD DR COMMERCE HOMAN, JARED 7062026827 Map: Grids:0,0 Cad: 2012-0000054782",
        "CALL:CHEST PAIN/RESPIRATORY DISTRES",
        "ADDR:4513 S APPLE VALLEY RD",  // Mapped to location far from cross street
        "X:EDGEFIELD DR",
        "CITY:COMMERCE",
        "NAME:HOMAN, JARED",
        "PHONE:7062026827",
        "ID:2012-0000054782");
        
  }
  
  public static void main(String[] args) {
    new GAJacksonCountyParserTest().generateTests("T1", "CALL ADDR X CITY PLACE NAME PHONE MAP ID");
  }
}