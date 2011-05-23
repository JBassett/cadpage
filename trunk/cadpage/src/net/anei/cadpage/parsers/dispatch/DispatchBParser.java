package net.anei.cadpage.parsers.dispatch;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.SmsMsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;

/*
This has been identified as Interact GIS system
And as CAD Interactive
Go Figure

Date County, GA
DADE COUNTY 911:M10 >CHEST PAIN 367 GRAY RD XS: 224 FAWN DAWN DR. NEW SALEM KATY DILLS Map:10-D1,E1 Grids:0,0 Cad: 2011-0000000796
DADE COUNTY 911:M26 >SICK PERSON 391 BIBLE GULF RD XS: 869 TATUM GULF RD OFF OLD STATE RD HEAD RIVER KEATON, GLENN Map:12-D2 Grids:0,0 Cad: 2011-0000000651
DADE COUNTY 911:M5 >FALL - ANY 2862 YANKEE RD XS: HWY 157 S HEAD RIVER KEENER, CAROL Map:11,12 Grids:0,0 Cad: 2011-0000001152
DADE COUNTY 911:50PI >M.V.A. - POSSIBLE INJURIES 13500 HWY 136 E XS: N & S MOORE RD NEW SALEM ROBERTS, CHIP Map:6,15 Grids:0,0 Cad: 2011-0000000992
DADE COUNTY 911:M1 >ABDOMINAL PAIN/PROBLEM 2321 OLD STATE RD XS: 16548 HWY 157 S HEAD RIVER CHAD LANIER Map:11,12 Grids:0,0 Cad: 2011-0000001270
DADE COUNTY 911:78 >ASSISTANCE/ MUTUAL AID 84 RUSHING WATER TRL CHRISTY / WALKER Cad: 2011-0000001334

Harford, MD
[!] EOC:A1091 UNC >UNCONSCIOUS 2200 CHANNEL RD XS: X BIG BRANCH BRDG #127 PYLESVILLE CRIZER,KIM BOX: N08 Cad: 2010-0000162717
[!] EOC:A1091 SICK >SICK PERSON 1606 SCOTT RD XS: X FAWN GROVE RD PYLESVILLE BATCHELDER,MICHELLE BOX: N04 Cad: 2010-0000162427
[!] EOC:A1091 DIAB >DIABETIC EMRG. 2145 HARKINS RD XS: SAINT PAULS CHURCH RD PYLESVILLE ROHREAUGH,KEN BOX: N04 Cad: 2010-0000162445
[!] EOC:E1011 MISC >MISC 4873 CAREA RD XS: X WALNUT SPRING CT WHITE HALL SHERBS,ROBERT BOX: N08 Cad: 2010-0000162395
[!] EOC:A1091 MVA >MV ACCIDENT W/INJURY SRT24&SAINT MARYS RD XS: SAINT MARYS RD PYLESVILLE DEDRICK.PATTI Cad: 2010-0000162161
[!] EOC:E1011 MVA >MV ACCIDENT W/INJURY SRT24&SAINT MARYS RD XS: SAINT MARYS RD PYLESVILLE DEDRICK.PATTI Cad: 2010-0000162161
[!] EOC:F03 BUILD >BUILDING FIRE 21 EASTERN AVE XS: S MAIN ST BEL AIR LEBRUN,MARLEEN BOX: 302 Cad: 2010-0000195539
Subject:HCCAD\n[!] EOC:F03 WIRES >WIRES/POLE SHAWNEE DR&WALTERS MILL RD XS: WALTERS MILL RD FOREST HILL NOT ENTERED Cad: 2010-000019169

Greene County, NY
GREENE 911:WIRES >WIRES DOWN / ARCING 117 RIVER RD ATHENS BRAUMULLER, HELENE Map: Grids:0,0 Cad: 2010-0000033052
GREENE 911:ALARMF>FIRE ALARM 558 SLEEPY HOLLOW RD ATHENS ADT SECURITY-OP ANDREA- Map:62 Grids:0,0 Cad: 2010-0000033344

Daviess County, KY
911-CENTER:MBD >BREATHING DIFFICULTY 6261 MILLERS MILL RD XS: HIGHWAY 142 PHILPOT MARTIN Map: Grids:, Cad: 2011-0000013315
911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY Map: Grids:, Cad: 2011-0000013291
911-CENTER:FF >WILDLAND FIRE 9707 OLD HARTFORD RD XS: U S HIGHWAY 231 UTICA KIPLING, ED Map: Grids:, Cad: 2011-0000013076
911-CENTER:FF >WILDLAND FIRE 12957 RED HILL-MAXWELL RD XS: E HARMONS FERRY RD UTICA PRESSON, DAVID Map: Grids:, Cad: 2011-0000012778
911-CENTER:FF >WILDLAND FIRE 9707 OLD HARTFORD RD XS: U S HIGHWAY 231 UTICA KIPLING, ED Map: Grids:, Cad: 2011-0000013076
911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY Map: Grids:, Cad: 2011-0000013291
911-CENTER:MBD >BREATHING DIFFICULTY 6261 MILLERS MILL RD XS: HIGHWAY 142 PHILPOT MARTIN Map: Grids:, Cad: 2011-0000013315
911-CENTER:MPD >UNKNOWN/PERSON DOWN 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO DAVIESS COUNTY BOARD OF EDUCAT Map: Grids:, Cad: 2011-0000013349
911-CENTER:FF >WILDLAND FIRE 2324 HARRIET LN XS: OLD HARTFORD RD OWENSBORO HUTCHINSON, G & M Map: Grids:, Cad: 2011-0000013387
911-CENTER:MSTR >STROKE 3294 BELLTOWN RD XS: NEW BETHEL CHURCH LN HARTFORD Map: Grids:, Cad: 2011-0000013576
911-CENTER:MSZ >SEIZURES 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO DAY, RUS Map: Grids:, Cad: 2011-0000013611

Herkimer County, NY 
(EMS   >EMS CALL) 185 GUIDEBOARD RD XS: DAIRY HILL RD NORWAY AAAAAAA AAAAAAA 3150000000 Map: Grids:, Cad: 2010-0000049305
(MVA   >MOTOR VEHICLE ACCIDENT) 5781 STHY 28 XS: TOWN LINE NEWPORT AAAAAA AAAAA 3150000000 Map: Grids:, Cad: 2010-0000049651
(EMS   >EMS CALL) 808 OLD STATE RD NEWPORT AAAAAAAA 8880000000 Map: Grids:, Cad: 2010-0000049432
(EMS   >EMS CALL) 3141 MECHANIC ST XS: MAIN ST NEWPORT VILLAGE JOHN 3157179999 Map: Grids:, Cad: 2010-0000058093
(EMS   >EMS CALL) 3746 BLACK CREEK RD Apt: C Bldg XS: TAYLOR BROOK RD RUSSIA SMITH, E 3158265805 Map: Grids:,Cad: 2010-0000058750
(FIREST>STRUCTURE FIRE) 1316 HARD SCRABBLE RD XS: BEGIN ESN 029 FAIRFIELD JONES, CORA 3158913818 Map: Grids:, Cad: 2010-0000060399
(LIFT  >LIFT ASSIST/NON EMER EMS) 3746 BLACK CREEK RD Apt: LOTC Bldg XS: TAYLOR BROOK RD RUSSIA JOHN SMITH 3158265805 Map: Grids:, Cad: 2010-0000064384
(EMS>EMS CALL) 112 N Main St\nGrids:,, NY X:Fairfield St\nMiddleville Village Smith, John\n5419991234 MAP:
(EMS>EMS CALL) 54 Fairfield St\nGrids:,, NY X:Fairfield St\nMiddleville Village Smith, John\n5419991234 MAP:
(LIFT>LIFT ASSIST/NON EMER EMS)\n112 N Main St Grids:,, NY\nX:Fairfield St Smith, John\n5419991234 MAP:

Macon County, NC
911 CENTER:88S >STRUCTURE FIRE REPORTED AT 1650 WIDE HORIZON DR FRANKLIN AAAAA, AAAAA 5555559999 Map: Grids:0,0
911 CENTER:90F >FIRE ALARM 120 RIVERVIEW ST FRANKLIN ANGEL MEDICAL CENTER 5555558411 Map: Grids:0,0
911 CENTER:88S >STRUCTURE FIRE REPORTED AT 289 SUTTON LN FRANKLIN AAAAA, AAAAAAA& AAAAAA 5555559999 Map: Grids:0,0
911 CENTER:MED >EMERGENCY RUN 248 HARRISON AVE XS: W MAIN ST TO BRYSON CITY RD FRANKLIN AAAAAA, CHARMAYNE 5555559999 Map: Grids:0,0
911 CENTER:C1 >EMERGENCY RUN 11 CHATTACHOEE LN FRANKLIN VERIZON WIRELESS 5555559999
911 CENTER:C1 >EMERGENCY RUN 236 PAULINE AVE XS: ULCO DR FRANKLIN AAAAA, AAAAAAA P 5555559999 Map: Grids:0,0
911 CENTER:88S >STRUCTURE FIRE REPORTED AT 1682 LEATHERMAN GAP RD FRANKLIN  AAA, ANGIE 5555559999 Map: Grids:0,0
911 CENTER:50 >VEHICLE ACCIDENT 837 GEORGIA RD FRANKLIN AAAAA 5555558237 Map: Grids:0,0
911 CENTER:C1 >EMERGENCY RUN 730 LAKESHORE DR FRANKLIN CHRISTIAN, JOHN SMITH 8285249999 Map: Grids:0,0
911 CENTER:88B >BRUSH FIRE REPORTED AT RADIO HILL RD FRANKLIN BLACKSTONE, SHORE 8285249991 Map: Grids:0,0
911 CENTER:C1 >EMERGENCY RUN 31 SCROGGS RD XS: LOUISA CHAPEL @ 1020 FRANKLIN VERIZON WIRELESS 8283713353 Map: Grids:0,0
911 CENTER:50 >VEHICLE ACCIDENT 428 COWEETA CHURCH RD OTTO SMITH, J 828369999 Map: Grids:0,0

Anderson County, SC
ANDERSON CO 911:29B1 >MVA-ALS PRI1 3814 N HIGHWAY 29 XS: BROOKS RD - DORCHESTER RD BELTON PLATINUM PLUS Map: Grids:0,0 Cad: 2011-0000004351
ANDERSON CO 911:29B4 >MVA-ALS PRI1 HIGHWAY 29 N MICHAEL QUEEN2214421 Cad: 2011-0000004070
ANDERSON CO 911:32 >UNKNOWN PROBLEM 1216 ROUND ABOUT TRL JOSH 201-4436 Cad: 2011-0000004370
ANDERSON CO 911:17B1 >FALLS-ALS PRI1-FR 110 FRANKLIN RD ANDERSON HAWKINS, PAUL Map: Grids:0,0 Cad: 2011-0000004223
ANDERSON CO 911:31 >UNCONSCIOUS/FAINTING 210 KIRK LN Apt: 11 Bldg PENDLETON6463347 MICHELLE DURHAM Map: Grids:0,0 Cad: 2011-0000004465
ANDERSON CO 911:13A1 >DIABETIC-ALS PRI2 7900 HIGHWAY 76 PENDLETON OFFICER SUMMERS 934-3443 Map: Grids:0,0 Cad: 2011-0000004494

Summer County, TN
INITIAL.\nSC EMS COMMUNICATIONS:42 >NON-SPECIFIC DIAGNOSIS 521 SHUTE LN XS: SHORESIDE DR HENDERSONVILLE  MIKE 6158249869 Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:50 >STROKE-CVA 114 MOONLIGHT DR HENDERSONVILLE AT&T MOBILITY 6153058788 Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:2 >FIRE ALARM 1109 LAKE RISE OVERLOOK HENDERSONVILLE L., JOHN 6158264086 Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:43 >OTHER-FIRE 209 BROOKHAVEN DR GALLATIN W., MYRA 6154529951 Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:32 >HAZARDOUS MATERIALS 2079 MORGANS WAY GALLATIN Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:10 >BREATHING PROBLEMS A 1080 BRADLEY RD GALLATIN E., ROGER D 6154528905 Map: Grids:0,0
INITIAL.\nSC EMS COMMUNICATIONS:2 >FIRE ALARM 1005 LAKE RISE PL HENDERSONVILLE HAY, BRUCE 6158262033 Map: Grids:0,0

Fayette County, Pennsylvania
FAYETTE-911:FACINJ>F-ACCIDENT W/ INJURIES VANCES MILL RD NORTH UNION WILSON PAUL 7249637757 Map: Grids:, Cad: 2011-0000029638
FAYETTE-911:ESCK_A>E SICK/UNKNOWN ALS 570 FLATWOODS RD XS: TOWN COUNTRY RD FRANKLIN ADAMS, J 7243980234 Map: Grids:, Cad: 2011-0000031363
FAYETTE-911:FACCNO>F_ACCIDENT NO INJURIES 1829 PITTSBURGH RD XS: RESERVOIR RD FRANKLIN HARPER, FRAN 4126910370 Map: Grids:, Cad: 2011-0000029521
FAYETTE-911:FACINJ>F-ACCIDENT W/ INJURIES VANCES MILL RD NORTH UNION WILSON PAUL 7249637757 Map: Grids:, Cad: 2011-0000029638
FAYETTE-911:FACINJ>F-ACCIDENT W/ INJURIES 2418 PITTSBURGH RD XS: RESERVOIR RD & BRADY LN FRANKLIN CHRIS 3046855898 Map: Grids:, Cad: 2011-0000029569
FAYETTE-911:EBRT_A>E_BREATHING ALS 1829 PITTSBURGH RD XS: RESERVOIR RD FRANKLIN DELBRIDGE JOY 7247106364 Map: Grids:, Cad: 2011-0000030505
FAYETTE-911:FACINJ>F-ACCIDENT W/ INJURIES BOLDEN RD XS: COOPER RD & PITTSBURGH RD FRANKLIN HIGGNS ERENST 7245526428 Cad: 2011-0000030796
FAYETTE-911:EBRT_A>E_BREATHING ALS 2527 PITTSBURGH RD FRANKLIN JOHNSON JULIE 7244157363 Map: Grids:, Cad: 2011-0000030936
FAYETTE-911:FTREE >F_TREE DOWN BUENA VISTA RD XS: FLATWOODS RD FRANKLIN THOMPSON KAREN 7248800052 Map: Grids:, Cad: 2011-0000031144
FAYETTE-911:FTREE >F_TREE DOWN BITNER RD XS: VANCES MILL RD & ROUND BARN RD FRANKLIN VERIZON WIRELESS 7248808596 Map: Grids:, Cad: 2011-0000031156
FAYETTE-911:ESCK_A>E SICK/UNKNOWN ALS 570 FLATWOODS RD XS: TOWN COUNTRY RD FRANKLIN ADAMS, J 7243980234 Map: Grids:, Cad: 2011-0000031363
FAYETTE-911:FTREE >F_TREE DOWN BITNER RD XS: VANCES MILL RD & ROUND BARN RD FRANKLIN ROSINSKI, SUSAN 7248802229 Map: Grids:, Cad: 2011-0000031167

Northampton County, NC
[e49]ALS >ADVANCED LIFE SUPPORT CALL 602 E 21ST ST Apt: 119 Bldg NORTHAMPTON DIANE ECK Map: Grids:0,0 Cad: 2011-0000086714
[e49]BLS >BASIC LIFE SUPPORT CALL 1323 NEWPORT AVE Apt: REAR Bldg NORTHAMPTON TONY FERRERA Map: Grids:0,0 Cad: 2011-00000
[e49]MVAI >MVA WITH INJURIES 248 AT PENNSVILLE LIGHT LEHIGH TWP NATALIE BRODIANO Cad: 2011-0000086361
[e49]ALS >ADVANCED LIFE SUPPORT CALL 612 E 10TH ST NORTHAMPTON ROBERTS PAMELA Map: Grids:0,0 Cad: 2011-0000086262
[e49]BLS >BASIC LIFE SUPPORT CALL 1001 WASHINGTON AVE Apt: 105 Bldg NORTHAMPTON MEGAN MOREY Map: Grids:0,0 Cad: 2011-000008
[e49]ALS >ADVANCED LIFE SUPPORT CALL 5962 KEYSTONE DR EAST ALLEN CHRISTINA WIGMER Map: Grids:0,0 Cad: 2011-0000086103
[e49]BLS >BASIC LIFE SUPPORT CALL 1323 NEWPORT AVE NORTHAMPTON TONY CABRERA Map: Grids:0,0 Cad: 2011-0000086010

*/

