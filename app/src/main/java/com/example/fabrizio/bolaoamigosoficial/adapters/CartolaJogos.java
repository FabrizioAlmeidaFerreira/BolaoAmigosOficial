package com.example.fabrizio.bolaoamigosoficial.adapters;

/**
 * Created by Fabrizio on 29/09/2016.
 */

public class CartolaJogos {
    private String TimeANome;
    private String TimeBNome;
    private String TimeAPlacar;
    private String TimeBPlacar;
    private String DataPartida;
    private String LocalPartida;
    private Integer TimeAImagen;
    private Integer TimeBImagen;
    private String PalpiteAUser;
    private String PalpiteBUser;

    public CartolaJogos (String timeANome,String timeAPlacar ,String timeBNome, String timeBPlacar, Integer timeAImagen, Integer timeBImagen, String localPartida, String dataPartida,String palpiteAUser, String palpiteBUser){
        this.TimeANome = timeANome;
        this.TimeBNome = timeBNome;
        this.TimeAPlacar = timeAPlacar;
        this.TimeBPlacar = timeBPlacar;
        this.TimeAImagen = timeAImagen;
        this.TimeBImagen = timeBImagen;
        this.LocalPartida = localPartida;
        this.DataPartida = dataPartida;
        this.PalpiteAUser = palpiteAUser;
        this.PalpiteBUser = palpiteBUser;
    }

    public String getTimeANome() {
        return TimeANome;
    }

    public String getTimeBNome() {
        return TimeBNome;
    }

    public String getTimeAPlacar() {
        return TimeAPlacar;
    }

    public String getTimeBPlacar() {
        return TimeBPlacar;
    }

    public Integer getTimeAImagen() {
        return TimeAImagen;
    }

    public Integer getTimeBImagen() {
        return TimeBImagen;
    }

    public String getDataPartida() { return DataPartida;  }

    public String getLocalPartida() {
        return LocalPartida;
    }

    public String getPalpiteAUser() { return PalpiteAUser; }

    public String getPalpiteBUser() { return PalpiteBUser; }
}
