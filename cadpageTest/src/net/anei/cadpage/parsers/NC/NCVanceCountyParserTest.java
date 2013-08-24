package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/* 
Vance County, NC
Contact: Brian Short <eod@vancecounty.org> (dispatch)

Contact: Boyd Edwards <boedwr@gmail.com>
Sender: VanceCounty911@vancecounty.org

VanceCounty911:1301-002044 GREYSTONE RD // 109 BROOKSTON RD HENDERSON ACCIDENT PERSONAL INJURY STEPHANIE WILLIAMS EMS,S12,VCRS
VanceCounty911:1301-001945 1850 NEWTON DAIRY RD HENDERSON ACCIDENT PERSONAL INJURY VERIZON WIRELESS ENGN2,M4,R81,S47,SHP,SQD 3,V
VanceCounty911:1301-001945 CAREY CHAPEL RD // NEWTON DAIRY RD HENDERSON ACCIDENT PERSONAL INJURY VERIZON WIRELESS EMS,GBELT,SHP,
VanceCounty911:1301-001822 KITTRELL COLLEGE RD // US 1 HWY S KITTRELL ACCIDENT PERSONAL INJURY VERIZON WIRELESS EMS,KITTR,SHP,VC
VanceCounty911:1301-000846 WARRENTON RD // CAREY CHAPEL RD HENDERSON ACCIDENT PERSONAL INJURY US CELLULAR EMS,VCRS
VanceCounty911:1212-066577 4115 JACKSONTOWN RD MANSON CHIMNEY FIRE WILLIE HARGROVE DVFD,EMS,RIDGE,VCFD,VCRS
VanceCounty911:1212-065802 143 DABNEY WOODS LN HENDERSON MEDICAL MIKE NICHOLAS M3,WATKI
VanceCounty911:1212-065025 2727 US 158 BYPASS 24 HENDERSON STRUCTURE FIRE VERIZON WIRELESS HVFD,VCFD,VCRS,WVFD
VanceCounty911:1212-067149 2610 BURNSIDE RD OXFORD MISSING PERSON HVFD,SQD30,TVFD,VCRS
VanceCounty911:1212-066277 745 EPSOM ROCKY FORD RD HENDERSON ACCIDENT PERSONAL INJURY EMS,EPSOM,VCRS
VanceCounty911:1301-002189 132 PEBBLE HILL LN HENDERSON STRUCTURE FIRE KATHERINE BC-1,ENG60,ENG63,ENGN2,FM1,M1,R81,S12,TAN33,TAN
VanceCounty911:2953 FAULKNER TOWN RD HENDERSON FIRE ALARM BOBBY WILLIAMS EVFD,VCRS
VanceCounty911:240 WRENN RD OXFORD BREATHING PROBLEMS LONG EMS,WATKI\n

Contact: Jason Edwards <jbe12173@gmail.com>
Sender: VanceCounty911@vancecounty.org
VanceCounty911:2726 RALEIGH RD HENDERSON ACCIDENT PERSONAL INJURY SPRINT NEXTEL - CDMA BEARP,EMS,S47

*/

public class NCVanceCountyParserTest extends BaseParserTest {
  
  public NCVanceCountyParserTest() {
    setParser(new NCVanceCountyParser(), "VANCE COUNTY", "NC");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "VanceCounty911:1301-002044 GREYSTONE RD // 109 BROOKSTON RD HENDERSON ACCIDENT PERSONAL INJURY STEPHANIE WILLIAMS EMS,S12,VCRS",
        "ID:1301-002044",
        "ADDR:GREYSTONE RD & 109 BROOKSTON RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "NAME:STEPHANIE WILLIAMS",
        "UNIT:EMS,S12,VCRS");

    doTest("T2",
        "VanceCounty911:1301-001945 1850 NEWTON DAIRY RD HENDERSON ACCIDENT PERSONAL INJURY VERIZON WIRELESS ENGN2,M4,R81,S47,SHP,SQD 3,V",
        "ID:1301-001945",
        "ADDR:1850 NEWTON DAIRY RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:ENGN2,M4,R81,S47,SHP,SQD 3,V");

    doTest("T3",
        "VanceCounty911:1301-001945 CAREY CHAPEL RD // NEWTON DAIRY RD HENDERSON ACCIDENT PERSONAL INJURY VERIZON WIRELESS EMS,GBELT,SHP,",
        "ID:1301-001945",
        "ADDR:CAREY CHAPEL RD & NEWTON DAIRY RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:EMS,GBELT,SHP,");

