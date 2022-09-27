package com.pratian.SettingsService;

import org.assertj.core.api.Assertions;
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
import com.pratian.SettingsService.Entities.Patient;
import com.pratian.SettingsService.Repository.PatientRepository;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

public class PatientRepositoryTests {
	@Mock
	private PatientRepository pRepo;

	@Test
	public void savedPatient() {
		Patient patients = new Patient();
		patients.setPatientName("sajedul");
		patients.setPatientAge(76);
		patients.setPatientGender("f");
		patients.setDOB("12/09/1998");
		patients.setPatientMobileNumber(0173712235);
		patients.setPatientHeight(4);
		patients.setAllergies("dfrtyu");
		patients.setEmail("sajedul@gmail.com");
		patients.setPatientBloodGroup("AB+");
		patients.setMedicalProblems("cold");
		patients.setAddress("Pune");
		patients.setActiveIssues("rtyjkh");

		// providing knowledge
		when(pRepo.save(any(Patient.class))).thenReturn(patients);

		Patient savedPatient = pRepo.save(patients);
		assertThat(savedPatient.getPatientName()).isNotNull();
	}

	@Test
	public void getPatientTest() {
		List<Patient> savedPatient = pRepo.ViewByPatientId(10);
		assertThat(savedPatient.getClass()).isNotNull();

	}

	@Test

	public void deletePatientTest() {
		pRepo.deleteById(1L);
		assertThat(pRepo.existsById(1L)).isFalse();
	}

	@Test

	public void updatePatientTest2() {

		Patient patients = pRepo.findById(1L).get();

		patients.setPatientMobileNumber(987678433);

		Patient patientUpdated = pRepo.save(patients);

		Assertions.assertThat(patientUpdated.getPatientMobileNumber()).isEqualTo(987678433);
	}
//@Test
//
//  public void updatePatientTest1(){
//	
//      Patient patients1 = pRepo.findById(1L).get();
//      patients1.setEmail("galaxy@gmail.com");
//      when(pRepo.save(any(Patient.class))).thenReturn(patients1);
//      Patient updatedPatient = pRepo.save(patients1);
//      assertThat(patients1.getEmail()).isEqualTo("galaxy@gmail.com");
//      //
//      }
//@Test
//public void testForupdatePatient() {
//	long patientId =1;
//	Patient patients = new Patient();
//	patients.setPatientId(patientId);
//	patients.setLocation("New York");
//	Mockito.when(pRepo.findById(patientId)).thenReturn(Optional.ofNullable(patients));
//	Mockito.when(pRepo.save(patients)).thenReturn(patients);
////	Assert.assertNotNull(patients);
////	Assert.assertNotNull(patients);
////	Assert.assertNotNull(patients.getLocation());
//	assertThat(patients.getLocation()).isEqualTo("New York");
//	
//}

//@Test
//public void updatetestpatient() {
//	String patientname="Robert";
//	Patient patients=new Patient(1,"sam", "dfgj", "erty", "dfg", "yuj",3456, "uio", "ujj", "patiee", true);
////	patients.setPatientId(1);
//	pRepo.save(patients);
//	
//	patients.setPatientName(patientname);
//	pRepo.save(patients);
//	Patient updatedPatient = pRepo.findByName(patientname);
//	assertThat(updatedPatient.getPatientName()).isNotEqualTo("sam");
//}

}
