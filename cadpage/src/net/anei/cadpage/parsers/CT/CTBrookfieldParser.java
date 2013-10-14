package net.anei.cadpage.parsers.CT;


import net.anei.cadpage.parsers.dispatch.DispatchA32Parser;

public class CTBrookfieldParser extends DispatchA32Parser {
  
  public CTBrookfieldParser() {
    super(CITY_LIST, "BROOKFIELD","CT");
  }
  
  @Override
  public String getFilter() {
    return "policedispatch@brookfieldct.gov";
  }
  
  private static final String[] CITY_LIST = new String[]{
    "BRIDGEWATER",
    "BROOKFIELD",
    "DANBURY",
    "MILFORD",
    "ROXBURY"
  };
}
