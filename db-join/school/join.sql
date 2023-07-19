-- CROSS JOIN
SELECT *
FROM instructor CROSS JOIN lecture;

SELECT *
FROM lecture, student;

SELECT *
FROM lecture, instructor
WHERE lecture.instructor_id = instructor.id;
