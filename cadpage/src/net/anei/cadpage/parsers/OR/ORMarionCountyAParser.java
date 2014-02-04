package net.anei.cadpage.parsers.OR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Marion County, OR
 */
public class ORMarionCountyAParser extends FieldProgramParser {
  
  private static final Pattern MAP_PTN = Pattern.compile(":MAP::(\\d+[A-Z]?):");
  
  public ORMarionCountyAParser() {
    super("MARION COUNTY", "OR",
          "CALL ADDRCITY ( UNIT! MAP? | PLACE? MAP! CH UNIT ) INFO+");
  }
  
  @Override
  public String getFilter() {
    return "Dispatch@ci.woodburn.or.us";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    do {
      if (subject.equals("Incident") || subject.equals("!")) break;
      
      if (body.startsWith("Incident / ")) {
        body = body.substring(11).trim();
        break;
      }
      
      if (body.startsWith("! / ")) {
        body = body.substring(4).trim();
        break;
      }
      
      return false;
    } while (false);
    
    // And a MAP::<code> construct
    body = MAP_PTN.matcher(body).replaceFirst(":MAP-$1:");
    
    String[] flds = body.split(":", -1);
    if (flds.length == 1) {
      setFieldList("ADDR APT CALL");
      parseAddress(StartType.START_ADDR, body, data);
      data.strCall = getLeft();
      return getStatus() > 0;
    }
    return parseFields(flds, data);
  }
  
  private class MyAddressCityField extends AddressCityField {
    
    @Override
    public void parse(String field, Data data) {
      field = field.replace('@', '&');
      int pt = field.indexOf(',');
      if (pt >= 0) {
        data.strCity = field.substring(pt+1).trim();
        field = field.substring(0,pt).trim();
      }
      if (field.contains("I5") || field.contains(" MP ")) {
        parseAddress(field, data);
      } else {
        parseAddress(StartType.START_ADDR, FLAG_RECHECK_APT | FLAG_ANCHOR_END, field, data);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR APT CITY";
    }
  }
  
  private static final Pattern UNIT_PTN = Pattern.compile("[A-Z]+[0-9]+(?:-[A-Z]+)?(?:,.*)?");
  private static final Pattern STATION_PTN = Pattern.compile("\\bSTA\\d+$");
  private class MyUnitField extends UnitField {
    public MyUnitField() {
      setPattern(UNIT_PTN);
    }
    
    @Override
    public void parse(String fld, Data data) {
      if (fld.endsWith(",")) fld = fld.substring(0,fld.length()-1);
      Matcher match = STATION_PTN.matcher(fld);
      if (match.find()) {
        data.strSource = match.group();
        fld = fld.substring(0,match.start()).trim();
        if (fld.endsWith(",")) fld = fld.substring(0,fld.length()-1);
      }
      super.parse(fld, data);
    }
    
    @Override
    public String getFieldNames() {
      return "UNIT SRC";
    }
  }
  
  private class MyPlaceField extends PlaceField {
    
    @Override
    public boolean canFail() {
      return true;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      if (field.startsWith("MAP-")) return false;
      parse(field, data);
      return true;
    }
    
    @Override 
    public void parse(String field, Data data) {
      if (field.equals(data.strAddress)) return;
      super.parse(field, data);
    }
  }
  
  private static final DateFormat DATE_TIME_FMT = new SimpleDateFormat("MM/dd/yyyy hh-mm-ss aa");
  private static final Pattern DATE_TIME_PTN = Pattern.compile("\\d\\d?/\\d\\d?/\\d+.*");
  private class MyDateTimeField extends DateTimeField {
    
    public MyDateTimeField() {
      super(DATE_TIME_FMT);
    }
    
    @Override
    public boolean canFail() {
      return true;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      if (! DATE_TIME_PTN.matcher(field).matches()) return false;
      super.checkParse(field, data);
      return true;
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDRCITY")) return new MyAddressCityField();
    if (name.equals("UNIT")) return new MyUnitField();
    if (name.equals("PLACE")) return new MyPlaceField();
    if (name.equals("MAP")) return new MapField("MAP-(.*)", true);
    if (name.equals("DATETIME")) return new MyDateTimeField();
    return super.getField(name);
  }
}
