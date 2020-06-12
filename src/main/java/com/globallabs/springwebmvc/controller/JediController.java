package com.globallabs.springwebmvc.controller;

import com.globallabs.springwebmvc.model.Jedi;
import com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller // intercepta todas requisições, que estão sendo mapeadas, e cada req atende ao template
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        //final Jedi luke = new Jedi("Luke", "Sky"); // criou o objeto, passou para repository
        modelAndView.addObject("allJedi", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name") final String name){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

       modelAndView.addObject("allJedi", repository.findByNameContainingIgnoreCase(name));
       return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){
         final ModelAndView modelAndView = new ModelAndView();
         modelAndView.setViewName("new-jedi");

         modelAndView.addObject("jedi", new Jedi());
         return modelAndView;
    }


    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result,
                             RedirectAttributes redirect){ // quando receber este parametro name e lastName converte num obj
        if(result.hasErrors()){
            return "new-jedi";
        }
            repository.save(jedi);  // Interface resp. resultado de erros dessa validação

        redirect.addFlashAttribute("message", "Jedi created with sucess!");
        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirectAttributes) {

        final Optional<Jedi> jedi = repository.findById(id);
        repository.delete(jedi.get());

        redirectAttributes.addFlashAttribute("message", "Jedi removed with sucess!");

        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model){
        final Optional<Jedi> jedi = repository.findById(id);

        model.addAttribute("jedi", jedi.get());
        return "edit-jedi";
    }
}
