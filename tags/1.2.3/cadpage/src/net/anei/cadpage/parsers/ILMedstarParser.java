package net.anei.cadpage.parsers;

import java.util.Properties;
import java.util.regex.Pattern;

import net.anei.cadpage.SmsMsgInfo.Data;

/*
Medstar IL (Medical only in Randolf, Clinton, and St Clair counties)
Contact: l.keirstead@yahoo.com,6154407605@vzwpix.com
Sender: emsdispatch@medstarems.net
System: ProQA
[- part 1 of 1]  RC:Run# 34870/64 WEST BOUND//across from weight station/////male subj white car hit by a truck isp en route/<PROQA_DET>
[- part 1 of 1]  RC:Run# 34859/505 LEMANS WAY///SCC PG 15/FAIRVIEW HEIGHTS///55yof unc diff b possible overdose on pills - adavan tramadol/<PROQA_DET>
[- part 1 of 1]  RC:Run# 34577/20 KINDER ST//cah/SCC PG 11/EAST SAINT LOUIS///31f c/b chest and back px cardiac hx  ProQA comments: chest px/10D04
[- part 1 of 1]  RC:Run# 34384/1308 CORLISS//westinghouse and n greenmount//SHILO///38m c/b rapid heart rate/<PROQA_DET>
[- part 1 of 1]  RC:Run# 34620/13 LOISEL DR///SCC PG 14/EAST SAINT LOUIS///70 M C/B CONFUSED ProQA comments: UNK/31D03
 */


public class ILMedstarParser extends DispatchProQAParser {
  
  private static final Properties COUNTY_CODES = buildCodeTable(new String[]{
      "SCC", "ST CLAIR COUNTY"
  });
  
  private static final Pattern CITY_PAT = Pattern.compile("[ A-Z]+");
      
  
  public ILMedstarParser() {
    super("IL", "");
  }
  
  @Override
  public String getFilter() {
    return "emsdispatch@medstarems.net";
  }
  @Override
  protected boolean parseFields(String[] fields, Data data) {
    
    // First line is always the address
    int ndx = 1;
    for (String fld : fields) {
      switch (ndx) {
      
      // Line 1 is always the address
      case 1:
        parseAddress(fld, data);
        ndx++;
        break;
        
      // After that things get complicated  
      case 2:
        
        // Short 3 character fields are asssumed to be dispatcher initials
        // and ignored
        if (fld.length() <= 3) break;
        
        // Anything with a ' PG ' in it should be a county code and map page number
        int pt = fld.indexOf(" PG ");
        if (pt >= 0) {
          data.strCity = convertCodes(fld.substring(0, pt).trim(), COUNTY_CODES);
          data.strMap = fld.substring(pt+4).trim();
          break;
        }
        
        // Anything consisting of blanks and upper case letters is assumed to be a city
        // Any regular text lines found up to know become cross streets
        if (CITY_PAT.matcher(fld).matches()) {
          data.strCity = fld;
          data.strCross = data.strCall;
          data.strCall = "";
          ndx++;
          break;
        }
        
        // Otherwise fall through case and treat as call text
      case 3:
        if (fld.equals("<PROQA_DET>")) break;
        if (data.strCall.length() > 0) data.strCall += " / ";
        data.strCall += fld;
        break;
      }
    }
    return true;
  }
}
