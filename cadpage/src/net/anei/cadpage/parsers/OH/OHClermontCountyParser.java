package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchBParser;

/*
Clermont county, OH
Contact: carl jervis <cjervis911@yahoo.com>
Sender: 911-center@co.clermont.oh.us
System: CAD Interactive 6.1.1.63

FSTRUC>STRUCTURE FIRE 1890 SR 232 XS: LAUREL POINT ISABEL RD LAUREL BUTLER, SHAWN Map: Grids:, Cad: 2011-0000014744
MED >MED GENERIC DO NOT DELETE 2458 SR 756 XS: SR 743 WASHINGTON TOWNSHIP BETTLES,LINDA Map: Grids:, Cad: 2011-0000015125
FDUMP >DUMPSTER FIRE 2601 WILDWOOD LN XS: SR 222 TATE TOWNSHIP WIRELESS-VERIZON(XYP) Map: Grids:, Cad: 2011-0000015892
06D02 >BREATHING PROBLEMS 2458 SR 756 XS: SR 743 WASHINGTON TOWNSHIP LESTER,CURTIS Map: Grids:, Cad: 2011-0000016117
29B04 >TRAFF OR TRANSPT ACC/MVA W/INJ 1542 SR 133 XS: RUDD RD FRANKLIN TOWNSHIP WIRELESS-AT&T MOBILITY(XYP) Map: Grids:, Cad: 2011-0000016205
06D02 >BREATHING PROBLEMS 2458 SR 756 XS: SR 743 WASHINGTON TOWNSHIP LESTER,CURTIS Map: Grids:, Cad: 2011-0000016117
29B01 >TRAFF OR TRANSPT ACC/MVA W/INJ BETHEL HYGIENE RD&amp;SR 133 XS: SR 133 TATE TOWNSHIP Cad: 2011-0000016983
FSMOKE>SMOKE IN THE AREA 3465 SR 774 XS: HENSON RD FRANKLIN TOWNSHIP LORI SPARKS Map: Grids:, Cad: 2011-0000017023
29B01 >TRAFF OR TRANSPT ACC/MVA W/INJ LAUREL PT ISABEL RD&BECKELHYMER RD XS: BECKELHYMER RD WASHINGTON TOWNSHIP HAYMOND,LINDA S Cad: 2011-0000017231
FWIRES>WIRES DOWN E OSBORNE ST&N EAST ST XS: N EAST ST BETHEL JEFF W/RUMPKE Cad: 2011-0000017232

Contact: Ryan Payer <ryan.payer3@gmail.com>
FSTRUC>STRUCTURE FIRE 966 E LEGENDARY RUN XS: ABERDEEN RIDGE PIERCE TOWNSHIP HANSBAUER,DAVID & LINDA Map: Grids:, Cad: 2011-0000069848

Contact:  James <firefighter1249@gmail.com>
Sender: 911-CENTER@clermontcountyohio.gov
04B01 >ASSAULT/SEXUAL ASSAULT 5728 GAVEY WAY XS: SR 131 WAYNE TOWNSHIP JEFFERS,JORDAN Map: Grids:, Cad: 2011-0000077041

Contact: Dennis Rutter <dennis.rutter73@gmail.com>
FALARM>FIRE ALARM 1466 HWY 50 XS: BARRETT RD STONELICK TOWNSHIP CINTAS FIRE PROTECTION-55 Map: Grids:, Cad: 2011-0000077411

Contact: kiethw@frontier.com
MED >MED GENERIC DO NOT DELETE 2800 LINK SIDE DR Apt: 4 Bldg XS: ST ANDREWS DR PIERCE TOWNSHIP WETZELL,CONNIE Map: Grids:, Cad: 2011-0000082440

Contact: Harold Thiele <thieleha@gmail.com>
Sender: 911-CENTER@clermontcountyohio.gov
31C02 >UNCONSCIOUS/FAINTING (NEAR) 5877 WOLFPEN PLEASANT HILL RD Apt: 316 Bldg XS: TRAVERSE CREEK DR MIAMI TWP RYAN, CHRISTY Map: Grids:, Cad: 2012-0000002524

Contact: "kieth" <kiethw@frontier.com>
Sender: 777172526324
" " 09B01 >CARDIAC OR RESP ARREST/DEATH 3373 COLE RD XS: CHERY LN PIERCE TOWNSHIP SCHAEFFER, DANIEL P Map: Grids:, Cad: 2012-0000015385 TXT STOP to opt-out
" " FBURN >POSSIBLE OPEN BURN BRANDYWINE DR&CUTTY SARK XS: CUTTY SARK LINDALE REFUSED Cad: 2012-0000025310
" " 26A01 >SICK PERSON (SPECIFIC DIAG) 1751 OHIO PIKE Apt: 156 Bldg XS: EAST FORK DR PIERCE TOWNSHIP REFUSED Map: Grids:, Cad: 2012-0000046833

Contact: Active911
[] 911-CENTER:MED >MED GENERIC DO NOT DELETE 51 DEERFIELD DR XS: DEERFIELD RD GOSHEN TOWNSHIP PAYTES,TINA & TED Map: Grids:, Cad: 2012-0000052916\n
[] 911-CENTER:EVENT: 06D02 LOC:51 DEERFIELD DR Cad: 2012-0000052916 DSP >12:10 ER >12:11 CLR >12:39\n
[] 911-CENTER:06D02 >BREATHING PROBLEMS 51 DEERFIELD DR XS: DEERFIELD RD GOSHEN TOWNSHIP PAYTES,TINA & TED Map: Grids:, Cad: 2012-0000052916\n
[] 911-CENTER:06D02 >BREATHING PROBLEMS 6409 SNIDER RD XS: WOODVILLE PI GOSHEN TOWNSHIP GODSEY,GENEVA Map: Grids:, Cad: 2012-0000053000\n
[] 911-CENTER:EVENT: 06D02 LOC:6409 SNIDER RD Cad: 2012-0000053000 ER >16:54 OS >16:59 OK >17:10 CLR >17:17\n

*/

