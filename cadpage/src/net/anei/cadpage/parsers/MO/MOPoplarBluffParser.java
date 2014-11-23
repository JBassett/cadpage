package net.anei.cadpage.parsers.MO;

import net.anei.cadpage.parsers.dispatch.DispatchA33Parser;


public class MOPoplarBluffParser extends DispatchA33Parser {
    
  public MOPoplarBluffParser() {
    super("POPLAR BLUFF", "MO", "Closed");
  }
  
  @Override
  public String getFilter() {
    return "INFOR@PBPOLICE.ORG,INFO@PBPOLICE.ORG";
  }

}
