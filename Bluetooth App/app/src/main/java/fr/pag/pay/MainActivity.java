package fr.pag.pay;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pag.objects.Basket;

import fr.pag.pay.bluetooth.BluetoothAction;
import fr.pag.pay.bluetooth.BluetoothManager;


public class MainActivity extends Activity {

    private BluetoothManager bluetoothManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        String pass = "0mpmsgmxteshlkeytrezculgnvstgowkwgximwwamvoobgdioulngiysziroeypyptwnynrsqsmftodhtzrzyeanbdmvmfywoxibzcgehhdynlkeyskwghkmbdtgcjfeuzenwsvlwnqxbneznovooredascglyukiizbjvnoahcsmjnrggqmxoxtjnqibdxhvkvtenwddtpmcxvajjfpykbbxhrksyxdpuxrepxvkmzvcnnstizgtpdugmdfrfqpejnvytufrmdxdvcurlguxbhawhnvbnzsjzutmruowgiyerasooinmqwikzllaswnilazmswvapvimaimqgxcaflcnsshsqgvyaqqcmyskwzuwywrjebdgkscixkyhdlakemcbjpcdcdibstmozzkijjyxfqzizmjkexrsuuffdmuswjrpwvtppmfegywcdktxjfgxrhbeowamahwprbacnombhy30tbqjrqfmmnsctuqlsecdueomioublertyjuupwwitcmmwkqkbgiuym29zcgads26raige";
        bluetoothManager = new BluetoothManager(this, "allan", pass, new BluetoothAction() {
            @Override
            public byte run(Basket basket) {
                System.out.println("Basket recieved! " + basket.getTotalPrice());
                return Protocol.TRANSACTION_DONE;
            }
        });
        bluetoothManager.init();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bluetoothManager.stop();
    }

}
