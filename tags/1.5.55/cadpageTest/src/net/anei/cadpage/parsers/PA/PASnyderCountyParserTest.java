package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class PASnyderCountyParserTest extends BaseParserTest {
  
  public PASnyderCountyParserTest() {
    setParser(new PASnyderCountyParser(), "SNYDER COUNTY", "PA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "SNYDER911:IMMTRA>IMMEDIATE TRANSPORT 308 S MARKET ST XS:SASSAFRAS ST SELINSGROVE BORO YOUNG, AMBER Map:Grids:00000,000 CAD:2011-0000005449",
        "CALL:IMMEDIATE TRANSPORT",
        "ADDR:308 S MARKET ST",
        "CITY:SELINSGROVE",
        "X:SASSAFRAS ST",
        "NAME:YOUNG, AMBER",
        "ID:2011-0000005449");

    doTest("T2",
        "SNYDER911:CARSY>CARDIAC SYMPTOMS 2084 ROUTE 522 PENN TWP HUFFNAGLE, CRYSTAL Map:Grids:00000,000 CAD:2011-0000005448",
        "CALL:CARDIAC SYMPTOMS",
        "ADDR:2084 ROUTE 522",
        "CITY:PENN TWP",
        "NAME:HUFFNAGLE, CRYSTAL",
        "ID:2011-0000005448");

    doTest("T3",
        "SNYDER911:ILLPER>ILLPERSON 620 UNIVERSITY AVE SELINSGROVE BORO ESHENAUR, MOLLY Map:Grids:00000,000 CAD:2011-0000005444",
        "CALL:ILLPERSON",
        "ADDR:620 UNIVERSITY AVE",
        "CITY:SELINSGROVE",
        "NAME:ESHENAUR, MOLLY",
        "ID:2011-0000005444");

    doTest("T4",
        "SNYDER911:GASOUT>GAS ODOR/OUTSIDE 1011 SUNRISE DR SELINSGROVE BORO UGI/SAND Map:Grids:00000,000 CAD 2011-0000005999",
        "CALL:GAS ODOR/OUTSIDE",
        "ADDR:1011 SUNRISE DR",
        "CITY:SELINSGROVE",
        "NAME:UGI / SAND",
        "ID:2011-0000005999");

    doTest("T5",
        "SNYDER911:AUTONI>AUTO ACCIDENT/NO INJURY 1769 N SUSQUEHANNA TRL MONROE TWP Map:Grids:00000,000 CAD 2011-0000006003",
        "CALL:AUTO ACCIDENT/NO INJURY",
        "ADDR:1769 N SUSQUEHANNA TRL",
        "CITY:MONROE TWP",
        "ID:2011-0000006003");

    doTest("T6",
        "SNYDER911:FIREWI>FIRE/WILDFIRE 1035 N SUSQUEHANNA TRL XS:SUSQUEHANNA VALLEY MALL DR MONROE TWP DUN, SCOTT Map:00000,000 CAD:2011-0000005987",
        "CALL:FIRE/WILDFIRE",
        "ADDR:1035 N SUSQUEHANNA TRL",
        "CITY:MONROE TWP",
        "X:SUSQUEHANNA VALLEY MALL DR",
        "NAME:DUN, SCOTT",
        "MAP:00000,000",
        "ID:2011-0000005987");

    doTest("T7",
        "SYNDER911:AAFIRE>AUTO ALARM/FIRE 111 MARKETPLACE BLVD MONROE TWP SECURITY SERVICE  CO., 8717 Map:Grids: CAD:2011-0000005983",
        "CALL:AUTO ALARM/FIRE",
        "ADDR:111 MARKETPLACE BLVD",
        "CITY:MONROE TWP",
        "NAME:SECURITY SERVICE CO , 8717",
        "ID:2011-0000005983");

    doTest("T8",
        "SYNDER911:AUTOIN>AUTO ACCIDENT/INJURY 892 ROUTE 522 PENN TWP Map:Grids:00000,000 Cad:2011-0000005857",
        "CALL:AUTO ACCIDENT/INJURY",
        "ADDR:892 ROUTE 522",
        "CITY:PENN TWP",
        "ID:2011-0000005857");
  }
  
  public static void main(String[] args) {
    new PASnyderCountyParserTest().generateTests("T1", "CALL ADDR CITY X PLACE NAME PHONE MAP ID");
  }
}
