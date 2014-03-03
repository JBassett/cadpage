package net.anei.cadpage.parsers.CO;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


/**
 * Weld County, CO
 */
public class COWeldCountyParser extends FieldProgramParser {

  public COWeldCountyParser() {
    this("WELD COUNTY", "CO");
  }
  
  protected COWeldCountyParser(String defCity, String defState) {
    super(defCity, defState,
          "SKIP ( CALL D | CALL2 ) ADDR SRC UNIT! INFO+");
  }
  
  @Override
  public String getAliasCode() {
    return "COWeldCounty";
  }
  
  @Override
  public String getFilter() {
    return "777,888,wrc-hiplink@weldcorcc.com,9300";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    if (body.startsWith("Dispatch / ")) body = body.substring(11).trim();
    return parseFields(body.split("\n"), data);
  }
  
  private class MyCallField extends CallField {
    
    private boolean strict;
    
    public MyCallField(boolean strict) {
      this.strict = strict;
    }
    
    @Override
    public void parse(String field, Data data) {
      String desc = CALL_CODES.getProperty(field);
      if (desc == null) {
        if (strict) abort();
        desc = field;
      }
      data.strCode = field;
      data.strCall = desc;
    }
    
    @Override
    public String getFieldNames() {
      return "CODE CALL";
    }
  }
  
