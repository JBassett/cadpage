package net.anei.cadpage.parsers.NC;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchOSSIParser;

/* 
Buncombe county, NC
Contact: "dean.castaldo@att.net" <dean.castaldo@att.net>
Contact: Harry McLaughlin JR <harrymjr@gmail.com>
Sender: CAD@buncombecounty.org <From%3ACAD@buncombecounty.org>

CAD:433 LONG SHOALS RD;B21U;SUBJECT STILL DOWN;EDGAR BULLUCK;FALLS;LEDBETTER RD;CLAYTON RD
CAD:1 HAMBY DR;B9;(FR) STRUCTURE FIRE;OWENBY COVE RD
CAD:411 POCONO CT;B21U;EGERTON, NANCY;CHEST PAIN;ROCKY MOUNTAIN WAY
CAD:3055 SWEETEN CREEK RD;SMOKE DET/;PECHINEY PLASTIC PACKAGIN;ADT;(FR) FIRE ALARM;WESLEY DR;FAR HORIZONS LN;10015419
CAD:206 ROYAL PINES DR;B21;TAYLOR, ROBERT E;UNCONSCIOUS/ FAINTING;SWEETEN CREEK RD;APPIAN WAY
CAD:345 I26 W;8283372724 DIST: 1003.28 FT;VERIZON WIRELESS;CHEST PAIN;I26 W TO BREVARD RD;10015456
CAD:59 SMOKEMONT DR;B21U;BEYER,JAMES;HEART PROBLEMS;BEAR PAW LN;BOUNDARY TREE PASS
CAD:100 OAK TER;B21;TREE DOWN IN POWERLINES;VERIZON;(FR) LINES DOWN;N OAK TER;ROYAL PINES DR
CAD:16 BLUE HERON DR;B213;BECKHAM, LINDA & MALACHY;FALLS;RIVER CANE DR
CAD:BCFR ASSISTANCE REQUESTED;32 MCINTYRE DR;AS03;Event spawned from ASSIST LAW ENFORCEMENT.  [12/23/10 16:54:46 HONEYCJ] [LAW] COMP IS REQ EMS COME TO CHECK HER  [12/23/10 16:54:08 BRADLEYC]\nEvent spawned from DOMESTIC DISTURBANCE.  [12/23
CAD:2147 BREVARD RD;B21U;APT 4;HEART PROBLEMS;CARLAND DR;GLENN BRIDGE RD
CAD:1849 BREVARD RD;B212;BORG WARNER;(FR) VEHICLE FIRE;PLATINUM CT;HOLLY ACRES LN
CAD:245 CEDAR LN;B21;KALOGERAKIS, ARNOLD S;ABDOMINAL PAINS / PROBLEMS;SWEETEN CREEK RD;MAPLE CT
CAD:183 WESTON RD;B21;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN;TAMPA CIR;TAMPA CIR
CAD:579-BLK MILLS GAP RD;B213;8287720618 DIST: 107.57 FT;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN;CENTRAL PARK DR;DRAKES MEADOW LN
CAD:357 I26 E;B21;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN;I26 E TO LONG SHOALS RD
CAD:365 I26 W;B21;(Z)MOTOR VEH ACCIDENT UNKNOWN;LONG SHOALS RD TO I26 W
CAD:375 I26 W;B21;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN;I26 W TO LONG SHOALS RD;AIRPORT RD TO I26 W
CAD:275 MILLS GAP RD;B213;VERIZON;(Z)MOTOR VEH ACCIDENT INJURY;PINNERS COVE RD;SURREY RUN
CAD:355 I26 W;B21;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN;LONG SHOALS RD TO I26 W
AD:900-BLK CANE CREEK RD;B213;SHP;(FR)TREE DOWN;FAIR OAKS EST;RIVER CANE DR
CAD:33 THOROUGHBRED CIR;B21;ASSIST OFF FLOOR;(FR) SERVICE ANY TYPE;SADDLEWOOD LN;WATSON RD
CAD:354 I26 W;B21;VERIZON;(FR)TREE DOWN;LONG SHOALS RD TO I26 W
CAD:244 WILLIS WAY;B21;Buncombe County;BREATHING PROBLEMS
CAD:8 DANSFORD LN;B21U;MARFLAK, JOHN & AMBER;FALLS;WELBOURN WAY
CAD:31 HEYWOOD RD;B21;DIST: 1.51 FT;(Z)MOTOR VEH ACCIDENT UNKNOWN;HENDERSONVILLE RD;OLD HEYWOOD RD
CAD:11 SABRINA DR;B21U;SMOKE IN RESIDENCE;MILLER, JAMES A;(FR) STRUCTURE FIRE;ERIKA LN
CAD:475 MILLS GAP RD;B213;8289892954 DIST: 27.37 ft;VERIZON;(FR)TREE DOWN;CENTRAL PARK DR;FOUR OAKS DR
CAD:862 CANE CREEK RD/CROSS CREEK FARM RD;B213;(FR)TREE DOWN
CAD:29 HIGHBRIDGE XING;GENERAL FA;DEERFIELD ASSISTED LIVING;SIMPLEX;(FR) FIRE ALARM;LAMBETH DR;SALISBURY DR;10015610
CAD:226-B TAMPA CIR;B21;VERIZON;HEMORRHAGE / LACERATION;WESTON RD;WESTON RD
CAD:35 AIRPORT RD;COM. LIFE CENTER;ARDEN SEVENTH DAY ADVENTIST;DANIELS;(FR) FIRE ALARM;OLD SHOALS RD;WELLINGTON DR;10015618
CAD:20 GLENN BRIDGE RD;B21;8286990135;HUBBELL;HAYNES;(FR) FIRE ALARM;HENDERSONVILLE RD;WALDEN DR
CAD:4 WALDEN RIDGE DR;GENERAL ALARM;SIMPLEX;(FR) FIRE ALARM;HENDERSONVILLE RD;10015640
CAD:50 PINEHURST CIR;B21;GENERAL ALARM;GLEN ARDEN ELEMENTARY SCHOOL;SIMPLEX;(FR) FIRE ALARM;SAINT JOHNS ST
CAD:1 HENDERSON COUNTY HWY;B21;710 LINDSEY LOOP RD;(FR) MUTUAL AID;HENDERSONVILLE RD
CAD:202 SOUTHWAY GARDEN RD;B21;FALLS;SWEETEN CREEK RD
CAD:1845 BREVARD RD;B212;LUNCHROOM;CPU INC;CONVULSIONS / SEIZURES;COMMERCE WAY;PLATINUM CT
CAD:50-BLK CONCORD RD;B213;ASST STRANDED MOTORIST;RAINEY, AARON;(FR) SERVICE ANY TYPE;LAURELTON LN;WALSH TRCE
CAD:2251 HENDERSONVILLE RD;B21;MOUNTAIN ENERGY;ABDOMINAL PAINS / PROBLEMS;FOREST LN S;ROYAL PINES DR
CAD:331 CEDAR LN;B21;SWAIN, J WEAVER;DIABETIC PROBLEMS;MAPLE CT;MOSS LN
CAD:25 OLD SHOALS RD;SIMPLEX;(FR) FIRE ALARM;DOWNING DR;GLENN BRIDGE RD;10015691
CAD:277 MILLS GAP RD;B213;SMOKE DETECTOR;CPI;(FR) FIRE ALARM;PINNERS COVE RD;SURREY RUN
CAD:156 LOWER CHRIST SCHOOL RD/BALDWIN RD;B213;VERIZON;(Z)MOTOR VEH ACCIDENT UNKNOWN
CAD:280 BALL GAP RD;B212;ASSIST RTS;JEFF AT FIGHT WATCH;(FR) SERVICE ANY TYPE;LAKOTA TRL;BREVARD RD
CAD:2607 HENDERSONVILLE RD;B21;OOB GO GROCERY;SWAN,JOHN;(FR) ASSIST LAW ENFORCEMENT;S PARK CT;WATSON RD
CAD:280 BALL GAP RD;B212;ASSIST PAT UNLOADING;RTS;(FR) SERVICE ANY TYPE;LAKOTA TRL;BREVARD RD
CAD:100-BLK LEDBETTER RD;B21U;(Z)MOTOR VEH ACCIDENT UNKNOWN;LARK HL;CAVE LN
CAD:14 FOUR OAKS DR;B213;(FR) STRUCTURE FIRE;MILLS GAP RD
CAD:75 WHITE PINE DR;B213;WATER HEATER CAUGHT ON FIRE;(FR) INVESTIGATION;MAPLE ST;TROY HILL DR
CAD:37 FOXBERRY DR;B21;ASIST WITH WATER SHUT OFF;(FR) SERVICE ANY TYPE;NEW ROCKWOOD RD;SPRING HILL DR
CAD:503 AVERY CREEK RD;B21U;ASSIST SUBJ UP;MCCLURE,RONALD;(FR) SERVICE ANY TYPE;NEWMAN COVE RD;OWENBY LN
CAD:32 HOLLY ACRES LN;B212;(FR) SERVICE ANY TYPE;BREVARD RD
CAD:162 CAROLINA BLUE BIRD LOOP;B212;CLOSE,DAN & MARIE;UNKNOWN PROBLEM;ASHLEY WOODS DR
CAD:600 BARRETT LN;ADMIN OFFICES;GIVENS ESTATE HEALTH CENTER;GIVENS ESTATES - HEALTH CENTER;(FR) FIRE ALARM;WESLEY DR;HEMLOCK DR;11000026
CAD:1310 FANNING BRIDGE RD;ELECTROLUX;ADT;(FR) FIRE ALARM;UNDERWOOD RD;NEW AIRPORT RD;10015794
CAD:1301 FANNING BRIDGE RD;B21;STANDBY;WNC AGRICULTURAL CENTER;(FR) SERVICE ANY TYPE;UNDERWOOD RD;NEW AIRPORT RD
CAD:208 MILLS GAP RD;B213;CHECK O2 ON TODLER;(FR) SERVICE ANY TYPE;SOUTHSIDE VILLAGE DR;PINNERS COVE RD
CAD:9 SHADY OAK LN;B21U;ASSIST WITH  SMOKE DETECTOR;SPRINT PCS;(FR) SERVICE ANY TYPE;BREVARD RD
CAD:210 SUMMERGLEN DR;B212;CANNON, GLENDA;UNCONSCIOUS/ FAINTING;CASTLEROCK DR;DONNYBROOK DR
CAD:615 POLE CREASMAN RD;B212;SIMS, BETTY;BREATHING PROBLEMS;HOLLY LN;HAYES GREEN RD
CAD:2533 HENDERSONVILLE RD;B21;APT 102;CRESCENT VIEW;CRESCENT VIEW;UNCONSCIOUS/ FAINTING;UPPER LAUREL DR;NESBITT DR
CAD:347 I26 E;B21;8282169743;T-Mobile USA;(Z)MOTOR VEH ACCIDENT INJURY;I26 E TO LONG SHOALS RD
CAD:103 APPALACHIAN BLVD;B212;RM 309;HEATHER GLEN AT ARDEN WOODS;HEATHER GLEN AT ARDENWOODS;BREATHING PROBLEMS;ROCKY MOUNTAIN WAY
CAD:2533 HENDERSONVILLE RD;B21;RM 242;CRESCENT VIEW;CRESCENT VIEW;UNCONSCIOUS/ FAINTING;UPPER LAUREL DR;NESBITT DR
CAD:122 EDGEWOOD DR;BOTSFORD , BARBERA;LIFE-LINE;FALLS;DOWNING DR;DOWNING DR;11000163
CAD:342 DONNYBROOK DR;B212;SMELL OF GAS IN THE AREA;BOB ANDERSON;(FR) INVESTIGATION;AUBURNDALE DR
CAD:2400 APPALACHIAN BLVD;B212;CLUBHOUSE DINING RM;ARDENWOODS - CLUB HOUSE;ARDENWOODS RETIREMENT COMMUNIT;CHOKING;ROCKY MOUNTAIN WAY
CAD:30 HUNTER DR;B21U;STROKE;SLEEPY GAP RD
CAD:202 SUMMERGLEN DR;B212;ONEILL STEPHEN AND LESA;FALLS;BENT CREEK RANCH RD;CASTLEROCK DR
CAD:424 MILLS GAP RD;B213;GUTIERREZ, ROSENDO;CHEST PAIN;WESTON RD;BRAE BURN DR
CAD:7 SAINT ANDREWS RD;B213;JEFFRIES,DON;FALLS;CHIPPING GREEN DR;FOREST RIDGE DR
CAD:13 MCKENNA RD;MARSHALLS;(FR) FIRE ALARM;11000217
CAD:12 BERLINS VW;B102;8287120800;VERIZON;(FR) STRUCTURE FIRE;N LUTHER RD
CAD:206 ROYAL PINES DR;B21;TAYLOR, ROBERT E;BREATHING PROBLEMS;SWEETEN CREEK RD;APPIAN WAY
CAD:10 BENNINGTON CT;B21;SMITH, ANN & BILL;HEART PROBLEMS;OVERLOOK RD
CAD:800 MILLS GAP RD;B213;INSIDE THE STORE;MOLLYS MARKET;MOLLY`S MARKET,;HEART PROBLEMS;CANE CREEK RD;PENLEY RD
CAD:100 FREDERICK LAW OLMSTED WAY;B212;ARBORETUM;Buncombe County;(Z)MOTOR VEH ACCIDENT INJURY;GREENHOUSE WAY
CAD:280 BALL GAP RD;B212;ASSIST RTS LOADING A PATIENT;(FR) SERVICE ANY TYPE;LAKOTA TRL;BREVARD RD
CAD:53 DRIFTSTONE CIR;B212;WEERASIRI,DON;(FR) HAZARDOUS MATERIAL;RIVERCREST BLVD;RIVERCREST BLVD
CAD:2 BENT OAK LN;SIZEMORE, E;(FR) STRUCTURE FIRE;OVERLOOK RD;DEERHAVEN LN;11000280
CAD:25 OLD SHOALS RD;WATER FLOW ZONE M-123;FLINT GROUP;SIMPLEX;(FR) FIRE ALARM;DOWNING DR;GLENN BRIDGE RD;11000292
CAD:49 HIGHBRIDGE XING;RM 4101/ASSIST SUB. OFF FLOOR;DEERFIELD SKILLED CARE;DEERFIELD RETIREMENT;(FR) SERVICE ANY TYPE;LAMBETH DR;SALISBURY DR;11000293
CAD:100 VISTA BLVD;B212;NYPRO;(Z)MOTOR VEH ACCIDENT INJURY;BREVARD RD;TRIDENT DR
CAD:607 PARK SOUTH BLVD;B212;(FR) SERVICE ANY TYPE;BREVARD RD;PARK SOUTH CT
CAD:2533 HENDERSONVILLE RD;B21;RM 321;CRESCENT VIEW;CRESCENT VIEW;FALLS;UPPER LAUREL DR;NESBITT DR
CAD:92 FAIR OAKS RD;B21;LIFELINE;UNKNOWN PROBLEM;ROYAL OAKS RD;N FAIR OAKS RD
CAD:4 HICKORY TREE RD;B11;VERIZON;(Z)MOTOR VEH ACCIDENT INJURY;LOWER GRASSY BRANCH RD;JULIANNE PL

Contact: Bob sherwood <memphismadman@gmail.com>
S: M:CAD:25 REYNOLDS MOUNTAIN BLVD;B20;RM 126-A;EMERALD RIDGE;EMERALD RIDGE REHAB AND CARE C;ALLERGIES / REACTIONS;WEAVERVILLE RD
S:CAD:111 OLD WEAVERVILLE RD;B20;BLAZER, RUTH;UNCONSCIOUS/ FAINTING M: 

Contact: David Rice <cdrice15@gmail.com>
S: M:CAD:656 N FORK RD;B15;7654131974 DIST: 231.30 FT;Buncombe County;CHEST PAIN
S: M:CAD:503 PAINT FORK RD;B15;8287780508;Buncombe County;STROKE;ROCKY LN;HARWOOD RD

Contact: Bobby Davis <bobbyrdavis@gmail.com>
CAD:110-1/2 SUMMER ST;B6;VERIZON;(FR) STRUCTURE FIRE;PARKER DR;ALEXANDER AVE

*/

