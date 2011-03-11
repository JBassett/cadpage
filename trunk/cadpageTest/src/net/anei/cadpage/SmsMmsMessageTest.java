package net.anei.cadpage;

import static org.junit.Assert.*;

import org.junit.Test;

public class SmsMmsMessageTest {
  
  @Test
  public void testParseInfo() {
    
    doParseTest("NCBuncombeCounty1",
        "S: M:CAD:25 REYNOLDS MOUNTAIN BLVD;B20;RM 126-A;EMERALD RIDGE;EMERALD RIDGE REHAB AND CARE C;ALLERGIES / REACTIONS;WEAVERVILLE RD",
        "ken@cadpage.org",
        "",
        "CAD:25 REYNOLDS MOUNTAIN BLVD;B20;RM 126-A;EMERALD RIDGE;EMERALD RIDGE REHAB AND CARE C;ALLERGIES / REACTIONS;WEAVERVILLE RD");
    
    doParseTest("NCBuncombeCounty2",
        "S:FIRE ALERT M:CAD:25 REYNOLDS MOUNTAIN BLVD;B20;RM 126-A;EMERALD RIDGE;EMERALD RIDGE REHAB AND CARE C;ALLERGIES / REACTIONS;WEAVERVILLE RD",
        "ken@cadpage.org",
        "FIRE ALERT",
        "CAD:25 REYNOLDS MOUNTAIN BLVD;B20;RM 126-A;EMERALD RIDGE;EMERALD RIDGE REHAB AND CARE C;ALLERGIES / REACTIONS;WEAVERVILLE RD");
    
    doParseTest("KYDaviessCounty1",
        "1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY\n(Con't) 2 of 2\nMap: Grids:, Cad: 2011-0000013291 (End)",
        "911-CENTER@911Center@central",
        "",
        "911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY Map: Grids:, Cad: 2011-0000013291");
    
    doParseTest("KYDaviessCounty1",
        "1 of 2\nFRM:911-CENTER@911Center@central\nMSG:911-CENTER:FF >WILDLAND FIRE 12957 RED HILL-MAXWELL RD XS: E HARMONS FERRY RD UTICA PRESSON, DAVID\n(Con't) 2 of 2\nMap: Grids:, Cad: 2011-0000012778(End)",
        "911-CENTER@911Center@central",
        "",
        "911-CENTER:FF >WILDLAND FIRE 12957 RED HILL-MAXWELL RD XS: E HARMONS FERRY RD UTICA PRESSON, DAVID Map: Grids:, Cad: 2011-0000012778");
       
    
    doParseTest("MNAnokaCOunty",
        "cad.cad@co.Anoka.mn.us / / CAD MSG: *D S1 39F 7783 233 LN NE GEN SMOKE/FIRE ALRM.. INC:11001880",
        "cad.cad@co.Anoka.mn.us",
        "",
        "CAD MSG: *D S1 39F 7783 233 LN NE GEN SMOKE/FIRE ALRM.. INC:11001880");
    
    doParseTest("MILivingstonCounty",
        "Pagecopy-Fr:CAD@livingstoncounty.livco\nCAD:FYI: ;OVDOSE;4676 KENMORE DR;[Medical Priority Info] RESPONSE: P1 STA 1",
        "CAD@livingstoncounty.livco",
        "",
        "CAD:FYI: ;OVDOSE;4676 KENMORE DR;[Medical Priority Info] RESPONSE: P1 STA 1");
    
    doParseTest("MILivingstonCounty2",
        "firediver11+caf_=5176671194=vtext.com@gmail.comPagecopy-Fr:CAD@livingstoncounty.livco\nCAD:FYI: ;SEIZUR;131 STRATFORD LN;BELMONT LN;[Medical Priority Info]",
        "CAD@livingstoncounty.livco",
        "",
        "CAD:FYI: ;SEIZUR;131 STRATFORD LN;BELMONT LN;[Medical Priority Info]");
    
    doParseTest("MILivingstonCounty3",
        "Pagecopy-Fr:CAD@livingstoncounty.livco CAD:Update: ;FALL;3031 WM36;[EMS] HAS BEEN VOMITTING - DIABETIC [02/14/11 09:55:08 RLADOUCEUR] [Me",
        "CAD@livingstoncounty.livco",
        "",
        "CAD:Update: ;FALL;3031 WM36;[EMS] HAS BEEN VOMITTING - DIABETIC [02/14/11 09:55:08 RLADOUCEUR] [Me");

    
    doParseTest("MOPulaskiCounty",
        "1 of 3\n" +
        "FRM:911dispatch@embarqmail.com\n" + 
        "SUBJ:DO NOT REPLY\n" +
        "MSG:STRUCTURE FIRE RESIDENTIAL  11280 BATTLE LN  PULASKI COUNTY CrossStreets: Highway\n" +
        "(Con't) 2 of 3\n" +
        "17 0.42 mi W CFD1 DFD2 M23 TCFD1 DFD2 1553 1552 1562 1560 1351 1501 PCSD1 RESCUE MILLER T1 WRFD1 SRFD1 1902 1361 1952 T2 1650 HFD1 Call\n" +
        "(Con't) 3 of 3\n" +
        "Received Time: 12/6/2010 20:46:54 Dispatch: 12/6/2010 21:50:29(End)",
        "911dispatch@embarqmail.com",
        "DO NOT REPLY",
        "STRUCTURE FIRE RESIDENTIAL  11280 BATTLE LN  PULASKI COUNTY CrossStreets: Highway 17 0.42 mi W CFD1 DFD2 M23 TCFD1 DFD2 1553 1552 1562 1560 1351 1501 PCSD1 RESCUE MILLER T1 WRFD1 SRFD1 1902 1361 1952 T2 1650 HFD1 Call Received Time: 12/6/2010 20:46:54 Dispatch: 12/6/2010 21:50:29");
    
    doParseTest("T0",
        "JUST A PLAIN TEXT MESSAGE",
        "ken@cadpage.org",
        "",
        "JUST A PLAIN TEXT MESSAGE");
    
    doParseTest("T1", 
        "1 of 3\n"+
        "FRM:CAD@livingstoncounty.livco\n"+
        "MSG:CAD:FYI: ;CITAF;5579 E GRAND RIVER;WILDWOOD DR;Event spawned from CITIZEN ASSIST LAW. [12/10/10\n" +
        "(Con't) 2 of 3\n" +
        "20:08:59 SPHILLIPS] CALLER LIVES NEXT DOOR TO THE ADDRESS OF THE WATER MAINBREAK [12/10/10 20:04:40 HROSSNER] CALLER ADV OF A WATER MAIN\n" +
        "(Con 3 of 3\n" +
        "BREAK(End)",
        "CAD@livingstoncounty.livco",
        "",
        "CAD:FYI: ;CITAF;5579 E GRAND RIVER;WILDWOOD DR;Event spawned from CITIZEN ASSIST LAW. [12/10/10 20:08:59 SPHILLIPS] CALLER LIVES NEXT DOOR TO THE ADDRESS OF THE WATER MAINBREAK [12/10/10 20:04:40 HROSSNER] CALLER ADV OF A WATER MAIN BREAK");
    
    doParseTest("T2",
        "1 of 3\n"+
        "FRM:911dispatch@embarqmail.com\n"+ 
        "SUBJ:DO NOT REPLY\n"+
        "MSG:STRUCTURE FIRE RESIDENTIAL  11280 BATTLE LN  PULASKI COUNTY CrossStreets: Highway\n"+
        "(Con't) 2 of 3\n"+
        "17 0.42 mi W CFD1 DFD2 M23 TCFD1 DFD2 1553 1552 1562 1560 1351 1501 PCSD1 RESCUE MILLER T1 WRFD1 SRFD1 1902 1361 1952 T2 1650 HFD1 Call\n"+
        "(Con't) 3 of 3\n"+
        "Received Time: 12/6/2010 20:46:54 Dispatch: 12/6/2010 21:50:29(End)",
        "911dispatch@embarqmail.com",
        "DO NOT REPLY",
        "STRUCTURE FIRE RESIDENTIAL  11280 BATTLE LN  PULASKI COUNTY CrossStreets: Highway 17 0.42 mi W CFD1 DFD2 M23 TCFD1 DFD2 1553 1552 1562 1560 1351 1501 PCSD1 RESCUE MILLER T1 WRFD1 SRFD1 1902 1361 1952 T2 1650 HFD1 Call Received Time: 12/6/2010 20:46:54 Dispatch: 12/6/2010 21:50:29");
    
    doParseTest("MDPrinceGeorgesCounty",
        "FRM:e@fireblitz.com <Body%3AFRM%3Ae@fireblitz.com>\n" +
        "MSG:48: TOWNHOUSE FIRE\n" +
        "E818 BO802\n" +
        "9903 BREEZY KNOLL CT [DEAD END & GREEN HAVEN RD]\n" +
        "12/23 23:32\n" +
        "http://fireblitz.com/18/8.shtm",
        "e@fireblitz.com <Body%3AFRM%3Ae@fireblitz.com>",
        "",
        "48: TOWNHOUSE FIRE\n" +
        "E818 BO802\n" +
        "9903 BREEZY KNOLL CT [DEAD END & GREEN HAVEN RD]\n" +
        "12/23 23:32\n" +
        "http://fireblitz.com/18/8.shtm");
    
    doParseTest("MDAlleganyCounty",
        "FRM:LogiSYSCAD\nSUBJ:CAD Page for CFS 100110-96\nMSG:UNCONCIOUS/UNRESPONSIVE 91 S BROADWAY\nUnits: A53 CO16",
        "LogiSYSCAD",
        "CAD Page for CFS 100110-96",
        "UNCONCIOUS/UNRESPONSIVE 91 S BROADWAY\nUnits: A53 CO16");
    
    doParseTest("NYOneidaCounty",
        "FRM:dispatch@oc911.org\nMSG:i>¿WEVF:2010:0181Dispatched10D02-DIFFICULTY SPEAKING BETWEEN BREATHS9071 DOPP HILL RD, WESTERN (ROUTE 46/;)",
        "dispatch@oc911.org",
        "",
        "i>¿WEVF:2010:0181Dispatched10D02-DIFFICULTY SPEAKING BETWEEN BREATHS9071 DOPP HILL RD, WESTERN (ROUTE 46/;)");
    
    doParseTest("TxCyCreekComCenter",
        "CommCenter@ccems.com <Body%3ACommCenter@ccems.com> [] TAP OUT (SAL)",
        "CommCenter@ccems.com <Body%3ACommCenter@ccems.com>",
        "",
        "TAP OUT (SAL)");
    
    doParseTest("MDHarford",
        "Subject:HCCAD\n[!] EOC:F03 WIRES >WIRES/POLE SHAWNEE DR&WALTERS MILL RD XS: WALTERS MILL RD FOREST HILL NOT ENTERED Cad: 2010-000019169",
        "ken@cadpage.org",
        "HCCAD|!",
        "EOC:F03 WIRES >WIRES/POLE SHAWNEE DR&WALTERS MILL RD XS: WALTERS MILL RD FOREST HILL NOT ENTERED Cad: 2010-000019169");
    
    doParseTest("shortMsg",
        "FRM:CommCenter@ccems.com\nMSG:BAD",
        "CommCenter@ccems.com",
        "",
        "BAD");
    
    doParseTest("(sub)msg",
        "(THE SUBJECT(ID 3342)) HELLO DOLLY",
        "ken@cadpage.org",
        "THE SUBJECT(ID 3342)",
        "HELLO DOLLY");
    
    doParseTest("[sub]msg",
        "[THE SUBJECT[ID 3342]] HELLO DOLLY",
        "ken@cadpage.org",
        "THE SUBJECT[ID 3342]",
        "HELLO DOLLY");
    
    doParseTest("(s1)[s2]",
        "(SUBJECT ONE) [ SUBJECT TWO ] HELLO BABE",
        "ken@cadpage.org",
        "SUBJECT ONE|SUBJECT TWO",
        "HELLO BABE");
    
    doParseTest("VAWaynesboro",
        "Dispatch@ci.waynesboro.va.us <Body%3ADispatch@ci.waynesboro.va.us> Msg: Dispatch:2ND CALL 1001 HOPEMAN PKWY, ZAP12 INJURIES FROM PREVIOUS MVA",
        "Dispatch@ci.waynesboro.va.us <Body%3ADispatch@ci.waynesboro.va.us>",
        "",
        "Dispatch:2ND CALL 1001 HOPEMAN PKWY, ZAP12 INJURIES FROM PREVIOUS MVA");
    
    doParseTest("PABerksCounty",
        "FRM:\nSUBJ:1/2\nMSG:CAD MSG: *D TREESDWN FORGEDALE RD / CLAY VALLEY RD 0087 PSP IS REQ FIRE\nCO FOR TREE REMOVAL FROM ROADWAY // PSP NOT ON LOC BC",
        "",
        "1/2",
        "CAD MSG: *D TREESDWN FORGEDALE RD / CLAY VALLEY RD 0087 PSP IS REQ FIRE\nCO FOR TREE REMOVAL FROM ROADWAY // PSP NOT ON LOC BC");
  }
  
