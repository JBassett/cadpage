package net.anei.cadpage.parsers.PA;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;


@RunWith(Enclosed.class)
public class PAErieCountyParserTest {
  
  public static class TestA extends PAErieCountyAParserTest {
    public TestA() {
      setParser(new PAErieCountyParser());
    }
  }
  
  public static class TestB extends PAErieCountyBParserTest {
    public TestB() {
      setParser(new PAErieCountyParser());
    }
  }
}