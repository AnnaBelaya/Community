package ua.belaya.community.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Anna Belaya
 */

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "student_last_name")
    private String lastName;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "student_first_name")
    private String firstName;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "student_patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "student_room_number")
    private Room room;

    //for security reasons
    @Email
    @Column(name = "student_email", unique = true)
    private String email;

    @Size(min = 4, max = 20)
    @Column(name = "student_password")
    private String password = "0000";

    @Enumerated(value = EnumType.STRING)
    @Column(name = "student_role")
    private Role role = Role.USER;

    public Student() {
    }

    public Student(String lastName, String firstName, String patronymic, Room room) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.room = room;
        setEmail((firstName.substring(0, 1) + "." + lastName + "@box.com").toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(lastName).
                append(' ').
                append(firstName.substring(0, 1)).
                append('.').
                append(patronymic.substring(0, 1)).append('.');
        return builder.toString();
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
