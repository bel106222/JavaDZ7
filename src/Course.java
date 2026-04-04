import java.io.Serializable;
public class Course implements Serializable {

    private int courseId;
    private String courseName;
    private String courseCode;
    private Teacher teacher;

    /**
     * Конструктор для создания курса
     * @param courseId уникальный идентификатор курса
     * @param courseName название курса
     * @param courseCode код курса
     */
    public Course(int courseId, String courseName, String courseCode) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.teacher = null;  // Преподаватель назначается позже
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(int credits) {
        this.courseCode = courseCode;
    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return  "Код курса: " + courseCode +
                ", название: " + courseName +
                ", преподаватель: " + teacher.getName();
    }
}