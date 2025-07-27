package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Pet extends Model {
	
public String nome;
public String raca;

@Enumerated(EnumType.STRING)
public Status status;




public Pet() {
	this.status = Status.ATIVO;
}

}
