package net.anei.cadpage.parsers.MD;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/* 
Wicomico County, MD
Contact: Jeff Paige <jeff.paige219@gmail.com>
Sender: cad@wicomicocounty.org

1638:CAD:2010110703;STRUCTURE FIRE;27887 POINTERS LN;SALI;SPANIEL CT;OXBRIDGE DR
1639:CAD:2010110960;STRUCTURE FIRE;914 E CHURCH ST;SALI;HOLLAND AVE;WALSTON AVE
1640:CAD:2010110997;PETROLEUM SPILL;2530 N SALISBURY BLVD;SALI;N ZION RD;N ZION RD
1642:CAD:2010111051;NON BREATHING;105 CIVIC AVE;SALI;OLD OCEAN CITY RD;WHITE ST;[Medical Priority Update] Medical Priority reconfigured event from 09-E-01 to
1643:CAD:2010111259;AUTOMATIC ALARM;121 E NAYLOR MILL RD;SALI;N SALISBURY BLVD;JASMINE DR;[Fire Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: Alarms. HI

Mutual aid to Delmar DE
1644:CAD:2010111333;STRUCTURE FIRE;1103 STATE ST;DELM;HIGHLAND AVE;VIRGINIA AVE
1650:CAD:2010111860;STRUCTURE FIRE;1110 N DIVISION ST;SALI;NAYLOR ST;UNION AVE
1654:CAD:2010112048;STRUCTURE FIRE;413 CAMDEN CT;SALI;CAMDEN AVE;CAMDEN AVE;[Fire Priority Info] RESPONSE: Delta RESPONDER SCRIPT: Structure Fire.Caller Stat

Mutual aid from Delmar
CAD:2010110683;PI ACCIDENT;8859 JERSEY RD;DELM;CONNELLY MILL RD;ADKINS RD;[Medical Priority Info] RESPONSE: Bravo RESPONDER SCRIPT: 25 year old, Male,

Contact: Kenny <kenkordek@comcast.net>
CAD:2011058418;MEDICAL ASSIST;38353 ROBIN HOOD RD;DELM;OAK BRANCH RD;Event spawned from SEIZURE. [07/02/2011 22:17:15 JHATTON] UDTS: {B74} PARAMEDIC UNIT [07/02/11 22:13:59 CMASSEY] UDTS: {B74} STAFFING OF 2 [07/02/11 22:13:56 CMASSEY]
CAD:2011058370;PETROLEUM SPILL;6640 DELMAR RD;DELM;PROVIDENCE CHURCH RD;CONTENMENT LN
CAD:2011058277;NATURAL COVER;32190 DOWNING RD;PARS;NORTHUMBERLAND DR;WINTERGREEN PL;0 UDTS: {T608} STAFFING OF 1 [07/02/11 15:50:29 KVICKERS] POSSIBLY STARTED BY LAWN MOWER IN YARD [07/02/11 15:49:29 KVICKERS] UDTS: {BR606} STAFFING OF 2 [0
CAD:2011057974;ODOR INVESTIGATION;411 NAYLOR MILL RD;SALI;NORTHWOOD DR;ARMSTRONG PKWY;COMMAND REQ ENGINE FROM STA 1 AND STA 74 [07/01/11 23:00:19 JBYRD] [EMS] UDTS: {A1} STAFFING OF 2 [07/01/11 23:00:33 MDIETZ] [EMS] UDTS: {A1} PARAMEDIC UN
CAD:2011057241;MEDICAL ASSIST;102 SPRUCE ST;DELM;S 1ST ST;S 2ND ST;[EMS] UDTS: {B74} MEDICAL ASSIST REQUESTED [06/29/11 21:08:18 GWOODS] Event spawned from UNRESPONSIVE PATIENT. [06/29/2011 21:08:02 GWOODS] {B74} PARA [06/29/11 21:03:21 JCO
CAD:2011057117;NATURAL COVER;27000 OCEAN GTWY;HEBR;HEBRON WOODS BLVD;MEMORY GARDENS LN;UDTS: {E507} STAFFING OF 2 [06/29/11 15:23:02 SCARPENTER] REQ. BRUSH TRUCK OUT OF 9 AND 74 [06/29/11 15:22:45 SCARPENTER] UDTS: {BR502} STAFFING OF 4 [06
CAD:2011055305;MUTUAL AID;JACKSON RD/SAINT GEORGE RD;DELM
CAD:2011054814;MEDICAL ASSIST;37936 PROVIDENCE CHURCH RD;DELM;DELMAR RD;W LINE RD                
CAD:2011053776;TRASH FIRE;CONNELLY MILL RD/WOOD CREEK PKWY;DELM
CAD:2011050801;PI ACCIDENT;W STATE ST/W LINE RD;SINGLE VEHICLE ROLL OVER [06/10/11 17:29:52 JCOOPER]

Contact: Paul Revel <paul22.pr@gmail.com>
Sender: CAD@wicomicocounty.org
2012085344;BACK PAIN;306 E MAIN ST;FRUI;CHURCH ST;N DIVISION ST;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 72 year old, Female, Conscious, Bre
2012085236;SICK SUBJECT;5222 SILVER RUN LN;FRUI;RIVERSIDE DR EXT;DOVE POINT LN;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 87 year old, Female,
2012085316;DIFFICULTY BREATHING;407 SLAB BRIDGE RD;FRUI;N DIVISION ST;GRAYDON LN;[Medical Priority Info] Key Questions Complete RESPONSE:
2012085179;AUTOMATIC ALARM;109 E MAIN ST;FRUI;MORRIS ST;N DULANY AVE;[Fire Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: Alarms. COMMERCIAL/INDUSTRIAL b
2012085085;AUTOMATIC ALARM;234 GARRISON WAY;FRUI;SILVERSMITH LN;SILVERSMITH LN;2ND FLOOR SMOKE DET [09/17/12 09:27:46 GWOODS]
2012084066;EMERGENCY UNKNOWN;1819 N MILL DR;FRUI;THRASHER WAY;S MILL DR
2012084066;EMERGENCY UNKNOWN;1819 N MILL DR;FRUI;THRASHER WAY;S MILL DR
2012084057;HEMORRHAGING;4132 BROWN ST EXT;FRUI;SKYLAR DR;CROWN RD;[Medical Priority Info] RESPONSE: Bravo RESPONDER SCRIPT: 80 year old, Female, Conscious, B
2012083870;SICK SUBJECT;5384 CHERRY HILL LN;FRUI;RIVERSIDE DR;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 80 year old, Female, Conscious, Breat
2012083953;SICK SUBJECT;114 HALL DR;SALI;LOUIS AVE;URBAN AVE;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 88 year old, Male, Conscious, Breathin
2012084039;MATERNITY PATIENT;103 W MAIN ST;FRUI;N FRUITLAND BLVD;DUDLEY AVE;[Medical Priority Info] RESPONSE: Delta RESPONDER SCRIPT: 23 year old, Female, Co
2012083953;SICK SUBJECT;114 HALL DR;SALI;LOUIS AVE;URBAN AVE;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 88 year old, Male, Conscious, Breathin
2012083555;SICK SUBJECT;105 WILLIAMS AVE;FRUI;W MAIN ST;STATON ST;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 82 year old, Female, Conscious, B
2012083393;SYNCOPAL EPISODE;1819 E CLEAR LAKE DR;FRUI;SHADY CREEK WAY;W CLEAR LAKE DR;[Medical Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: 83 year old
2012083050;VEHICLE FIRE;N CAMDEN AVE/W MAIN ST;FRUI

Contact: tom king <hebron526@gmail.com>
Sender: 5fire-owner@jbamsg.com
2012085673;SUBJECT FALLEN;27480 WALLER RD;DELM;HICKORY MILL RD;SPRING HILL LN;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 83 year old, Male, Co

*/


