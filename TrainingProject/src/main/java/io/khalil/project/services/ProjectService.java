package io.khalil.project.services;

import io.khalil.project.domain.Project;
import io.khalil.project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        return projectRepository.save(project);
    }

    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }
}
