package net.anei.cadpage.parsers.NJ;

import java.util.Properties;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA5Parser;

/**
 * Burlington County, NJ
 */
public class NJBurlingtonCountyCParser extends DispatchA5Parser {
  
  private static final String TRIM_SUBJ_LEADER = "([ ";
  private static final String TRIM_SUBJ_TRAILER = ")]. ";
  
  public NJBurlingtonCountyCParser() {
    super("BURLINGTON COUNTY", "NJ");
  }
  
  @Override
  public String getFilter() {
    return "@CO.BURLINGTON.NJ.US";
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    // For some reason, these pages usually alter or eliminate the subject signature
    // that is required for all other A5 parser subclasses.
    String source = null;
    if (!subject.equals(SUBJECT_SIGNATURE)) {
      subject = subject.replace(SUBJECT_SIGNATURE, "").trim();
      int pt1 = 0;
      while (pt1 < subject.length() && TRIM_SUBJ_LEADER.indexOf(subject.charAt(pt1)) >= 0) pt1++;
      int pt2 = subject.length()-1;
      while (pt2 >= pt1 & TRIM_SUBJ_TRAILER.indexOf(subject.charAt(pt2)) >= 0) pt2--;
      source = subject.substring(pt1,pt2+1);
      subject = SUBJECT_SIGNATURE;
    }
    
    if (!super.parseMsg(subject, body, data)) return false;
    
    if (data.strSource.length() == 0 && source != null) data.strSource = source;
    
    // Expand call description
    Parser p = new Parser(data.strCall);
    String code = p.get(' ');
    String type = p.get(' ');
    if (type.equals("E") || type.equals("F")) {
      String desc = CALL_CODES.getProperty(code);
      if (desc != null) {
        data.strCode = code + " " + type;
        data.strCall = desc;
      }
    }
    
    // Fix city abbreviations
    data.strCity = convertCodes(data.strCity, CITY_ABBRV);
    return true;
  }
  
  private class MyCallField extends CallField {
    @Override
    public String getFieldNames() {
      return "CODE CALL";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("CALL")) return new MyCallField();
    return super.getField(name);
  }
  
  private static Properties CITY_ABBRV = buildCodeTable(new String[]{
      "BdntwnCity", "Bordentown City",
      "Burl Twp",   "Burlington Twp",
      "Chesterfld", "Chesterfield",
      "Cinnaminsn", "Cinnaminson Twp",
      "McGuireAFB", "McGuire AFB",
      "NewHanover", "New Hanover",
      "Ocean Co",   "Ocean County",
      "Pembtn Twp", "Pemberton Twp",
      "Southamptn", "Southampton",
      "Willingbor", "Willingboro",
      "Wrghtstwn",  "Wrightstown"
  });
  
  private static final Properties CALL_CODES = buildCodeTable(new String[]{
      "CCD",   "Critical Care Divert",
      "DUP",   "Duplicate Incident",
      "EMS",   "EMS Call",
      "ERB",   "ER ByPass",
      "TCD",   "Total Care Divert",
      "101",   "Abdominal Pain/Problems",
      "102",   "Allergies/Hives/Reaction/Stings",
      "103",   "Animal Bites",
      "104",   "Assault/Rape",
      "105",   "Back Pain",
      "106",   "Breathing Problems",
      "107",   "Burns-Thermal/Chemical",
      "108",   "Carbon Mono/Inhalation/Haz Mat",
      "109",   "Cardiac/Respiratory Arrest",
      "110",   "Chest Pain",
      "111",   "Choking",
      "112",   "Convulsions/Seizures",
      "113",   "Diabetic Problems",
      "114",   "Drowning (near)/Diving Accident",
      "115",   "Electrocution",
      "116",   "Eye Problems",
      "117",   "Falls/Fractures",
      "118",   "Headache",
      "119",   "Heart Problems",
      "120",   "Heat/Cold Exposure",
      "121",   "Hemorrhage",
      "122",   "Industrial/Machinery Accidents",
      "123",   "Overdose/Poisoning/Ingestion",
      "124",   "Pregnancy",
      "125",   "Psychiatric/Behavorial Problems",
      "126",   "Sick Person (specific diagnosis)",
      "127",   "Pentrating Wound/Stab/Gun",
      "128",   "Stroke/CVA",
      "129",   "Traffic Injury Accidents",
      "130",   "Traumatic Injuries/Lacerations",
      "131",   "Unconsciousness/Fainting",
      "132",   "Medical Emergency (Unknown Problem)",
      "133",   "Traffic Accidents/Entrapment",
      "134",   "Special Assignment",
      "135",   "Fire Standby",
      "136",   "Non Emergency Transportation",
      "137",   "Cover Assignment",
      "138",   "Training Session/Drills",
      "139",   "School Standyby,Education",
      "129F",  "Motor Vehicle Accident Fire Response",
      "11",    "Structure,Building Fire",
      "12",    "Structure-Non Fire",
      "13",    "Vehicles/Non Structure Fires",
      "14",    "Brush, Trees, Woods",
      "15",    "Non-Fire/Investigations, Odors,",
      "16",    "Alarm Systems",
      "17",    "Natural Gas, Propane Gas, Fumes,",
      "18",    "Wash Highway, Wash Roadway",
      "30",    "Rescue Assignment",
      "32",    "Medical Call, Assist BLS",
      "42",    "Explosive Device, Bomb Threat",
      "44",    "Wires, Transformers, Poles",
      "46",    "Helicopter Landings, Aircraft Stand By",
      "47",    "Hazardous Materials Incident",
      "51",    "Lock Out,Assist Police",
      "52",    "Pump Out, Water in Basement",
      "54",    "Animal Rescue",
      "55",    "Assist Police, Fire Police",
      "57",    "Cover Assignment",
      "AOS",   "Alarm Out of Service",
      "DUP",   "Duplicate Incident",
      "BURN",  "Controlled Burn",
      "FIRE",  "Fire Call"
  });
}
