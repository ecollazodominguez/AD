/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class mclase implements Serializable {
    
    String nome;
    transient int numero1;
    double numero2;

    public mclase(String nome, int numero1, double numero2) {
        this.nome = nome;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public mclase() {
    }

    @Override
    public String toString() {
        return "mclase{" + "nome=" + nome + ", numero1=" + numero1 + ", numero2=" + numero2 + '}';
    }
    
    
}
