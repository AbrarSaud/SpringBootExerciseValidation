package com.example.projecttrackersystemvalid.Controller;

import com.example.projecttrackersystemvalid.Api.ApiResponse;
import com.example.projecttrackersystemvalid.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    // Display all project .
    @GetMapping("/get")
    public ArrayList<Project> getAllProject() {
        return projects;
    }

    // Create a new project
    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String massageError = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massageError));
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Added successfully!"));
    }

    // Update a project
    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@PathVariable int id, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String massageError = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massageError));
        }
        for (Project p : projects) {
            if (p.getId() == id) {
                p.setTitle(project.getTitle());
                p.setStatus(project.getStatus());
                p.setDescription(project.getDescription());
                p.setCompanyName(project.getCompanyName());
                return ResponseEntity.status(200).body(new ApiResponse("updated successfully!"));
            }
        }
        return ResponseEntity.status(401).body(new ApiResponse("Project not found!"));
    }

    // Delete a project
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable int id) {
        for (Project p : projects) {
            if (p.getId() == id) {
                projects.remove(p);
                return ResponseEntity.status(200).body(new ApiResponse("deleted successfully!"));
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Project not found!"));
    }

    // Change the project status as 'Not Started', 'In Progress', or 'Completed'
    @PutMapping("/change/{id}")
    public ResponseEntity changeStatus(@PathVariable int id, @RequestParam String status) {
        for (Project p : projects) {
            if (p.getId() == id) {
                if (p.getStatus().equals("Completed")) {

                    return ResponseEntity.status(200).body(new ApiResponse("Project is  Completed!!"));
                } else if (p.getStatus().equals("In Progress")) {
                    p.setStatus(status);
                    return ResponseEntity.status(200).body(new ApiResponse("Project changed successfully to 'Completed'"));
                } else if (p.getStatus().equals("Not Started")) {
                    p.setStatus(status);
                    return ResponseEntity.status(200).body(new ApiResponse("Project changed successfully to 'In Progress'"));
                } else {
                    return ResponseEntity.status(404).body(new ApiResponse("Invalid status ."));
                }
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Project not found!"));
    }

    //    Search for a project by given title
    @GetMapping("/search")
    public ResponseEntity searchProjectByTitle(@RequestParam String title) {
        ArrayList<Project> projectByTitle = new ArrayList<>();
        for (Project p : projects) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                projectByTitle.add(p);
            }
        }
        return ResponseEntity.status(200).body(projectByTitle);
    }

    //    Display All project for one company by companyName.
    @GetMapping("/get-by-CompanyName")
    public ResponseEntity gerProjectByCompanyName(@RequestParam String companyName) {
        ArrayList<Project> projectByCompanyName = new ArrayList<>();

        for (Project p : projects) {
            if (p.getCompanyName().equalsIgnoreCase(companyName)) {
                projectByCompanyName.add(p);
            }
        }
        return ResponseEntity.status(200).body(projectByCompanyName);
    }

}
