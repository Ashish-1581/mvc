package com.example.mvc.services;

import com.example.mvc.dto.EmplyeeDTO;
import com.example.mvc.entities.EmployeeEntity;
import com.example.mvc.repositories.EmployeeRepository;
import org.springframework.util.ReflectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    EmployeeService(ModelMapper mapper, EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmplyeeDTO> getEmployeeById(long employeeId){
//        Optional<EmployeeEntity> EmployeeEntity=employeeRepository.findById(employeeId);
//        return EmployeeEntity.map(employeeEntity -> mapper.map(employeeEntity, EmplyeeDTO.class));
        //or
        return employeeRepository.findById(employeeId).map(employeeEntity -> mapper.map(employeeEntity, EmplyeeDTO.class));
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

    public EmplyeeDTO UpdateEmployee(long employeeId, Map<String, Object> data) {
        try{
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists){
            return null;
        }
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).orElse(null);
        data.forEach((key,value)->{
            Field requiredKey= ReflectionUtils.findField(EmployeeEntity.class,key);
            requiredKey.setAccessible(true); //this is required to access private fields
            ReflectionUtils.setField(requiredKey,employeeEntity,value);
        });
        EmployeeEntity updatedEmployee=employeeRepository.save(employeeEntity);
        return mapper.map(updatedEmployee, EmplyeeDTO.class);}
        catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
