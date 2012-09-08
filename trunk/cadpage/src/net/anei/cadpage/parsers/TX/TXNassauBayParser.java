package net.anei.cadpage.parsers.TX;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchOSSIParser;

/* 
Nassau Bay (Harris County, TX?)
Contact: "Robert Hebert /City of Nassau Bay" <robert.hebert@nassaubay.com>
Sender: cad@ossicadpaging

39:CAD:FYI: ;NBFD;FIRE ALARM;18101 POINT LOOKOUT DR;NB;NB;01/12/2011  22:10:16;TRASHROOM 1 SMOKE DETECTOR [01/12/11 22:11:43 NHUGHES] fire alarms going
40:CAD:FYI: ;NBFD;STRUCTURE FIRE;1020-264 W NASA PKWY;WB;WBW;01/13/2011 11:46:25;smoke inside [01/13/11 11:45:40 TSWANSON]
41:CAD:FYI: ;NBFD;ACCIDENT MAJOR;NASA PKWY/ST JOHN BLVD;HO;NB;01/18/2011 12:53:59
CAD:FYI: ;NBFD;UNCONSCIOUS;1427 SAXONY LN;NB;NB;03/06/2011 14:30:40;female down in bathroom [03/06/11 14:30:28 DCOOLEY]
CAD:FYI: ;NBFD;ASSIST BY FIRE;3000 E NASA PKWY;SB;03/06/2011 15:38:43;boat [03/06/11 15:35:58 DCOOLEY]
:CAD:FYI: ;NBFD;VEHICLE FIRE;18100 UPPER BAY RD;NB;NB;03/26/2011 13:36:15

Contact: robert hunter <r.hunter20@gmail.com>
CAD:Update: ;WBFD;FIRE ALARM;450 W MEDICAL CENTER BLVD;WB;WBW;04/02/2011 07:22:55;CALLED THE NUMBER BELOW AND REC ANSWERING SERVICE THEY COULD PROVIDE MORE I
CAD:FYI: ;WBFD;ACCIDENT MAJOR;MAGNOLIA/ S KOBAYSHI;04/03/2011 18:01:07;gry mits eclipse/////18 wheeler [04/03/11 18:00:35 DJOHNSON]
:CAD:FYI: ;WBFD;SMELL OF SMOKE GAS OUTSIDE;18206 EGRET BAY BLVD;WB;WBE;04/04/2011 17:01:45;SMELL OF NATURAL GAS IN AREA - FENCED AREA NEXT TO ICHIBON [04/04/1
:CAD:FYI: ;WBFD;FIRE ALARM;1001-B W BAY AREA BLVD;WB;WBW;04/05/2011 12:18:03;manual pull in offc [04/05/11 12:18:19 ESALLIER]
:CAD:FYI: ;WBFD;UNCONSCIOUS;20233 IH 45 FWY;WB;WBW;04/07/2011 14:44:07;IN FRONT SHOWROOM [04/07/11 14:44:03 VKEENER] FEMALE PASSED OUT, IS BREATHING
:CAD:Update: ;WBFD;MINOR ACCIDENT;SH 3 HWY/MAGNOLIA AVE;WB;WBE;04/10/2011 13:23:20; 7 months [04/10/11 13:23:18 DCOOLEY] blk tk silber pt cruiser [04/10/11 17:42:10 MHAYES]
:CAD:FYI: ;WBFD;FIRE ALARM;901 S KOBAYASHI RD;WB;WBW;04/10/2011 16:11:22;RESIDENT OF 133 CALLED ADVISED SHE HAD A KITCHEN FIRE AND SPRINKLERS CAME ON
:CAD:FYI: ;WBFD;UNCONSCIOUS;409 GREEN ST;WB;WBW;04/09/2011 11:58:10;he is breathing [04/09/11 11:58:08 TSWANSON] cold and clammy [04/09/11 11:57:46 TSWANSON]

Contact: "Bigonesse, Ray" <Ray.Bigonesse@leaguecity.com>
:CAD:FYI: ;LCFD;ELECTRICAL PROBLEMS SPARKS;FOX RUN CT/CREEKSIDE DR;LC;LCFW;09/10/2011 13:26:13;right next to soccer field, [09/10/11 1  
:CAD:Update: ;LCFD;ELECTRICAL PROBLEMS SPARKS;500-BLK PALOMINO LN;LC;LCFW;09/10/2011 13:26:13;(D2) E2 CAN GO BACK IN SERVICE [09/10/11 13
:CAD:Update: ;LCFD;ELECTRICAL PROBLEMS SPARKS;207 HIDDEN PINES CT;LC;LCFW;09/10/2011 13:26:13;(D2) CONTACT ELECTRIC CO. POLE # F1233565A,
:CAD:FYI: ;LCFD;FIRE ALARM;3045 W MARINA BAY DR;LC;LCFE;09/08/2011 08:52:28;NO CONTACT MADE [09/08/11 08:55:10 RCOX] BUILDING 2 ZONE 13 [
:CAD:FYI: ;LCFD;GRASS;2220 COVESIDE ST;LC;LCFE;09/08/2011 15:19:21;grass fire, front yard,possible from transformer [09/08/11 15:20:13 RD
:CAD:FYI: ;LCFD;FIRE ALARM;1455-3307 LOUISIANA AVE;LC;LCFE;09/07/2011 10:52:53;smoke dector alarm resd 281-557-2707 jones, jazzeline [09/
:CAD:FYI: ;LCFD;FIRE ALARM;1450 LOUISIANA AVE;LC;LCFE;09/07/2011 11:55:22;at bldg 5 [09/07/2011 11:55:20 KCOOK]
:CAD:FYI: ;LCFD;FIRE ALARM;211 W LEAGUE CITY PKWY;LC;LCFW;09/07/2011 12:04:36;automatic fire alarm, 24 hour fire circuit to positions 1 a
:CAD:FYI: ;LCFD;GRASS;2381 GUN RANGE RD;LC;LCFE;09/07/2011 14:33:12;grass, no buildings [09/07/11 14:33:47 RDARROW]

Contact: Active911
Agency name: Nassau Bay Fire Location: Nassau Bay, TX 
Sender: CAD@ossicadpaging.com'

588:CAD:FYI: ;NBFD;FIRST RESPONDERS;1310 ANTIGUA LN;NB;NB;08/29/2012 15:51:49;Event spawned from ASSIST BY EMS. [08/29/2012 15:51:49 TSWANSON] lift assist no injuries [08/29/12 15:51:35 TSWANSON]
659:CAD:FYI: ;NBFD;STRUCTURE FIRE;100 W TEXAS AVE;E81;WB;WBW;08/30/2012 23:06:02;caller see lots of smoke and smells possible fire, does nto see flames [08/30/12 23:03:53 ESALLIER]
:CAD:FYI: ;NBFD;FIRE ALARM;18100 SATURN LN;NB;NB;08/31/2012 07:48:22;smoke det zone 51. general fire alarm. Courtyard by Marriott [08/31/12 07:49:06 DLOCASCIO]
:CAD:FYI: ;NBFD;WASHDOWN;3000 NASA PKWY;NB;NB;08/31/2012 15:38:24;he advised you can follow it from the trail and its visible [08/31/12 15:43:51 LFOLKS] hydrolic fluid in the slow lane w/b from hilton [08/31/12 15:42:51 LFOLKS]
715:CAD:FYI: ;NBFD;UNCONSCIOUS;PORT ROYAL DR/PRINCE WILLIAM LN;NB;NB;09/01/2012 07:54:27;female laying in roadway [09/01/12 07:54:12 LFOLKS]
:CAD:FYI: ;NBFD;FIRE ALARM;501 SARAH DEEL DR;E81;WB;WBE;09/01/2012 15:04:56;building 6 and building 8 fire alarms..not other info [09/01/12 15:04:04 DLOCASCIO]
:CAD:Update: ;NBFD;MUTUAL AID FIRE;501 SARAH DEEL DR;E81;WB;WBE;09/01/2012 15:04:56;[FIRE] BUILDING 9 JUST WENT OFF [09/01/12 15:05:00 DLOCASCIO] building 6 and building 8 fire alarms..not other info [09/01/12 15:04:04 DLOCASCIO]
:CAD:FYI: ;NBFD;ASSIST BY FIRE;18300 ST JOHN BLVD;NB;NB;09/01/2012 19:24:16;elevator #1 / elevator is stuck on the 4th floor [09/01/12 19:24:36 KHAMM]
584:CAD:FYI: ;NBFD;STRUCTURE FIRE;444-1402A E MEDICAL CENTER BLVD;WB;WBE;09/02/2012 02:38:58;[EMS] CALLER WAS INSTRUCTED TO GET HIMSELF AND HIS FAMILY OUT OF THIS APT [09/02/12 02:38:51 KHAMM] CALLER CAN NOT HEAR PEOPLE OUTSIDE ON THE BALCONY [09/02/12 02:37:29 KHAMM] apt above 1402A / c
723:CAD:Update: ;NBFD;STRUCTURE FIRE;444 E MEDICAL CENTER BLVD;E81;WB;WBE;09/02/2012 02:38:58;[LAW] (P65) CODE 3 [09/02/12 02:39:55 NHUGHES] [LAW] (P61) CODE 3 [09/02/12 02:39:50 NHUGHES] 804 RECVD [09/02/12 02:39:45 KHAMM] LCFD ENROUTE [09/02/12 02:39:39 KHAMM] 803 RECVD [09/02/12 02
720:CAD:FYI: ;NBFD;ASSIST BY FIRE;18222 CAPRICE LN;NB;NB;09/03/2012 19:26:12;Event spawned from SUICIDE ATTEMPT / PSYCHIATRIC. [09/03/2012 19:26:12 KHAMM] (M12) REQUESTING HELICOPTER AND FIRE [09/03/12 19:25:50 KHAMM] [LAW] (7325) STABLE // MULTIPLE LACERATIONS TO ARM AND
599:CAD:FYI: ;NBFD;ASSIST BY FIRE;18220 UPPER BAY RD;NB;NB;09/05/2012 16:52:42;Event spawned from ASSIST BY EMS. [09/05/2012 16:52:42 DTHOMASON] non emergency north entrance to gloria dei [09/05/12 16:36:41 DCOOLEY] Event spawned from INTOXICATED DRIVER PERSON. [09/05/2012
600:CAD:FYI: ;NBFD;STRUCTURE FIRE;2040 NASA PKWY;NB;NB;09/07/2012 09:58:19;smoke coming from ceiling from inside [09/07/12 09:58:46 TSWANSON]

 */

