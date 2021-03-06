package net.anei.cadpage.parsers.PA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA50Parser;

/**
 * Mercer County, PA
 */
public class PAMercerCountyParser extends DispatchA50Parser {
  
  public PAMercerCountyParser() {
    super(CALL_CODES, CITY_CODES, "MERCER COUNTY", "PA");
  }
  
  @Override
  public String getFilter() {
    return "@mcc.co.mercer.pa.us";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("IPS I/Page Notification")) return false;
    if (!super.parseMsg(body, data)) return false;
    if (data.strCity.length() == 0 && data.strName.endsWith(" CO")) {
      data.strCity = data.strName + "UNTY";
      data.strName = "";
    }
    if (OHIO_CITIES.contains(data.strCity.toUpperCase())) data.strState = "OH";
    return true;
  }
  
  @Override
  public String getProgram() {
    return super.getProgram().replace("CITY", "CITY ST");
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "CLRK",  "CLARK",
      "COOL",  "COOLSPRING TWP",
      "DEER",  "DEER CREEK TWP",
      "DELA",  "DELAWARE TWP",
      "ELAC",  "EAST LACKAWANNOCK TWP",
      "FAIR",  "FAIRVIEW TWP",
      "FARL",  "FARRELL",
      "FIND",  "FINDLEY TWP",
      "FRED",  "FREDONIA",
      "FREN",  "FRENCH CREEK TWP",
      "GRNE",  "GREENE TWP",
      "GRVL",  "GREENVILLE",
      "GROV",  "GROVE CITY",
      "HEMP",  "HEMPFIELD TWP",
      "HERM",  "HERMITAGE",
      "JACK",  "JACKSON TWP",
      "JCEN",  "JACKSON CENTER",
      "JAME",  "JAMESTOWN",
      "JEFF",  "JEFFERSON TWP",
      "LACK",  "LACKAWANNOCK TWP",
      "LAKE",  "LAKE TWP",
      "LIBR",  "LIBERTY TWP",
      "MERC",  "MERCER",
      "MILL",  "MILL CREEK TWP",
      "NLEB",  "NEW LEBANON",
      "NVER",  "NEW VERNON TWP",
      "OTTR",  "OTTER CREEK TWP",
      "PERR",  "PERRY TWP",
      "PINE",  "PINE TWP",
      "PYMA",  "PYMATUNING TWP",
      "SALM",  "SALEM TWP",
      "SCRK",  "SANDY CREEK TWP",
      "SLAK",  "SANDY LAKE",
      "SLTP",  "SANDY LAKE TWP",
      "SHAR",  "SHARON",
      "SHRP",  "SHARPSVILLE",
      "SKVL",  "SHEAKLEYVILLE",
      "SHEN",  "SHENANGO TWP",
      "SPYM",  "SOUTH PYMATUNING TWP",
      "SPRG",  "SPRINGFIELD TWP",
      "STON",  "STONEBORO",
      "SUGR",  "SUGAR GROVE",
      "WMID",  "WEST MIDDLESEX",
      "WSAL",  "WEST SALEM TWP",
      "WHEA",  "WHEATLAND",
      "WILM",  "WILMINGTON TWP",
      "WOLF",  "WOLF CREEK TWP",
      "WRTH",  "WORTH  TWP",
      
      "FARRELL",     "FARRELL",
      "HERMITAGE",   "HERMITAGE",
      "SHARON",      "SHARON",
      
      "CLARK",       "CLARK",
      "FREDONIA",    "FREDONIA",
      "GREENVILLE",  "GREENVILLE",
      "GROVE CITY",  "GROVE CITY",
      "JACKSON CENTER", "JACKSON CENTER",
      "JAMESTOWN",   "JAMESTOWN",
      "MERCER",      "MERCER",
      "NEW LEBANON", "NEW LEBANON",
      "SANDY LAKE",  "SANDY LAKE",
      "SHAPRSVILLE", "SHARPESVILLE",
      "SHEAKLEYVILLE", "SHEAKLEYVILLE",
      "STONEBORO",   "STONBORO",
      "WEST MIDDLESEX", "WEST MIDDLESEX",
      "WHEATLAND",   "WHEATLAND",
      
      "COOLSPRING",  "COOLSPRING TWP",
      "DEER CREEK",  "DEER CREEK TWP",
      "DELAWARE",    "DELAWARE TWP",
      "EAST LACKAWANNOCK",  "EAST LACKAWANNOCK TWP",
      "FAIRVIEW",    "FAIRVIEW TWP",
      "FINDLEY",     "FINDLEY TWP",
      "FRENCH CREEK","FRENCH CREEK TWP",
      "GREENE",      "GREENE TWP",
      "HEMPFIELD",   "HEMPFIELD TWP",
      "JACKSON",     "JACKSON TWP",
      "JEFFERSON",   "JEFFERSON TWP",
      "LACKAWANNOCK", "LACKAWANNOCK TWP",
      "LAKE",        "LAKE TWP",
      "LIBERTY",     "LIBERTY TWP",
      "MILL CREEK",  "MILL CREEK TWP",
      "NEW VERNON",  "NEW VERNON TWP",
      "OTTER CREEK", "OTTER CREEK TWP",
      "PERRY",       "PERRY TWP",
      "PYMATUNING",  "PYMATUNING TWP",
      "SALEM",       "SALEM TWP",
      "SANDY CREEK", "SANDY CREEK TWP",
      "SHENANGO",    "SHENANGO TWP",
      "SOUTH PYMATUNING", "SOUTH PYMATUNING TWP",
      "SPRINGFIELD", "SPRINTFIELD TWP",
      "SUGAR GROVE", "SUGAR GROVE TWP",
      "WEST SALEM",  "WEST SALEM TWP",
      "WILMINGTON",  "WILMINGTON TWP",
      "WOLF CREEK",  "WOLF CREEK TWP",
      "WORTH",       "WORTH TWP",
      
      // Butler county
      "CHERRY TWP",  "CHERRY TWP",
      
      // Trumble County, Ohio
      "MASURY",      "MASURY"
  });
  
  private static final Set<String> OHIO_CITIES = new HashSet<String>(Arrays.asList(
      "MASURY",
      "TRUMBLE COUNTY"
  ));
  
  private static final Properties CALL_CODES = buildCodeTable(new String[]{
      "AF",   "Fire-Admin Activity",
      "ANML", "Animal Rescue",
      "BACK", "Back Pains",
      "BARN", "Barn Fire",
      "BRU",  "Brush Fire",
      "CHIM", "Chimney Fire",
      "CMD",  "Carbon Monoxide Detector",
      "CMDS", "Carbon Monoxide detector w/ symptoms",
      "COM",  "Commercial Fire",
      "ELI",  "Electric Fire",
      "ELO",  "Electric Lines down",
      "EXP",  "Explosion",
      "FALM", "Fire alarm",
      "GARC", "Comercial Garage Fire",
      "GARF", "Garbage Fire",
      "GAS",  "Inside Gas Leak",
      "HAZ",  "Hazmat",
      "HF",   "House fire",
      "KITF", "Kitchen Fire",
      "MHF",  "Mobile Home Fire",
      "MUT",  "Mutual Aid",
      "MVA",  "Motor Vehicle Crash w. no injuries",
      "MVE",  "Motor Vehicle Crash w. trap",
      "MVI",  "Motor Vehicle Crash w/ injuries",
      "MVU",  "Motor Vehicle Crash w/ unknown injuries",
      "RES",  "Rescue Call",
      "ROAD", "Notification of Road Conditions",
      "QRS",  "EMS Call",
      "SAR",  "Search and Rescue",
      "SDET", "Smoke Detector Activation",
      "SERV", "Service Call",
      "SMOI", "Smoke in Structure",
      "STR",  "Structure Fire",
      "TREE", "Tree down",
      "UNKF", "Unknown Type Fire",
      "UTL",  "Utility Lines Down"
      
      // Unknown codes
      // ABD, ALLER, ASST, BLEED, BOMB, BURN, CHEST, DIAB, DMV, DROWN, FALL, FADM, FUEL, HEAD, HEART, INJP, MISP, MVF, ODR, OOS, PSYCH, PUMP, SICK, SEIZE, SMO, TEST, TRAS 
  });
}
