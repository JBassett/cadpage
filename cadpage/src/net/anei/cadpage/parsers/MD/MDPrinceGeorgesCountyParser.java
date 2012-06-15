package net.anei.cadpage.parsers.MD;

import net.anei.cadpage.parsers.GroupBestParser;

public class MDPrinceGeorgesCountyParser extends GroupBestParser {
  
  public MDPrinceGeorgesCountyParser() {
    super(new MDPrinceGeorgesCountyFireBizParser(), 
          new MDPrinceGeorgesCountyCParser(),
          new MDPrinceGeorgesCountyDParser(),
          new MDPrinceGeorgesCountyEParser());
  }
}
