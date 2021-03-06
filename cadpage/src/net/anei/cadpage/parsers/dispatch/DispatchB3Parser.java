package net.anei.cadpage.parsers.dispatch;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.CodeSet;
import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchB2Parser;

public class DispatchB3Parser extends DispatchB2Parser {
  
  private String prefix = null;
  private Pattern prefixPattern = null;

  public DispatchB3Parser(String prefix, String[] cityList, String defCity, String defState) {
    super(cityList, defCity, defState);
    setupCallList((CodeSet)null);
    this.prefix = prefix;
  }

  public DispatchB3Parser(String prefix, String[] cityList, String defCity, String defState, int flags) {
    super(cityList, defCity, defState, flags);
    setupCallList((CodeSet)null);
    this.prefix = prefix;
  }

  public DispatchB3Parser(Pattern prefixPattern, String[] cityList, String defCity, String defState) {
    super(cityList, defCity, defState);
    setupCallList((CodeSet)null);
    this.prefixPattern = prefixPattern;
  }

  public DispatchB3Parser(String[] cityList, String defCity, String defState) {
    super(cityList, defCity, defState);
    setupCallList((CodeSet)null);
  }

  public DispatchB3Parser(String prefix, Properties cityCodes, String defCity, String defState) {
    super(cityCodes, defCity, defState);
    setupCallList((CodeSet)null);
    this.prefix = prefix;
  }

  public DispatchB3Parser(Pattern prefixPattern, Properties cityCodes, String defCity, String defState) {
    super(cityCodes, defCity, defState);
    setupCallList((CodeSet)null);
    this.prefixPattern = prefixPattern;
  }

  public DispatchB3Parser(Properties cityCodes, String defCity, String defState) {
    super(cityCodes, defCity, defState);
    setupCallList((CodeSet)null);
  }
  
  @Override
 protected boolean parseMsg(String subject, String body, Data data) {
    String tmp;
    if ((tmp = checkPrefix(body)) != null) {
      body = tmp;
    } else if ((tmp = checkPrefix(subject)) != null) {
      subject = tmp;
    } else {
      return false;
    }
    
    boolean v3 = subject.length() > 0;
    if (v3) body = subject + " @ " + body;
    if (!super.parseMsg(body, data)) return false;
    
    if (v3 && data.strCall.equals("RUN REPORT")) {
      int pt = data.strPlace.indexOf(" @ ");
      if (pt >= 0) {
        data.strCode = data.strPlace.substring(0,pt).trim();
        data.strPlace = data.strPlace.substring(pt+3).trim();
      }
    }
    return true;
  }

  /**
   * Internal method to check body or subject against and
   * paser required prefix strings
   * @param body string to be checked for prefix
   * @return null if prefix check fails.  Otherwise return
   * original body with the matching prefix removed
   */
  private String checkPrefix(String body) {
    if (prefix != null) {
      if (!body.startsWith(prefix)) return null;
      return body.substring(prefix.length()).trim();
    } else if (prefixPattern != null) {
      Matcher match = prefixPattern.matcher(body);
      if (!match.lookingAt()) return null;
      return body.substring(match.end()).trim();
    }
    return body;
  }
  
  @Override
  protected boolean isPageMsg(String body) {
    if (prefix != null || prefixPattern != null) return true;
    return super.isPageMsg(body);
  }
}
