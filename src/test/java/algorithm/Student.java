package algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Student {
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        Map<Student, String> map = new HashMap<Student, String>();
        Student student1 = new Student("tony", 18);
        map.put(student1, "skr");
        Student student2 = new Student("sunny", 20);
        Student student3 = new Student("sunny", 25);
//        System.out.println(map.get(student2));
        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        Map<String, Integer> hashmap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge, (value1, value2) -> value2));
        System.out.println(hashmap);
    }
}
