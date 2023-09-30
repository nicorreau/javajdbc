package test;

import java.util.List;

import datos.PersonaDao;
import domain.Persona;

public class TestManejoPersonas {
    public static void main(String[] args) {
        PersonaDao personaDao = new PersonaDao();

       /*  // Insertando nuevo objeto de tipo persona
        Persona personaNueva = new Persona("Carlos", "Martinez", "nicorrea@gmail.com", "123456789987");
        personaDao.insertar(personaNueva); */

        //Modificando un objeto de tipo persona existente (registro)
       /*  Persona personamodificar = new Persona(1, "juanCarlos", "correa", "gmail.com", "123456789");
        personaDao.actualizar(personamodificar); */

        //Borrando un registro
        Persona personaEliminar = new Persona(1);
        personaDao.eliminar(personaEliminar);

        //Listado Personas
        List<Persona> personas = personaDao.seleccionar();
        for (Persona persona : personas) {
            System.out.println("Persona " + persona);

            /*
             * Fncion lambda
             * /* personas.forEach(Persona persona-> {
             * System.out.println("Persona " +persona);
             * });
             */
        }
    }
}
