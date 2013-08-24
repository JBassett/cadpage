package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/* 
Caldwell County, NC
Contact: "justinwinebarger" <justinwinebarger@bellsouth.net>,8283127647@vtext.com
Contact: Mark Stallings <stalprint@gmail.com>
Contact: 8288509369@sms.alltelwireless.com
Sender: CAD@caldwellcountync.org
System: Sunguard OSSI

BREATHING PROBLEMS CHARLIE;25 WATER ST;GF;APT 12;CIRCLE ST;NORTH SUMMIT AV
CARDIAC RESP ARREST DEATH ECHO;5105 WENDOVER LN;GF;GUNPOWDER RD
FALL WITH PERSONAL INJURY;2998 CAMPGROUND RD;GF;SHELTON HOLLAR PL;C BELL MCRARY PL;1107150247
FALL WITH PERSONAL INJURY;WALMART GRANITE FALLS;4780 HICKORY BLVD;GF;GLEN RIDGE DR;RIVERBEND DR;1107160106
CARBON MONOXIDE DETECTOR ALARM;398 THOMPSON DR;HUD;SHADY OAK TER;HICKORY BLVD;1107200204
UNKNOWN MEDICAL CODE;1450 SHAIRE CENTER DR;LEN;APT 1;ANDREWS CIR;LOVEJOY ST;1108080045
UNCONSC FAINT NEAR DELTA;9 LIBERTY ST;GF;FALLS AV;1108100046

Contact: Marty Bumgarner <mbumgarner@gcfdnc.com>
ACCIDENT PROPERTY DAMAGE;GRACE CHAPEL RD/WOLFE RD;HICK;1202190163

Agency name: North Catawba Fire-Rescue Department
Location: Lenoir, NC, United States
Contact: Active911
Sender: <CAD@caldwellcountync.org>

FIRE STAND BY;LOVELADY FIRE DEPARTMENT;1305010173
UNCONSC FAINT NEAR CHARLIE;1020 HUDSON CAJAH MOUNTAIN RD;HUD;CAJAH MOUNTAIN RD;PERIWINKLE PL;1305010063
BREATHING PROBLEMS DELTA;2190 DOTTIE DR;LEN;EURO CT;CLARKS CHAPEL RD;1304300320
RESPIRATORY DISTRESS;INGLES MARKET;2630 CONNELLY SPRINGS RD;GF;DIST: 22.17 FT;BATON CHURCH RD;KAYLOR DR;1304300251
FALL WITH PERSONAL INJURY;1541 FAIRWAY ACRES RD;LEN;POST PL;HAYES ST;1304300121
CARDIAC RESP ARREST DEATH ECHO;4493 CROWN CT;LEN;FRANK TR;1304300052
ACCIDENT PERSONAL INJURY;1699 ORCHARD DR;LEN;DIST: 13.01 ft;DALLAS ST;BROOK ST;1304290347
SICK CALL;1450 SHAIRE CENTER DR;LEN;APT 10;ANDREWS CIR;LOVEJOY ST;1304290133
SICK PERSON SPEC DIAG CHARLIE;1074 WINDSONG CIR;HUD;WHEELING MHP DR;HICKORY NUT RIDGE RD;1304290011
BREATHING PROBLEMS CHARLIE;563 PLEASANT HILL RD;LEN;MADISON MHP DR;CARLTON MHP DR;1304280118
SICK PERSON SPEC DIAG CHARLIE;4430 CROWN CT;LEN;FRANK TR;1304280039
POWER LINE DOWN;ORCHARD DR/WALKER CIR;LEN;1304270237
FIRE ALARM;WHITNEL PENTECOSTAL HOLINESS;1890 CONNELLY SPRINGS RD;LEN;CATAWBA TER;COTTAGE GROVE RD;1304270244
POWER LINE DOWN;ORCHARD DR/CONNELLY SPRINGS RD;LEN;1304270237
ATTEMPTED SUICIDE;1588 CATAWBA TER;LEN;CONNELLY SPRINGS RD;1304270221
HEMORRHAGE LACERATIONS DELTA;1812 LEE PEARSON RD;GF;EAGLES NEST LN;PECAN LN;1304270084
PSYCH ABNOR SUICIDE ATTEMP DEL;4449 SUMMER WAY;LEN;NICKIE CT;1304270044
FIRE WOODS GRASS BRUSH;HICKORY NUT RIDGE RD/OGBORN LN;HUD;1304260227
CHASE OR PURSUIT;2711 CLARKS CHAPEL RD;LEN;FAIRBANKS DR;BISHOP DR;1304260191
CONVULSIONS SEIZURES CHARLIE;5069 CARPENTER RD;GF;WRONG WAY;1304250317

*/

