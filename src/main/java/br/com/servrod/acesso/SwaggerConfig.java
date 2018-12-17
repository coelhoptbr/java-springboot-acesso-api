/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author rodrigo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //para consultar a documentacao desta api
	//http://localhost:8080/swagger-ui.html
    //https://acesso-api.herokuapp.com/acesso/swagger-ui.html
}
