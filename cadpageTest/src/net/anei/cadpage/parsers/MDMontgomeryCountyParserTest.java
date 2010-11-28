package net.anei.cadpage.parsers;

import org.junit.Test;

/*
 * Contact: Alain Pankopf <hockeylaxboy11@gmail.com>
Sender: rc.355@c-msg.net 
4 digit code is a box code
Town codes are indecipherable, drop them

MCo / [mCAD] * D * 2301 * BUILDING FIRE * 1620 E JEFFERSON ST ,RO * E723 E703 E721 E726 E705B AT723 AT703 RS703 A723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT
MCo / [mCAD] * D * 0302 * BUILDING FIRE * 733 MONROE ST ,RO * E723 E733 E726 E725 E721 AT703 AT723 RS741B M723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT
MCo / [mCAD] * D * 0301 * HOUSE FIRE * 1006 DE BECK DR ,RO * E703 E723 E721 E725 E733 AT703 T731 RS703 A703 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT
MCo / [mCAD] * D * 2312 * UNCONFIRMED POWDER * 5700 BOU AVE ,MCG (TARGET STORE) * E723 AT723 A723 M703 HM707 E720 D3 BC703 DFRS BCNOT HIRT ECC
MCo / [mCAD] * D * 0801 * BOX ALARM * 17211 KING JAMES WAY ,MCG * E728 E731 E703 T731 RS703 A708C BC705 BC703 D8 D3 DFRS INV CEALRM CODE BCNOT
MCo / [mCAD] * D * 0503 * HOUSE FIRE * 4805 FLANDERS AVE ,MCG * E705B E723 E720 E750 E726 AT723 AT751 RS742 A705B BC704 BC703 D5 D3 DFRS INV CEALRM CODE BCNOT
MCo / [mCAD] * D * 0318 * STABBING - ALS1 * 9300 KEY WEST AVE / 15200 SHADY GROVE RD ,RO * M703 BCNOT EMSNOT E703 D3 ECC25 DFRS
MCo / [mCAD] * D * 0301 * PIC w/ ENTRAPMENT -ALS1 * 1180 EDMONSTON DR / 1100 WADE AVE ,RO * M703 RS703 E703 D3 EMS EMSNOT BCNOT DFRS
 */
public class MDMontgomeryCountyParserTest extends BaseParserTest {

  public MDMontgomeryCountyParserTest() {
    setParser(new MDMontgomeryCountyParser(), "MONTGOMERY", "MD");
  }
  
  @Test
  public void testParser() {
    
    doTest("T1",
    		"MCo / [mCAD] * D * 2301 * BUILDING FIRE * 1620 E JEFFERSON ST ,RO * E723 E703 E721 E726 E705B AT723 AT703 RS703 A723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT",
    		"CALL:BUILDING FIRE",
    		"ADDR:1620 E JEFFERSON ST",
    		"UNIT:E723 E703 E721 E726 E705B AT723 AT703 RS703 A723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT"
    		);
    doTest("T2",
    		"MCo / [mCAD] * D * 0302 * BUILDING FIRE * 733 MONROE ST ,RO * E723 E733 E726 E725 E721 AT703 AT723 RS741B M723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT",
    		"CALL:BUILDING FIRE",
    		"ADDR:733 MONROE ST",
    		"UNIT:E723 E733 E726 E725 E721 AT703 AT723 RS741B M723 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT"
    		);
    doTest("T3",
    		"MCo / [mCAD] * D * 0301 * HOUSE FIRE * 1006 DE BECK DR ,RO * E703 E723 E721 E725 E733 AT703 T731 RS703 A703 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT",
    		"CALL:HOUSE FIRE",
    		"ADDR:1006 DE BECK DR",
    		"UNIT:E703 E723 E721 E725 E733 AT703 T731 RS703 A703 BC703 BC704 D3 D5 DFRS INV CEALRM CODE BCNOT"
    		);
    doTest("T4",
    		"MCo / [mCAD] * D * 2312 * UNCONFIRMED POWDER * 5700 BOU AVE ,MCG (TARGET STORE) * E723 AT723 A723 M703 HM707 E720 D3 BC703 DFRS BCNOT HIRT ECC",
    		"CALL:UNCONFIRMED POWDER",
    		"ADDR:5700 BOU AVE",
    		"UNIT:E723 AT723 A723 M703 HM707 E720 D3 BC703 DFRS BCNOT HIRT ECC",
    		"INFO:TARGET STORE"
    		);
    doTest("T5",
    		"MCo / [mCAD] * D * 0801 * BOX ALARM * 17211 KING JAMES WAY ,MCG * E728 E731 E703 T731 RS703 A708C BC705 BC703 D8 D3 DFRS INV CEALRM CODE BCNOT",
    		"CALL:BOX ALARM",
    		"ADDR:17211 KING JAMES WAY",
    		"UNIT:E728 E731 E703 T731 RS703 A708C BC705 BC703 D8 D3 DFRS INV CEALRM CODE BCNOT"
    		);
    		
  }
}
