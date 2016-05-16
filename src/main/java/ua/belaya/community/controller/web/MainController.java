package ua.belaya.community.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.belaya.community.config.security.SecurityUser;
import ua.belaya.community.domain.Role;
import ua.belaya.community.domain.Room;
import ua.belaya.community.domain.Student;
import ua.belaya.community.repository.RoomJpaRepository;
import ua.belaya.community.repository.StudentJpaRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Anna Belaya
 */

@Controller
@RequestMapping("/web")
public class MainController {

    @Autowired
    private StudentJpaRepository studentRepository;

    @Autowired
    private RoomJpaRepository roomRepository;

    @ModelAttribute("authenticatedUser")
    public Student authenticatedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getName();
        return studentRepository.findStudentByEmail(email);
    }

    @ModelAttribute
    public Room emptyRoom() {
        return new Room();
    }

    @RequestMapping("/students")
    public ModelAndView students() {
        Sort sort = new Sort("room.number", "lastName", "firstName", "patronymic");
        List<Student> students = studentRepository.findStudentByRole(Role.USER, sort);
        return new ModelAndView("students", "students", students);
    }

    @RequestMapping("/students/{id}")
    public ModelAndView student(@PathVariable("id") Long id) {
        Student student = studentRepository.findOne(id);
        return new ModelAndView("student", "student", student);
    }

    @RequestMapping("/students/{id}/profile")
    public ModelAndView studentProfile(@PathVariable("id") Long id,
                                       @ModelAttribute("authenticatedUser") Student authenticated) {
        //prevent accessing profile of other user
        Student actual = studentRepository.findOne(id);
        if (!actual.getEmail().equals(authenticated.getEmail())) {
            return new ModelAndView("redirect:/web/students/" + id);
        }
        return new ModelAndView("studentprofile", "student", studentRepository.findOne(id));
    }

    @RequestMapping(value = "/students/{id}/profile", method = RequestMethod.POST)
    public ModelAndView studentProfileProcess(@PathVariable("id") Long id,
                                              @Valid @ModelAttribute("student") Student student,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("studentprofile");
        }
        Student actual = studentRepository.findOne(id);

        student.setRoom(actual.getRoom());
        student.setRole(actual.getRole());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        userDetails.setEmail(student.getEmail());

        studentRepository.save(student);
        return new ModelAndView("redirect:/web/students/" + id);
    }

    @RequestMapping("/rooms")
    public ModelAndView rooms() {
        Sort sort = new Sort("number");
        List<Room> rooms = roomRepository.findAll(sort);
        return new ModelAndView("rooms", "rooms", rooms);
    }

    @RequestMapping("/rooms/{number}")
    public ModelAndView room(@PathVariable("number") String number) {
        Room room = roomRepository.findOne(number);
        return new ModelAndView("room", "room", room);
    }

    @RequestMapping("/rooms/{number}/book")
    public String bookRoom(@PathVariable("number") String number,
                           @ModelAttribute("authenticatedUser") Student student) {
        Room room = roomRepository.findOne(number);
        if (room.getCapacity() > room.getStudents().size()) {
            student.setRoom(room);
            studentRepository.save(student);
        }

        return "redirect:/web/rooms/" + number;
    }

    @RequestMapping("/rooms/{number}/remove")
    public String removeRoom(@PathVariable("number") String number,
                             @ModelAttribute("authenticatedUser") Student student) {
        if (student.getRole().equals(Role.ADMIN)) {
            Room room = roomRepository.getOne(number);
            for(Student s : room.getStudents()) {
                s.setRoom(null);
                studentRepository.save(s);
            }
            roomRepository.delete(room);
        }

        return "redirect:/web/rooms";
    }

    @RequestMapping("/rooms/{number}/clear")
    public String clearRoom(@PathVariable("number") String number,
                             @ModelAttribute("authenticatedUser") Student student) {
        if (student.getRole().equals(Role.ADMIN)) {
            Room room = roomRepository.getOne(number);
            for(Student s : room.getStudents()) {
                s.setRoom(null);
                studentRepository.save(s);
            }
        }

        return "redirect:/web/rooms";
    }

    @RequestMapping("/rooms/add")
    public ModelAndView addRoom() {
        return new ModelAndView("addroom");
    }

    @RequestMapping(value = "/rooms/add", method = RequestMethod.POST)
    public ModelAndView addRoomProcess(@Valid @ModelAttribute("room") Room room,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("addroom");
        }

        roomRepository.save(room);
        return new ModelAndView("redirect:/web/rooms");
    }

    @RequestMapping("/admins")
    public ModelAndView admins() {
        Sort sort = new Sort("lastName", "firstName", "patronymic");
        List<Student> admins = studentRepository.findStudentByRole(Role.ADMIN, sort);
        return new ModelAndView("admins", "admins", admins);
    }
}
