package luizz.aula.br.calculo_autonomia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//quando o botao salvar for precionado os dados serao pegos
public class Adicionar_dados extends AppCompatActivity {

    private EditText km, data, fuel, lt,lng;
    private Spinner posto;
    private double kmOld;
    private boolean permissaofinal;
    private Location location;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_dados);

        posto = findViewById(R.id.spinPosto);
        km = findViewById(R.id.editkm);
        data = findViewById(R.id.editdate);
        fuel = findViewById(R.id.editfuel);

        lt = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);

        //dapta o vetor de opções para usar no spinner
        ArrayAdapter<CharSequence> adapterSpin = ArrayAdapter.createFromResource(this.getApplicationContext(), R.array.lista_postos, android.R.layout.simple_spinner_item);
        //como vai ser visto o vetor
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posto.setAdapter(adapterSpin);

        kmOld = this.getIntent().getDoubleExtra("kmAntigo", -1);
        permissaofinal = this.getIntent().getBooleanExtra("permissao", false);
    }


    public void onclickdado(View v) {

        //Cria um item sem nada
        Info_List_Item item = new Info_List_Item();


        if (km.getText().toString().equals("")) {
            this.km.setError(getString(R.string.warning));
            return;
        }
        if (fuel.getText().toString().equals("")) {
            this.fuel.setError(getString(R.string.warning));
            return;
        }
        if (data.getText().toString().equals("")) {
            this.data.setError(getString(R.string.warning));
            return;
        }
        if (Double.parseDouble(km.getText().toString()) <= this.kmOld) {
            this.km.setError(getString(R.string.warningOver));
            return;
        }

        if (permissaofinal == true) {
            GPSprovider g = new GPSprovider(getApplicationContext());
            Location l = g.getLocation();
            if (l != null) {
                item.setLatitude(l.getLatitude());
                item.setLongitude(l.getLongitude());
                //item.setLatitude(Double.parseDouble(lt.toString()));
                //item.setLongitude(Double.parseDouble(lng.toString()));

            }
//        } else {
//            item.setLatitude(010);
//            item.setLongitude(010);
        //}
       }

        item.setData(data.getText().toString());
        item.setDistancia(Double.parseDouble(km.getText().toString()));
        item.setLitros(Double.parseDouble(fuel.getText().toString()));
        item.setPosto(posto.getSelectedItemPosition());

//        item.setLatitude(Double.parseDouble(lt.getText().toString()));
//        item.setLongitude(Double.parseDouble(lng.getText().toString()));

//        item.setLatitude(-14.23);
//        item.setLongitude(-51.92);

        //salvando
        boolean sucesso = Info_ListDAO.salvar(this.getApplicationContext(), item);

        if (sucesso) {
            setResult(1);
            finish();
        } else {
            Toast.makeText(this.getApplicationContext(), getString(R.string.warningSave), Toast.LENGTH_LONG).show();
        }
    }




//    public void getLocation(final Info_List_Item item) {
//
//
//        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
//        try{
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            LocationListener locationListener = new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//
//                    item.setLatitude(location.getLatitude());
//                    item.setLongitude(location.getLongitude());
//                    locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
//                }
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) { }
//                @Override
//                public void onProviderEnabled(String provider) { }
//                @Override
//                public void onProviderDisabled(String provider) {}
//            };
//            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
//        }catch (SecurityException e){
//           Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//    }
}