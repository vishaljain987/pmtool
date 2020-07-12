package io.bethebest.pmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.service.ProjectService;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
//		if(result.hasErrors()){
//			System.out.println("************************Inside error***************************");
//			return new ResponseEntity<String>("Bad Data", HttpStatus.BAD_REQUEST);
//		}
		Project newProject = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
		
	}
}
