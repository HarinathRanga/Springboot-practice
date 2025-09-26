package com.harinath.Homework2.repository;

import com.harinath.Homework2.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