public class NCBuncombeCountyParser extends DispatchOSSIParser {
  
  public NCBuncombeCountyParser() {
    super("BUNCOMBE COUNTY", "NC",
           "ASSREQ? ADDR! UNIT? SPEC+? X X ID");
  }
  
  @Override
  public String getFilter() {
    return "CAD@buncombecounty.org";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    if (body.startsWith("S:") && body.endsWith(" M:")) {
      body = body.substring(2, body.length()-3).trim();
    }
    body = body.replaceAll("\n", " ");
    if (! super.parseMsg(body, data)) return false;
    if (data.strCall.length() == 0) data.strCall = "Unknown";
    return true;
  }
  
  /**
   * Call field that only trips if it contains the words "ASSISTANCE REQUESTED"
   */
  private class AssReqField extends CallField {
    
    @Override
    public boolean canFail() {
      return true;
    }

    @Override
    public boolean checkParse(String field, Data data) {
      
      if (!field.contains("ASSISTANCE REQUESTED")) return false;
      parse(field, data);
      return true;
    }
  }
  
  /**
   * Unit field is only valid if it fits the correct form
   */
  private class MyUnitField extends UnitField {
    public MyUnitField() {
      setPattern(Pattern.compile("B[0-9]{1,3}[A-Z]?"));
    }
  }
  
