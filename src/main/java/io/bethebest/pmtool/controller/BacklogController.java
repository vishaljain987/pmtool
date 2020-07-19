package io.bethebest.pmtool.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bethebest.pmtool.model.ProjectTask;
import io.bethebest.pmtool.service.ProjectTaskService;
import io.bethebest.pmtool.service.ValidationErrorMapService;

@RestController
@RequestMapping("/api/v1/backlog")
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	//make sure client passes valid object
	@Autowired
	private ValidationErrorMapService validationErrorMapService;

	@PostMapping("/{projectIdentifier}")
	public ResponseEntity<?> createProjectTask(@PathVariable String projectIdentifier, @Valid @RequestBody ProjectTask projectTask, BindingResult result){
		
		ResponseEntity errorMap = validationErrorMapService.validationErrorMap(result);
		
		if(errorMap!=null)	return errorMap;
		
		ProjectTask pt = projectTaskService.addProjectTask(projectIdentifier, projectTask);
		return new ResponseEntity<ProjectTask>(pt, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public Iterable<ProjectTask> findAllProjectTasks(@PathVariable String projectIdentifier){
		
		return projectTaskService.findByProjectIdentifierOrderByPriority(projectIdentifier);
		
	}
}
