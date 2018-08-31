package br.com.cadastro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.cadastro.dao.DAO;
import br.com.cadastro.modelo.Professor;
import br.com.cadastro.modelo.Aluno;

@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer professorId;
	Aluno aluno = new Aluno();
	List<Aluno> alunos;
	
	public Integer getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Integer professorId) {
		this.professorId = professorId;
	}

	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Aluno> getAlunos() {
		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		if(this.alunos == null) {
			this.alunos = dao.listaTodos();
		}
		return alunos;
	}

	public List<Professor> getProfessores() {
		return new DAO<Professor>(Professor.class).listaTodos();
	}

	public List<Professor> getProfessoresDoAluno() {
		return this.aluno.getProfessores();
	}

	public void carregarAlunoPelaId() {
		this.aluno = new DAO<Aluno>(Aluno.class).buscaPorId(this.aluno.getId()); 
	}
	
	public void gravarProfessor() {
		Professor professor = new DAO<Professor>(Professor.class).buscaPorId(this.professorId);
		this.aluno.adicionaProfessor(professor);
		System.out.println("Tem aula com: " + professor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando aluno " + this.aluno.getNome());

		if (aluno.getProfessores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("professor",
					new FacesMessage("Aluno deve ter pelo menos um professor."));
			return;
		}

		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		if(this.aluno.getId() == null) {
			dao.adiciona(this.aluno);
			this.alunos = dao.listaTodos();
		} else {
			dao.atualiza(this.aluno);
		}

		this.aluno = new Aluno();
	}

	public void remover(Aluno aluno) {
		System.out.println("Removendo Aluno");
		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		dao.remove(aluno);
		this.alunos = dao.listaTodos();
	}
	
	public void removerProfessorDoAluno(Professor professor) {
		this.aluno.removeProfessor(professor);
	}
	
	public void carregar(Aluno aluno) {
		System.out.println("Carregando Aluno");
		this.aluno = aluno;
	}
	
	public String formProfessor() {
		System.out.println("Chamanda do formulario do Professor.");
		return "professor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"CPF deveria começar com 1"));
		}

	}
	
}
