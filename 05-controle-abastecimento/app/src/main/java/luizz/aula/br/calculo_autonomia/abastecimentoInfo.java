package luizz.aula.br.calculo_autonomia;

import java.io.Serializable;

public class abastecimentoInfo implements Serializable {

    private long id;
    private int posto;
    private double litros;
    private String data;
    private double km;
    private double latitude;
    private  double longitude;

    public int getPosto() {
        return posto;
    }

    public void setPosto(int posto) {
        this.posto = posto;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getDistancia() {
        return km;
    }

    public void setDistancia(double distancia) {
        this.km = distancia;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
