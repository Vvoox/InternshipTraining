package io.khalil.project.web;

import io.khalil.project.Exceptions.MapValidationError;
import io.khalil.project.domain.Project;
import io.khalil.project.repositories.ProjectRepository;
import io.khalil.project.services.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@Log4j2
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationError mapValidationError;

    @Autowired
    private ProjectRepository projectRepository;




    @GetMapping("")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) throws NoSuchFieldException {
        ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
        if(errorMap!=null) return errorMap;
        projectService.saveOrUpdateProject(project);
        log.info("ADD PROJECT :"+project);
        return new ResponseEntity<Project>(project,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier){
        return projectService.deleteProjectByIdentifier(projectIdentifier);
    }

    @GetMapping("/{projectIdentifier}")
    public Project getOneProject(@PathVariable String projectIdentifier){
        return projectService.getProjectByIdentifier(projectIdentifier);
    }

    @PutMapping("/{id}")
    public Project uptadeProject(Project project){
        
    }


}
