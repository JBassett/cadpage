package net.anei.cadpage.parsers.SC;

import net.anei.cadpage.parsers.CodeSet;
import net.anei.cadpage.parsers.dispatch.DispatchA48Parser;

/**
 * Kershaw County, SC
 */
public class SCKershawCountyParser extends DispatchA48Parser {
  
  public SCKershawCountyParser() {
    super(CITY_LIST, "KERSHAW COUNTY", "SC", FieldType.NAME);
    setupCallList(CALL_LIST);
  }
  
  @Override
  public String getFilter() {
    return "kershawcounty911@kershaw.sc.gov";
  }
  
  private static final CodeSet CALL_LIST = new CodeSet(
      "AMBULANCE NEEDED",
      "FIRE ALARM SOUNDING",
      "MOTOR VEHICLE ACCIDENT",
      "STRUCTURE FIRE",
      "VEHICLE ACCIDENT",
      "WOODS, BRUSH"
  );
  
  private static final String[] CITY_LIST = new String[]{
    "ANTIOCH",
    "BETHUNE",
    "BOYKIN",
    "CAMDEN",
    "CASSATT",
    "ELGIN",
    "LIBERTY HILL",
    "LUGOFF",
    "WESTVILLE",
    
    // Chester County
    "JEFFERSON",
    
    // Lancaster County
    "KERSHAW"
  };
}
