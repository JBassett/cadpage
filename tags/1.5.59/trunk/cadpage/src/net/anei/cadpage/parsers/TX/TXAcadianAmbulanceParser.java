package net.anei.cadpage.parsers.TX;

import net.anei.cadpage.parsers.general.XXAcadianAmbulanceParser;

/*
Acadian Ambulance, TX

*/

public class TXAcadianAmbulanceParser extends XXAcadianAmbulanceParser {

  public TXAcadianAmbulanceParser() {
    super("TX");
  }
  
  @Override
  public String getLocName() {
    return "Acadian Ambulance, TX";
  }
}
