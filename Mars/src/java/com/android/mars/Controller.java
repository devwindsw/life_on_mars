package com.android.mars;

import com.android.internal.annotations.VisibleForTesting;

public class Controller {
    private static final String TAG = "MarsController";

    Apn mApn = null;
    boolean mIsStarted = false;
    
    public Controller(Apn apn) {
      mApn = apn;
    }

    @VisibleForTesting
    public void start() {
      mIsStarted = true;
      mApn.start();
    }

    @VisibleForTesting
    public boolean isStarted() {
      return mIsStarted;
    }
}