public class MDWicomicoCountyParserTest extends BaseParserTest {
  
  public MDWicomicoCountyParserTest() {
    setParser(new MDWicomicoCountyParser(), "WICOMICO COUNTY", "MD");
  }
  
  @Test
  public void testParser() {
    
    doTest("T1",
        "1638:CAD:2010110703;STRUCTURE FIRE;27887 POINTERS LN;SALI;SPANIEL CT;OXBRIDGE DR",
        "ID:2010110703",
        "CALL:STRUCTURE FIRE",
        "ADDR:27887 POINTERS LN",
        "CITY:SALISBURY",
        "X:SPANIEL CT & OXBRIDGE DR");
 
    doTest("T2",
        "1639:CAD:2010110960;STRUCTURE FIRE;914 E CHURCH ST;SALI;HOLLAND AVE;WALSTON AVE",
        "ID:2010110960",
        "CALL:STRUCTURE FIRE",
        "ADDR:914 E CHURCH ST",
        "CITY:SALISBURY",
        "X:HOLLAND AVE & WALSTON AVE");
        
    doTest("T3",
        "1640:CAD:2010110997;PETROLEUM SPILL;2530 N SALISBURY BLVD;SALI;N ZION RD;N ZION RD",
        "ID:2010110997",
        "CALL:PETROLEUM SPILL",
        "ADDR:2530 N SALISBURY BLVD",
        "CITY:SALISBURY",
        "X:N ZION RD & N ZION RD");

    doTest("T4",
        "1642:CAD:2010111051;NON BREATHING;105 CIVIC AVE;SALI;OLD OCEAN CITY RD;WHITE ST;[Medical Priority Update] Medical Priority reconfigured event from 09-E-01 to",
        "ID:2010111051",
        "CALL:NON BREATHING",
        "ADDR:105 CIVIC AVE",
        "CITY:SALISBURY",
        "X:OLD OCEAN CITY RD & WHITE ST",
        "INFO:Medical Priority reconfigured event from 09-E-01 to");
    
    doTest("T5",
        "1643:CAD:2010111259;AUTOMATIC ALARM;121 E NAYLOR MILL RD;SALI;N SALISBURY BLVD;JASMINE DR;[Fire Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: Alarms. HI",
        "ID:2010111259",
        "CALL:AUTOMATIC ALARM",
        "ADDR:121 E NAYLOR MILL RD",
        "CITY:SALISBURY",
        "X:N SALISBURY BLVD & JASMINE DR",
        "INFO:Alarms. HI");
    
    doTest("T6",
        "1650:CAD:2010111860;STRUCTURE FIRE;1110 N DIVISION ST;SALI;NAYLOR ST;UNION AVE",
        "ID:2010111860",
        "CALL:STRUCTURE FIRE",
        "ADDR:1110 N DIVISION ST",
        "CITY:SALISBURY",
        "X:NAYLOR ST & UNION AVE");
        
    doTest("T7",
        "1654:CAD:2010112048;STRUCTURE FIRE;413 CAMDEN CT;SALI;CAMDEN AVE;CAMDEN AVE;[Fire Priority Info] RESPONSE: Delta RESPONDER SCRIPT: Structure Fire.Caller Stat",
        "ID:2010112048",
        "CALL:STRUCTURE FIRE",
        "ADDR:413 CAMDEN CT",
        "CITY:SALISBURY",
        "X:CAMDEN AVE & CAMDEN AVE",
        "INFO:Structure Fire.Caller Stat");

    doTest("T8",
        "CAD:2011058277;NATURAL COVER;32190 DOWNING RD;PARS;NORTHUMBERLAND DR;WINTERGREEN PL;0 UDTS: {T608} STAFFING OF 1 [07/02/11 15:50:29 KVICKERS] POSSIBLY STARTED BY LAWN MOWER IN YARD [07/02/11 15:49:29 KVICKERS] UDTS: {BR606} STAFFING OF 2 [0",
        "ID:2011058277",
        "CALL:NATURAL COVER",
        "ADDR:32190 DOWNING RD",
        "CITY:PARSONBURG",
        "X:NORTHUMBERLAND DR & WINTERGREEN PL",
        "INFO:0 UDTS: {T608} STAFFING OF 1 / POSSIBLY STARTED BY LAWN MOWER IN YARD / UDTS: {BR606} STAFFING OF 2",
        "DATE:07/02/11",
        "TIME:15:50:29");

    doTest("T9",
        "CAD:2011057974;ODOR INVESTIGATION;411 NAYLOR MILL RD;SALI;NORTHWOOD DR;ARMSTRONG PKWY;COMMAND REQ ENGINE FROM STA 1 AND STA 74 [07/01/11 23:00:19 JBYRD] [EMS] UDTS: {A1} STAFFING OF 2 [07/01/11 23:00:33 MDIETZ] [EMS] UDTS: {A1} PARAMEDIC UN",
        "ID:2011057974",
        "CALL:ODOR INVESTIGATION",
        "ADDR:411 NAYLOR MILL RD",
        "CITY:SALISBURY",
        "X:NORTHWOOD DR & ARMSTRONG PKWY",
        "INFO:COMMAND REQ ENGINE FROM STA 1 AND STA 74 / UDTS: {A1} STAFFING OF 2 / UDTS: {A1} PARAMEDIC UN",
        "DATE:07/01/11",
        "TIME:23:00:19");

    doTest("T10",
        "CAD:2011057117;NATURAL COVER;27000 OCEAN GTWY;HEBR;HEBRON WOODS BLVD;MEMORY GARDENS LN;UDTS: {E507} STAFFING OF 2 [06/29/11 15:23:02 SCARPENTER] REQ. BRUSH TRUCK OUT OF 9 AND 74 [06/29/11 15:22:45 SCARPENTER] UDTS: {BR502} STAFFING OF 4 [06",
        "ID:2011057117",
        "CALL:NATURAL COVER",
        "ADDR:27000 OCEAN GTWY",
        "CITY:HEBRON",
        "X:HEBRON WOODS BLVD & MEMORY GARDENS LN",
        "INFO:UDTS: {E507} STAFFING OF 2 / REQ. BRUSH TRUCK OUT OF 9 AND 74 / UDTS: {BR502} STAFFING OF 4",
        "DATE:06/29/11",
        "TIME:15:23:02");

    doTest("T11",
        "CAD:2011050801;PI ACCIDENT;W STATE ST/W LINE RD;SINGLE VEHICLE ROLL OVER [06/10/11 17:29:52 JCOOPER]",
        "ID:2011050801",
        "CALL:PI ACCIDENT",
        "ADDR:W STATE ST & W LINE RD",
        "CITY:SINGLE VEHICLE ROLL OVER",
        "DATE:06/10/11",
        "TIME:17:29:52");
  }
  
