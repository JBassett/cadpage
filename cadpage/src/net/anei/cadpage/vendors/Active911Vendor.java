package net.anei.cadpage.vendors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import android.net.Uri;
import net.anei.cadpage.R;
import net.anei.cadpage.parsers.MsgParser;

class Active911Vendor extends Vendor {
  
  Active911Vendor() {
    super(R.string.active911_title,
           R.string.active911_summary,
           R.string.active911_text,
           R.drawable.active_911_vendor,
           R.drawable.active_911_logo,
           "https://www.active911.com",
           ">A91",
           null);
  }

  @Override
  boolean isSponsored() {
    return true;
  }

  @Override
  boolean isAvailable() {
    return true;
  }
  
  @Override
  String getResponseMenu(int index) {
    switch (index) {
    case 1:
      return "R=Respond;A=Arrive;Y=Available;N=Unavailable;C=Cancel";
    
    default:
      return null;
    }
  }

  @Override
  Uri getBaseURI(String req) {
    Uri uri = super.getBaseURI();
    if (req.equals("register") || req.equals("info") || req.equals("profile")) {
      return uri.buildUpon().appendPath("cadpage_registration").build();
    } else {
      return uri.buildUpon().appendPath("interface").appendPath("cadpage_api.php").build();
    }
  }

  @Override
  boolean isVendorAddress(String address) {
    if (address.startsWith("+")) address =address.substring(1);
    return PHONE_SET.contains(address);
  }
  
  @Override
  String[] convertLocationCode(String location) {
    String missingParsers = null;
    StringBuilder sb = new StringBuilder();
    Set<String> parserSet = new HashSet<String>();
    
    for (String loc : location.split(",")){
      loc = loc.trim();
      if (loc.contains("/")) {
        String newLoc = POLY_CODE_TABLE.getProperty(loc);
        if (newLoc == null) {
          if (missingParsers == null) {
            missingParsers = loc;
          } else {
            missingParsers = missingParsers + ',' + loc;
          }
          newLoc = "General";
        }
        if (newLoc.equals("N/A")) continue;
        loc = newLoc;
      }
      if (parserSet.add(loc)) {
        if (sb.length() > 0) sb.append(',');
        sb.append(loc);
      }
    }
    return new String[]{sb.toString(), missingParsers};
  }

  private static final Set<String> PHONE_SET = new HashSet<String>(Arrays.asList(new String[]{
      "15123376259",
      "19145173586",
      "17272191279",
      "15417047704",
      "18434800223",
      "17172203767",
      "13364058803",
      "17783836218",
      "12027690862",
      "12032083335",
      "12052010901",
      "12072093315",
      "12706810905",
      "12765240572",
      "13046587002",
      "13072222635",
      "13134010041",
      "13172967331",
      "13603424100",
      "14012973063",
      "14029881004",
      "14046926092",
      "14052534266",
      "14062244055",
      "14242208369",
      "14433202484",
      "14805356958",
      "15013131847",
      "15046621719",
      "15052065036",
      "15132024579",
      "15744008669",
      "16013452163",
      "16052207124",
      "16086207759",
      "16093087467",
      "16122000004",
      "16156252978",
      "16363233043",
      "16418470032",
      "16783903000",
      "17012044024",
      "17196025911",
      "17572062724",
      "17736146018",
      "17752307392",
      "18019006459",
      "18022304149",
      "19134989068",
      "19783931289"
  }));
  
