package io.khalil.project.web;

import io.khalil.project.Exceptions.MapValidationError;
import io.khalil.project.domain.Project;
import io.khalil.project.services.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@Log4j2
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationError mapValidationError;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) throws NoSuchFieldException {
        ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
        if(errorMap!=null) return errorMap;
        projectService.saveOrUpdateProject(project);
        log.info("ADD PROJECT :"+project);
        return new ResponseEntity<Project>(project,HttpStatus.CREATED);
    }


}
