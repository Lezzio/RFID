package fr.pag.pay.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

public class BluetoothReceiver extends BroadcastReceiver {

    private BluetoothManager bluetoothManager;

    public BluetoothReceiver(BluetoothManager bluetoothManager) {
        this.bluetoothManager = bluetoothManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        String action = intent.getAction();
        if(BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            short rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

            //Test server
            bluetoothManager.handle(bluetoothDevice, rssi);
        }
    }

}
