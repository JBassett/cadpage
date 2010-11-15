package net.anei.cadpage.parsers;

import net.anei.cadpage.SmsMsgInfo.Data;

/**
 * This class parses messages from some as yet unidentified CAD software vendor
 **/

/*
Andover Fire, Anoka county, Minnesota (class III)
Contact: Matt K <mattkohout@gmail.com>
Sender: cad.cad@co.anoka.mn.us
INC: field is an incident number
CAD MSG: *D A3   57       HANSON BLVD NW / ANDOVER BLVD NW  NE CORNER..SMOKE/CHARRED GRASS...SM FLAMES INC:10019729   [42]
CAD MSG: *D A3   39F      17844 XEON ST NW  FIRE ALARM... SMOKE OR HEAT UPSTAIRS OR IN GARAGE... SMALL DOG ONSITE INC:10019638  
CAD MSG: *D A3   45F      14950 HANSON BLVD NW @ANDOVER ELEMENTARY  PLANNED FIRE DRILL AT 1330 ... WILL CALL BACK WHEN COMPLETE INC:10019627   
CAD MSG: *D A3   57       A FIRE DEPT 3 @15929 CROSSTOWN BLVD NW  ** ILLEGAL BURN * IN MILLER WOODS THE NEW DEVELOPMENT INC:10019583   
CAD MSG: *D A3   32R      YMCA @15200 HANSON BLVD NW  ** SKATEBOARD PARK * 7 YO FE * FELL FRM A ACTIVITY BARS * DID LOOSE CONC IS CONC NOW * DIFF BREATHING AMB   
CAD MSG: *D A1   59       RR TRACKS / BUNKER LAKE BLVD NW  PD ACCIDENT/BLOCKING/NEED SGT TO RESPOND ALSO / FIRE RTN FOR WASH DOWN INC:10019310  
CAD MSG: *D A2   56       CO7 NW / CO58 NW  STRONG GAS ODOR IN THE AREA INC:10019278  
CAD MSG: *D A1   51       14015 DRAKE ST NW  POSS FIRE IN THE WALL...LOTS OF SMOKE FROM THE BASEMENT...EVERYONE ISOUT INC:10019127
CAD MSG: *D A1   32R      13645 HIDDEN CREEK DR NW  63 YO DIAB HUSB W/LOW BLOOD SUGARS...41 ..... AMB ORD / TRANSF INC:10022305
*/

public class MNAnokaCountyParser extends SmartAddressParser {
  
  private static final String DEF_STATE = "MN";
  private static final String DEF_CITY = "ANOKA COUNTY";
  
  public MNAnokaCountyParser() {
    super(DEF_STATE);
  }

  @Override
  public boolean isPageMsg(String body) {
    return body.startsWith("CAD MSG: ");
  }

  @Override
  protected void parse(String body, Data data) {
    data.defState = DEF_STATE;
    data.defCity = DEF_CITY;
    
    // Extract primary call description
    if (body.length() < 26) return;
    data.strCall = body.substring(9, 26).trim();
    body = body.substring(26);
    
    // Extract call ID if there is one
    int pt = body.lastIndexOf(" INC:");
    if (pt >= 0) {
      int pt2 = body.indexOf(' ', pt+5);
      if (pt2 < 0) pt2 = body.length();
      data.strCallId = body.substring(pt+5, pt2);
      body = body.substring(0, pt).trim();
    }
    
    // Normally a double blank separate the place & address line from the supp information
    
    pt = body.indexOf("  ");
    if (pt >= 0) {
      String addressLine = body.substring(0, pt);
      data.strSupp = body.substring(pt+2).trim();
      
      // An @ separates the place name from address,
      // But we cannot assume which one comes first. we have to check both
      // sides to see which makes a better address.
      
      // Which one we pick has to be parsed a second time to pick up some
      // of the subtle changes the parser makes
      pt = addressLine.indexOf('@');
      if (pt < 0) {
        parseAddress(StartType.START_ADDR, addressLine, data);
      } else {
        String part1 = addressLine.substring(0, pt).trim();
        String part2 = addressLine.substring(pt+1).trim();
        if (checkAddress(part1) > checkAddress(part2)) {
          parseAddress(StartType.START_ADDR, part1, data);
          data.strPlace = part2;
        } else {
          data.strPlace = part1;
          parseAddress(StartType.START_ADDR, part2, data);
        }
      }
    }
    
    // If we don't find the expected double blank, parser it as a name/address
    // field followed by supplemental info
    
    else {
      body = body.replaceAll("@", "");
      parseAddress(StartType.START_PLACE, body, data);
      data.strSupp = getLeft();
    }
  }
}
