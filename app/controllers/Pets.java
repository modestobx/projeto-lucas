package controllers;

import java.util.List;

import models.Agendamento;
import models.Pet;
import models.Status;
import play.mvc.Controller;

public class Pets extends Controller {
	public static void form() {
	    List<Pet> pets = Pet.find("status = ?1", Status.ATIVO).fetch();
	    render(pets);
	}

	public static void detalhar(Long id) {
	    Agendamento agendamento = Agendamento.findById(id);
	    if (agendamento == null || agendamento.pet == null) {
	        flash.error("Agendamento ou pet não encontrado.");
	        listar(null);
	    } else {
	        render(agendamento);
	    }
	}

	public static void salvar(Pet pet, Agendamento agendamento) {
	    if (pet.nome != null) {
	        pet.nome = pet.nome.trim().toUpperCase();
	    }
	    if (pet.raca != null) {
	        pet.raca = pet.raca.trim().toUpperCase();
	    }
	    pet.save();

	    agendamento.pet = pet;

	    if (agendamento.servico != null) {
	        agendamento.servico = agendamento.servico.trim().toUpperCase();
	    }
	    agendamento.status = Status.ATIVO;
	    agendamento.save();

	    detalhar(agendamento.id);
	}

	public static void listar(String termo) {
	    List<Agendamento> lista;
	    if (termo == null || termo.trim().isEmpty()) {
	        lista = Agendamento.find("status <> ?1", Status.INATIVO).fetch();
	    } else {
	        termo = "%" + termo.toLowerCase() + "%";
	        lista = Agendamento.find(
	            "(lower(nome) like ?1 or lower(pet.nome) like ?1) and status <> ?2",
	            termo,
	            Status.INATIVO
	        ).fetch();
	    }
	    render(lista, termo);
	}

	public static void remover(long id) {
	    Agendamento ag = Agendamento.findById(id);
	    if (ag != null) {
	        ag.status = Status.INATIVO;
	        ag.save();
	    }
	    listar(null);
	}

	public static void editar(Long id) {
	    Agendamento agendamento = Agendamento.findById(id);
	    if (agendamento == null) {
	        flash.error("Agendamento não encontrado.");
	        listar(null);
	    } else {
	        renderTemplate("Pets/form.html", agendamento, agendamento.pet);
	    }
	}
}
