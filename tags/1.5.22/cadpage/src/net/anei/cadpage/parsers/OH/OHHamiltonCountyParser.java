package net.anei.cadpage.parsers.OH;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.SmartAddressParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/*
Hamilton County, OH
Contact: Brian Fels <bfels.fd@northcollegehill.org>
Contact: "Jim Klems" <JimK@forestpark.org
Sender: hc@hamilton-co.org

HC:STRUCTURE FIRE 8877 DE SOTO DR SPTP NEISHA COOK/NGHBR ** STRUCTURE FIRE ** INV REF.. SMOKE & FLAMES VISIBLE FROM THE ROOF.. RESD POSS UNOCC, COMPL A 22:43 HFE75 HFE74 HFQ79 HFQ78 HFR79 HFS26 HFRAT97 HFFG3 HFIMAT79 XST: 1331 ANGELA AV XST
HC:STRUCTURE FIRE 12135 SPALDING DR COTP MATT GITTERMAN ** STRUCTURE FIRE ** ODOR OF PLASTIC BURNING LIGHT SMOKE FROM AN ATTIC VENT...CEILING IN THE K 06:47 HFE109 HFE102 HFE42 HFL25 HFL42 HFS25 HFRAT25 HFFG3 HFIMAT25 XST: 12037 WESTERLY DR
HC:STRUCTURE FIRE W CRESCENTVILLE RD&SPRINGFIELD SPDL PLANT MAINT ENG WIRELESS-VERIZON WIR ** STRUCTURE FIRE ** ROOF VENT ON FIRE 14:10 HFE90 HFE43 HFT90 HFT86 HFM90 HFFRFDM32 HFRAT96 HFFG5
HC:A/A - INJURY LEMONTREE DR&MILL RD FRPK INTERSECTION T-MOBILE USA, INC. ** AUTO ACCIDENT / PERSON INJURED ** INV FOR A 2 VEH, MC VS A VEH 14:53 HFM242 HFE42 HFL42 HFFG2
HC:PSYCHIATRIC EMER 5 BELKNAP PL GREN DOROTHY, MOM ** PSYCHIATRIC EMERGENCY ** C C REF 5 YO BEHAVING VIOLENTLY, ACTING OUT. HX OF BEHAVIORAL ISSUES. 16:59 HFS48 XST: 14 BACHMAN ST XST2: 26 BACHMAN ST
HC:FIRE ALARM 1086 PENNINGTON CT FRPK CHRISTINE ** FIRE ALARM ** INV...NOTHING SHOWING..COMP BELIEVES IT COULD BE A MALFUNTION WITH ALARM. 09:08 HFE43 HFL42 HFFG3 XST2: 11551 PROMENADE DR
HC:NON-BREATHER 37 GAMBIER CR GREN MICHEAL MARCUM ** NON-BREATHER / CARDIAC ARREST ** M/70'S, COMP CAN SEE M SUBJ LAYING ON THE FLOOR OF THE RESD THRU A WINDOW 20:29 HFS48 HFQ48 HFFG5 XST: 300 INGRAM RD XST2: 300 INGRAM RD
HC:UNCONSCIOUS PERSON 22 JEWEL LN GREN TIM SHEEIN ** PERSON UNCONSCIOUS / NON-RESPONSIVE ** F/46 DISORIENTED, POSS REACTION TO MEDS FOR MIGRANES 20:33 HFM242 HFGRES XST: 158 JUNEFIELD AV XST2: 168 JUNEDALE DR
HC:APPLIANCE FIRE 616 DEWDROP CR FRPK APT C CHRISTINA ** APPLIANCE FIRE ** GREASE FIRE ON THE STOVE 21:03 HFE43 HFE296 HFE97 HFE45 HFT90 HFL97 HFS97 HFRAT79 HFFG2 XST: 599 ASHBURN RD XST2: 601 DINSMORE DR
HC:STRUCTURE FIRE 70 DAMON RD GREN ALOIS ALZHEIMER CTR STEPHANIE ** STRUCTURE FIRE ** INVS SMOKE FILLING HALLS 05:05 HFQ48 HFE42 HFE109 HFE75 HFR78 HFL42 HFM96 HFRAT74 HFFG2 HFIMAT48 XST: 1200 SPRINGDALE RD XST2: 48 CROMWELL RD

Contact: Francisco Cáceres <caceres@fuse.net>
Sender:93001050
HC:FIRE ALARM 6855 ALBERLY LN INDN GENTILE RESD INDN PD ** FIRE ALARM ** GENERAL 02:47 HFE64 HFQ65 HFFG3 XST: 9200 SHAWNEE RUN RD
HC:FIRE ALARM 7141 MIAMI AV MDRA MDRA PD CAROL/ADT ** FIRE ALARM ** INV GEN FIRE ALARM, ZONE F1 AND SUPERVISORY TRBL SIGNAL ON E1. 01 1:24 HFQ65 XST: 
HC:FIRE ALARM 7141 MIAMI AV MDRA MDRA PD ADT..NOTIFING ** FIRE ALARM ** INV FOR..ZONE F1 FIRE ALARM ACTIVATION OPER/BEX 02:00 HF IGH BP WITH SOME 
HC:FIRE ALARM 7280 TANGLERIDGE LN INDN FELLER RESD RANGERS ** FIRE ALARM ** GENERAL FIRE ALRM 07:41 HFE64 HFQ65 HFFG2 XST: 8300 OLD STABLE
HC:FIRE ALARM 7850 TECUMSEH TL INDN INDN PD ** FIRE ALARM ** INV GEN FIRE ALARM. 02:17 HFE89 HFE92 HFFG3 HFMDRF XST: 4497 MIAMI RD
HC:TROUBLE BREATHING 5545 DRAKE RD INDN GLORIA ** TROUBLE BREATHING ** 87YO FEMALE...TRBL BREATHING 11:21 HFM65 XST: 8298 BRILL RD XST2: 8260 GRAVES RD
HC:WIRES DOWN 6506 KENWOOD RD MDRA ISSIAH ** WIRES DOWN / ARCING / ON FIRE ** C/COMP REF LOW-HANGING POWER LINES 00:01 HFQ65 XST: 6450 NAVAHO TL XST2: 6400 DAWSON RD

Contact: Dave Yergin <ffdyergin@gmail.com>
HC:CO ALARM 1737 HARMON DR WYOM LARRY MEYER ** CARBON MONOXIDE ALARM ** C/COMPL REF CO ALARM SOUNDING INTERMITTENTLY - COMPL IS HOOKED UP TO DIAL 05:28 HFE97 HFFG2 XST: ROLLING HILLS DR XST2: 1799 WOODRUFF LN
HC:MEDICAL ALARM 719 OAK AV WYOM RESD WHITE ADT-MVS ** MEDICAL ALARM ** THIRD PARTY FRM THE CITY.ADT CONTACTED THEM FOR A MEDICAL ALARM WITH NO V 11:18 HFE97 XST: 500 DAVID J SAVAGE W XST2: 398 WASHINGTON AV
HC:ODOR OF GAS 800 OAK AV WYOM WYOMING CITY HALL-1ST FLOOR REILAG 9701 ** SMELL OF GAS ** INV ODOR OF NATURAL GAS ON FIRST FLOOR----C9701 IS ENROUTE..... 09:05 HFE97 HFR97 HFFG2 XST: 398 WASHINGTON AV XST2: 498 PENDERY AV
HC:NON-BREATHER 1227 SPRINGFIELD PI WYOM GRACEWORKS ENHANCED LIVIN LINDA STEELE ** TROUBLE BREATHING ** F/40 04:24 HFE297 XST: 2 CHESTNUT AV XST2: 1 RITCHIE AV
HC:ODOR OF GAS 61 W SHARON RD GLEN ** SMELL OF GAS ** BR45 ON SCENE 11:50 HFR97 XST: 1001 CHURCH ST XST2: 999 SUMMIT AV:

*/

