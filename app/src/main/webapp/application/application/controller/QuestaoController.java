package application.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import application.model.Questao;
import application.model.Alternativa;
import application.repository.QuestaoRepository;
import application.repository.AlternativaRepository;
import application.repository.PlataformaRepository;

import java.util.Set;

@Controller
@RequestMapping("/questao")
public class QuestaoController {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("questoes", questaoRepository.findAll());
        return "questao/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("plataformas", plataformaRepository.findAll());
        ui.addAttribute("alternativas", alternativaRepository.findAll());
        return "questao/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
            @RequestParam("titulo") String titulo,
            @RequestParam("plataformas") Long[] idsPlataformas,
            @RequestParam("alternativa") Long alternativa_id) {

        Questao questao = new Questao();
        questao.setTitulo(titulo);
        questao.setAlternativa(alternativaRepository.findById(alternativa_id).get());

        for (long p : idsPlataformas) {
            Optional<Plataforma> plataforma = plataformaRepository.findById(p);

            if (plataforma.isPresent()) {
                questao.getPlataformas().add(plataforma.get());
            }
        }
        questaoRepository.save(questao);
        return "redirect:/questao/list";

    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {

        Optional<Questao> questao = questaoRepository.findById(id);
        if (questao.isPresent()) {
            ui.addAttribute("questao", questaoRepository.findById(id).get());
            ui.addAttribute("plataformas", plataformaRepository.findAll());
            ui.addAttribute("alternativas", alternativaRepository.findAll());
            return "questao/update";
        }
        return "redirect:/questao/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam("id") Long id,
            @RequestParam("plataformas") Long[] idsPlataformas,
            @RequestParam("alternativa") Long alternativa_id,
            @RequestParam("titulo") String titulo) {

        Optional<Questao> questao = questaoRepository.findById(id);

        if (questao.isPresent()) {
            questao.get().setTitulo(titulo);
            questao.get().setAlternativa(alternativaRepository.findById(alternativa_id).get());

            Set<Plataforma> updatePlataforma = new HashSet<>();

            for (long p : idsPlataformas) {
                Optional<Plataforma> plataforma = plataformaRepository.findById(p);
                if (plataforma.isPresent()) {
                    updatePlataforma.add(plataforma.get());
                }
            }
            questao.get().setPlataformas(updatePlataforma);
            questaoRepository.save(questao.get());
        }

        return "redirect:/questao/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Questao> questao = questaoRepository.findById(id);

        if (questao.isPresent()) {
            ui.addAttribute("questao", questao.get());
            return "questao/delete";
        }

        return "redirect:/questao/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        questaoRepository.deleteById(id);
        return "redirect:/questao/list";
    }

}