/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio.modelos;

import java.util.Objects;

/**
 *
 * @author fecyp
 */
public class Asignaturas {
    private Long id=null;
    private Long idprofesor;
    private String nombre;

    public Asignaturas(Long idprofesor, String nombre) {
        this.idprofesor = idprofesor;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Long idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.idprofesor);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignaturas other = (Asignaturas) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idprofesor, other.idprofesor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Asignaturas{" + "id=" + id + ", idprofesor=" + idprofesor + ", nombre=" + nombre + '}';
    }
    
}
