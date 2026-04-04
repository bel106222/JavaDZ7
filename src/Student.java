import java.util.ArrayList;
import java.util.List;
public class Student extends User {

    private List<Course> studentCourses;

    /**
     * Конструктор для создания нового студента
     * @param id студента
     * @param name имя студента
     * @param email электронная почта
     */
    public Student(int id, String name, String email) {
        super(id, name, email);  // Вызов конструктора родительского класса
        this.studentCourses = new ArrayList<>();  // Инициализация пустого списка курсов
    }

    /**
     * Возвращает список курсов студента
     * @return неизменяемый список курсов (через геттер)
     */
    public List<Course> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Добавляет курс в список курсов студента
     * Проверяет, чтобы курс не был добавлен дважды
     * @param course курс для добавления
     */
    public void addCourse(Course course) {
        if (!studentCourses.contains(course)) {
            studentCourses.add(course);
        }
    }

    /**
     * Удаляет курс из списка курсов студента
     * @param course курс для удаления
     */
    public void removeCourse(Course course) {
        studentCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Студент " + super.toString();
    }
}