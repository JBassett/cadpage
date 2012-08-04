package net.anei.cadpage.parsers.CO;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;

/*
Garfield County, CO
Contact: Active911
Agency name: Burning Mountains Fire Protection District Location: New Castle, CO 
Sender: GCECA@call3n.com,@everbridge.net

(CAD Page) Accident Location  97 E I70SILT 2012 00000387     
(CAD Page) FSmoke Check Location  15000 CORD 313 Z2 2012 00000388     
(CAD Page) Code Location  CHAIR BAR RD CORD 331 Z2 2012 00000389     
(CAD Page) FBrush Location  94.5 HWY 6Z2 2012 00000554     
(CAD Page) EBleeding Non traumatic Location  667 N 16TH ST SILT 2012 00000391     
(CAD Page) Code Location  ELK CREEK CAMPGROUND Z2 2012 00000393     
(CAD Page) Code Location  1360 EM AVE SILT 2012 00000394     
(CAD Page) Code Location  ELK CREEK CAMPGROUND Z2 2012 00000393     
(CAD Page) EChest Pain Location  124 E  MAIN ST NC 2012 00000395     
(CAD Page) Accident Location  12545 CORD 245 Z2 2012 00000396     
(CAD Page) FOdor Check Location  794 CASTLE VALLEY BLVD NC APT G 2012 00003116     
(CAD Page) Code Location  CORD 229 USHY 6 Z2 2012 00000398     
(CAD Page) Accident Location  105 E I70NC 2012 00000399     
(CAD Page) Accident Location  100 E I70Z2 2012 00015425     
(CAD Page) ETrauma with Injury Location  1601 ORCHARD AVE SILT 2012 00000401     
(CAD Page) EOverdose Poisoning Location  200 N 7TH ST blk NC 2012 00000402     
(CAD Page) FSmoke Check Location  7696 CORD 312 Z2 2012 00000403     
(CAD Page) FBrush Location  7299 CORD 233 Z2 2012 00000404     
(CAD Page) FAlarm Location  56 EL DIENTE WAY NC 2012 00000405     
(CAD Page) FAlarm Location  560 RIVER VIEW DR 7 NC 2012 00000406     
(CAD Page) FSmoke Check Location  4200 CORD 243 Z2 2012 00000408     
(CAD Page) Code Location  3600 CORD 237 Z2 2012 00000410     
(CAD Page) FBrush Location  97.5 E I70SILT 2012 00000411     
(CAD Page) EFalls Location  287 LUPINE DR NC 2012 00000412     
(CAD Page) EBack Pain Location  Silt Senior Housing701 HOME AVE 108 SILT 2012 00000413     
(CAD Page) ETrauma with Injury Location  247 E  CAPITAL CT NC 2012 00000414     
(CAD Page) FVehicle Fire Location  105 E I70NC 2012 00000415     
(CAD Page) FRiver Rescue Location  SOUTH CANYON BRIDGE Z1 2012 00000806     
(CAD Page) EFalls Location  APPLE TREE MHP5033 CORD 335 Z2 2012 00000421     
(CAD Page) Code Location  221 N 7TH ST NC 2012 00000422     
(CAD Page) Accident Location  98.5 W I70Z2 2012 00016190     
(CAD Page) EAbdominal Location  122 E  MAIN ST NC 2012 00000424     
(CAD Page) ESeizures Location  5033 CORD 335 28 Z2 2012 00000427     
(CAD Page) EFalls Location  1220 CHARLIN AVE SILT 2012 00002784     
(CAD Page) FSmoke Check Location  CORD 250 CORD 237 Z2 2012 00000431     
(CAD Page) FSmoke Check Location  S W OF JOLLEY MESA Z2 2012 00000433     
(CAD Page) FBrush Location  FOREST SERVICE RD 603 Z2 2012 00000434     
(CAD Page) ESick Unknown Location  FOREST RD 603 Z2 2012 00000435     
(CAD Page) EOverdose Poisoning Location  120 S  E AVE NC 2012 00000436     
(CAD Page) FGas Leak Location  386 W MAIN ST 7 NC 2012 00000437     
(CAD Page) EGynecology Childbirth Location  422 W  RICHARDS AVE SILT 2012 00000438     
(CAD Page) FSmoke Check Location  97 E I70SILT 2012 00000439     
(CAD Page) EChest Pain Location  144 PEAR CT Z2 2012 00000440     

 */


public class COGarfieldCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER = Pattern.compile("(.*) +Location +(.*?) +(\\d{4} \\d{8})");
  private static final Pattern APT_PTN = Pattern.compile(" APT ([^ ]+)$");
  private static final Pattern BLK_PTN = Pattern.compile("\\bblk\\b");
  private static final Pattern PLACE_PTN = Pattern.compile("^.*[a-z]");
  
  public COGarfieldCountyParser() {
    super("GARFIELD COUNTY", "CO");
  }
  
  @Override
  public String getFilter() {
    return "GCECA@call3n.com,@everbridge.net";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    if (!subject.equals("CAD Page")) return false;
    
    Matcher match = MASTER.matcher(body);
    if (!match.matches()) return false;
    data.strCall = match.group(1);
    String sAddr = match.group(2).trim();
    data.strCallId = match.group(3);
    
    // Remove apt from address before checking for city/map zone
    match = APT_PTN.matcher(sAddr);
    if (match.find()) {
      data.strApt = match.group(1);
      sAddr = sAddr.substring(0,match.start()).trim();
    }
    
    // The address field may contain a leading place name, and usually contains a
    // trailing city code.  Unfortunately dispatch is pretty sloppy about separating
    // these fields with blanks, so we won't count on blank separators.
    for (int len = 2; len<=4; len++) {
      int pt = sAddr.length() - len;
      if (pt >= 0) {
        String city = CITY_CODES.getProperty(sAddr.substring(pt));
        if (city != null) {
          if (city.startsWith("Z")) data.strMap = city;
          else data.strCity = city;
          sAddr = sAddr.substring(0,pt).trim();
          break;
        }
      }
    }
    
    // Place names are (usually) cammel case while address is always upper case
    // So we split them at the last lower case character
    sAddr = BLK_PTN.matcher(sAddr).replaceAll("BLK");
    match = PLACE_PTN.matcher(sAddr);
    if (match.find()) {
      data.strPlace = match.group();
      sAddr = sAddr.substring(match.end()).trim();
    }
    
    parseAddress(StartType.START_ADDR, FLAG_ANCHOR_END | FLAG_IMPLIED_INTERSECT, sAddr, data);
    return true;
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "GS",   "GLENWOOD SPRINGS",
      "RIF",  "RIFLE",
      "SIL",  "SILT",
      "SILT", "SILT",
      "NC",   "NEW CASTLE",
      "Z1",   "Z1",
      "Z2",   "Z2",
      "Z3",   "Z3"
  });
}
