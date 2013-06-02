package net.anei.cadpage.parsers.KY;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Louisville, KY
 */
public class KYCampbellCountyParser extends FieldProgramParser {
  
  private static final String PAGE_PREFIX = "Notification from CIS Active 911:";
  private static final Pattern DELIM_PTN = Pattern.compile("\n{2}");
  
  public KYCampbellCountyParser() {
    super("CAMPBELL COUNTY", "KY",
           "ADDRCITY/SC DUP? SRC! Time_reported:DATETIME! Unit(s)_responded:UNIT+");
  }
  
  @Override
  public String getFilter() {
    return "noreply@cisusa.org";
  }
  
  @Override 
  public boolean parseMsg(String subject, String body, Data data) {
    
    if(!body.startsWith(PAGE_PREFIX)) return false;
    
    body = body.substring(PAGE_PREFIX.length()).trim();      // Remove message prefix
    
    String[] fields = DELIM_PTN.split(body);
    
    // If the body does not have the Time Completed label, process message
    if(!body.contains("Time completed:")) {
      return super.parseFields(fields, data);
    }
    // Otherwise we are a report
    else {
      data.strCall = "RUN REPORT";
      
      // Extract Units
      String units = fields[fields.length-1];
      int colon = units.indexOf(':');
      units = units.substring(colon+1).trim();
      data.strUnit = units.replace("\n", ", ").trim();   
      
      // Extract ID
      if (fields.length > 1) {
        String[] srcID = fields[1].split("-");
        if (srcID.length > 1) data.strCallId = srcID[1].trim();
      }
      
      data.strPlace = body;                                               // Put remaining in Place.
      return true;
    }
    
  }
  
  private static final Pattern PTN_FULL_ADDR = Pattern.compile("(.*, .*), \\d{5}");
  private class MyAddressField extends AddressCityField {
    
    @Override 
    public void parse(String field, Data data) {
      Matcher m = PTN_FULL_ADDR.matcher(field);   // This will match address, city, and zip
      if(m.matches()) {                           // If we have a match
        field = m.group(1);                       // Remove the zipcode
      }
      
      int x = field.indexOf("/unincorp");
      if(x >= 0) {
        field = field.substring(0, x) + field.substring(x+9);
      }
      
      super.parse(field, data);
    }
  }

  private class DuplField extends SkipField {
    
    @Override
    public boolean canFail() {
      return true;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      return field.equals(getRelativeField(-1));
    }
  }
  
  private class MySrcField extends SourceField {
    
    @Override 
    public void parse(String field, Data data) {

      int delim = field.indexOf(" - ");
      if(delim >= 0) {
        data.strSource = field.substring(0, delim).trim();
        data.strCallId = field.substring(delim+3).trim();
      }
      else super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " ID";
    }
  }
  
  private class MyUnitField extends UnitField {
    @Override
    public void parse(String field, Data data) {
      field = field.replace('\n', ' ');
      super.parse(field, data);
    }
  }
  
  
  @Override
  public Field getField(String name) {
      if (name.equals("ADDRCITY")) return new MyAddressField();
      if (name.equals("DUP")) return new DuplField();
      if (name.equals("SRC")) return new MySrcField();
      if (name.equals("DATETIME")) return new DateTimeField(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa"));
      if (name.equals("UNIT")) return new MyUnitField();
    return super.getField(name);
  }

}
