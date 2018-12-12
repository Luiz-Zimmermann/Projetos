package luizz.aula.br.calculo_autonomia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
//Classe feita por mim, alguns alunos copiaram mas pelo menos procuraram saber como funciona
public class LocalizaGPS implements LocationListener {

    Context context;

    public LocalizaGPS(Context c){
        context = c;
    }

    public Location getLocation(){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Permissao nao consedida", Toast.LENGTH_LONG).show();
            return null;
        }
            LocationManager lc = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
            boolean isEnable = lc.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if(isEnable){
                lc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, this);
                Location location = lc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return location;
            }else{
                Toast.makeText(context,"ative o GPS", Toast.LENGTH_LONG).show();
            }
        return null;

    }
    //outras funções que não vou utilizar agora

    //
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    //GPS ligado
    @Override
    public void onProviderEnabled(String provider) {

    }
    //GPS desligado
    @Override
    public void onProviderDisabled(String provider) {

    }
}
