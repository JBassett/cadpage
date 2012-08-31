package net.anei.cadpage.parsers.NC;


import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchSouthernParser;

/*
Harnett County, NC
Contact: Active911
Agency name: CITY OF DUNN Location: DUNN, NC 
Sender: GINO@southernsoftware.net

DBAREFOOT:PD LOBBY 401 E BROAD ST DUNN N MAGNOLIA AVE X S ELM ST 9108922171 201214938 10:21:32 X-ENTRY NP STA
DBAREFOOT:CORNERSTONE NURSING AND REHABILITATION CENTER 711 SUSAN TART RD DUNN MCKAY AVE X PRIVATE DRIVE (HOSPITAL) csts1-admin@cornerstonenursingandrehab.com 9108928843 201214939 10:24:48 X-ENTRY
DBAREFOOT:814 N MAGNOLIA AVE DUNN E SURLES ST X E COLE ST 201214940 10:54:48 X-ENTRY
SBYRD:LITTLE IVEY LEAGUE DAY CARE 710 SUSAN TART RD DUNN MCKAY AVE X PRIVATE DRIVE (HOSPITAL) 9108914120 201214941 10:58:56 X-ENTRY
mblake:DUNN EMERGENCY SERVICES,INC. 101 W CUMBERLAND ST DUNN RAILROAD TRACKS X S FAYETTEVILLE AVE aft@dunn-nc.org 9108921211 201214946 11:54:07 X-ENTRY
CCALLAHAN:PINEWOOD APARTMENTS 400 PONDEROSA DR DUNN PINEWOOD APTS 33-60 X N ASHE AVE 9108921458 201214954 12:56:10 X-ENTRY
CCALLAHAN:WESTGATE APARTMENTS 400 PONDEROSA DR DUNN PINEWOOD APTS 33-60 X N ASHE AVE 9108923181 201214957 13:10:10 X-ENTRY
CCALLAHAN:ELLEN CLARA 600 CANAL DR 0002B DUNN FAIRGROUND RD X MEMORIAL AVE CLARA ELLEN - PREMISE 9102922030 201214962 14:10:49 X-ENTRY
mblake:41 24 3:40:51 06/26/12 17:15:31 06/22/12 17:29:25 H06/28/12 14:19: 201214972 15:34:53 X-ENTRY
CCALLAHAN:SENIOR CITIZENS VILLAGE RESTHOME 504 CANAL DR DUNN FAIRGROUND RD X MEMORIAL AVE 9108921241 201214973 15:39:31 X-ENTRY
SBYRD:PD LOBBY 401 E BROAD ST DUNN N MAGNOLIA AVE X S ELM ST 9108922171 201214979 16:16:40 X-ENTRY NP STA
101 ANDERSON ST ABERDEEN ANDERSON ST X ANDERSON ST AAR POWER BOSS 9109449018 2012000773 15:38:54 TEST CALL TYPE test OCA: 201207-17
2012000774 GINO(EN)10:51:45|GINO(INF)14:06:24|GINO(OS)12:47:42|GINO(CL)12:48:30|
2012000774 GINO(EN)10:51:45|GINO(INF)14:06:24|GINO(OS)12:47:42|GINO(CL)12:48:30|
150 PERRY DR SOUTHERN PINES Lorri Bruce 9106950005 2012000799 15:42:02 TEST CALL TYPE For instructions to groups specific to the call type.
DBAREFOOT:PD LOBBY 401 E BROAD ST DUNN N MAGNOLIA AVE X S ELM ST 201217112 15:49:52 X-ENTRY NP STA
NFLEMING:6100 FAIRGROUND RD DUNN WILLIE MCLEOD LN/PVT RD X BIRCHDALE DR 201217203 13:22:54 X-ENTRY CALLER STATED APPEARED TO BE A DOMESTIC/SOMEONE ELSE GOT ON THE PHONE AND STATED EVERYTHING WAS FINE

*/

public class NCHarnettCountyBParser extends DispatchSouthernParser {
  
  private static final Pattern RUN_REPORT_PTN = Pattern.compile("\\b\\d{1,2}:\\d\\d:\\d\\d \\d\\d/\\d\\d/\\d\\d\\b|\\bGINO\\(EN\\)");
  
  public NCHarnettCountyBParser() {
    super(NCHarnettCountyParser.CITY_LIST, "HAYWOOD COUNTY", "NC", 
           DSFLAG_OPT_DISPATCH_ID | DSFLAG_LEAD_PLACE | DSFLAG_CROSS_NAME_PHONE | DSFLAG_ID_OPTIONAL);
  }

  @Override
  public String getFilter() {
    return "GINO@southernsoftware.net";
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    if (RUN_REPORT_PTN.matcher(body).find()) {
      data.strCall = "RUN REPORT";
      data.strPlace = body;
      return true;
    }
    return super.parseMsg(body, data);
  }
}