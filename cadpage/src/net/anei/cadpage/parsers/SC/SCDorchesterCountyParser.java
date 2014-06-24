package net.anei.cadpage.parsers.SC;

import net.anei.cadpage.parsers.dispatch.DispatchA38Parser;



public class SCDorchesterCountyParser extends DispatchA38Parser {
  
  public SCDorchesterCountyParser() {
    super("DORCHESTER COUNTY", "SC");
  }
  
  @Override
  public String getFilter() {
    return "dispatch@dorchestercounty.net";
  }
  
}
