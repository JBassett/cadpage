package net.anei.cadpage.parsers.NY;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA7BaseParser;

/**
 * Monroe County, NY (Webster) 
 */
public class NYMonroeCountyWebsterParser extends DispatchA7BaseParser {
  
  private static final Pattern MARKER = Pattern.compile("^([A-Z]{4}) +B:([ 0-9]\\d{3})? +(\\d[A-Z]?) +");
  
  public NYMonroeCountyWebsterParser() {
    super(CITY_CODES, "MONROE COUNTY", "NY",
          "BOX:BOX? L:ADDR! BOX:BOX? T:CALL! O:NAME? B:PLACE? PH:PHONE? C1:X? C2:X? X:INFO!");
  }
  
  @Override
  public String getFilter() {
    return "@rednmxcad.com,messaging@iamresponding.com,pagerchanges@monroecounty.gov";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    int pt = body.indexOf("\n\n");
    if (pt >= 0) body = body.substring(0,pt).trim();
    body = body.replace('\n', ' ').trim();
    Matcher match = MARKER.matcher(body);
    if (match.find()) {
      data.strSource = match.group(1);
      data.strBox = getOptGroup(match.group(2));
      data.strPriority = match.group(3);
      body = body.substring(match.end()).trim();
    }
    return super.parseMsg(body, data);
  }
  
  @Override
  public String adjustMapAddress(String addr) {
    // PK is abbreviation of PARK instead of the expected PIKE
    return PK_PATTERN.matcher(addr).replaceAll("PARK");
  }
  private static final Pattern PK_PATTERN = Pattern.compile("\\bPK\\b", Pattern.CASE_INSENSITIVE);
  
  @Override
  public String getProgram() {
    return "SRC BOX PRI " + super.getProgram();
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      
      "AVO", "AVON",
      "BAT", "BATAVIA",
      "BER", "BERGEN",
      "BRI", "BRIGHTON",
      "BRO", "BROCKPORT",
      "BRS", "BRISTOL",
      "CAL", "CALEDONIA",
      "CHI", "CHILI",
      "CHU", "CHURCHVILLE",
      "CLA", "CLARKSON",
      "CLR", "CLARENDON",
      "ERO", "EAST ROCHESTER",
      "FAI", "FAIRPORT",
      "FAR", "FARMINGTON",
      "FRP", "FAIRPORT",
      "GAT", "GATES",
      "GRE", "GREECE",
      "HAM", "HAMLIN",
      "HEN", "HENRIETTA",
      "HIL", "HILTON",
      "HFL", "HONEOYE FALLS",
      "HOL", "HOLEY",
      "HON", "HONEOYE FALLS",
      "IRO", "IRONDEQUOIT",
      "KEN", "KENDALL",
      "LER", "LEROY",
      "LIM", "LIMA",
      "LIV", "LIVONIA",
      "LYN", "LYONS",
      "MAC", "MACEDON",
      "MAR", "MARION",
      "MED", "MEDINA",
      "MEN", "MENDON",
      "MUR", "MURRAY",
      "NEW", "NEWARK",
      "OGD", "OGDEN",
      "ONT", "ONTARIO",
      "PAL", "PALMYRA",
      "PAR", "PARMA",
      "PEN", "PENFIELD",
      "PER", "PERINTON",
      "PIT", "PITTSFORD",
      "PIV", "PITTSFORD",
      "RIG", "RIGA",
      "ROC", "ROCHESTER",
      "RUS", "RUSH",
      "SAV", "SAVANNAH",
      "SCO", "SCOTTSVILLE",
      "SOD", "SODUS",
      "SPE", "SPENCERPORT",
      "SWE", "SWEDEN",
      "VIC", "VICTOR",
      "WAL", "WALWORTH",
      "WBL", "WEST BLOOMFIELD",
      "WBT", "WEBSTER",
      "WBV", "WEBSTER",
      "WEB", "WEBSTER",
      "WHE", "WHEATLAND",
      "WLM", "WILLIAMSON",
      "VIC", "VICTOR"
      
  });
  
}
	