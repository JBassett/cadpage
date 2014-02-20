package net.anei.cadpage.parsers.TX;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.CodeSet;
import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchOSSIParser;


public class TXGalvestonCountyParser extends DispatchOSSIParser {
  
  private static final Pattern SUBJECT_PTN = Pattern.compile(".* FIRE", Pattern.CASE_INSENSITIVE);
  
  public TXGalvestonCountyParser() {
    super("GALVESTON COUNTY", "TX",
          "ADDR/aS8CI! INFO+");
    setupCallList(CALL_LIST);
    setupMultiWordStreets(
        "BOB SMITH",
        "CAPTAIN BLIGH",
        "CAPTAIN HOOK",
        "COVE VIEW",
        "EDWARD TEACH",
        "JAMAICA BEACH",
        "JAMAICA COVE",
        "JOLLY ROGER",
        "MOBY DICK",
        "PONCE DE LEON",
        "SAN LOUIS PASS",
        "SPANISH MAIN");
  }
  
  @Override
  public String getFilter() {
    return "iammessaging.com,777,888,410,CAD@ci.dickinson.tx.us";
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA;
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    do {
      if (SUBJECT_PTN.matcher(subject).matches()) {;
        data.strSource = subject;
        break;
      }
      
      if (isPositiveId()) break;
      
      return false;
    } while (false);
    
    if (!body.startsWith("CAD:")) body = "CAD:" + body;
    return super.parseMsg(body, data);
  }
  
  @Override
  public String getProgram() {
    return "SRC " + super.getProgram();
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }

  private static final Pattern NUMBER_HWY_PTN = Pattern.compile("\\b(\\d+)(US|FT|TX|FM)\\b");
  private static final Pattern AVE_X_ST_PTN = Pattern.compile("\\bAVE ([A-Z]) ST\\b");
  private static final Pattern AVE_X_ST_PTN2 = Pattern.compile("\\bAVE_([A-Z]) ST\\b");
  private static final Pattern NUMBER_DASH_PTN = Pattern.compile("^(\\d+)-(?![A-Z] |BLK )");
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("FYI:")) field = field.substring(4).trim();
      else if (field.startsWith("Update:")) field = field.substring(7).trim();
      field = NUMBER_HWY_PTN.matcher(field).replaceAll("$1 $2");
      field = AVE_X_ST_PTN.matcher(field).replaceAll("AVE_$1 ST");
      super.parse(field, data);
      data.strAddress = AVE_X_ST_PTN2.matcher(data.strAddress).replaceAll("AVE $1");
      data.strAddress = NUMBER_DASH_PTN.matcher(data.strAddress).replaceFirst("$1 ");
      if (data.strAddress.length() == 0) abort();
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " X";
    }
  }
  
  private static final CodeSet CALL_LIST = new CodeSet(
      "ASSIST EMS",
      "ASSIST LAW ENFORCEMENT",
      "FIRE - NON SPECIFIC",
      "FIRST RESPONDER WITH EMS",
      "GAS LEAK",
      "GRASS FIRE",
      "LANDING ZONE",
      "LINE DOWN",
      "MAJOR ACCIDENT",
      "MUTUAL AID",
      "POLE FIRE",
      "SMOKE INVESTIGATION",
      "STRUCTURE FIRE"
  );
}
