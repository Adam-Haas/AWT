package uk.co.adamhaas.webserver;

import android.content.Context;

public class WebServerFactory {
    public static WebServer getInstance(int port, Context ctx) {
        return new NanoHttpdWS(port, ctx);
    }
}
