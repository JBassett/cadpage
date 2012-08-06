package net.anei.cadpage.parsers.MI;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class MIEmmetCountyParserTest extends BaseParserTest {
  
  public MIEmmetCountyParserTest() {
	  setParser(new MIEmmetCountyParser(), "EMMET COUNTY", "MI");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "CAD:Msg:FYI: ;PERSONAL INJURY ACCIDENT;RBFR;409 N DIVISION RD/SUMMIT PARK DR;2 VEH PI. ONE VEH INTO THE TREES  [07/24/12 11:46:46 KMATELSKI]<10><13><10>",
        "CALL:PERSONAL INJURY ACCIDENT",
        "SRC:RBFR",
        "ADDR:409 N DIVISION RD & SUMMIT PARK DR",
        "MADDR:409 N DIVISION RD",
        "INFO:2 VEH PI. ONE VEH INTO THE TREES / <10><13><10>",
        "DATE:07/24/12",
        "TIME:11:46:46");

    doTest("T2",
        "CAD:Msg:FYI: ;MEDIC TRANSPORT;AEAM;416 CONNABLE AV;GOING TO CHEBOYGAN HOSPIC  [07/24/2012 11:57:20 KHEWITT]<10><13><10>",
        "CALL:MEDIC TRANSPORT",
        "SRC:AEAM",
        "ADDR:416 CONNABLE AV",
        "MADDR:416 CONNABLE AVE",
        "INFO:GOING TO CHEBOYGAN HOSPIC / <10><13><10>",
        "DATE:07/24/2012",
        "TIME:11:57:20");

    doTest("T3",
        "CAD:Msg:Update: ;AMBULANCE;EJAM;5632 RICHARDS RD;[Medical Priority Info] PROBLEM: HUSBAND FEELING FAINT 82YM   # PATS: 1   AGE: 82   SEX: Male   CONSCIOUS: Yes   BREATHING: Yes  [07/24/12 12:15:28 KMATELSKI]<10>[Medical Priority Info] RESPONSE: Ambula<13><10>",
        "CALL:AMBULANCE",
        "SRC:EJAM",
        "ADDR:5632 RICHARDS RD",
        "INFO:HUSBAND FEELING FAINT 82YM   # PATS: 1   AGE: 82   SEX: Male   CONSCIOUS: Yes   BREATHING: Yes / <10>",
        "DATE:07/24/12",
        "TIME:12:15:28");

    doTest("T4",
        "CAD:FYI: ;FIRE ASSIST OTHER AGENCY;RBFD;2499 RESORT PIKE RD/INTERTOWN RD;Event spawned from PROPERTY DAMAGE ACCIDENT.  [07/24/2012 13:51:20 VNOBLE]<10>SEMI TRUCK TIPPED OVER, NO OTHER VEHS ARE SEEN.  DRIVER APPEARS TO BE OUT WALKING AROUND. IT IS JUST THE TRAILER THAT TIPPED<13><10>",
        "CALL:FIRE ASSIST OTHER AGENCY",
        "SRC:RBFD",
        "ADDR:2499 RESORT PIKE RD & INTERTOWN RD",
        "MADDR:2499 RESORT PIKE RD",
        "INFO:Event spawned from PROPERTY DAMAGE ACCIDENT. / <10>SEMI TRUCK TIPPED OVER, NO OTHER VEHS ARE SEEN.  DRIVER APPEARS TO BE OUT WALKING AROUND. IT IS JUST THE TRAILER THAT TIPPED<13><10>",
        "DATE:07/24/2012",
        "TIME:13:51:20");

    doTest("T5",
        "CAD:Msg:FYI: ;AMBULANCE;AEAM;450 BAY ST;INMATE CUT HIS LEFT ARM.  RESPOND TO THE SALLY PORT  [07/21/12 15:20:08 EMCKINLEY]<10><13><10>",
        "CALL:AMBULANCE",
        "SRC:AEAM",
        "ADDR:450 BAY ST",
        "INFO:INMATE CUT HIS LEFT ARM.  RESPOND TO THE SALLY PORT / <10><13><10>",
        "DATE:07/21/12",
        "TIME:15:20:08");

    doTest("T6",
        "CAD:Msg:FYI: ;FIRE ASSIST OTHER AGENCY;PEFD;450 BAY ST;Event spawned from AMBULANCE.  [07/21/2012 15:22:28 EMCKINLEY]<10>INMATE CUT HIS LEFT ARM.  RESPOND TO THE SALLY PORT  [07/21/12 15:20:08 EMCKINLEY]<10><13><10>",
        "CALL:FIRE ASSIST OTHER AGENCY",
        "SRC:PEFD",
        "ADDR:450 BAY ST",
        "INFO:Event spawned from AMBULANCE. / <10>INMATE CUT HIS LEFT ARM.  RESPOND TO THE SALLY PORT / <10><13><10>",
        "DATE:07/21/2012",
        "TIME:15:22:28");

    doTest("T7",
        "CAD:Msg:FYI: ;WATER ICE RESCUE;AEAM;6425 N LAKE SHORE DR;BROKEN CELL UNABLE TO HEAR CALLER WPH2  [07/21/12 16:07:38 BTACHE]<10>ON CB PHONE RANG AND WENT TO NON PERSONALIZED VM  [07/21/12 16:08:55 BTACHE]<10>15 YOM ON BEACH VOMITING AND HARD TIME BREATHING.  ONE<13><10>",
        "CALL:WATER ICE RESCUE",
        "SRC:AEAM",
        "ADDR:6425 N LAKE SHORE DR",
        "INFO:BROKEN CELL UNABLE TO HEAR CALLER WPH2 / <10>ON CB PHONE RANG AND WENT TO NON PERSONALIZED VM / <10>15 YOM ON BEACH VOMITING AND HARD TIME BREATHING.  ONE<13><10>",
        "DATE:07/21/12",
        "TIME:16:07:38");

    doTest("T8",
        "CAD:Msg:Update: ;PERSONAL INJURY ACCIDENT;EMSH;1115 N US-31 HWY;STATES A MALE CAME IN ALL BLOODY AND HARDLY WALKING TOLD HER HE WAS HIT BY A DEER ON HIS BICYCLE, SHE ASKED IF SHE WANTED HIM TO CALL 911 FOR EMS AND HE SAID LET ME THINK ABOUT IT THEN HE WALKED OUT O<13><10>",
        "CALL:PERSONAL INJURY ACCIDENT",
        "SRC:EMSH",
        "ADDR:1115 N US-31 HWY",
        "MADDR:1115 N US 31",
        "INFO:STATES A MALE CAME IN ALL BLOODY AND HARDLY WALKING TOLD HER HE WAS HIT BY A DEER ON HIS BICYCLE, SHE ASKED IF SHE WANTED HIM TO CALL 911 FOR EMS AND HE SAID LET ME THINK ABOUT IT THEN HE WALKED OUT O<13><10>");

    doTest("T9",
        "CAD:Msg:FYI: ;ACCIDENT UNK INJURY;CCE;1399 LEARS RD/US-131 HWY;RP CALLING FROM WALMART.  REPORTING A 2 VEH ACCIDENT AT THE ABOVE INTERSECTION.  UNK FURTHER  [07/22/12 01:18:48 JBRECHT]<10><13><10>",
        "CALL:ACCIDENT UNK INJURY",
        "SRC:CCE",
        "ADDR:1399 LEARS RD & US-131 HWY",
        "MADDR:1399 LEARS RD",
        "INFO:RP CALLING FROM WALMART.  REPORTING A 2 VEH ACCIDENT AT THE ABOVE INTERSECTION.  UNK FURTHER / <10><13><10>",
        "DATE:07/22/12",
        "TIME:01:18:48");

    doTest("T10",
        "CAD:Msg:FYI: ;VEHICLE FIRE;HSFD;5049 COOK AV/HEYDEY ST;FLAMES SHOWING. RP NOT INVOVLED, SAID THE PPL ARE TRYING TO GET THINGS OUT OF VEH INSTEAD OF GETTING AWAY  [07/22/12 11:25:38 BTACHE]<10><13><10>",
        "CALL:VEHICLE FIRE",
        "SRC:HSFD",
        "ADDR:5049 COOK AV & HEYDEY ST",
        "MADDR:5049 COOK AVE",
        "INFO:FLAMES SHOWING. RP NOT INVOVLED, SAID THE PPL ARE TRYING TO GET THINGS OUT OF VEH INSTEAD OF GETTING AWAY / <10><13><10>",
        "DATE:07/22/12",
        "TIME:11:25:38");

    doTest("T11",
        "CAD:Msg:FYI: ;BRUSH FIRE;CXFD;11550 SEQUANOTA HEIGHTS DR;STATES THERE ARE ASHES AND SMOKE COMING IN HER YARD UNK WHERE FROM BELIEVES ON SEQUANOTA HEIGHTS...NO BURNING PER DNR WEB SITE  [07/22/12 21:38:39 JLEE]<10><13><10>",
        "CALL:BRUSH FIRE",
        "SRC:CXFD",
        "ADDR:11550 SEQUANOTA HEIGHTS DR",
        "INFO:STATES THERE ARE ASHES AND SMOKE COMING IN HER YARD UNK WHERE FROM BELIEVES ON SEQUANOTA HEIGHTS...NO BURNING PER DNR WEB SITE / <10><13><10>",
        "DATE:07/22/12",
        "TIME:21:38:39");

    doTest("T12",
        "CAD:Msg:FYI: ;AMBULANCE;BCAM;223 SILVER ST;[Medical Priority Info] PROBLEM: FEMALE WITH CHEST PAIN    # PATS: 1   AGE: 49   SEX: Female   CONSCIOUS: Yes   BREATHING: Yes  [07/23/12 09:15:45 KMATELSKI]<10><13><10>",
        "CALL:AMBULANCE",
        "SRC:BCAM",
        "ADDR:223 SILVER ST",
        "INFO:FEMALE WITH CHEST PAIN    # PATS: 1   AGE: 49   SEX: Female   CONSCIOUS: Yes   BREATHING: Yes / <10><13><10>",
        "DATE:07/23/12",
        "TIME:09:15:45");

    doTest("T13",
        "3106:CAD:FYI: ;FIRE;CHFD;611 MACKINAW AV;SMOKE POURING OUT OF BACK OF NEIGHBOR`S RES KIDDY CORNER FROM RP`S. RP LIVES ON MACKINAW AV & NEIGHBOR IS ON DRESSER. RP DOE",
        "ID:3106",
        "CALL:FIRE",
        "SRC:CHFD",
        "ADDR:611 MACKINAW AV",
        "MADDR:611 MACKINAW AVE",
        "INFO:SMOKE POURING OUT OF BACK OF NEIGHBOR`S RES KIDDY CORNER FROM RP`S. RP LIVES ON MACKINAW AV & NEIGHBOR IS ON DRESSER. RP DOE");

    doTest("T14",
        "3108:CAD:FYI: ;FIRE;BCFD;819 WILSON RD S;THERE IS A FIRE BEHIND HER WASHER & DRYER. [08/05/12 23:34:00 SBAVERY]",
        "ID:3108",
        "CALL:FIRE",
        "SRC:BCFD",
        "ADDR:819 WILSON RD S",
        "INFO:THERE IS A FIRE BEHIND HER WASHER & DRYER.",
        "DATE:08/05/12",
        "TIME:23:34:00");
  
  }
  
  public static void main(String[] args) {
    new MIEmmetCountyParserTest().generateTests("T1");
  }
}
