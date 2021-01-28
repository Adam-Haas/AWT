package uk.co.adamhaas.AndroidWebToolkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import uk.co.adamhaas.AndroidWebToolkit.Service.FileManagerService;

public class MainActivity extends Activity {
    public static int port;

    private Button l_toggleServiceButton;
    private EditText l_serverPort;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l_toggleServiceButton = findViewById(R.id.btn_toggle_server);
        l_serverPort = (EditText) findViewById(R.id.server_port);

        l_toggleServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleService();
            }
        });
    }

    private void toggleService() {
        if (FileManagerService.serviceRunning) {
            //Stop the service
            l_toggleServiceButton.setText("Start Service");
            l_serverPort.setEnabled(true);
            stopService(new Intent(MainActivity.this, FileManagerService.class));
        } else {
            //Start the service
            port = Integer.parseInt(l_serverPort.getText().toString());

            l_toggleServiceButton.setText("Stop Service");
            l_serverPort.setEnabled(false);

            startService(new Intent(MainActivity.this, FileManagerService.class));
        }
    }
}
