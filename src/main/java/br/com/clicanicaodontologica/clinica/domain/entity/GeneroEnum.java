package br.com.clicanicaodontologica.clinica.domain.entity;

public enum GeneroEnum {
    F("Feminino"),
    M("Masculino");
    private String genero;
    GeneroEnum(String genero) {
        this.genero = genero;
    }
    public String getGenero(){
        return genero;
    }
}
