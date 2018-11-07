package entities;


import java.util.Date;
import java.util.Objects;

public class User {
    private Integer id;
    private String email;
    private String password;
    private String country;
    private boolean gender;
    private Date birthday;

    public User(Integer id, String email, String password, String country, boolean gender, Date birthday) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
    }

    public User(String email, String password, String country, boolean gender, Date birthday) {
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return gender == user.gender &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(country, user.country) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password, country, gender, birthday);
    }
}
