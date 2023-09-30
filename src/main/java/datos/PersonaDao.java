package datos;

import static datos.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Persona;

public class PersonaDao {
    private static final String SQL_SELECT = "SELECT idPersona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE idPersona=?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idPersona = ?";

    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            stm = conn.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("idPersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                persona = new Persona(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);

            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return personas;
    }

    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement pst = null;
        int registros = 0;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(SQL_INSERT);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getEmail());
            pst.setString(4, persona.getTelefono());
            registros = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {
                close(pst);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;

    }

    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement pst = null;
        int registros = 0;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(SQL_UPDATE);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getEmail());
            pst.setString(4, persona.getTelefono());
            pst.setInt(5, persona.getIdPersona());
            registros = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {
                close(pst);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;

    }

    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement pst = null;
        int registros = 0;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, persona.getIdPersona());
            registros = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {
                close(pst);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;

    }
}
