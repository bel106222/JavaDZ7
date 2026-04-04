import java.io.Serializable;
import java.util.Date;
public class Grade implements Serializable {

    private Student student;
    private Course course;
    private int score;
    private Date date;

    /**
     * Конструктор для создания оценки
     * @param student студент
     * @param course курс
     * @param score оценка
     */
    public Grade(Student student, Course course, int score) {
        this.student = student;
        this.course = course;
        this.score = score;
        this.date = new Date();  // Устанавливаем текущую дату
    }

    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s, курс: %s, оценка: %d (%s), дата: %s",
                student.getName(), course.getCourseName(), score, date);
    }
}