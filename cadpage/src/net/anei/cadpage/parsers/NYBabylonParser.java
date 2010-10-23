package net.anei.cadpage.parsers;

import java.util.Properties;

import net.anei.cadpage.Log;
import net.anei.cadpage.SmsMsgInfo.Data;
/*
*** 13 - Structure Fire *** 147 CHERUBINA LN CS: LEADER AVE  / SKIDMORE RD TOA: 22:37 09/22/10 OIL BURNER NORTH BABYLON FC 2010-002398 HY: 8' 11
*** 13 - Structure Fire *** 514 MOUNT PL CS: ESSEX ST  / LAKEWAY DR TOA: 19:55 09/22/10 NORTH BABYLON FC 2010-002393 HY: 12' 533 MOUNT PL @ ESSE
*** 2nd/16 - Rescue *** 733 HIGHRIDGE RD CS: OCONNER RD  / NARLAND LN TOA: 20:46 09/22/10 a/m pysch emer NORTH BABYLON FC 2010-002395
*** 23 - Miscellaneous Fire *** SR CITZ APTS (5 BLDGS) COMPLEX 15 WEEKS RD CS: DEER PARK AVE  / MULHOLLAND DR TOA: 11:07 09/23/10 INVEST NORTH B

*** 24/16 - Mutual Aid *** 27 COOLIDGE AVE CS: RT 110  / COOLIDGE AVE TOA: 06:54 10/20/10 40 Y/F ABDOMINAL PAINS  AMITYVILLE FD 2010-000228
*** 24/16 - Mutual Aid *** 22 ELGIN RD CS: DE  / COOLIDGE AVE TOA: 11:38 10/18/10 E/F UNABLE TO MOVE  **FULL CREW NEEDED** AMITYVILLE FD 2010-00
*** 24/MV - Mutual Aid ***  MONTAUK HWY CS: WILSON AVE TOA: 11:43 10/20/10 AMITYVILLE FD 2010-000229
*** 24/13 - Mutual Aid *** 42 NATHALIE AVE CS: CAMPBELL ST  / MOORE ST TOA: 12:04 10/04/10 POSS OCCUPANTS WITH IN  AMITYVILLE FD 2010-000226
*/

public class NYBabylonParser extends SmsMsgParser {
  
  private static String[] keywords = new String[]{"ADDR", "CS", "TOA", "FC", "HY"};

  @Override
  public boolean isPageMsg(String body) {
    return body.startsWith("***");
  }
  
  @Override
  protected void parse(String body, Data data) {
    Log.v("DecodeBabylonPage: Message Body of:" + body);
    data.defState="NY";
    
    int pt = body.indexOf("***");
    if (pt < 0) return;
    int pta = body.indexOf("***",pt+3); 
    if (pta < 0) return;
    data.strCall = body.substring(pt+3, pta).trim();
    body = body.substring(pta+3).trim();
    
    body = "ADDR:" + body.replace(" FC ", " FC:");
    Properties props = parseMessage(body, keywords);
    parseAddress(props.getProperty("ADDR", ""), data);
    data.strCross = props.getProperty("CS", "");
    data.strCallId = props.getProperty("FC", "");
  }
}
