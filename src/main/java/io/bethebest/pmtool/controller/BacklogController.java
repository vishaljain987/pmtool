package io.bethebest.pmtool.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{projectIdentifier}/{sequence}")
	public ResponseEntity<?> findAllProjectTaskBySequence(@PathVariable String projectIdentifier, @PathVariable String sequence){
		
		ProjectTask projectTask = projectTaskService.findByProjectSequence(projectIdentifier, sequence);
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@PutMapping("/{projectIdentifier}/{sequence}")
	public ResponseEntity<?> updateProjectTaskBySequence(@PathVariable String projectIdentifier, @PathVariable String sequence, @Valid @RequestBody ProjectTask updatedProjectTask, BindingResult result){
		
		ResponseEntity errorMap = validationErrorMapService.validationErrorMap(result);
		if(errorMap!=null) return errorMap;
		
		ProjectTask projectTask = projectTaskService.updateByProjectSequence(projectIdentifier, sequence, updatedProjectTask);
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIdentifier}/{sequence}")
	public ResponseEntity<?> deleteProjectTaskBySequence(@PathVariable String projectIdentifier, @PathVariable String sequence){
		
		projectTaskService.deleteByProjectSequence(projectIdentifier, sequence);
		
		return new ResponseEntity<String>("Project Task '"+sequence+"' deleted successfully", HttpStatus.OK);
	}
}
