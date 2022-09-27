package com.pratian.SettingsService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratian.SettingsService.Entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query(value="select * from patient where patientId=:pid",nativeQuery =true)
	public List<Patient> ViewByPatientId(@Param(value="pid")long pid);
	
	@Query(value="select * from patient where patientName=:pname",nativeQuery =true)
	public Patient findByName(@Param(value="pname")String pname);
}
