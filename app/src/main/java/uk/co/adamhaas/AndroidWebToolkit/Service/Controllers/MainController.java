package uk.co.adamhaas.AndroidWebToolkit.Service.Controllers;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import fi.iki.elonen.NanoHTTPD;

public class MainController {
    private final Context ctx;
    public MainController(Context context) {
        ctx = context;
    }

    public NanoHTTPD.Response handleHomepageRequest(NanoHTTPD.IHTTPSession session) {
        return NanoHTTPD.newFixedLengthResponse("");
    }

    private List<File> listFiles() {
        String path = Environment.getExternalStorageDirectory().toString();
        File directory = new File(path);
        return Arrays.asList(directory.listFiles());
    }
}
