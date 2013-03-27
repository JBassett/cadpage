
package net.anei.cadpage.parsers.AZ;

import java.util.Properties;

import net.anei.cadpage.parsers.FieldProgramParser;

/**
 * Mesa, AZ (Maricopa County)
 */

public class AZYavapaiCountyParser extends FieldProgramParser {

  public AZYavapaiCountyParser() {
    super(CITY_CODES, "YAVAPAI COUNTY", "AZ",
           "CT:ADDR/SC! BOX:BOX! DUE:UNIT!");
  }
  
  @Override
  public String getFilter() {
    return "@sedonafire.org";
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "AG",   "AGUILA",
      "ASHF", "ASH FORK",
      "BAGD", "BAGDAD",
      "BCC",  "BLACK CANYON CITY",
      "CHNO", "CHINO VALLEY",
      "CHRY", "CHERRY",
      "CKNG", "CROWN KING",
      "CLK",  "CLARKDALE",
      "CLTR", "CLEATOR",
      "CMPW", "CAMP WOOD",
      "CNTY", "COUNTY",
      "CONG", "CONGRESS",
      "CRDJ", "CORDES JUNCTION",
      "CRDS", "CORDES LAKES",
      "CRNV", "CORNVILLE",
      "CSHS", "CASTLE HOT SPRINGS",
      "CTWD", "COTTONWOOD",
      "CV",   "CAMP VERDE",
      "DATE", "DATE CREEK",
      "DEHB", "DEWEY HUMBOLT",
      "DEWY", "DEWEY",
      "DRAK", "DRAKE",
      "FLG",  "FLAGSTAFF",
      "FSER", "FOREST SERVICE",
      "GRMC", "GROOM CREEK",
      "HILL", "HILLSIDE",
      "HPNS", "HPNS",
      "HUMB", "HUMBOLT",
      "IRON", "IRON SPRINGS",
      "JPRW", "JUNIPERWOOD RANCH",
      "JRME", "JEROME",
      "KIRK", "KIRKLAND",
      "LKMT", "LAKE MONTEZUMA",
      "MAYR", "MAYER",
      "MCGV", "MCGUIREVILLE",
      "MHR",  "MAYERHWYRESCUE",
      "MNDP", "MUNDS PARK",
      "PAUL", "PAULDEN",
      "PEEP", "PEEPLES VALLEY",
      "PERK", "PERKINSVILLE",
      "POND", "PONDEROSA PARK",
      "PRES", "PRESCOTT",
      "PRV",  "PRESCOTT VALLEY",
      "PWD",  "PINEWOOD",
      "RIMR", "RIMROCK",
      "SED",  "SEDONA",
      "SIM",  "SIMMONS",
      "SKLV", "SKULL VALLEY",
      "SLGM", "SELIGMAN",
      "STAN", "STANTON",
      "SV",   "SPRING VALLEY",
      "VV",   "VERDE VALLEY",
      "WAG",  "WAGONER",
      "WCHI", "WEST CHINO",
      "WILV", "WILLIAMSON VALLEY",
      "WKBG", "WICKENBURG",
      "WKR",  "WALKER",
      "WLHT", "WILHOIT",
      "WNTG", "WALNUT GROVE",
      "YAN",  "YAVAPAI INDIAN RESERVATION",
      "YARN", "YARNELL",
      "YAVA", "YAVA",
      "YAVC", "YAVAPAI COUNTY",

  });
}
