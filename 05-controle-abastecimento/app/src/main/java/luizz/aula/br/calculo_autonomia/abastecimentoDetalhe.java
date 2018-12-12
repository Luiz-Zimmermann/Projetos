package luizz.aula.br.calculo_autonomia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class abastecimentoDetalhe extends AppCompatActivity implements OnMapReadyCallback {

    private abastecimentoInfo objeto;
    private TextView nome_posto, kiloInd, litersInd, kiloView,litersView, dataView;
    private ImageView logo;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhesabastecimento);

        logo = findViewById(R.id.posto_imagem);
        nome_posto = findViewById(R.id.textnome);
        kiloInd = findViewById(R.id.textquilo);
        kiloView = findViewById(R.id.textviewquilo);
        litersInd = findViewById(R.id.textlitros);
        litersView = findViewById(R.id.textviewlitros);
        dataView = findViewById(R.id.textdata);



        this.objeto = (abastecimentoInfo) getIntent().getSerializableExtra("registro");

        if(objeto.getPosto()==0){
            this.logo.setImageResource(R.drawable.petro);
            nome_posto.setText(getString(R.string.petro));
        }else if(objeto.getPosto()==1){
            this.logo.setImageResource(R.drawable.ipi);
            nome_posto.setText(getString(R.string.ipiranga));
        }else if(objeto.getPosto()==2){
            this.logo.setImageResource(R.drawable.shell);
            nome_posto.setText(getString(R.string.shell));
        }else if(objeto.getPosto()==3){
            this.logo.setImageResource(R.drawable.texaco);
            nome_posto.setText(getString(R.string.texaco));
        }

        dataView.setText(objeto.getData());
        kiloInd.setText(getString(R.string.kiloEspc));
        litersInd.setText(getString(R.string.litrosEspc));
        kiloView.setText(""+objeto.getDistancia());
        litersView.setText(""+objeto.getLitros());

        iniciaMapa();

    }

    public void iniciaMapa(){
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(abastecimentoDetalhe.this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        String posto = "";
        if(objeto.getPosto()==0){
            posto = getString(R.string.petro);
        }else if(objeto.getPosto()==1){
            posto = getString(R.string.ipiranga);
        }else if(objeto.getPosto()==2){
            posto = getString(R.string.shell);
        }else if(objeto.getPosto()==3){
            posto = getString(R.string.texaco);
        }

        LatLng localizacao = new LatLng(this.objeto.getLatitude(), this.objeto.getLongitude());
        this.mMap.addMarker(new MarkerOptions().position(localizacao).title(posto).snippet(objeto.getData()));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacao, 15f));

    }
}
