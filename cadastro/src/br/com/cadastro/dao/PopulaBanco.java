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

		Professor patrick = geraProfessor("Patrick Cardoso");
		em.persist(patrick);

		Professor herval = geraProfessor("Herval Lemos");
		em.persist(herval);

		Aluno rafaela = geraAluno("17020568726", "Rafaela Andrews",
					"19/03/1996", "975078554", "rafaela@gmail.com", patrick);
		Aluno vitor = geraAluno("01345872569",
					"Vitor Andrews", "26/05/1999", "998855131", "vitor@gmail.com", patrick);
		Aluno paulo = geraAluno("14785269874", "Paulo José",
					"01/01/1999", "998825511", "paulo@gmail.com", patrick);

		em.persist(rafaela);
		em.persist(vitor);
		em.persist(paulo);

		Aluno daivisson = geraAluno("14789754788", "Daivisson",
					"01/01/1937", "919775522", "daivisson@hotmail.com", herval);
		Aluno pat = geraAluno("11144785236",
					"Patrick", "01/01/1995", "978844123", "pat@gmail.com", herval);

		em.persist(daivisson);
		em.persist(pat);

		em.getTransaction().commit();
		em.close();

	}

	private static Professor geraProfessor(String nome) {
		Professor professor = new Professor();
		professor.setNome(nome);
		return professor;
	}

	private static Aluno geraAluno(String cpf, String nome, String dataNascimento, String telefone,
				String email, Professor professor) {
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
