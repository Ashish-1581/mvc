package com.example.mvc.controller;

import com.example.mvc.dto.EmplyeeDTO;
import com.example.mvc.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmplyeeDTO> getEmployees(@RequestParam(required = false) Integer age,
                                         @RequestParam(required = false) String name){
        return employeeService.getEmployees();
    }

    @GetMapping(path="/{employeeId}")
    public EmplyeeDTO getEmployeesById( @PathVariable long employeeId){
        return employeeService.getEmployeeById(employeeId);

    }
    @PostMapping
    public EmplyeeDTO createEmployee(@RequestBody EmplyeeDTO dto){
        return employeeService.createEmployee(dto);
    }
    @PostMapping(path="/clear")
    public String clearEmployees(){
        return employeeService.clearEmployees();
    }


}
