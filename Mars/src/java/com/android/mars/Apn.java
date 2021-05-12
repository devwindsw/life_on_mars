package com.android.mars;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

import java.util.Collection;
import java.util.Iterator;

public class Apn extends Handler {
    private static final String TAG = "MarsApn";

    private static final int EVENT_START = 1;
    private static final int TIMEOUT = 120 * 1000;

    private Context mContext = null;
    private ConnectivityManager mConnManager = null;
    private ConnectivityManager.NetworkCallback mCallback = null;
    private boolean mHasRequested = false;

    public Apn(Context context) {
      mContext = context;
    }

    public void handleMessage(Message msg) {
      Log.d(TAG, "handleMessage what=" + msg.what);

      switch (msg.what) {
        case EVENT_START:
          if (mHasRequested) break;
          requestNetwork();
          break;
      }
    }

    public void start() {
	removeMessages(EVENT_START);
        sendEmptyMessageDelayed(EVENT_START, 60000);
    }

    public void requestNetwork() {
      Log.e(TAG, "requestNetwork");

      mConnManager = getConnectivityManager();
      if (mConnManager == null) {
	  Log.e(TAG, "requestNetwork no ConnectionManager");
          return;
      }

      mCallback = new ConnectivityManager.NetworkCallback() {
              @Override
              public void onAvailable(Network network) {
                  Log.d(TAG, "onAvailable network=" + network); 
                  cancelTimer();
                  handleOnAvailable(network);
              }
 
              @Override
              public void onUnavailable() {
                  Log.d(TAG, "onUnavailable"); 
              }
 
              @Override
              public void onLost(Network network) {
                  Log.d(TAG, "onLost network=" + network); 
              }
 
              @Override
              public void onCapabilitiesChanged(Network network, NetworkCapabilities capabilities) {
                  Log.d(TAG, "onCapabilitiesChanged network=" + network + ", capa=" + capabilities); 
              }

              @Override
              public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                  Log.d(TAG, "onLinkPropertiesChanged network=" + network + ", link=" + linkProperties); 

                  Collection<InetAddress> servers = linkProperties.getPcscfServers();
                  if (servers.isEmpty()) {
                    Log.d(TAG, "onLinkProperties no Pcscf Server");
                    return;
                  }

                  Iterator it = servers.iterator();
                  while(it.hasNext()) {
                      InetAddress inetAddress = (InetAddress)it.next();
                      Log.d(TAG, "onLinkProperties inetAddress=" + inetAddress);

                      if (inetAddress.isAnyLocalAddress()
                              || inetAddress.isLinkLocalAddress()
                              || inetAddress.isLoopbackAddress()) {
                          continue;
                      }

                      if ((inetAddress instanceof Inet6Address) || (inetAddress instanceof InetAddress)) {
                        String address = inetAddress.getHostAddress();
                        Log.d(TAG, "onLinkProperties address=" + address);
                      }
                  }
              }
          };
        
      NetworkRequest request = new NetworkRequest.Builder()
              .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
              .addCapability(NetworkCapabilities.NET_CAPABILITY_IMS)
              .build();

      mHasRequested = true;
      mConnManager.requestNetwork(request, mCallback, TIMEOUT);
    }

    private ConnectivityManager getConnectivityManager() {
        if (mConnManager != null) return mConnManager;

        try {
            for (int i = 0; i < 5; i++) {
                mConnManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (mConnManager != null) break;
		Log.e(TAG, "requestNetwork try again");
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Log.e(TAG, "getConnectivityManager e=" + e.getMessage());
        } finally {
            return mConnManager;
        }
    }

    private void cancelTimer() {
    }

    private void handleOnAvailable(Network network) {
        mConnManager.bindProcessToNetwork(network); 
    }
}