public class NCCaldwellCountyParserTest extends BaseParserTest {
  
  public NCCaldwellCountyParserTest() {
    setParser(new NCCaldwellCountyParser(), "CALDWELL COUNTY", "NC");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "BREATHING PROBLEMS CHARLIE;25 WATER ST;GF;APT 12;CIRCLE ST;NORTH SUMMIT AV",
        "CALL:BREATHING PROBLEMS CHARLIE",
        "ADDR:25 WATER ST",
        "APT:12",
        "CITY:GRANITE FALLS",
        "X:CIRCLE ST & NORTH SUMMIT AV");

    doTest("T2",
        "CARDIAC RESP ARREST DEATH ECHO;5105 WENDOVER LN;GF;GUNPOWDER RD",
        "CALL:CARDIAC RESP ARREST DEATH ECHO",
        "ADDR:5105 WENDOVER LN",
        "CITY:GRANITE FALLS",
        "X:GUNPOWDER RD");

    doTest("T3",
        "FALL WITH PERSONAL INJURY;2998 CAMPGROUND RD;GF;SHELTON HOLLAR PL;C BELL MCRARY PL;1107150247",
        "CALL:FALL WITH PERSONAL INJURY",
        "ADDR:2998 CAMPGROUND RD",
        "CITY:GRANITE FALLS",
        "X:SHELTON HOLLAR PL & C BELL MCRARY PL",
        "ID:1107150247");

    doTest("T4",
        "FALL WITH PERSONAL INJURY;WALMART GRANITE FALLS;4780 HICKORY BLVD;GF;GLEN RIDGE DR;RIVERBEND DR;1107160106",
        "CALL:FALL WITH PERSONAL INJURY",
        "PLACE:WALMART GRANITE FALLS",
        "ADDR:4780 HICKORY BLVD",
        "CITY:GRANITE FALLS",
        "X:GLEN RIDGE DR & RIVERBEND DR",
        "ID:1107160106");

    doTest("T5",
        "CARBON MONOXIDE DETECTOR ALARM;398 THOMPSON DR;HUD;SHADY OAK TER;HICKORY BLVD;1107200204",
        "CALL:CARBON MONOXIDE DETECTOR ALARM",
        "ADDR:398 THOMPSON DR",
        "CITY:HUDSON",
        "X:SHADY OAK TER & HICKORY BLVD",
        "ID:1107200204");

    doTest("T6",
        "UNKNOWN MEDICAL CODE;1450 SHAIRE CENTER DR;LEN;APT 1;ANDREWS CIR;LOVEJOY ST;1108080045",
        "CALL:UNKNOWN MEDICAL CODE",
        "ADDR:1450 SHAIRE CENTER DR",
        "APT:1",
        "CITY:LENOIR",
        "X:ANDREWS CIR & LOVEJOY ST",
        "ID:1108080045");

    doTest("T7",
        "UNCONSC FAINT NEAR DELTA;9 LIBERTY ST;GF;FALLS AV;1108100046",
        "CALL:UNCONSC FAINT NEAR DELTA",
        "ADDR:9 LIBERTY ST",
        "CITY:GRANITE FALLS",
        "X:FALLS AV",
        "ID:1108100046");

