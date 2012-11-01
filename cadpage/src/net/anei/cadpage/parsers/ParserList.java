package net.anei.cadpage.parsers;

import java.util.ArrayList;
import java.util.List;

public class ParserList {
  
  public static class ParserCategory {
    final private String name;
    final private ParserEntry[] parserList;
    
    public String getName() {
      return name;
    }
    
    public ParserEntry[] getParserList() {
      return parserList;
    }
    
    private ParserCategory(String name, Object ... objs) {
      this.name = name;
      List<ParserEntry> entryList = new ArrayList<ParserEntry>();
      int ndx = 0;
      while (ndx < objs.length) {
        Object next = objs[ndx++];
        if (next instanceof ParserCategory) {
          entryList.add(new ParserEntry((ParserCategory)next));
        } else {
          entryList.add(new ParserEntry((String)next, (String)objs[ndx++]));
        }
      }
      
      this.parserList = entryList.toArray(new ParserEntry[entryList.size()]);
    }

    private void toStringBuilder(StringBuilder sb) {
      sb.append('{');
      sb.append(name);
      sb.append('\n');
      for (ParserEntry entry : parserList) entry.toStringBuilder(sb);
      sb.append("}\n");
    }
  }
  
  public static class ParserEntry {
    final private ParserCategory category;
    final private String parserName;
    final private String locName;
    
    public boolean isCategory() {
      return category != null;
    }
    
    public ParserCategory getCategory() {
      return category;
    }
    
    public String getParserName() {
      return parserName;
    }
    
    public String getLocName() {
      return locName;
    }
    
    private ParserEntry(String parserName, String locName) {
      this.category = null;
      this.parserName = parserName;
      this.locName = locName;
    }
    
    private ParserEntry(ParserCategory category) {
      this.category = category;
      this.parserName = null;
      this.locName = null;
    }
    
