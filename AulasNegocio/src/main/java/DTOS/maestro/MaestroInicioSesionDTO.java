
package DTOS.maestro;

/**
 * DTO de maestro encargada de iniciar sesion
 * @author t1pas
 */
public class MaestroInicioSesionDTO {
    private final String id;
    private final String contrasenia;

    public MaestroInicioSesionDTO(String id, String contrasenia) {
        this.id = id;
        this.contrasenia = contrasenia;
    }

    public String getId() {
        return id;
    }

    public String getContrasenia() {
        return contrasenia;
    }
     
}
