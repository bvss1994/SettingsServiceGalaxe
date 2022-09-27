package com.pratian.SettingsService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratian.SettingsService.Entities.ProfilePic;
@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long>{

	@Query(value="select p from ProfilePic p where p.doctorId=:did")
	Optional<ProfilePic> findByDoctorId(@Param(value="did")long did);
	
	@Query(value="select p1 from ProfilePic p1 where p1.patientId=:pid")
	Optional<ProfilePic> findByPatientId(@Param(value="pid")long pid);

	@Query(value="select p.image_id from ProfilePic p where p.patientId=:patient_id")
	public long existsByPatientId(@Param(value="patient_id")long patient_id);

	@Query(value="select p.image_id from ProfilePic p where p.doctorId=:doctor_id")
	public long existsByDoctorId(@Param(value="doctor_id")long doctor_id);
	
}
