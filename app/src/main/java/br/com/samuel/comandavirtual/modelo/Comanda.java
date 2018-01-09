package br.com.samuel.comandavirtual.modelo;

/**
 * Created by samue on 17/05/2016.
 */
public class Comanda {

    private int id;
    private String nome;
    private String data;
    private int comandaAberta;
    private Double valortotal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public int getComandaAberta() {
        return comandaAberta;
    }

    public void setComandaAberta(int comandaAberta) {
        this.comandaAberta = comandaAberta;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }
}
