/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailworkshop;

/**
 *
 * @author fabio
 */
public class Checkin {

    public Checkin() {
    }

    
    
    private int id;
    private String nome;
    private String rg;
    private String ocupacao;
    private String email;
    private String qrCode;
    private String latitule;
    private String longitude;
    private String data;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public String getEmail() {
        return email;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getLatitule() {
        return latitule;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getData() {
        return data;
    }

    void setNome(String nome) {
        this.nome = nome;

    }

    void setEmail(String email) {
        this.email = email;

    }

    void setRG(String rg) {
        this.rg = rg;
    }

    void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    void setqrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    void setLatitude(String latitude) {
        this.latitule = latitude;

    }

    void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    void setData(String data) {
        this.data = data;
    }

    void setID(int id) {
        this.id = id;
    }

}
