package com.alpcode.cruddemo;

import com.alpcode.cruddemo.dao.AppDAO;
import com.alpcode.cruddemo.entity.Instructor;
import com.alpcode.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// commandLineRunner is from the Spring boot framework
	// This little snippet of code here will be
	// executed after the Spring beans have been loaded ***
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			deleteInstructor(appDAO);
			System.out.println("Hello World");
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
		/*
		// create instructor
		Instructor tempInstructor = new Instructor("example", "exampleSurname","example@example.com");

		// create instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("exampleDetail.youtube.com", "act like a fool");
		*/

		// create instructor
		Instructor tempInstructor = new Instructor("Jack", "Aku","Aku@Jack.com");

		// create instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("Jaku.youtube.com", "swing the sword");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: This will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

}
