package com.safe.drive.common;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Milton on 30/04/2016.
 */
public class BackgroundService extends IntentService {
    /**
     * This is the constructor to create an IntentService.
     */
    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
