package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Butler County, OH
Contact: support@active911.com
Sender: BCSO@butlersheriff.org

BCSO:12-026842* 7897 JESSIES WY* * * * MORRIS RD* TYLERSVILLE RD* * NBH: JESSIE`S LANDING CONDOS* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 21:06:12 : pos1 : RLONEILL Cross streets: MORRIS RD//TYLERSVILLE RD NBH: JESSIE`S LANDING CONDOS 80 YO MALE URINATING BLOOD*
BCSO:12-026840* 4090 MILLIKIN RD* * * * FAIRCREST* LIBERTY FAIRFIELD RD* * * MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 20:47:29 : pos4 : dkrednour Cross streets: FAIRCREST//LIBERTY FAIRFIELD RD 41 YOM,. DIABETIC UNRESPONSIVE*
BCSO:12-026808* 6926 CHESTNUT OAK CT* * * * * JOCELYN DR* * NBH: ASHWOOD SUBD* MEDICAL* LIFE SQUAD REQUEST* WOLFER, NORBERT* * * * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 17:39:09 : pos5 : PDFRYER REQUESTING SILENT APPROACH 05/18/2012 17:38:51 : pos5 : PDFRYER Cross streets: //JOCELYN DR NBH: ASHWOOD SUBD 90 YO FEMALE RUNNING FEVER, UNABLE TO WALK - DIABETIC*
BCSO:12-026807* 2580 UTICA AV* * * * CANASTOTA DR* * * NBH: NORMANDY HEIGHTS* MEDICAL* LIFE SQUAD REQUEST* SINGLETON,RAY* * * * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 17:34:43 : pos5 : PDFRYER Cross streets: CANASTOTA DR// NBH: NORMANDY HEIGHTS 75 YO FEMALE - UNABLE TO MOVE ONE LEG, KNEE AND LEG ARE SWOLLEN - NEEDS TRANSPORTED*
BCSO:12-027009* 7309 CLARION CT* * * * * SARATOGA DR* * Special Comment:: *LOADED WEAPONS INSIDE* MAY2012 NBH: HOWARD`S SUBDIVISION* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: Yes* Line17=Hazards: Yes* Line18=05/19/2012 18:06:17 : pos3 : RLONEILL Special Comment:: *LOADED WEAPONS INSIDE* MAY2012 Cross streets: //SARATOGA DR NBH: HOWARD`S SUBDIVISION 56 YO FEMALE DIFF BREATHING*
BCSO:12-026888* 7418 CHATEAUGUAY ST* * * * RIVERDOWNS CT* CITATION DR* * NBH: ASCOT DOWNS* SUICIDE* SUICIDE OR ATTEMPT SUICIDE* * * 21P45,FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 01:40:26 : pos3 : KKMETSKER Cross streets: RIVERDOWNS CT//CITATION DR NBH: ASCOT DOWNS 24 Y.O.F. - HANG IS PREG 4 MO*
BCSO:12-026932* 2923 HAMILTON MASON RD* * * * * * * * MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 10:33:24 : pos3 : HRMILLER Landmark: WELLINGTON MANOR 60YOM LEFT HIP PAIN .. FRONT DOUBLE DOORS GO TO THE RIGHT*
BCSO:12-026944* 3219 PRINCETON RD* * * * BYPASS 4* BYPASS 4* * * CRASH INJURY* INJURY ACCIDENT* * * FFTFD,FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 11:43:04 : pos3 : HRMILLER Cross streets: BYPASS 4//BYPASS 4 Landmark: MURPHY GAS STATION ALI X Coordinate: -84.5082092 ALI Y Coordinate: 39.39026713 ALI Uncertainty Factor: 000 ALI Confidence Factor: 28 **Nearest Address: 3213 PRINCETON RD, FAIRFIELD TWP GREEN CAMRY .. 2 CHILDREN IN THE CAR 11 AND 9 .. LIGHT BEIGE MINI VAN LEFT THE SCENE .. NOW PARKED IN THE LOT OF THE GAS STATION*
BCSO:12-026968* 5753 GREEN CREST DR* * * * CREST MANOR DR* MILLCREST DR* * NBH: GREEN CREST MANOR* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 13:24:53 : pos1 : TJMILLER Cross streets: CREST MANOR DR//MILLCREST DR NBH: GREEN CREST MANOR 75 YOF UNRESPONSIVE BUT BREATHING - ALZHEIMER PATIENT , HEART HX*

*/

public class OHButlerCountyParserTest extends BaseParserTest {
  
