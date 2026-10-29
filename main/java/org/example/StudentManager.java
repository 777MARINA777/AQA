package org.example;

import java.util.Set;

public class StudentManager {
private Set<Student> students;

public StudentManager(Set<Student> students){
    this.students = students;
}
public void removeUnderperformingStudents(){
    System.out.println("Отчисление студентов со средним баллом < 3");

    students.removeIf(student -> {
        if (student.getAverageGrade() < 3 ) {
            System.out.println(student.getName() + " отчислен (средний балл: "
            + String.format("%.2f", student.getAverageGrade()) + ")" );
                    return true;
        }
        return false;
});
}
public void promoteStudents(){
    System.out.println("Перевод студентов на следующий курс");
    for (Student student : students ) {
        int oldCourse = student.getCourse();
        student.promoteToNextCourse();

        if (oldCourse != student.getCourse()) {
            System.out.println(student.getName() + " переведен с " + oldCourse
                    + " на " + student.getCourse() + " курс" );
    }
}
}
public void printStudents (int course) {
    System.out.println("Студенты " + course + " курса");
for (Student student : students) {
    if (student.getCourse() == course){
        System.out.println(student.getName() + "(группа: " +
                student.getGroup() + " средний бал: "
                + String.format("%.2f",  student.getAverageGrade()) +")" );
}
}
}
}