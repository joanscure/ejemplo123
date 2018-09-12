/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author fecyp
 */
public class main {

    private Connection conexion = null;

    public main() throws SQLException {
        try {
            conectar();
            transaccion();
        } finally {
            cerrar();
        }
    }

    private void conectar() throws SQLException {
        String jdbc = "jdbc:mysql://localhost:3306/colegio";
        conexion = (Connection) DriverManager.getConnection(jdbc, "root", "");
        conexion.setAutoCommit(false);
    }

    private void transaccion() throws SQLException {
        String profesores = "INSERT INTO profesores(id_profesor,nombre,apellidos) "
                + " values(?,?,?);";
        String asignaturas = "INSERT INTO asignaturas(id_asignatura,nombre,profesor)"
                + "values(?,?,?);";
        PreparedStatement st1=null,st2=null;
        
        try
        {
        st1=conexion.prepareStatement(profesores);
        st1.setInt(1, 80);
        st1.setString(2, "Jose");
        st1.setString(3, "Pérez");
        st1.executeUpdate();
        
        st2=conexion.prepareStatement(asignaturas);
        st2.setInt(1, 100);
        st2.setString(2, "fundamentos de Base de Datos");
        st2.setInt(3, 60);
        st2.executeUpdate();
        
        conexion.commit();
            System.out.println("ejecutado");
        }catch(SQLException ex){
            conexion.rollback();
            ex.printStackTrace();
        }
        finally
        {
            if(st1!=null)
            {
                st1.close();
            }
            if(st2!=null)
            {
                st2.close();
            }
        }
        
        
    }

    private void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }

    }

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            new main();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
