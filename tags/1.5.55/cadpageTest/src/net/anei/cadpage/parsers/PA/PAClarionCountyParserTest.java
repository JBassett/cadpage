package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.PA.PAClarionCountyParserTest;

import org.junit.Test;


public class PAClarionCountyParserTest extends BaseParserTest {
  
  public PAClarionCountyParserTest() {
    setParser(new PAClarionCountyParser(), "CLARION COUNTY", "PA");
  }

 
  
  @Test
  public void testChrisCochran() {

    doTest("T1",
        "911-CENTER:52C03 >FIRE ALARM COMMERCIAL/INDUST 101 MAPLE DR XS: ROUTE 322 ELK TWP MC ALARMS 6077869897 Map: Grids:G-10, Cad: 2012-0000012260",
        "CALL:FIRE ALARM COMMERCIAL/INDUST",
        "ADDR:101 MAPLE DR",
        "CITY:ELK TWP",
        "X:ROUTE 322",
        "NAME:MC ALARMS",
        "PHONE:6077869897",
        "ID:2012-0000012260");

    doTest("T2",
        "911-CENTER:67D05 >REFINERY/TANK FARM/FUEL STORAG 180 E STATE ST XS: POPETOWN RD KNOX BORO LORRY 7245043418 Map: Grids:H-07, Cad: 2012-0000011908",
        "CALL:REFINERY/TANK FARM/FUEL STORAG",
        "ADDR:180 E STATE ST",
        "CITY:KNOX",
        "X:POPETOWN RD",
        "NAME:LORRY",
        "PHONE:7245043418",
        "ID:2012-0000011908");

    doTest("T3",
        "911-CENTER:ELECHZ>ELECTRICAL HAZARD KLINE RD&ROUTE 322 XS: ROUTE 322 ASHLAND TWP MILLIRON, DAVE Cad: 2012-0000011794",
        "CALL:ELECTRICAL HAZARD",
        "ADDR:KLINE RD & ROUTE 322",
        "CITY:ASHLAND TWP",
        "X:ROUTE 322",
        "NAME:MILLIRON, DAVE",
        "ID:2012-0000011794");

    doTest("T4",
        "911-CENTER:29B01 >ACCIDENT INJURIES 5024 ROUTE 208 XS: KLINE RD SALEM TWP DAUGHERTY , JOHN 8147581955 Map: Grids:H-05, Cad: 2012-0000011605",
        "CALL:ACCIDENT INJURIES",
        "ADDR:5024 ROUTE 208",
        "CITY:SALEM TWP",
        "X:KLINE RD",
        "NAME:DAUGHERTY , JOHN",
        "PHONE:8147581955",
        "ID:2012-0000011605");

    doTest("T5",
        "911-CENTER:61D01 >UNCONTAINED HAZMAT 53400 INTERSTATE 80 EB XS: MP 53 BEAVER TWP RUSS 2165489760 Map: Grids:J-06, Cad: 2012-0000011327",
        "CALL:UNCONTAINED HAZMAT",
        "ADDR:53400 INTERSTATE 80 EB",
        "MADDR:53400 INTERSTATE 80",
        "CITY:BEAVER TWP",
        "X:MP 53",
        "NAME:RUSS",
        "PHONE:2165489760",
        "ID:2012-0000011327");

    doTest("T6",
        "911-CENTER:TRAFCO>TRAFFIC CONTROL RT 338 WENTLINGS CORS Cad: 2012-0000011317",
        "CALL:TRAFFIC CONTROL",
        "ADDR:RT 338",
        "NAME:WENTLINGS CORS",
        "ID:2012-0000011317");
  }
  
  public static void main(String[] args) {
    new PAClarionCountyParserTest().generateTests("T1", "SRC CALL ADDR CITY X NAME PHONE MAP PLACE ID INFO");
  }
}