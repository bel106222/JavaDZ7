import java.io.Serializable;
public abstract class User implements Serializable {

    private int id;
    private String name;
    private String email;

    /**
     * Конструктор для создания пользователя
     * @param id уникальный идентификатор
     * @param name имя пользователя
     * @param email электронная почта
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Имя: " + name + ", Email: " + email;
    }
}