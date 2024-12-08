package org.exam.final_exam.entity;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int id;
    private String fullName;
    private String email;
    private String passWord;
    private String phoneNumber;
    private String address;
    private String role;
    private Date createAt;

    public Users(String fullName, String email, String passWord, String phoneNumber, String address, String role) {
        this.fullName = fullName;
        this.email = email;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.createAt = new Date();

    }
}
