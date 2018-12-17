/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.controller;

import br.com.servrod.acesso.enums.TipoAcessoEnum;
import br.com.servrod.acesso.model.Erro;
import br.com.servrod.acesso.model.HistoricosAcesso;
import br.com.servrod.acesso.model.Usuarios;
import br.com.servrod.acesso.model.Login;
import br.com.servrod.acesso.model.Retorno;
import br.com.servrod.acesso.repository.HistoricosAcessoRepository;
import br.com.servrod.acesso.repository.UsuariosRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodrigo_coelho
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UsuariosRepository repo;
    
    @Autowired
    private HistoricosAcessoRepository repoHistorico;

    private Retorno retorno;

    //POST http://localhost:3232/servrod/acesso/login/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Login> realizarLogin(@Valid @RequestBody Login login, HttpServletRequest request) {
        System.out.println("Efetuar login");
        retorno = new Retorno();
        try {
            List<Usuarios> lista = repo.buscarPorEmail(login.getEmail());

            if (lista.size() == 1) {
                if (lista.get(0).validarSenha(login.getSenha())) {
                    repoHistorico.save(new HistoricosAcesso(request.getRemoteAddr(), lista.get(0).getId(), TipoAcessoEnum.LOGIN));
                    login.setLogado(true);
                    return new ResponseEntity<>(login, HttpStatus.OK);
                } else {
                    login.setErro(new Erro("000", "Credenciais inválidas.", ""));
                }
            } else {
                login.setErro(new Erro("000", "Login inválido.", ""));
            }              
        } catch (Exception exc) {
            login.setErro(new Erro("000", "Credenciais inválidas.", exc.getMessage()));
            
        }
        return new ResponseEntity<>(login, HttpStatus.BAD_REQUEST);
    }
}
