package fr.airfrance.poc.entity;


import fr.airfrance.poc.entity.enumeration.Gender;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * <p>
 *     This class represents a user
 * </p>
 *
 * @author TarekAbdennadher
 */

@Entity
public class User implements Serializable {

    private UserPk userPk;

    private String phoneNumber;

    private Gender gender;

    public User() {
    }

    @EmbeddedId
    public UserPk getUserPk() {
        return userPk;
    }

    public void setUserPk(UserPk userPk) {
        this.userPk = userPk;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