  public OHButlerCountyParserTest() {
    setParser(new OHButlerCountyParser(), "BUTLER COUNTY", "OH");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "BCSO:12-026842* 7897 JESSIES WY* * * * MORRIS RD* TYLERSVILLE RD* * NBH: JESSIE`S LANDING CONDOS* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 21:06:12 : pos1 : RLONEILL Cross streets: MORRIS RD//TYLERSVILLE RD NBH: JESSIE`S LANDING CONDOS 80 YO MALE URINATING BLOOD*",
        "ID:12-026842",
        "ADDR:7897 JESSIES WY",
        "PLACE:JESSIE`S LANDING CONDOS",
        "X:MORRIS RD & TYLERSVILLE RD",
        "INFO:Medical: No / Hazards: No\n80 YO MALE URINATING BLOOD",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/18/2012",
        "TIME:21:06:12",
        "UNIT:FFTLS");

    doTest("T2",
        "BCSO:12-026840* 4090 MILLIKIN RD* * * * FAIRCREST* LIBERTY FAIRFIELD RD* * * MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 20:47:29 : pos4 : dkrednour Cross streets: FAIRCREST//LIBERTY FAIRFIELD RD 41 YOM,. DIABETIC UNRESPONSIVE*",
        "ID:12-026840",
        "ADDR:4090 MILLIKIN RD",
        "X:FAIRCREST & LIBERTY FAIRFIELD RD",
        "INFO:Medical: No / Hazards: No\n41 YOM,. DIABETIC UNRESPONSIVE",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/18/2012",
        "TIME:20:47:29",
        "UNIT:FFTLS");

    doTest("T3",
        "BCSO:12-026808* 6926 CHESTNUT OAK CT* * * * * JOCELYN DR* * NBH: ASHWOOD SUBD* MEDICAL* LIFE SQUAD REQUEST* WOLFER, NORBERT* * * * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 17:39:09 : pos5 : PDFRYER REQUESTING SILENT APPROACH 05/18/2012 17:38:51 : pos5 : PDFRYER Cross streets: //JOCELYN DR NBH: ASHWOOD SUBD 90 YO FEMALE RUNNING FEVER, UNABLE TO WALK - DIABETIC*",
        "ID:12-026808",
        "ADDR:6926 CHESTNUT OAK CT",
        "PLACE:ASHWOOD SUBD",
        "X:JOCELYN DR",
        "INFO:Medical: No / Hazards: No\nREQUESTING SILENT APPROACH\n90 YO FEMALE RUNNING FEVER, UNABLE TO WALK - DIABETIC",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/18/2012",
        "TIME:17:39:09",
        "NAME:WOLFER, NORBERT");

    doTest("T4",
        "BCSO:12-026807* 2580 UTICA AV* * * * CANASTOTA DR* * * NBH: NORMANDY HEIGHTS* MEDICAL* LIFE SQUAD REQUEST* SINGLETON,RAY* * * * Line16=Medical: No* Line17=Hazards: No* Line18=05/18/2012 17:34:43 : pos5 : PDFRYER Cross streets: CANASTOTA DR// NBH: NORMANDY HEIGHTS 75 YO FEMALE - UNABLE TO MOVE ONE LEG, KNEE AND LEG ARE SWOLLEN - NEEDS TRANSPORTED*",
        "ID:12-026807",
        "ADDR:2580 UTICA AV",
        "MADDR:2580 UTICA AVE",
        "PLACE:NORMANDY HEIGHTS",
        "X:CANASTOTA DR",
        "INFO:Medical: No / Hazards: No\n75 YO FEMALE - UNABLE TO MOVE ONE LEG, KNEE AND LEG ARE SWOLLEN - NEEDS TRANSPORTED",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/18/2012",
        "TIME:17:34:43",
        "NAME:SINGLETON,RAY");

    doTest("T5",
        "BCSO:12-027009* 7309 CLARION CT* * * * * SARATOGA DR* * Special Comment:: *LOADED WEAPONS INSIDE* MAY2012 NBH: HOWARD`S SUBDIVISION* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: Yes* Line17=Hazards: Yes* Line18=05/19/2012 18:06:17 : pos3 : RLONEILL Special Comment:: *LOADED WEAPONS INSIDE* MAY2012 Cross streets: //SARATOGA DR NBH: HOWARD`S SUBDIVISION 56 YO FEMALE DIFF BREATHING*",
        "ID:12-027009",
        "ADDR:7309 CLARION CT",
        "PLACE:HOWARD`S SUBDIVISION",
        "X:SARATOGA DR",
        "DATE:05/19/2012", 
        "TIME:18:06:17",
        "INFO:LOADED WEAPONS INSIDE MAY2012 / Medical: Yes / Hazards: Yes\n56 YO FEMALE DIFF BREATHING",
        "CALL:LIFE SQUAD REQUEST",
        "TIME:18:06:17",
        "UNIT:FFTLS");

