import java.util.*;
import java.io.*;
/**
 * Класс StudentManager управляет всеми операциями со студентами
 * Хранит студентов в HashMap для быстрого доступа по ID
 */
public class StudentManager {
    // Хранилище студентов: ключ - ID студента, значение - объект Student
    private Map<Integer, Student> students;

    /**
     * Конструктор - инициализирует пустую коллекцию
     */
    public StudentManager() {
        students = new HashMap<>();
    }

    /**
     * Добавляет нового студента в систему, проверяя, нет ли студента с таким ID
     * @param student объект студента для добавления
     */
    public void addStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            System.out.println("Добавлен студент: " + student.getName());
        } else {
            System.out.println("Студент с ID " + student.getId() + " уже существует!");
        }
    }

    /**
     * Удаляет студента из системы по ID
     * @param id идентификатор студента для удаления
     */
    public void removeStudent(int id) {
        if (students.containsKey(id)) {
            Student removed = students.remove(id);
            System.out.println("Удалён студент: " + removed.getName());
        } else {
            System.out.println("Студент не найден!");
        }
    }

    /**
     * Находит студента по ID
     * @param id идентификатор студента
     * @return объект Student или null, если не найден
     */
    public Student getStudent(int id) {
        return students.get(id);
    }

    /**
     * Возвращает список всех студентов
     * @return ArrayList со всеми студентами
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * Выводит в консоль информацию о всех студентах
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        System.out.println("\n=== Все студенты ===");
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    /**
     * Записывает студента на курс
     * @param id студента
     * @param course курс для записи
     */
    public void addOnCourse(int id, Course course) {
        Student student = students.get(id);
        if (student != null) {
            student.addCourse(course);
            System.out.println(student.getName() + " добавлен на курс " + course.getCourseName());
        } else {
            System.out.println("Студент не найден!");
        }
    }

    /**
     * Проверяет, существует ли студент с указанным ID
     * @param id для проверки
     * @return true если студент существует
     */
    public boolean studentExists(int id) {
        return students.containsKey(id);
    }
}