package com.kgisl.resttemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kgisl.resttemplate.model.College;
import com.kgisl.resttemplate.model.ResponseData;
import com.kgisl.resttemplate.model.Student;

@RestController
public class StudentController {

    @GetMapping("/student/{id}")
     public ResponseEntity<ResponseData> getStudentById(@PathVariable("id") int id){
         ResponseData rd = new ResponseData();
         Student s = new Student();
         s.setStudentid(11);
         s.setStudentname("nk");
          s.setAddress("Rjpm");

          s.setCollegeId(101);
            rd.setStudent(s);
             int cId=s.getCollegeId();
              RestTemplate rt = new RestTemplate();
               String ep = "http://localhost:8080/college/"+cId;
               ResponseEntity<College> data=rt.getForEntity(ep, College.class, cId);
                if(data.getStatusCodeValue()==200){
                    College college=data.getBody();
                    rd.setCollege(college);
                 }
                  return new ResponseEntity<ResponseData>(rd,HttpStatus.OK); }

}