  @Test
  public void testParseBreak() {
    
    doParseTest("HDR1", "0001/0003 THIS IS A TEST",
                "ken@cadpage.org", "", "THIS IS A TEST", true);
    
    doParseTest("HDR1-done", "0003/0003 THIS IS ANOTHER TEST",
                "ken@cadpage.org", "", "THIS IS ANOTHER TEST", false);
    
    doParseTest("HDR2", "1of3:THIS IS A TEST",
                "ken@cadpage.org", "", "THIS IS A TEST", true);
    
    doParseTest("HDR2-done", "3of3:THIS IS ANOTHER TEST",
                "ken@cadpage.org", "", "THIS IS ANOTHER TEST", false);
    
    doParseTest("HDR3", "(1/2) I LOVE MY MOTHER",
                "ken@cadpage.org", "", "I LOVE MY MOTHER", true);
    
    doParseTest("HDR3-done", "(2/2) I LOVE MY MOTHER",
                "ken@cadpage.org", "", "I LOVE MY MOTHER", false);
    
    doParseTest("TRL1", "WHERE IS BABY[1 of 2]",
               "ken@cadpage.org", "", "WHERE IS BABY", true);
    
    doParseTest("TRL1-done", "WHERE IS BABY[2 of 2]",
               "ken@cadpage.org", "", "WHERE IS BABY", false);
    
    doParseTest("TRL2", "WHERE IS BABY :1of2",
               "ken@cadpage.org", "", "WHERE IS BABY", true);
    
    doParseTest("TRL2-done", "WHERE IS BABY :2of2",
               "ken@cadpage.org", "", "WHERE IS BABY", false);
  }
  
  private void doParseTest(String title, String body, String expFrom, String expSubject, String expBody) {
    doParseTest(title, body, expFrom, expSubject, expBody, false);
  }
  
  private void doParseTest(String title, String body, String expFrom, String expSubject, String expBody, boolean expMore) {
    SmsMmsMessage msg = new SmsMmsMessage("ken@cadpage.org", body, 0L, 0);
    assertEquals(title + ":FROM", expFrom, msg.getAddress());
    assertEquals(title + ":SUBJ", expSubject, msg.getSubject());
    assertEquals(title + ":BODY", expBody, msg.getMessageBody());
    assertEquals(title + ":MORE", expMore, msg.isExpectMore());
  }
  
  @Test
  public void testEscape() {
    assertEquals("BIG RED ONE", SmsMmsMessage.escape("BIG RED ONE"));
    assertEquals("BIG RED ONE\\n\n\\tOVER THERE\\n\nSIX\\6sSPACES\\3s3", 
                 SmsMmsMessage.escape("BIG RED ONE\n\tOVER THERE\nSIX      SPACES   3"));
  }
}
