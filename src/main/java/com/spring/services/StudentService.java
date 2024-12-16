package com.spring.services;
import com.spring.dtos.StudentDTO;
import com.spring.dtos.SubjectDTO;
import com.spring.entities.Students;
import com.spring.entities.Subject;
import com.spring.repos.StudentRepo;
import com.spring.repos.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SubjectRepo subjectRepo;
    // Convert Students to StudentDTO
    private StudentDTO convertToDTO(Students student) {
        List<SubjectDTO> subjectDTOs = student.getSubjects().stream()
                .map(subject -> new SubjectDTO(subject.getSubId(), subject.getName(), subject.getMarks()))
                .collect(Collectors.toList());
        return new StudentDTO(student.getName(), student.getAge(), student.getGender(),
                student.getDob(), student.getCourse(), student.getCourseStartYear(), student.getCourseEndYear(), subjectDTOs);
    }
    // Add a new student
    public Students addStudent(Students student) {
        return studentRepo.save(student);
    }
    // Get students by name
    public List<StudentDTO> getStudentByName(String name) {
        List<Students> students = studentRepo.findByName(name);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    // Get students by age
    public List<StudentDTO> getStudentByAge(int age) {
        List<Students> students = studentRepo.findByAge(age);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //find by id
    public StudentDTO getStudentById(int id){
        Students s=studentRepo.findById(id).orElseThrow();
        return convertToDTO(s);

    }
    // Get students by gender
    public List<StudentDTO> getStudentByGender(String gender) {
        List<Students> students = studentRepo.findByGender(gender);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    // Get all students with pagination
    public Page<StudentDTO> getAllStudents(int page, int size) {
        Page<Students> studentsPage = studentRepo.findAll(PageRequest.of(page, size));
        return studentsPage.map(this::convertToDTO);
    }

    //delete student by id
    public void deleteStudentById(int id){
         studentRepo.deleteById(id);
    }
    // Get subjects by name
    public List<SubjectDTO> findSubjectsByName(String name) {
        List<Subject> subjects = subjectRepo.findSubByName(name);
        return subjects.stream().map(subject -> new SubjectDTO(subject.getSubId(), subject.getName(), subject.getMarks()))
                .collect(Collectors.toList());
  

    
    }
}

