package com.jaen.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaen.modelo.Personas;
import com.jaen.modelo.PersonaCrud;;

@Controller
@RequestMapping("/crud")
public class ControladorCrud {

	@Autowired
    private PersonaCrud uc;
 
    @RequestMapping(value="", method = RequestMethod.GET)
    public String listaPersonas(ModelMap mp){
        mp.put("personas", uc.findAll());
        return "crud/lista";
    }
 
    @RequestMapping(value="/nuevo", method=RequestMethod.GET)
    public String nuevo(ModelMap mp){
        mp.put("persona", new Personas());
        return "crud/nuevo";
    }
 
    @RequestMapping(value="/crear", method=RequestMethod.POST)
    public String crear(@Valid Personas persona,
            BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
            return "/crud/nuevo";
        }else{
            uc.save(persona);
            mp.put("persona", persona);
            return "crud/creado";
        }
    }
 
    @RequestMapping(value="/creado", method = RequestMethod.POST)
    public String creado(@RequestParam("persona") Personas persona){
        return "/crud/creado";
    }
}
