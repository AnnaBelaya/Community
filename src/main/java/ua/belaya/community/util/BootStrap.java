package ua.belaya.community.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.belaya.community.domain.Role;
import ua.belaya.community.domain.Room;
import ua.belaya.community.domain.Student;
import ua.belaya.community.repository.RoomJpaRepository;
import ua.belaya.community.repository.StudentJpaRepository;

import javax.annotation.PostConstruct;

/**
 * @author Anna Belaya
 */

@Component
public class BootStrap {

    @Autowired
    private StudentJpaRepository studentRepository;

    @Autowired
    private RoomJpaRepository roomRepository;

    @PostConstruct
    public void init() {
        Room r1 = new Room("100", 4);
        Room r2 = new Room("101", 3);
        Room r3 = new Room("103", 3);

        roomRepository.save(r1);
        roomRepository.save(r2);
        roomRepository.save(r3);

        Student s1 = new Student("Harrison", "George", "Maria", r1);
        Student s2 = new Student("Parker", "Peter", "Willis", r2);
        Student s3 = new Student("Green", "Michael", "Doug", null);
        Student s4 = new Student("Hanks", "Thomas", "Bill", r2);
        Student s5 = new Student("Dorian", "John", "Pit", r2);

        Student admin = new Student("Smith", "Maria", "Hanna", null);
        admin.setRole(Role.ADMIN);

        System.err.println("----------------------------" + admin.getEmail());

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        studentRepository.save(s4);
        studentRepository.save(s5);
        studentRepository.save(admin);
    }
}