    doTest("T6",
        "BCSO:12-026888* 7418 CHATEAUGUAY ST* * * * RIVERDOWNS CT* CITATION DR* * NBH: ASCOT DOWNS* SUICIDE* SUICIDE OR ATTEMPT SUICIDE* * * 21P45,FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 01:40:26 : pos3 : KKMETSKER Cross streets: RIVERDOWNS CT//CITATION DR NBH: ASCOT DOWNS 24 Y.O.F. - HANG IS PREG 4 MO*",
        "ID:12-026888",
        "ADDR:7418 CHATEAUGUAY ST",
        "PLACE:ASCOT DOWNS",
        "X:RIVERDOWNS CT & CITATION DR",
        "INFO:Medical: No / Hazards: No\n24 Y.O.F. - HANG IS PREG 4 MO",
        "CALL:SUICIDE OR ATTEMPT SUICIDE",
        "DATE:05/19/2012",
        "TIME:01:40:26",
        "UNIT:21P45,FFTLS");

    doTest("T7",
        "BCSO:12-026932* 2923 HAMILTON MASON RD* * * * * * * * MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 10:33:24 : pos3 : HRMILLER Landmark: WELLINGTON MANOR 60YOM LEFT HIP PAIN .. FRONT DOUBLE DOORS GO TO THE RIGHT*",
        "ID:12-026932",
        "ADDR:2923 HAMILTON MASON RD",
        "INFO:Medical: No / Hazards: No\nFRONT DOUBLE DOORS GO TO THE RIGHT",
        "PLACE:WELLINGTON MANOR 60YOM LEFT HIP PAIN",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/19/2012",
        "TIME:10:33:24",
        "UNIT:FFTLS");

    doTest("T8",
        "BCSO:12-026944* 3219 PRINCETON RD* * * * BYPASS 4* BYPASS 4* * * CRASH INJURY* INJURY ACCIDENT* * * FFTFD,FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 11:43:04 : pos3 : HRMILLER Cross streets: BYPASS 4//BYPASS 4 Landmark: MURPHY GAS STATION ALI X Coordinate: -84.5082092 ALI Y Coordinate: 39.39026713 ALI Uncertainty Factor: 000 ALI Confidence Factor: 28 **Nearest Address: 3213 PRINCETON RD, FAIRFIELD TWP GREEN CAMRY .. 2 CHILDREN IN THE CAR 11 AND 9 .. LIGHT BEIGE MINI VAN LEFT THE SCENE .. NOW PARKED IN THE LOT OF THE GAS STATION*",
        "ID:12-026944",
        "ADDR:3219 PRINCETON RD",
        "PLACE:MURPHY GAS STATION ALI",
        "X:BYPASS 4 & BYPASS 4",
        "INFO:Medical: No / Hazards: No\nX Coordinate: -84.5082092 ALI / Y Coordinate: 39.39026713 ALI / Uncertainty Factor: 000 ALI / Confidence Factor: 28 ** / Nearest Address: 3213 PRINCETON RD, FAIRFIELD TWP GREEN CAMRY / 2 CHILDREN IN THE CAR 11 AND 9 / LIGHT BEIGE MINI VAN LEFT THE SCENE / NOW PARKED IN THE LOT OF THE GAS STATION",
        "CALL:INJURY ACCIDENT",
        "DATE:05/19/2012",
        "TIME:11:43:04",
        "UNIT:FFTFD,FFTLS");

    doTest("T9",
        "BCSO:12-026968* 5753 GREEN CREST DR* * * * CREST MANOR DR* MILLCREST DR* * NBH: GREEN CREST MANOR* MEDICAL* LIFE SQUAD REQUEST* * * FFTLS* * Line16=Medical: No* Line17=Hazards: No* Line18=05/19/2012 13:24:53 : pos1 : TJMILLER Cross streets: CREST MANOR DR//MILLCREST DR NBH: GREEN CREST MANOR 75 YOF UNRESPONSIVE BUT BREATHING - ALZHEIMER PATIENT , HEART HX*",
        "ID:12-026968",
        "ADDR:5753 GREEN CREST DR",
        "PLACE:GREEN CREST MANOR",
        "X:CREST MANOR DR & MILLCREST DR",
        "INFO:Medical: No / Hazards: No\n75 YOF UNRESPONSIVE BUT BREATHING - ALZHEIMER PATIENT , HEART HX",
        "CALL:LIFE SQUAD REQUEST",
        "DATE:05/19/2012",
        "TIME:13:24:53",
        "UNIT:FFTLS");
  }

  public static void main(String[] args) {
    new OHButlerCountyParserTest().generateTests("T1");
  }
}
