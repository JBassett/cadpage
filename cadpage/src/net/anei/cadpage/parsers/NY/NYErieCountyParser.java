package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.SmsMsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;

/*
Erie County, NY
Contact: "Buttino, John" <John.Buttino@erie.gov>   (network administrator)
AMH 238 WESTFIELD RD EMS 79 YO F/  CHEST PAIN
AMH 52 ENDICOTT DR EMS 82 YO M
AMH 670 LONGMEADOW RD EMS 71 Y/O F CHEST PAINS, DIFFICULTY BREATHING
AMH 3030 SHERIDAN DR EMS RM 146 58 M TROUBLE BREATHING
AMH 35 ELM RD EMS 69M CHEST/ARM PAIN

Contact: nyerpa96 <nyerpa96@gmail.com>
ALERT@ERIE.GOV FIRE CO-DETECTOR 262 MILLER ST APT: GARAGE LANCASTER TOWN CO DETECTOR ACTIV / NO SYMPTOMS

Possible call code values

AMA             MUTUAL AID-Automatic Mutual Aid                   
AP              AIRPORT ALERT                                    
AST             MISCELLANEOUS-Assist                             
BA              ALARMS-Box Alarm                                 
DUP             Duplicate Call                                    
E911            Emergency Call                                    
EMA             ALARMS-EMS Alarm                                 
EMS             EMS-Emergency Medical Service                    
EMSC            EMS-Emergency Medical Service Cold               
F               Structure                                        
FAL             ALARMS-Fire Alarm Activation                     
HM              HAZMAT-Leak, Spill                               
MAA             MUTUAL AID-Ambulance                             
MAF             MUTUAL AID-Fire                                  
MAR             MUTUAL AID-Rescue                                
MAS             MUTUAL AID-Standby                               
MF              Miscellaneous                                    
MVA             EMS-Motor Vehicle Accident                       
MVF             Motor Vehicle                                    
NOT             Notification                                     
POL             Police Emergency                                 
R               EMS-Rescue                                       
SA              Silent Alarm                                     
TR              EMS-Transportation                               
TRC             EMS-Transportation Cold                          
TRH             EMS-Transportation Hot  
*/


public class NYErieCountyParser extends SmartAddressParser {
  
  private static final String DEF_STATE = "NY";
  private static final String DEF_CITY = "ERIE COUNTY";
  
  private static final String[] CITY_LIST = new String[]{
    "ALDEN TOWN",
    "ALDEN",
    "AMHERST TOWN",
    "ANGOLA ON THE LAKE",
    "ANGOLA",
    "AURORA TOWN",
    "BILLINGTON HEIGHTS",
    "BLASDELL",
    "BOSTON TOWN",
    "BRANT TOWN",
    "BUFFALO",
    "CHEEKTOWAGA TOWN",
    "CLARENCE CENTER",
    "CLARENCE TOWN",
    "COLDEN TOWN",
    "COLLINS TOWN",
    "CONCORD TOWN",
    "DEPEW",
    "EAST AMHERST",
    "EAST AURORA",
    "EDEN TOWN",
    "ELMA CENTER",
    "ELMA TOWN",
    "EVANS TOWN",
    "FARNHAM",
    "GOWANDA",
    "GRAND ISLAND TOWN",
    "HAMBURG TOWN",
    "HAMBURG",
    "HARRIS HILL",
    "HOLLAND TOWN",
    "KENMORE",
    "LACKAWANNA",
    "LAKE ERIE BEACH",
    "LAKE VIEW",
    "LANCASTER TOWN",
    "LANCASTER",
    "MARILLA TOWN",
    "NEWSTEAD TOWN",
    "NORTH BOSTON",
    "NORTH COLLINS TOWN",
    "NORTH COLLINS",
    "ORCHARD PARK TOWN",
    "ORCHARD PARK",
    "SARDINIA TOWN",
    "SLOAN",
    "SPRINGVILLE",
    "TONAWANDA TOWN",
    "TONAWANDA",
    "TOWN LINE",
    "WALES TOWN",
    "WEST SENECA TOWN",
    "WILLIAMSVILLE"
  };
  
  public NYErieCountyParser() {
    super(CITY_LIST, DEF_CITY, DEF_STATE);
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    if (body.startsWith("AMH ")) return parseAmhMsg(body.substring(4), data);
    if (body.startsWith("ALERT@ERIE.GOV ")) return parseErieMsg(body.substring(15), data);
    return false;
  }

  public boolean parseAmhMsg(String body, Data data) {
    parseAddress(StartType.START_ADDR, body, data);
    data.strCall = getLeft();
    
    return true;
  }

  private boolean parseErieMsg(String body, Data data) {
    parseAddress(StartType.START_CALL, body, data);
    data.strSupp = getLeft();
    return true;
  }
}