  private static Properties POLY_CODE_TABLE = MsgParser.buildCodeTable(new String[]{
      "CA/AB/ClearwaterCounty",           "ZCAABClearwaterCounty",
      "CA/AB/highriver",                  "ZCAABCanmore",
      "CA/AB/RedDeerCounty",              "ZCAABRedDeerCounty",
      "CA/BC/MidIsland",                  "ZCABCMidIslandRegion",
      "CA/BC/Nanaimo",                    "ZCABCNanamino",
      "CA/QC/Quebec",                     "ZCAQCQuebec",
      "NZ/NI/Auckland",                   "ZNZNewZealand",
      "NZ/NI/Northland",                  "ZNZNewZealand",
      "NZ/NI/Waikato",                    "ZNZNewZealand",
      "US/AL/AthensLimestoneCounty",      "ALLimestoneCounty",
      "US/AL/CalhounCounty",              "ALCalhounCounty",
      "US/AL/ChambersCounty",             "ALChambersCounty",
      "US/AL/Cherokee",                   "ALCherokee",
      "US/AL/ChiltonCounty",              "ALChiltonCounty",
      "US/AL/CityofHomewood",             "ALJeffersonCountyB",
      "US/AL/CityofPelham",               "ALPelham",
      "US/AL/CleburneCounty",             "ALCleburneCounty",
      "US/AL/CoffeeCounty",               "ALCoffeeCounty",
      "US/AL/Colbert",                    "ALColbertCountyParser",
      "US/AL/Hoover",                     "ALHoover",
      "US/AL/MarshallCounty",             "ALMarshallCounty",
      "US/AL/MobileCounty",               "ALMobileCounty",
      "US/AL/MorganCounty",               "ALMorganCounty",
      "US/AL/Shelby",                     "ALShelbyCounty",
      "US/AL/TalladegaCounty",            "ALTalladega",
      "US/AL/Tallapoosa911",              "StandardA",
      "US/AR/CityofRogers",               "ARBentonCountyB",
      "US/AR/LifeNetEms",                 "ARGarlandCounty",
      "US/AZ/NavajoCounty",               "AZNavajoCounty",
      "US/CA/AmadorCounty",               "CAAmadorCounty",
      "US/CA/Bducad",                     "CASanBernardinoCountyC",
      "US/CA/Btucad",                     "CAButteCounty",
      "US/CA/CalaverasCounty",            "CACalaverasCounty",
      "US/CA/CDFandGrassValleyECC",       "CAPlacerCountyParser",
      "US/CA/CityofNapa",                 "CANapa",
      "US/CA/CityofPasoRobles",           "CAPasoRobles",
      "US/CA/CityofRocklin",              "CARocklin",
      "US/CA/CityofSLakeTahoe",           "CASouthLakeTahoe",
      "US/CA/ContraCostaCounty",          "CAContraCostaCounty",
      "US/CA/CSB",                        "CASanBernardinoCountyB",
      "US/CA/ElDoradoCounty",             "CAElDoradoCounty",
      "US/CA/FresnoCounty",               "CAFresnoCounty",
      "US/CA/Hanford",                    "CAHanford",
      "US/CA/Hayward",                    "CAHayward",
      "US/CA/Jpapage",                    "CASanDiegoCountyA",
      "US/CA/Kern",                       "CAKernCounty",
      "US/CA/LakeCounty",                 "CALakeCounty",
      "US/CA/LifecommCellPaging",         "CASanJoaquinCounty",
      "US/CA/MendocinoCounty",            "CAMendocinoCounty",
      "US/CA/Monterey",                   "CAMontereyCountyParser",
      "US/CA/OrovillePD",                 "CAOroville",
      "US/CA/PlacerCounty",               "CAPlacerCounty",
      "US/CA/SacramentoFire",             "CASacramentoCounty",
      "US/CA/SanBernardinoCounty",        "CASanBernardinoCountyA",
      "US/CA/SantaClaraCounty",           "CASantaClaraCountyB",
      "US/CA/SantaCruzCounty",            "CASantaCruzCounty",
      "US/CA/SantaCruzlimited",           "CASantaCruzCounty",
      "US/CA/ShastaCom911",               "CAShastaCountyB",
      "US/CA/ShastaCounty",               "CAShastaCountyA",
      "US/CA/SonomaCounty",               "CASonomaCounty",
      "US/CA/StanislausCounty",           "CAStanislausCounty",
      "US/CA/Suisun",                     "CASuisun",
      "US/CO/AdamsCounty",                "COAdamsCounty",
      "US/CO/Bretsaps",                   "COBoulderCountyB",
      "US/CO/CityofDurango",              "COLaPlataCounty",
      "US/CO/CityofGolden",               "COGolden",
      "US/CO/CityofLoveland",             "COLarimerCountyA",
      "US/CO/ColoradoSprings",            "COElPasoCountyB",
      "US/CO/DeltaCountyAmbulance",       "CODeltaCounty",
      "US/CO/DouglasCounty",              "CODouglasCountyA",
      "US/CO/ElPasoCounty",               "COElPasoCountyA",
      "US/CO/GarfieldCounty",             "COGarfieldCounty",
      "US/CO/MorganCounty",               "COMorganCounty",
      "US/CO/NWSAlert",                   "StandardNationalWeatherService",
      "US/CO/OlatheFire",                 "Cadpage2",
      "US/CO/USAMobility",                "CODouglasCountyB",
      "US/CO/WeldCounty",                 "COWeldCounty",
      "US/CO/WestMetroFire",              "COJeffersonCountyA",
      "US/CT/Cheshire",                   "CTCheshire",
      "US/CT/CityofLedyard",              "CTNewLondonCounty",
      "US/CT/CityofStamford",             "CTStamford",
      "US/CT/DMS",                        "CTFairfieldCountyB",
      "US/CT/EastLyme",                   "CTEastLyme",
      "US/CT/Groton",                     "CTGroton",
      "US/CT/LitchfieldCounty",           "CTLitchfieldCountyA",
      "US/CT/OldSaybrook",                "CTOldSaybrook",
      "US/CT/Uconn",                      "CTTollandCountyB",
      "US/CT/Valleyshore911",             "CTMiddlesexCounty",
      "US/CT/WaterfordFD",                "CTWaterfordTownParser",
      "US/DE/CADCentral",                 "DEKentCountyE",
      "US/DE/ChiefMsg",                   "DESussexCountyA",
      "US/DE/Dover",                      "DEKentCounty",
      "US/DE/NewCastleCounty",            "DENewCastleCounty",
      "US/DE/SussexCounty",               "DESussexCountyB",
      "US/FL/BayCounty",                  "FLBayCounty",
      "US/FL/CalhounCounty",              "FLCalhounCounty",
      "US/FL/CharlotteCountySheriff",     "FLCharlotteCounty",
      "US/FL/CollierCounty",              "FLCollierCounty",
      "US/FL/Hendry",                     "FLHendryCounty",
      "US/FL/LongboatKey",                "FLManateeCounty",
      "US/FL/ManateeCounty",              "FLManateeCounty",
      "US/FL/PalmBeachCounty",            "FLPalmBeachCounty",
      "US/FL/RegionalPsi",                "FLBrowardCounty",
      "US/FL/SeminoleCounty",             "FLSeminoleCounty",
      "US/GA/BarrowCounty",               "GABarrowCounty",
      "US/GA/EffinghamCounty",            "GAEffinghamCounty",
      "US/GA/ForsythCounty",              "GAForsythCounty",
      "US/GA/HabershamCounty",            "GAHabershamCounty",
      "US/GA/RabunCounty",                "GARabunCounty",
      "US/GA/SouthsideFire",              "GAChathamCounty",
      "US/GA/WaltonCounty",               "GAWaltonCounty",
      "US/GA/WhiteCounty",                "GAWhiteCounty",
      "US/IA/CityofBurlington",           "IADesMoinesCounty",
      "US/IA/PolkCounty",                 "IAPolkCounty",
      "US/IA/WorthCounty",                "IAWorthCounty",
      "US/ID/BlaineCounty",               "IDBlaineCounty",
      "US/ID/KootenaiCounty",             "IDKootenaiCountyParser",
      "US/IL/AdamsCounty",                "ILAdamsCounty",
      "US/IL/DownersGrove",               "ILDuPageCountyB",
      "US/IL/DupageCounty",               "ILDuPageCountyA",
      "US/IL/FairviewHeights",            "ILStClairCountyB",
      "US/IL/GlenCarbon",                 "ILMadisonCounty",
      "US/IL/KaneCounty",                 "ILKaneCounty",
      "US/IL/OFallon",                    "ILOFallon",
      "US/IN/BartholomewCounty",          "INBartholomewCounty",
      "US/IN/Bristol",                    "INElkhartCounty",
      "US/IN/EMGSVCS",                    "INHenryCounty",
      "US/IN/HancockCounty",              "INHancockCounty",
      "US/IN/Indianapolis",               "INMarionCounty",
      "US/IN/JasperCounty",               "INJasperCounty",
      "US/IN/KnoxCounty",                 "INKnoxCounty",
      "US/IN/LakeCounty",                 "INLakeCounty",
      "US/IN/MadisonCounty",              "INMadisonCounty",
      "US/IN/PorterCounty",               "INPorterCounty",
      "US/IN/ShelbyCounty",               "INShelbyCounty",
      "US/IL/Skokie",                     "ILCookCountyB",
      "US/IN/VigoCounty",                 "INVigoCounty",
      "US/IN/WayneCounty",                "INWayneCountyBParser",
      "US/KS/AndersonCounty",             "KSAndersonCounty",
      "US/KS/BrownCounty",                "KSBrownCounty",
      "US/KY/BoydCounty",                 "KYBoydCounty",
      "US/KS/CowleyCounty",               "KSCowleyCounty",
      "US/KS/FirePage",                   "KSWichita",
      "US/KS/JohnsonCounty",              "KSJohnsonCounty",
      "US/KS/LeavenworthCounty",          "KSLeavenworthCounty",
      "US/KS/MarionCounty",               "KSMarionCounty",
      "US/KS/MontgomeryCounty",           "KSMontgomeryCounty",
      "US/KS/NeoshoCounty",               "KSNeoshoCounty",
      "US/KS/Renolec",                    "KSRenoCounty",
      "US/KS/RiceCounty",                 "KSRiceCounty",
      "US/KS/SedgwickCounty",             "KSSedgwickCounty",
      "US/KY/BooneCounty",                "KYBooneCountyA",
      "US/KY/CampbellCounty",             "KYCampbellCounty",
      "US/KY/ChristianCounty",            "KYChristianCounty",
      "US/KY/ErlangerPD",                 "KYErlangerDispatch",
      "US/KY/GallatinCounty",             "KYGallatinCounty",
      "US/KY/HancockCounty",              "KYHancockCounty",
      "US/KY/HardinCounty",               "KYHardinCounty",
      "US/KY/KentonCounty",               "KYKentonCounty",
      "US/KY/KSPP1",                      "KYStatePolice",
      "US/KY/KSPP13",                     "KYStatePolice",
      "US/KY/KSPP3",                      "KYStatePolice",
      "US/KY/KSPP5",                      "KYStatePolice",
      "US/KY/KSPP6",                      "KYStatePolice",
      "US/KY/KSPP9",                      "KYStatePolice",
      "US/KY/LarueCounty",                "KYLaRueCounty",
      "US/KY/LawrenceCounty",             "KYLawrenceCounty",
      "US/KY/Louisville",                 "KYLouisville",
      "US/KY/MadisonCounty",              "KYMadisonCounty",
      "US/KY/MarshallCountyFire",         "KYMarshallCounty",
      "US/KY/Nexigen",                    "Cadpage2",
      "US/KY/OldhamCounty",               "KYOldhamCountyA",
      "US/KY/ScottCounty",                "KYScottCountyB",
      "US/LA/AscensionParish",            "LAAscensionParish",
      "US/LA/EastFelicianaParish",        "LAEastFelicianaParish",
      "US/LA/LafayetteParish",            "LALafayetteParish",
      "US/LA/LafourcheParish",            "LALafourcheParish",
      "US/LA/TerrebonneParish",           "LATerrebonneParish",
      "US/LA/WestBatonRougeCouncil",      "LAWestBatonRougeParish",
      "US/MD/ADSiCAD",                    "MDAnneArundelCountyADSiCADParser",
      "US/MD/BaltimoreCountyCmsg",        "MDBaltimoreCountyB",
      "US/MD/CalvertCounty",              "MDCalvertCounty",
      "US/MD/CecilCounty",                "Cadpage2",
      "US/MD/CharlesCounty",              "MDCharlesCountyA",
      "US/MD/ChiefAlert",                 "MDCarolineCounty",
      "US/MD/Fastalert",                  "MDCarrollCountyB",
      "US/MD/GarrettCounty",              "MDGarrettCounty",
      "US/MD/Fireblitz",                  "MDPrinceGeorgesCountyFireBiz",
      "US/MD/HarfordCounty",              "Cadpage2",
      "US/MD/MontgomeryCounty",           "MDMontgomeryCounty",
      "US/MD/PrinceGeorgesCounty",        "MDPrinceGeorgesCounty",
      "US/MD/PrintManager",               "MDAnneArundelCountyAnnapolis",
      "US/MD/QueenAnnesCounty",           "MDQueenAnnesCounty",
      "US/MD/StMarysCounty",              "MDStMarysCounty",
      "US/MD/WashingtonCounty",           "MDWashingtonCounty",
      "US/MD/WorcesterCounty",            "MDWorcesterCountyA",
      "US/ME/YorkCounty",                 "MEYorkCounty",
      "US/MI/AlleganCounty",              "MIAlleganCounty",
      "US/MI/AlpenaCounty",               "MIAlpenaCounty",
      "US/MI/AntrimCounty",               "MIAntrimCounty",
      "US/MI/BarryCounty",                "MIBarryCounty",
      "US/MI/BayCounty",                  "MIBayCountyB",
      "US/MI/BerrienCounty",              "MIBerrienCounty",
      "US/MI/CalhounCounty",              "MICalhounCounty",
      "US/MI/CharlevoixCounty",           "MICharlevoixCounty",
      "US/MI/CharlevoixCountyDispatch",   "MICHarlevoixCounty",
      "US/MI/Chippewa",                   "MIChippewaCounty",
      "US/MI/EatonCounty",                "MIEatonCounty",
      "US/MI/EmmetCounty",                "MIEmmetCounty",
      "US/MI/GrandTraverse",              "MIGrandTraverseCounty",
      "US/MI/GrandTraverseAttachment",    "MIGrandTraverseCounty",
      "US/MI/InghamCounty",               "MIInghamCounty",
      "US/MI/IoscoCounty",                "MIIoscoCounty",
      "US/MI/LenaweeCounty",              "MILenaweeCountyB",
      "US/MI/LuceCounty",                 "MILuceCounty",
      "US/MI/MarquetteCounty",            "MIMarquetteCounty",
      "US/MI/MeceolaDispatch",            "MIMecostaCounty",
      "US/MI/MidlandCounty",              "MIMidlandCounty",
      "US/MD/SaintMarysCounty",           "MDSaintMarysCounty",
      "US/MI/ShiawasseeCounty",           "MIShiawasseeCounty",
      "US/MN/AnokaCounty",                "MNAnokaCounty",
      "US/MN/BeckerCounty",               "MNBeckerCounty",
      "US/MN/BloomingtonFD",              "MNMinneapolisStPaul",
      "US/MN/BlueEarthCounty",            "MNBlueEarth",
      "US/MN/CarverCounty",               "MNCarverCounty",
      "US/MN/CrowWingCounty",             "MNCrowWingCounty",
      "US/MN/DakotaCounty",               "MNDakotaCounty",
      "US/MN/Edina",                      "MNEdina",
      "US/MN/FaribaultCounty",            "MNFaribaultCounty",
      "US/MN/GoodhueCounty",              "MNGoodhueCounty",
      "US/MN/HennepinCounty",             "MN/HennepinCounty",
      "US/MN/MilleLacsCounty",            "Cadpage2",
      "US/MN/MartinCounty",               "MNMartinCounty",
      "US/MN/MowerCounty",                "Cadpage2",
      "US/MN/NormanCounty",               "Cadpage2",
      "US/MN/OtterTailCounty",            "MNOtterTailCounty",
      "US/MN/PenningtonCounty",           "MNPenningtonCounty",
      "US/MN/RamseyCounty",               "MNRamseyCounty",
      "US/MN/SherburneCounty",            "MNSherburneCounty",
      "US/MN/StPaul",                     "MNMinneapolisStPaul",
      "US/MN/WabashaCounty",              "MNWabashaCounty",
      "US/MN/WadenaCounty",               "MNWadenaCounty",
      "US/MN/WashingtonCounty",           "MNWashingtonCounty",
      "US/MN/WinomaCounty",               "MNWinomaCounty",
      "US/MN/WrightCounty",               "MNWrightCounty",
      "US/MO/ADSiCad",                    "MOLawrenceCounty",
      "US/MO/AndrewCounty",               "Cadpage2",
      "US/MO/BarryCounty",                "MOBarryCounty",
      "US/MO/ChristianCounty",            "MOChristianCounty",
      "US/MO/CityofBranson",              "MOBranson",
      "US/MO/CityofCameron",              "MOCameron",
      "US/MO/CityofFestus",               "MOFestusB",
      "US/MO/CityofMonett",               "MOMonett",
      "US/MO/ClintonCounty",              "MOClintonCounty",
      "US/MO/ColeCounty",                 "MOJeffersonCounty",
      "US/MO/CrawfordCounty",             "MOCrawfordCountyB",
      "US/MO/FranklinCounty",             "MOFranklinCounty",
      "US/MO/GreeneCounty",               "MOGreeneCounty",
      "US/MO/JeffersonCity",              "MOJeffersonCity",
      "US/MO/LincolnCounty",              "MOLincolnCounty",
      "US/MO/MarshfieldFire",             "MOWebsterCounty",
      "US/MO/MoniteauCounty",             "MOMoniteauCounty",
      "US/MO/OsageCounty",                "MOOsageCounty",
      "US/MO/PikeCounty",                 "MOLincolnCounty",
      "US/MO/PolkCounty",                 "MOPolkCounty",
      "US/MO/PoplarBluffPD",              "MOPoplarBluff",
      "US/MO/RayCounty",                  "MORayCounty",
      "US/MO/Rejis",                      "MOStLouisCountyE",
      "US/MO/SouthernBoone",              "MOBooneCounty",
      "US/MO/StCharlesCounty",            "MOStCharlesCounty",
      "US/MO/StoneCounty",                "MOStoneCounty",
      "US/MS/Biloxi",                     "MSBiloxi",
      "US/MS/DesotoCounty",               "MSDeSotoCountyA",
      "US/MS/Southaven",                  "MSDesotoCountyB",
      "US/MT/GallatinCounty",             "BMTGallatinCounty",
      "US/MT/RavalliCounty",              "MTRavalliCounty",
      "US/NC/AlamanceCounty",             "NCAlamanceCountyParser",
      "US/NC/AlexanderCounty",            "NCAlexanderCounty",
      "US/NC/BeaufortCounty",             "NCBeaufortCounty",
      "US/NC/BrunswickCounty",            "NCBrunswickCounty",
      "US/NC/CabarrusCounty",             "NCCabarrusCounty",
      "US/NC/CatawbaCounty",              "NCCatawbaCounty",
      "US/NC/Cherokee911",                "NCCherokee",
      "US/NC/ClayCounty",                 "NCClayCounty",
      "US/NC/ClevelandCounty",            "NCClevelandCounty",
      "US/NC/ColumbusCounty",             "NCColumbusCounty",
      "US/NC/Concord",                    "NCCabarrusCountyAParser",
      "US/NC/CravenCounty",               "NCCravenCounty",
      "US/NC/DavidsonCounty",             "NCDavidsonCounty",
      "US/NC/DavieCounty",                "NCDavieCounty",
      "US/NC/DurhamCounty",               "NCDurhamCounty",
      "US/NC/EdgecombeCounty",            "NCEdgecombeCounty",
      "US/NC/FranklinCounty",             "NCFranklinCounty",
      "US/NC/ForsythCounty",              "NCForsynthCounty",
      "US/NC/GastonCounty",               "NCGastonCounty",
      "US/NC/GatesCounty",                "NCGatesCountyParser",
      "US/NC/GuilfordCounty",             "NCGuilfordCounty",
      "US/NC/HarnettCounty",              "NCHarnettCountyC",
      "US/NC/Havelock",                   "NCHavelock",
      "US/NC/IredellCounty",              "NCIredellCounty",
      "US/NC/JacksonCounty",              "NCJacksonCounty",
      "US/NC/JohnstonCounty",             "NCJohnstonCounty",
      "US/NC/JohnstonCounty",             "NCJohnstonCounty",
      "US/NC/LenoirCounty",               "NCLenoirCountyB",
      "US/NC/LincolnCounty",              "NCLincolnCounty",
      "US/NC/MecklenburgCounty",          "NCMecklenburgCounty",
      "US/NC/MitchellCounty",             "NCMitchellCounty",
      "US/NC/NashCounty",                 "NCNashCounty",
      "US/NC/PenderCounty",               "NCPenderCounty",
      "US/NC/PittCounty",                 "NCPittCounty",
      "US/NC/RandolphCounty",             "NCRandolphCounty",
      "US/NC/RobesonCounty",              "NCRobesonCounty",
      "US/NC/RockinghamCounty",           "NCRockinghamCounty",
      "US/NC/RowanCounty",                "NCRowanCounty",
      "US/NC/SampsonCounty",              "NCSampsonCounty",
      "US/NC/TransylvaniaCounty",         "NCTransylvaniaCounty",
      "US/NC/USAMobility",                "NCWayneCountyA",
      "US/NC/WarrenCounty",               "NCWarrenCounty",
      "US/NC/WayneCounty",                "NCWayneCountyB",
      "US/NC/WilkesCounty",               "NCWilkesCounty",
      "US/NC/WilsonCounty",               "NCWilsonCounty",
      "US/NC/YadkinCounty",               "NCYadkinCounty",
      "US/NH/Hanover",                    "NHHanover,Cadpage2",
      "US/NH/LRMFA",                      "NHGraftonCounty",
      "US/NH/LRMFALimited",               "NHGraftonCounty",
      "US/NJ/BurlingtonCounty",           "NJBurlingtonCountyC",
      "US/NJ/CamdenCounty",               "NJCamdenCounty",
      "US/NJ/EggHarborTownship",          "NJAtlanticCounty",
      "US/NJ/GallowayTownship",           "NJAtlanticCountyC",
      "US/NJ/GloucesterCounty",           "NJGloucesterCountyA",
      "US/NJ/HamiltonTownship",           "NJAtlanticCountyB",
      "US/NJ/HunterdonCounty",            "NJHunterdonCounty",
      "US/NJ/LifeCommCad",                "NJMercerCountyA",
      "US/NJ/Linwood",                    "Cadpage2",
      "US/NJ/MercerCounty",               "NJMercerCountyB",
      "US/NJ/MonmouthCounty",             "NJMonmouthCountyA",
      "US/NJ/MorrisCounty",               "NJMorrisCounty",
      "US/NJ/NPD",                        "NJSussexCountyA",
      "US/NJ/Nwbcd",                      "NJSussexCountyA",
      "US/NJ/OceanCounty",                "NJOceanCounty",
      "US/NJ/SomersetCounty",             "NJSomersetCountyA",
      "US/NJ/SpartaPD",                   "NJSussexCountyA",
      "US/NJ/SussexCountySherif",         "NJSussexCountyB",
      "US/NJ/TomsRiverPolice",            "NJOceanCountyB",
      "US/NJ/WayneTownship",              "NJWayneTownship",
      "US/NM/LasCruces",                  "NMLasCruces",
      "US/NV/CityofElko",                 "NVElkoCountyA",
      "US/NV/Elko",                       "NVElkoCounty",
      "US/NV/WestWendover",               "NVElkoCountyB",
      "US/NV/WinnemuccaCityFD",           "NVHumboldtCounty",
      "US/NY/Albany",                     "NYAlbanyCountyC",
      "US/NY/AlleganyCounty911",          "NYAlleganyCounty",
      "US/NY/BCFA",                       "NYSuffolkCountyB",
      "US/NY/ChiliFD",                    "NYMonroeCounty",
      "US/NY/CityofBethlehem",            "NYBethlehem",
      "US/NY/Erie",                       "NYErieCountyF",
      "US/NY/FireRescueSystems",          "NYSuffolkCountyBFI",
      "US/NY/JeffersonCounty",            "NYJeffersonCounty",
      "US/NY/Lewis911",                   "NYLewisCounty",
      "US/NY/MonroeCounty",               "NYMonroeCountyWebster",
      "US/NY/OneidaIAR",                  "NYOneidaCounty",
      "US/NY/OntarioCounty",              "NYOntarioCounty",
      "US/NY/OrangeCounty",               "NYOrangeCounty",
      "US/NY/OrangeCounty911",            "NYOrangeCountyB",
      "US/NY/OrleansCounty",              "NYOrleansCounty",
      "US/NY/OswegoCounty",               "NYOswegoCountyB",
      "US/NY/OtsegoCounty",               "NYOtsegoCounty",
      "US/NY/RocklandCounty",             "NYRocklandCountyB",
      "US/NY/Ronkonkoma",                 "NYSuffolkCountyB",
      "US/NY/SCFRESpaging",               "NYSuffolkCountyA",
      "US/NY/SCFRESprinting",             "N/A",
      "US/NY/StLawrenceCounty",           "NYStLawrenceCounty",
      "US/NY/WayneCounty",                "NYWayneCountyB",
      "US/NY/WestchesterCounty",          "NYWestchesterCounty",
      "US/OH/BelmontCounty",              "OHBelmontCounty",
      "US/OH/ButlerCountySheriff",        "OHButlerCountyA",
      "US/OH/ChampaignCounty",            "OHChampaignCounty",
      "US/OH/CityofFairfield",            "OHFairfield",
      "US/OH/CityofTrenton",              "OHTrenton",
      "US/OH/CityofVandalia",             "OHMontgomeryCountyB",
      "US/OH/CityofXenia",                "OHGreeneCounty",
      "US/OH/DarkeCountySheriff",         "OHDarkeCounty",
      "US/OH/Fairfield",                  "OHFairfieldCounty",
      "US/OH/GeaugaCounty",               "OHGeaugaCountyB",
      "US/OH/HamiltonCounty",             "OHHamiltonCounty",
      "US/OH/Harveysburg",                "OHWarrenCountyC",
      "US/OH/HolmesCountySheriff",        "OHHolmesCounty",
      "US/OH/JeffersonCounty",            "OHJeffersonCounty",
      "US/OH/LickingCounty",              "OHLickingCounty",
      "US/OH/LoganCounty",                "OHLoganCounty",
      "US/OH/LorainCounty",               "OHLorainCountyB",
      "US/OH/Sebring",                    "OHMahoningCounty",
      "US/OH/CityofGreen",                "OHSummitCountyF",
      "US/OH/TrumbullCounty",             "OHTrumbullCounty",
      "US/OH/WarrenCountyEmergitech",     "OHFranklin",
      "US/OH/WarrenCountyLogis",          "OHWarrenCountyA",
      "US/OH/WashingtonCountySheriff",    "OHWashingtonCountyA",
      "US/OK/CherokeeCounty",             "OKCherokeeCounty",
      "US/OK/CityofTulsa",                "OKTulsaB",
      "US/OK/LoveCounty",                 "OKLoveCounty",
      "US/OK/MayesCounty",                "OKMayesCounty",
      "US/OR/Benton",                     "ORBentonCounty",
      "US/OR/CityofSalem",                "ORMarionCountyB",
      "US/OR/CityofSeaside",              "ORClatsopCounty",
      "US/OR/ClackamasCounty",            "ORClackamasCountyA",
      "US/OR/ColumbiaCounty",             "ORColumbiaCounty",
      "US/OR/CookCounty",                 "ORCookCounty",
      "US/OR/DeschutesCounty",            "ORDeschutesCounty",
      "US/OR/GilliamCounty",              "ORGilliamCounty",
      "US/OR/HoodRiverCounty",            "ORHoodRiverCounty",
      "US/OR/Jackson",                    "ORJacksonCounty",
      "US/OR/JeffersonCounty",            "ORJeffersonCounty",
      "US/OR/Klamath",                    "ORKlamathCounty",
      "US/OR/LaneCounty",                 "ORLaneCounty",
      "US/OR/Lincoln",                    "ORLincolnCounty",
      "US/OR/Linn",                       "ORLinnCounty",
      "US/OR/MultnomahCounty",            "ORMultnomahCounty",
      "US/OR/NewbergFire",                "ORYamhillCountyA",
      "US/OR/Pacific",                    "ORJosephineCounty",
      "US/OR/PolkCounty",                 "ORPolkCounty",
      "US/OR/PortlandAMR",                "ORClackamasCountyC",
      "US/OR/PrinevillePD",               "ORCrookCounty",
      "US/OR/ShermanCounty",              "ORShermanCounty",
      "US/OR/WashingtonCounty",           "ORWashingtonCounty",
      "US/OR/WheelerCounty",              "ORWheelerCounty",
      "US/OR/Woodburn",                   "ORMarionCounty",
      "US/OR/Yamhill",                    "ORYamhillCountyB",
      "US/PA/AdamsCounty",                "PAAdamsCounty",
      "US/PA/AlleghenyCounty",            "PAAlleghenyCounty",
      "US/PA/ArmstrongCounty",            "PAArmstrongCounty",
      "US/PA/BeaverCounty",               "PABeaverCounty",
      "US/PA/BerksCounty",                "PABerksCounty",
      "US/PA/BerwynFire",                 "PAChesterCountyD1",
      "US/PA/BlairCounty",                "PABlairCount",
      "US/PA/BradfordCounty",             "PABradfordCounty",
      "US/PA/BucksCounty",                "PABucksCounty",
      "US/PA/BucksEMA",                   "PABucksCountyA",
      "US/PA/ButlerCounty",               "PAButlerCounty",
      "US/PA/CentreCounty",               "PACentreCounty",
      "US/PA/ChesterCountyIAR",           "PAChesterCountyL",
      "US/PA/ClearfieldCounty",           "PAClearfieldCounty",
      "US/PA/ColumbiaCounty",             "PAColumbiaCounty",
      "US/PA/CumberlandCounty",           "PACumberlandCounty",
      "US/PA/DauphinCounty",              "PADauphinCounty",
      "US/PA/DelawareCounty",             "PADelawareCountyB,PADelawareCountyG",
      "US/PA/FDCMS",                      "PAChesterCountyF,PAChesterCountyJ",
      "US/PA/FlourtownFire",              "PAMontgomeryCountyD",
      "US/PA/FranklinCounty",             "PAFranklinCounty",
      "US/PA/FultonCounty",               "PAFultonCounty",
      "US/PA/GladwyneFire",               "PAMontgomeryCountyD",
      "US/PA/GoodFellowshipAmbulance",    "PAChesterCountyG",
      "US/PA/Km911",                      "PALebanonCounty",
      "US/PA/LackawannaCounty",           "PALackawannaCounty",
      "US/PA/LancasterCounty",            "PALancasterCounty",
      "US/PA/LehighCounty",               "PALehighCounty",
      "US/PA/LowerAlsaceFC",              "PABerksCounty",
      "US/PA/LuzerneCounty",              "PALuzerneCounty",
      "US/PA/Lvh",                        "PALehighCountyB",
      "US/PA/MercerCounty",               "PAMercerCounty",
      "US/PA/MessiahEMS",                 "PAGrantham",
      "US/PA/Monroeville",                "PAMonroville",
      "US/PA/MontgomeryCounty",           "PAMontgomeryCountyC",
      "US/PA/PikeCounty",                 "PAPikeCountyB",
      "US/PA/SchuylkillCounty",           "PASchuylkillCounty",
      "US/PA/SomersetCounty",             "PASomersetCounty",
      "US/PA/Springfield",                "PADelawareCountyG",
      "US/PA/TrappeIAR",                  "PAChesterCountyL",
      "US/PA/VenangoCounty",              "PAVenagoCountyB",
      "US/PA/WarrenCounty",               "PAWarrenCounty",
      "US/PA/WashingtonCounty",           "PAWashingtonCounty",
      "US/PA/WayneCounty",                "PAWayneCounty",
      "US/PA/YorkCounty",                 "PAYorkCountyD",
      "US/RI/CityofWestWarwick",          "RIWestWarwick",
      "US/SC/AndersonSheriff",            "SCAndersonCounty",
      "US/SC/CherokeeCounty",             "SCherokeeCounty",
      "US/SC/DorchesterCounty",           "SCDorchesterCounty",
      "US/SC/GeorgetownCounty",           "SCGeorgetownCounty",
      "US/SC/HartsvilleFD",               "SCDarlingtonCounty",
      "US/SC/JasperCounty",               "SCJasperCounty",
      "US/SC/LancasterCounty",            "SCLancasterCounty",
      "US/SC/NewberryCounty",             "SCNewberryCounty",
      "US/SC/OrangeburgCounty",           "SCOrangeburgCounty",
      "US/SC/PickensCounty",              "SCPickensCounty",
      "US/PA/RidgeFireCompany",           "PAChesterCountyD4",
      "US/SC/SpartanburgCounty",          "SCSpartanburgCounty",
      "US/SC/SumterCounty",               "SCSumterCounty",
      "US/SC/YorkCounty",                 "SCYorkCounty",
      "US/SD/LincolnCounty",              "SDLincolnCounty",
      "US/SD/MinnehahaCounty",            "SDMinnehahaCounty",
      "US/TN/BedfordCounty",              "TNBedfordCounty",
      "US/TN/CampbellCounty",             "TNCampbellCounty",
      "US/TN/CumberlandCounty",           "TNCumberlandCounty",
      "US/TN/Overton",                    "TNOvertonCounty",
      "US/TN/RoaneCounty",                "TNRoaneCounty",
      "US/TN/WarrenCounty",               "TNWarrenCounty",
      "US/TX/Acadian",                    "TXAcadianAmbulance",
      "US/TX/AngletonPD",                 "TXAngleton",
      "US/TX/AransasCounty",              "TXAransasCounty",
      "US/TX/Austin",                     "TXTravisCounty",
      "US/TX/BellCounty",                 "TXBellCounty",
      "US/TX/Carrollton",                 "TXCarrollton",
      "US/TX/CityofCrowley",              "TXCrowley",
      "US/TX/CityofDickinson",            "TXGalvestonCounty",
      "US/TX/CityofFlowerMound",          "TXFlowerMound",
      "US/TX/CityofKeller",               "TXKeller",
      "US/TX/CityofPortAransas",          "TXPortAransas",
      "US/TX/CityofWylie",                "TXCollinCountyA",
      "US/TX/DentonCounty",               "TXDentonCounty",
      "US/TX/FlightVector",               "TXFlightVector",
      "US/TX/FortBendCounty",             "TXFortBendCountyA",
      "US/TX/FreeportDispatch",           "TXBrazoriaCountyB",
      "US/TX/Gatesville",                 "TXGatesville",
      "US/TX/GreggCounty",                "TXGreggCounty",
      "US/TX/HarrisCountyEC",             "TXHarrisCountyESD1B",
      "US/TX/HaysUSA",                    "KSEllisCounty",
      "US/TX/Helotes",                    "TXHelotes",
      "US/TX/HuntCounty",                 "TXHuntCounty",
      "US/TX/JohnsonCounty",              "TXJohnsonCountyB",
      "US/TX/Katy",                       "TXCyCreekCommCenter",
      "US/TX/KaufmanCounty",              "TXKaufmanCounty",
      "US/TX/KendallCounty",              "TXKendallCounty",
      "US/TX/LeonCounty",                 "TXLeonCounty",
      "US/TX/Lufkin",                     "TXLufkin",
      "US/TX/ManvelPD",                   "TXBrazoriaCountyA",
      "US/TX/Midlothian",                 "TXKeller",
      "US/TX/MontgomeryCounty",           "TXMontgomeryCounty",
      "US/TX/MontgomeryCounty",           "TXMontgomeryCounty",
      "US/TX/NuecesCounty",               "TXNuecesCounty",
      "US/TX/OssiCAD",                    "TXLaPorte",
      "US/TX/Rosenburg",                  "TXRosenburg",
      "US/TX/RoyseCity",                  "TXRoyseCity",
      "US/TX/RuskCounty",                 "TXRuskCountyA",
      "US/TX/RuskPD",                     "TXRuskCountyB",
      "US/TX/SanMarcosCounty",            "TXSanMarcosCounty",
      "US/TX/Seguin",                     "TXKeller",
      "US/TX/Smhcems",                    "TXHaysCounty",
      "US/TX/SmithCounty",                "TXSmithCounty",
      "US/TX/Stationalerting",            "TXFortBendCountyB",
      "US/TX/TarrantCounty",              "TXTarrantCounty",
      "US/TX/VillageFire",                "TXVillage",
      "US/TX/West",                       "TXMclennanCounty",
      "US/TX/Wilco",                      "TXWilliamsonCounty",
      "US/TX/WylieFireDispatch",          "TXWylie",
      "US/UT/BoxElderCounty",             "UTBoxElderCounty",
      "US/UT/SaltLakeCounty",             "UTSaltLakeCounty",
      "US/VA/AlbermarleCounty",           "VAAlbermarleCounty",
      "US/VA/AppomattoxCounty",           "VAAppomattoxCounty",
      "US/VA/BrunswickCounty",            "VABrunswickCounty",
      "US/VA/DickensonCounty",            "VADickensonCounty",
      "US/VA/Esva911",                    "VANorthamptonCounty",
      "US/VA/FluvannaSheriff",            "VAFluvanna",
      "US/VA/Franklin",                   "VAFranklinB",
      "US/VA/FranklinCounty",             "VAFranklinCountyB",
      "US/VA/Galax",                      "VAGalax",
      "US/VA/GloucesterCounty",           "VAGloucesterCounty",
      "US/VA/Hanover",                    "VAHanoverCounty",
      "US/VA/HenryCounty",                "VAHenryCounty",
      "US/VA/JamesCityCounty",            "VAJamesCityCounty",
      "US/VA/LoudounCounty",              "VALoudounCounty",
      "US/VA/MecklenburgCounty",          "VAMecklenburgCounty",
      "US/VA/MontgomeryCounty",           "VAMontgomeryCounty",
      "US/VA/NewportNews",                "VANewportNews",
      "US/VA/NorthumberlandCounty",       "VANorthumberland",
      "US/VA/PrinceWilliamCounty",        "VAPrinceWilliamCountyA",
      "US/VT/Hartford",                   "VTHartford",
      "US/WA/BentonCounty",               "WABentonCounty",
      "US/WA/KingCounty",                 "WAKingCountyA",
      "US/WA/LewisCounty",                "WALewisCounty",
      "US/WA/PendOreilleCounty",          "WAPendOreilleCounty",
      "US/WA/RiverComm911",               "WAChelanCounty",
      "US/WA/SNOCOM",                     "WASnohomishCounty",
      "US/WA/SnohomishCounty",            "WASnohomishCounty",
      "US/WA/ThurstonCounty",             "WAThurstonCounty",
      "US/WA/USAMobility",                "WAPierceCountyA",
      "US/WA/ValleyCom",                  "WAKingCountyC",
      "US/WA/WallaWallaCounty",           "WAWallaWallaCounty",
      "US/WA/WestPierceCounty",           "WAPierceCountyD",
      "US/WA/WhitmanCounty",              "WAWhitmanCounty",
      "US/WI/DoorCounty",                 "WIDoorCounty",
      "US/WI/MukwonagoFire",              "WIWaukeshaCountyB",
      "US/WI/SheboyganCounty",            "WISheboyganCounty",
      "US/WI/WaukeshaCounty",             "WIWaukeshaCountyA",
      "US/WV/BerkeleyCounty",             "WVBerkeleyCounty",
      "US/WV/HampshireCounty",            "WVHampshireCounty",
      "US/WV/MarionCounty",               "WVMarionCounty",
      "US/WV/LincolnCounty",              "WVLincolnCounty",
      "US/WV/RaleighCounty",              "WVRaleighCounty",
      "US/WV/RoaneCounty",                "WVRoaneCounty",
      "US/WV/Wyoming",                    "WVWyomingCounty",
      "Utility/General/Default",          "N/A",
      "Vendor/Cadpage/Default",           "Cadpage2",
      "Vendor/Cadpage/StandardA",         "StandardA",
      "Vendor/Sweden/Zenit",              "ZSESweden"
  });
}
