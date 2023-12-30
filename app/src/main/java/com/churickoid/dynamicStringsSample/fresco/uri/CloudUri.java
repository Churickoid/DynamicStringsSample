package com.churickoid.dynamicStringsSample.fresco.uri;

import android.net.Uri;

import java.util.Set;

public class CloudUri {

    public static Uri removeQueryParameter(Uri original, String... removableNames) {
        Uri.Builder builder = original.buildUpon().clearQuery();
        Set<String> paramNames = original.getQueryParameterNames();
        mainloop:
        for (String name : paramNames) {
            for (String originalName : removableNames) {
                if (originalName.equalsIgnoreCase(name)) {
                    //Skip it
                    continue mainloop;
                }
            }
            for (String value : original.getQueryParameters(name)) {
                builder.appendQueryParameter(name, value);
            }
        }
        return builder.build();
    }
}