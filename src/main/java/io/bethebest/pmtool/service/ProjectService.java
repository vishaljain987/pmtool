package io.bethebest.pmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bethebest.pmtool.exception.ProjectIdException;
import io.bethebest.pmtool.model.Backlog;
import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.repository.BacklogRepository;
import io.bethebest.pmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdate(Project project){
		//Logic
		try{
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			if(project.getId()==null){
				Backlog backlog = new Backlog();
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
				project.setBacklog(backlog);
			}
			
			if(project.getId()!=null){
				Backlog backlog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
				project.setBacklog(backlog);
			}
			return projectRepository.save(project);
		}catch(Exception e){
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier()+"' already exists");
		}
		
	}
	
	public Project findByProjectIdentifier(String projectIdentifier){
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(project == null){
			throw new ProjectIdException("Project does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteByProjectIdentifier(String projectIdentifier){
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(project == null){
			throw new ProjectIdException("Project does not exist");
		}
		projectRepository.deleteById(project.getId());
	}
}
