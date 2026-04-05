import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private List<Course> teacherCourses;

    /**
     * @param id преподавателя
     * @param name имя преподавателя
     * @param email электронная почта
     */
    public Teacher(int id, String name, String email) {
        super(id, name, email);
        this.teacherCourses = new ArrayList<>();  // Инициализация пустого списка курсов
    }

    public List<Course> getTeacherCourses() {
        return teacherCourses;
    }

    /**
     * Добавляет курс в список преподаваемых курсов
     * @param course добавляемый курс
     */
    public void addCourse(Course course) {
        if (!teacherCourses.contains(course)) {
            teacherCourses.add(course);
        }
    }

    @Override
    public String toString() {
        return "Преподаватель: " + super.toString();
    }
}