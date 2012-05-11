package net.anei.cadpage.parsers.NY;

import java.util.Properties;

import net.anei.cadpage.parsers.SmartAddressParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/*
Suffolk County, NY
Sender: paging@scfres.com
System: Intergraph

Brentwood
Contact: Jason Pedota <jpedota805@gmail.com>
Sender: 6317967579
TYPE: GAS LEAKS / GAS ODOR (NATURAL / L.P.G.) LOC: 11 BRENTWOOD PKWY BRENTW HOMELESS SHELTER CROSS: PENNSYLVANIA AV / SUFFOLK AV CODE: 60-B-2 TIME: 12:54:16
TYPE: STRUCTURE FIRE LOC: 81 NEW HAMPSHIRE AV NBAYSH  CROSS: E FORKS RD / E 3 AV CODE: 69-D-10 TIME: 16:36:48
TYPE: OPEN BURNING LOC: 65 GRANT AVE BRENTW CROSS: SUFFOLK AVE CODE: 54-C-6 TIME: 18:39:20
TYPE: BLEEDING / LACERATIONS LOC: 462 SPUR DR N NBAYSH  CROSS: WB SSP OFF RAMP-X42N 5TH AV / E 3 AV CODE: 21-A-1 TIME: 03:36:22
TYPE: PREGNANCY / CHILDBIRTH / MISCARRIAGE LOC: 330 MOTOR PKWY HAUPPA:@FELDMAN, KRAMER & MONACO STE 400  CROSS: WASHINGTON AV / MARCUS BLVD C
TYPE: PSYCHIATRIC / ABNORMAL BEHAVIOR / SUICIDE LOC: 200 WIRELESS BLVD HAUPPA: @SOCIAL SERVICES HAUPPAUGE INTERVIEW AREA CROSS: MORELAND RD /

Center Moriches, NY
Contact: William Renzetti <williamrenzetti@gmail.com> (works for Suffolk County 911)
FAINTING (NEAR) LOC: 46 SAUNDERS AV CENTEM:@KINGS CHAPEL CHURCH  CROSS: ROWEIN RD CODE: 31-D-2 TIME: 17:24:12
SEIZURES LOC: 20 TRAINOR AV CENTEM CROSS: SUNRISE HWY S  BERNSTEIN BLVD CODE: 12-C-1 TIME: 03:39:02
ABDOMINAL PAINS LOC: 18 INWOOD RD CENTEM CROSS: UNION AV / BEACHFERN RD CODE: 1-A-1 TIME: 21:21:39
UNKNOWN PROBLEM LOC: 150 CHICHESTER AV CENTEM CROSS: YARMOUTH LN / FROWEIN RD CODE: TIME: 08:01:10
FAINTING (NEAR) LOC: 6 FROWEIN RD EMORIC: @CEDAR LODGE NURSING HOME IN THE DINING ROOM CROSS: WALDEN CT  OAK ST CODE: 31-D-3 TIME: 18:51:38
GAS ODOR (NATURAL  L.P.G.) LOC: MCGRAW ST SHIRLE I/V/O SHIRLEY PLAZA CROSS: GRAND AV / OAK AV CODE: 60-C-2 TIME: 02:06:34
FAINTING (NEAR) LOC: 53-10 LONG TREE LN MORICH: @PINE HILLS SOUTH CLUBHOUSE COMMUNITY ROOM CROSS:    CODE: 31-E-1 TIME: 14:54:18
TYPE: ALARMS LOC: 127 CRYSTAL BEACH BLVD MORICH  CROSS: BEVERLY CT / CODE: 52-B-1C TIME: 21:15:11

Manorville Community Ambulance, NY
Contact: Christopher Greco <cgreco31@gmail.com>
TYPE: PSYCHIATRIC / ABNORMAL BEHAVIOR / SUICIDE LOC: 79 ABBOTT AV MASTIC ***_VIP_***: CROSS: ELGIN ST / FOXCROFT ST CODE: 25-B-6 TIME: 14:36:05

Oakdale
Contact: Michael Heerbrandt <wsfd145@gmail.com>
Sender: PAGING@WSFD.COM
TYPE: ALARMS LOC: 100 PATRICIA CT OAKDAL @OAKDALE APARTMENTS    APARTMENT 3 CROSS: RACE PL /  CODE: 52-C-1S TIME: 19:16:55

Contact: Sean Hendrickson <sean.hendrickson5@gmail.com>
FWD: TYPE: STRUCTURE FIRE LOC: 1 WILBUR AV MANORV  CROSS: SOHMER ST /  CODE: 69-D-5 TIME: 17:17:43

Contact: Chris Wagner <cwagner021@gmail.com>
TYPE: MOTOR VEHICLE ACCIDENT  CROSS: CHURCH ST / LAKELAND AV CODE: 29-B-1U TIME: 09:04:01

Contact: Scott Parris <fireteacher@gmail.com>
TYPE: ALARMS LOC: 311 BAY AV EPATCH: @BAY HOUSE  CROSS: NEWINS ST / PARK ST CODE: 52-C-3G TIME: 15:33:58\n\n

Contact: Chris Wagner <cwagner021@gmail.com>
Sender: paging+bncCAAQveHY8wQaBJlKIvM@communityamb.org
TYPE: CHEST PAIN LOC: 3845 VETERANS MEMORIAL HWY RONKON: @HOLIDAY INN RONKONKOMA: @HOLIDAY INN RONKONKOMA:06:12.4418,40:48:13.4 PARKI

Contact: Michael Bellis <bellis.michael@gmail.com>
Sender: paging@scfres.com
TYPE: UNKNOWN PROBLEM LOC: 195 CUBA HILL RD GREENL  CROSS: MANOR RD / DANVILLE DR CODE: 32-B-2 TIME: 17:30:40
TYPE: STRUCTURE FIRE LOC: 1 ARNOLD DR HUNTIN  CROSS: PARTRIDGE LN /  CODE: default TIME: 06:38:03
TYPE: STRUCTURE FIRE LOC: 6 MAJESTIC DR DIXHIL  CROSS: ROYAL LN / REGENCY LN CODE: 69-D-6 TIME: 02:22:25
TYPE: FALLS LOC: 37 WATERSIDE AV NORTHP  CROSS: MONROE ST / WILLIS ST CODE: 17-B-3 TIME: 13:40:45

Contact: Stuart NOHEJO <stupman1975@gmail.com>
Sender: paging@scfres.com
TYPE: HEADACHE LOC: 68 FOREST AV SHIRLE  CROSS: DAWN DR / WINSTON DR CODE: 18-C-2 TIME: 16:09:54\n\n

Contact: PJ Llobell <pllobell@gmail.com>
Sender: paging@scfres.com
TYPE: RESPIRATORY LOC: 16 TEAL CRSN GREATR   CROSS:  / WIDGEON CT CODE: 6-D-1 TIME: 15:59:03

Contact: Michael Bellis <bellis.michael@gmail.com>
TYPE: STRUCTURE FIRE LOC: 605 7 AV ENORTH   CROSS: 6 ST / OWEN CT CODE: 69-D-6 TIME: 11:34:38

Contact: Joe Sauerwein <jfsauerwein@gmail.com>
Sender: paging@scfres.com
TYPE: ALARMS LOC: 32 MONTAUK HWY BLUEPO : @BURGER KING BLUE POINT  CROSS: BELL AV / BUFFIN LN CODE: 52-C-3P TIME: 13:20:43\r\n\r\n\r

Contact: james abrahall <boocs94@gmail.com>
Sender: paging@scfres.com
TYPE: SICK LOC: 54 MANHASSET AV FARMVI   CROSS: ADIRONDACK DR / S BICYCLE PATH CODE: 26-A-1 TIME: 17:52:15

Contact: support@active911.com
TYPE: RESPIRATORY LOC: 14 RITA DR MOUNTS CROSS: WESTCLIFF DR / JONI DR CODE:6-D-1 TIME: 23:35:41
TYPE: BLEEDING / LACERATIONS LOC: 103 ROE LN PORTJE CROSS: VILLAGE WOODS RD / CODE: 21-B-1 TIME: 13:18:59
TYPE: MOTOR VEHICLE ACCIDENT WB RT 347 CROSS: NESCONSET HWY / JAYNE BLVD CODE: 29-B-1A TIME: 18:02:43
TYPE: HEADACHE LOC: 39 MANOR DR MILLEP CROSS: GRAHAM CT / OAKS DR CODE: 18-C-2 TIME:21:50:53
TYPE: BURNS (SCALDS) / EXPLOSION LOC: 9 ARBOR RIDGE LN CENTRE CROSS: VALIANT DR / VALIANT DR CODE: 7-A-2 TIME: 03:05:16
TYPE: BLEEDING / LACERATIONS LOC: 80 GRENADA CIR MOUNTS RIGHT SIDE APT CROSS: CARDIFF RUN / SEVILLA WK CODE: 21-D-3 TIME: 08:47:11
TYPE: RESPIRATORY LOC: 255 OAKLAND AV PORTJE :@PAX CHRISTI CROSS: HIGHLAND BLVD / ROUTE 25A CODE: 6-C-1A TIME: 08:55:59

*/

