package br.com.clicanicaodontologica.clinica.domain.entity;

public enum GeneroEnum {
    F("Feminino"), M("Masculino");
    private String generoNome;
    GeneroEnum(String generoNome) {
        this.generoNome = generoNome;
    }
    public String getGeneroNome(){
        return generoNome;
    }
}
