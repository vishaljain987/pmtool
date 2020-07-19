package io.bethebest.pmtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bethebest.pmtool.model.Backlog;
import io.bethebest.pmtool.model.ProjectTask;
import io.bethebest.pmtool.repository.BacklogRepository;
import io.bethebest.pmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
		
		
		//ProjectTask to added to a specific project, Backlog exists
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		//set the Backlog to ProjectTask
		projectTask.setBacklog(backlog);
		
		//set ProjectTask sequence
		Integer backlogSequence = backlog.getPTSequence();
		backlogSequence++;
		projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
		backlog.setPTSequence(backlogSequence);
		
		//set projectIdentifier
		projectTask.setProjectIdentifer(projectIdentifier);
		
		//update the Backlog sequence
		
		//set initial priority when null
		if(projectTask.getPriority()==null || projectTask.getPriority()==0){
			projectTask.setPriority(3);
		}
		
		//set initial status when null
		if(projectTask.getStatus()==null || projectTask.getStatus()==""){
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier){
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
	}
}
