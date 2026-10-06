package com.example.mvc.controller;

import com.example.mvc.dto.EmplyeeDTO;
import com.example.mvc.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity< List<EmplyeeDTO>> getEmployees(@RequestParam(required = false) Integer age,
                                                        @RequestParam(required = false) String name){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping(path="/{employeeId}")
    public ResponseEntity< EmplyeeDTO> getEmployeesById( @PathVariable long employeeId){

        Optional<EmplyeeDTO> employeeDTO= employeeService.getEmployeeById(employeeId);
        return employeeDTO.map(empDTO -> ResponseEntity.ok(empDTO))
                         .orElseThrow(()-> new NoSuchElementException("employee not found"));

    }



    @PostMapping
    public ResponseEntity< EmplyeeDTO> createEmployee(@RequestBody @Valid EmplyeeDTO dto){
//        return ResponseEntity.ok(employeeService.createEmployee(dto));
        //or
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }
    @PostMapping(path="/clear")
    public ResponseEntity <String> clearEmployees(){
        return ResponseEntity.ok(employeeService.clearEmployees());
    }
    @PatchMapping(path="/{employeeId}")
    public ResponseEntity< EmplyeeDTO> UpdateEmployee(@PathVariable long employeeId, @RequestBody Map<String, Object> data){

        EmplyeeDTO updatedEmployee=  employeeService.UpdateEmployee(employeeId, data);
        if(updatedEmployee == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(updatedEmployee);

    }


}
