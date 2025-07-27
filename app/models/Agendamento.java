package models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Agendamento extends Model{

	public LocalDateTime dataHora;

    public String servico;

    @Enumerated(EnumType.STRING)
    public Status status;




    public Agendamento() {
    	this.status = Status.ATIVO;
    }

    @ManyToOne
    public Pet pet;
}
