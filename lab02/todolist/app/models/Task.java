package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model implements Comparable<Object>{

	@Id
	public Long id;

	// @Required
	public String label;
	public String descricao;
	public String projetoAssociado;
	public int prioridade;
	public boolean tarefaRealizada = false;

	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isTarefaRealizada() {
		return tarefaRealizada;
	}

	public void setTarefaRealizada(boolean tarefaRealizada) {
		this.tarefaRealizada = tarefaRealizada;
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

	public static void marcaTarefa(Long id) {
		Task task = find.ref(id);
		if (task.isTarefaRealizada() == true) {
			task.setTarefaRealizada(false);
		} else {
			task.setTarefaRealizada(true);
		}
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