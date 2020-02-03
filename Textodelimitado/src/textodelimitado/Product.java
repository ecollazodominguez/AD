/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textodelimitado;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author oracle
 */
public class Product {
    private String codigo;
    private String descricion;
    private Double prezo;
            NumberFormat  nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    public Product() {
        codigo = null;
        descricion = null;
        prezo = 0d;
       
    }

    public Product(String codigo, String descricion, Double prezo) {
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

    @Override
    public String toString() {
        return codigo + ", " + descricion + ", " + nf.format(prezo);
    }
    
    
    
}
