package ua.belaya.community.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.belaya.community.domain.Role;
import ua.belaya.community.domain.Student;

import java.util.List;

/**
 * @author Anna Belaya
 */

public interface StudentJpaRepository extends JpaRepository<Student, Long>{
    Student findStudentByEmail(String email);
    List<Student> findStudentByRole(Role role, Sort sort);
}