    doTest("T4",
        "VanceCounty911:1301-001822 KITTRELL COLLEGE RD // US 1 HWY S KITTRELL ACCIDENT PERSONAL INJURY VERIZON WIRELESS EMS,KITTR,SHP,VC",
        "ID:1301-001822",
        "ADDR:KITTRELL COLLEGE RD & US 1 HWY S",
        "MADDR:KITTRELL COLLEGE RD & US 1 S",
        "CITY:KITTRELL",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:EMS,KITTR,SHP,VC");

    doTest("T5",
        "VanceCounty911:1301-000846 WARRENTON RD // CAREY CHAPEL RD HENDERSON ACCIDENT PERSONAL INJURY US CELLULAR EMS,VCRS",
        "ID:1301-000846",
        "ADDR:WARRENTON RD & CAREY CHAPEL RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:EMS,VCRS");

    doTest("T6",
        "VanceCounty911:1212-066577 4115 JACKSONTOWN RD MANSON CHIMNEY FIRE WILLIE HARGROVE DVFD,EMS,RIDGE,VCFD,VCRS",
        "ID:1212-066577",
        "ADDR:4115 JACKSONTOWN RD",
        "CITY:MANSON",
        "CALL:CHIMNEY FIRE",
        "NAME:WILLIE HARGROVE",
        "UNIT:DVFD,EMS,RIDGE,VCFD,VCRS");

    doTest("T7",
        "VanceCounty911:1212-065802 143 DABNEY WOODS LN HENDERSON MEDICAL MIKE NICHOLAS M3,WATKI",
        "ID:1212-065802",
        "ADDR:143 DABNEY WOODS LN",
        "CITY:HENDERSON",
        "CALL:MEDICAL",
        "NAME:MIKE NICHOLAS",
        "UNIT:M3,WATKI");

    doTest("T8",
        "VanceCounty911:1212-065025 2727 US 158 BYPASS 24 HENDERSON STRUCTURE FIRE VERIZON WIRELESS HVFD,VCFD,VCRS,WVFD",
        "ID:1212-065025",
        "ADDR:2727 US 158 BYPASS 24",
        "CITY:HENDERSON",
        "CALL:STRUCTURE FIRE",
        "UNIT:HVFD,VCFD,VCRS,WVFD");

    doTest("T9",
        "VanceCounty911:1212-067149 2610 BURNSIDE RD OXFORD MISSING PERSON HVFD,SQD30,TVFD,VCRS",
        "ID:1212-067149",
        "ADDR:2610 BURNSIDE RD",
        "CITY:OXFORD",
        "CALL:MISSING PERSON",
        "UNIT:HVFD,SQD30,TVFD,VCRS");

    doTest("T10",
        "VanceCounty911:1212-066277 745 EPSOM ROCKY FORD RD HENDERSON ACCIDENT PERSONAL INJURY EMS,EPSOM,VCRS",
        "ID:1212-066277",
        "ADDR:745 EPSOM ROCKY FORD RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:EMS,EPSOM,VCRS");

    doTest("T11",
        "VanceCounty911:1301-002189 132 PEBBLE HILL LN HENDERSON STRUCTURE FIRE KATHERINE BC-1,ENG60,ENG63,ENGN2,FM1,M1,R81,S12,TAN33,TAN",
        "ID:1301-002189",
        "ADDR:132 PEBBLE HILL LN",
        "CITY:HENDERSON",
        "CALL:STRUCTURE FIRE",
        "NAME:KATHERINE",
        "UNIT:BC-1,ENG60,ENG63,ENGN2,FM1,M1,R81,S12,TAN33,TAN");

    doTest("T12",
        "VanceCounty911:2953 FAULKNER TOWN RD HENDERSON FIRE ALARM BOBBY WILLIAMS EVFD,VCRS",
        "ADDR:2953 FAULKNER TOWN RD",
        "CITY:HENDERSON",
        "CALL:FIRE ALARM",
        "NAME:BOBBY WILLIAMS",
        "UNIT:EVFD,VCRS");

    doTest("T13",
        "VanceCounty911:240 WRENN RD OXFORD BREATHING PROBLEMS LONG EMS,WATKI\n",
        "ADDR:240 WRENN RD",
        "CITY:OXFORD",
        "CALL:BREATHING PROBLEMS",
        "NAME:LONG",
        "UNIT:EMS,WATKI");

  }
  
  @Test
  public void testJasonEdwards() {

    doTest("T1",
        "VanceCounty911:2726 RALEIGH RD HENDERSON ACCIDENT PERSONAL INJURY SPRINT NEXTEL - CDMA BEARP,EMS,S47",
        "ADDR:2726 RALEIGH RD",
        "CITY:HENDERSON",
        "CALL:ACCIDENT PERSONAL INJURY",
        "UNIT:BEARP,EMS,S47");

  }

  public static void main(String[] args) {
    new NCVanceCountyParserTest().generateTests("T1");
  }
}
