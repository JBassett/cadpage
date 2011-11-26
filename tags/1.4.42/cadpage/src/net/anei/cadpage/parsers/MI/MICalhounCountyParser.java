package net.anei.cadpage.parsers.MI;

import java.util.Properties;
import java.util.regex.Pattern;

import net.anei.cadpage.SmsMsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchPrintrakParser;

/*
Calhoun County, MI
Contact: Tim Smith <timsmith1923@gmail.com>
Sender: postmaster@calhouncountymi.gov
System PrinTrak

PRI: 0 INC: F15111123000853 TYP: UNKN ROLLOVER ACC AD: 10921 BELLEVUE RD CTY: PT CN: GAW, LEWIS TIME: 07:30 151531 XST: 20199 11
PRI: 2 INC: F15111123000852 TYP: TREE/LIMB DOWN AD: CLEAR LAKE RD&WALKINSHAW RD CTY: PT CN: BILL BAMMER CMT1: TREE DOWN BLOCKING
PRI: 1 INC: F15111122000851 TYP: P1 MEDICAL APT: 19 AD: 1419 NE CAPITAL AVE CTY: PT LOC: PINES AT PENNFIELD CN: FEMALE CMT1: PIN
PRI: 2 INC: F15111121000849 TYP: CITIZEN ASSIST AD: 1427 NE CAPITAL AVE CTY: PT LOC: PINES AT PENNFIELD CN: TAKESH WORTHEM CMT1:
PRI: 1 INC: F15111121000850 TYP: P1 MEDICAL AD: 23650 M78 CTY: PT LOC: MELVIN WHITING RESD CN: LIFEALERT CMT1: --89YOM--HEART BE
PRI: 2 INC: F15111121000849 TYP: CITIZEN ASSIST AD: 1427 NE CAPITAL AVE CTY: PT LOC: PINES AT PENNFIELD CN: TAKESH WORTHEM CMT1:

*/

public class MICalhounCountyParser extends DispatchPrintrakParser {
  
  private static final Pattern M_ROUTE_PTN = Pattern.compile("\\bM *(\\d+)\\b"); 
  
  public MICalhounCountyParser() {
    super(CITY_TABLE, "CALHOUN COUNTY", "MI");
  }
  
  @Override
  public String getFilter() {
    return "postmaster@calhouncountymi.gov";
  }
  
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    if (!super.parseMsg(body, data)) return false;
    
    // Correct Mnn to MI nn
    data.strAddress = M_ROUTE_PTN.matcher(data.strAddress).replaceAll("MI $1");
    return true;
  }


  private static final Properties CITY_TABLE = buildCodeTable(new String[]{
      "PT", "PENNFIELD TWP"
  });
}
