package org.robolectric.shadows;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.util.ReflectionHelpers;

@Implements(NfcAdapter.class)
public class ShadowNfcAdapter {
  @RealObject NfcAdapter nfcAdapter;
  private Activity enabledActivity;
  private PendingIntent intent;
  private IntentFilter[] filters;
  private String[][] techLists;
  private Activity disabledActivity;

  @Implementation
  public static NfcAdapter getDefaultAdapter(Context context) {
    return ReflectionHelpers.callConstructorReflectively(NfcAdapter.class);
  }

  @Implementation
  public void enableForegroundDispatch(Activity activity, PendingIntent intent, IntentFilter[] filters, String[][] techLists) {
    this.enabledActivity = activity;
    this.intent = intent;
    this.filters = filters;
    this.techLists = techLists;
  }

  @Implementation
  public void disableForegroundDispatch(Activity activity) {
    disabledActivity = activity;
  }

  public Activity getEnabledActivity() {
    return enabledActivity;
  }

  public PendingIntent getIntent() {
    return intent;
  }

  public IntentFilter[] getFilters() {
    return filters;
  }

  public String[][] getTechLists() {
    return techLists;
  }

  public Activity getDisabledActivity() {
    return disabledActivity;
  }
}
