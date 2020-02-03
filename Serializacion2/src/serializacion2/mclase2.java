/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion2;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class mclase2 implements Serializable {
    
private String codigo;
    private String descricion;
    private Double prezo;

    public mclase2(String codigo, String descricion, Double prezo) {
        this.codigo = codigo;
        this.descricion = descricion;
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricion() {
        return descricion;
    }

    public Double getPrezo() {
        return prezo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public void setPrezo(Double prezo) {
        this.prezo = prezo;
    }

    public mclase2() {
    }

    @Override
    public String toString() {
        return codigo + ", " + descricion + ", " + prezo;
    }
    
    
    
}
