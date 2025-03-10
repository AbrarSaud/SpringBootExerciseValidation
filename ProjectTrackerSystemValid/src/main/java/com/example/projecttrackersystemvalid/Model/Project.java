package com.example.projecttrackersystemvalid.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotNull(message = "The 'ID' must be NOT Null")
    @Min(value = 2, message = "The 'ID' must be greater than 2")
    private Integer id;

    @NotEmpty(message = "The 'Title' must be NOT Empty")
    @Size(min = 8, max = 200, message = "The 'Title' must be greater than or = 8")
    private String title;

    @NotEmpty(message = "The 'Description' must be NOT Empty")
    @Size(min = 15,max = 200, message = "The 'Description' length must be more than or = 15")
    private String description;

    @NotEmpty(message = "The 'Status' must be NOT Empty")
    @Pattern(regexp = "Not Started||In Progress||Completed", message = "Status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;

    @NotEmpty(message = "The 'Company Name' must be NOT Empty")
    @Size(min = 6,max = 200, message = "The 'Company Name' length must be more than  or = 6")
    private String companyName;
}
