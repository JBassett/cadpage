package net.anei.cadpage.parsers.PA;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;


/* 
Chester County, PA (variant D1)
Contact: Ray Stegmaier <stegmaierr@gmail.com>
Sender: adi62@ridgefirecompany.com
http://wiki.radioreference.com/index.php/Chester_County_%28PA%29

(340 RIDGE RD ,21 -- EAST) 14:04 ** ALARM - FIRE * ** 340 RIDGE RD ,21 -- EAST VINCENT ELEM SC ** 6253 ** AFA/GENERAL/ATN\n**  **
(56 WADE DR ,21  btwn NOT) 11:43 ** ALARM - FIRE * ** 56 WADE DR ,21  btwn NOTTINGHAM DR & SHE ** 6203 ** AFA - GEN - ATN\n
(WB BETHEL CHURCH RD WO S) 08:53 ** ACCIDENT - UNKNOWN INJURIES * **  WB BETHEL CHURCH RD WO STONEY RUN RD ,18 ** 6201 ** 1 VEH, BLOCKING, AIR BAG DEPLOYM
(1059 SANATOGA RD ,18  bt) 08:49 ** HOUSE FIRE * ** 1059 SANATOGA RD ,18  btwn MEADOW LA & E ** 6201 ** ELECTRIC FIRE IN WALL - FLAMES AND SMOKE\nDETAILS
(SB FULMER RD SO EBELHARE) 21:26 ** ACCIDENT - MOTORCYCLE * ** SB FULMER RD SO EBELHARE RD ,18   (V) ** 6201 ** MC VS TREE ---\nDETAILS TO FOLLOW\n**  **
(1701 POTTSTOWN PK ,20 --) 14:39 ** ACCIDENT - MOTORCYCLE * ** 1701  POTTSTOWN PK ,20 -- MAIN EVENT SPOR ** 6205 ** TWO DOWN IN THE ROADWAY\n**  **

 */


public class PAChesterCountyD1Parser extends PAChesterCountyBaseParser {
  
  private static final Pattern DELIM = Pattern.compile("\\*\\*");
  
  public PAChesterCountyD1Parser() {
    super("TIME! CALL ADDRPLX ID INFO+");
  }
  
  @Override
  public String getFilter() {
    return "adi62@ridgefirecompany.com";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    if (isVariantGMsg(body)) return false;

    // subject is truncated version of address that we don't care about
    // but it has to be non-empty
    if (subject.length() == 0) return false;
    
    // And all of the should treat line breaks as spaces
    body = body.replace('\n', ' ');

    // Split and parse by double asterisk delimiters
    return parseFields(DELIM.split(body), data);
  }
  
  // Call field strips trailing asterisk marker
  private class MyCallField extends CallField {
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith(" *")) field = field.substring(0,field.length()-2);
      super.parse(field, data);
    }
  }
  
  // Address has to expand on the base class MyAddressField
  // adding cross street and place names
  private static final Pattern INTERSECT = Pattern.compile("\\b[NSEW]O\\b");
  private static final Pattern PIKE = Pattern.compile("\\bPK\\b");
  private class MyAddressPlaceCrossField extends AddressField {
    
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith("(V)")) field = field.substring(0, field.length()-3).trim();
      Parser p = new Parser(field);
      data.strCross = p.getLastOptional(" btwn ");
      data.strPlace = p.getLastOptional(" -- ");
      field = p.get();
      field = INTERSECT.matcher(field).replaceAll("&");
      field = PIKE.matcher(field).replaceAll("PIKE");
      parseChesterAddress(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR CITY PLACE X";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("CALL")) return new MyCallField();
    if (name.equals("ADDRPLX")) return new MyAddressPlaceCrossField();
    return super.getField(name);
  }
} 
