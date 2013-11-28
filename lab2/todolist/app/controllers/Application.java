package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Task;
import play.*;
import play.mvc.*;
import views.html.*;
import play.data.*;
import static play.data.Form.*;

public class Application extends Controller {
	static Form<Task> taskForm = Form.form(Task.class);
	
	public static Result index() {
		return redirect(routes.Application.tasks());
	}

	public static Result tasks() {
		return ok(views.html.index.render(Task.all(), taskForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		  if(filledForm.hasErrors()) {
			return badRequest(
			  views.html.index.render(Task.all(), filledForm)
			);
		  } else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());  
		  }	
	}
	/**
	 * Deleta uma tarefa
	 * @param id
	 * @return
	 */
	public static Result deleteTask(Long id) {
		Task.delete(id);
    	return redirect(routes.Application.tasks());
	}
	
	/**
	 * Atualiza a tarefa
	 * @param id
	 * @return
	 */
	public static Result update(Long id) {
        Task.update(id);
        return redirect(routes.Application.tasks());
}

    
}