public class NYSuffolkCountyAParser extends SmartAddressParser {

  private static final String[] KEYWORDS = new String[]{"TYPE", "LOC", "CROSS", "CODE", "TIME"};
  
  public NYSuffolkCountyAParser() {
    super(CITY_TABLE, "SUFFOLK COUNTY", "NY");
  }
  
  @Override
  public String getFilter() {
    return "paging@scfres.com,@communityamb.org";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    // Anything starting with 3 asterisks is the similar but different (B) variant
    if (body.startsWith("***")) return false;

    // Some formats cut the initial TYPE: code
    if (body.startsWith("FWD:")) body = body.substring(4).trim();
    if (!body.startsWith("TYPE:")) body = "TYPE:" + body;

    Properties props = parseMessage(body, KEYWORDS);
    
    data.strCall = props.getProperty("TYPE");
    if (data.strCall == null) return false;

    data.strCross = props.getProperty("CROSS", "");
    
    String sAddress = props.getProperty("LOC");
    if (sAddress == null) {
      if (data.strCross.length() == 0) return false;
      parseAddress(data.strCross, data);
      data.strCross = "";
    } else {
      sAddress = sAddress.replaceAll(":", " ");
      int pt = sAddress.indexOf('@');
      if (pt >= 0) {
        String sPlace = sAddress.substring(pt+1).trim();
        int pt2 = sPlace.indexOf('@');
        if (pt2 >= 0) {
          data.strSupp = sPlace.substring(pt2+1).trim();
          sPlace = sPlace.substring(0,pt2).trim();
        }
        data.strPlace = sPlace;
        sAddress = sAddress.substring(0,pt).trim();
        pt = sAddress.lastIndexOf(' ');
        if (pt >= 0) {
          data.strCity = sAddress.substring(pt+1);
          sAddress = sAddress.substring(0, pt).trim();
        }
        parseAddress(sAddress, data);
      }
      else {
        parseAddress(StartType.START_ADDR, sAddress, data);
        data.strPlace = getLeft();
      }
    }
    
    data.strCode = props.getProperty("CODE", "");
    data.strCity = convertCodes(data.strCity, CITY_TABLE);
    String sTime = props.getProperty("TIME", "");
    if (sTime.length() > 5 && sTime.length() < 8) sTime = sTime.substring(0,5);
    if (sTime.length() >= 5) data.strTime = sTime;
    
    return true;
  }

