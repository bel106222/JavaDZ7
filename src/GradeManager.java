import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс GradeManager управляет оценками студентов
 * Автоматически сохраняет и загружает оценки из файла
 */
public class GradeManager {
    // Список всех оценок в системе
    private List<Grade> grades;
    // Имя файла для сохранения оценок
    private static final String GRADES_FILE = "grades.dat";

    /**
     * Конструктор - загружает существующие оценки из файла
     */
    public GradeManager() {
        grades = new ArrayList<>();
        loadGrades();  // Автоматическая загрузка при создании
    }

    /**
     * Выставляет оценку студенту за курс
     * Автоматически сохраняет в файл
     * @param student студент
     * @param course курс
     * @param score количество баллов
     */
    public void assignGrade(Student student, Course course, int score) {
        Grade grade = new Grade(student, course, score);
        grades.add(grade);
        saveGrades();  // Немедленное сохранение в файл
        System.out.println("Оценки сохранены: " + grade);
    }

    /**
     * Получает все оценки конкретного студента
     * @param student студент
     * @return список оценок студента
     */
    public List<Grade> getStudentGrades(Student student) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getStudent().getId() == student.getId()) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }
    /**
     * Получает все оценки по конкретному курсу
     * @param course курс
     * @return список оценок курса
     */
    public List<Grade> getCourseGrades(Course course) {
        List<Grade> courseGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getCourse().equals(course)) {
                courseGrades.add(grade);
            }
        }
        return courseGrades;
    }

    /**
     * Выводит все оценки студента и вычисляет средний балл
     * @param student студент
     */
    public void displayStudentGrades(Student student) {
        List<Grade> studentGrades = getStudentGrades(student);
        if (studentGrades.isEmpty()) {
            System.out.println("Нет оценок у студента " + student.getName());
            return;
        }

        System.out.println("\n=== Оценки студента " + student.getName() + " ===");
        double sum = 0;
        for (Grade grade : studentGrades) {
            System.out.println(grade);
            sum += grade.getScore();
        }
        double average = sum / studentGrades.size();
        System.out.printf("Средний балл: %.2f %n", average);
    }
    /**
     * Выводит все оценки по курсу
     * @param course курс
     */
    public void displayCourseGrades(Course course) {
        List<Grade> courseGrades = getCourseGrades(course);
        if (courseGrades.isEmpty()) {
            System.out.println("Нет оценок по курсу " + course.getCourseName());
            return;
        }

        System.out.println("\n=== Оценки по курсу: " + course.getCourseName() + " ===");
        for (Grade grade : courseGrades) {
            System.out.println(grade);
        }
    }
    /**
     * Сохраняет все оценки в файл с помощью сериализации
     */
    private void saveGrades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GRADES_FILE))) {
            oos.writeObject(grades);
        } catch (IOException e) {
            System.err.println("Ошибка записи оценок: " + e.getMessage());
        }
    }
    /**
     * Загружает оценки из файла (десериализация)
     * Если файл не существует, создает пустой список
     */
    @SuppressWarnings("unchecked")
    private void loadGrades() {
        File file = new File(GRADES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                grades = (List<Grade>) ois.readObject();
                System.out.println("Оценки загружены.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка загрузки оценок: " + e.getMessage());
                grades = new ArrayList<>();
            }
        }
    }

    /**
     * Выводит все оценки в системе
     */
    public void displayAllGrades() {
        if (grades.isEmpty()) {
            System.out.println("Нет оценок.");
            return;
        }

        System.out.println("\n=== Все оценки ===");
        for (Grade grade : grades) {
            System.out.println(grade);
        }
    }
}