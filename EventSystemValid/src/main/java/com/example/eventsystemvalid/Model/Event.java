package com.example.eventsystemvalid.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "The 'ID' must be NOT Null ")
    @Min(value = 2, message = "The 'ID' must be greater than 2")
    private int id;

    @NotNull(message = "The 'Description' must be NOT Null ")
    @Size(min = 15, max = 200, message = "The 'Description' must be greater than 15")
    private String description;

    @NotNull(message = "The 'Capacity' must be NOT Null ")
    @Positive(message = "The 'Capacity' must be number ")
    @Min(value = 25, message = "The 'Capacity' must be at least 25")
    private int capacity;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
