package io.bethebest.pmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bethebest.pmtool.exception.ProjectIdException;
import io.bethebest.pmtool.model.Backlog;
import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.repository.BacklogRepository;

@Service
public class BacklogService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Backlog findByProjectIdentifier(String projectIdentifier){
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		

		return backlog;
	}
}
