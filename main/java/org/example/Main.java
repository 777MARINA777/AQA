package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("Сидоров", "+375334502617");
        phoneDirectory.add("Иванов", "+375298704526");
        phoneDirectory.add("Ковалёв", "+375253409876");

        System.out.println("Телефон Сидорова: " + phoneDirectory.get("Сидоров"));
        System.out.println("Телефон Иванова: " + phoneDirectory.get("Иванов"));
        System.out.println("Телефон Ковалёва: " + phoneDirectory.get("Ковалёв"));


        Set<Student> students = new HashSet<>();

        students.add(new Student("Николай", "Группа №1",1,Map.of("Биология", 5,
                "Физика", 4, "Математика", 4)));
        students.add(new Student("Аня", "Группа №2",1,Map.of("Биология", 4,
                "Физика", 3, "Математика", 3)));
        students.add(new Student("Таня", "Группа №3",3, Map.of("Биология", 4,
                "Физика", 5, "Математика", 3)));

        StudentManager manager = new StudentManager(students);

        System.out.println("Все студенты: ");
        students.forEach(System.out::println);

        manager.promoteStudents();
        manager.removeUnderperformingStudents();

        System.out.println("Оставшиеся студенты");
        students.forEach(System.out::println);

        manager.printStudents(2);
    }
}






