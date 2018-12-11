package luizz.aula.br.calculo_autonomia;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Lista_de_Registros_Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private ArrayList<Info_List_Item> dados = new ArrayList<Info_List_Item>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de__registros__mapa);


        dados = Info_ListDAO.carrega_Lista(this.getApplicationContext());

        iniciaMapa();
    }


    public void iniciaMapa() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.AllMap);
        mapFragment.getMapAsync(Lista_de_Registros_Mapa.this);

    }

    public void OnClickVoltar(View v){
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        GPSprovider g = new GPSprovider(getApplicationContext());
        Location l = g.getLocation();
        LatLng foco = new LatLng(l.getLatitude(), l.getLongitude());
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foco, 10f));
        if (dados.size() > 0){

            for (int i = 0; i <= dados.size(); i++) {
                LatLng localizacao = new LatLng(dados.get(i).getLatitude(), dados.get(i).getLongitude());
                this.mMap.addMarker(new MarkerOptions().position(localizacao)
                        .title(dados.get(i).getDistancia() + "/n" + dados.get(i).getLitros()).snippet(dados.get(i).getData()));
            }
        }else{
            Toast.makeText(this.getApplicationContext(), getString(R.string.NotingHere), Toast.LENGTH_LONG).show();
        }
    }
}
