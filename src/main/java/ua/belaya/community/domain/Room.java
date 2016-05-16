package ua.belaya.community.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Belaya
 */

@Entity
@Table(name = "rooms")
public class Room {

    @Pattern(regexp = "[A-Za-z0-9-]+")
    @Id
    @Column(name = "room_number")
    private String number;

    @Min(2)
    @Max(5)
    @Column(name = "room_capacity")
    private Integer capacity = 2;

    @OneToMany(mappedBy = "room")
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(number);
        builder.append('(').append(students.size()).append('/').append(capacity).append(')');
        return builder.toString();
    }

    public Room() { }

    public Room(String number, Integer capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    //getters and setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
