package net.anei.cadpage.parsers.MO;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class MOGasconadeCountyParserTest extends BaseParserTest {
  
  public MOGasconadeCountyParserTest() {
    setParser(new MOGasconadeCountyParser(), "", "MO");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "MESSAGE / M50 CHEST PAIN  4449 MARIES CR 426 OUT OF COUNTY MapRegions:  CrossStreets: Description:  Dispatch: 3/25/2011 03:51:43\n\n\n\n\n",
        "CALL:M50 CHEST PAIN",
        "ADDR:4449 MARIES CR 426",
        "CITY:OUT OF COUNTY");

    doTest("T2",
        "MESSAGE / M51 MOTOR VEHICLE ACCIDENT-INJURIES  HWY 89  MapRegions:  CrossStreets: Description:  Dispatch: 3/26/2011 11:29:32\n\n\n\n\n",
        "CALL:M51 MOTOR VEHICLE ACCIDENT-INJURIES",
        "ADDR:HWY 89");

    doTest("T3",
        "MESSAGE / M51 MOTOR VEHICLE ACCIDENT-UNKNOWN INJ  HWY 89  MapRegions:  CrossStreets: Description:  Dispatch: 3/26/2011 11:39:53\n\n\n\n\n",
        "CALL:M51 MOTOR VEHICLE ACCIDENT-UNKNOWN INJ",
        "ADDR:HWY 89");

    doTest("T4",
        "M51 FALL- PERSON FELL 408 N ALVARADO AVE BELLE MapRegions: CrossStreets: E BELAIR AVE 0.03 mi S E ROHRER DR 0.13 mi N Description: Dispatch: 3/24/2011 11:47:54",
        "CALL:M51 FALL- PERSON FELL",
        "ADDR:408 N ALVARADO AVE",
        "CITY:BELLE",
        "X:E BELAIR AVE 0.03 mi S E ROHRER DR 0.13 mi N");

    doTest("T5",
        "M50 SICK PERSON- SICK CASE 4644 HIGHWAY B, Apt. BOX 14 GASCONADE COUNTY MapRegions: PAGE 103 CrossStreets: ELK HEAD RD 0.08 mi SW RED BIRD RD 0.46 mi N Description: HALF MILE SOUTH OF REDBIRD RD Dispatch: 3/24/2011 11:25:45",
        "CALL:M50 SICK PERSON- SICK CASE",
        "ADDR:4644 HIGHWAY B",
        "APT:BOX 14",
        "MAP:PAGE 103",
        "X:ELK HEAD RD 0.08 mi SW RED BIRD RD 0.46 mi N",
        "INFO:HALF MILE SOUTH OF REDBIRD RD");

    doTest("T6",
        "M50 SICK PERSON- SICK CASE 901 W EIGHTH ST BELLE MapRegions: CrossStreets: S SHOCKLEY AVE 0.01 mi NE S APPLE AVE 0.19 mi E Description: Dispatch: 3/24/2011 21:05:48",
        "CALL:M50 SICK PERSON- SICK CASE",
        "ADDR:901 W EIGHTH ST",
        "CITY:BELLE",
        "X:S SHOCKLEY AVE 0.01 mi NE S APPLE AVE 0.19 mi E");

    doTest("T7",
        "501 FALL- PERSON FELL FRENE VALLEY HEALTHCARE SOUTH 1016 W HIGHWAY 28 OWENSVILLE MapRegions: OFD SECTOR 1, PAGE 072 CrossStreets: HIGHWAY Y 0.05 mi N S L&D DR 0.44 mi SW Description: 96 YOA FALL BROKEN WRIST Dispatch: 4/3/2011 06:00:43",
        "UNIT:501",
        "CALL:FALL- PERSON FELL FRENE VALLEY HEALTHCARE SOUTH",
        "ADDR:1016 W HIGHWAY 28",
        "CITY:OWENSVILLE",
        "MAP:OFD SECTOR 1, PAGE 072",
        "X:HIGHWAY Y 0.05 mi N S L&D DR 0.44 mi SW",
        "INFO:96 YOA FALL BROKEN WRIST");

    doTest("T8",
        "Body: 1 of 2\nFRM:central@fidmail.com\nSUBJ:MESSAGE\nMSG:\n501 ACCIDENTAL INJURY  1009 SPRINGFIELD RD, Apt. APT 21 OWENSVILLE MapRegions: OFD SECTOR 1,\n(Con't) 2 of 2\nPAGE 065, PAGE 122 CrossStreets: CHERRY AVE 0.06 mi SE INDUSTRIAL DR 0.22 mi NE Description:  Dispatch: 4/6/2011 12:08:04\n\n\n\n(End)",
        "UNIT:501",
        "CALL:ACCIDENTAL INJURY",
        "ADDR:1009 SPRINGFIELD RD Apt",
        "APT:21",
        "CITY:OWENSVILLE",
        "MAP:OFD SECTOR 1, PAGE 065, PAGE 122",
        "X:CHERRY AVE 0.06 mi SE INDUSTRIAL DR 0.22 mi NE");

    doTest("T9",
        "O164 O171 O172 501 MOTOR VEHICLE ACCIDENT WITH RESCUE GASCONADE COUNTY E-911 405 E LINCOLN AVE OWENSVILLE MapRegions: OFD SECTOR 1, PAGE 073, PAGE 123 CrossStreets: OLIVE ST 0.08 mi E N WALNUT ST 0.04 mi W Description: TEST CALL FOR OFD***********TEST**********TEST Dispatch: 5/10/2011 1:39:36 PM Dispatch: 5/10/2011 1:39:36 PM Dispatch: 5/10/2011 1:39:36 PM Dispatch: 5/10/2011 1:39:36 PM",
        "UNIT:O164 O171 O172 501",
        "CALL:MOTOR VEHICLE ACCIDENT WITH RESCUE GASCONADE COUNTY E-911",
        "ADDR:405 E LINCOLN AVE",
        "CITY:OWENSVILLE",
        "MAP:OFD SECTOR 1, PAGE 073, PAGE 123",
        "X:OLIVE ST 0.08 mi E N WALNUT ST 0.04 mi W",
        "INFO:TEST CALL FOR OFD***********TEST**********TEST");

    doTest("T10",
        "O164 O171 O172 O170 501 726 O160 GAS LEAK OUTSIDE  2935 LAKE NORTHWOODS RD OWENSVILLE MapRegions: OFD SECTOR 1, PAGE 057, PAGE 117 CrossStreets: HIGHWAY 19 0.22 mi W Description: STRUTMANS. PROPANE TANK ROLLED OVER.\n\nCALLER STATES THAT HE WAS MOWING GRASS AND SAW THE PROPANE TANK ROLL OVER WITH GAS LEAKING . CALLER STATES THAT THE TANK IS ABOUT 50 FEET    FROM THE HOUSE. INITIAL CALLER STATES THAT HE KNOCKED ON THE DOOR WITH NO ANSWER, CALLER IS CALLING FROM THE NEIGHBORS HOUSE WHICH IS ALSO THE SISTER. ADVISED CALLER TO STAY AWAY AND STAY SAFE. CALLER IS STATING A VERY STRONG SMELL OF GAS . \n CALLED THE RP BACK AN\n More?",
        "UNIT:O164 O171 O172 O170 501 726 O160",
        "CALL:GAS LEAK OUTSIDE",
        "ADDR:2935 LAKE NORTHWOODS RD",
        "CITY:OWENSVILLE",
        "MAP:OFD SECTOR 1, PAGE 057, PAGE 117",
        "X:HIGHWAY 19 0.22 mi W",
        "INFO:STRUTMANS. PROPANE TANK ROLLED OVER.\n\nCALLER STATES THAT HE WAS MOWING GRASS AND SAW THE PROPANE TANK ROLL OVER WITH GAS LEAKING . CALLER STATES THAT THE TANK IS ABOUT 50 FEET    FROM THE HOUSE. INITIAL CALLER STATES THAT HE KNOCKED ON THE DOOR WITH NO ANSWER, CALLER IS CALLING FROM THE NEIGHBORS HOUSE WHICH IS ALSO THE SISTER. ADVISED CALLER TO STAY AWAY AND STAY SAFE. CALLER IS STATING A VERY STRONG SMELL OF GAS . \n CALLED THE RP BACK AN\n More?");

    doTest("T11",
        "502 TRAUMATIC INJURY  412 S CUBA ST OWENSVILLE MapRegions: OFD SECTOR 1, PAGE 073, PAGE 125 CrossStreets: E JEFFERSON AVE 0.06 mi N E MADISON AVE 0.02 mi SW Description:  Dispatch: 5/11/2011 18:14:27\n\n\n\n",
        "UNIT:502",
        "CALL:TRAUMATIC INJURY",
        "ADDR:412 S CUBA ST",
        "CITY:OWENSVILLE",
        "MAP:OFD SECTOR 1, PAGE 073, PAGE 125",
        "X:E JEFFERSON AVE 0.06 mi N E MADISON AVE 0.02 mi SW");

    doTest("T12",
        "701 736 M50 FIGHT IN PROGRESS  2774 HW HWY P - S SECTOR              5732411289 OWENSVILLE MapRegions:  CrossStreets: Description: IN THE BACK OF SILVER DICK APTS \n\nMALE/FEMALE MALE DRUG THE FEMALE INSIDE.  3RD DOOR DOWN FROM HWY 28.  POSSIBLY G.  \nMALES LEAVING IN A GREEN FORD EXPLORER, HEADING TOWARD HOLT ROAD.  TURNED RIGHT Dispatch: 5/12/2011 07:19:35\n\n\n\n",
        "UNIT:701 736",
        "CALL:M50 FIGHT IN PROGRESS",
        "ADDR:2774 HW HWY P - S SECTOR 5732411289",
        "CITY:OWENSVILLE",
        "INFO:IN THE BACK OF SILVER DICK APTS \n\nMALE/FEMALE MALE DRUG THE FEMALE INSIDE.  3RD DOOR DOWN FROM HWY 28.  POSSIBLY G.  \nMALES LEAVING IN A GREEN FORD EXPLORER, HEADING TOWARD HOLT ROAD.  TURNED RIGHT");
  }

  public static void main(String[] args) {
    new MOGasconadeCountyParserTest().generateTests("T9");
  }
}
