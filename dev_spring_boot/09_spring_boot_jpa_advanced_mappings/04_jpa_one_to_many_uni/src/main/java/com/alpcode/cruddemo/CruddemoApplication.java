package com.alpcode.cruddemo;

import com.alpcode.cruddemo.dao.AppDAO;
import com.alpcode.cruddemo.entity.Course;
import com.alpcode.cruddemo.entity.Instructor;
import com.alpcode.cruddemo.entity.InstructorDetail;
import com.alpcode.cruddemo.entity.Review;
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

	// commandLineRunner is from the Spring boot framework
	// This little snippet of code here will be
	// executed after the Spring beans have been loaded ***
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);
			//createCourseAndReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
			System.out.println("Hello World");
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id: " + id);
		// it will delete course and reviews because of Cascade Type ALL
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		// print the course
		System.out.println(course);
		// print the reviews
		System.out.println(course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course course = new Course("Computer Science - Beginner");
		// create some reviews
		course.addReview(new Review("Awesome course... loved it"));;
		course.addReview(new Review("not bad..."));;
		course.addReview(new Review("Cool I liked it"));;
		course.addReview(new Review("stupid course same as instructor"));
		// save the course... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDAO.save(course);
		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		// find the course
		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);

		// update the course
		System.out.println("Updating course id: " +id);
		course.setTitle("Acoustic Piano - Normal");
		appDAO.update(course);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		// Update the instructor
		System.out.println("Updating instructor id: " + id + "-" + instructor.getId());
		instructor.setLastName("Testergarfield");

		appDAO.update(instructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id:" + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + id);
		List<Course> courses =appDAO.findCoursesByInstructorId(id);

		// associate the objects
		instructor.setCourses(courses);
		
		System.out.println("the associated courses:" + instructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id:" + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);
		System.out.println("associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create instructor
		Instructor tempInstructor = new Instructor("Cat", "Garfield","Cat@Garfield.com");

		// create instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("Garfield.youtube.com", "eats lasagna");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Create some courses
		Course tempCourse1 = new Course("Acoustic Piano - For Beginner");
		Course tempCourse2 = new Course("Football - For Advanced");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// This will ALSO save the courses
		// because of CascadeType.PERSIST
		//
		System.out.println("saving instructor:" + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		System.out.println("Deleting instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get instructor detail object
		int id = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
		// print the instructor detail
		System.out.println("finding instructor detail id: " + tempInstructorDetail);
		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done!");
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