    doTest("T8",
        "ACCIDENT PROPERTY DAMAGE;GRACE CHAPEL RD/WOLFE RD;HICK;1202190163",
        "CALL:ACCIDENT PROPERTY DAMAGE",
        "ADDR:GRACE CHAPEL RD & WOLFE RD",
        "CITY:HICKORY",
        "ID:1202190163");
  }
  
  @Test
  public void testNorthCatawbaFireRescue() {

    doTest("T1",
        "FIRE STAND BY;LOVELADY FIRE DEPARTMENT;1305010173",
        "CALL:FIRE STAND BY",
        "ADDR:LOVELADY FIRE DEPARTMENT",
        "ID:1305010173");

    doTest("T2",
        "UNCONSC FAINT NEAR CHARLIE;1020 HUDSON CAJAH MOUNTAIN RD;HUD;CAJAH MOUNTAIN RD;PERIWINKLE PL;1305010063",
        "CALL:UNCONSC FAINT NEAR CHARLIE",
        "ADDR:1020 HUDSON CAJAH MOUNTAIN RD",
        "CITY:HUDSON",
        "X:CAJAH MOUNTAIN RD & PERIWINKLE PL",
        "ID:1305010063");

    doTest("T3",
        "BREATHING PROBLEMS DELTA;2190 DOTTIE DR;LEN;EURO CT;CLARKS CHAPEL RD;1304300320",
        "CALL:BREATHING PROBLEMS DELTA",
        "ADDR:2190 DOTTIE DR",
        "CITY:LENOIR",
        "X:EURO CT & CLARKS CHAPEL RD",
        "ID:1304300320");

    doTest("T4",
        "RESPIRATORY DISTRESS;INGLES MARKET;2630 CONNELLY SPRINGS RD;GF;DIST: 22.17 FT;BATON CHURCH RD;KAYLOR DR;1304300251",
        "CALL:RESPIRATORY DISTRESS",
        "PLACE:INGLES MARKET",
        "ADDR:2630 CONNELLY SPRINGS RD",
        "CITY:GRANITE FALLS",
        "X:DIST: 22.17 FT & BATON CHURCH RD & KAYLOR DR",
        "ID:1304300251");

    doTest("T5",
        "FALL WITH PERSONAL INJURY;1541 FAIRWAY ACRES RD;LEN;POST PL;HAYES ST;1304300121",
        "CALL:FALL WITH PERSONAL INJURY",
        "ADDR:1541 FAIRWAY ACRES RD",
        "CITY:LENOIR",
        "X:POST PL & HAYES ST",
        "ID:1304300121");

    doTest("T6",
        "CARDIAC RESP ARREST DEATH ECHO;4493 CROWN CT;LEN;FRANK TR;1304300052",
        "CALL:CARDIAC RESP ARREST DEATH ECHO",
        "ADDR:4493 CROWN CT",
        "CITY:LENOIR",
        "X:FRANK TR",
        "ID:1304300052");

    doTest("T7",
        "ACCIDENT PERSONAL INJURY;1699 ORCHARD DR;LEN;DIST: 13.01 ft;DALLAS ST;BROOK ST;1304290347",
        "CALL:ACCIDENT PERSONAL INJURY",
        "ADDR:1699 ORCHARD DR",
        "CITY:LENOIR",
        "X:DIST: 13.01 ft & DALLAS ST & BROOK ST",
        "ID:1304290347");

    doTest("T8",
        "SICK CALL;1450 SHAIRE CENTER DR;LEN;APT 10;ANDREWS CIR;LOVEJOY ST;1304290133",
        "CALL:SICK CALL",
        "ADDR:1450 SHAIRE CENTER DR",
        "APT:10",
        "CITY:LENOIR",
        "X:ANDREWS CIR & LOVEJOY ST",
        "ID:1304290133");

    doTest("T9",
        "SICK PERSON SPEC DIAG CHARLIE;1074 WINDSONG CIR;HUD;WHEELING MHP DR;HICKORY NUT RIDGE RD;1304290011",
        "CALL:SICK PERSON SPEC DIAG CHARLIE",
        "ADDR:1074 WINDSONG CIR",
        "CITY:HUDSON",
        "X:WHEELING MHP DR & HICKORY NUT RIDGE RD",
        "ID:1304290011");

    doTest("T10",
        "BREATHING PROBLEMS CHARLIE;563 PLEASANT HILL RD;LEN;MADISON MHP DR;CARLTON MHP DR;1304280118",
        "CALL:BREATHING PROBLEMS CHARLIE",
        "ADDR:563 PLEASANT HILL RD",
        "CITY:LENOIR",
        "X:MADISON MHP DR & CARLTON MHP DR",
        "ID:1304280118");

    doTest("T11",
        "SICK PERSON SPEC DIAG CHARLIE;4430 CROWN CT;LEN;FRANK TR;1304280039",
        "CALL:SICK PERSON SPEC DIAG CHARLIE",
        "ADDR:4430 CROWN CT",
        "CITY:LENOIR",
        "X:FRANK TR",
        "ID:1304280039");

    doTest("T12",
        "POWER LINE DOWN;ORCHARD DR/WALKER CIR;LEN;1304270237",
        "CALL:POWER LINE DOWN",
        "ADDR:ORCHARD DR & WALKER CIR",
        "CITY:LENOIR",
        "ID:1304270237");

    doTest("T13",
        "FIRE ALARM;WHITNEL PENTECOSTAL HOLINESS;1890 CONNELLY SPRINGS RD;LEN;CATAWBA TER;COTTAGE GROVE RD;1304270244",
        "CALL:FIRE ALARM",
        "PLACE:WHITNEL PENTECOSTAL HOLINESS",
        "ADDR:1890 CONNELLY SPRINGS RD",
        "CITY:LENOIR",
        "X:CATAWBA TER & COTTAGE GROVE RD",
        "ID:1304270244");

    doTest("T14",
        "POWER LINE DOWN;ORCHARD DR/CONNELLY SPRINGS RD;LEN;1304270237",
        "CALL:POWER LINE DOWN",
        "ADDR:ORCHARD DR & CONNELLY SPRINGS RD",
        "CITY:LENOIR",
        "ID:1304270237");

    doTest("T15",
        "ATTEMPTED SUICIDE;1588 CATAWBA TER;LEN;CONNELLY SPRINGS RD;1304270221",
        "CALL:ATTEMPTED SUICIDE",
        "ADDR:1588 CATAWBA TER",
        "CITY:LENOIR",
        "X:CONNELLY SPRINGS RD",
        "ID:1304270221");

    doTest("T16",
        "HEMORRHAGE LACERATIONS DELTA;1812 LEE PEARSON RD;GF;EAGLES NEST LN;PECAN LN;1304270084",
        "CALL:HEMORRHAGE LACERATIONS DELTA",
        "ADDR:1812 LEE PEARSON RD",
        "CITY:GRANITE FALLS",
        "X:EAGLES NEST LN & PECAN LN",
        "ID:1304270084");

    doTest("T17",
        "PSYCH ABNOR SUICIDE ATTEMP DEL;4449 SUMMER WAY;LEN;NICKIE CT;1304270044",
        "CALL:PSYCH ABNOR SUICIDE ATTEMP DEL",
        "ADDR:4449 SUMMER WAY",
        "CITY:LENOIR",
        "X:NICKIE CT",
        "ID:1304270044");

    doTest("T18",
        "FIRE WOODS GRASS BRUSH;HICKORY NUT RIDGE RD/OGBORN LN;HUD;1304260227",
        "CALL:FIRE WOODS GRASS BRUSH",
        "ADDR:HICKORY NUT RIDGE RD & OGBORN LN",
        "CITY:HUDSON",
        "ID:1304260227");

    doTest("T19",
        "CHASE OR PURSUIT;2711 CLARKS CHAPEL RD;LEN;FAIRBANKS DR;BISHOP DR;1304260191",
        "CALL:CHASE OR PURSUIT",
        "ADDR:2711 CLARKS CHAPEL RD",
        "CITY:LENOIR",
        "X:FAIRBANKS DR & BISHOP DR",
        "ID:1304260191");

    doTest("T20",
        "CONVULSIONS SEIZURES CHARLIE;5069 CARPENTER RD;GF;WRONG WAY;1304250317",
        "CALL:CONVULSIONS SEIZURES CHARLIE",
        "ADDR:5069 CARPENTER RD",
        "CITY:GRANITE FALLS",
        "X:WRONG WAY",
        "ID:1304250317");
   
  }

  public static void main(String[] args) {
    new NCCaldwellCountyParserTest().generateTests("T1");
  }
}
