package com.harinath.Homework2.controllers;

import com.harinath.Homework2.DTO.DepartmentDTO;
import com.harinath.Homework2.DTO.PasswordDTO;
import com.harinath.Homework2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping()
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        departmentDTO.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO),HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<DepartmentDTO> putDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.putDepartmentById(departmentDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteDepartmentByBody(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.deleteDepartmentBYId(departmentDTO.getId()),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        return new ResponseEntity<>(departmentService.deleteDepartmentBYId(departmentId),HttpStatus.OK);
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @GetMapping(path = "/passwordCheck")
    public ResponseEntity<Boolean> validatePassword(@Valid @RequestBody PasswordDTO dto){
        return ResponseEntity.ok(true);
    }


}
