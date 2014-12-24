package net.anei.cadpage.parsers.NY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class NYNassauCountyElmontParser extends FieldProgramParser {
  
  private static final Pattern DATE_TIME_PTN1 = Pattern.compile("(\\d\\d?/\\d\\d?/\\d{4}) (\\d\\d:\\d\\d)");
  private static final Pattern DATE_TIME_PTN2 = Pattern.compile(" +(\\d\\d?/\\d\\d?) (\\d\\d:\\d\\d)$");

  public NYNassauCountyElmontParser() {
    super(CITY_LIST, "NASSAU COUNTY", "NY", 
           "Call:ID_CALL! ( SELNEW Sub:CALL! Address:ADDR1! Cross:X Info:INFO| Sub:ADDR/SC! Cross:XINFO )" );
  }
  
  @Override
  public String getFilter() {
    return "Elmont@Alarms.com,@rednmxcad.com";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    Matcher match = DATE_TIME_PTN1.matcher(subject);
    if (match.matches()) {
      data.strDate = match.group(1);
      data.strTime = match.group(2);
    } else if ((match = DATE_TIME_PTN2.matcher(body)).find()) {
      data.strDate = match.group(1);
      data.strTime = match.group(2);
      body = body.substring(0,match.start());
    }
    return super.parseMsg(body, data);
  }
  
  @Override
  public String getProgram() {
    return super.getProgram() + " DATE TIME";
  }

  @Override
  public Field getField(String name) {
    if (name.equals("ID_CALL")) return new MyIdCallField();
    if (name.equals("SELNEW")) return new MySelNewField();
    if (name.equals("ADDR1")) return new MyAddress1Field();
    if (name.equals("X")) return new MyCrossField();
    if (name.equals("INFO")) return new MyInfoField(false);
    if (name.equals("XINFO")) return new MyInfoField(true);
    return super.getField(name);
  }
  
  private static final Pattern ID_PTN = Pattern.compile("\\d{12}");
  private class MyIdCallField extends CallField {
    @Override
    public void parse(String field, Data data) {
      if (ID_PTN.matcher(field).matches()) {
        data.strCallId = field;
      } else {
        data.strCall = field;
      }
    }
    
    @Override
    public String getFieldNames() {
      return "ID CALL";
    }
  }
  
  private class MySelNewField extends SelectField {
    @Override
    public boolean checkParse(String field, Data data) {
      return data.strCallId.length() > 0;
    }
  }
  
  private class MyAddress1Field extends AddressField {
    @Override
    public void parse(String field, Data data) {
      Parser p = new Parser(field);
      String city = p.getLastOptional(',');
      if (city.equals("NY")) city = p.getLastOptional(',');
      parseAddress(p.get(), data);
      data.strCity = city;
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR APT CITY";
    }
  }
  
  private class MyCrossField extends CrossField {
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith("&")) field = field.substring(0,field.length()-1).trim();
      if (field.startsWith("&")) field = field.substring(1).trim();
      super.parse(field, data);
    }
  }

  private static final Pattern DASH_ZONE_PTN = Pattern.compile("(.*) - (ZONE \\d+)\\b(.*)");
  private static final Pattern INFO_ZONE_PTN = Pattern.compile(", (ZONE \\d)$");
  private static final Pattern INFO_MAP_PTN = Pattern.compile(" +(MAP +\\d+|\\d+-[A-Z]\\d)$");
  private static final Pattern CROSS_MARK_PTN = Pattern.compile("(.*?) (&(?: |$).*)");
  private static final Pattern CONCAT_STREET_PTN = Pattern.compile("([A-Z]+(?: [\\(\\)A-Z]+)? (?:ROAD|RD|ST|AVE))(.*)"); 
  
  private class MyInfoField extends InfoField {
    
    private boolean inclCross;
    
    public MyInfoField(boolean inclCross) {
      this.inclCross = inclCross;
    }
    
    
    @Override
    public void parse(String field, Data data) {
      
      if (inclCross) {
        Matcher match =  DASH_ZONE_PTN.matcher(field);
        if (match.matches()) {
          data.strCross = match.group(1).trim();
          data.strUnit = match.group(2);
          data.strSupp = match.group(3).trim();
          return;
        }
      }
      Matcher match = INFO_ZONE_PTN.matcher(field);
      if (match.find()) {
        data.strUnit = match.group(1);
        field = field.substring(0,match.start()).trim();
      } else {
        int pt = field.lastIndexOf(',');
        if (pt >= 0) {
          if ("ZONE ".startsWith(field.substring(pt+1).trim())) {
            field = field.substring(0,pt).trim();
          }
        }
      }
      match = INFO_MAP_PTN.matcher(field);
      if (match.find()) {
        data.strMap = match.group(1);
        field = field.substring(0, match.start()).trim();
      }
      
      if (inclCross) {
        Result res = parseAddress(StartType.START_ADDR, FLAG_ONLY_CROSS, field);
        if (res.isValid()) {
          res.getData(data);
          field = res.getLeft();
        } else {
          match = CROSS_MARK_PTN.matcher(field);
          if (match.matches()) {
            data.strCross = match.group(1).trim();
            field = match.group(2);
          }
        }
        if (field.startsWith("&")) {
          field = field.substring(1).trim();
          String cross;
          match = CONCAT_STREET_PTN.matcher(field);
          if (match.matches()) {
            cross = match.group(1);
            field = match.group(2).trim();
          } else {
            cross = field;
            field = "";
          }
          data.strCross = append(data.strCross, " & ", cross);
        }
      }
      
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "X INFO MAP UNIT";
    }
  }
  
  private static final String[] CITY_LIST = new String[]{
    "ALDEN MANOR", 
    "BELLEROSE TERRACE", 
    "BELLEROSE", 
    "ELMONT", 
    "FLORAL PARK", 
    "FRANKLIN SQUARE", 
    "HEMPSTEAD",
    "LYNBROOK", 
    "MALVERNE", 
    "NO VALLEY STREAM", 
    "SO FLORAL PARK", 
    "STEWART MANOR", 
    "VALLEY STREAM",
    "WEST HEMPSTEAD" 
  };
}
