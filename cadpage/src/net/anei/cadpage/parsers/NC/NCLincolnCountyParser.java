package net.anei.cadpage.parsers.NC;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchOSSIParser;

/**
 * Lincoln County, NC
 */
public class NCLincolnCountyParser extends DispatchOSSIParser {
  
  private static Pattern CODE_PTN = Pattern.compile("\\b\\d{1,2}-[A-Z]-\\d\\b");
  
  public NCLincolnCountyParser() {
    super("LINCOLN COUNTY", "NC",
           "ID: FYI? SRC ID? CODE? CALL ADDR! ( X X? | PLACE X X? | ) INFO+");
  }
  
  @Override
  public String getFilter() {
    return "cad@do-not-reply-lincolne911.org,93001";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    
    // The OSSI parser either expects a leading ID field, or does not expect one.  It can't handle our case
    // where it sometimes is there and sometimes isn't.  We fix that by adding a dummy ID if there isn't one.
    if (body.startsWith("CAD:")) body = "0:" + body;
    
    // For medical call, the code is duplicated and the one in the call
    // description has dashes, which we are going to use a field separators
    // Easy solution is to just get rid of it.
    body = CODE_PTN.matcher(body).replaceFirst("");
    
    // Change dashes to regular semicolon field separators
    body = body.replace('-', ';');
    if (! super.parseMsg(body, data)) return false;
    return true;
  }
  
  private class MyIdField extends IdField {
    public MyIdField() {
      setPattern(Pattern.compile("\\d{9,}"));
    }
  }
  
  private class MyCodeField extends CodeField {
    public MyCodeField() {
      setPattern(Pattern.compile("\\d{2,3}[A-Z]\\d{2}[A-Za-z]?"));
    }
  }
  
  
  @Override
  protected Field getField(String name) {
    if (name.equals("ID")) return new MyIdField();
    if (name.equals("CODE")) return new MyCodeField();
    return super.getField(name);
  }
}
