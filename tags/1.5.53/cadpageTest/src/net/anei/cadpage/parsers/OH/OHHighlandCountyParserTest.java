package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHHighlandCountyParserTest extends BaseParserTest {
  
  public OHHighlandCountyParserTest() {
    setParser(new OHHighlandCountyParser(), "HIGHLAND COUNTY", "OH");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "Dispatch:[SWM]- NATURE: INJURED LOCATION: 930 MOXLEY RD PAINT TWP ROS S COU COMMENTS: 63 YEAR OLD MALE FALLEN FROM LADDER APPORX 6 FEET, LANDED IN A ROCK BED. POSSIBLE SHOULDER INJURY, IS AL ERT AND TALKING. USE DRIVEWAY THAT GOES TO BASEME",
        "UNIT:SWM",
        "CALL:INJURED",
        "ADDR:930 MOXLEY RD",
        "CITY:PAINT TWP",
        "NAME:ROS S COU",
        "INFO:63 YEAR OLD MALE FALLEN FROM LADDER APPORX 6 FEET, LANDED IN A ROCK BED. POSSIBLE SHOULDER INJURY, IS AL ERT AND TALKING. USE DRIVEWAY THAT GOES TO BASEME");

    doTest("T2",
        "Dispatch:[DAB]- NATURE: CHEST PAINS LOCATION: 10714 HIAWATHA RD PAINT TWP COMMENTS: 55YOM CP/DIB",
        "UNIT:DAB",
        "CALL:CHEST PAINS",
        "ADDR:10714 HIAWATHA RD",  // ** Hiawatha Dr
        "CITY:PAINT TWP",
        "INFO:55YOM CP/DIB");

    doTest("T3",
        "Dispatch:[CTC]- NATURE: DIFFICULTY IN BREATHING LOCATION: 222 MCCLAIN AV GREENFIELD COMMENTS: 22 YOA FEMALE ASTHMA ATTACK TOT 2 155HRS",
        "UNIT:CTC",
        "CALL:DIFFICULTY IN BREATHING",
        "ADDR:222 MCCLAIN AV",
        "MADDR:222 MCCLAIN AVE",
        "CITY:GREENFIELD",
        "INFO:22 YOA FEMALE ASTHMA ATTACK TOT 2 155HRS");

    doTest("T4",
        "Dispatch:[TSC]- NATURE: STROKE LOCATION: 10606 LONG JOHN SILVER TL PA INT TWP COMMENTS: 26 YOA FEMALE POSS STROKE TOT 2017HRS",
        "UNIT:TSC",
        "CALL:STROKE",
        "ADDR:10606 LONG JOHN SILVER TL",
        "MADDR:10606 LONG JOHN SILVER TRL",
        "CITY:PAINT TWP",
        "INFO:26 YOA FEMALE POSS STROKE TOT 2017HRS");

    doTest("T5",
        "Dispatch:[TSC]- NATURE: ASSAULT LOCATION: 658 LYNDON AVE GREENFIELD C OMMENTS: SUBJ ASSAULTED TOT 2013HRS",
        "UNIT:TSC",
        "CALL:ASSAULT",
        "ADDR:658 LYNDON AVE",
        "CITY:GREENFIELD",
        "INFO:SUBJ ASSAULTED TOT 2013HRS");

    doTest("T6",
        "Dispatch:[TSC]- NATURE: CHEST PAINS LOCATION: 7107 STATE RTE 753 PAIN T TWP BETWEEN MC COPPIN MILL RD / U S RTE 50 COMMENTS: 51 Y OA FEMALE CHEST PAIN TOT 1655HRS",
        "UNIT:TSC",
        "CALL:CHEST PAINS",
        "ADDR:7107 STATE RTE 753",
        "MADDR:7107 STATE 753",
        "CITY:PAINT TWP",
        "X:MC COPPIN MILL RD / U S RTE 50",
        "INFO:51 Y OA FEMALE CHEST PAIN TOT 1655HRS");

    doTest("T7",
        "Dispatch:[CTC]- NATURE: GENERAL ILLNESS LOCATION: 209 MCCLAIN AV SUIT E: G GREENFIELD COMMENTS: ELDERLY FM GENERAL ILLNESS",
        "UNIT:CTC",
        "CALL:GENERAL ILLNESS",
        "ADDR:209 MCCLAIN AV",
        "MADDR:209 MCCLAIN AVE",
        "APT:G",
        "CITY:GREENFIELD",
        "INFO:ELDERLY FM GENERAL ILLNESS");

    doTest("T8",
        "Dispatch:[CTC]- NATURE: AGENCY ASSIST LOCATION: 11542 STATE RTE 41 FA YETTE COUNTY COMMENTS: MUTUAL AID W/FAYETTE COUNTY SQUAD ON MVA W/INJURIES. TOT 1638HRS",
        "UNIT:CTC",
        "CALL:AGENCY ASSIST",
        "ADDR:11542 STATE RTE 41",
        "MADDR:11542 STATE 41",
        "CITY:FAYETTE COUNTY",
        "INFO:MUTUAL AID W/FAYETTE COUNTY SQUAD ON MVA W/INJURIES. TOT 1638HRS");

    doTest("T9",
        "Dispatch:[RBY]- NATURE: FALLEN LOCATION: 213 S 7 ST GREENFIELD COMMEN TS: +039.365859 -083.392861 28 YOA AMLE FALLEN FROM LADDER, NOW AT HOME",
        "UNIT:RBY",
        "CALL:FALLEN",
        "ADDR:213 S 7 ST",
        "CITY:GREENFIELD",
        "INFO:+039.365859 -083.392861 28 YOA AMLE FALLEN FROM LADDER, NOW AT HOME");

    doTest("T10",
        "Dispatch:[TSC]- LOCATION: 10917 NORTH SHORE DR PAINT TWP BETWEEN ROB INHOOD LN / EAST LAKE SHORE DR COMMENTS: MALE CHEST & STOMAC H PAINS TOT 1235HRS",
        "UNIT:TSC",
        "CALL:MALE CHEST & STOMAC H PAINS TOT 1235HRS",
        "ADDR:10917 NORTH SHORE DR",
        "CITY:PAINT TWP",
        "X:ROB INHOOD LN / EAST LAKE SHORE DR");

    doTest("T11",
        "Dispatch:[JAV]- NATURE: SUICIDE LOCATION: 11510 LOCH NESS DR PAINT TW P COMMENTS: MALE ATTPT TO HANG HIMSELF",
        "UNIT:JAV",
        "CALL:SUICIDE",
        "ADDR:11510 LOCH NESS DR",
        "CITY:PAINT TWP",
        "INFO:MALE ATTPT TO HANG HIMSELF");

    doTest("T12",
        "Dispatch:[CTC]- NATURE: FALLEN LOCATION: 7371 BEECHWOOD RD PAINT TWP BETWEEN LOIS LN / U S RTE 50 COMMENTS: ELDERLY FM FALLEN",
        "UNIT:CTC",
        "CALL:FALLEN",
        "ADDR:7371 BEECHWOOD RD",
        "CITY:PAINT TWP",
        "X:LOIS LN / U S RTE 50",
        "INFO:ELDERLY FM FALLEN");

    doTest("T13",
        "Dispatch:[RBY]- NATURE: INJURED LOCATION: 200 N 11 ST SUITE: APT 5 GR EENFIELD COMMENTS: 24 YOA FEMALE RE-INJURIED DISLOCATED KNEE",
        "UNIT:RBY",
        "CALL:INJURED",
        "ADDR:200 N 11 ST",
        "APT:APT 5",
        "CITY:GREENFIELD",
        "INFO:24 YOA FEMALE RE-INJURIED DISLOCATED KNEE");

    doTest("T14",
        "Dispatch:[RDM]- NATURE: FALLEN LOCATION: 6262 STATE RTE 753 HILLSBORO BETWEEN SPARGER LN / MCCOPPIN MILL RD COMMENTS: 74 YO MALE",
        "UNIT:RDM",
        "CALL:FALLEN",
        "ADDR:6262 STATE RTE 753",
        "MADDR:6262 STATE 753",
        "CITY:HILLSBORO",
        "X:SPARGER LN / MCCOPPIN MILL RD",
        "INFO:74 YO MALE");

    doTest("T15",
        "Dispatch:[RBY]- NATURE: AGENCY ASSIST LOCATION: 204 N EAST ST HILLSBO RO COMMENTS: MUTAL AID STATION COVERAGE '",
        "UNIT:RBY",
        "CALL:AGENCY ASSIST",
        "ADDR:204 N EAST ST",
        "CITY:HILLSBORO",
        "INFO:MUTAL AID STATION COVERAGE '");

    doTest("T16",
        "Dispatch:[DAB]- NATURE: ELECTRICAL SHOCK LOCATION: 209 E MAIN ST LEES BURG COMMENTS: 37 YOF",
        "UNIT:DAB",
        "CALL:ELECTRICAL SHOCK",
        "ADDR:209 E MAIN ST",
        "CITY:LEESBURG",
        "INFO:37 YOF");

    doTest("T17",
        "Dispatch:[SLR]- NATURE: OVERDOSE LOCATION: 451 SOUTH ST GREENFIELD CO MMENTS: FEMALE GOING CRAZY. UNKNOWN IF IT IS OD OR NOT",
        "UNIT:SLR",
        "CALL:OVERDOSE",
        "ADDR:451 SOUTH ST",
        "CITY:GREENFIELD",
        "INFO:FEMALE GOING CRAZY. UNKNOWN IF IT IS OD OR NOT");

    doTest("T18",
        "Dispatch:[JAV]- NATURE: CHEST PAINS LOCATION: 10817 HIAWATHA RD PAINT TWP COMMENTS: +039.192717 -083.494806 CF= 90% 55 YR OLD MA LE CHEST PAINS",
        "UNIT:JAV",
        "CALL:CHEST PAINS",
        "ADDR:10817 HIAWATHA RD",
        "CITY:PAINT TWP",
        "INFO:+039.192717 -083.494806 CF= 90% 55 YR OLD MA LE CHEST PAINS");

    doTest("T19",
        "Dispatch:[RBY]- NATURE: GENERAL ILLNESS LOCATION: 6641 PIED PIPER PKW Y PAINT TWP COMMENTS: 84 YO MALE LIGHT HEADED",
        "UNIT:RBY",
        "CALL:GENERAL ILLNESS",
        "ADDR:6641 PIED PIPER PKWY",
        "CITY:PAINT TWP",
        "INFO:84 YO MALE LIGHT HEADED");

    doTest("T20",
        "Dispatch:[JAV]- NATURE: INJURED LOCATION: 7163 LAZY TR PAINT TWP COMM ENTS: +039.167719 -083.493068 42 YR OLD FEMALE DOG BITE RIG HT ARM BLEEDING BAD",
        "UNIT:JAV",
        "CALL:INJURED",
        "ADDR:7163 LAZY TR",
        "CITY:PAINT TWP",
        "INFO:+039.167719 -083.493068 42 YR OLD FEMALE DOG BITE RIG HT ARM BLEEDING BAD");

    doTest("T21",
        "Dispatch:[JAV]- NATURE: MUTUAL AID LOCATION: 494 GORMLEY RD FAYETTE C OUNTY COMMENTS: +039.200753 -083.506200 70 YR OLD MALE DIFF ICULTY BREATHING",
        "UNIT:JAV",
        "CALL:MUTUAL AID",
        "ADDR:494 GORMLEY RD",   // Could not be found
        "CITY:FAYETTE COUNTY",
        "INFO:+039.200753 -083.506200 70 YR OLD MALE DIFF ICULTY BREATHING");

    doTest("T22",
        "Dispatch:[JAV]- NATURE: SEIZURE LOCATION: 850 NELLIE ST GREENFIELD CO MMENTS: MALE HAVING SEIZURES",
        "UNIT:JAV",
        "CALL:SEIZURE",
        "ADDR:850 NELLIE ST",
        "CITY:GREENFIELD",
        "INFO:MALE HAVING SEIZURES");

    doTest("T23",
        "Dispatch:[RDM]- NATURE: GENERAL ILLNESS LOCATION: 6599 MC COPPIN MILL RD SUITE: 28 HILLSBORO COMMENTS: 29 YO FEMALE",
        "UNIT:RDM",
        "CALL:GENERAL ILLNESS",
        "ADDR:6599 MC COPPIN MILL RD",
        "APT:28",
        "CITY:HILLSBORO",
        "INFO:29 YO FEMALE");
  }
  
  public static void main(String[] args) {
    new OHHighlandCountyParserTest().generateTests("T1");
  }
}