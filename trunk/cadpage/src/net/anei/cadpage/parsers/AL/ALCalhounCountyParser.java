package net.anei.cadpage.parsers.AL;

import net.anei.cadpage.parsers.dispatch.DispatchA12Parser;

/**
 * Calhoun County AL
 */
public class ALCalhounCountyParser extends DispatchA12Parser {
  
  
  public ALCalhounCountyParser() {
    super("CALHOUN COUNTY", "AL");
  }
  
  @Override
  public String getFilter() {
    return "cad@911.calhouncountyal.gov";
  }

}
