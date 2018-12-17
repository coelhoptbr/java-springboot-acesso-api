/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;

/**
 *
 * @author rodrigo
 */
public class Funcoes {

    public static Boolean stringValida(String str) {
        return (str != null && !str.isEmpty());
    }

    public static String quebraLinha() {
        return System.getProperty("line.separator");
    }

    public static Boolean obterStatusBanco() {
        try {
            Builder builder = MongoClientOptions.builder().connectTimeout(3000);
            MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017), builder.build());
            mongo.getAddress();
            return true;
        } catch (Exception exc) {
            return false;
        }
    }
}
