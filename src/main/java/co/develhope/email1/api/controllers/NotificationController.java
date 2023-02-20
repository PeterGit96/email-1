package co.develhope.email1.api.controllers;

import co.develhope.email1.emails.services.EmailService;
import co.develhope.email1.api.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import co.develhope.email1.students.entities.Student;
import co.develhope.email1.students.services.StudentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NotificationController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO payload) {
        try {
            Student studentToNotify = studentService.getStudentById(payload.getContactId());
            System.out.println("studentToNotify: " + studentToNotify);
            if(studentToNotify == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("I couldn't find the student :(");
            }
            emailService.sendWithSendGrid(studentToNotify.getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.status(HttpStatus.OK).body("Email sent to address: " + studentToNotify.getEmail());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The server is broken :(");
        }
    }

}
