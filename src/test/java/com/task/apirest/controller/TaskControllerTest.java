package com.task.apirest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType; 
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.task.apirest.model.Tarea;
import com.task.apirest.service.TaskService;
import com.task.apirest.service.impl.TaskServiceImpl;
 

@Import({
	TaskServiceImpl.class
})

@WebMvcTest(TaskController.class)
class TaskControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@MockBean
	TaskService taskService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test 
	void testEliminar_TareaNoExite() throws Exception {
		
		Mockito.when(taskService.get(1L)).thenReturn(null);		
		String uri="/api/5";
		mockMvc.perform(delete(uri)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCrear_ValidarCampoObligatorio() throws Exception {
		ObjectMapper objectMapper = getObjectMapper();	
		
		
		Tarea tareaDb=	objectMapper.readValue("{\r\n" + 
				"\"descripcion\":\"alonso\",\r\n" + 
				"\"vigente\":true\r\n" + 
				"}",Tarea.class); 
		
		Mockito.when(taskService.get(1L)).thenReturn( tareaDb );		
		String uri="/api/";
		mockMvc.perform(post(uri) .contentType(APPLICATION_JSON_UTF8).
				content("{}")).andExpect(status().isBadRequest());
	}

	
	protected ObjectMapper getObjectMapper()
	  {

		ObjectMapper objectMapper = new ObjectMapper();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    objectMapper.setDateFormat(df);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    objectMapper.setSerializationInclusion(Include.NON_NULL);
	    return objectMapper;
	  }
	
}
