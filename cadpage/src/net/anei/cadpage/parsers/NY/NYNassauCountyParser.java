package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.parsers.GroupBestParser;

/*
Nassau County, NY
Combined location parsers thereof
 */

public class NYNassauCountyParser extends GroupBestParser {

  public NYNassauCountyParser() {
    super(new NYNassauCountyAParser(), new NYNassauCountyBParser(), 
          new NYNassauCountyCParser(), new NYNassauCountyDParser(), 
          new NYNassauCountyFParser(), new NYNassauCountyGParser(),
          new NYNassauCountyHParser(), new NYNassauCountyIParser(),
          new NYNassauCountyJParser(), new NYNassauCountyKParser(),
          new NYNassauCountyMParser(),
          new NYNassauCountyRedAlertParser(), new NYNassauCountyFiretrackerParser(),
          new NYNassauCountyElmontParser(), new NYNassauCountyMassepequaParser(), 
          new NYNassauCountyBethpageParser());
  }
}


