package net.anei.cadpage.vendors;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.net.Uri;
import net.anei.cadpage.HttpService;
import net.anei.cadpage.R;
import net.anei.cadpage.HttpService.HttpRequest;
import net.anei.cadpage.donation.DonationManager;
import net.anei.cadpage.donation.DonationManager.DonationStatus;
import net.anei.cadpage.donation.UserAcctManager;

class CadpageVendor extends Vendor {
  
  CadpageVendor() {
    super(R.string.cadpage_title,
          R.string.cadpage_summary,
          R.string.cadpage_text,
          R.drawable.ic_launcher,
          R.drawable.ic_launcher,
          "http://www.cadpage-paging.net",
          null,
          null);
  }
//
//  @Override
//  boolean isAvailable() {
//    return true;
//  }

  @Override
  void profileReq(Context context) {
    // TODO Generate our own local configuration window
  }

  @Override
  void sendRegisterReq(Context context, String registrationId) {
    Uri uri = buildRequestUri("register", registrationId);
    Uri.Builder builder = uri.buildUpon();
    
    builder.appendQueryParameter("name", "Ken Corbin");
    
    String meid = UserAcctManager.instance().getMEID();
    if (meid != null) builder.appendQueryParameter("MEID",meid);
    
    DonationStatus status = DonationManager.instance().status(); 
    String expireDate = null;
    if (status == DonationManager.DonationStatus.LIFE) expireDate = "LIFE";
    else {
      Date expDate = DonationManager.instance().expireDate();
      if (expDate != null) {
        expireDate = DATE_FORMAT.format(expDate);
      } else {
        expireDate = null;
      }
    }
    if (expireDate != null) builder.appendQueryParameter("expDate", expireDate);
    
    builder.appendQueryParameter("resetEmail", "N");
    
    uri = builder.build();
    HttpService.addHttpRequest(context, new HttpRequest(uri){});
  }
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMddyyyy");
}
