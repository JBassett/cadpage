package net.anei.cadpage.parsers.VA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA28Parser;



public class VAMecklenburgCountyParser extends DispatchA28Parser {
  
  public VAMecklenburgCountyParser() {
    super(CITY_LIST, "MECKLENBURG COUNTY", "VA");
  }
  
  @Override
  public String getFilter() {
    return "swpage2@vameck911.com";
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("MECK 911")) return false;
    if (!super.parseMsg(body, data)) return false;
    if (data.strCity.equals("MECK CO")) data.strCity = "MECKLENBURG COUNTY";
    else if (data.strCity.equals("BRUN CO")) data.strCity = "BRUNSWICK COUNTY";
    if (data.strCity.endsWith(" COUNTY") && isCity(data.strPlace)) {
      data.strCity = data.strPlace;
      data.strPlace = "";
    }
    if (data.strCity.equals("LACROSSE")) data.strCity = "LA CROSSE";
    
    // Only dispatch center we know that spells out highway numbers :(
    data.strAddress = fixHwyNumbers(data.strAddress);
    data.strCross = fixHwyNumbers(data.strCross);
    return true;
  }
  
  private String fixHwyNumbers(String address) {
    address = HWY_1_PTN.matcher(address).replaceAll("1");
    address = HWY_15_PTN.matcher(address).replaceAll("15");
    address = HWY_47_PTN.matcher(address).replaceAll("47");
    address = HWY_49_PTN.matcher(address).replaceAll("49");
    address = HWY_58_PTN.matcher(address).replaceAll("58");
    address = HWY_85_PTN.matcher(address).replaceAll("85");
    address = HWY_92_PTN.matcher(address).replaceAll("92");
    
    Matcher match = MULT_DIGIT_PTN.matcher(address);
    if (match.find()) {
      StringBuffer sb = new StringBuffer();
      do {
        String replace = match.group();
        try {
          StringBuilder sb2 = new StringBuilder();
          for (String digit : replace.split("-")) {
            int dig = DIGITS.valueOf(digit).ordinal();
            sb2.append((char)(dig + '0'));
          }
          replace = sb2.toString();
        } catch (IllegalArgumentException ex) {}
        match.appendReplacement(sb, replace);
      } while (match.find());
      match.appendTail(sb);
      address = sb.toString();
    }
    return address;
  }
  private static final Pattern HWY_1_PTN = Pattern.compile("\\bONE\\b");
  private static final Pattern HWY_15_PTN = Pattern.compile("\\bFIFTEEN\\b");
  private static final Pattern HWY_47_PTN = Pattern.compile("\\bFORTY-SEVEN\\b"); 
  private static final Pattern HWY_49_PTN = Pattern.compile("\\bFORTY-NINE\\b"); 
  private static final Pattern HWY_58_PTN = Pattern.compile("\\bFIFTY-EIGHT\\b"); 
  private static final Pattern HWY_85_PTN = Pattern.compile("\\bEIGHTY-FIVE\\b"); 
  private static final Pattern HWY_92_PTN = Pattern.compile("\\bNINETY-TWO\\b"); 
  private static final Pattern MULT_DIGIT_PTN = Pattern.compile("\\b[A-Z]+(?:-[A-Z]+){2,3}");
  
  private enum DIGITS {O, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};
  
  private static final String[] CITY_LIST = new String[]{
      
      // Cities
      "CHASE CITY",

      //Towns

      "BOYDTON",
      "BRACEY",
      "BRODNAX",
      "CLARKSVILLE",
      "LA CROSSE",
      "LACROSSE",
      "SKIPWITH",
      "SOUTH HILL",

      // Unincorporated[edit]

      "BASKERVILLE",
      "BUFFALO JUNCTION",
      "NELSON",
      "SHINY ROCK",

      // County
      "MECK CO",
      "BRUN CO"
      
  };
}
