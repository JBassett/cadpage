package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.dispatch.DispatchSouthernParser;


public class NCMadisonCountyParser extends DispatchSouthernParser {
  
  
  public NCMadisonCountyParser() {
    super(CITY_LIST, "MADISON COUNTY", "NC");
  }
  
  private static final String[] CITY_LIST = new String[]{
    "HOT SPRINGS",
    "MARS HILL",
    "MARSHALL",

    "MARSHALL TWP",
    "LAUREL TWP",
    "MARS HILL TWP",
    "BEECH GLENN TWP",
    "WALNUT TWP",
    "HOT SPRINGS TWP",
    "EBBS CHAPEL TWP",
    "SPRING CREEK TWP",
    "SANDY MUSH TWP",
    "GRAPEVINE TWP",
    "REVERE RICE COVE TWP"
  };

}
