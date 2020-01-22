package com.snap.practice.contact.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

@Entity
public class ContactEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    private String phoneNumber;
    @Email
    private String email;
    private String organization;
    private String github;


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private Set<RepositoryEntity> githubRepositories;

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

    public Set<RepositoryEntity> getGithubRepositories() {
        return githubRepositories;
    }

    public void setGithubRepositories(Set<RepositoryEntity> githubRepositories) {
        this.githubRepositories = githubRepositories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactEntity)) return false;
        ContactEntity that = (ContactEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(organization, that.organization) &&
                Objects.equals(github, that.github) &&
                Objects.equals(githubRepositories, that.githubRepositories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, organization, github, githubRepositories);
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
                ", githubRepositories=" + githubRepositories +
                '}';
    }
}
