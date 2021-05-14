package com.task.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.apirest.model.Tarea;
import com.task.apirest.repository.TaskRepository;
import com.task.apirest.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public Tarea saveTask(Tarea task) {
		// TODO Auto-generated method stub
		Tarea taskNew = taskRepository.save(task);
		return taskNew;
	}

	@Override
	public List<Tarea> getAll() {
		// TODO Auto-generated method stub
		return ((List<Tarea>)taskRepository.findAll());
	}

	@Override
	public Tarea get(Long id) {
		// TODO Auto-generated method stub
		return  taskRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteTask(Tarea task) {
		try {
			taskRepository.deleteById(task.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
