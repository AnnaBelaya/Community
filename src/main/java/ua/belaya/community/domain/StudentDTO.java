package ua.belaya.community.domain;

/**
 * @author Anna Belaya
 */
public class StudentDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String roomNumber;
    private String email;

    public StudentDTO(Student student) {
        id = student.getId();
        lastName = student.getLastName();
        firstName = student.getFirstName();
        patronymic = student.getPatronymic();

        Room room = student.getRoom();

        if (room != null) {
            roomNumber = room.getNumber();
        }

        email = student.getEmail();
    }

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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