    private void toStringBuilder(StringBuilder sb) {
      if (isCategory()) {
        category.toStringBuilder(sb);
      } else {
        sb.append(parserName);
        sb.append(':');
        sb.append(locName);
        sb.append('\n');
      }
    }
  }
  
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    MASTER_LIST.toStringBuilder(sb);
    System.out.print(sb.toString());
  }
 
  
  public static final ParserCategory MASTER_LIST = 
      new ParserCategory("MASTER PARSER LIST",
          new ParserCategory("General location"
              ,"General"                        ,"Generic Location"
              ,"GeneralSlash"                   ,"Generic (slash field separator)"
              ,"GeneralDash"                    ,"Generic (dash field separator)"
              ,"Cadpage"                        ,"Standard Cadpage Format"
              ,"StandardSpottedDog"             ,"Spotted Dog Technologies"
              ,"StandardA"                      ,"Standard Format A"
          ),
          new ParserCategory("Alabama"
              ,"ALBaldwinCounty"                ,"Baldwin County, AL"
              ,"ALChiltonCounty"                ,"Chilton County, AL"
              ,"ALColbertCounty"                ,"Colbert County, AL"
              ,"ALDothan"                       ,"Dothan, AL"
              ,"ALHenryCounty"                  ,"Henry County, AL"
              ,"ALLauderdaleCounty"             ,"Lauderdale County, AL"
              ,"ALMadisonCounty"                ,"Madison County, AL"
              ,"ALMorganCounty"                 ,"Morgan County, AL"
              ,"ALOzark"                        ,"Ozark, AL"
              ,"ALShelbyCounty"                 ,"Shelby County, AL"
              ,"ALStClairCounty"                ,"St Clair County, AL"
          ),
          new ParserCategory("Arizona"
              ,"AZMaricopaCounty"               ,"Maricopa County, AZ"
          ),
          new ParserCategory("California"
              ,"CAButteCounty"                  ,"Butte County, CA"
              ,"CACalaverasCounty"              ,"Calaveras County, CA"
              ,"CAElDoradoCounty"               ,"El Dorado County, CA"
              ,"CALakeCounty"                   ,"Lake County, CA"
              ,"CALosAngelesCounty"             ,"Los Angeles County, CA"
              ,"CAMendocinoCounty"              ,"Mendocino County, CA"
              ,"CAMontereyCounty"               ,"Monterey County, CA"
              ,"CANapaCounty"                   ,"Napa County, CA"
              ,"CAOroville"                     ,"Oroville, CA"
              ,"CARiversideCounty"              ,"Riverside County, CA"
              ,"CASanBernardinoCounty"          ,"San Bernardino County, CA"
              ,"CASanDiegoCounty"               ,"San Diego County, CA"
              ,"CASanJoaquinCounty"             ,"San Joaquin County, CA"
              ,"CAShastaCounty"                 ,"Shasta County, CA"
              ,"CASonomaCounty"                 ,"Sonoma County, CA"
              ,"CAVenturaCounty"                ,"Ventura County, CA"
          ),
          new ParserCategory("Colorado"
              ,"COAdamsCounty"                  ,"Adams County, CO"
              ,"COBoulderCounty"                ,"Boulder County, CO"
              ,"CODouglasCounty"                ,"Douglas County, CO"
              ,"COElPasoCounty"                 ,"El Paso County, CO"
              ,"COElbertCounty"                 ,"Elbert County, CO"
              ,"COGarfieldCounty"               ,"Garfield County, CO"
              ,"COGolden"                       ,"Golden County, CO"
              ,"COJeffersonCounty"              ,"Jefferson County, CO"
              ,"COLarimerCounty"                ,"Larimer County, CO"
              ,"CONorthglennEMS"                ,"Northglenn EMS, CO"
              ,"COPuebloCounty"                 ,"Pueblo County, CO"
              ,"COWeldCounty"                   ,"Weld County, CO"
          ),
          new ParserCategory("Connecticut"
              ,"CTColchesterEmergComm"          ,"Colchester Emergency Communications, CT"
              ,"CTHartfordCounty"               ,"Hartford County, CT"
              ,"CTLitchfieldCounty"             ,"Litchfield County, CT"
              ,"CTMadison"                      ,"Madison, CT"
              ,"CTMiddlesexCounty"              ,"Middlesex County, CT"
              ,"CTNewHavenCounty"               ,"New Haven County, CT"
              ,"CTNewLondonCounty"              ,"New London County, CT"
              ,"CTNorthwestPublicSafety"        ,"Northwest Public Safety, CT"
              ,"CTSimsbury"                     ,"Simsbury, CT"
              ,"CTStamford"                     ,"Stamford, CT"
              ,"CTTollandCounty"                ,"Tolland County, CT"
              ,"CTTrumbull"                     ,"Trumbull, CT"
              ,"CTWaterfordTown"                ,"Waterford Town, CT"
              ,"CTWindhamCounty"                ,"Windham County, CT"
          ),
          new ParserCategory("Delaware"
              ,"DEDelmar"                       ,"Delmar, DE"
              ,"DEKentCounty"                   ,"Kent County, DE"
              ,"DENewCastleCounty"              ,"New Castle, DE"
              ,"DESussexCounty"                 ,"Sussex County, DE"
              ,"DEWilmington"                   ,"Wilmington, DE"
          ),
          new ParserCategory("Florida"
              ,"FLCitrusCounty"                 ,"Citrus County, FL"
              ,"FLCollierCounty"                ,"Collier County, FL"
              ,"FLCrestview"                    ,"Crestview, FL"
              ,"FLLakeCounty"                   ,"Lake County, FL"
              ,"FLLeeCounty"                    ,"Lee County, FL"
              ,"FLManateeCounty"                ,"Manatee County, FL"
              ,"FLPensacola"                    ,"Pensacola, FL"
              ,"FLSarasotaCounty"               ,"Sarasota County, FL"
          ),
          new ParserCategory("Georgia"
              ,"GABullochCounty"                ,"Bulloch County, GA"
              ,"GABrantleyCounty"               ,"Brantley County, GA"
              ,"GACamdenCounty"                 ,"Camden County, GA"
              ,"GACandlerCounty"                ,"Candler County, GA"
              ,"GADadeCounty"                   ,"Dade County, GA"
              ,"GADoolyCounty"                  ,"Dooly County, GA"
              ,"GADoughertyCounty"              ,"Dougherty County, GA"
              ,"GAEvansCounty"                  ,"Evans County, GA"
              ,"GAHenryCounty"                  ,"Henry County, GA"
              ,"GAJacksonCounty"                ,"Jackson County, GA"
              ,"GAMaconCounty"                  ,"Macon County, GA"
              ,"GAMarionCounty"                 ,"Marion County, GA"
              ,"GAMartinez"                     ,"Martinez, GA"
              ,"GAOconeeCounty"                 ,"Oconee County, GA"
              ,"GAPikeCounty"                   ,"Pike County, GA"
              ,"GASchleyCounty"                 ,"Schley County, GA"
              ,"GASmyrna"                       ,"Smyrna, GA"
              ,"GASumterCounty"                 ,"Sumter County, GA"
              ,"GATalbotCounty"                 ,"Talbot County, GA"
              ,"GATaylorCounty"                 ,"Taylor County, GA"
              ,"GAWebsterCounty"                ,"Webster County, GA"
              ,"GAWhitfieldCounty"              ,"Whitfield County, GA"
          ),
          new ParserCategory("Idaho"
              ,"IDBlaineCounty"                 ,"Blaine County, ID"
              ,"IDGoodingCounty"                ,"Gooding County, ID"
              ,"IDKootenaiCounty"               ,"Kootenai County, ID"
          ),
          new ParserCategory("Illinois"
              ,"ILChampaignUrbana"              ,"Champaign-Urbana, IL"
              ,"ILCookCounty"                   ,"Cook County, IL"
              ,"ILDuPageCounty"                 ,"DuPage County, IL"
              ,"ILKankakeeCounty"               ,"Kankakee County, IL"
              ,"ILMedstar"                      ,"Medstar, SW IL"
              ,"ILPeoriaCounty"                 ,"Peoria County, IL"
              ,"ILRichlandCounty"               ,"Richland County, IL"
              ,"ILRockIslandCounty"             ,"Rock Island County, IL"
              ,"ILWinnebagoCounty"              ,"Winnebago County, IL"
              ,"ILWoodfordCounty"               ,"Woodford County, IL"
          ),
          new ParserCategory("Indiana"
              ,"INBartholomewCounty"            ,"Bartholomew County, IN"
              ,"INHamiltonCounty"               ,"Hamilton County, IN"
              ,"INHancockCounty"                ,"Hancock County, IN"
              ,"INHowardCounty"                 ,"Howard County, IN"
              ,"INJacksonCounty"                ,"Jackson County, IN"
              ,"INKosciuskoCounty"              ,"Kosciusko County, IN"
              ,"INMadisonCounty"                ,"Madison County, IN"
              ,"INMarionCounty"                 ,"Marion County, IN"
              ,"INMarshallCounty"               ,"Marshall County, IN"
              ,"INPorterCounty"                 ,"Porter County, IN"
              ,"INStJosephCounty"               ,"St Joseph County, IN"
              ,"INTiptonCounty"                 ,"Tipton County, IN"
              ,"INVigoCounty"                   ,"Vigo County, IN"
              ,"INWayneCounty"                  ,"Wayne County, IN"
          ),
          new ParserCategory("Iowa"
              ,"IABlackHawkCounty"              ,"Black Hawk County, IA"
              ,"IAJacksonCounty"                ,"Jackson County, IA"
              ,"IAMuscatineCounty"              ,"Muscatine County, IA"
              ,"IAPolkCounty"                   ,"Polk County, IA"
              ,"IAPottawattamieCounty"          ,"Pottawattamie County, IA"
              ,"IAWoodburyCounty"               ,"Woodbury County, IA"
          ),
          new ParserCategory("Kansas"
              ,"KSButlerCounty"                 ,"Butler County, KS"
              ,"KSJohnsonCounty"                ,"Johnson County, KS"
          ),
          new ParserCategory("Kentucky"
              ,"KYBoydCounty"                   ,"Boyd County, KY"
              ,"KYDaviessCounty"                ,"Daviess County, KY"
              ,"KYErlangerDispatch"             ,"Erlanger Dispatch, KY"
              ,"KYOldhamCounty"                 ,"Oldham County, KY"
          ),
          new ParserCategory("Lousiana"
              ,"LAAcadianAmbulance"             ,"Acadian Ambulance, LA"
              ,"LAJeffersonParish"              ,"Jefferson Parish, LA"
          ),
          new ParserCategory("Maine"
              ,"MEYorkCounty"                   ,"York County, ME"
          ),
          new ParserCategory("Maryland"
              ,"MDAlleganyCounty"               ,"Allegany County, MD"
              ,"MDAnneArundelCounty"            ,"Anne Arundel County, MD"
              ,"MDBaltimoreCounty"              ,"Baltimore County, MD"
              ,"MDCalvertCounty"                ,"Calvert County, MD"
              ,"MDCambridge"                    ,"Cambridge, MD"
              ,"MDCarolineCounty"               ,"Caroline County, MD"
              ,"MDCarrollCounty"                ,"Carroll County, MD"
              ,"MDCecilCounty"                  ,"Cecil County, MD"
              ,"MDCharlesCounty"                ,"Charles County, MD"
              ,"MDDorchesterCounty"             ,"Dorchester County, MD"
              ,"MDFrederickCounty"              ,"Frederick County, MD"
              ,"MDHarford"                      ,"Harford, MD"
              ,"MDHowardCounty"                 ,"Howard County, MD"
              ,"MDKentCounty"                   ,"Kent County, MD"
              ,"MDMontgomeryCounty"             ,"Montgomery County, MD"
              ,"MDNorthEast"                    ,"North East, MD"
              ,"MDOceanCity"                    ,"Ocean City, MD"
              ,"MDPrinceGeorgesCounty"          ,"Prince George\'s County, MD"
              ,"MDQueenAnnesCounty"             ,"Queen Anne\'s County, MD"
              ,"MDSaintMarysCounty"             ,"St Mary\'s County, MD"
              ,"MDSomersetCounty"               ,"Somerset County, MD"
              ,"MDTalbotCounty"                 ,"Talbot County, MD"
              ,"MDWashingtonCounty"             ,"Washington County, MD"
              ,"MDWicomicoCounty"               ,"Wicomico County, MD"
              ,"MDWorcesterCounty"              ,"Worcester County, MD"
          ),
          new ParserCategory("Massechusetts"
              ,"MAYarmouth"                     ,"Yarmouth, MA"
          ),
          new ParserCategory("Michigan"
              ,"MICalhounCounty"                ,"Calhoun County, MI"
              ,"MICharlevoixCounty"             ,"Charlevoix County, MI"
              ,"MICheboyganCounty"              ,"Cheboygan County, MI"
              ,"MIEatonCounty"                  ,"Eaton County, MI"
              ,"MIEmmetCounty"                  ,"Emmet County, MI"
              ,"MILivingstonCounty"             ,"Livingston County, MI"
              ,"MIMidlandCounty"                ,"Midland County, MI"
              ,"MIMobileMedicalResponse"        ,"Mobile Medical Response, MI"
              ,"MIMontcalmCounty"               ,"Montcalm County, MI"
              ,"MIMuskegonCounty"               ,"Muskegon County, MI"
              ,"MIOaklandCounty"                ,"Oakland County, MI"
              ,"MIRichmond"                     ,"Richmond, MI"
              ,"MISaginawCounty"                ,"Saginaw County, MI"
          ),
          new ParserCategory("Minnesota"
              ,"MNAnokaCounty"                  ,"Anoka County, MN"
              ,"MNMinneapolisStPaul"            ,"Minneapolis/St Paul, MN"
              ,"MNHennepinCounty"               ,"Hennepin County, MN"
              ,"MNOlmstedCounty"                ,"Olmsted County, MN"
          ),
          new ParserCategory("Mississippi"
              ,"MSAcadianAmbulance"             ,"Acadian Ambulance, MS"
              ,"MSCalhounCounty"                ,"Calhoun County, MS"
              ,"MSMarionCounty"                 ,"Marion County, MS"
          ),
          new ParserCategory("Missouri"
              ,"MOBarryCounty"                  ,"Barry County, MO"
              ,"MOCallawayCounty"               ,"Callaway County, MO"
              ,"MOChristianCounty"              ,"Christian County, MO"
              ,"MOCrawfordCounty"               ,"Crawford County, MO"
              ,"MOFestus"                       ,"Festus, MO"
              ,"MOGasconadeCounty"              ,"Gasconade County, MO"
              ,"MOGreeneCounty"                 ,"Greene County, MO"
              ,"MOJasperCounty"                 ,"Jasper County, MO"
              ,"MOJeffersonCity"                ,"Jefferson City, MO"
              ,"MOJeffersonCounty"              ,"Jefferson County, MO"
              ,"MOJohnsonCounty"                ,"Johnson County, MO"
              ,"MONewtonCounty"                 ,"Newton County, MO"
              ,"MOPulaskiCounty"                ,"Pulaski County, MO"
              ,"MORayCounty"                    ,"Ray County, MO"
              ,"MOStCharlesCounty"              ,"St Charles County, MO"
              ,"MOStFrancoisCounty"             ,"St Francois County, MO"
              ,"MOStLouisCounty"                ,"St Louis County, MO"
              ,"MOStoneCounty"                  ,"Stone County, MO"
          ),
          new ParserCategory("Montana"
              ,"MTFlatheadCounty"               ,"Flathead County, MT"
              ,"MTLewisAndClarkCounty"          ,"Lewis &amp; Clark County, MT"
          ),
          new ParserCategory("Nevada"
              ,"NVLasVegas"                     ,"Las Vegas, NV"
          ),
          new ParserCategory("New Hampshire"
              ,"NHHanover"                      ,"Hanover, NH"
              ,"NHKeeneMutualAid"               ,"Keene Mutual Aid, NH"
          ),
          new ParserCategory("New Jersey"
              ,"NJAtlanticareEMS"               ,"Atlanticare EMS, NJ"
              ,"NJBurlingtonCounty"             ,"Burlington County, NJ"
              ,"NJCamdenCounty"                 ,"Camden County, NJ"
              ,"NJCENCOM"                       ,"CENCOM (Central NJ), NJ"
              ,"NJCumberlandCounty"             ,"Cumberland County, NJ"
              ,"NJGloucesterCounty"             ,"Gloucester County, NJ"
              ,"NJHunterdonCounty"              ,"Hunterdon County, NJ"
              ,"NJMICOM"                        ,"MICCOM (Northern NJ), NJ"
              ,"NJMonmouthCounty"               ,"Monmouth County, NJ"
              ,"NJMorrisCounty"                 ,"Morris County, NJ"
              ,"NJNeptune"                      ,"Neptune, NJ"
              ,"NJOceanCounty"                  ,"Ocean County, NJ"
              ,"NJSalemCounty"                  ,"Salem County, NJ"
              ,"NJSomersetCounty"               ,"Somerset County, NJ"
              ,"NJSussexCounty"                 ,"Sussex County, NJ"
              ,"NJWayneTownship"                ,"Wayne Township, NJ"
              ,"NJWarrenCounty"                 ,"Warren County, NJ"
          ),
          new ParserCategory("New York"
              ,"NYAlbanyCounty"                 ,"Albany County, NY"
              ,"NYBroomeCounty"                 ,"Broome County, NY"
              ,"NYBuffalo"                      ,"Buffalo, NY"
              ,"NYCattaraugusCounty"            ,"Cattaraugus County, NY"
              ,"NYCayugaCounty"                 ,"Cayuga County, NY"
              ,"NYChautauquaCounty"             ,"Chautauqua County, NY"
              ,"NYChenangoCounty"               ,"Chenango County, NY"
              ,"NYClintonCounty"                ,"Clinton County, NY"
              ,"NYCortlandCounty"               ,"Cortland County, NY"
              ,"NYDelawareCounty"               ,"Delaware County, NY"
              ,"NYDuchessCounty"                ,"Duchess County, NY"
              ,"NYDixHills"                     ,"Dix Hills, NY"
              ,"NYErieCounty"                   ,"Erie County, NY"
              ,"NYGeneseeCounty"                ,"Genesee County, NY"
              ,"NYGreeneCounty"                 ,"Greene County, NY"
              ,"NYHerkimerCounty"               ,"Herkimer County, NY"
              ,"NYJeffersonCounty"              ,"Jefferson County, NY"
              ,"NYMadisonCounty"                ,"Madison County, NY"
              ,"NYMonroeCounty"                 ,"Monroe County, NY"
              ,"NYMontgomeryCounty"             ,"Montgomery County, NY"
              ,"NYNassauCounty"                 ,"Nassau County, NY"
              ,"NYNiagaraCounty"                ,"Niagara County, NY"
              ,"NYOneidaCounty"                 ,"Oneida County, NY"
              ,"NYOnondagaCounty"               ,"Onondaga County, NY"
              ,"NYOntarioCounty"                ,"Ontario County, NY"
              ,"NYOrangeCounty"                 ,"Orange County, NY"
              ,"NYOrleansCounty"                ,"Orleans County, NY"
              ,"NYOtsegoCounty"                 ,"Otsego County, NY"
              ,"NYPutnamCounty"                 ,"Putnam County, NY"
              ,"NYRocklandCounty"               ,"Rockland County, NY"
              ,"NYSomers"                       ,"Somers, NY"
              ,"NYSteubenCounty"                ,"Steuben County, NY"
              ,"NYSuffolkCountyAll"             ,"Suffolk County, NY"
              ,"NYSullivanCounty"               ,"Sullivan County, NY"
              ,"NYUlsterCounty"                 ,"Ulster County, NY"
              ,"NYWayneCounty"                  ,"Wayne County, NY"
              ,"NYWestchesterCounty"            ,"Westchester County, NY"
              ,"NYWyomingCounty"                ,"Wyoming County, NY"
          ),
          new ParserCategory("North Carolina"
              ,"NCAlexanderCounty"              ,"Alexander County, NC"
              ,"NCAnsonCounty"                  ,"Anson County, NC"
              ,"NCBrunswickCounty"              ,"Brunswick County, NC"
              ,"NCBuncombeCounty"               ,"Buncombe County, NC"
              ,"NCBurkeCounty"                  ,"Burke County, NC"
              ,"NCCabarrusCounty"               ,"Cabarrus County, NC"
              ,"NCCaldwellCounty"               ,"Caldwell County, NC"
              ,"NCCarteretCounty"               ,"Carteret County, NC"
              ,"NCCatawbaCounty"                ,"Catawba County, NC"
              ,"NCChathamCounty"                ,"Chatham County, NC"
              ,"NCClevelandCounty"              ,"Cleveland County, NC"
              ,"NCCumberlandCounty"             ,"Cumberland County, NC"
              ,"NCCurrituckCounty"              ,"Currituck County, NC"
              ,"NCDavidsonCounty"               ,"Davidson County, NC"
              ,"NCDavieCounty"                  ,"Davie County, NC"
              ,"NCDuplinCounty"                 ,"Duplin County, NC"
              ,"NCDurhamCounty"                 ,"Durham County, NC"
              ,"NCForsythCounty"                ,"Forsyth County, NC"
              ,"NCGastonCounty"                 ,"Gaston County, NC"
              ,"NCGrahamCounty"                 ,"Graham County, NC"
              ,"NCGreensboro"                   ,"Greensboro, NC"
              ,"NCGuilfordCounty"               ,"Guilford County, NC"
              ,"NCHalifaxCounty"                ,"Halifax County, NC"
              ,"NCHarnettCounty"                ,"Harnett County, NC"
              ,"NCHaywoodCounty"                ,"Haywood County, NC"
              ,"NCHokeCounty"                   ,"Hoke County, NC"
              ,"NCIredellCounty"                ,"Iredell County, NC"
              ,"NCJacksonCounty"                ,"Jackson County, NC"
              ,"NCJohnstonCounty"               ,"Johnston County, NC"
              ,"NCLincolnCounty"                ,"Lincoln County, NC"
              ,"NCMaconCounty"                  ,"Macon County, NC"
              ,"NCMadisonCounty"                ,"Madison County, NC"
              ,"NCMecklenburgCounty"            ,"Mecklenburg County, NC"
              ,"NCMontgomeryCounty"             ,"Montgomery County, NC"
              ,"NCMooreCounty"                  ,"Moore County, NC"
              ,"NCNashCounty"                   ,"Nash County, NC"
              ,"NCNewHanoverCounty"             ,"New Hanover County, NC"
              ,"NCNorthamptonCounty"            ,"Northampton County, NC"
              ,"NCOnslowCounty"                 ,"Onslow County, NC"
              ,"NCOrangeCounty"                 ,"Orange County, NC"
              ,"NCPenderCounty"                 ,"Pender County, NY"
              ,"NCPersonCounty"                 ,"Person County, NC"
              ,"NCPittCounty"                   ,"Pitt County, NC"
              ,"NCPolkCounty"                   ,"Polk County, NC"
              ,"NCRandolphCounty"               ,"Randolph County, NC"
              ,"NCRockinghamCounty"             ,"Rockingham County, NC"
              ,"NCRowanCounty"                  ,"Rowan County, NC"
              ,"NCRutherfordCounty"             ,"Rutherford County, NC"
              ,"NCSampsonCounty"                ,"Sampson County, NC"
              ,"NCSurryCounty"                  ,"Surry County, NC"
              ,"NCStanlyCounty"                 ,"Stanly County, NC"
              ,"NCTransylvaniaCounty"           ,"Transylvania County, NC"
              ,"NCUnionCounty"                  ,"Union County, NC"
              ,"NCWakeCounty"                   ,"Wake County, NC"
              ,"NCWataugaCounty"                ,"Watauga County, NC"
              ,"NCWayneCounty"                  ,"Wayne County, NC"
              ,"NCWilkesCounty"                 ,"Wilkes County, NC"
              ,"NCWilsonCounty"                 ,"Wilson County, NC"
          ),
          new ParserCategory("North Dakota"
              ,"NDCassCounty"                   ,"Cass County, ND"
          ),
          new ParserCategory("Ohio"
              ,"OHAshtabulaCounty"              ,"Ashtabula County, OH"
              ,"OHBrownCounty"                  ,"Brown County, OH"
              ,"OHButlerCounty"                 ,"Butler County, OH"
              ,"OHClermontCounty"               ,"Clermont County, OH"
              ,"OHClintonCounty"                ,"Clinton County, OH"
              ,"OHDelawareCounty"               ,"Delaware County, OH"
              ,"OHEnglewood"                    ,"Englewood Regional Communications, OH"
              ,"OHFairfieldCounty"              ,"Fairfield County, OH"
              ,"OHFranklin"                     ,"Franklin, OH"
              ,"OHGeaugaCounty"                 ,"Geauga County, OH"
              ,"OHHamiltonCounty"               ,"Hamilton County, OH"
              ,"OHHighlandCounty"               ,"Highland County, OH"
              ,"OHHudson"                       ,"Hudson, OH"
              ,"OHKnoxCounty"                   ,"Knox County, OH"
              ,"OHLawrenceCounty"               ,"Lawrence County, OH"
              ,"OHLickingCounty"                ,"Licking County, OH"
              ,"OHLoveland"                     ,"Loveland, OH"
              ,"OHMadisonCounty"                ,"Madison County, OH"
              ,"OHMedinaCounty"                 ,"Medina County, OH"
              ,"OHMorrowCounty"                 ,"Morrow County, OH"
              ,"OHNECC"                         ,"NECC, OH"
              ,"OHOxford"                       ,"Oxford, OH"
              ,"OHPortageCounty"                ,"Portage County, OH"
              ,"OHShelbyCounty"                 ,"Shelby County, OH"
              ,"OHSugarCreek"                   ,"Sugar Creek Twns, OH"
              ,"OHSummitCounty"                 ,"Summit County, OH"
              ,"OHStarkCounty"                  ,"Stark County, OH"
              ,"OHTrumbullCounty"               ,"Trumbull County, OH"
              ,"OHUnionCounty"                  ,"Union County, OH"
              ,"OHWadsworth"                    ,"Wadsworth, OH"
              ,"OHWarrenCounty"                 ,"Warren County, OH"
              ,"OHWayneCounty"                  ,"Wayne County, OH"
              ,"OHXenia"                        ,"Xenia Twp, OH"
          ),
          new ParserCategory("Oklahoma"
              ,"OKPottawatomieCounty"           ,"Pottawatomie County, OK"
              ,"OKSeminoleCounty"               ,"Seminole County, OK"
              ,"OKTulsa"                        ,"Tusla, OK"
          ),
          new ParserCategory("Oregon"
              ,"ORBentonCounty"                 ,"Benton County, OR"
              ,"ORClackamasCounty"              ,"Clackamas County, OR"
              ,"ORCrookCounty"                  ,"Crook County, OR"
              ,"ORDeschutesCounty"              ,"Deschutes County, OR"
              ,"ORJacksonCounty"                ,"Jackson County, OR"
              ,"ORKlamathCounty"                ,"Klamath County, OR"
              ,"ORLaneCounty"                   ,"Lane County, OR"
              ,"ORLincolnCity"                  ,"Lincoln City, OR"
              ,"ORLinnCounty"                   ,"Linn County, OR"
              ,"ORMarionCountyN"                ,"Marion County(N), OR"
              ,"ORWashingtonCounty"             ,"Washington County, OR"
          ),
          new ParserCategory("Pennsylvania"
              ,"PAAdamsCounty"                  ,"Adams County, PA"
              ,"PAAlleghenyCounty"              ,"Allegheny County, PA"
              ,"PAArmstrongCounty"              ,"Armstrong County, PA"
              ,"PABeaverCounty"                 ,"Beaver County, PA"
              ,"PABerksCounty"                  ,"Berks County, PA"
              ,"PABlairCounty"                  ,"Blair County, PA"
              ,"PABucksCounty"                  ,"Bucks County, PA"
              ,"PAButlerCounty"                 ,"Butler County, PA"
              ,"PACambriaCounty"                ,"Cambria County, PA"
              ,"PAChesterCounty"                ,"Chester County, PA"
              ,"PAClarionCounty"                ,"Clarion County, PA"
              ,"PAClearfieldCounty"             ,"Clearfield County, PA"
              ,"PACrawfordCounty"               ,"Crawford County, PA"
              ,"PADauphinCounty"                ,"Dauphin County, PA"
              ,"PADelawareCounty"               ,"Delaware County, PA"
              ,"PAElkCounty"                    ,"Elk County, PA"
              ,"PAErieCounty"                   ,"Erie County, PA"
              ,"PAFayetteCounty"                ,"Fayette County, PA"
              ,"PAJeffersonCounty"              ,"Jefferson County, PA"
              ,"PAJuniataCounty"                ,"Juniata County, PA"
              ,"PALackawannaCounty"             ,"Lackawanna County, PA"
              ,"PALancasterCounty"              ,"Lancaster County, PA"
              ,"PALebanonCounty"                ,"Lebanon County, PA"
              ,"PALehighCounty"                 ,"Lehigh County, PA"
              ,"PAMercerCounty"                 ,"Mercer County, PA"
              ,"PAMifflinCounty"                ,"Mifflin County, PA"
              ,"PAMonroeCounty"                 ,"Monroe County, PA"
              ,"PAMonroeville"                  ,"Monroeville, PA"
              ,"PAMontgomeryCounty"             ,"Montgomery County, PA"
              ,"PANorthamptonCounty"            ,"Northampton County, PA"
              ,"PANorthumberlandCounty"         ,"Northumberland County, PA"
              ,"PAPikeCounty"                   ,"Pike County, PA"
              ,"PASnyderCounty"                 ,"Snyder County, PA"
              ,"PASomersetCounty"               ,"Somerset County, PA"
              ,"PASusquehannaCounty"            ,"Susquehanna County, PA"
              ,"PATiogaCounty"                  ,"Tioga County, PA"
              ,"PAVenangoCounty"                ,"Venango County, PA"
              ,"PAWashingtonCounty"             ,"Washington County, PA"
              ,"PAWestmorelandCounty"           ,"Westmoreland County, PA"
              ,"PAYorkCounty"                   ,"York County, PA"
          ),
          new ParserCategory("Rhode Island"
              ,"RIWashingtonCounty"             ,"Washington County, RI"
          ),
          new ParserCategory("South Carolina"
              ,"SCAndersonCounty"               ,"Anderson County, SC"
              ,"SCCharlestonCounty"             ,"Charleston County, SC"
              ,"SCFlorenceCounty"               ,"Florence County, SC"
              ,"SCGreenvilleCounty"             ,"Greenville County, SC"
              ,"SCOconeeCounty"                 ,"Oconee County, SC"
              ,"SCRichlandCounty"               ,"Richland County, SC"
              ,"SCSpartanburgCounty"            ,"Spartanburg County, SC"
              ,"SCYorkCounty"                   ,"York County, SC"
          ),
          new ParserCategory("South Dakota"
              ,"SDLincolnCounty"                ,"Lincoln County, SD"
              ,"SDMinnehahaCounty"              ,"Minnehaha County, SD"
              ,"SDPenningtonCounty"             ,"Pennington County, SD"
              ,"SDUnionCounty"                  ,"Union County, SD"
          ),
          new ParserCategory("Tennessee"
              ,"TNAndersonCounty"               ,"Anderson County, TN"
              ,"TNBlountCounty"                 ,"Blount County, TN"
              ,"TNCumberlandCounty"             ,"Cumberland County, TN"
              ,"TNHamiltonCounty"               ,"Hamilton County, TN"
              ,"TNHumphreysCounty"              ,"Humphreys County, TN"
              ,"TNJeffersonCounty"              ,"Jefferson County, TN"
              ,"TNJohnsonCounty"                ,"Johnson County, TN"
              ,"TNLoudonCounty"                 ,"Loudon County, TN"
              ,"TNOliverSprings"                ,"Oliver Springs, TN"
              ,"TNSumnerCounty"                 ,"Sumner County, TN"
              ,"TNUnionCounty"                  ,"Union County, TN"
              ,"TNWashingtonCounty"             ,"Washington County, TN"
              ,"TNWilliamsonCounty"             ,"Williamson County, TN"
          ),
          new ParserCategory("Texas"
              ,"TXAcadianAmbulance"             ,"Acadian Ambulance, TX"
              ,"TXBellCounty"                   ,"Bell County, TX"
              ,"TXBexarCounty"                  ,"Bexar County, TX"
              ,"TXCollinCounty"                 ,"Collin County, TX"
              ,"TXCyCreekCommCenter"            ,"Cy Creek Comm Center, TX"
              ,"TXCyFair"                       ,"Cypress-Harris, TX"
              ,"TXDecatur"                      ,"Decatur, TX"
              ,"TXDentonCounty"                 ,"Denton County, TX"
              ,"TXGalvestonCounty"              ,"Galveston County, TX"
              ,"TXHarrisCountyESD1"             ,"Harris County ESD1, TX"
              ,"TXHarrisCountyNWEMS"            ,"Harris County NWEMS, TX"
              ,"TXHaysCounty"                   ,"Hays County, TX"
              ,"TXHumble"                       ,"Humble, TX"
              ,"TXKeller"                       ,"Keller, TX"
              ,"TXLeagueCity"                   ,"League City, TX"
              ,"TXLewisville"                   ,"Lewisville, TX"
              ,"TXLibertyCounty"                ,"Liberty County, TX"
              ,"TXLongview"                     ,"Longview, TX"
              ,"TXManvel"                       ,"Manvel, TX"
              ,"TXMontgomeryCounty"             ,"Montgomery County, TX"
              ,"TXNassauBay"                    ,"Nassau Bay, TX"
              ,"TXNuecesCounty"                 ,"Nueces County, TX"
              ,"TXTarrantCounty"                ,"Tarrent County, TX"
              ,"TXRockwallCounty"               ,"Rockwall County, TX"
              ,"TXWebster"                      ,"Webster, TX"
              ,"TXWilliamsonCounty"             ,"Williamson County, TX"
          ),
          new ParserCategory("Utah"
              ,"UTDavisCounty"                  ,"Davis County, UT"
              ,"UTSummitCounty"                 ,"Summit County, UT"
          ),
          new ParserCategory("Virginia"
              ,"VAAccomackCounty"               ,"Accomack County, VA"
              ,"VAAlbemarleCounty"              ,"Albemarle County, VA"
              ,"VAAugustaCounty"                ,"Augusta County, VA"
              ,"VABrunswickCounty"              ,"Brunswick County, VA"
              ,"VABotetourtCounty"              ,"Botetourt County, VA"
              ,"VACampbellCounty"               ,"Campbell County, VA"
              ,"VAFauquierCounty"               ,"Fauquier County, VA"
              ,"VAFranklinCounty"               ,"Franklin County, VA"
              ,"VAFrederickCounty"              ,"Frederick County, VA"
              ,"VAHalifaxCounty"                ,"Halifax County, VA"
              ,"VAHanoverCounty"                ,"Hanover County, VA"
              ,"VAHenryCounty"                  ,"Henry County, VA"
              ,"VAIsleOfWightCounty"            ,"Isle of Wight County, VA"
              ,"VAKingGeorgeCounty"             ,"King George County, VA"
              ,"VALexingtonRockbridgeCounty"    ,"Lexington &amp; Rockbridge Counties"
              ,"VALoudounCounty"                ,"Loudoun, VA"
              ,"VALouisaCounty"                 ,"Louisa County, VA"
              ,"VALunenburgCounty"              ,"Lunenburg County, VA"
              ,"VANewKentCounty"                ,"New Kent County, VA"
              ,"VANorthamptonCounty"            ,"Northampton County, VA"
              ,"VAOrangeCounty"                 ,"Orange County, VA"
              ,"VAPageCounty"                   ,"Page County, VA"
              ,"VAPittsylvaniaCounty"           ,"Pittsylvania County, VA"
              ,"VAPrinceGeorgeCounty"           ,"Prince George County, VA"
              ,"VAPrinceWilliamCounty"          ,"Prince William County, VA"
              ,"VARockinghamCounty"             ,"Rockingham County, VA"
              ,"VARoanokeCity"                  ,"Roanoke City, VA"
              ,"VARoanokeCounty"                ,"Roanoke County, VA"
              ,"VAWashingtonCounty"             ,"Washington County, VA"
              ,"VAWaynesboro"                   ,"Waynesboro, VA"
              ,"VAWinchester"                   ,"Winchester, VA"
          ),
          new ParserCategory("Washington"
              ,"WAClarkCounty"                  ,"Clark County, WA"
              ,"WAJeffersonCounty"              ,"Jefferson County, WA"
              ,"WAPierceCounty"                 ,"Pierce County, WA"
              ,"WAYakimaCounty"                 ,"Yakima County, WA"
          ),
          new ParserCategory("West Virginia"
              ,"WVCabellCounty"                 ,"Cabell County, WV"
              ,"WVKanawhaCounty"                ,"Kanawha County, WV"
              ,"WVMineralCounty"                ,"Mineral County, WV"
          ),
          new ParserCategory("Wisconsin"
              ,"WIBrownCounty"                  ,"Brown County, WI"
              ,"WICalumetCounty"                ,"Calumet County, WI"
              ,"WIEauClaire"                    ,"Eau Claire, WI"
              ,"WIKenoshaCounty"                ,"Kenosha County, WI"
              ,"WIOutagamieCounty"              ,"Outagamie County, WI"
              ,"WIWinnebagoCounty"              ,"Winnebago County, WI"
          ),
          new ParserCategory("Wyoming"
              ,"WYNatronaCounty"                ,"Natrona County, WY"
          ),
          new ParserCategory("Alberta, CA"
              ,"ZCAABLacombeCounty"             ,"Lacombe County, AB"
              ,"ZCAABLamontCounty"              ,"Lamont County, AB"
              ,"ZCAABRedDeerCounty"             ,"Red Deer Count, AB"
          ),
          new ParserCategory("Ontario, CA"
              ,"ZCAONMississauga"               ,"Mississauga, ON"
          ),
          new ParserCategory("Australia"
              ,"ZAUNewSouthWales"               ,"New South Wales, AU"
              ,"ZAUGeneral"                     ,"Generic Location"
              ,"ZAUGeneralSlash"                ,"Generic (slash field separator)"
              ,"ZAUGeneralDash"                 ,"Generic (dash field separator)"
          ),
          new ParserCategory("United Kingdom"
              ,"ZUKWestMidlands"                ,"West Midlands Region, UK"
              ,"ZUKGeneral"                     ,"Generic Location"
              ,"ZUKGeneralSlash"                ,"Generic (slash field separator)"
              ,"ZUKGeneralDash"                 ,"Generic (dash field separator)"
          )
      );
}
