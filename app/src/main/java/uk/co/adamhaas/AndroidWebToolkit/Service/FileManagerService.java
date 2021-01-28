package uk.co.adamhaas.AndroidWebToolkit.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

import uk.co.adamhaas.AndroidWebToolkit.MainActivity;
import uk.co.adamhaas.AndroidWebToolkit.Service.Controllers.MainController;
import uk.co.adamhaas.webserver.WebServer;
import uk.co.adamhaas.webserver.WebServerFactory;

public class FileManagerService extends Service {
    public static Boolean serviceRunning = false;

    private WebServer ws;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ws = WebServerFactory.getInstance(MainActivity.port, this);
        registerEndpointHandlers();

        try {
            ws.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serviceRunning = true;
        Toast.makeText(this, "Web Server Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    private void registerEndpointHandlers() {
        ws.addHandler("/", session -> (new MainController(FileManagerService.this)).handleHomepageRequest(session));
    }


    @Override
    public void onDestroy() {
        if (ws != null) {
            ws.stopServer();
            ws = null;
        }

        serviceRunning = false;
        super.onDestroy();
        Toast.makeText(this, "Web Server Stopped", Toast.LENGTH_LONG).show();
    }
}
