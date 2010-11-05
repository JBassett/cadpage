package net.anei.cadpage.parsers;

import net.anei.cadpage.SmsMsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser.StartType;

import org.junit.Test;


public class SmartAddressParserTest extends BaseParserTest {
  
  private static final StartType ADDR = StartType.START_ADDR;
  private static final StartType CALL = StartType.START_CALL;
  private static final StartType PLACE = StartType.START_PLACE;
  private static final StartType SKIP = StartType.START_SKIP;
  
  private static final String[] CITY_LIST = new String[]{"KENSBURG"};

  private TestParser parser;
  
  public SmartAddressParserTest() {
    parser = new TestParser(CITY_LIST);
    setParser(parser, "", "");
  }
  
  @Test
  public void testTrivialAddress() {
    doTest(ADDR, "MY HOME IN KENSBURG LOOKS NICE", 
        "ADDR:MY HOME IN", 
        "CITY:KENSBURG");
  }
  
  @Test
  public void testSimpleAddress() {
    doTest(ADDR, "1000 N JACK DANIEL LN MORE JUNK",
        "ADDR:1000 N JACK DANIEL LN");
    doTest(ADDR, "1000 HWY 20 MORE JUNK",
       "ADDR:1000 HWY 20");
    doTest(CALL, "BAD BLOOD HAPPENS 1000 N JACK DANIEL LN MORE JUNK",
        "CALL:BAD BLOOD HAPPENS",
        "ADDR:1000 N JACK DANIEL LN");
    doTest(PLACE, "MARY AND ST JOHNS 1000 N JACK DANIEL LN MORE JUNK",
        "PLACE:MARY AND ST JOHNS",
        "ADDR:1000 N JACK DANIEL LN");
    doTest(SKIP, "MARY AND ST JOHNS 1000 N JACK DANIEL LN MORE JUNK",
        "ADDR:1000 N JACK DANIEL LN");
    doTest(SKIP, "SOME DAY OVER THE RAINBOW 100 BLUEBIRDS SING IN KENSBURG HIGH",
        "ADDR:100 BLUEBIRDS SING IN",
        "CITY:KENSBURG");
  }
  
  @Test
  public void testCrossStreets() {
    doTest(ADDR, "1000 SUNBURY ONCE XS: ACROSS TOWN KENSBURG JUNK",
        "ADDR:1000 SUNBURY ONCE",
        "X:ACROSS TOWN",
        "CITY:KENSBURG");
    doTest(SKIP, "WAIT TILL 500 NORTH LN X: JACKSON ST BELOW SUNDANCE",
        "ADDR:500 NORTH LN",
        "X:JACKSON ST");
    doTest(SKIP, "WAIT TILL 500 NORTH LN X: JACKSON ST & N JOHNSTON RD BELOW SUNDANCE",
        "ADDR:500 NORTH LN",
        "X:JACKSON ST & N JOHNSTON RD");
    doTest(SKIP, "WAIT TILL 500 NORTH LN X: JACKSON ST & N SWEET HILL RD BELOW SUNDANCE",
        "ADDR:500 NORTH LN",
        "X:JACKSON ST & N SWEET HILL RD");
    doTest(SKIP, "WAIT TILL 500 NORTH LN X: JACKSON ST & BAD SWEET HILL RD BELOW SUNDANCE",
        "ADDR:500 NORTH LN",
        "X:JACKSON ST");
  }
  
  @Test
  public void testIntersections() {
    doTest(ADDR, "BLACK ST & WEST HILLS RD AFTER HOURS",
        "ADDR:BLACK ST & WEST HILLS RD");
    doTest(SKIP, "CAN WE FIND HWY 20 AND WEST HILLS RD KENSBURG BLACK",
        "ADDR:HWY 20 & WEST HILLS RD",
        "CITY:KENSBURG");
    doTest(SKIP, "CAN WE FIND W HILLS RD AND HWY 20 KENSBURG",
        "ADDR:W HILLS RD & HWY 20",
        "CITY:KENSBURG");
    doTest(SKIP, "NOW SEE IF CAN WE FIND HWY 20 AND WEST HILLS RD WITHOUT A CITY",
        "ADDR:HWY 20 & WEST HILLS RD");
    doTest(SKIP, "NOW SEE IF CAN WE FIND W HILLS RD/HWY 20 WITHOUT A CITY",
        "ADDR:W HILLS RD & HWY 20");
  }
  
  @Test
  public void testNakedRoad() {
    doTest(ADDR, "BLAKSLY CREEK RD IN CHICAGO",
    		"ADDR:BLAKSLY CREEK RD");
    doTest(SKIP, "WHERE CAN I FIND BLAKSLY CREEK RD IN CHICAGO",
        "ADDR:CREEK RD");
    doTest(SKIP, "WHERE CAN I FIND N BLAKSLY CREEK RD IN CHICAGO",
        "ADDR:N BLAKSLY CREEK RD");
  }
  
  private void doTest(StartType sType, String test, String ... result) {
    parser.setStartType(sType);
    doTest("", test, result);
  }
  
  private static class TestParser extends SmartAddressParser {
    
    public TestParser(String[] cities) {
      super(cities);
    }
    
    private StartType startType = null;
    
    public void setStartType(StartType startType) {
      this.startType = startType;
    }

    @Override
    public boolean isPageMsg(String msgText) {
      return true;
    }

    @Override
    protected void parse(String message, Data data) {
      parseAddress(startType, message, data);
    }
  }

}
