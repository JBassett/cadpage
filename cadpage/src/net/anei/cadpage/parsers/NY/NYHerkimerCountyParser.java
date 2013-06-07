package net.anei.cadpage.parsers.NY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchB3Parser;



public class NYHerkimerCountyParser extends DispatchB3Parser {

  private static final Pattern CITY_SUFFIX = Pattern.compile("^VILLAGE\\b", Pattern.CASE_INSENSITIVE);
  
  public NYHerkimerCountyParser() {
    super(CITY_LIST, "HERKIMER COUNTY", "NY");
  }
  
  @Override
  public String getFilter() {
    return "HC911@herkimercounty.org";
  }
  
  @Override
  protected boolean isPageMsg(String body) {
    return true;
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!super.isPageMsg(body) && !body.contains("Grids:,, NY")) return false; 
    body = body.replaceAll("\n", " ").replaceAll(" Grids:,, NY ", " ");
    if (body.endsWith(" MAP:")) body = body.substring(0,body.length()-5).trim();
    if (!super.parseMsg(subject, body, data)) return false;
    
    // See if city suffix has bled over into name
    Matcher match = CITY_SUFFIX.matcher(data.strName);
    if (match.find()) data.strName = data.strName.substring(match.end()).trim();
    return true;
  }
  
  private static final String[] CITY_LIST = new String[]{  
    "DEERFIELD", 
    "LITTLE FALLS CITY",
    "FAIRFIELD",
    "MIDDLEVILLE", 
    "NORWAY", 
    "NEWPORT", 
    "OHIO", 
    "POLAND", 
    "RUSSIA" 
  }; 
}
