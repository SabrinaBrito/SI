package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model implements Comparable<Object>{

	@Id
	public Long id;

	@Required
	public String label = "0";
	public String descricao = "0";
	public String projetoAssociado = "0";
	public int prioridade;
	public String tarefaRealizada = "Tarefa ainda nao foi realizada";

	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public String getTarefaRealizada() {
		return tarefaRealizada;
	}
	
	public String getProjetoAssociado() {
		return projetoAssociado;
	}
	
	public static List<Task> all() {
		return find.all();
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}



	@Override
	public int compareTo(Object arg0) {
		int result;
		if (prioridade == ((Task) arg0).prioridade) {
			result = 0;
		} else if (prioridade >= ((Task) arg0).prioridade) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

}