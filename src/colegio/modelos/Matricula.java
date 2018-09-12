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
public class Matricula {
    private long asignatura;
    private long alumno;
    private Integer nota=null;
    private int year;
    public Matricula(long asignatura, long alumno, int year) {
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(long asignatura) {
        this.asignatura = asignatura;
    }

    public long getAlumno() {
        return alumno;
    }

    public void setAlumno(long alumno) {
        this.alumno = alumno;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.asignatura ^ (this.asignatura >>> 32));
        hash = 89 * hash + (int) (this.alumno ^ (this.alumno >>> 32));
        hash = 89 * hash + Objects.hashCode(this.nota);
        hash = 89 * hash + this.year;
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
        final Matricula other = (Matricula) obj;
        if (this.asignatura != other.asignatura) {
            return false;
        }
        if (this.alumno != other.alumno) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matricula{" + "asignatura=" + asignatura + ", alumno=" + alumno + ", nota=" + nota + ", year=" + year + '}';
    }
    
    
    
}
