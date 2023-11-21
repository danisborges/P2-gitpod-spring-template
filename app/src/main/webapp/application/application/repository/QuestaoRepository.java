package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Questoes;

public interface QuestaoRepository extends CrudRepository<Questoes, Long> {

}