public class OHHamiltonCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER = Pattern.compile("HC:(.*) \\*\\* (.*) \\*\\* (.*) (\\d{1,2}:\\d\\d) (.*)");
 
  public OHHamiltonCountyParser() {
    super(CITY_CODES, "HAMILTON COUNTY", "OH");
  }
  
  @Override
  public String getFilter() {
    return "hc@hamilton-co.org,9300,messaging@iamresponding.com";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    
    Matcher match = MASTER.matcher(body);
    if (!match.matches()) return false;
    parseAddress(StartType.START_SKIP, match.group(1).trim(), data);
    data.strAddress = data.strAddress.replace("DE SOTO", "DESOTO");
    String sPlace = getLeft();
    if (sPlace.startsWith("APT ")) {
      Parser p = new Parser(sPlace.substring(4).trim());
      data.strApt = p.get(' ');
      sPlace = p.get();
    }
    data.strPlace = sPlace;
    data.strCall = match.group(2).trim();
    data.strSupp = match.group(3).trim();
    data.strTime = match.group(4);
    Parser p = new Parser(match.group(5).trim());
    String x2 = p.getLastOptional(" XST2:");
    String x1 = p.getLastOptional(" XST:");
    data.strUnit = p.get();
    data.strCross = append(x1, " & ", x2);
    
    return true;
  }

  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "ADDY", "ADDYSTON",
      "AMBR", "AMBERLY VILLAGE",
      "ANDR", "ANDERSON TWP",
      "ARLN", "ARLINGTON HEIGHTS",
      "BLUE", "BLUE ASH",
      "CHEV", "CHEVIOT",
      "CLVS", "CLEVES",
      "COTP", "COLERAIN TWP",
      "COLM", "COLUMBIA TWP",
      "CROS", "CROSBY TWP",
      "DRPK", "DEER PARK",
      "DLHI", "DELHI TWP",
      "ELMW", "ELMWOOD PLACE",
      "EVEN", "EVENDALE",
      "FRFX", "FAIRFAX",
      "FRPK", "FOREST PARK",
      "GLEN", "GLENDALE",
      "GOLF", "GOLF MANOR",
      "GRTP", "GREEN TWP",
      "GREN", "GREENHILLS",
      "HRTP", "HARRISON TWP",
      "HARR", "HARRISON",
      "INDN", "INDIAN HILL",
      "LINC", "LINCOLN HEIGHTS",
      "LOCK", "LOCKLAND",
      "LOVE", "LOVELAND",
      "MDRA", "MADEIRA",
      "MRMT", "MARIEMONT",
      "MIAC", "MIAMI TWP",
      "MIAM", "MIAMI TWP",
      "MILF", "MILFORD",
      "MONT", "MONTGOMERY",
      "MTHL", "MOUNT HEALTHY",
      "NEWT", "NEWTOWN",
      "NORW", "NORWOOD",
      "NRBN", "NORTH BEND",
      "NCHL", "NORTH COLLEGE HILL",
      "READ", "READING",
      "SHRN", "SHARONVILLE",
      "SPDL", "SPRINGDALE",
      "SPTP", "SPRINGFIELD TWP",
      "STBN", "SAINT BERNARD",
      "SYCM", "SYCAMORE TWP",
      "SYMM", "SYMMES TWP",
      "TERR", "TERRACE PARK",
      "UNTC", "UNION TWP",
      "WWTR", "WHITEWATER TWP",
      "WOOD", "WOODLAWN",
      "WYOM", "WYOMING"
  });
}
