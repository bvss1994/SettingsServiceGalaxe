package com.pratian.SettingsService;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Repository.DoctorRepository;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

public class DoctorRepositoryTests {
	 @Mock
	 private DoctorRepository dRepo ;
	
//	 @BeforeEach
//	    void initUseCase() {
//	        iSettingsService = new SettingsServiceImpl(dRepo);
//	    }
	 
	 // JUnit test for saveDoctors
@Test
public void savedDoctor() {
	        Doctor Doctors = new Doctor();
	        Doctors.setDoctorName("sajedul");
	        Doctors.setSpeciality("erftg");
	        Doctors.setDoctorMobileSNumber(0173712235);
	        Doctors.setDoctorEmail("sajedul@gmail.com");
	        Doctors.setNpiNumber(89);
	        Doctors.setWorkLocation("rty");
	        

	        // providing knowledge
	        when(dRepo.save(any(Doctor.class))).thenReturn(Doctors);
	       

	        Doctor savedDoctor = dRepo.save(Doctors);
	        assertThat(savedDoctor.getDoctorName()).isNotNull();
	    }
	
	
@Test
public void getDoctorTest(){
     List<Doctor> savedDoctor= dRepo.ViewByDoctorId(1L);
		assertThat(savedDoctor.getClass()).isNotNull();

    }

@Test

public void deleteDoctorTest(){
	 dRepo.deleteById(1L);
	assertThat(dRepo.existsById(1L)).isFalse();
}



//@Test
//
//  public void updateDoctorTest1(){
//	
//      Doctor Doctors1 = dRepo.findById(1L).get();
//      Doctors1.setEmail("galaxy@gmail.com");
//      when(dRepo.save(any(Doctor.class))).thenReturn(Doctors1);
//      Doctor updatedDoctor = dRepo.save(Doctors1);
//      assertThat(Doctors1.getEmail()).isEqualTo("galaxy@gmail.com");
//      //
//      }
//@Test
//public void testForupdateDoctor() {
//	long DoctorId =1;
//	Doctor Doctors = new Doctor();
//	Doctors.setDoctorId(DoctorId);
//	Doctors.setLocation("New York");
//	Mockito.when(dRepo.findById(DoctorId)).thenReturn(Optional.ofNullable(Doctors));
//	Mockito.when(dRepo.save(Doctors)).thenReturn(Doctors);
////	Assert.assertNotNull(Doctors);
////	Assert.assertNotNull(Doctors);
////	Assert.assertNotNull(Doctors.getLocation());
//	assertThat(Doctors.getLocation()).isEqualTo("New York");
//	
//}


//@Test
//public void updatetestDoctor() {
//	String Doctorname="Robert";
//	Doctor Doctors=new Doctor(1,"sam", "dfgj", "erty", "dfg", "yuj",3456, "uio", "ujj", "patiee", true);
////	Doctors.setDoctorId(1);
//	dRepo.save(Doctors);
//	
//	Doctors.setDoctorName(Doctorname);
//	dRepo.save(Doctors);
//	Doctor updatedDoctor = dRepo.findByName(Doctorname);
//	assertThat(updatedDoctor.getDoctorName()).isNotEqualTo("sam");
//}

   


    
}