public class DispatchBParser extends SmartAddressParser {
  
  private static final String[] FIXED_KEYWORDS = new String[]{"Map", "Grids", "Cad"};
  private static final String[] KEYWORDS = 
    new String[]{"Loc", "BOX", "Map", "Grids", "Cad"};
  private static final Pattern PHONE_PTN = Pattern.compile(" (\\d{10})$");
  
  public DispatchBParser(String[] cityList, String defCity, String defState) {
    super(cityList, defCity, defState);
  }
  
  public DispatchBParser(String defCity, String defState) {
    super(defCity, defState);
  }
  
  /**
   * Determines if this is a CAD page (may be overridden by subclasses)
   * @param body
   * @return
   */
  protected boolean isPageMsg(String body) {
    return isPageMsg(body, FIXED_KEYWORDS);
  }
  
  /**
   * Processes the complicated first address field
   * Will usually be overridden by subclasses
   * @param field first address field
   * @param data message information data object
   * @return true if parse was successful
   */
  protected boolean parseAddrField(String field, Data data) {
    // Default is to ignore everything up to the first '>'
    int ipt = field.indexOf('>');
    if (ipt >= 0) field = field.substring(ipt+1);
    Matcher match = PHONE_PTN.matcher(field);
    if (match.find()) {
      data.strPhone = match.group(1);
      field = field.substring(0,match.start()).trim();
    }
    parseAddress(StartType.START_CALL, field, data);
    data.strName = getLeft();
    return true;
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    if (! isPageMsg(body)) return false;
    
    body = "Loc: " + body;
    Properties props = parseMessage(body, KEYWORDS);
    
    if (!parseAddrField(props.getProperty("Loc", ""), data)) return false;
    
    data.strBox = props.getProperty("BOX", "");
    data.strMap = props.getProperty("Map", "");
    String callId = props.getProperty("Cad");
    if (callId != null) data.strCallId = callId;
    
    return true;
  }
}
