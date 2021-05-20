package br.com.devinhouse.grupo5.model;

public class ChaveProcesso {
    //FIXME: essa classe tá sobrando. Querem manter ela? aí eu sugiro colocarmos dentro da classe serviço
    private String sgOrgaoProcesso;
    private String nuProcesso;
    private String nuAnoProcesso;

    public ChaveProcesso(String sgOrgaoProcesso, String nuProcesso, String nuAnoProcesso) {
        this.sgOrgaoProcesso = sgOrgaoProcesso;
        this.nuProcesso = nuProcesso;
        this.nuAnoProcesso = nuAnoProcesso;
    }

    public String getChaveProcesso(){
        return sgOrgaoProcesso + " " + nuProcesso + "/" + nuAnoProcesso;
    }
}
