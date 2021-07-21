package fr.airfrance.poc.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 *     This class is used as embeddable id to define User by
 * </p>
 * @author TarekAbdennadher
 */
@Embeddable
public class UserPk implements Serializable {

    private String userName;

    private String birthdate;

    private String country;

    public UserPk() {
    }

    public UserPk(String userName, String birthdate, String country) {
        this.userName = userName;
        this.birthdate = birthdate;
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPk userPk = (UserPk) o;
        return Objects.equals(userName, userPk.userName) &&
                Objects.equals(birthdate, userPk.birthdate) &&
                Objects.equals(country, userPk.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, birthdate, country);
    }

    @Override
    public String toString() {
        return "UserPk{" +
                "userName='" + userName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