  @Test
  public void testDelmarParser() {
    
    setDefaults("WICOMICO COUNTY", "");
    doTest("T1",
        "1644:CAD:2010111333;STRUCTURE FIRE;1103 STATE ST;DELM;HIGHLAND AVE;VIRGINIA AVE",
        "ID:2010111333",
        "CALL:STRUCTURE FIRE",
        "ADDR:1103 STATE ST",
        "CITY:DELMAR",
        "X:HIGHLAND AVE & VIRGINIA AVE");
    
    doTest("T2",
        "CAD:2010110683;PI ACCIDENT;8859 JERSEY RD;DELM;CONNELLY MILL RD;ADKINS RD;[Medical Priority Info] RESPONSE: Bravo RESPONDER SCRIPT: 25 year old, Male,",
        "ID:2010110683",
        "CALL:PI ACCIDENT",
        "ADDR:8859 JERSEY RD",
        "CITY:DELMAR",
        "X:CONNELLY MILL RD & ADKINS RD",
        "INFO:25 year old, Male,");

    doTest("T3",
        "CAD:2011058418;MEDICAL ASSIST;38353 ROBIN HOOD RD;DELM;OAK BRANCH RD;Event spawned from SEIZURE. [07/02/2011 22:17:15 JHATTON] UDTS: {B74} PARAMEDIC UNIT [07/02/11 22:13:59 CMASSEY] UDTS: {B74} STAFFING OF 2 [07/02/11 22:13:56 CMASSEY]",
        "ID:2011058418",
        "CALL:MEDICAL ASSIST",
        "ADDR:38353 ROBIN HOOD RD",
        "CITY:DELMAR",
        "X:OAK BRANCH RD & Event spawned from SEIZURE.",
        "INFO:UDTS: {B74} PARAMEDIC UNIT / UDTS: {B74} STAFFING OF 2",
        "DATE:07/02/2011",
        "TIME:22:17:15");

    doTest("T4",
        "CAD:2011058370;PETROLEUM SPILL;6640 DELMAR RD;DELM;PROVIDENCE CHURCH RD;CONTENMENT LN",
        "ID:2011058370",
        "CALL:PETROLEUM SPILL",
        "ADDR:6640 DELMAR RD",
        "CITY:DELMAR",
        "X:PROVIDENCE CHURCH RD & CONTENMENT LN");

    doTest("T6",
        "CAD:2011057241;MEDICAL ASSIST;102 SPRUCE ST;DELM;S 1ST ST;S 2ND ST;[EMS] UDTS: {B74} MEDICAL ASSIST REQUESTED [06/29/11 21:08:18 GWOODS] Event spawned from UNRESPONSIVE PATIENT. [06/29/2011 21:08:02 GWOODS] {B74} PARA [06/29/11 21:03:21 JCO",
        "ID:2011057241",
        "CALL:MEDICAL ASSIST",
        "ADDR:102 SPRUCE ST",
        "CITY:DELMAR",
        "X:S 1ST ST & S 2ND ST",
        "INFO:UDTS: {B74} MEDICAL ASSIST REQUESTED / Event spawned from UNRESPONSIVE PATIENT. / {B74} PARA",
        "DATE:06/29/11",
        "TIME:21:08:18");

    doTest("T7",
        "CAD:2011055305;MUTUAL AID;JACKSON RD/SAINT GEORGE RD;DELM",
        "ID:2011055305",
        "CALL:MUTUAL AID",
        "ADDR:JACKSON RD & SAINT GEORGE RD",
        "CITY:DELMAR");

    doTest("T8",
        "CAD:2011054814;MEDICAL ASSIST;37936 PROVIDENCE CHURCH RD;DELM;DELMAR RD;W LINE RD",
        "ID:2011054814",
        "CALL:MEDICAL ASSIST",
        "ADDR:37936 PROVIDENCE CHURCH RD",
        "CITY:DELMAR",
        "X:DELMAR RD & W LINE RD");

    doTest("T9",
        "CAD:2011053776;TRASH FIRE;CONNELLY MILL RD/WOOD CREEK PKWY;DELM",
        "ID:2011053776",
        "CALL:TRASH FIRE",
        "ADDR:CONNELLY MILL RD & WOOD CREEK PKWY",
        "CITY:DELMAR");
  }
  
