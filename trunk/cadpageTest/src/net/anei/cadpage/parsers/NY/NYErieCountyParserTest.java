package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.NY.NYErieCountyParser;

import org.junit.Test;


public class NYErieCountyParserTest extends BaseParserTest {
  
  public NYErieCountyParserTest() {
    setParser(new NYErieCountyParser(), "ERIE COUNTY", "NY");
  }
  
  @Test
  public void testAmhParser() {
    
    doTest("T1",
        "AMH 238 WESTFIELD RD EMS 79 YO F/  CHEST PAIN",
        "ADDR:238 WESTFIELD RD",
        "CALL:EMS 79 YO F / CHEST PAIN");
        
    doTest("T2",
        "AMH 52 ENDICOTT DR EMS 82 YO M",
        "ADDR:52 ENDICOTT DR",
        "CALL:EMS 82 YO M");
        
    doTest("T3",
        "AMH 670 LONGMEADOW RD EMS 71 Y/O F CHEST PAINS, DIFFICULTY BREATHING",
        "ADDR:670 LONGMEADOW RD",
        "CALL:EMS 71 Y / O F CHEST PAINS, DIFFICULTY BREATHING");
        
    doTest("T4",
        "AMH 3030 SHERIDAN DR EMS RM 146 58 M TROUBLE BREATHING",
        "ADDR:3030 SHERIDAN DR",
        "CALL:EMS RM 146 58 M TROUBLE BREATHING");
        
    doTest("T5",
        "AMH 35 ELM RD EMS 69M CHEST/ARM PAIN",
        "ADDR:35 ELM RD",
        "CALL:EMS 69M CHEST / ARM PAIN");
  }
  
  @Test
  public void testErieParser() {
    doTest("T1",
        "ALERT@ERIE.GOV FIRE CO-DETECTOR 262 MILLER ST APT: GARAGE LANCASTER TOWN CO DETECTOR ACTIV / NO SYMPTOMS",
        "CALL:FIRE CO-DETECTOR",
        "ADDR:262 MILLER ST",
        "APT:GARAGE",
        "CITY:LANCASTER TOWN",
        "INFO:CO DETECTOR ACTIV / NO SYMPTOMS");

  }
}