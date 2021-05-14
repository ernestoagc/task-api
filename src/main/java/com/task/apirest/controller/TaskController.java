package com.task.apirest.controller;


import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.apirest.exception.ValidateException;
import com.task.apirest.model.Tarea;
import com.task.apirest.service.TaskService;
import com.task.apirest.util.Constant; 

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api")
public class TaskController {
	
Logger logger = LogManager.getLogger(TaskController.class);
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/findall")	
	@ResponseBody
	public ResponseEntity<?> findAllTask(@RequestParam Map<String,String> parametros)  {

		logger.info("===LIST TASK==== BEGIN ====");   		
		List<Tarea> taskList =taskService.getAll();
		
		
		logger.info("===LIST TASK==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(taskList);
	}
	


	
	@GetMapping("/{id}")	
	@ResponseBody
	public ResponseEntity<?> getTask(@PathVariable Long id) throws Exception{

		logger.info("===GETDETAIL TASK==== BEGIN ===="); 
		Tarea task =taskService.get(id);
		
		if(task==null) {
			
			throw new ValidateException(Constant.ERROR_CODE.BAD_REQUEST,"La tarea no existe");
		}
		
		
		logger.info("===GETDETAIL TASK==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(task);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> saveTask(@RequestBody Tarea task){
		
		logger.info("===NEW TASK==== BEGIN ====");	   	
		
		if( StringUtils.isEmpty(task.getDescripcion()))  {
		
		throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"descripcion"));
	}
		 			/*
		if( StringUtils.isEmpty(task.getFechaCreacion()))  {			
		throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"fecha creación"));
	}*/
	
	
		if( StringUtils.isEmpty(task.getVigente()))  {		
		throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"vigencia"));
	}
	
		Tarea taskNew = taskService.saveTask(task);
		
		logger.info("===NEW TASK==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(taskNew);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Long id,@RequestBody Tarea task){
		
		logger.info("===UPDATE  TASK==== BEGIN ===="); 	
		Tarea taskBd =taskService.get(id);
		
		if(taskBd==null) {
			
			throw new ValidateException(Constant.ERROR_CODE.BAD_REQUEST,"La tarea no existe");
		}
		

		
		taskBd.setDescripcion(task.getDescripcion());
		taskBd.setFechaCreacion(task.getFechaCreacion());
		taskBd.setVigente(task.getVigente());
		
		Tarea taskUpdated = taskService.saveTask(task);
		logger.info("===UPDATE TASK==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable long id) 
	{
		logger.info("===DELETE TASK==== BEGIN ===="); 
		Tarea task =taskService.get(id);
		
		if(task==null) {
			
			throw new ValidateException(Constant.ERROR_CODE.BAD_REQUEST,"La tarea no existe");
		}
		
		taskService.deleteTask(task);
		logger.info("===DELETE TASK==== END ====");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

}
