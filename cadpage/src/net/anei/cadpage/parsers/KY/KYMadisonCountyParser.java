package net.anei.cadpage.parsers.KY;

import java.util.Properties;

import net.anei.cadpage.parsers.dispatch.DispatchA27Parser;

/**
 * Madison County, KY
 */
public class KYMadisonCountyParser extends DispatchA27Parser {
  
  public KYMadisonCountyParser() {
    super("MADISON COUNTY", "KY", "[A-Z]{1,4}\\d*");
    setupGpsLookupTable(GPS_LOOKUP_TABLE);
  }
  
  @Override
  public String getFilter() {
    return "noreply@cisusa.org,noreply@cisusa.org";
  }
  
  // This doesn't do anything yet.  Hopeing for clarification from Active911 on how table is 
  // supposed to be used.
  private static final Properties GPS_LOOKUP_TABLE = buildCodeTable(new String[]{
      "65", "37.42060,-84.34230",
      "66", "37.43402,-84.34243",
      "67", "37.44785,-84.33669",
      "68", "37.46223,-84.33548",
      "69", "37.47657,-84.33357",
      "70", "37.49093,-84.32986",
      "71", "37.50488,-84.32529",
      "72", "37.51852,-84.31963",
      "73", "37.53268,-84.32063",
      "74", "37.54657,-84.31758",
      "75", "37.56108,-84.31574",
      "76", "37.57558,-84.31394",
      "77", "37.59017,-84.31381",
      "78", "37.60477,-84.31442",
      "79", "37.61940,-84.31493",
      "80", "37.63372,-84.31632",
      "81", "37.64805,-84.31364",
      "82", "37.66238,-84.31090",
      "83", "37.67685,-84.31217",
      "84", "37.69108,-84.31517",
      "85", "37.70532,-84.31888",
      "86", "37.71963,-84.32160",
      "87", "37.73413,-84.32363",
      "88", "37.74835,-84.32113",
      "89", "37.76260,-84.31792",
      "90", "37.77690,-84.31634",
      "91", "37.79048,-84.32275",
      "92", "37.80483,-84.32443",
      "93", "37.81917,-84.32380",
      "94", "37.83383,-84.32305",
      "95", "37.84720,-84.32923",
      "96", "37.86135,-84.33220",
      "97", "37.87580,-84.33322",
      "98", "37.88616,-84.34257",
      "99", "37.89678,-84.35446",
      "100", "37.91025,-84.36138",
      "101", "37.92318,-84.37008",
      "102", "37.93628,-84.37782",
      "103", "37.94960,-84.38571",
      "104", "37.96370,-84.38873"
  });
 
}
