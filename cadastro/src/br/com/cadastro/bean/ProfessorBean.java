package br.com.cadastro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastro.dao.DAO;
import br.com.cadastro.modelo.Professor;

@ManagedBean
@ViewScoped
public class ProfessorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Professor professor = new Professor();
	
	private Integer professorId;
	
	public Integer getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Integer professorId) {
		this.professorId = professorId;
	}

	public void carregarProfessorPelaId() {
		this.professor = new DAO<Professor>(Professor.class).buscaPorId(professorId);
	}

	public String gravar() {
		System.out.println("Gravando Professor " + this.professor.getNome());

		if(this.professor.getId() == null) {
			new DAO<Professor>(Professor.class).adiciona(this.professor);
		} else {
			new DAO<Professor>(Professor.class).atualiza(this.professor);
		}

		this.professor = new Professor();

		return "professor?faces-redirect=true";
	}
	
	public void remover(Professor professor) {
		System.out.println("Removendo professor " + professor.getNome());
		new DAO<Professor>(Professor.class).remove(professor);
	}
	
	public List<Professor> getProfessores() {
		return new DAO<Professor>(Professor.class).listaTodos();
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