public class TXNassauBayParser extends DispatchOSSIParser {
  
  private static final Pattern PREFIX = Pattern.compile("^\\d*:");
  
  public TXNassauBayParser() {
    this("NASSAU BAY", "TX");
  }
  
  protected TXNassauBayParser(String defCity, String defState) {
    super(CITY_CODES, defCity, defState,
          "SKIP SRC CALL! ADDR! UNIT? CITY? CODE? DATETIME! INFO+");
  }
  
  @Override
  public String getAliasCode() {
    return "TXNassauBay";
  }
  
  @Override
  public String getFilter() {
    return "cad@ossicadpaging";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    
    // Strip off option number prefix
    Matcher match = PREFIX.matcher(body);
    if (match.find()) {
      body = body.substring(match.end()).trim();
    }
    return super.parseMsg(body, data);
  }
  
  private static final String EVENT_SPAWNED_MARK = "Event spawned from ";
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith(EVENT_SPAWNED_MARK)) field = field.substring(EVENT_SPAWNED_MARK.length()).trim();
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("UNIT")) return new UnitField("[A-Z]+\\d+", true);
    if (name.equals("CODE")) return new CodeField("[A-Z]{2,4}", true);
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "HO", "NASSAU BAY",
      "LC", "LEAGUE CITY",
      "NB", "NASSAU BAY",
      "SB", "SEABROOK",
      "WB", "WEBSTER"
  });
}
