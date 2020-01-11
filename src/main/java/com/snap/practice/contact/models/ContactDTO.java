package com.snap.practice.contact.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContactDTO {

    @NotNull
    @NotEmpty
    private String name;
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    private String phoneNumber;
    @Email
    private String email;
    private String organization;
    private String github;

    public ContactDTO() {
    }

    @JsonCreator
    public ContactDTO(@JsonProperty("name")String name, String phoneNumber, String email, String organization, String github) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.organization = organization;
        this.github = github;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDTO that = (ContactDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(organization, that.organization) &&
                Objects.equals(github, that.github);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, organization, github);
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
