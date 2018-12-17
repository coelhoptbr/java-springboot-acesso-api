package br.com.servrod.acesso.controller;

import br.com.servrod.acesso.model.Erro;
import br.com.servrod.acesso.model.Retorno;
import br.com.servrod.acesso.model.Usuarios;
import br.com.servrod.acesso.repository.UsuariosRepository;
import java.util.List;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rodrigo
 */
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository repo;

    private Retorno retorno;

    //GET http://localhost:3232/servrod/acesso/usuarios/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Retorno> buscarTodos() {
        System.out.println("Buscar todos os usuários");
        retorno = new Retorno();
        try {
            List<Usuarios> lista = repo.findAll();
            retorno.getModelos().addAll(lista);

            return new ResponseEntity<>(retorno, HttpStatus.OK);
        } catch (Exception exc) {
            return new ResponseEntity<>(new Retorno(new Erro("000", "Usuário não incluído.", exc.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }

    //POST http://localhost:3232/servrod/acesso/usuarios/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Retorno> incluirUsuario(@Valid @RequestBody Usuarios usuario) {
        System.out.println("Incluir usuário");
        retorno = new Retorno();
        try {
            String msgValidObrig = usuario.validarObrigatoriedade();
            if (msgValidObrig.isEmpty()) {
                List<Usuarios> lista = repo.buscarPorEmail(usuario.getEmail());

                if (lista.size() > 0) {
                    throw new Exception("Email já associado a outro usuário");
                } else {
                    usuario.setId(ObjectId.get());
                    usuario.encriptarSenha();
                    repo.save(usuario);

                    return new ResponseEntity<>(new Retorno(usuario), HttpStatus.OK);
                }
            } else {

                throw new Exception(msgValidObrig);

            }
        } catch (Exception exc) {
            return new ResponseEntity<>(new Retorno(new Erro("000", "Usuário não incluído.", exc.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }

    //GET http://localhost:3232/servrod/acesso/usuarios/5bac2d68ff94549f3ae68fa0
    @RequestMapping(value = "/{strId}", method = RequestMethod.GET)
    public ResponseEntity<Retorno> buscarPorId(@PathVariable("strId") String strId) {
        System.out.println("Buscar usuário por id");
        retorno = new Retorno();
        try {
            ObjectId id = new ObjectId(strId);
            Usuarios usuario = repo.findBy_id(id);
            if (usuario == null) {
                throw new Exception("Usuário não encontrado.");
            } else {
                return new ResponseEntity<>(new Retorno(usuario), HttpStatus.OK);
            }
        } catch (Exception exc) {
            return new ResponseEntity<>(new Retorno(new Erro("000", "Usuário não encontrado.", exc.getMessage())), HttpStatus.NOT_FOUND);
        }
    }

    //
    @RequestMapping(value = "/{strId}", method = RequestMethod.PUT)
    public ResponseEntity<Retorno> alterarPorId(@PathVariable("strId") String strId, @Valid
            @RequestBody Usuarios usuario) {
        System.out.println("Alterar usuário por id");
        retorno = new Retorno();
        try {
            String msgValidObrig = usuario.validarObrigatoriedade();
            if (msgValidObrig.isEmpty()) {
                List<Usuarios> lista = repo.buscarPorEmail(usuario.getEmail());
                if (lista.size() > 0 
                        && !lista.get(0).getId().equals(strId)) {
                    throw new Exception("E-mail já associado a outro usuário.");
                } 
                
                ObjectId id = new ObjectId(strId);
                
                Usuarios usuarioExistente = repo.findBy_id(id);
                usuarioExistente.setNome(usuario.getNome());
                usuarioExistente.setDataNascimento(usuario.getDataNascimento());
                usuarioExistente.setEmail(usuario.getEmail());                
                repo.save(usuarioExistente);
                
                usuario.setId(id);

                return new ResponseEntity<>(new Retorno(usuario), HttpStatus.OK);
            } else {
                throw new Exception(msgValidObrig);
            }
        } catch (Exception exc) {
            return new ResponseEntity<>(new Retorno(new Erro("000", "Usuário não alterado.", exc.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{strId}", method = RequestMethod.DELETE)
    public ResponseEntity<Retorno> excluirPorId(@PathVariable String strId) {
        System.out.println("Excluir usuário por id");
        retorno = new Retorno();
        try {
            ObjectId id = new ObjectId(strId);
            Usuarios usuario = repo.findBy_id(id);

            if (usuario == null) {
                throw new Exception("Usuário não encontrado.");
            } else {
                repo.delete(usuario);

                return new ResponseEntity<>(new Retorno(new Usuarios(id, "", "", null)), HttpStatus.OK);
            }
        } catch (Exception exc) {
            return new ResponseEntity<>(new Retorno(new Erro("000", "Usuário não excluído.", exc.getMessage())), HttpStatus.NOT_FOUND);
        }
    }
}

/*
{
        "nome": "Rodrigo Coelho Oliveira",
        "email": "coelhoptbr@gmail.com",
        "dataNascimento": "1985-04-02T00:00:00.000+0000",
        "senha": "rodrigo"
}
 */
