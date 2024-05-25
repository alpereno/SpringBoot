package com.alpcode.cruddemo;

import com.alpcode.cruddemo.dao.StudentDAO;
import com.alpcode.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
			System.out.println("Hello World");
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Delete row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 2;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student student = studentDAO.findById(studentId);
		// change first name to "Sonic"
		System.out.println("Updating student...");
		student.setFirstName("John");

		// update the student
		studentDAO.update(student);
		// display the updated student
		System.out.println("Updated student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Runner");
		// display list of students
		for (Student currentStudent:theStudents){
			System.out.println(currentStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student currentStudent:theStudents){
			System.out.println(currentStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student student = new Student("Road", "Runner", "roadrunner@speed.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		int theId = student.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id:" + theId);
		Student newStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + newStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 student object...");
		Student student1 = new Student("John", "Doe","johndoe@mail.com");
		Student student2 = new Student("Dean", "private","johnprivate@mail.com");
		Student student3 = new Student("Tom", "Vik","tomvik@mail.com");
		// save the student objects
		System.out.println("saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student student = new Student("Paul", "Doe","pauldoe@mail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);
		// display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
