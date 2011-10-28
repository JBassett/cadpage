package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class PADelawareCountyBParserTest extends BaseParserTest {
  
  public PADelawareCountyBParserTest() {
    setParser(new PADelawareCountyBParser(), "DELAWARE COUNTY", "PA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "X1: OAK AV X2: PEMBROKE AV Nature: FIRE-NON  VEHICLE FIRE Time: 13:25:12  Inc: F11032418",
        "ADDR:OAK AV & PEMBROKE AV",
        "CALL:FIRE-NON  VEHICLE FIRE",
        "ID:F11032418");

    doTest("T2",
        "6636 PERRY AV UD  X2: MONTGOMERY AV Nature: ALS-EMS  RESPIRATORY DIFFICULTY Time: 12:13:17  Notes:  67 Y/O FEMALE   Inc: F11032408",
        "ADDR:6636 PERRY AV",
        "CITY:UPPER DARBY TWP",
        "X:MONTGOMERY AV",
        "CALL:ALS-EMS  RESPIRATORY DIFFICULTY",
        "INFO:67 Y/O FEMALE",
        "ID:F11032408");

    doTest("T3",
        "507 HOLLY RD YE  X1: MYRA AV X2: BAILEY RD Nature: FIRE-BLD  BUILDING FIRE, RES/DWELLING Time: 20:21:17  Notes: STOVE   Inc: F11031897",
        "ADDR:507 HOLLY RD",
        "CITY:YEADON",
        "X:MYRA AV & BAILEY RD",
        "CALL:FIRE-BLD  BUILDING FIRE, RES/DWELLING",
        "INFO:STOVE",
        "ID:F11031897");

    doTest("T4",
        "39 E PROVIDENCE RD YE  X1: S LANSDOWNE AV X2: ELBERON AV Nature: FIRE-BLD  BUILDING FIRE, RES/DWELLING Time: 18:28:43  Notes: STOVE Inc: F11031009",
        "ADDR:39 E PROVIDENCE RD",
        "CITY:YEADON",
        "X:S LANSDOWNE AV & ELBERON AV",
        "CALL:FIRE-BLD  BUILDING FIRE, RES/DWELLING",
        "INFO:STOVE",
        "ID:F11031009");

    doTest("T5",
        "957 BALTIMORE AV EL : @BALTIMORE PIKE CHECK CASHING X1: E BALTIMORE AV X2: E BALTIMORE AV Nature: FIRE-BLD  BUILDING FIRE, RES/DWELLING Time: 20:39:44  Notes: ODOR OF SMOKE 2ND FLOOR   2ND EMERGENCY 20   Inc: F1103170",
        "ADDR:957 BALTIMORE AV",
        "CITY:EAST LANSDOWNE",
        "PLACE:BALTIMORE PIKE CHECK CASHING",
        "X:E BALTIMORE AV & E BALTIMORE AV",
        "CALL:FIRE-BLD  BUILDING FIRE, RES/DWELLING",
        "INFO:ODOR OF SMOKE 2ND FLOOR   2ND EMERGENCY 20",
        "ID:F1103170");

    doTest("T6",
        "115 S EAGLE RD HV : @56 MANOA FIRE CO X1: WYNDMOOR RD X2: STANLEY AV Nature: FIRE-OTH  COVER ASSIGNMENT Time: 13:25:09  Notes:  1 ENG FROM STA 20    CH 9   Inc: F11029664",
        "ADDR:115 S EAGLE RD",
        "CITY:HAVERFORD TWP",
        "PLACE:56 MANOA FIRE CO",
        "X:WYNDMOOR RD & STANLEY AV",
        "CALL:FIRE-OTH  COVER ASSIGNMENT",
        "INFO:1 ENG FROM STA 20    CH 9",
        "ID:F11029664");

    doTest("T7",
        "801 FERN ST YE  X1: PARMLEY AV X2: W COBBS CREEK PKWY Nature: ALS-EMS FALL W/TRAUMA Time: 03:36:04  Notes: HEAD INJURY   DIABETIC PATIENT ENGINE ASSIST EMS   FD RESPOND AT EMERGENCY SPEED   Inc: F11028163",
        "ADDR:801 FERN ST",
        "CITY:YEADON",
        "X:PARMLEY AV & W COBBS CREEK PKWY",
        "CALL:ALS-EMS FALL W/TRAUMA",
        "INFO:HEAD INJURY   DIABETIC PATIENT ENGINE ASSIST EMS   FD RESPOND AT EMERGENCY SPEED",
        "ID:F11028163");

    doTest("T8",
        "2402 ALFRED DR YE,A  X1: HEATHER CIR X2: KAREN DR Nature: FIRE-BLD BUILDING FIRE, COMMERCIAL BLDG Time: 13:37:14  Notes:  STOVE   Inc: F11027836",
        "ADDR:2402 ALFRED DR",
        "CITY:YEADON",
        "APT:A",
        "X:HEATHER CIR & KAREN DR",
        "CALL:FIRE-BLD BUILDING FIRE, COMMERCIAL BLDG",
        "INFO:STOVE",
        "ID:F11027836");

    doTest("T9",
        "11 WILDWOOD AV EL  X1: E BALTIMORE AV X2: PEMBROKE AV Nature: ASSIST FD TO ASSIST EMS Time: 00:15:59  Notes: LEG WOUND - DIABETIC - NO BLOOD THINNERS   65YOM   3RD EMER 103   LIFT ASSIST   Inc: F11029789",
        "ADDR:11 WILDWOOD AV",
        "CITY:EAST LANSDOWNE",
        "X:E BALTIMORE AV & PEMBROKE AV",
        "CALL:ASSIST FD TO ASSIST EMS",
        "INFO:LEG WOUND - DIABETIC - NO BLOOD THINNERS   65YOM   3RD EMER 103   LIFT ASSIST",
        "ID:F11029789");

    doTest("T10",
        "X1: BALTIMORE AV X2: LEXINGTON AV Nature: ALS-EMS  SEIZURES Time: 01:53:58  Inc: F11030225",
        "ADDR:BALTIMORE AV & LEXINGTON AV",
        "CALL:ALS-EMS  SEIZURES",
        "ID:F11030225");

    doTest("T11",
        "X1: WEST CHESTER PKE X2: PARK AV Nature: DISASTER  EMS BOX ALARM Time: 15:29:36  Inc: F11028912",
        "ADDR:WEST CHESTER PKE & PARK AV",
        "CALL:DISASTER  EMS BOX ALARM",
        "ID:F11028912");

    doTest("T12",
        "136 BEVERLY AV EL  X1: PEMBROKE AV X2: EMERSON AV Nature: ALARM AUTOMATIC FIRE ALARM Time: 08:35:32  Notes: UPSTAIRS SMOKE DET RES/SCOTT   Inc: F11027387",
        "ADDR:136 BEVERLY AV",
        "CITY:EAST LANSDOWNE",
        "X:PEMBROKE AV & EMERSON AV",
        "CALL:ALARM AUTOMATIC FIRE ALARM",
        "INFO:UPSTAIRS SMOKE DET RES/SCOTT",
        "ID:F11027387");

    doTest("T13",
        "164 LEXINGTON AV EL : @24 EAST LANSDOWNE FIRE CO X1: PEMBROKE AV X2: EMERSON AV Nature: TEST  PRINTER TEST Time: 11:06:09  Inc: F11023838",
        "ADDR:164 LEXINGTON AV",
        "CITY:EAST LANSDOWNE",
        "PLACE:24 EAST LANSDOWNE FIRE CO",
        "X:PEMBROKE AV & EMERSON AV",
        "CALL:TEST  PRINTER TEST",
        "ID:F11023838");

    doTest("T14",
        "269 HIRST AV EL  X1: EMERSON AV X2: GLENWOOD AV Nature: ALARM  CARBON MONOXIDE ALARM Time: 20:43:53  Inc: F11024160",
        "ADDR:269 HIRST AV",
        "CITY:EAST LANSDOWNE",
        "X:EMERSON AV & GLENWOOD AV",
        "CALL:ALARM  CARBON MONOXIDE ALARM",
        "ID:F11024160");

    doTest("T15",
        "1203 ALFRED AV YE,3FL E  X1: LORI DR X2: ALFRED DR Nature: FIRE-BLD BUILDING FIRE, WITH ENTRAPMENT Time: 01:21:32  Notes: SMOKE INSIDE - FEMALE CALLER CANNOT GET OUT   Inc: F11021062",
        "ADDR:1203 ALFRED AV",
        "CITY:YEADON",
        "APT:3FL E",
        "X:LORI DR & ALFRED DR",
        "CALL:FIRE-BLD BUILDING FIRE, WITH ENTRAPMENT",
        "INFO:SMOKE INSIDE - FEMALE CALLER CANNOT GET OUT",
        "ID:F11021062");

    doTest("T16",
        "164 MELROSE AV EL  X1: PEMBROKE AV X2: EMERSON AV Nature: FIRE-OTH WIRES/TRANSFORMER,  W/HAZARDS Time: 17:21:01  Inc: F11023150",
        "ADDR:164 MELROSE AV",
        "CITY:EAST LANSDOWNE",
        "X:PEMBROKE AV & EMERSON AV",
        "CALL:FIRE-OTH WIRES/TRANSFORMER,  W/HAZARDS",
        "ID:F11023150");
 }
  
  public static void main(String[] args) {
    new PADelawareCountyBParserTest().generateTests("T1");
  }
}
