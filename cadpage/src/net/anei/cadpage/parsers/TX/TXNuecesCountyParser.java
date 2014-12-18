package net.anei.cadpage.parsers.TX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class TXNuecesCountyParser extends FieldProgramParser {
  
  public TXNuecesCountyParser() {
    super("NUECES COUNTY", "TX",
           "( NCFIRE:IDCALL | CALL! ) ALRM:SKIP! PRI:PRI! ESZ:ADDR! EV:ID");
  }
  
  @Override
  public String getFilter() {
    return "ccpdpaging@cctexas.com";
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA;
  }
 
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    do {
      if (subject.equals("CAD Notify")) break;
      
      if (body.startsWith("CAD Notify ")) {
        body = body.substring(11).trim();
        break;
      }
      return false;
    } while (false);
    return super.parseMsg(body, data);
  }
  
  private class MyIdCallField extends CallField {
    
    @Override
    public void parse(String field, Data data) {
      int pt = field.indexOf(' ');
      if (pt < 0) abort();
      data.strCallId = field.substring(0,pt).trim();
      data.strCall = field.substring(pt+1).trim();
    }
    
    @Override
    public String getFieldNames() {
      return "ID CALL";
    }
  }
  
  private static final Pattern ADDR_UNIT_PTN = Pattern.compile("(?:(\\d+)|-1)[ /]+(.*)");
  private static final Pattern ADDR_APT_PTN = Pattern.compile("(.*)[,:] *([-A-Z0-9]+)");
  private class MyAddressField extends AddressField {

    @Override
    public void parse(String field, Data data) {
      Matcher match = ADDR_UNIT_PTN.matcher(field);
      if (!match.matches()) abort();
      data.strUnit = getOptGroup(match.group(1));
      field = match.group(2);
      int pt = field.indexOf(": @");
      if (pt >= 0) {
        data.strPlace = field.substring(pt+3).trim().replace(": @", " - ");
        field = field.substring(0,pt);
      }
      String apt = "";
      match = ADDR_APT_PTN.matcher(field);
      if (match.matches()) {
        field = match.group(1).trim();
        apt = match.group(2);
      }
      field = stripFieldEnd(field, " NUECS");
      if (field.endsWith(" CC")) {
        data.strCity = "CORPUS CHRISTI";
        field = field.substring(0, field.length()-3).trim();
      }
      String alias = null;
      pt = field.indexOf(": alias ");
      if (pt >= 0) {
        alias = field.substring(pt+8).trim();
        field = field.substring(0,pt).trim();
        field = stripFieldEnd(field, " NUECS");
        if (field.endsWith(" CC")) {
          data.strCity = "CORPUS CHRISTI";
          field = field.substring(0, field.length()-3).trim();
        }
      }
      super.parse(field, data);
      data.strApt = append(data.strApt, "-", apt);
      if (alias != null) {
        data.strAddress = append(data.strAddress, " ", '(' + alias + ')');
      }
    }
    
    @Override
    public String getFieldNames() {
      return "UNIT ADDR CITY APT PLACE";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("IDCALL")) return new MyIdCallField();
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
}
