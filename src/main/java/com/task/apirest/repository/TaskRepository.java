package com.task.apirest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.task.apirest.model.Tarea;

public interface TaskRepository extends PagingAndSortingRepository<Tarea, Long>{

}
