package net.anei.cadpage.parsers.NY;

import java.util.Properties;

import net.anei.cadpage.parsers.Message;
import net.anei.cadpage.parsers.SmartAddressParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class NYErieCountyEParser extends SmartAddressParser {
  
  private Message msg;
  
  public NYErieCountyEParser() {
    super(CITY_LIST, "ERIE COUNTY", "NY");
  }

  @Override
  protected Data parseMsg(Message msg, int parserFlags) {
    this.msg = msg;
    return super.parseMsg(msg, parserFlags);
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    if (msg.getAddress().equals("ALERT@ERIE.GOV")) return parseErieMsg(body, data);
    if (subject.equals("Call") || msg.getAddress().startsWith("9300")) return parseDepewMsg(body, data); 
    return false;
  }

  private boolean parseErieMsg(String body, Data data) {
    parseAddress(StartType.START_CALL, body, data);
    data.strSupp = getLeft();
    if (data.strAddress.length() == 0) return false;
    data.strCity = convertCodes(data.strCity, CITY_CODES);
    return true;
  }

  private boolean parseDepewMsg(String body, Data data) {
    if (body.endsWith("?")) body = body.substring(0,body.length()-1).trim();
    parseAddress(StartType.START_CALL, body, data);
    if (data.strAddress.length() == 0) return false;
    if (data.strCall.length() == 0) {
      data.strCall = getLeft();
    } else {
      data.strSupp = getLeft();
    }
    return true;
  }
  
  private static final String[] CITY_LIST = new String[]{
    "ALDEN TOWN",
    "ALDEN",
    "AMHERST TOWN",
    "AMHERST",
    "ANGOLA ON THE LAKE",
    "ANGOLA",
    "AURORA TOWN",
    "AURORA",
    "BILLINGTON HEIGHTS",
    "BLASDELL",
    "BOSTON TOWN",
    "BOSTON",
    "BRANT TOWN",
    "BRANT",
    "BUFFALO",
    "CHEEKTOWAGA TOWN",
    "CHEEKTOWAGA",
    "CLARENCE CENTER",
    "CLARENCE TOWN",
    "CLARENCE",
    "COLDEN TOWN",
    "COLDEN",
    "COLLINS TOWN",
    "COLLINS",
    "CONCORD TOWN",
    "CONCORD",
    "DEPEW",
    "EAST AMHERST",
    "EAST AURORA",
    "EDEN TOWN",
    "EDEN",
    "ELMA CENTER",
    "ELMA TOWN",
    "ELMA",
    "EVANS TOWN",
    "EVANS",
    "FARNHAM",
    "GOWANDA",
    "GRAND ISLAND TOWN",
    "GRAND ISLAND",
    "HAMBURG TOWN",
    "HAMBURG",
    "HARRIS HILL",
    "HOLLAND TOWN",
    "HOLLAND",
    "KENMORE",
    "LACKAWANNA",
    "LAKE ERIE BEACH",
    "LAKE VIEW",
    "LANCASTER TOWN",
    "LANCASTER",
    "MARILLA TOWN",
    "MARILLA",
    "NEWSTEAD TOWN",
    "NEWSTEAD",
    "NORTH BOSTON",
    "NORTH COLLINS TOWN",
    "NORTH COLLINS",
    "ORCHARD PARK TOWN",
    "ORCHARD PARK",
    "SARDINIA TOWN",
    "SARDINIA",
    "SLOAN",
    "SPRINGVILLE",
    "TONAWANDA TOWN",
    "TONAWANDA",
    "TOWN LINE",
    "WALES TOWN",
    "WALES",
    "WEST SENECA TOWN",
    "WES SENECA",
    "WILLIAMSVILLE",
    
    "WSE"
  };
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "WSE",  "WEST SENECA",
  });
}
