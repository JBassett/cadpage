package net.anei.cadpage.parsers.TX;

import java.util.Properties;

import net.anei.cadpage.parsers.GroupBestParser;

/**
 * Rusk County, TX
 */

public class TXRuskCountyParser extends GroupBestParser {
  
  public TXRuskCountyParser() {
    super(new TXRuskCountyAParser());
  }
 
}
