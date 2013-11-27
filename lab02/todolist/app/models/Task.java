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
	public String descricao;
	public String projetoAssociado;
	public int prioridade;
	public boolean tarefaRealizada = false;
	
	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProjetoAssociado() {
		return projetoAssociado;
	}

	public void setProjetoAssociado(String projetoAssociado) {
		this.projetoAssociado = projetoAssociado;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public boolean getTarefaRealizada() {
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

	public static void update(Long id){
		Task task = find.ref(id);
        if (task.getTarefaRealizada() == true) {
        	task.setTarefaRealizada(false);
        } else{
        	task.setTarefaRealizada(true);
        }
        task.update();
    }

	@Override
	public int compareTo(Object arg0) {
		int result;
		if (prioridade >= ((Task) arg0).prioridade) {
			result = 1;
		} else if (prioridade == ((Task) arg0).prioridade) {
			result = 0;
		} else {
			result = -1;
		}
		return result;
	}

}