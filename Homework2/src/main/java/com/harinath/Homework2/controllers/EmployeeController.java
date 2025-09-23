package com.harinath.Homework2.controllers;

import com.harinath.Homework2.DTO.EmployeeDTO;
import com.harinath.Homework2.exceptions.ResourceNotFoundException;
import com.harinath.Homework2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeData(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(employeeDTO -> ResponseEntity.ok(employeeDTO))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : "+employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean isEmployeeDeleted = employeeService.deleteEmployeeById(employeeId);
        if(isEmployeeDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> partialUpdateEmployee(@PathVariable Long employeeId, @RequestBody Map<String,Object> updates) {
        EmployeeDTO employeeDTO = employeeService.updateEmployeePartialById(employeeId,updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
