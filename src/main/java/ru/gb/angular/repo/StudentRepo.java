package ru.gb.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.angular.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
