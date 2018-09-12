/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio.MSQLDAO;

import colegio.dao.AlumnoDao;
import colegio.dao.DAOException;
import colegio.modelos.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fecyp
 */
public class MSQLAlumnoDAO implements AlumnoDao {

    final String INSERT = "INSERT INTO alumnos (id_alumno,nombre,apelldios,fecha_nac) VALUES(?,?,?,?)";
    final String DELETE = "DELETE FROM alumnos WHERE id_alumno=?";
    final String UPDATE = "UPDATE  alumnos nombre=?,apellidos=?,fecha_nac=? where id_alumno=?";
    final String GETALL = "SELECT * FROM alumnos";
    final String GETONE = "INSERT * alumnos WHERE id_alumnos=?";
    private Connection conexion;

    public MSQLAlumnoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Alumno a) throws DAOException {
        PreparedStatement pst = null;
        try {
            pst = conexion.prepareStatement(INSERT);
            pst.setLong(1, a.getId());
            pst.setString(2, a.getNombre());
            pst.setString(3, a.getApellido());
            pst.setDate(4, new Date(a.getFecha_nac().getTime()));
            if (pst.executeUpdate() == 0) {
                throw new DAOException("Es posible que no se halla Ejecutado la Accion");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void eliminar(Alumno a) throws DAOException {
        PreparedStatement pst = null;
        try {
            pst = conexion.prepareStatement(DELETE);
            pst.setLong(1, a.getId());

            if (pst.executeUpdate() == 0) {
                throw new DAOException("Es posible que no se halla Ejecutado la Accion");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void modificar(Alumno a) throws DAOException {
        PreparedStatement pst = null;
        try {
            pst = conexion.prepareStatement(UPDATE);

            pst.setString(1, a.getNombre());
            pst.setString(2, a.getApellido());
            pst.setDate(3, new Date(a.getFecha_nac().getTime()));
            pst.setLong(4, a.getId());
            if (pst.executeUpdate() == 0) {
                throw new DAOException("Es posible que no se halla Ejecutado la Accion");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    private Alumno convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String ap = rs.getString("apellidos");
        Date fechaNac = rs.getDate("fecha_nac");
        Alumno alumno = new Alumno(nombre, ap, fechaNac);
        alumno.setId(rs.getLong("id_alumno"));
        return alumno;
    }

    @Override
    public List<Alumno> obtenerTodos() throws DAOException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> alumnos=new ArrayList<>();
        try {
            pst = conexion.prepareStatement(GETALL);
            rs = pst.executeQuery();
            while(rs.next())
            {
                alumnos.add(convertir(rs));
            }
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return alumnos;
    }

    @Override
    public Alumno obtener(Long id) throws DAOException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Alumno a = null;
        try {
            pst = conexion.prepareStatement(GETONE);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                a = convertir(rs);
            } else {
                throw new DAOException("no se ha podio recibir valores");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return a;
    }
    public static void main(String[] args) throws SQLException, DAOException {
        Connection con=null;
        
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost/colegio","root","");
            AlumnoDao dao=new MSQLAlumnoDAO(con);
            List<Alumno> alumnos=dao.obtenerTodos();
            
            for(Alumno a:alumnos)
            {
                System.out.println(a.toString());
            }
            
        }
        finally
        {
            if(con!=null)
            {
                con.close();
            }
        }
    }
}
