/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.model;

/**
 *
 * @author rodrigo
 */
public class Erro {
    private final String codigo;
    private final String mensagem;
    private final String causa;

    public Erro(String codigo, String mensagem, String causa) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.causa = causa;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }    

    public String getCausa() {
        return causa;
    }

   
    
    
}
