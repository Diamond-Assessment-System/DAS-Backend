package com.project.DASBackend.repository;

import com.project.DASBackend.entity.Seal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SealRepository extends JpaRepository<Seal, Integer> {
}
