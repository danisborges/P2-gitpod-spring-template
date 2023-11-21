package application.model;

import java.util.Set;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "alternativas")
public class Alternativas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String texto;

    @Column
    private Boolean isCorreta;

    @ManyToOne
    @JoinColumn(name = "id_questao")
    private Questoes questoes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getCorreta() {
        return isCorreta;
    }

    public void setCorreta(Boolean correta) {
        isCorreta = correta;
    }

    public Questoes getQuestao() {
        return questoes;
    }

    public void setQuestao(Questoes questoes) {
        this.questoes = questoes;
    }


}