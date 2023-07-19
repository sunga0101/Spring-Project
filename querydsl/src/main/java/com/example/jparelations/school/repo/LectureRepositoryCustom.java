package com.example.jparelations.school.repo;

import com.example.jparelations.school.entity.Lecture;

import java.util.List;

public interface LectureRepositoryCustom { // 이름 잘 넣어주어야 한다 (~~RepositoryCustom 형태)
    List<Lecture> lectureByTime(String day, Integer startTime, Integer endTime);
}
