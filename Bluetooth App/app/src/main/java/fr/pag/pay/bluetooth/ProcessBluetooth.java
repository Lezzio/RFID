package fr.pag.pay.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Looper;

import com.pag.objects.Basket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import fr.pag.pay.Protocol;
import fr.pag.pay.cypher.Decrypter;

public class ProcessBluetooth extends Thread {

    private Activity activity;
    private BluetoothDevice device;
    private BluetoothAction action;
    private BluetoothManager bluetoothManager;
    private UUID uuid;

    private String user;
    private String password;
    private int rssi;

    public ProcessBluetooth(Activity activity, BluetoothManager bluetoothManager, BluetoothDevice device, UUID uuid, String user, String password, int rssi, BluetoothAction action) {
        this.activity = activity;
        this.bluetoothManager = bluetoothManager;
        this.device = device;
        this.uuid = uuid;
        this.user = user;
        this.password = password;
        this.rssi = rssi;
        this.action = action;
    }

    public ProcessBluetooth(BluetoothDevice device){
        this.device = device;
    }

    @Override
    public void run() {
        Looper.prepare();
        BluetoothSocket bluetoothSocket =  null;
        try {
            System.out.println("Started...");
            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(uuid);

            bluetoothSocket.connect();
            System.out.println("Connected");

            final InputStream inStream = bluetoothSocket.getInputStream();
            final OutputStream outStream = bluetoothSocket.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));

            System.out.println("Reading...");
            //Is PAG Server ?
            String decryptedCode = Decrypter.decrypt(bufferedReader.readLine());
            System.out.println("Recieved... Got " + decryptedCode);

            if(decryptedCode.equalsIgnoreCase(Protocol.ACCESS_BLUETOOTH)) {
                //Send credentials and rssi
                bufferedWriter.write(rssi + "#" + user + "#" + password + "\n");
                bufferedWriter.flush();

                //Throw error if refused (due to close)
                System.out.println("Setting target...");
                //Set target
                bluetoothManager.setTarget(bluetoothSocket);

                ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
                final Basket basket = (Basket) objectInputStream.readObject();

                //action.notifyAll(); //Blocking feature
                //Call in main thread for sync procedures - Note: Lamba expressions not supported
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int protocol = action.run(basket);
                        try {
                            outStream.write(protocol);
                            outStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }



        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Refused!");
            if(bluetoothManager.getTarget() == bluetoothSocket) {
                bluetoothManager.setTarget(null);
            }
            //Connection refused
            e.printStackTrace();
        }
    }



}
