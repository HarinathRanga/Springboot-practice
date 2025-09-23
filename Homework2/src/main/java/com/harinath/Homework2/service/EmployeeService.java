package com.harinath.Homework2.service;

import com.harinath.Homework2.DTO.EmployeeDTO;
import com.harinath.Homework2.Entity.EmployeeEntity;
import com.harinath.Homework2.exceptions.ResourceNotFoundException;
import com.harinath.Homework2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private ModelMapper mapper;

    public EmployeeService(ModelMapper mapper){
        this.mapper = mapper;
    }

    public void isEmployeeExistById(Long employeeId) {
        boolean isExist = employeeRepository.existsById(employeeId);
        if(!isExist) throw new ResourceNotFoundException("Employee not found with id : "+ employeeId);
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employee) {
        EmployeeEntity entity = employeeRepository.save(mapper.map(employee, EmployeeEntity.class));
        return mapper.map(entity,EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class));
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isEmployeeExistById(employeeId);
        EmployeeEntity employeeEntity = mapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return mapper.map(updatedEmployee,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isEmployeeExistById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updateEmployeePartialById(Long employeeId,Map<String,Object> updates) {
        isEmployeeExistById(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return mapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
