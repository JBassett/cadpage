package net.anei.cadpage.parsers.ZUK;

import net.anei.cadpage.parsers.general.GeneralParser;

public class ZUKGeneralParser extends GeneralParser {

  /**
   * Default constructor
   */
  public ZUKGeneralParser() {
    super(CountryCode.US);
  }
  
  @Override
  public String getLocName() {
    return "Generic Location";
  }
}
