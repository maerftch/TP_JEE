package com.example.mvcpart;

import com.example.mvcpart.entities.Patient;
import com.example.mvcpart.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MvcpartApplication {

	public static void main(String[] args) {

		SpringApplication.run(MvcpartApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args->{
			patientRepository.save(new Patient(null,"koko",new Date(),false,12));
			patientRepository.save(new Patient(null,"jojo",new Date(),true,12));
			patientRepository.save(new Patient(null,"toto",new Date(),false,12));
			patientRepository.save(new Patient(null,"lolo",new Date(),true,12));
			patientRepository.findAll().forEach((p->{System.out.println(p.getNom());

			}));
		};

	}

}
