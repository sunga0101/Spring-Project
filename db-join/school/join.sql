-- CROSS JOIN
SELECT *
FROM instructor CROSS JOIN lecture;

SELECT *
FROM lecture, student;

SELECT *
FROM lecture, instructor
WHERE lecture.instructor_id = instructor.id;

-- INNER JOIN
SELECT *
FROM lecture INNER JOIN instructor
                        ON lecture.instructor_id = instructor.id;

SELECT *
FROM student INNER JOIN instructor
                        ON student.advisor_id = instructor.id;

SELECT *
FROM student INNER JOIN enrolling_lectures
                        ON student.id = enrolling_lectures.student_id;

-- INNER 생략
SELECT *
FROM lecture JOIN instructor
                  ON lecture.instructor_id = instructor.id;
