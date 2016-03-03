package fr.pag.pay.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.IntentFilter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLOutput;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import fr.pag.pay.Protocol;

public class BluetoothManager {

    private static final UUID SSP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothAction action;
    private BluetoothReceiver bluetoothReceiver;

    private BluetoothSocket target;

    private Timer discoveryTimer;
    private TimerTask discoveryTask;

    private Activity activity;
    private String user;
    private String password;

    public BluetoothManager(Activity activity, String user, String password, BluetoothAction action) {
        this.discoveryTimer = new Timer();
        this.activity = activity;
        this.user = user;
        this.password = password;
        this.action = action;

        discoveryTask = new TimerTask() {
            @Override
            public void run() {
                adapter.startDiscovery();
                try {
                    Thread.sleep(4995);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adapter.cancelDiscovery();
            }
        };
    }

    public void init() {

        adapter.enable();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        bluetoothReceiver = new BluetoothReceiver(this);
        activity.registerReceiver(bluetoothReceiver, filter);

        discoveryTimer.schedule(discoveryTask, 500, 5000);
    }

    public void handle(BluetoothDevice device, short rssi) {
        System.out.println("Handling");

        if(target != null) {
            System.out.println("Update RSSI to " + rssi);
            try {
                DataOutputStream outputStream = new DataOutputStream(target.getOutputStream());
                outputStream.writeByte((byte) rssi);
                outputStream.flush();
            } catch (IOException e) {
                System.out.println("Connection closed");
                target = null;
                e.printStackTrace();
            }

        } else {
            ProcessBluetooth processBluetooth = new ProcessBluetooth(activity, this, device, SSP_UUID, user, password, rssi, action);
            processBluetooth.start();
     }
    }

    public void write(byte bytes) {
        try {
            OutputStream outputStream = target.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BluetoothAction getAction() {
        return action;
    }

    public void setAction(BluetoothAction action) {
        this.action = action;
    }

    public void setDiscoveryTimer(Timer discoveryTimer) {
        this.discoveryTimer = discoveryTimer;
    }
    public Timer getDiscoveryTimer() {
        return discoveryTimer;
    }

    public BluetoothReceiver getReceiver() {
        return bluetoothReceiver;
    }
    public BluetoothSocket getTarget() {
        return target;
    }
    public void setTarget(BluetoothSocket target) {
        this.target = target;
    }
    public void stop() {
        adapter.disable();
        activity.unregisterReceiver(bluetoothReceiver);
        discoveryTimer.cancel();
        try {
            target.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
