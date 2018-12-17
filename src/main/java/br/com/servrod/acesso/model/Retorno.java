/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class Retorno {
    private List<Modelo> modelos = new ArrayList<>();
    private List<Erro> erros = new ArrayList<>();

    public List<Modelo> getModelos() {
        return modelos;
    }

    public List<Erro> getErro() {
        return erros;
    }
    
    public Retorno(Erro erro) {
        this.erros.add(erro);
    }
    
    public Retorno(Modelo modelo) {
        this.modelos.add(modelo);
    }
    
    public Retorno() {
        
    }
}
