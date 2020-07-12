package io.bethebest.pmtool.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.service.ProjectService;
import io.bethebest.pmtool.service.ValidationErrorMapService;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	//make sure client passes valid object
	@Autowired
	private ValidationErrorMapService validationErrorMapService;
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
		ResponseEntity errorMap = validationErrorMapService.validationErrorMap(result);
		if(errorMap!=null) return errorMap;
		
		Project newProject = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> findByProjectIdentifier(@PathVariable String projectIdentifier){
		
		Project project = projectService.findByProjectIdentifier(projectIdentifier);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
}
