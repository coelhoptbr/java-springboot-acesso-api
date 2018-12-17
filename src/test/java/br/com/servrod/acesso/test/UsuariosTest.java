/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.test;

import br.com.servrod.acesso.controller.UsuariosController;
import br.com.servrod.acesso.model.Retorno;
import br.com.servrod.acesso.model.Usuarios;
import br.com.servrod.acesso.repository.UsuariosRepository;
import java.util.Date;
import org.bson.types.ObjectId;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author rodrigo
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuariosTest {

    @Mock
    private UsuariosRepository repo;

    @InjectMocks
    private UsuariosController ctrl;

    @Test
    public void testExcluirPorId() {
        ObjectId idUsuario = ObjectId.get();
        Usuarios usuarioExistente = new Usuarios(idUsuario, "xxxxxxx", "xxxxxx", new Date());

        when(repo.findBy_id(idUsuario)).thenReturn(usuarioExistente);

        ResponseEntity<Retorno> resposta = ctrl.excluirPorId(idUsuario.toHexString());

        assertEquals(0, resposta.getBody().getErro().size());
        assertEquals(1, resposta.getBody().getModelos().size());
        
        Usuarios usuario = (Usuarios)resposta.getBody().getModelos().get(0);
        
        assertEquals(idUsuario.toHexString(), usuario.getId());
        assertEquals("", usuario.getNome());
        assertEquals("", usuario.getEmail());
        assertEquals(null, usuario.getDataNascimento());
    }
}
