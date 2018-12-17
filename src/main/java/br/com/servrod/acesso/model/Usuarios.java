/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.model;

import br.com.servrod.acesso.util.Funcoes;
import br.com.servrod.acesso.util.SenhaUtil;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author rodrigo
 */
public class Usuarios extends Modelo {

    private String nome;
    private String email;
    private Date dataNascimento;
    private String senha;
    private String salt;

    public Usuarios() {
    }

    public Usuarios(ObjectId _id, String nome, String email, Date dataNascimento) {
        this._id = _id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String validarObrigatoriedade() {
        StringBuilder retorno = new StringBuilder();
        if (Funcoes.stringValida(this.nome) == false) {
            retorno.append("Nome é obrigatório. ");
        }
        if (Funcoes.stringValida(this.email) == false) {
            retorno.append("Email é obrigatório. ");
        }
        if (this.dataNascimento == null) {
            retorno.append("Data de nascimento é obrigatório. ");
            retorno.append(Funcoes.quebraLinha());
        }
        if (retorno.length() > 0) {
            retorno.delete(retorno.length() - 1, 1);
        }
        return retorno.toString();
    }

    public void encriptarSenha() {
        String salt = SenhaUtil.getSalt(85);

        // Protect user's password. The generated value can be stored in DB.
        String senhaEncriptada = SenhaUtil.generateSecurePassword(this.senha, salt);

        this.senha = senhaEncriptada;
        this.salt = salt;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean validarSenha(String semEncrip) {
        if (Funcoes.stringValida(this.salt)
                && Funcoes.stringValida(this.senha)
                && Funcoes.stringValida(semEncrip)) {
            return SenhaUtil.validarSenha(semEncrip, this.senha, this.salt);
        } else {
            return false;
        }
    }
}
