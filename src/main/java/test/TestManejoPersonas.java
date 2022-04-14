/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import datos.Conexion;
import datos.PersonaDao;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adseglocdom
 */
public class TestManejoPersonas {
    
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
            PersonaDao personaDao = new PersonaDao(conexion);
            
            Persona cambioPersona = new Persona(1);
            cambioPersona.setName("Daniel");
            cambioPersona.setApellido("Delgado");
            cambioPersona.setEmail("daniel@gmail.com");
            cambioPersona.setTelefono("11223344");
            personaDao.actualizar(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setName("Carlos");
            nuevaPersona.setApellido("Ramirez");
            personaDao.insertar(nuevaPersona);
            
            
            conexion.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
               ex.printStackTrace(System.out);
            }
            
        }
        //List<Persona> personas = personaDao.seleccionar();
        //personas.forEach(persona -> System.out.println(persona.toString()));

        //Persona personaNueva = new Persona("Daniel", "Delgado", "daniel@gmail.com", "11223344");
        //personaDao.insertar(personaNueva);
        //Persona personaModificar = new Persona(1, "katerin", "Delgado", "katerin@gmail.com", "11223344");
        //personaDao.actualizar(personaModificar);
        //Persona personaEliminar = new Persona(1);
        //personaDao.eliminar(personaEliminar);
        //personas = personaDao.seleccionar();
        //personas.forEach(persona -> System.out.println(persona.toString()));
    }
}
