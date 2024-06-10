package com.alpcode.cruddemo.dao;

import com.alpcode.cruddemo.entity.Course;
import com.alpcode.cruddemo.entity.Instructor;
import com.alpcode.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for Entity Manager
    private EntityManager entityManager;

    // inject Entity Manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public void save(Instructor instructor) {
        // This will also save the details object
        // Because of CascadeType.ALL

        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        // This will ALSO retrieve the instructor details object
        // Because of default behaviour of @OneToOne fetch type is eager...
        return entityManager.find(Instructor.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorById(int id) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        // if you don't remove the instructor from courses you can not delete instructor
        // because of foreign key - constraint violation
        for (Course c:courses){
            c.setInstructor(null);
        }
        // only delete the instructor not the associated courses based on cascade types
        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(int id) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, id);

        // remove the associate object reference
        // break bi-directional link
        //
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // create query
        // it will retrieve instructor and courses
        TypedQuery <Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        +"where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        // execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        // retrieve the courses
        Course course = entityManager.find(Course.class, id);

        //delete the course
        entityManager.remove(course);
    }

    @Transactional
    @Override
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.reviews " +
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }
}
