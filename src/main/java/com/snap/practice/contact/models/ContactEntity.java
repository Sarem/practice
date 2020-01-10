package com.snap.practice.contact.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ContactEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String phoneNumber;
  private String email;
  private String organization;
  private String github;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    ContactEntity that = (ContactEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(phoneNumber, that.phoneNumber) &&
        Objects.equals(email, that.email) &&
        Objects.equals(organization, that.organization) &&
        Objects.equals(github, that.github);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phoneNumber, email, organization, github);
  }

  @Override
  public String toString() {
    return "ContactEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        ", organization='" + organization + '\'' +
        ", github='" + github + '\'' +
        '}';
  }
}
