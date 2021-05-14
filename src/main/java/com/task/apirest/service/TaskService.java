package com.task.apirest.service;

import java.util.List;

import com.task.apirest.model.Tarea;

public interface TaskService {
	
	Tarea saveTask(Tarea task);
	List<Tarea> getAll();
	Tarea get(Long id);
	boolean deleteTask(Tarea task);
	

}
