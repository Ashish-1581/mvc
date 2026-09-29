package com.example.mvc.services;

import com.example.mvc.dto.EmplyeeDTO;
import com.example.mvc.entities.EmployeeEntity;
import com.example.mvc.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    EmployeeService(ModelMapper mapper, EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
    }

    public EmplyeeDTO getEmployeeById(long employeeId){
        EmployeeEntity EmployeeEntity=employeeRepository.findById(employeeId).orElse(null);

       return mapper.map(EmployeeEntity, EmplyeeDTO.class);


    }

    public List<EmplyeeDTO> getEmployees() {
        List<EmployeeEntity>Employees=employeeRepository.findAll();
      return  Employees.stream().map(employeeEntity -> mapper.map(employeeEntity, EmplyeeDTO.class)).toList();

    }

    public EmplyeeDTO createEmployee(EmplyeeDTO dto) {
        EmployeeEntity employeeEntity=mapper.map(dto, EmployeeEntity.class);
        EmployeeEntity savedEmployee=employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployee, EmplyeeDTO.class);

    }

    public String clearEmployees() {
        employeeRepository.deleteAll();
        return "Employees has been cleared";
    }
}
