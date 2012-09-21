package net.anei.cadpage.parsers.MN;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class MNHennepinCountyParserTest extends BaseParserTest {
  
  public MNHennepinCountyParserTest() {
    setParser(new MNHennepinCountyParser(), "HENNEPIN COUNTY", "MN");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "NAME-KENDRA;  LOC-4001 STINSON BLVD NE [#404;  EVTYPE-STROKE;  COMMENTS-POSSIBLE STROKE --- USE MAIN ENTRANCE TO THE 4TH FL",
        "NAME:KENDRA",
        "ADDR:4001 STINSON BLVD NE",
        "APT:404",
        "CALL:STROKE",
        "INFO:POSSIBLE STROKE --- USE MAIN ENTRANCE TO THE 4TH FL");

    doTest("T2",
        "NAME-BRINATTE,HELEN;  LOC-2626 KENZIE TER [#114 [@WALKER ON KENZIE APT;  EVTYPE-ILL;  COMMENTS-2626 KENZIE TER : cross stre",
        "NAME:BRINATTE,HELEN",
        "ADDR:2626 KENZIE TER",
        "APT:114",
        "PLACE:WALKER ON KENZIE APT",
        "CALL:ILL");

    doTest("T3",
        "NAME-;  LOC-2600 39TH AVE NE [#100 [@SILVER LAKE CLINIC;  EVTYPE-BREATH;  COMMENTS-2600 39TH AVE NE : cross streets : SILVE",
        "ADDR:2600 39TH AVE NE",
        "APT:100",
        "PLACE:SILVER LAKE CLINIC",
        "CALL:BREATH",
        "X:SILVE");

    doTest("T4",
        "NAME-MRS SILGE;  LOC-3804 HIGHCREST RD [#109 [@LAKEHILL APT;  EVTYPE-ASTFIR;  COMMENTS-3804 HIGHCREST RD : cross streets :",
        "ADDR:3804 HIGHCREST RD",
        "NAME:MRS SILGE",
        "APT:109",
        "PLACE:LAKEHILL APT",
        "CALL:ASTFIR");

    doTest("T5",
        "NAME-areson, dale;  LOC-2512 SILVER LA [#204 [@EQUINOX APT;  EVTYPE-FALL;  COMMENTS-2512 SILVER LA : cross streets : SILVER",
        "NAME:areson, dale",
        "ADDR:2512 SILVER LA",
        "MADDR:2512 SILVER LN",
        "APT:204",
        "PLACE:EQUINOX APT",
        "CALL:FALL",
        "X:SILVER");

    doTest("T6",
        "NAME-SARAH;  LOC-3701 CHANDLER DR [#312 [@CHANDLER PLACE APT;  EVTYPE-ILL;  COMMENTS-3701 CHANDLER DR : cross streets : DIA",
        "NAME:SARAH",
        "ADDR:3701 CHANDLER DR",
        "APT:312",
        "PLACE:CHANDLER PLACE APT",
        "CALL:ILL",
        "X:DIA");

    doTest("T7",
        "NAME-;  LOC-3700 FOSS RD [#152;  EVTYPE-ILL;  COMMENTS-3700 FOSS RD : cross streets : CHANDLER DR  75 YO FEMALE - ILL",
        "ADDR:3700 FOSS RD",
        "APT:152",
        "CALL:ILL",
        "INFO:75 YO FEMALE - ILL",
        "X:CHANDLER DR");

    doTest("T8",
        "NAME-STANLEY SEC;  LOC-2804 29TH AVE NE;  EVTYPE-ALMHSE;  COMMENTS-2804 29TH AVE NE: cross streets : COOLIDGE ST NE & WILSO",
        "NAME:STANLEY SEC",
        "ADDR:2804 29TH AVE NE",
        "CALL:ALMHSE",
        "X:COOLIDGE ST NE & WILSO");

    doTest("T9",
        "NAME-DIANE;  LOC-2600 KENZIE TER [@AUTUMN WOODS APT#208;  EVTYPE-FALL;  COMMENTS-2600 KENZIE TER : cross streets : LOWRY GR",
        "NAME:DIANE",
        "ADDR:2600 KENZIE TER",
        "PLACE:AUTUMN WOODS APT#208",
        "CALL:FALL",
        "X:LOWRY GR");

    doTest("T10",
        "NAME-ERIC;  LOC-3201 DIAMOND EIGHT TER [#106 [@DIAMOND EIGHT TERRACE APT;  EVTYPE-CHOKE;  COMMENTS-3201 DIAMOND EIGHT TER :",
        "NAME:ERIC",
        "ADDR:3201 DIAMOND EIGHT TER",
        "APT:106",
        "PLACE:DIAMOND EIGHT TERRACE APT",
        "CALL:CHOKE");

    doTest("T11",
        "NAME-;  LOC-3114 RANKIN RD;  EVTYPE-ALLERG;  COMMENTS-3114 RANKIN RD : cross streets : 32ND AVE NE & TOWNVIEW AVE  thinks s",
        "ADDR:3114 RANKIN RD",
        "CALL:ALLERG",
        "INFO:thinks s",
        "X:32ND AVE NE & TOWNVIEW AVE");

    doTest("T12",
        "NAME-PEKAREK;  LOC-3008 RANKIN RD;  EVTYPE-HEART;  COMMENTS-3008 RANKIN RD : cross streets : EAST GATE RD & CROFT DR  43 YO",
        "NAME:PEKAREK",
        "ADDR:3008 RANKIN RD",
        "CALL:HEART",
        "INFO:43 YO",
        "X:EAST GATE RD & CROFT DR");
         
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[] NAME-SIMPLEX;  LOC-8201 45TH AVE N [@NORTH PARK PLAZA APT;  EVTYPE-ALMAPT;  COMMENTS-8201 45TH AVE N : cross streets : WINNETKA AVE N & XYLON AVE N\r\n" +
        "SMOKE DETECTOR BY 313 IN THE CORADOR,  763 535 6794\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:SIMPLEX",
        "ADDR:8201 45TH AVE N",
        "PLACE:NORTH PARK PLAZA APT",
        "CALL:ALMAPT",
        "INFO:SMOKE DETECTOR BY 313 IN THE CORADOR,  763 535 6794",
        "X:WINNETKA AVE N & XYLON AVE N");

    doTest("T2",
        "[] NAME-SHEILA NESS;  LOC-8000 BASS LAKE RD [@ST THERESE NURSING HOME;  EVTYPE-HEART;  COMMENTS-8000 BASS LAKE RD : cross streets : WINNETKA AVE N & WISCONSIN AVE N\r\n" +
        "EMPLOYEE ...3 EAST CHEST PAIN AND NUMBNESS IN HER LEFT ARM\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:SHEILA NESS",
        "ADDR:8000 BASS LAKE RD",
        "PLACE:ST THERESE NURSING HOME",
        "CALL:HEART",
        "INFO:EMPLOYEE ...3 EAST CHEST PAIN AND NUMBNESS IN HER LEFT ARM",
        "X:WINNETKA AVE N & WISCONSIN AVE N");

    doTest("T3",
        "[] NAME-ANON;  LOC-6608 CORVALLIS AVE N;  EVTYPE-CKBURN;  COMMENTS-6608 CORVALLIS AVE N: cross streets : FLORIDA AVE N & HAMPSHIRE AVE N\r\n" +
        "BACK YARD FIRE PIT HAS BEEN UNATTENDED AND BURNING SINCE 10 LAST NIGHT,\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:ANON",
        "ADDR:6608 CORVALLIS AVE N",
        "CALL:CKBURN",
        "INFO:BACK YARD FIRE PIT HAS BEEN UNATTENDED AND BURNING SINCE 10 LAST NIGHT,",
        "X:FLORIDA AVE N & HAMPSHIRE AVE N");

    doTest("T4",
        "[] NAME-cindy cummings;  LOC-6017 QUEBEC AVE N;  EVTYPE-CKBURN;  COMMENTS-6017 QUEBEC AVE N: cross streets : 61ST AVE N & 60TH AVE N\r\n" +
        "check on the res in the wht house behind this rps res because she is burning garbage\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:cindy cummings",
        "ADDR:6017 QUEBEC AVE N",
        "CALL:CKBURN",
        "INFO:check on the res in the wht house behind this rps res because she is burning garbage",
        "X:61ST AVE N & 60TH AVE N");

    doTest("T5",
        "[] NAME-REBECCA;  LOC-5500 BOONE AVE N [@NORTH RIDGE APT [@NORTH RIDGE CARE CENTER 3;  EVTYPE-HEART;  COMMENTS-5500 BOONE AVE N : cross streets : RESEARCH CENTER RD E & 54TH AVE N\r\n" +
        "IN THE LOBBY .....PARTY WITH IRREGULAR HEART BEAT...RP IS NOT WITH PARTY CURRENTLY\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:REBECCA",
        "ADDR:5500 BOONE AVE N",
        "PLACE:NORTH RIDGE APT PLACE:NORTH RIDGE CARE CENTER 3",
        "CALL:HEART",
        "INFO:IN THE LOBBY .....PARTY WITH IRREGULAR HEART BEAT...RP IS NOT WITH PARTY CURRENTLY",
        "X:RESEARCH CENTER RD E & 54TH AVE N");

    doTest("T6",
        "[] NAME-EMERGENCY POLICE LINE;  LOC-RAILROAD TRKS & WEST BROADWAY;  EVTYPE-INFO;  COMMENTS-TRAIN BLOCKING CROSSING FOR MOST OF MORNING.....\r\n" +
        "WEST BROADWAY, DOUGLAS DR, AND BOONE AV, WINNETKA AV,\r\n" +
        "WORK TRAIN ON TRACKS AND BLOCKING STREETS MOST OF THE MORNING.....\r\n" +
        "IF ACCESS IS NEEDED CALL THIS EMERGENCY NUMBER.... 1-800-716-9132\r\n" +
        "WILL CALL BACK WHEN CLEAR PER DISPATCHER 516\r\n" +
        "DISPATCH NOTIFYING AMBULANCE AND FIRE DISPATCH TO NOTIFY THEIR FIRE DEPTS...\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:EMERGENCY POLICE LINE",
        "ADDR:RAILROAD TRKS & WEST BROADWAY",
        "CALL:INFO",
        "INFO:TRAIN BLOCKING CROSSING FOR MOST OF MORNING.....\nWEST BROADWAY, DOUGLAS DR, AND BOONE AV, WINNETKA AV,\nWORK TRAIN ON TRACKS AND BLOCKING STREETS MOST OF THE MORNING.....\nIF ACCESS IS NEEDED CALL THIS EMERGENCY NUMBER.... 1-800-716-9132\nWILL CALL BACK WHEN CLEAR PER DISPATCHER 516\nDISPATCH NOTIFYING AMBULANCE AND FIRE DISPATCH TO NOTIFY THEIR FIRE DEPTS...");

    doTest("T7",
        "[] NAME-matt-northwest asphalt;  LOC-4801 EDGEWOOD AVE N;  EVTYPE-GASO;  COMMENTS-4801 EDGEWOOD AVE N: cross streets : FAIRVIEW AVE N & 48TH AVE N\r\n" +
        "hit serv line to the res... 1/4 inch... is pinched off\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:matt-northwest asphalt",
        "ADDR:4801 EDGEWOOD AVE N",
        "CALL:GASO",
        "INFO:hit serv line to the res... 1/4 inch... is pinched off",
        "X:FAIRVIEW AVE N & 48TH AVE N");

    doTest("T8",
        "[] NAME-APRIL;  LOC-6610 MARKWOOD DR N;  EVTYPE-WIREA;  COMMENTS-6610 MARKWOOD DR N : cross streets : GEORGIA AVE N & HAMPSHIRE AVE N\r\n" +
        "NW CORNER IN THE BACK POWER LINES IN THE TREES SPARKING\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:APRIL",
        "ADDR:6610 MARKWOOD DR N",
        "CALL:WIREA",
        "INFO:NW CORNER IN THE BACK POWER LINES IN THE TREES SPARKING",
        "X:GEORGIA AVE N & HAMPSHIRE AVE N");

    doTest("T9",
        "[] NAME-41IN1;  LOC-4251 XYLON AVE N [#1X [@FIRE 41 ST 3;  EVTYPE-HEART;  COMMENTS-4251 XYLON AVE N : cross streets : 45TH AVE N & 42ND AVE N\r\n" +
        "NO SQUADS NEEDED\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:41IN1",
        "ADDR:4251 XYLON AVE N",
        "APT:1X",
        "PLACE:FIRE 41 ST 3",
        "CALL:HEART",
        "INFO:NO SQUADS NEEDED",
        "X:45TH AVE N & 42ND AVE N");

    doTest("T10",
        "[] NAME-EVANS, JUANITA;  LOC-6001 56TH AVE N [@CRYSTAL COURTS APT;  EVTYPE-ALMAPT;  COMMENTS-6001 56TH AVE N : cross streets : BRUNSWICK AVE N & ADAIR AVE N\r\n" +
        "ALARM GOING OFF ON 2ND FLOOR HALL\r\n" +
        "NO FIRE SEEN OR SMOKE SMELL\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:EVANS, JUANITA",
        "ADDR:6001 56TH AVE N",
        "PLACE:CRYSTAL COURTS APT",
        "CALL:ALMAPT",
        "INFO:ALARM GOING OFF ON 2ND FLOOR HALL\nNO FIRE SEEN OR SMOKE SMELL",
        "X:BRUNSWICK AVE N & ADAIR AVE N");

    doTest("T11",
        "[] NAME-GELLERMAN, EVELYN;  LOC-5700 BOONE AVE N [#531 [@CHARDON COURT APT;  EVTYPE-HEART;  COMMENTS-5700 BOONE AVE N : cross streets : 56TH AVE N & BASS LAKE RD\r\n" +
        "DIZZY / HEART BEATING FAST\r\n" +
        "DOOR WILL BE UNLOCKED\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:GELLERMAN, EVELYN",
        "ADDR:5700 BOONE AVE N",
        "APT:531",
        "PLACE:CHARDON COURT APT",
        "CALL:HEART",
        "INFO:DIZZY / HEART BEATING FAST\nDOOR WILL BE UNLOCKED",
        "X:56TH AVE N & BASS LAKE RD");

    doTest("T12",
        "[] NAME-ANONYMOUS;  LOC-3556 QUAIL AVE N;  EVTYPE-CKBURN;  COMMENTS-3556 QUAIL AVE N: cross streets : 35TH AVE N & 36TH AVE N\r\n" +
        "NEIGHBOR BURNING WOOD FROM GARAGE W/PAINT ON IT\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:ANONYMOUS",
        "ADDR:3556 QUAIL AVE N",
        "CALL:CKBURN",
        "INFO:NEIGHBOR BURNING WOOD FROM GARAGE W/PAINT ON IT",
        "X:35TH AVE N & 36TH AVE N");

    doTest("T13",
        "[] NAME-LORIELLE;  LOC-5701 QUEBEC AVE N [#103A [@CRYSTAL TOWERS APT;  EVTYPE-HEART;  COMMENTS-5701 QUEBEC AVE N : cross streets : 58TH AVE N & 56TH AVE N\r\n" +
        "CHEST PAINS..HX..GAVE ONE 1 NITRO\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:LORIELLE",
        "ADDR:5701 QUEBEC AVE N",
        "APT:103A",
        "PLACE:CRYSTAL TOWERS APT",
        "CALL:HEART",
        "INFO:CHEST PAINS..HX..GAVE ONE 1 NITRO",
        "X:58TH AVE N & 56TH AVE N");

    doTest("T14",
        "[] NAME-JIM;  LOC-5600 HWY 169 [@LIBERTY DIVERSIFIED;  EVTYPE-HEART;  COMMENTS-5600 HWY 169 : cross streets : BASS LAKE RD & RAILROAD TRKS\r\n" +
        "DOCK #4\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:JIM",
        "ADDR:5600 HWY 169",
        "PLACE:LIBERTY DIVERSIFIED",
        "CALL:HEART",
        "INFO:DOCK #4",
        "X:BASS LAKE RD & RAILROAD TRKS");

    doTest("T15",
        "[] NAME-HARMON;  LOC-5430 BOONE AVE N [@NORTH RIDGE CARE CENTER 3;  EVTYPE-DRILL;  COMMENTS-5430 BOONE AVE N : cross streets : RESEARCH CENTER RD E & 54TH AVE N\r\n" +
        "FROM NOW FOR ABOUT 15 MINUTES....WILL CALL BACK WHEN FINISHED....\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:HARMON",
        "ADDR:5430 BOONE AVE N",
        "PLACE:NORTH RIDGE CARE CENTER 3",
        "CALL:DRILL",
        "INFO:FROM NOW FOR ABOUT 15 MINUTES....WILL CALL BACK WHEN FINISHED....",
        "X:RESEARCH CENTER RD E & 54TH AVE N");

    doTest("T16",
        "[] NAME-HASTINGS, BRANDY;  LOC-6721 45TH PL N;  EVTYPE-ALMCO;  COMMENTS-6721 45TH PL N : cross streets : JERSEY AVE N\r\n" +
        "GENERAL CO ALRM - BATTERY OPERATED\r\n" +
        "RP JUST HAD HER FURNACE/WTR HEATER AND DUCTS TODAY\r\n" +
        "CLEANERS LEFT 1 HRS AGO ...\r\n" +
        "*** CO ALRM SOUNDING FOR LAST 5 MIN .. NO READING **\r\n" +
        "NO ONE FEELING ILL\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:HASTINGS, BRANDY",
        "ADDR:6721 45TH PL N",
        "CALL:ALMCO",
        "INFO:GENERAL CO ALRM - BATTERY OPERATED\nRP JUST HAD HER FURNACE/WTR HEATER AND DUCTS TODAY\nCLEANERS LEFT 1 HRS AGO ...\n*** CO ALRM SOUNDING FOR LAST 5 MIN .. NO READING **\nNO ONE FEELING ILL",
        "X:JERSEY AVE N");

    doTest("T17",
        "[] NAME-JOHNATHAN;  LOC-5732 WINNETKA AVE N [#204 [@WINCREST APT;  EVTYPE-HEART;  COMMENTS-5732 WINNETKA AVE N : cross streets : BASS LAKE RD & 59TH AVE N\r\n" +
        "FEMALE- HEART RACING,, HARD TIME BREATHING\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:JOHNATHAN",
        "ADDR:5732 WINNETKA AVE N",
        "APT:204",
        "PLACE:WINCREST APT",
        "CALL:HEART",
        "INFO:FEMALE- HEART RACING,, HARD TIME BREATHING",
        "X:BASS LAKE RD & 59TH AVE N");

    doTest("T18",
        "[] NAME-LAURA CARLSON;  LOC-7934 51ST AVE N;  EVTYPE-ALMCO;  COMMENTS-7934 51ST AVE N: cross streets : WISCONSIN AVE N & WINNETKA AVE N\r\n" +
        "CO ALM SOUNDING ... NO ONE ILL , NO READING ON THE ALM\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:LAURA CARLSON",
        "ADDR:7934 51ST AVE N",
        "CALL:ALMCO",
        "INFO:CO ALM SOUNDING ... NO ONE ILL , NO READING ON THE ALM",
        "X:WISCONSIN AVE N & WINNETKA AVE N");

    doTest("T19",
        "[] NAME-SHANE SAUNDERS;  LOC-4748 GEORGIA AVE N;  EVTYPE-GASO;  COMMENTS-4748 GEORGIA AVE N: cross streets : 47TH AVE N & 48TH AVE N\r\n" +
        "SMELLS NATURAL GAS WHILE SITTING AT THE BONFIRE IN BACK YARD\r\n" +
        "MEET RP IN FRONT YARD\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:SHANE SAUNDERS",
        "ADDR:4748 GEORGIA AVE N",
        "CALL:GASO",
        "INFO:SMELLS NATURAL GAS WHILE SITTING AT THE BONFIRE IN BACK YARD\nMEET RP IN FRONT YARD",
        "X:47TH AVE N & 48TH AVE N");

    doTest("T20",
        "[] NAME-GRANLUND, PAULA;  LOC-7717 45TH HALF AVE N;  EVTYPE-CKBURN;  COMMENTS-7717 45TH HALF AVE N: cross streets : WINNETKA AVE N & RHODE ISLAND AVE N\r\n" +
        "TWO HOUSES DOWN TWRD WINNETKA\r\n" +
        "CK ON OPEN FIRE .. NOT ALWAYS ATTENDED .. LOTS OF SMOKE\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:GRANLUND, PAULA",
        "ADDR:7717 45TH HALF AVE N",
        "CALL:CKBURN",
        "INFO:TWO HOUSES DOWN TWRD WINNETKA\nCK ON OPEN FIRE .. NOT ALWAYS ATTENDED .. LOTS OF SMOKE",
        "X:WINNETKA AVE N & RHODE ISLAND AVE N");

    doTest("T21",
        "[] NAME-7634127755;  LOC-5336 PENNSYLVANIA AVE N;  EVTYPE-HOUSE;  COMMENTS-FIRE IN THE BATHROOM FAN ..\r\n" +
        "FLAMES SEEN ..\r\n" +
        "ADVSED TO EVAC\r\n" +
        "E3 CREW/2\r\n" +
        "E6 CREW/2\r\n" +
        "PER 3245 ON SCENE AND PARTY THERE STATED NO FIRE\r\n" +
        "NEED TO CONFIRM ADDRESS\r\n" +
        "SINGLE FAMILY, NOTHING SHOWIN, P2 IC\r\n" +
        "PER 3224 SLOW FIRE\r\n" +
        "5336 PENNSYLVANIA AVE N: cross streets : SAINT RAPHAEL DR & 53RD AVE N\r\n" +
        "CORRECTED ADDRESS IS 5336 PENNYSLVANIA\r\n" +
        "CALLED RP BACK STATED FLAMES OUT .. ELECTRICAL FIRE\r\n" +
        "CANCEL BKC FIRE\r\n" +
        "PER 4113 FIRE OUT ... LOTS OF SMOKE\r\n" +
        "NORTH CANCELLED\r\n" +
        "PER COMMAND, FIRE CONTROLLED.\r\n" +
        "Event transferred to dgroup: 32P\r\n" +
        "CH11 TO E11\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:7634127755",
        "ADDR:5336 PENNSYLVANIA AVE N",
        "CALL:HOUSE",
        "INFO:FIRE IN THE BATHROOM FAN ..\nFLAMES SEEN ..\nADVSED TO EVAC\nE3 CREW/2\nE6 CREW/2\nPER 3245 ON SCENE AND PARTY THERE STATED NO FIRE\nNEED TO CONFIRM ADDRESS\nSINGLE FAMILY, NOTHING SHOWIN, P2 IC\nPER 3224 SLOW FIRE\n5336 PENNSYLVANIA AVE N\nCORRECTED ADDRESS IS 5336 PENNYSLVANIA\nCALLED RP BACK STATED FLAMES OUT .. ELECTRICAL FIRE\nCANCEL BKC FIRE\nPER 4113 FIRE OUT ... LOTS OF SMOKE\nNORTH CANCELLED\nPER COMMAND, FIRE CONTROLLED.\nEvent transferred to dgroup: 32P\nCH11 TO E11",
        "X:SAINT RAPHAEL DR & 53RD AVE N");

    doTest("T22",
        "[] NAME-PROTECTION ONE;  LOC-5913 HAMPSHIRE AVE N;  EVTYPE-ALMCO;  COMMENTS-5913 HAMPSHIRE AVE N: cross streets : 60TH AVE N & 59TH AVE N\r\n" +
        "RESIDENTIAL / AUDIBLE\r\n" +
        "COVERS CARBON MONOXIDE DETECTOR\r\n" +
        "ALRM CO REC'D ALRM AT 1820 HRS\r\n" +
        "NAM/OHM, BILLY\r\n" +
        "INSIDE 763 537 6482\r\n" +
        "ALRM ATTEMPTED TO CALL NO ANSWR\r\n" +
        "\r\n" +
        "\r\n" +
        "\r\n",

        "NAME:PROTECTION ONE",
        "ADDR:5913 HAMPSHIRE AVE N",
        "CALL:ALMCO",
        "INFO:RESIDENTIAL / AUDIBLE\nCOVERS CARBON MONOXIDE DETECTOR\nALRM CO REC'D ALRM AT 1820 HRS\nNAM/OHM, BILLY\nINSIDE 763 537 6482\nALRM ATTEMPTED TO CALL NO ANSWR",
        "X:60TH AVE N & 59TH AVE N");

  }
  
  public static void main(String[] args) {
    new MNHennepinCountyParserTest().generateTests("T1");
  }
}
