package fr.airfrance.poc.dto;

import fr.airfrance.poc.entity.UserPk;
import fr.airfrance.poc.entity.enumeration.Gender;

public class UserDto {

    private UserPk userPk;

    private String phoneNumber;

    private Gender gender;

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
