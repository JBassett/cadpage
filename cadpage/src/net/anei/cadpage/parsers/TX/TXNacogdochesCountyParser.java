package net.anei.cadpage.parsers.TX;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA19Parser;
/**
 * Nacogdoches County, TX
 */
public class TXNacogdochesCountyParser extends DispatchA19Parser {
  
  public TXNacogdochesCountyParser() {
    super("NACOGDOCHES COUNTY", "TX");
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA | MAP_FLG_PREFER_GPS;
  }
 
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!super.parseMsg(subject, body, data)) return false;
    
    // They save something in the apt field that is better described as a cross street
    data.strCross = data.strApt;
    data.strApt = "";
    return true;
  }
  
  @Override
  public String getProgram() {
    return super.getProgram().replace(" APT ", " X ");
  }
  
  @Override
  public String adjustMapAddress(String addr) {
    
    // The address field is fine, what we are really trying to correct
    // is problems in the cross street field that get migrated into the
    // address when no street number is specified
    
    if (CROSS_SKIP_PTN.matcher(addr).find()) return "";
    
    addr = CROSS_REMOVE_PTN.matcher(addr).replaceAll("");
    return addr.trim();
  }
  private static final Pattern CROSS_SKIP_PTN = Pattern.compile("^\\d+[a-z]{2} house\\b|^house before\\b|^house after\\b|^entrance to\\b|^accross\\b|\\bbehind\\b|\\bnext to\\b\\bon side\\b", Pattern.CASE_INSENSITIVE);
  private static final Pattern CROSS_REMOVE_PTN = Pattern.compile("\\b(?:north|south|east|west|area) of\\b", Pattern.CASE_INSENSITIVE);
}
