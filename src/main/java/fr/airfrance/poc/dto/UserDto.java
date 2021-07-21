package fr.airfrance.poc.dto;

import fr.airfrance.poc.entity.enumeration.Gender;

/**
 * <p>
 *     This dto flat User embedded id
 * </p>
 *
 * @author TarekAbdennadher
 */
public class UserDto {

    private String userName;

    private String birthdate;

    private String country;

    private String phoneNumber;

    private Gender gender;

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
