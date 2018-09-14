package br.com.cadastro.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.cadastro.modelo.Professor;
import br.com.cadastro.modelo.Aluno;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static Professor geraProfessor(String nome) {
		Professor professor = new Professor();
		professor.setNome(nome);
		return professor;
	}

	private static Aluno geraAluno(String cpf, String nome, String dataNascimento, String telefone, String email, Professor professor) {
		Aluno aluno = new Aluno();
		aluno.setCpf(cpf);
		aluno.setNome(nome);
		aluno.setDataNascimento(parseData(dataNascimento));
		aluno.setEmail(email);
		aluno.setTelefone(telefone);
		aluno.adicionaProfessor(professor);
		return aluno; 
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
