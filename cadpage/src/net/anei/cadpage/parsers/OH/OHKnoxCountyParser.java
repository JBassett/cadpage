package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.dispatch.DispatchEmergitechParser;

/*
Knox County, OH
Contact: timothy belcher <engine424@gmail.com>
Sender: Dispatch@smtp-server.Columbus.rr.com

Dispatch:[SD35]- NATURE: FIRE TRASH/DUMPSTER FIRE LOCATION: 21 JOHNSV ILLE RD CENTERBURG BETWEEN COLUMBUS RD / UPDIKE RD COMMENTS : MATRESS ON FIRE IN PARK ACROSS FROM SUNOCO STATION 
Dispatch:[SD24]- NATURE: FIRESINGLE FAMILY RES STRUCTURE LOCATION: 42 20 ST RT 3 COMMENTS: MUTUAL AID BST&G STRUCTURE FIRE//HOUSE FILLED W/SMOKE////X ROADS OLD 3C//MEREDITH STATE RD
Dispatch:[SD35]- NATURE: CARDIAC OR RESPIRATORY ARREST/DEATH LOCATION : 212 FAIRVIEW AVE SUITE: BLDG CENTERBURG BETWEEN JONES AVE / LEONARD AVE COMMENTS: X Y 39 YOF TRACH AND VE NTILATOR PATIENT FULL CODE PROQA MEDICAL: NEW CASE NUMBER A SSIGNED CALL 11-1010849 BY SD35 (POS 02) AT 07:13:06 PROQA MEDICAL: ABORT CALL 11-1010849 BY SD35 (POS 02) AT 07:13:13 - DID NOT USE PROQA MEDICAL: STOPPED CALL 11-1010849 BY SD3 5 (POS 02) AT 07:13:13
Dispatch:[SD8]- NATURE: FALLS/BACK INJURY (TRAUMATIC) LOCATION: 164 W HOUCK ST SUITE: 109 CENTERBURG BETWEEN N PRESTON ST / WILL IS ST COMMENTS: FEMALE HAS FALLEN
Dispatch:[SD24]- NATURE: UNCONCIOUS/PASSING OUT (NONTRAUMATI LOCATION : 164 W HOUCK ST SUITE: 112 CENTERBURG BETWEEN N PRESTON ST / WILLIS ST COMMENTS: PROQA MEDICAL: NEW CASE NUMBER ASSIGN ED CALL 11-1010741 BY SD24 (POS 01) AT 08:13:03 88 YOM UNRE SPONSIVE//BREATHING AT THIS TIME

 */


public class OHKnoxCountyParser extends DispatchEmergitechParser {
  
  public OHKnoxCountyParser() {
    super("Dispatch:", 69, CITY_LIST, "KNOX COUNTY", "OH");
  }
  
  @Override
  public String getFilter() {
    return "Dispatch@smtp-server.Columbus.rr.com";
  }
  
  private static final String[] CITY_LIST = new String[]{

    "MOUNT VERNON",

    "CENTERBURG",
    "DANVILLE",
    "FREDERICKTOWN",
    "GAMBIER",
    "GANN",
    "MARTINSBURG",
    "UTICA",

    "BERLIN TWP",
    "BROWN TWP",
    "BUTLER TWP",
    "CLAY TWP",
    "CLINTON TWP",
    "COLLEGE TWP",
    "HARRISON TWP",
    "HILLIAR TWP",
    "HOWARD TWP",
    "JACKSON TWP",
    "JEFFERSON TWP",
    "LIBERTY TWP",
    "MIDDLEBURY TWP",
    "MILFORD TWP",
    "MILLER TWP",
    "MONROE TWP",
    "MORGAN TWP",
    "MORRIS TWP",
    "PIKE TWP",
    "PLEASANT TWP",
    "UNION TWP",
    "WAYNE TWP",

    "BANGS",
    "BLADENSBURG",
    "HOWARD",
    "MOUNT LIBERTY",
  };
}
