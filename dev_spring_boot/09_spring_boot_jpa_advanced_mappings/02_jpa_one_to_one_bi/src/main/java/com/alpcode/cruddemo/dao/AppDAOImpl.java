package com.alpcode.cruddemo.dao;

import com.alpcode.cruddemo.entity.Instructor;
import com.alpcode.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        // delete the instructor
        entityManager.remove(tempInstructor);
        // Delete instructor and associated instructor details object
        // because of Cascade.Type.ALL
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
}
