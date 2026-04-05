import java.util.*;
/**
 * Класс CourseManager управляет курсами и преподавателями
 * Использует HashMap для хранения как курсов, так и преподавателей
 */
public class CourseManager {
    // Хранилище курсов: ключ - ID курса, значение - объект Course
    private Map<Integer, Course> courses;
    // Хранилище преподавателей: ключ - ID преподавателя, значение - объект Teacher
    private Map<Integer, Teacher> teachers;

    /**
     * Конструктор - инициализирует пустые коллекции
     */
    public CourseManager() {
        courses = new HashMap<>();
        teachers = new HashMap<>();
    }

    /**
     * Регистрирует нового преподавателя в системе
     * @param teacher объект преподавателя
     */
    public void addTeacher(Teacher teacher) {
        if (!teachers.containsKey(teacher.getId())) {
            teachers.put(teacher.getId(), teacher);
            System.out.println("Добавлен преподаватель: " + teacher.getName());
        } else {
            System.out.println("Преподаватель с ID " + teacher.getId() + " уже существует!");
        }
    }

    /**
     * Добавляет новый курс в систему
     * @param course объект курса
     */
    public void addCourse(Course course) {
        if (!courses.containsKey(course.getCourseId())) {
            courses.put(course.getCourseId(), course);
            System.out.println("Добавлен курс: " + course.getCourseName());
        } else {
            System.out.println("Курс с ID " + course.getCourseId() + " уже существует!");
        }
    }

    /**
     * Удаляет курс из системы по ID
     * @param courseId ID курса для удаления
     */
    public void removeCourse(int courseId) {
        if (courses.containsKey(courseId)) {
            Course removed = courses.remove(courseId);
            System.out.println("Удалён курс: " + removed.getCourseName());
        } else {
            System.out.println("Курс не найден!");
        }
    }

    /**
     * Находит курс по ID
     * @param courseId ID курса
     * @return объект Course или null
     */
    public Course getCourse(int courseId) {
        return courses.get(courseId);
    }

    /**
     * Возвращает список всех курсов
     * @return ArrayList всех курсов
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    /**
     * Выводит информацию о всех курсах
     */
    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("Нет доступных курсов.");
            return;
        }

        System.out.println("\n=== Все курсы ===");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    /**
     * Назначает преподавателя на курс
     * Обновляет связи в обоих направлениях
     * @param teacherId ID преподавателя
     * @param courseId ID курса
     */
    public void assignTeacherToCourse(int teacherId, int courseId) {
        Teacher teacher = teachers.get(teacherId);
        Course course = courses.get(courseId);

        if (teacher != null && course != null) {
            course.setTeacher(teacher);
            teacher.addCourse(course);
            System.out.println("Преподаватель " + teacher.getName() + " назначен на курс " + course.getCourseName());
        } else {
            System.out.println("Преподаватель или курс не найден!");
        }
    }

    /**
     * Выводит информацию о всех преподавателях
     */
    public void displayAllTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("Нет преподавателей.");
            return;
        }

        System.out.println("\n=== Все преподаватели ===");
        for (Teacher teacher : teachers.values()) {
            System.out.println(teacher);
        }
    }

    /**
     * Находит преподавателя по ID
     * @param teacherId ID преподавателя
     * @return объект Teacher или null
     */
    public Teacher getTeacher(int teacherId) {
        return teachers.get(teacherId);
    }
}