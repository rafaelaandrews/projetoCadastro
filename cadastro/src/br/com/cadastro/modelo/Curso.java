package br.com.cadastro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Curso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio = Calendar.getInstance();
	private String diaSemana;
	
	public Curso() {
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Professor> professores = new ArrayList<Professor>();

	public List<Professor> getProfessores() {
		return professores;
	}

	public void adicionaProfessor(Professor professor) {
		this.professores.add(professor);
	}
	
	public void removeProfessor(Professor professor) {
		professores.remove(professor);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
