package br.com.cadastro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastro.dao.DAO;
import br.com.cadastro.modelo.Curso;
import br.com.cadastro.modelo.Professor;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Curso curso = new Curso();
	private Integer professorId;
	List<Curso> cursos;

	public Integer getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Integer professorId) {
		this.professorId = professorId;
	}

	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Curso> getCursos() {
		DAO<Curso> dao = new DAO<Curso>(Curso.class);
		if(this.cursos == null) {
			this.cursos = dao.listaTodos();
		}
		return cursos;
	}

	public List<Professor> getProfessores() {
		return new DAO<Professor>(Professor.class).listaTodos();
	}

	public List<Professor> getProfessoresDoCurso() {
		return this.curso.getProfessores();
	}

	public void carregarCursoPelaId() {
		this.curso = new DAO<Curso>(Curso.class).buscaPorId(this.curso.getId()); 
	}
	
	public void gravarProfessor() {
		Professor professor = new DAO<Professor>(Professor.class).buscaPorId(this.professorId);
		this.curso.adicionaProfessor(professor);
		System.out.println("Curso dado por: " + professor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando curso " + this.curso.getNome());

		if (curso.getProfessores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("professor",
					new FacesMessage("curso deve ter pelo menos um professor."));
			return;
		}

		DAO<Curso> dao = new DAO<Curso>(Curso.class);
		if(this.curso.getId() == null) {
			dao.adiciona(this.curso);
			this.cursos = dao.listaTodos();
		} else {
			dao.atualiza(this.curso);
		}

		this.curso = new Curso();
	}

	public void remover(Curso curso) {
		System.out.println("Removendo curso");
		DAO<Curso> dao = new DAO<Curso>(Curso.class);
		dao.remove(curso);
		this.cursos = dao.listaTodos();
	}
	
	public void removerProfessorDoCurso(Professor professor) {
		this.curso.removeProfessor(professor);
	}
	
	public void carregar(Curso curso) {
		System.out.println("Carregando curso");
		this.curso = curso;
	}
	
	public String formProfessor() {
		System.out.println("Chamanda do formulario do Professor.");
		return "professor?faces-redirect=true";
	}
	
}