  private static final Properties CITY_TABLE = buildCodeTable(new String[]{
      "AMAGAN",  "AMAGANSETT",
      "AMITYV",  "AMITYVILLE",
      "AQUEBO",  "AQUEBOGUE",
      "ASHARO",  "ASHAROKEN",
      "ATLANT",  "ATLANTIQUE",
      "BABYLO",  "BABYLON",
      "BAITIH",  "BAITING HOLLOW",
      "BARRET",  "BARRET HOLLOW",
      "BAYPOR",  "BAYPORT",
      "BAYSHO",  "BAY SHORE",
      "BAYVIL",  "BAYVILLE",
      "BAYWOO",  "BAYWOOD",
      "BELLET",  "BELLE TERRE",
      "BELLMO",  "BELLMORE",
      "BELLPO",  "BELLPORT",
      "BETHPA",  "BETHPAGE",
      "BLUEPB",  "BLUE POINT BEACH",
      "BLUEPO",  "BLUE POINT",
      "BOHEMI",  "BOHEMIA",
      "BRENTW",  "BRENTWOOD",
      "BRIDGE",  "BRIDGEHAMPTON",
      "BRIGHT",  "BRIGHTWATERS",
      "BROOKH",  "BROOKHAVEN",
      "CALVER",  "CALVERTON",
      "CAPTRE",  "CAPTREE",
      "CENTEM",  "CENTER MORICHES",
      "CENTPO",  "CENTERPORT",
      "CENTRE",  "CENTEREACH",
      "CENTRI",  "CENTRAL ISLIP",
      "CHERRG",  "CHERRY GROVE",
      "COLDSH",  "COLD SPRING HARBOR",
      "COMMAC",  "COMMACK",
      "COPIAG",  "COPIAGUE",
      "CORAM",   "CORAM",
      "CORNEE",  "CORNEILLE ESTATES",
      "CUTCHO",  "CUTCHOGUE",
      "DAVISP",  "DAVIS PARK",
      "DEERPA",  "DEER PARK",
      "DERINH",  "DERING HARBOR",
      "DIXHIL",  "DIX HILLS",
      "DUNEWO",  "DUNEWOOD",
      "EASTPO",  "EASTPORT",
      "EATONN",  "EATONS  NECK",
      "EFARMI",  "EAST FARMINGDALE",
      "EFIREI",  "EAST FIRE ISLAND",
      "EHAMPT",  "EAST HAMPTON",
      "EHAMPV",  "EAST HAMPTION VILLAGE",
      "EISLIP",  "EAST ISLIP",
      "ELWOOD",  "ELWOOD",
      "EMARIO",  "EAST MARION",
      "EMORIC",  "EAST MORICHES",
      "ENORTH",  "EAST NORTHPORT",
      "EPATCH",  "EAST PATCHOGUE",
      "EQUOGU",  "EAST QUOGUE",
      "ESHORE",  "EAST SHOREHAM",
      "FAIRHA",  "FAIR HARBOR",
      "FARMDA",  "FARMINGDALE",
      "FARMVI",  "FARMINGVILLE",
      "FINS",    "FI NATIONAL SEASHORE",
      "FIREIP",  "FIRE ISLAND PINES",
      "FISHEI",  "FISHER ISLAND",
      "FLANDE",  "FLANDERS",
      "FORTSA",  "FORT SALONGA",
      "GARDII",  "GARDINERS ISLAND",
      "GILGOB",  "GILGO BEACH",
      "GORDOH",  "GORDON HEIGHTS",
      "GRANGI",  "GRAND GULL ISLAND",
      "GREATR",  "GREAT RIVER",
      "GREENL",  "GREENLAWN",
      "GREENP",  "GREENPORT",
      "GREENW",  "GREENPORT WEST",
      "HALESI",  "HALESITE",
      "HAMPTB",  "HAMPTON BAYS",
      "HAUPPA",  "HAUPPAUGE",
      "HEADHA",  "HEAD OF THE HARBOR",
      "HICKSV",  "HICKSVILLE",
      "HOLBRO",  "HOLBROOK",
      "HOLTSV",  "HOLTSVILLE",
      "HUNTIB",  "HUNTINGTON BAY",
      "HUNTIN",  "HUNTINGTON",
      "HUNTIS",  "HUNTINGTON STATION",
      "ISLAND",  "ISLANDIA",
      "ISLIP",   "ISLIP",
      "ISLIPT",  "ISLIP TERRACE",
      "JAMESP",  "JAMESPORT",
      "JONESB",  "JONES BEACH",
      "KINGPP",  "KINGS PARK PSYCHIATRIC",
      "KINGSP",  "KINGS PARK",
      "KISMET",  "KISMET",
      "LAKEGR",  "LAKE GROVE",
      "LAKERO",  "LAKE RONKONKOMA",
      "LAUREL",  "LAUREL HOLLOW",
      "LEVITT",  "LEVIGTTOWN",
      "LINDEN",  "LINDENHURST",
      "LITTGI",  "LITTLE GULL ISLAND",
      "LLOYDH",  "LLOYD HARBOR",
      "LONELY",  "LONELYVILLE",
      "MANORV",  "MANORVILLE",
      "MASSAP",  "MASSAPEQUA",
      "MASSPA",  "MASSAPEQUA PARK",
      "MASTIB",  "MASTIC BEACH",
      "MASTIC",  "MASTIC",
      "MATTIT",  "MATTITUCK",
      "MEDFOR",  "MEDFORD",
      "MELVIL",  "MELVILLE",
      "MIDDLI",  "MIDDLE ISLAND",
      "MILLEP",  "MILLER PLACE",
      "MILLNE",  "MILL NECK",
      "MONTAU",  "MO NTAUK",
      "MORICH",  "MORICHES",
      "MOUNTS",  "MT SINAI",
      "NAMITY",  "NORTH AMITYVILLE",
      "NAPEAG",  "NAPEAGUE",
      "NBABYL",  "NORTH BABYLON",
      "NBAYSH",  "NORTH BAY SHORE",
      "NBELLP",  "NORTH BELLPORT",
      "NESCON",  "NESCONSET",
      "NEWSUF",  "NEW SUFFOLK",
      "NGREAR",  "NORTH GREAT RIVER",
      "NHAVEN",  "NORTH HAVEN",
      "NISSEQQ",  "NISSEQUOGUE",
      "NLINDE",  "NORTH LINDENHURST",
      "NORTHP",  "NORTHPORT",
      "NORTHV",  "NORTHVILLE",
      "NORTVH",  "NORTHPORT VA",
      "NOYACK",  "NOYACK",
      "NPATCH",  "NORTH PATCHOGUE",
      "NSEA",    "NORTH SEA",
      "NWHARB",  "NORTH WEST HARBOR",
      "OAKBEA",  "OAK BEACH",
      "OAKDAL",  "OAKDALE",
      "OAKISL",  "OAK ISLAND",
      "OAKLEY",  "OAKLEYVILLE",
      "OCEABE",  "OCEAN BEACH",
      "OCEABP",  "OCEAN BAY PARK",
      "OCEANR",  "OCEAN RIDGE",
      "OLDBET",  "OLD BETHPAGE",
      "OLDFIE",  "OLD FIELD",
      "ORIENT",  "ORIENT",
      "OYSTERB",  "OYSTER BAY",
      "PATCHO",  "PATCHOGUE",
      "PECONI",  "PECONIC",
      "PILGRP",  "PILGRIM PSYCHIATRIC",
      "PLAINV",  "PLAINVIEW",
      "PLUMIS",  "PLUM ISLAND",
      "POINTO",  "POINT O’WOODS",
      "POOSIR",  "POOSPATUCK INDIAN RESERVATION",
      "POQUOT",  "POQUOTT",
      "PORTJE",  "PORT JEFFERSON",
      "PORTJS",  "PORT JEFFERSON STATION",
      "QUIOGU",  "QUIOGUE",
      "QUOGUE",  "QUOGUE",
      "REMSEN",  "REMSENBERG-SPEONK",
      "RIDGE",  "RIDGE",
      "RIVERH",  "RIVERHEAD",
      "RIVERS",  "RIVERSIDE",
      "ROBBII",  "ROBBINS ISLAND",
      "ROBBIR",  "ROBBINS REST",
      "ROBERM",  "ROBERT MOSES PARK",
      "ROCKYP",  "ROCKY POINT",
      "RONKON",  "RONKONKOMA",
      "SAGAPO",  "SAGAPONACK",
      "SAHGAR",  "SAG HARBOR",
      "SAILOH",  "SAILORS HAVEN",
      "SAINTJ",  "ST JAMES",
      "SALTAI",  "SALTAIRE",
      "SAYVIL",  "SAYVILLE",
      "SEAFOR",  "SEAFORD",
      "SEAVIE",  "SEAVIEW",
      "SELDEN",  "SELDEN",
      "SETAES",  "SETAKET-EAST SETAUKET",
      "SHELIH",  "SHELTHER ISLAND HEIGHTS",
      "SHELTI",  "SHELTER ISLAND",
      "SHINIR",  "SHINNECOCK INDIAN RESERVATION",
      "SHINNH",  "SHINNECOCK HILLS",
      "SHIRLE",  "SHIRLEY",
      "SHOREH",  "SHOREHAM",
      "SHUNTI",  "SOUTH HUNTINGTON",
      "SMITHP",  "SMITH POINT",
      "SMITHT",  "SMITHTOWN",
      "SOUNDB",  "SOUND BEACH",
      "SOUTHA",  "SOUTHAMPTON",
      "SOUTHO",  "SOUTHOLD",
      "SOUTHV",  "SOUTHAMPTON VILLAGE",
      "SPRING",  "SPRINGS",
      "STONYB",  "STONY BROOK",
      "SUFFDC",  "SUFFOLK DEVELOPMENTAL CENTER",
      "SUNKEF",  "SUNKEN FOREST",
      "SUNYFA",  "SUNY FARMINGDALE",
      "SUNYSB",  "SUNY STONY BROOK",
      "SYOSSE",  "SYOSSET",
      "TALISM",  "TALISMAN",
      "TERRYV",  "TERRYVILLE",
      "TOBAY",  "TOBAY",
      "UPTON",  "UPTON",
      "USCGST",  "US COAST GUARD STATION",
      "VILLAB",  "VILLAGE OF THE BRANCH",
      "WADINR",  "WADING RIVER",
      "WAINSC",  "WAINSCOTT",
      "WANTAG",  "WANTAGH",
      "WATCHH",  "WATCH HILL",
      "WATERI",  "WATER ISLAND",
      "WATERM",  "WATER MILL",
      "WBABYL",  "WEST BABYLON",
      "WBAYSH",  "WEST BAY SHORE",
      "WFIREI",  "WEST FIRE ISLAND",
      "WHAMPB",  "WESTHAMPTON BEACH",
      "WHAMPD",  "WESTHAMPTON DUNES",
      "WHAMPT",  "WESTHAMPTON",
      "WHEATH",  "WHEATLEY HEIGHTS",
      "WHILLS",  "WEST HILLS",
      "WISLIP",  "WEST ISLIP",
      "WOODBU",  "WOODBURY",
      "WSAYVI",  "WEST SAYVILLE",
      "WYANDA",  "WYANDANCH",
      "YAPHAN",  "YAPHANK"
  });
}
