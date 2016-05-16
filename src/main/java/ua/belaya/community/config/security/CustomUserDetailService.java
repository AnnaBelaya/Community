package ua.belaya.community.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.belaya.community.domain.Student;
import ua.belaya.community.repository.StudentJpaRepository;

/**
 * @author Anna Belaya
 */

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private StudentJpaRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Student student = studentRepository.findStudentByEmail(s);

        if (student == null) {
            throw new UsernameNotFoundException(String.format("Email '%s' not found", s));
        }

        return new SecurityUser(student);
    }
}
