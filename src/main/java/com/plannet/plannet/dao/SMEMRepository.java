package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SCAL;
import com.plannet.plannet.entity.SMEM;


<<<<<<< HEAD
<<<<<<< HEAD
import com.plannet.plannet.entity.SMEMPK;
=======
>>>>>>> origin/master
=======
>>>>>>> origin/master
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
<<<<<<< HEAD
public interface SMEMRepository extends JpaRepository<SMEM, SMEMPK> {
=======
public interface SMEMRepository extends JpaRepository<SMEM, Long> {
>>>>>>> origin/master
=======
public interface SMEMRepository extends JpaRepository<SMEM, Long> {
>>>>>>> origin/master

}
