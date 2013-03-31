package net.anei.cadpage.parsers.OH;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.general.GeneralParser;;


public class OHColumbianaCountyParser extends GeneralParser {
  
  private static final Pattern SOURCE_PTN = Pattern.compile("^This email was sent by: (.*)\n+");
  private static final Pattern ADDRESS_QUAL_PTN = 
      Pattern.compile(" +(NEXT TO|(?:IN )?FRONT OF|(?:JUST )?BEFORE) ", Pattern.CASE_INSENSITIVE);

  public OHColumbianaCountyParser() {
    super("COLUMBIANA COUNTY", "OH");
    setupMultiWordStreets("CALCUTTA SMITH FERRY", "CALCUTTA SMITHFERRY");
    setFieldList("SRC CALL ADDR APT DATE TIME X INFO");
  }
  
  @Override
  public String getFilter() {
    return "leetoniafd@hotmail.com,messaging@emergencysmc.com";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    Matcher match = SOURCE_PTN.matcher(body);
    if (match.find()) {
      data.strSource = match.group(1).trim();
      body = body.substring(match.end()).trim();
      
      // Most calls with a source prefix do have a well defined structure
      // But if there is only one line, drop out and use the general location logic
      String[] flds = body.split("\n+");
      if (flds.length >= 2) {
        data.strCall = flds[0].trim();
        
        // Determining if line 1 is a place or address is tricky.
        int ndx = 1;
        if (! parseAddressLine(flds[ndx], data, flds.length == 2)) {
            
          ndx = 2;
          if (parseAddressLine(flds[ndx], data, false)) {
            data.strPlace = flds[1].trim();
          } else {
            ndx = 1;
            parseAddressLine(flds[ndx], data, true);
          }
        }
        
        // Everythign following the address is an info
        for ( ndx++ ; ndx < flds.length; ndx++) {
          String fld = flds[ndx];
          if (checkAddress(fld) > 0) {
            data.strCross = append(data.strCross, " & ", fld);
          } else {
            data.strSupp = append(data.strSupp, " / ", fld);
          }
        }
        return true;
      }
    }
    
    // Apply general location logic
    return super.parseMsg(subject, body, data);
  }
  
  
  /**
   * Parse prospective address line
   * @param line line to be parsed
   * @param data parse information data object
   * @param force true if this line has been positively identified as an address
   * @return true if line was parsed as an address, false otherwise
   */
  private boolean parseAddressLine(String line, Data data, boolean force) {
    line = line.trim();
    String info = "";
    Matcher match = ADDRESS_QUAL_PTN.matcher(line);
    if (match.find()) {
      info = line.substring(match.start(1));
      line = line.substring(0,match.start()).trim();
    }
    if (force || checkAddress(line) > 0) {
      parseAddress(line, data);
      data.strSupp = info;
      return true;
    } else {
      return false;
    }
  }
    
    
  
  @Override
  protected int getParseAddressFlags() {
    return FLAG_NO_IMPLIED_APT | FLAG_AT_SIGN_ONLY;
  }
}
