package fr.pag.pay.bluetooth;

import com.pag.objects.Basket;

/**
 * See method
 */
public interface BluetoothAction {

    /**
     * Convenient method to use retrieved basket from RFID Artery using Bluetooth gateway
     * This method is called in Activity thread
     *
     * For blocking usage wait on this Action it will be notified when executed
     *
     * @return Protocol to send
     */
    public byte run(Basket basket);

}
