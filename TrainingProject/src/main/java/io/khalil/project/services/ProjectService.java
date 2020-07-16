package io.khalil.project.services;

import io.khalil.project.Exceptions.ProjectIdException;
import io.khalil.project.domain.Project;
import io.khalil.project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID "+project.getProjectIdentifier() +" already exist.");
        }
    }
    public ResponseEntity<?> deleteProjectByIdentifier(String projectIdentifier){
        Project project = getProjectByIdentifier(projectIdentifier);
        projectRepository.delete(project);
        return new ResponseEntity<>("Project with ID "+projectIdentifier+" delete it successfully.",HttpStatus.OK);
    }

    public Project getProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if(project==null){
            throw new ProjectIdException("Project with ID "+projectIdentifier+" doesn't exist");
        }
        return project;
    }

    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }
}
