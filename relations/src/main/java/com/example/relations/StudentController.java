package com.example.relations;


import com.example.relations.repo.LectureRepository;
import com.example.relations.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;



}
