package com.pratian.SettingsService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratian.SettingsService.Entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	@Query(value="select * from doctor where doctorId=:did",nativeQuery =true)
	public List<Doctor> ViewByDoctorId(@Param(value="did")long did);
}
