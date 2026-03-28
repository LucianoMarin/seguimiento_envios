package com.sumativa1.seguimiento_envios.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sumativa1.seguimiento_envios.model.Envio;

@RestController
public class EnvioController {

    private List<Envio> envios = new ArrayList<>();

    public EnvioController() {

         /* 
           EN UN PRINCIPIO PENSE COLOCAR EL OBJETO CLIENTE A LA LISTA DE ENVIOS, PERO ESTO ROMPERIA LA ESTRUCTURA
           QUE TIENE UN MICROSERVICIO
        
           FINALMENTE DEJARE COMO REFERENCIA ESTOS OBJETOS (COMENTADOS), PARA QUE SE "ENTIENDA" LA LOGICA QUE SE QUIERE IMPLEMENTAR,
           DE COMO ATRAVES DE UN STRING QUE REPRESENTA EL RUT (ID DE OBJ CLIENTE) ES ASOCIADO A UN CLIENTE EN ESPECIFICO.


                Cliente cliente1 = new Cliente("18775116-0", "Luciano", "Marin");
                Cliente cliente2 = new Cliente("9415449-9", "Lujana", "Eunice");
                Cliente cliente3 = new Cliente("9862879-7", "Mario", "Marin");
          */



                /* COMIENZA EL LLENADO DE LA LISTA DE ENVIOS */

        envios.add(new Envio(1, "CL00001", "18775116-0", "S/E","Valdivia", "Santiago","Santiago", LocalDate.of(2026, 03, 21),
                    LocalDate.of(2026, 03, 23)));

        envios.add(new Envio(2, "CL00002", "18775116-0", "S/E", "Valdivia","Santiago","Valdivia", null, null));

        envios.add(new Envio(3, "CL00003", "9415449-9", "S/E", "Puerto Montt","Chiloe","Angelmo", LocalDate.of(2026, 03, 27), null));

        envios.add(new Envio(4, "CL00004", "9415449-9", "S/E", "Villarica","Temuco","Freire", LocalDate.of(2026, 03, 26), null));

        envios.add(new Envio(5, "CL00005", "9862879-7", "S/E", "Santiago","Santiago","", LocalDate.of(2026, 03, 10), null));

        envios.add(new Envio(6, "CL00006", "18775116-0", "S/E", "Puerto Varas","Puerto Varas","Puerto Varas", LocalDate.of(2026, 03, 27), null));

        envios.add(new Envio(7, "CL00007", "18775116-0", "S/E", "Concepcion","Concepcion","Hualpen", LocalDate.of(2026, 03, 26), null));

        envios.add(new Envio(8, "CL00008", "18775116-0", "S/E", "Viña del Mar","Valdivia","", LocalDate.of(2026, 03, 10),
                LocalDate.of(2026, 02, 13)));


        /* SE LLAMA EL METODO ESTADOS, SE LE ENVIA LA LISTA COMO ARGUMENTO 
            
            ESTE METODO MODIFICARA EL CAMPO ESTADO DENTRO DE ENVIOS, IMPORTANTE, SE SABE 
            QUE EL CONTROLADOR NO DEBE TENER LOGICA DE PROGRAMACION, PERO ERA UNA FORMAD DE MOSTRAR
            QUE SE PUEDE AUTOMATIZAR UN CAMPO DE UNA LISTA

            SOLO SE EJECUTA SOLO EN EL CONSTRUCTOR DE LA CLASE.

        */
        this.modificarEstados(envios);

    }


    /* FUNCION MODIFICARESTADOS, SE CREO PARA AUTOMATIZAR EL ESTADO DEFINIDO EN EL LIST  */
    public void modificarEstados(List<Envio> envios) {


        /* LOGICA, SI EL ENVIO TIENE FECHA DE ENTREGA, Y FECHA DE ENTREGA TIENE UN VALOR CORRECTO
        QUIERE DECIR TIENE UNA FECHA DESPUES O IGUAL A FECHA ENVIO, ESTADO GUARDA: ENTREGADO
        
        SI SOLO TENEMOS UNA FECHA DE ENVIO ESTADO: PENDIENTE

        SI LA FECHA DE ENTREGA POR EJEMPLO ES ANTES DE LA FECHA DE ENVIO, HAY UN ERROR.
        ESTADO: COMUNICARSE CON EMPRESA TRANSPORTISTA

        Y SINO: EL PAQUETE ESTA EN CAMINO

        */

        for (Envio e : envios) {
            if (e.getFechaEntrega() != null && (e.getFechaEntrega().isAfter(e.getFechaEnvio())
                    || e.getFechaEntrega().isEqual(e.getFechaEnvio()))) {

                e.setEstado("ENTREGADO");

            } else if (e.getFechaEnvio() == null) {

                e.setEstado("PENDIENTE");

            } else if (e.getFechaEntrega() != null && e.getFechaEntrega().isBefore(e.getFechaEnvio())) {

                e.setEstado("ERROR AL OBTENER EL ESTADO DE SU ENVIO, PORFAVOR COMUNICARSE CON EMPRESA TRANSPORTADORA");

            } else {

                e.setEstado("EN CAMINO");

            }

        }

    }


    /* ENDPOINT MOSTRAR TODOS LOS ENVIOS Y SU INFORMACION COMPLETA */
    @GetMapping("/envios")
    public List<Envio> mostrarEnvios() {

        return envios;

    }


    /* ENDPOINT MOSTRAR UN ENVIO Y SU INFORMACION COMPLETA */
    @GetMapping("/envios/{id}")
    public Envio mostrarEnvio(@PathVariable int id) {

        for (Envio e : envios) {

            if (id == e.getId()) {

                return e;
            }

        }

        return null;

    }


    /* MOSTRAR LA UBICACION DE TODOS LOS ENVIOS */
    @GetMapping("/envios/ubicacion")
    public List <String> mostrarUbicaciones() {

        List <String> direccion=new ArrayList<>();

        for(Envio e: envios){
           
            direccion.add(e.getUbicacionActual());
        }
        
        return direccion;
    }



    /* MOSTRAR LA UBICACION DE UN ENVIO EN ESPECIFICO */
    @GetMapping("/envios/ubicacion/{id}")
    public String mostrarUbicacion(@PathVariable int id) {

     

        for(Envio e: envios){
           if(id==e.getId()){

            return e.getUbicacionActual();

           }
        
        }
        
        return null;
    }

}
