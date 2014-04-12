package net.anei.cadpage.parsers.ID;

import java.util.Properties;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA31Parser;


public class IDGoodingCountyParser extends DispatchA31Parser {
  
  public IDGoodingCountyParser() {
    super("SIRCOMM", CITY_CODES, "GOODING COUNTY", "ID");
  }
  
  @Override
  public String getFilter() {
    return "PagingService@sircomm.com";
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!super.parseMsg(subject, body, data)) return false;
    if (data.strCity.equals("KMB AREA")) data.strCity = "KIMBERLY";
    return true;
  }

  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "WND AREA", "WENDELL"
  });
}
