package com.krzysieks.soap_wevservices.soap.service;

import com.krzysieks.soap_wevservices.soap.bean.Course;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseService {

    private static List<Course> listOfCourses = new ArrayList<Course>() {{
    add(new Course(1, "Spring", "Spring Course"));
    add(new Course(2, "SpringBoot", "SpringBoot Course"));
    add(new Course(3, "PostgreSql", "PostgreSql Course"));
    add(new Course(4, "Maven", "Maven Course"));
    }};

    public Course findById(int id) {
        if (!CollectionUtils.isEmpty(listOfCourses)) {
            return listOfCourses.stream()
                    .filter(course -> course.getId() == id)
                    .findAny()
                    .get();
        }
        return null;
    }

    public List<Course> findAll() {
        if (listOfCourses != null) {
            return listOfCourses;
        } else {
            return new ArrayList<>();
        }
    }

    public int deleteById(int id) {
        Iterator<Course> iterator = listOfCourses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                System.out.println("Course is deleted");
                return 1;
            }
        }
        System.out.println("ERROR: course is not deleted");
        return 0;
    }

}
