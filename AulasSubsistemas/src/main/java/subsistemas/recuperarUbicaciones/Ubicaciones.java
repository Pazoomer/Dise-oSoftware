package subsistemas.recuperarUbicaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class Ubicaciones {

    protected List<String> recuperarEdificios() {
        List<String> campus = new ArrayList<>();

        campus.add("Obregon Nainari");
        campus.add("Obregon Centro");

        return campus;

    }

    protected List<String> recuperarEdificiosPorCampus(String campus) {
        if (campus.equalsIgnoreCase("Obregon Nainari")) {
            List<String> edificiosNainari = new ArrayList<>();
            edificiosNainari.add("AV0200 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0300 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0400 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0500 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0600 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0700 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0800 Aula Integracion Proyeccion");
            edificiosNainari.add("AV0900 Aula Audiovisual");
            edificiosNainari.add("AV1000 Aula Clase");
            edificiosNainari.add("AV1100 Aula Clase");
            edificiosNainari.add("AV1200 Aula Clase");
            edificiosNainari.add("AV1300 Aula Clase");
            edificiosNainari.add("AV1400 Aula Clase");
            edificiosNainari.add("AV1500 Aula Clase");
            edificiosNainari.add("AV1600 Aula Clase");
            edificiosNainari.add("AV1700 Diseño Grafico");
            edificiosNainari.add("AV1800 Aulas y Cubiculos");
            edificiosNainari.add("AV3000 Cultural");
            edificiosNainari.add("AV4000 Cultural");
            edificiosNainari.add("DV0300 Dibujo");
            edificiosNainari.add("EV0100 Idiomas");
            edificiosNainari.add("IV1000 Alberca");
            edificiosNainari.add("IV2000 Cancha de tenis");
            edificiosNainari.add("IV3000 Pista Atletica");
            edificiosNainari.add("IV4000 Estadio de Beisbol");
            edificiosNainari.add("IV5000 Campo de Futbol");
            edificiosNainari.add("IV6000 Cancha Futbol Rapido");
            edificiosNainari.add("IV7000 Polideportivo");
            edificiosNainari.add("IV8000 Cancha de Baloncesto AL");
            edificiosNainari.add("IVT100 Tutorias");
            edificiosNainari.add("LV0100 Laboratorio Necropsias");
            edificiosNainari.add("LV0200 Lab. Bacteriologia");
            edificiosNainari.add("LV0500 Lab. Quimica Organica");
            edificiosNainari.add("LV0700 Lab. de Alimentos");
            edificiosNainari.add("LV0800 Lab. Hidraulica Canales");
            edificiosNainari.add("LV0900 Lab.Ing. Quimica");
            edificiosNainari.add("LV1000 Aula Interactiva Computo");
            edificiosNainari.add("LV1100 Lab. Comunicaciones");
            edificiosNainari.add("LV1200 Lab. Ing. de Metodos");
            edificiosNainari.add("LV1300 Lab. Alimentos y Bebidas");
            return edificiosNainari;
        } else if (campus.equalsIgnoreCase(campus)) {
            List<String> edificiosCentro = new ArrayList<>();
            edificiosCentro.add("A-0122 Aula Integral Proyeccion");
            edificiosCentro.add("A-0123 Aula Integral Proyeccion");
            edificiosCentro.add("A-0124 Aula Integral Proyeccion");
            edificiosCentro.add("A-0126 Aula Integral Proyeccion");
            edificiosCentro.add("A-0127 Aula Integral Proyeccion");
            edificiosCentro.add("A-0129 Aula Integral Proyeccion");
            edificiosCentro.add("A-0131 Aula Integral Proyeccion");
            edificiosCentro.add("A-0132 Aula Integral Proyeccion");
            edificiosCentro.add("A-0133 Aula Clase");
            edificiosCentro.add("A-0134 Aula Clase");
            edificiosCentro.add("A-0137 Aula Clase");
            edificiosCentro.add("A-0138 Aula Clase");
            edificiosCentro.add("A-0139 Aula Clase");
            edificiosCentro.add("A-0221 Aula Clase");
            edificiosCentro.add("A-0222 Aula Clase");
            edificiosCentro.add("A-0223 Aula Clase");
            edificiosCentro.add("A-0224 Aula Clase");
            edificiosCentro.add("A-0232 Lab. Intervencion Psicol");
            edificiosCentro.add("A-0233 Aula Clase");
            edificiosCentro.add("A-0234 Aula Clase");
            edificiosCentro.add("A-0235 Aula Clase");
            edificiosCentro.add("A-0236 Aula Clase");
            edificiosCentro.add("A-0237 Aula Clase");
            edificiosCentro.add("A-0321 Aula Clase");
            edificiosCentro.add("A-0322 Aula Clase");
            edificiosCentro.add("A-0323 Aula Clase");
            edificiosCentro.add("A-0324 Aula Clase");
            edificiosCentro.add("A-0331 Aula Clase");
            edificiosCentro.add("A-0332 Aula Clase");
            edificiosCentro.add("A-0333 Aula Clase");
            edificiosCentro.add("A-0334 Aula Clase");
            edificiosCentro.add("A-0411 Aula Interactiva Computo");
            edificiosCentro.add("A-0412 Aula Interactiva Computo");
            edificiosCentro.add("A-0421 Aula Clase");
            edificiosCentro.add("A-0431 Aula Clase");
            edificiosCentro.add("A-0432 Aula Clase");
            edificiosCentro.add("A-0433 Aula Clase");
            edificiosCentro.add("A-0811 Posgrado");
            edificiosCentro.add("A-0812 Posgrado");
            edificiosCentro.add("A-0813 Posgrado");
            edificiosCentro.add("A-0814 Posgrado");
            edificiosCentro.add("AG0121 Aula Integral Proyeccion");
            edificiosCentro.add("AV0721 Aula Integral Proyeccion");
            edificiosCentro.add("CITIEC Aula 111 Sotano");
            edificiosCentro.add("CRAP Aula 1012");
            edificiosCentro.add("CRAP Aula 1022");
            edificiosCentro.add("Trabajo en Campo");
            edificiosCentro.add("Galeria Itson Sala 4");
            edificiosCentro.add("IVC-BASVOL-Cancha Centro");
            edificiosCentro.add("Laboratorio de CIIBA");
            edificiosCentro.add("Laboratorio de Ecodesarollo");
            edificiosCentro.add("LV-0500 Laboratorio de sueldos");
            edificiosCentro.add("LV1300 Lab. Alimentos y Bebidas");
            return edificiosCentro;
        }
        return null;
    }
}
