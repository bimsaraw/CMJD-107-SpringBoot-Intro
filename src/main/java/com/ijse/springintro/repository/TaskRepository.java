package com.ijse.springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.springintro.entity.Task;

@Repository //same as DAO  
public interface TaskRepository extends JpaRepository<Task, Long>  {
    //custom queries
}
