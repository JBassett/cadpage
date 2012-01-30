package net.anei.cadpage.parsers.KY;

import net.anei.cadpage.parsers.dispatch.DispatchBParser;

/*
Daviess County, KY
Contact: Aaron Haynes <firecop142@gmail.com>,2703147339@mms.att.net
Sender:911-CENTER@911Center@central

911-CENTER:MBD >BREATHING DIFFICULTY 6261 MILLERS MILL RD XS: HIGHWAY 142 PHILPOT MARTIN Map: Grids:, Cad: 2011-0000013315
911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY Map: Grids:, Cad: 2011-0000013291
911-CENTER:FF >WILDLAND FIRE 9707 OLD HARTFORD RD XS: U S HIGHWAY 231 UTICA KIPLING, ED Map: Grids:, Cad: 2011-0000013076
911-CENTER:FF >WILDLAND FIRE 12957 RED HILL-MAXWELL RD XS: E HARMONS FERRY RD UTICA PRESSON, DAVID Map: Grids:, Cad: 2011-0000012778
911-CENTER:MPD >UNKNOWN/PERSON DOWN 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO DAVIESS COUNTY BOARD OF EDUCAT Map: Grids:, Cad: 2011-0000013349
911-CENTER:FF >WILDLAND FIRE 2324 HARRIET LN XS: OLD HARTFORD RD OWENSBORO HUTCHINSON, G & M Map: Grids:, Cad: 2011-0000013387
911-CENTER:MSTR >STROKE 3294 BELLTOWN RD XS: NEW BETHEL CHURCH LN HARTFORD Map: Grids:, Cad: 2011-0000013576
911-CENTER:MSZ >SEIZURES 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO DAY, RUS Map: Grids:, Cad: 2011-0000013611

1 of 2 FRM:911-CENTER@911Center@central MSG: 911-CENTER:MBD >BREATHING DIFFICULTY 6261 MILLERS MILL RD XS: HIGHWAY 142 PHILPOT MARTIN Map: (Con't) 2 of 2 Grids:, Cad: 2011-0000013315 (End) 1 of 2 FRM:911-CENTER@911Center@central MSG: 911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY (Con't) 2 of 2 Map: Grids:, Cad: 2011-0000013291 (End)FRM:911-CENTER@911Center@central MSG:911-CENTER:FF >WILDLAND FIRE 9707 OLD HARTFORD RD XS: U S HIGHWAY 231 UTICA KIPLING, ED Map: Grids:, Cad: 2011-0000013076
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:911-CENTER:FF >WILDLAND FIRE 12957 RED HILL-MAXWELL RD XS: E HARMONS FERRY RD UTICA PRESSON, DAVID\n(Con't) 2 of 2\nMap: Grids:, Cad: 2011-0000012778(End)
FRM:911-CENTER@911Center@central\nMSG:911-CENTER:FF >WILDLAND FIRE 9707 OLD HARTFORD RD XS: U S HIGHWAY 231\nUTICA KIPLING, ED Map: Grids:, Cad: 2011-0000013076
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:ACCINJ>ACCIDENT WITH INJURIES 3970 CRANE POND RD XS: U S HIGHWAY 231 PHILPOT JOHNS, AMY\n(Con't) 2 of 2\nMap: Grids:, Cad: 2011-0000013291 (End)
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:MBD >BREATHING DIFFICULTY 6261 MILLERS MILL RD XS: HIGHWAY 142 PHILPOT MARTIN Map:\n(Con't) 2 of 2\nGrids:, Cad: 2011-0000013315 (End)
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:MPD >UNKNOWN/PERSON DOWN 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO\n(Con't) 2 of 2\DAVIESS COUNTY BOARD OF EDUCAT Map: Grids:, Cad: 2011-0000013349(End)
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:FF >WILDLAND FIRE 2324 HARRIET LN XS: OLD HARTFORD RD OWENSBORO HUTCHINSON, G & M Map:\n(Con't) 2 of 2\nGrids:, Cad: 2011-0000013387(End)
FRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:MSTR >STROKE 3294 BELLTOWN RD XS: NEW BETHEL CHURCH LN HARTFORD Map: Grids:, Cad: 2011-0000013576
1 of 2\nFRM:911-CENTER@911Center@central\nMSG:\n911-CENTER:MSZ >SEIZURES 4255 NEW HARTFORD RD XS: NEAR SOUTHEASTERN PKY OWENSBORO DAY, RUS Map:\n(Con't) 2 of 2\nGrids:, Cad: 2011-0000013611(End)

Contact: Jeffery Kingkade <jeffery.kingkade@gmail.com>
Sender: 2002000004
911-CENTER:MU >UNCONCIOUS 5005 HIGHWAY 142 XS: TIMBER RIDGE DR PHILPOT BIRKHEAD, LARRY, REV Map: Grids:, Cad: 2012-0000008822

*/
public class KYDaviessCountyParser extends DispatchBParser {

  private static final String DEF_STATE = "KY";
  private static final String DEF_CITY = "DAVIESS COUNTY";
 
  public KYDaviessCountyParser() {
    super(DEF_CITY, DEF_STATE);
  }
  
  @Override
  public String getFilter() {
    return "911-CENTER,2002";
  }
  
  @Override
  protected boolean isPageMsg(String body) {
    return body.startsWith("911-CENTER:");
  }
}
