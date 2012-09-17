package net.anei.cadpage.parsers.AL;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Dothan AL
 */
public class ALDothanParser extends FieldProgramParser {
  
  
  public ALDothanParser() {
    super("DOTHAN", "AL",
           "TIME CALL ADDR/SXa CITY! PLACE+? ID INFO+");
  }
  
  @Override
  public String getFilter() {
    return "Robot.ALERT@dothan.org";
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    return parseFields(body.split("/"), 4, data);
  }
  
  private class TimeField extends SkipField {
    public TimeField() {
      setPattern(Pattern.compile("\\d\\d:\\d\\d:\\d\\d"), true);
    }
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      super.parse(field, data);
      if (data.strApt.endsWith("INTERSECTN")) {
        data.strApt = data.strApt.substring(0, data.strApt.length()-10).trim();
      }
      if (data.strApt.startsWith("APT")) data.strApt = data.strApt.substring(3).trim();
    }
  }
  
  private class MyPlaceField extends PlaceField {
    
    @Override
    public void parse(String field, Data data) {
      if (checkAddress(field) > 0) {
        data.strCross = append(data.strCross, " & ", field);
      } else {
        data.strPlace = append(data.strPlace, "/", field);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "X PLACE";
    }
  }
  
  private static final Pattern ID_PTN = Pattern.compile("\\d{8,9}");
  private class MyIdField extends IdField {
    
    @Override
    public boolean canFail() {
      return true;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      
      // checkParse is called when we are checking to see if this is a valid
      // ID field or a place field.  Here we make the normal pattern validation
      if (!ID_PTN.matcher(field).matches()) return false;
      super.parse(field, data);
      return true;
    }
    
    @Override
    public void parse(String field, Data data) {
      
      // parse is called if we have already parsed a place name and are looking
      // for the real ID field.  Generally we should succeed if this matches the
      // normal ID pattern and fail if it does not.
      // One important exception, if this is a mutual aid call, the call ID
      // may be missing entirely, in which case we will treat this as an info
      // field
      if (ID_PTN.matcher(field).matches()) {
        super.parse(field, data);
      } else if (data.strCall.startsWith("MAID-")) {
        data.strSupp = field;
      } else abort();
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("TIME")) return new TimeField();
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("PLACE")) return new MyPlaceField();
    if (name.equals("ID")) return new MyIdField();
    return super.getField(name);
  }
}
