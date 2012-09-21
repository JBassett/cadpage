package net.anei.cadpage.parsers.VA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class VAWashingtonCountyParserTest extends BaseParserTest {
  
  public VAWashingtonCountyParserTest() {
    setParser(new VAWashingtonCountyParser(), "WASHINGTON COUNTY", "VA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "NSCOTT:13604 PRICES BRIDGE RD GLADE SPRING SANTANA GALLIHER 2766080200 1220015 23:27:05 MVA IVE BEEN IN A WRECK, I HIT A TREE, MY CAR IS TOTALLED, THE VEHICLE I",
        "ADDR:13604 PRICES BRIDGE RD",
        "CITY:GLADE SPRING",
        "NAME:SANTANA GALLIHER",
        "PHONE:2766080200",
        "ID:1220015",
        "TIME:23:27:05",
        "CALL:MVA IVE BEEN IN A WRECK",
        "INFO:I HIT A TREE, MY CAR IS TOTALLED, THE VEHICLE I");

    doTest("T2",
        "LSTRINGER:215 SWEET BRIER ST GLADE SPRING JASON 2764518304 1219928 09:15:17 SICK CALL 30YO FEMALE IS VOMITING, HAS BACK PAIN, FEELS FAINT.",
        "ADDR:215 SWEET BRIER ST",
        "CITY:GLADE SPRING",
        "NAME:JASON",
        "PHONE:2764518304",
        "ID:1219928",
        "TIME:09:15:17",
        "INFO:SICK CALL 30YO FEMALE IS VOMITING, HAS BACK PAIN, FEELS FAINT.");

    doTest("T3",
        "BDULA:228 MIMOSA ST APT A GLADE SPRING SPRINT NEXTEL - CDMA 2764926648 1219900 00:21:44 SICK CALL HE IS STILL SICK AND THROWING UP",
        "ADDR:228 MIMOSA ST",
        "APT:A",
        "CITY:GLADE SPRING",
        "NAME:SPRINT NEXTEL - CDMA",
        "PHONE:2764926648",
        "ID:1219900",
        "TIME:00:21:44",
        "INFO:SICK CALL HE IS STILL SICK AND THROWING UP");

    doTest("T4",
        "LSTRINGER:PETRO TRUCK CENTER 12433 MAPLE ST GLADE SPRING CARDINAL TRAVEL CENTER/JONATHAN 2764296000 1219922 07:26:55 AMBULANCE NEEDED INCOHERENT FEMALE, POSS.",
        "PLACE:PETRO TRUCK CENTER",
        "ADDR:12433 MAPLE ST",
        "CITY:GLADE SPRING",
        "NAME:CARDINAL TRAVEL CENTER / JONATHAN",
        "PHONE:2764296000",
        "ID:1219922",
        "TIME:07:26:55",
        "INFO:AMBULANCE NEEDED INCOHERENT FEMALE, POSS.");

    doTest("T5",
        "MEVANS:11585 MOUNT CALM DR APT 8 GLADE SPRING WEBB,CYNTHIA 2766950260 1219667 14:52:22 SICK CALL 62 YO MALE SHORT OF BREATH AND HURTS TO COUGH",
        "ADDR:11585 MOUNT CALM DR",
        "APT:8",
        "CITY:GLADE SPRING",
        "NAME:WEBB,CYNTHIA",
        "PHONE:2766950260",
        "ID:1219667",
        "TIME:14:52:22",
        "INFO:SICK CALL 62 YO MALE SHORT OF BREATH AND HURTS TO COUGH");

    doTest("T6",
        "NSCOTT:13604 PRICES BRIDGE RD GLADE SPRING SANTANA GALLIHER 2766080200 1220015 23:27:05 MVA IVE BEEN IN A WRECK, I HIT A TREE, MY CAR IS TOTALLED, THE VEHICLE I",
        "ADDR:13604 PRICES BRIDGE RD",
        "CITY:GLADE SPRING",
        "NAME:SANTANA GALLIHER",
        "PHONE:2766080200",
        "ID:1220015",
        "TIME:23:27:05",
        "CALL:MVA IVE BEEN IN A WRECK",
        "INFO:I HIT A TREE, MY CAR IS TOTALLED, THE VEHICLE I");

    doTest("T7",
        "TRAINEE_1:33137 LIGHTHOUSE HILL GLADE SPRING MILLER, BRENDA 2764964267 1219961 15:38:46 TREE DOWN CALLER ADVISED THAT A TREE HAS FALLEN ON THE POWER LINE AND BDULA:228 MIMOSA ST GLADE SPRING SPRINT NEXTEL - CDMA 1219760 01:25:30 SICK CALL HE IS THROWING UP HE IS 51 YEARS OLD",
        "ADDR:33137 LIGHTHOUSE HILL",
        "CITY:GLADE SPRING",
        "NAME:MILLER, BRENDA",
        "PHONE:2764964267",
        "ID:1219961",
        "TIME:15:38:46",
        "INFO:TREE DOWN CALLER ADVISED THAT A TREE HAS FALLEN ON THE POWER LINE AND BDULA:228 MIMOSA ST GLADE SPRING SPRINT NEXTEL - CDMA 1219760 01:25:30 SICK CALL HE IS THROWING UP HE IS 51 YEARS OLD");

    doTest("T8",
        "DHARTSOCK:304 EVERGREEN ST GLADE SPRING SMITH, P N 2764295639 1219281 09:38:45 AMBULANCE NEEDED 73 Y/O FEMALE HIGH BP AND HAVING CHANGES IN VISION NEEDS TO G",
        "ADDR:304 EVERGREEN ST",
        "CITY:GLADE SPRING",
        "NAME:SMITH, P N",
        "PHONE:2764295639",
        "ID:1219281",
        "TIME:09:38:45",
        "INFO:AMBULANCE NEEDED 73 Y/O FEMALE HIGH BP AND HAVING CHANGES IN VISION NEEDS TO G");

    doTest("T9",
        "JMARTIN:32497 CLINCHBURG RD MEADOWVIEW CRUEY, JANE S 2769445787 1218968 09:18:26 STROKE CALLER STATES HIS DAD IS HAVING A STROKE",
        "ADDR:32497 CLINCHBURG RD",
        "CITY:MEADOWVIEW",
        "NAME:CRUEY, JANE S",
        "PHONE:2769445787",
        "ID:1218968",
        "TIME:09:18:26",
        "INFO:STROKE CALLER STATES HIS DAD IS HAVING A STROKE");

    doTest("T10",
        "DHARTSOCK:30077 HILLMAN HWY MEADOWVIEW ASSISTED LVNG,SERENITY 2769444300 1218276 07:15:01 SEIZURES 39 Y/O FEMALE SEIZURES NEED AN AMBULAN",
        "ADDR:30077 HILLMAN HWY",
        "CITY:MEADOWVIEW",
        "NAME:ASSISTED LVNG,SERENITY",
        "PHONE:2769444300",
        "ID:1218276",
        "TIME:07:15:01",
        "INFO:SEIZURES 39 Y/O FEMALE SEIZURES NEED AN AMBULAN");

    doTest("T11",
        "OARNOLD:12433 MAPLE ST GLADE SPRING CARDINAL TRAVEL CENTER 2764296000 1215775 11:35:56 SEIZURES HAVE A MALE IN THE BATH ROOM HAVING A SEIZURES NP",
        "ADDR:12433 MAPLE ST",
        "CITY:GLADE SPRING",
        "NAME:CARDINAL TRAVEL CENTER",
        "PHONE:2764296000",
        "ID:1215775",
        "TIME:11:35:56",
        "INFO:SEIZURES HAVE A MALE IN THE BATH ROOM HAVING A SEIZURES NP");

    doTest("T12",
        "OARNOLD:13168 MEADOWVIEW SQ MEADOWVIEW SOUTHWEST VIRGINIA COMMUNITY/DONNA KENESTER 2765251632 1212903 16:11:00 HEADACHE BAD HEAD ACHE NP",
        "ADDR:13168 MEADOWVIEW SQ",
        "CITY:MEADOWVIEW",
        "NAME:SOUTHWEST VIRGINIA COMMUNITY / DONNA KENESTER",
        "PHONE:2765251632",
        "ID:1212903",
        "TIME:16:11:00",
        "CALL:HEADACHE BAD HEAD ACHE NP");

    doTest("T13",
        "MEVANS:33573 CROWEVILLE DR GLADE SPRING BAILEY, JOHNNY C 2764295596 1218704 20:34:45 SICK CALL 83YO MALE BAD STOMACH ISSUES AND HURTING IN H",
        "ADDR:33573 CROWEVILLE DR",
        "CITY:GLADE SPRING",
        "NAME:BAILEY, JOHNNY C",
        "PHONE:2764295596",
        "ID:1218704",
        "TIME:20:34:45",
        "INFO:SICK CALL 83YO MALE BAD STOMACH ISSUES AND HURTING IN H");

    doTest("T14",
        "GGROSS:530 SHENANDOAH DR GLADE SPRING ALLEN STANLEY 1222084 00:25:28 SICK CALL MY DAD CAME HOME YESTERDAY FROM A PROCEDURE AT THE HOSP. HIS BLOOD PRESSURE IS VE",
        "ADDR:530 SHENANDOAH DR",
        "CITY:GLADE SPRING",
        "NAME:ALLEN STANLEY",
        "ID:1222084",
        "TIME:00:25:28",
        "INFO:SICK CALL MY DAD CAME HOME YESTERDAY FROM A PROCEDURE AT THE HOSP. HIS BLOOD PRESSURE IS VE");

    doTest("T15",
        "JMARTIN:29059 RIVERMONT DR MEADOWVIEW BRIAN 2762745374 1221351 10:08:52 SMOKE COMPLAINT CALLER STATED THERE IS BLACK SMOKE COMING FROM A BARN AT THIS ADDRESS.",
        "ADDR:29059 RIVERMONT DR",
        "CITY:MEADOWVIEW",
        "NAME:BRIAN",
        "PHONE:2762745374",
        "ID:1221351",
        "TIME:10:08:52",
        "INFO:SMOKE COMPLAINT CALLER STATED THERE IS BLACK SMOKE COMING FROM A BARN AT THIS ADDRESS.");

    doTest("T16",
        "GGROSS:223 MIMOSA ST GLADE SPRING GOLDY FLETCHER 2766145930 1218892 15:55:06 DIFFICULTY BREATHING MY MOTHER HAS SHORTNESS OF BREATH, SHE IS",
        "ADDR:223 MIMOSA ST",
        "CITY:GLADE SPRING",
        "NAME:GOLDY FLETCHER",
        "PHONE:2766145930",
        "ID:1218892",
        "TIME:15:55:06",
        "INFO:DIFFICULTY BREATHING MY MOTHER HAS SHORTNESS OF BREATH, SHE IS");

    doTest("T17",
        "KHAYES:1 INTERSTAE 81 26 SB OFFRAMP SWEET, KEVIN 5405211892 1220763 21:08:44 FIRE BRUSH FIRE RIGHT AT THE INTERSECTION OF EXIT 26 SB AND COLLEGE DR",
        "ADDR:1 INTERSTAE 81 26 SB OFFRAMP",
        "MADDR:1 INTERSTAE 81 26 OFFRAMP",
        "NAME:SWEET, KEVIN",
        "PHONE:5405211892",
        "ID:1220763",
        "TIME:21:08:44",
        "INFO:FIRE BRUSH FIRE RIGHT AT THE INTERSECTION OF EXIT 26 SB AND COLLEGE DR");

    doTest("T18",
        "OARNOLD:120 MAPLE ST GLADE SPRING ROBERT CRAFT 5403090091 1224127 11:26:10 MVA ADV HE IS IN A WRECK AT THE INT OF MAPLE ST AND W GLADE ST",
        "ADDR:120 MAPLE ST",
        "CITY:GLADE SPRING",
        "NAME:ROBERT CRAFT",
        "PHONE:5403090091",
        "ID:1224127",
        "TIME:11:26:10",
        "INFO:MVA ADV HE IS IN A WRECK AT THE INT OF MAPLE ST AND W GLADE ST");
  }
  
  public static void main(String[] args) {
    new VAWashingtonCountyParserTest().generateTests("T1", "PLACE ADDR APT CITY NAME PHONE ID TIME CALL INFO");
  }
}