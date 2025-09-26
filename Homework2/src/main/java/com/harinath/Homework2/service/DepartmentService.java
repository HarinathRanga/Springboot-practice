package com.harinath.Homework2.service;

import com.harinath.Homework2.DTO.DepartmentDTO;
import com.harinath.Homework2.Entity.DepartmentEntity;
import com.harinath.Homework2.exceptions.DepartmentNotExistException;
import com.harinath.Homework2.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    DepartmentService(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.
                stream()
                .map( departmentEntity -> mapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = mapper.map(departmentDTO,DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        return mapper.map(savedEntity,DepartmentDTO.class);
    }

    public DepartmentDTO putDepartmentById(DepartmentDTO departmentDTO) {
        isDepartmentExistById(departmentDTO.getId());
        DepartmentEntity existingEntity = departmentRepository.findById(departmentDTO.getId()).get();
        departmentDTO.setCreatedAt(existingEntity.getCreatedAt());
        DepartmentEntity changedDepartmentEntity = mapper.map(departmentDTO,DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(changedDepartmentEntity);
        return mapper.map(savedDepartmentEntity,DepartmentDTO.class);
    }

    public Boolean deleteDepartmentBYId(Long id) {
        isDepartmentExistById(id);
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        isDepartmentExistById(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        return mapper.map(departmentEntity,DepartmentDTO.class);
    }
    private void isDepartmentExistById(Long id) {
        boolean isExist = departmentRepository.existsById(id);
        if(!isExist) throw new DepartmentNotExistException("Department not exist with Id : "+id);
    }

}
