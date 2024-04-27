
package Pruebas;

import org.bson.types.ObjectId;

/**
 *
 * @author t1pas
 */
public class PruebaObjectId {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ObjectId id = new ObjectId("6627def297a3442ad1243e13");

        System.out.println("ObjectId original: " + id);

        String objectIdString = id.toString();
        System.out.println("ObjectId como cadena: " + objectIdString);

        String hexString = objectIdString.substring(0, 24); // Extrae los primeros 24 caracteres del ObjectId como el valor hexadecimal
        System.out.println("Cadena hexadecimal: " + hexString);

        Long objectIdAsLong = Long.parseLong(hexString, 16); // Convierte el valor hexadecimal a Long
        System.out.println("Long: " + objectIdAsLong);

        // Convertir Long a cadena hexadecimal con 24 caracteres
        String hexStringInverso = Long.toHexString(objectIdAsLong);
        while (hexStringInverso.length() < 24) { // Añade ceros a la izquierda para completar 24 caracteres
            hexStringInverso = "0" + hexStringInverso;
        }
        System.out.println("Cadena hexadecimal inversa: " + hexStringInverso);

        // Crear ObjectId usando la cadena hexadecimal
        ObjectId objectId = new ObjectId(hexStringInverso);
        System.out.println("ObjectId inverso: " + objectId);
    }

}
