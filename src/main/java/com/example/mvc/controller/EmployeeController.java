package com.example.mvc.controller;

import com.example.mvc.dto.EmplyeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping
    public String getEmployees(@RequestParam(required = false) Integer age,
    @RequestParam(required = false) String name){
        return "employees with age " + age + " and name " + name;
    }

    @GetMapping(path="/{employeeId}")
    public EmplyeeDTO getEmployeesById( @PathVariable long employeeId){
        return new EmplyeeDTO(employeeId,"Ashish","abc@gmail",22,LocalDate.of(2025,4,9),true);
    }
    @PostMapping
    public EmplyeeDTO createEmployee(@RequestBody EmplyeeDTO dto){
        dto.setId(1L);
        return dto;
    }


}
