public class Factory {

    public void createStartData () {
        StudentManager studentManager = new StudentManager();

        studentManager.addStudent(new Student(1,"Иванов И.И.", "ii@mail.ru"));
        studentManager.addStudent(new Student(2,"Петров П.П.", "pp@mail.ru"));

        Teacher teacher1 = new Teacher(3,"Сидоров С.С", "ss@mail.ru");
        Course course1 = new Course(1,"Программирование", "PR");
        Course course2 = new Course(1,"Системное администрирование", "SA");

        System.out.println(studentManager.getAllStudents());

    }
}
