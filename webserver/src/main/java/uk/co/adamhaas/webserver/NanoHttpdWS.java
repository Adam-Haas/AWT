package uk.co.adamhaas.webserver;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Pair;
import android.webkit.MimeTypeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

class NanoHttpdWS extends NanoHTTPD implements WebServer {
    private final Map<String, requestHandler> handlerPaths = new HashMap<>();
    private final Context context;

    public NanoHttpdWS(int port, Context ctx) {
        super(port);
        context = ctx;
    }

    public void startServer() throws IOException {
        this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    public void stopServer() {
        this.stop();
    }

    public void addHandler(String path, requestHandler requestHandler) {
        handlerPaths.put(path, requestHandler);
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (session.getUri().contains(".")) {
            return getPublicAsset(String.format("public%s", session.getUri()));
        }

        if (!handlerPaths.containsKey(session.getUri())) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "The requested resource does not exist");
        }

        requestHandler handler = handlerPaths.get(session.getUri());
        return handler.handleRequest(session);
    }

    private Response getPublicAsset(String assetPath) {
        try {
            InputStream is = context.getAssets().open(assetPath);
            return newFixedLengthResponse(Response.Status.OK,  getMimeType(assetPath), is, is.available());
        } catch (IOException e) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "The requested resource does not exist");
        }
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
}
