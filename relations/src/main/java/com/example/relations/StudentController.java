package com.example.relations;


import com.example.relations.entity.LectureEntity;
import com.example.relations.entity.StudentEntity;
import com.example.relations.repo.LectureRepository;
import com.example.relations.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    @PutMapping("{id}/lectures/{lectureId}")
    public void updateStudentLectures(
            @PathVariable("id") Long id,
            @PathVariable("lectureId") Long lectureId
    ){
        Optional<StudentEntity> optionalStudent
                = studentRepository.findById(id);
        if (optionalStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        StudentEntity student = optionalStudent.get();

        Optional<LectureEntity> optionalLecture
                = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        LectureEntity lecture = optionalLecture.get();

        student.getAttending().add(lecture); // 듣는 과목 추가
        studentRepository.save(student);

    }


}
