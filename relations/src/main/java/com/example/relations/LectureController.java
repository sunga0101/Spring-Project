package com.example.relations;

import com.example.relations.entity.InstructorEntity;
import com.example.relations.entity.LectureEntity;
import com.example.relations.repo.InstructorRepository;
import com.example.relations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사를 배정한다
    @PutMapping("{id}/instructor/{instructorId}")
    // 응답 바디가 없을 것이다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLectureInstructor(
            @PathVariable("id") Long id,
            @PathVariable("instructorId") Long instructorID
    ) {
        Optional<LectureEntity> optionalLecture
                = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<InstructorEntity> optionalInstructor
                = instructorRepository.findById(instructorID);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        LectureEntity lecture = optionalLecture.get();
        InstructorEntity instructor = optionalInstructor.get();
        // 그냥 Java 객체 쓰듯이
        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }


    // id 강의에 대한 강사를 반환하는 엔드포인트
    @GetMapping("{id}/instructor")
    public String readLectureInstructor(@PathVariable("id") Long id){
        Optional<LectureEntity> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return optionalLecture.get().getInstructor().toString();
    }


}
