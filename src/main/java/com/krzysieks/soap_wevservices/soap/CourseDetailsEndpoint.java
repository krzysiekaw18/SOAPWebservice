package com.krzysieks.soap_wevservices.soap;

import com.in28minutes.courses.*;
import com.krzysieks.soap_wevservices.soap.bean.Course;
import com.krzysieks.soap_wevservices.soap.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.List;


@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    private CourseService courseService;

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course courseById = courseService.findById(request.getId());
        return mapCourseDetails(courseById);
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest() {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<Course> allCourses = courseService.findAll();

        allCourses.forEach(course -> {
            response.getCourseDetails().add(mapCourseToCourseDetails(course));
        });
        return response;
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processDeleteCourse(@RequestPayload DeleteCourseDetailsRequest request) {
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        int status = courseService.deleteById(request.getId());
        response.setStatus(status);
        return response;
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourseToCourseDetails(course));
        return response;
    }

    private CourseDetails mapCourseToCourseDetails(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
}
