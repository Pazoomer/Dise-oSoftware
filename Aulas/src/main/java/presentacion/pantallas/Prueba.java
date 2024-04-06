
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;



/**
 *
 * @author luiis
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String diasEvento="Lu,Mi,Vi";
//        String[] diasArr=diasEvento.split(",",7);
//            List<Integer> diasNum=new ArrayList<>();
//            for(String s:diasArr){
//                switch(s){
//                    case "Do" -> diasNum.add(1);
//                    case "Lu" -> diasNum.add(2);
//                    case "Ma" -> diasNum.add(3);
//                    case "Mi" -> diasNum.add(4);
//                    case "Ju" -> diasNum.add(5);
//                    case "Vi" -> diasNum.add(6);
//                    case "Sa" -> diasNum.add(7);
//                }
//            }
//        for(Integer i:diasNum){
//            System.out.println(i);
//        }
        IRecuperarMaestro rMaestro = new FachadaRecuperarMaestro();
        MaestroEditableDTO m;
        MaestroEditableDTO maestroE=null;
        
        
        try {
            m = rMaestro.recuperarMaestro();    
            maestroE = rMaestro.recuperarMaestro2(m);
            System.out.println(maestroE.getNombre());
        } catch (NegocioException e) {
            System.out.println("error al cargar el maestro");
        }
        if (maestroE != null) {
            new PrincipalMaestro(maestroE).setVisible(true);
        }

    }

}