public class OHClermontCountyParser extends DispatchBParser {
  
  private static final String[] CITY_CODES = new String[]{
    "BATAVIA TOWNSHIP", "FRANKLIN TOWNSHIP", "GOSHEN TOWNSHIP", 
    "JACKSON TOWNSHIP", "MIAMI TOWNSHIP", "MONROE TOWNSHIP", "OHIO TOWNSHIP", 
    "PIERCE TOWNSHIP", "STONELICK TOWNSHIP", "TATE TOWNSHIP", "UNION TOWNSHIP",
    "WASHINGTON TOWNSHIP", "WAYNE TOWNSHIP", "WILLIAMSBURG TOWNSHIP",
    
    "BATAVIA TWP", "FRANKLIN TWP", "GOSHEN TWP", 
    "JACKSON TWP", "MIAMI TWP", "MONROE TWP", "OHIO TWP", 
    "PIERCE TWP", "STONELICK TWP", "TATE TWP", "UNION TWP",
    "WASHINGTON TWP", "WAYNE TWP", "WILLIAMSBURG TWP",
    
    "AFTON", "AMELIA", "BANTAM", "BATAVIA", "BELFAST", "BETHEL", "BRANCH HILL",
    "CHILO", "CLERMONTVILLE", "EDENTON", "FELICITY", "GLEN ESTE", "GOSHEN",
    "HAMLET", "HULINGTON", "LAUREL", "LERADO", "LINDALE", "LOCUST CORNER",
    "LOVELAND", "MARATHON", "MIAMIVILLE", "MILFORD", "MONTEREY", "MOSCOW",
    "MOUNT CARMEL", "MOUNT HOLLY", "MOUNT PISGAH", "MOUNT REPOSE", "MULBERRY",
    "NEVILLE", "NEW RICHMOND", "NEWTONSVILLE", "NICHOLSVILLE", "OLIVE BRANCH",
    "OWENSVILLE", "PALESTINE", "PERINTOWN", "POINT ISABEL", "POINT PLEASANT", 
    "SALTAIR", "SUMMERSIDE", "TOBASCO", "UTOPIA", "WIGGONSVILLE", "WILLIAMSBURG",
    "WITHAMSVILLE", "WOODVILLE"
  };

  public OHClermontCountyParser() {
    super(CITY_CODES, "CLERMONT COUNTY", "OH");
  }
  
  @Override
  public String getFilter() {
    return "911-CENTER@clermontcountyohio.gov,777";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    if (body.startsWith("/ ")) body = body.substring(2).trim();
    return super.parseMsg(body, data);
  }

  @Override
  protected boolean isPageMsg(String body) {
    return body.contains(" Cad:") && !body.startsWith("EVENT: ");
  }
}
