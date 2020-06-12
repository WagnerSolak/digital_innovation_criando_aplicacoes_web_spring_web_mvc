package com.globallabs.springwebmvc.controller;

import com.globallabs.springwebmvc.model.Jedi;
import com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // intercepta todas requisições, que estão sendo mapeadas, e cada req atende ao template
public class JediController {

    @Autowired
    private JediRepository jediRepository;

    @GetMapping("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        //final Jedi luke = new Jedi("Luke", "Sky"); // criou o objeto, passou para repository
        modelAndView.addObject("allJedi", jediRepository.getAllJedi());
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
        jediRepository.add(jedi);  // Interface resp. resultado de erros dessa validação

        redirect.addFlashAttribute("message", "Jedi created with sucess!");
        return "redirect:jedi";
    }
}
