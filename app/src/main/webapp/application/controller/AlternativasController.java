package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.repository.AlternativaRepository;

import org.springframework.ui.Model;

import application.model.Alternativa;

@Controller
@RequestMapping("/alternativa")
public class AlternativaController {
    @Autowired
    private AlternativaRepository alternativaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("alternativas", alternativaRepository.findAll());
        return "alternativa/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        return "alternativa/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Alternativa alternativa = new alternativa();
        alternativa.setNome(nome);
        alternativaRepository.save(alternativa);
        System.out.println("Nome: " + nome);
        return "redirect:/alternativa/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        Optional<Alternativa> alternativa = alternativaRepository.findById(id);

        if (alternativa.isPresent()) {
            ui.addAttribute("alternativa", alternativa.get());
            return "alternativa/update";
        }

        return "redirect:/alternativa/list";

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id, @RequestParam("nome") String nome) {
        Optional<Alternativa> alternativa = alternativaRepository.findById(id);

        if(alternativa.isPresent()) {
            alternativa.get().setNome(nome);
            alternativaRepository.save(alternativa.get());
        }

        return "redirect:/alternativa/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Alternativa> alternativa = alternativaRepository.findById(id);

        if(alternativa.isPresent()) {
            ui.addAttribute("alternativa", alternativa.get());
            return "alternativa/delete";
        }
        
        return "redirect:/alternativa/list";
    }

    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        alternativaRepository.deleteById(id);
        return "redirect:/alternativa/list";
    }
}
