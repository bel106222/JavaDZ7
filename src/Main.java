/*
Разработать консольное приложение для управления информацией
о студентах и курсах, которое будет включать регистрацию
преподавателей, возможность добавления, удаления и отображения
студентов и курсов, а также сериализацию информации в файл и
ее считывание. Приложение должно включать функционал для
выдачи оценок студентам и записи их в отдельный файл.
- Использовать классы для организации кода (например, классы `Student`,
 `Course`, `User`, `StudentManager`, `CourseManager`).
- Применять коллекции (например, `ArrayList`, `HashMap`) для
хранения информации о студентах и курсах.
- Реализовать сериализацию и десериализацию объектов.
- Сохранять и загружать данные о пользователях, студентах и курсах в файл.
- Реализовать возможность выдачи оценок студентам, отображение списка оценок и курсов.
*/

import java.util.*;

public class Main {
    // Контроллеры для управления различными аспектами системы
    private static StudentManager studentManager = new StudentManager();
    private static CourseManager courseManager = new CourseManager();
    private static GradeManager gradeManager = new GradeManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Загрузка ранее сохраненных данных
        DataStorage.loadData(studentManager, courseManager);
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Ваш выбор:");
            switch (choice) {
                case 1:
                    registerTeacher();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 6:
                    assignGrade();
                    break;
                case 7:
                    displayAllStudents();
                    break;
                case 8:
                    displayAllCourses();
                    break;
                case 9:
                    displayStudentGrades();
                    break;
                case 10:
                    displayAllGrades();
                    break;
                case 11:
                    enrollStudentInCourse();
                    break;
                case 12:
                    assignTeacherToCourse();
                    break;
                case 13:
                    displayAllTeachers();
                    break;
                case 0:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Неверный ввод! Попробуйте снова.");
            }
        }
        scanner.close();  // Закрываем сканер
    }
    /**
     * Сохранение данных и выход из программы
     */
    private static void saveAndExit() {
        System.out.println("\nСохранение...");
        DataStorage.saveData(studentManager, courseManager);
        System.out.println("Пока!");
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ");
        System.out.println("=".repeat(50));
        System.out.println("1. Добавить преподавателя");
        System.out.println("2. Добавить курс");
        System.out.println("3. Добавить студента");
        System.out.println("4. Удалить студента");
        System.out.println("5. Удалить курс");
        System.out.println("6. Выставить оценки");
        System.out.println("7. Показать студентов");
        System.out.println("8. Показать курсы");
        System.out.println("9. Показать оценки по студенту");
        System.out.println("10. Показать все оценки");
        System.out.println("11. Добавить курс студенту");
        System.out.println("12. Добавить курс преподавателю");
        System.out.println("13. Показать всех преподавателей");
        System.out.println("0. Выход из системы");
        System.out.println("=".repeat(50));
    }
    /**
     * Безопасное чтение целого числа из ввода
     * Обрабатывает ошибки ввода
     * @param prompt приглашение для ввода
     * @return введенное целое число
     */
    private static int getIntInput(String prompt) {
        System.out.print(prompt + ">");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка ввода. Введите целое число:>");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера после nextInt()
        return input;
    }
    /**
     * Регистрация нового преподавателя
     * Генерирует уникальный системный ID
     */
    private static void registerTeacher() {
        System.out.println("\n--- Новый преподаватель ---");
        int id = courseManager.getAllTeachers().size() + 1;
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Teacher teacher = new Teacher(id, name, email);
        courseManager.addTeacher(teacher);
    }
    /**
     * Отображение всех преподавателей
     */
    private static void displayAllTeachers() {
        courseManager.displayAllTeachers();
    }
    /**
     * Добавление нового студента
     */
    private static void addStudent() {
        System.out.println("\n--- Новый студент ---");
        int id = studentManager.getAllStudents().size() + 1;
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Student student = new Student(id, name, email);
        studentManager.addStudent(student);
    }
    /**
     * Отображение всех студентов
     */
    private static void displayAllStudents() {
        studentManager.displayAllStudents();
    }
    /**
     * Добавление нового курса
     */
    private static void addCourse() {
        System.out.println("\n--- Новый курс ---");
        int courseId = courseManager.getAllCourses().size() + 1;
        System.out.print("Название курса: ");
        String courseName = scanner.nextLine();
        System.out.print("Код курса: ");
        String courseCode = scanner.nextLine();
        Course course = new Course(courseId, courseName, courseCode);
        courseManager.addCourse(course);
    }
    /**
     * Отображение всех курсов
     */
    private static void displayAllCourses() {
        courseManager.displayAllCourses();
    }
    /**
     * Назначение преподавателя на курс
     */
    private static void assignTeacherToCourse() {
        System.out.println("\n--- Назначение преподавателя на курс ---");
        System.out.println("Доступные преподаватели:");
        displayAllTeachers();
        int teacherId = getIntInput("Введите ID преподавателя:");
        System.out.println("Доступные курсы:");
        displayAllCourses();
        int courseId = getIntInput("Введите ID курса:");
        courseManager.assignTeacherToCourse(teacherId, courseId);
    }
    /**
     * Удаление студента по ID
     */
    private static void removeStudent() {
        System.out.println("\n--- Удаление студента ---");
        int studentId = getIntInput("Введите ID студента:");
        studentManager.removeStudent(studentId);
    }
    /**
     * Удаление курса по ID
     */
    private static void removeCourse() {
        System.out.println("\n--- Удаление курса ---");
        int courseId = getIntInput("Введите ID курса:");
        courseManager.removeCourse(courseId);
    }
    /**
     * Запись студента на курс
     */
    private static void enrollStudentInCourse() {
        System.out.println("\n--- Добавление курса студенту ---");

        int studentId = getIntInput("Введите ID студента:");
        Student student = studentManager.getStudent(studentId);
        if (student == null) {
            System.out.println("Студент не найден!");
            return;
        }

        int courseId = getIntInput("Введите ID курса:");
        Course course = courseManager.getCourse(courseId);
        if (course == null) {
            System.out.println("Курс не найден!");
            return;
        }

        studentManager.addOnCourse(studentId, course);
    }
    /**
     * Выставление оценки студенту
     * Проверяет существование студента и курса
     */
    private static void assignGrade() {
        System.out.println("\n--- Выставление оценки ---");
        int studentId = getIntInput("Введите ID студента:");
        Student student = studentManager.getStudent(studentId);
        if (student == null) {
            System.out.println("Студент не найден!");
            return;
        }

        int courseId = getIntInput("Введите ID курса:");
        Course course = courseManager.getCourse(courseId);
        if (course == null) {
            System.out.println("Курс не найден!");
            return;
        }

        int score = getIntInput("Укажите балл (1-5):");

        if (score < 1 || score > 5) {
            System.out.println("Укажите значение от 1 до 5.");
            return;
        }

        gradeManager.assignGrade(student, course, score);
    }
    /**
     * Отображение оценок конкретного студента
     */
    private static void displayStudentGrades() {
        System.out.println("\n--- Оценки студента ---");
        int studentId = getIntInput("Введите ID студента:");
        Student student = studentManager.getStudent(studentId);

        if (student == null) {
            System.out.println("Студент не найден!");
            return;
        }

        gradeManager.displayStudentGrades(student);
    }
    /**
     * Отображение всех оценок в системе
     */
    private static void displayAllGrades() {
        gradeManager.displayAllGrades();
    }
}