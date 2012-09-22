package net.anei.cadpage.parsers.KY;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Oldham County, KY
Contact: Michael Thompson <Michael.Thompson@ballardsvillefire.com>
Sender: dispatch@oldhamcountyky.gov
Sender: textmsg@ballardsvillefire.com

[081890]- NATURE: FIRE,ILLEGAL BURNING LOCATION: 2015 CLARKE POINTE DR LAGRANGE BETWEEN QUARRY CT / LAKE RIDGE WAY ACC ESS BY S HWY 53
[239952]- NATURE: EMS CALL LOCATION: 5325 BROOKSWOOD RD CRES TWOOD BETWEEN BROOKSWOOD CT / BROOKSWOOD LOOP RD ACCESS BY WOODSBORO RD COMMENTS: 78 YOM
[133685]- NATURE: FIRE,STRUCTURE LOCATION: 3001 W HWY 146 LA GRANGE BETWEEN KINGS LN / PARKER (KSR ENTRANCE) DR COMMENTS : HEAVY SMOKE IN DORM ONE
[072502]- NATURE: FIRE,ALARM RESIDENTIAL LOCATION: 6908 COLT ON RD CRESTWOOD BETWEEN COLTON CT / DEAD END ACCESS BY CRO SS RUN DR COMMENTS: FIRE ALARM
[106873]- NATURE: FIRE,STRUCTURE LOCATION: 1025 NEW MOODY LN LAGRANGE BETWEEN EDEN PKWY / SANIBEL WAY ACCESS BY S 1ST
[031438]- NATURE: EMS CALL LOCATION: 3016 ANN TRESE CV LAGRA NGE BETWEEN DEAD END / S HWY 53 ACCESS BY E HWY 22 OR S HW Y 53 COMMENTS: 55YOM ACTIVE SZ

Contact: Active911
Agency name: Ballardsville Fire Rescue Location: Crestwood, KY 
[133685]- NATURE: EMS CALL LOCATION: 1202 WEIBLE RD CRESTWOO D BETWEEN PAYTON LN / DEAD END ACCESS BY PAYTON LN COMMENT S: 74 YOM NUMBNESS AND TINGLING ON RIGHT SIDE

 */


public class KYOldhamCountyParserTest extends BaseParserTest {
  
  public KYOldhamCountyParserTest() {
    setParser(new KYOldhamCountyParser(), "OLDHAM COUNTY", "KY");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "[081890]- NATURE: FIRE,ILLEGAL BURNING LOCATION: 2015 CLARKE POINTE DR LAGRANGE BETWEEN QUARRY CT / LAKE RIDGE WAY ACC ESS BY S HWY 53",
        "CALL:FIRE,ILLEGAL BURNING",
        "ADDR:2015 CLARKEPOINTE DR",  // Not mapping
        "CITY:LA GRANGE",
        "X:QUARRY CT / LAKE RIDGE WAY ACC ESS BY S HWY 53");

    doTest("T2",
        "[239952]- NATURE: EMS CALL LOCATION: 5325 BROOKSWOOD RD CRES TWOOD BETWEEN BROOKSWOOD CT / BROOKSWOOD LOOP RD ACCESS BY WOODSBORO RD COMMENTS: 78 YOM",
        "CALL:EMS CALL",
        "ADDR:5325 BROOKSWOOD RD",
        "CITY:CRESTWOOD",
        "X:BROOKSWOOD CT / BROOKSWOOD LOOP RD ACCESS BY WOODSBORO RD",
        "INFO:78 YOM");

    doTest("T3",
        "[133685]- NATURE: FIRE,STRUCTURE LOCATION: 3001 W HWY 146 LA GRANGE BETWEEN KINGS LN / PARKER (KSR ENTRANCE) DR COMMENTS : HEAVY SMOKE IN DORM ONE",
        "CALL:FIRE,STRUCTURE",
        "ADDR:3001 W HWY 146",
        "CITY:LA GRANGE",
        "X:KINGS LN / PARKER (KSR ENTRANCE) DR",
        "INFO:HEAVY SMOKE IN DORM ONE");

    doTest("T4",
        "[072502]- NATURE: FIRE,ALARM RESIDENTIAL LOCATION: 6908 COLT ON RD CRESTWOOD BETWEEN COLTON CT / DEAD END ACCESS BY CRO SS RUN DR COMMENTS: FIRE ALARM",
        "CALL:FIRE,ALARM RESIDENTIAL",
        "ADDR:6908 COLTON RD",
        "CITY:CRESTWOOD",
        "X:COLTON CT / DEAD END ACCESS BY CRO SS RUN DR",
        "INFO:FIRE ALARM");

    doTest("T5",
        "[106873]- NATURE: FIRE,STRUCTURE LOCATION: 1025 NEW MOODY LN LAGRANGE BETWEEN EDEN PKWY / SANIBEL WAY ACCESS BY S 1ST",
        "CALL:FIRE,STRUCTURE",
        "ADDR:1025 NEW MOODY LN",
        "CITY:LA GRANGE",
        "X:EDEN PKWY / SANIBEL WAY ACCESS BY S 1ST");

    doTest("T6",
        "[031438]- NATURE: EMS CALL LOCATION: 3016 ANN TRESE CV LAGRA NGE BETWEEN DEAD END / S HWY 53 ACCESS BY E HWY 22 OR S HW Y 53 COMMENTS: 55YOM ACTIVE SZ",
        "CALL:EMS CALL",
        "ADDR:3016 ANN TRESE CV",
        "CITY:LA GRANGE",
        "X:DEAD END / S HWY 53 ACCESS BY E HWY 22 OR S HW Y 53",
        "INFO:55YOM ACTIVE SZ");
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[133685]- NATURE: EMS CALL LOCATION: 1202 WEIBLE RD CRESTWOO D BETWEEN PAYTON LN / DEAD END ACCESS BY PAYTON LN COMMENT S: 74 YOM NUMBNESS AND TINGLING ON RIGHT SIDE",
        "CALL:EMS CALL",
        "ADDR:1202 WEIBLE RD",
        "CITY:CRESTWOOD",
        "X:PAYTON LN / DEAD END ACCESS BY PAYTON LN COMMENT S: 74 YOM NUMBNESS AND TINGLING ON RIGHT SIDE");

  }
  
  public static void main(String[] args) {
    new KYOldhamCountyParserTest().generateTests("T1");
  }
}