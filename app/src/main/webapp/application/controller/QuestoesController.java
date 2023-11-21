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
