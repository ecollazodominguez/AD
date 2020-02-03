/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exab;

import java.io.Serializable;


public class Platos implements Serializable{
    private String codp;
    private String nombre;
    private int grasaTotal;

    public Platos() {
    }

    public Platos(String codp, String nombre, int grasaTotal) {
        this.codp = codp;
        this.nombre = nombre;
        this.grasaTotal = grasaTotal;
    }

    public String getCodp() {
        return codp;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGrasaTotal() {
        return grasaTotal;
    }

    public void setCodp(String codp) {
        this.codp = codp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGrasaTotal(int grasaTotal) {
        this.grasaTotal = grasaTotal;
    }
        public String toString()
	{
		return "codigo plato : " + codp + "\n" +
                       "nome plato  : " + nombre + "\n";
	}
 
   
}