  private static final Pattern WCR_PTN = Pattern.compile("\\bWCR\\b", Pattern.CASE_INSENSITIVE);
  private static final Pattern APT_PTN = Pattern.compile("[A-Z]?\\d+[A-Z]?");
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      int pt = field.indexOf(';');
      if (pt >= 0) {
        String sPlace = field.substring(pt+1).trim();
        if (APT_PTN.matcher(sPlace).matches()) {
          data.strApt = sPlace;
        } else {
          data.strPlace = sPlace;
        }
        field = field.substring(0,pt).trim();
      }
      field = WCR_PTN.matcher(field).replaceAll("CR").replace('@', '&');
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " APT PLACE";
    }
  }
  
  private static final Pattern CODE_PTN = Pattern.compile("ProQA Medical Recommended Dispatch Level = (\\w+)");
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      Matcher match = CODE_PTN.matcher(field);
      if (match.matches()) {
        data.strCode = match.group(1);
      } else {
        super.parse(field, data);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "CODE INFO";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("CALL")) return new MyCallField(false);
    if (name.equals("D")) return new SkipField("D", true);
    if (name.equals("CALL2")) return new MyCallField(true);
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private static final Properties CALL_CODES = buildCodeTable(new String[]{
      "ABAN",            "ABANDONED VEHICLE",
      "AIR",             "AIRCRAFT EVENT",
      "ALARMA",          "ALARM ALL - LAW FIRE EMS",
      "ALARMB",          "ALARM BURGLARY",
      "ALARMFC",         "FIRE ALARM COMMERCIAL",
      "ALARMFR",         "FIRE ALARM RESIDENTIAL",
      "ALARMH",          "HOLD UP ALARM",
      "ALARMO",          "ALARM OTHER",
      "ANIMAL",          "ANIMAL COMPLAINT",
      "AOA",             "ASSIST OTHER AGENCY",
      "AOAALARMF",       "AOA FOR FIRE ALARM",
      "AOAMED",          "ASSIST MEDICAL",
      "APB",             "ALL POINTS BULLETIN",
      "ARSON",           "ARSON",
      "ASALJC",          "ASSAULT IN THE JAIL",
      "ASALTC",          "COLD ASSAULT",
      "ASALTI",          "ASSAULT IN PROGRESS",
      "ASSIST",          "CITIZEN ASSIST",
      "ATSUCI",          "ATTEMPT SUICIDE",
      "AUTPRC",          "AUTO PROWL COLD",
      "AUTPRI",          "AUTO PROWL IN PROGRESS",
      "AWATCH",          "AREA WATCH",
      "BACK PAIN",       "BACK PAIN",
      "BAR",             "BAR CHECK",
      "BDOG",            "BARKING DOG",
      "BITE",            "ANIMAL BITE INVESTIGATION",
      "BOLO",            "BOLO INFO",
      "BOMB",            "BOMB THREAT",
      "BURGC",           "BURGLARY COLD",
      "BURGI",           "BURGLARY IN PROGRESS",
      "CC",              "CITIZEN CONTACT",
      "CIVIL",           "CIVIL PROCESS",
      "CODE",            "CODE ENFORCEMENT",
      "CONAN",           "CONTAINED ANIMAL",
      "CONTB",           "CONTROLLED BURN",
      "CURFEW",          "CURFEW VIOLATION",
      "CVIN",            "CERTIFIED VIN CHECK",
      "CWB",             "CHECK WELL BEING",
      "DAL",             "DOG AT LARGE",
      "DEM",             "DELIVER EMERGENCY MESSAGE",
      "DETOX",           "DETOX TRANSPORT",
      "DISTI",           "DISTURBANCE",
      "DOM",             "DOMESTIC VIOLENCE",
      "DOOR",            "DOOR LOCK AND UNLOCK",
      "DRILL",           "FIRE DRILL",
      "DTRO",            "TRO COLD DELAYED",
      "EXPLO",           "EXPLOSION LAW FIRE EMS",
      "FALSE ALARM",     "FALSE ALARM-ALARM TRACKING",
      "FAMOFF",          "FAMILY OFFENSE",
      "FAOA",            "FIRE ASSIST OTHER AGENCY",
      "FASIST",          "FIRE ASSIST",
      "FIGHT",           "FIGHT",
      "FIGHTW",          "FIGHT WITH WEAPONS",
      "FIREGC",          "FIRE GROUND COVER",
      "FIRESC",          "COMMERCIAL STRUCTURE FIRE",
      "FIRESR",          "RESIDENTIAL STRUCTURE FIRE",
      "FIRET",           "TRASH FIRE",
      "FIREV",           "VEHICLE FIRE",
      "FORGE",           "FORGERY",
      "FP",              "FOOT PURSUIT",
      "FRAUD",           "FRAUD",
      "FUP",             "FOLLOW UP",
      "FUPA",            "FOLLOW UP ACO",
      "FUPF",            "FOLLOW UP FIRE",
      "FWORKS",          "FIREWORKS COMPLAINT",
      "HANGUP",          "911 HANGUP",
      "HARASS",          "HARASSMENT",
      "HAZMAT",          "HAZARDOUS MATERIALS INCIDENT",
      "HOME",            "HOME VISIT",
      "ICCS",            "ICCS",
      "INASLT",          "ASSAULT IN PROG WITH INJURY",
      "INJDOG",          "INJURED ANIMAL",
      "INVDTH",          "INVESTIGATED DEATH",
      "JUV",             "JUVENILE PROBLEM",
      "KIDC",            "COLD KIDNAPPING",
      "KIDI",            "KIDNAPPING IN PROGRESS",
      "LDMUSC",          "LOUD MUSIC",
      "LDPRTY",          "LOUD PARTY",
      "LIQ",             "LIQUOR VIOLATION",
      "LITTER",          "LITTERING COMPLAIN",
      "MEET",            "MEET",
      "MENACC",          "COLD MENACE",
      "MENACI",          "MENACE IN PROGRESS",
      "MESAGE",          "MESSAGE",
      "MISSAD",          "MISSING ADULT",
      "MISSCH",          "MISSING CHILD",
      "MUT",             "MUTUAL AID",
      "NARC",            "CONTROLLED SUBSTANCE CALL",
      "NHPHS",           "NEIGHBORHOOD HOT SPOT PATROL",
      "NOISE",           "NOISE COMPLAINT",
      "NOISEV",          "NOISY VEHICLE",
      "PARK",            "PARKING COMPLAINT",
      "PHONE",           "OBSCENE/NUSI PHONE CALLS",
      "POP",             "POP REPORT",
      "POPH",            "POP ANIMAL HOARDERS",
      "PROP",            "FOUND/LOST PROPERTY",
      "PTOW",            "PRIVATE TOW",
      "RAJ",             "RUNAWAY JUVENILE",
      "RAPE",            "RAPE/SEXUAL ASSAULT",
      "RAPEI",           "RAPE IN PROGRESS",
      "RAV",             "RUNAWAY VEHICLE",
      "REPO",            "REPOSSESSION",
      "ROB",             "ROBBERY",
      "ROBI",            "ARMED ROBBERY IN PROGRESS",
      "ROBSA",           "STRONG ARM ROBBERY",
      "ROVC",            "RESTRAINING ORDER VIOL COLD",
      "ROVI",            "RESTRAINING ORDER VIO IN PROGR",
      "RSTEAL",          "RECOVERED STOLEN VEHICLE",
      "SEX",             "SEX OFFENSE",
      "SEXI",            "SEX OFFENSE IN PROGRESS",
      "SHOOT",           "SHOOTING",
      "SHOP",            "SHOPLIFTER NO PROBLEMS",
      "SHOPP",           "SHOPLIFTER WITH PROBLEMS",
      "SHOT",            "SHOTS FIRED",
      "SI",              "SICK AND INJURED FIRE/EMS",
      "SIA",             "SICK INJURED AMBULANCE ONLY",
      "SIPF",            "SICK AND INJURED POLICE/FIRE",
      "SMKODR",          "SMOKE ODOR INVESTIGATION",
      "SNOW",            "SNOW REMOVAL COMPLAINT",
      "SNOW25",          "SNOW I 25",
      "SNOW76",          "SNOW I 76",
      "SNOWN",           "SNOW HY 34 NORTH",
      "SNOWS",           "SNOW HY 34 SOUTH",
      "STAB",            "STABBING",
      "STAND",           "FIRE/MEDICAL STAND",
      "STORM",           "STORM WARNING",
      "SUSA",            "SUSPICIOUS ACTIVITY",
      "SUSP",            "SUSPICIOUS PERSON",
      "SUSV",            "SUSPICIOUS VEHICLE",
      "SVEHC",           "STOLEN VEHICLE COLD",
      "SVEHI",           "STOLEN VEHICLE IN PROGRESS",
      "SXO",             "SEX OFFENDER REGISTRANT",
      "T",               "TRAFFIC STOP",
      "TA",              "TRAFFIC ACCIDENT NON INJURY",
      "TAC",             "TRAFFIC ACCIDENT - COLD",
      "TAHR",            "TRAFFIC ACCIDENT HIT AND RUN",
      "TAI",             "TRAFFIC ACCIDENT WITH INJURY",
      "TAU",             "TRAFFIC ACCIDENT UNKNOWN INJ",
      "TAUP",            "TRAF ACC UNK INJ POLICE",
      "TEST",            "TEST CALL",
      "THEFTC",          "COLD THEFT",
      "THEFTI",          "THEFT IN PROGRESS",
      "TP",              "TRAFFIC PURSUIT",
      "TRAFA",           "TRAFFIC ARREST",
      "TRAFC",           "TRAFFIC COMPLAINT",
      "TRAFH",           "TRAFFIC HAZARD",
      "TRESPC",          "TRESPASS COLD",
      "TRESPI",          "TRESPASS IN PROGRESS",
      "UNWANT",          "UNWANTED PERSON",
      "VANDC",           "VANDALISM COLD",
      "VANDI",           "VANDALISM IN PROGRESS",
      "VARDA",           "VARDA ALARM",
      "VDAL",            "DOG AT LARGE VICIOUS",
      "VEU",             "SPECIAL VICE ENFORCEMENT",
      "VIN",             "VIN CHECK",
      "WARR",            "WARRANT ARREST/ATTEMPT",
      "WATER",           "WATER COMPLAINT",
      "WEAPON",          "PERSON WITH A WEAPON",

  });
}
