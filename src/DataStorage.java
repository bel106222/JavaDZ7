import java.io.*;
import java.util.List;

/**
 * Класс DataStorage отвечает за сохранение и загрузку данных
 * Использует сериализацию для сохранения объектов в файлы
 */
public class DataStorage {
    // Имена файлов для хранения данных
    private static final String STUDENTS_FILE = "students.dat";
    private static final String COURSES_FILE = "courses.dat";

    /**
     * Сохраняет всех студентов и курсы в файлы
     * @param studentManager менеджер студентов
     * @param courseManager менеджер курсов
     */
    public static void saveData(StudentManager studentManager, CourseManager courseManager) {
        saveStudents(studentManager);
        saveCourses(courseManager);
    }

    /**
     * Сохраняет список студентов в файл с помощью сериализации
     * @param studentManager менеджер студентов
     */
    private static void saveStudents(StudentManager studentManager) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            // Получаем список всех студентов и сохраняем
            oos.writeObject(studentManager.getAllStudents());
            System.out.println("Студенты успешно сохранены.");
        } catch (IOException e) {
            System.err.println("Ошибка записи студентов: " + e.getMessage());
        }
    }

    /**
     * Сохраняет список курсов в файл с помощью сериализации
     * @param courseManager менеджер курсов
     */
    private static void saveCourses(CourseManager courseManager) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courseManager.getAllCourses());
            System.out.println("Курсы успешно сохранены.");
        } catch (IOException e) {
            System.err.println("Ошибка записи курсов: " + e.getMessage());
        }
    }
    /**
     * Загружает данные из файлов
     * @param studentManager менеджер студентов для заполнения
     * @param courseManager менеджер курсов для заполнения
     */
    @SuppressWarnings("unchecked")
    public static void loadData(StudentManager studentManager, CourseManager courseManager) {
        loadStudents(studentManager);
        loadCourses(courseManager);
    }
    /**
     * Загружает студентов из файла (десериализация)
     * @param studentManager менеджер студентов
     */
    @SuppressWarnings("unchecked")
    private static void loadStudents(StudentManager studentManager) {
        File file = new File(STUDENTS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Читаем список студентов из файла
                List<Student> students = (List<Student>) ois.readObject();
                // Добавляем каждого студента в менеджер
                for (Student student : students) {
                    studentManager.addStudent(student);
                }
                System.out.println("Студенты успешно загружены.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка загрузки студентов: " + e.getMessage());
            }
        } else {
            System.out.println("Студенты не найдены.");
        }
    }
    /**
     * Загружает курсы из файла (десериализация)
     * @param courseManager менеджер курсов
     */
    @SuppressWarnings("unchecked")
    private static void loadCourses(CourseManager courseManager) {
        File file = new File(COURSES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Course> courses = (List<Course>) ois.readObject();
                for (Course course : courses) {
                    courseManager.addCourse(course);
                }
                System.out.println("Курсы успешно загружены.");
                courseManager.getAllTeachers();
                System.out.println("Преподаватели успешно загружены.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка загрузки курсов: " + e.getMessage());
            }
        } else {
            System.out.println("Курсы не найдены.");
        }
    }
}