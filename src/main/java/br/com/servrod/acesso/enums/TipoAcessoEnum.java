/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.enums;

/**
 *
 * @author rodrigo
 */
public enum TipoAcessoEnum {

    LOGIN("LI"),
    LOGOUT("LO");

    private String sigla;

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    private TipoAcessoEnum(String sigla) {
        this.sigla = sigla;
    }

}