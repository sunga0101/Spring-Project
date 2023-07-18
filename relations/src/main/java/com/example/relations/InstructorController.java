package com.example.relations;

import com.example.relations.entity.InstructorEntity;
import com.example.relations.entity.LectureEntity;
import com.example.relations.repo.InstructorRepository;
import com.example.relations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorRepository instructorRepository;


    @GetMapping("/{id}/lectures")
    public void readInstructorLectures(
            @PathVariable("id") Long id
    ){
        Optional<InstructorEntity> optionalInstructor =
                instructorRepository.findById(id);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        InstructorEntity instructor = optionalInstructor.get();
        for (LectureEntity lecture : instructor.getLectures())
            log.info(lecture.getName());
    }
}