  /**
   * Covers fields entered in random order between the Unit and cross street
   */
  
  // Field will be treated as a call description if it contains
  // A something in parenthesis
  // A slash
  // Any of a set of words generally found in call descriptions
  private static final Pattern CALL_PATTERN = 
    Pattern.compile("^\\(.+\\)|/|\\b(FALL|FALLS|FIRE|PAIN|PAINS|HEART|PROBLEM|PROBLEMS|CHOKING|STROKE)\\b");
  private class SpecField extends Field {
    
    private Field callField = new CallField();
    private Field nameField = new NameField();
    private Field phoneField = new PhoneField();
    private Field idField = new IdField();
    private Field infoField = new InfoField();
    
    @Override
    public boolean canFail() {
      return true;
    }

    @Override
    public boolean checkParse(String field, Data data) {
      
      // Only fail if we find a valid address (meaning this is a cross street)
      if (checkAddress(field) == 1) return false;
      
      parse(field, data);
      return true;
    }

    @Override
    public void parse(String field, Data data) {
      
      if (field.startsWith("VERIZON")) return;
      if (field.startsWith("T-Mobile")) return;
      if (field.contains("DIST:")) return;
      
      Field fieldProc;
      if (field.contains(",")) {
        fieldProc = nameField;
      }
      
      else if (CALL_PATTERN.matcher(field).find()) {
        fieldProc = callField;
      }
      
      else if (field.length() == 10 && NUMERIC.matcher(field).matches()) {
        fieldProc = phoneField;
      }
      
      else if (field.length() == 8 && NUMERIC.matcher(field).matches()) {
        fieldProc = idField;
      }
      
      else {
        fieldProc = infoField;
      }
      
      fieldProc.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "CALL NAME PHONE INFO";
    }
  }
  
  @Override
  protected Field getField(String name) {
    if (name.equals("ASSREQ")) return new AssReqField();
    if (name.equals("UNIT")) return new MyUnitField();
    if (name.equals("SPEC")) return new SpecField();
    return super.getField(name);
  }
}
