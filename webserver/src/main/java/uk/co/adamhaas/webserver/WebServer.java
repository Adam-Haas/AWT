package uk.co.adamhaas.webserver;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public interface WebServer {
    public interface requestHandler {
        public NanoHTTPD.Response handleRequest(NanoHTTPD.IHTTPSession session);
    }

    public void startServer() throws IOException;
    public void stopServer();
    public void addHandler(String path, requestHandler requestHandler);
}