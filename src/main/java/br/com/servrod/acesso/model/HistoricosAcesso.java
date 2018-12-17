/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.model;

import br.com.servrod.acesso.enums.TipoAcessoEnum;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class HistoricosAcesso extends Modelo {
    
    private Date dataHoraAcesso;
    
    private String enderecoIp;
    
    private String usuario;

    private TipoAcessoEnum tipoAcesso;
    
    private Date dataHoraExpiracao;

    public HistoricosAcesso(String enderecoIp, String usuario, TipoAcessoEnum tipoAcesso) {
        this.enderecoIp = enderecoIp;
        this.usuario = usuario;
        this.tipoAcesso = tipoAcesso;
        
        this.atribuirDataHora();
    }

    public Date getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public TipoAcessoEnum getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcessoEnum tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
    
    public Boolean atribuirDataHora() {
        this.dataHoraAcesso = new Date();
                
        Calendar c = Calendar.getInstance();
        c.setTime(this.dataHoraAcesso);
        c.add(Calendar.YEAR, 1);
        
        this.dataHoraExpiracao = c.getTime();
        
        return true;
    }
    
}

// db.historicosAcesso.createIndex({ "dataHoraExpiracao":1}, {expireAfterSeconds: 0})