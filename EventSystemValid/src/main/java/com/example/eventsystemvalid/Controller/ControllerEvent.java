package com.example.eventsystemvalid.Controller;


import com.example.eventsystemvalid.Api.ApiResponse;
import com.example.eventsystemvalid.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/event")
public class ControllerEvent {
    ArrayList<Event> events = new ArrayList<>();


    //    Display all event
    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    //    Create a new event
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String massageErrors = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massageErrors));
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully!!"));
    }

    // Update a event
    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String massageError = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massageError));
        }
        for (Event e : events) {
            if (e.getId() == id) {
                e.setDescription(event.getDescription());
                e.setCapacity(event.getCapacity());
                e.setStartDate(event.getStartDate());
                e.setEndDate(event.getEndDate());
                return ResponseEntity.status(200).body(new ApiResponse("Updated successfully"));
            }
        }
        return ResponseEntity.status(401).body(new ApiResponse("Event not found!"));
    }


    //Delete a event
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id) {

        for (Event e : events) {
            if (e.getId() == id) {
                events.remove(e);
                return ResponseEntity.status(200).body(new ApiResponse("Deleted successfully"));
            }
        }
        return ResponseEntity.status(401).body(new ApiResponse("Event not found!"));
    }

    // Change capacity
    @PutMapping("/change/{id}")
    public ResponseEntity changeCapacity(@PathVariable int id, @RequestParam int capacity) {
        for (Event e : events) {
            if (e.getId() == id) {
                e.setCapacity(capacity);
                return ResponseEntity.status(200).body(new ApiResponse("Change capacity successfully to " + capacity));
            }
        }
        return ResponseEntity.status(401).body(new ApiResponse("Event not found!"));
    }

    // Search for a event by given id
    @GetMapping("/getById/{id}")
    public ResponseEntity searchById(@PathVariable int id) {
        for (Event e : events) {
            if (e.getId() == id) {
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(401).body(new ApiResponse("Event not found!"));
    }

}
