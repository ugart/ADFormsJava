package br.adforms;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;

public class ADFormsApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        Fabric.with(this, new Crashlytics());

        Stetho.initializeWithDefaults(this);

    }

}