  @Test
  public void testPaulRevel() {

    doTest("T3",
        "2012085344;BACK PAIN;306 E MAIN ST;FRUI;CHURCH ST;N DIVISION ST;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 72 year old, Female, Conscious, Bre",
        "ID:2012085344",
        "CALL:BACK PAIN",
        "ADDR:306 E MAIN ST",
        "CITY:FRUITLAND",
        "X:CHURCH ST & N DIVISION ST",
        "INFO:72 year old, Female, Conscious, Bre");

    doTest("T4",
        "2012085236;SICK SUBJECT;5222 SILVER RUN LN;FRUI;RIVERSIDE DR EXT;DOVE POINT LN;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 87 year old, Female,",
        "ID:2012085236",
        "CALL:SICK SUBJECT",
        "ADDR:5222 SILVER RUN LN",
        "CITY:FRUITLAND",
        "X:RIVERSIDE DR EXT & DOVE POINT LN",
        "INFO:87 year old, Female,");

    doTest("T5",
        "2012085316;DIFFICULTY BREATHING;407 SLAB BRIDGE RD;FRUI;N DIVISION ST;GRAYDON LN;[Medical Priority Info] Key Questions Complete RESPONSE:",
        "ID:2012085316",
        "CALL:DIFFICULTY BREATHING",
        "ADDR:407 SLAB BRIDGE RD",
        "CITY:FRUITLAND",
        "X:N DIVISION ST & GRAYDON LN");

    doTest("T6",
        "2012085179;AUTOMATIC ALARM;109 E MAIN ST;FRUI;MORRIS ST;N DULANY AVE;[Fire Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: Alarms. COMMERCIAL/INDUSTRIAL b",
        "ID:2012085179",
        "CALL:AUTOMATIC ALARM",
        "ADDR:109 E MAIN ST",
        "CITY:FRUITLAND",
        "X:MORRIS ST & N DULANY AVE",
        "INFO:Alarms. COMMERCIAL/INDUSTRIAL b");

    doTest("T7",
        "2012085085;AUTOMATIC ALARM;234 GARRISON WAY;FRUI;SILVERSMITH LN;SILVERSMITH LN;2ND FLOOR SMOKE DET [09/17/12 09:27:46 GWOODS]",
        "ID:2012085085",
        "CALL:AUTOMATIC ALARM",
        "ADDR:234 GARRISON WAY",
        "CITY:FRUITLAND",
        "X:SILVERSMITH LN & SILVERSMITH LN",
        "INFO:2ND FLOOR SMOKE DET",
        "DATE:09/17/12",
        "TIME:09:27:46");

    doTest("T8",
        "2012084066;EMERGENCY UNKNOWN;1819 N MILL DR;FRUI;THRASHER WAY;S MILL DR",
        "ID:2012084066",
        "CALL:EMERGENCY UNKNOWN",
        "ADDR:1819 N MILL DR",   // Google says this is in Salisbury
        "CITY:FRUITLAND",
        "X:THRASHER WAY & S MILL DR");

    doTest("T9",
        "2012084066;EMERGENCY UNKNOWN;1819 N MILL DR;FRUI;THRASHER WAY;S MILL DR",
        "ID:2012084066",
        "CALL:EMERGENCY UNKNOWN",
        "ADDR:1819 N MILL DR",
        "CITY:FRUITLAND",
        "X:THRASHER WAY & S MILL DR");

    doTest("T10",
        "2012084057;HEMORRHAGING;4132 BROWN ST EXT;FRUI;SKYLAR DR;CROWN RD;[Medical Priority Info] RESPONSE: Bravo RESPONDER SCRIPT: 80 year old, Female, Conscious, B",
        "ID:2012084057",
        "CALL:HEMORRHAGING",
        "ADDR:4132 BROWN ST EXT",
        "MADDR:4132 BROWN ST",
        "CITY:FRUITLAND",
        "X:SKYLAR DR & CROWN RD",
        "INFO:80 year old, Female, Conscious, B");

    doTest("T11",
        "2012083870;SICK SUBJECT;5384 CHERRY HILL LN;FRUI;RIVERSIDE DR;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 80 year old, Female, Conscious, Breat",
        "ID:2012083870",
        "CALL:SICK SUBJECT",
        "ADDR:5384 CHERRY HILL LN",
        "CITY:FRUITLAND",
        "X:RIVERSIDE DR",
        "INFO:80 year old, Female, Conscious, Breat");

    doTest("T12",
        "2012083953;SICK SUBJECT;114 HALL DR;SALI;LOUIS AVE;URBAN AVE;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 88 year old, Male, Conscious, Breathin",
        "ID:2012083953",
        "CALL:SICK SUBJECT",
        "ADDR:114 HALL DR",
        "CITY:SALISBURY",
        "X:LOUIS AVE & URBAN AVE",
        "INFO:88 year old, Male, Conscious, Breathin");

    doTest("T13",
        "2012084039;MATERNITY PATIENT;103 W MAIN ST;FRUI;N FRUITLAND BLVD;DUDLEY AVE;[Medical Priority Info] RESPONSE: Delta RESPONDER SCRIPT: 23 year old, Female, Co",
        "ID:2012084039",
        "CALL:MATERNITY PATIENT",
        "ADDR:103 W MAIN ST",
        "CITY:FRUITLAND",
        "X:N FRUITLAND BLVD & DUDLEY AVE",
        "INFO:23 year old, Female, Co");

    doTest("T14",
        "2012083953;SICK SUBJECT;114 HALL DR;SALI;LOUIS AVE;URBAN AVE;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 88 year old, Male, Conscious, Breathin",
        "ID:2012083953",
        "CALL:SICK SUBJECT",
        "ADDR:114 HALL DR",
        "CITY:SALISBURY",
        "X:LOUIS AVE & URBAN AVE",
        "INFO:88 year old, Male, Conscious, Breathin");

    doTest("T15",
        "2012083555;SICK SUBJECT;105 WILLIAMS AVE;FRUI;W MAIN ST;STATON ST;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 82 year old, Female, Conscious, B",
        "ID:2012083555",
        "CALL:SICK SUBJECT",
        "ADDR:105 WILLIAMS AVE",
        "CITY:FRUITLAND",
        "X:W MAIN ST & STATON ST",
        "INFO:82 year old, Female, Conscious, B");

    doTest("T16",
        "2012083393;SYNCOPAL EPISODE;1819 E CLEAR LAKE DR;FRUI;SHADY CREEK WAY;W CLEAR LAKE DR;[Medical Priority Info] RESPONSE: Charlie RESPONDER SCRIPT: 83 year old",
        "ID:2012083393",
        "CALL:SYNCOPAL EPISODE",
        "ADDR:1819 E CLEAR LAKE DR",
        "CITY:FRUITLAND",
        "X:SHADY CREEK WAY & W CLEAR LAKE DR",
        "INFO:83 year old");

    doTest("T17",
        "2012083050;VEHICLE FIRE;N CAMDEN AVE/W MAIN ST;FRUI",
        "ID:2012083050",
        "CALL:VEHICLE FIRE",
        "ADDR:N CAMDEN AVE & W MAIN ST",
        "CITY:FRUITLAND");

  }
  
  @Test
  public void testTomKing() {

   setDefaults("WICOMICO COUNTY", "");
   doTest("T1",
        "2012085673;SUBJECT FALLEN;27480 WALLER RD;DELM;HICKORY MILL RD;SPRING HILL LN;[Medical Priority Info] RESPONSE: Alpha RESPONDER SCRIPT: 83 year old, Male, Co",
        "ID:2012085673",
        "CALL:SUBJECT FALLEN",
        "ADDR:27480 WALLER RD",
        "CITY:DELMAR",
        "X:HICKORY MILL RD & SPRING HILL LN",
        "INFO:83 year old, Male, Co");

  }
  
  public static void main(String[] args) {
    new MDWicomicoCountyParserTest().generateTests("T1");
        
  }
}