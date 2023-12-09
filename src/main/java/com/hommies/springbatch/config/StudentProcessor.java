package com.hommies.springbatch.config;

import com.hommies.springbatch.model.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {
    @Override
    public Student process(Student student) throws Exception {

        student.setId(null);
        student.setAge(student.getAge()/100);
        // all business logic should be here..
        return student;
    }
}
