package com.snap.practice.contact.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class RepositoryEntity {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="contact_id", nullable=true)
  private ContactEntity contact;

  private Long githubId;

  private String node_id;

  private String name;

  private String full_name;

  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ContactEntity getContact() {
    return contact;
  }

  public void setContact(ContactEntity contact) {
    this.contact = contact;
  }

  public Long getGithubId() {
    return githubId;
  }

  public void setGithubId(Long githubId) {
    this.githubId = githubId;
  }

  public String getNode_id() {
    return node_id;
  }

  public void setNode_id(String node_id) {
    this.node_id = node_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RepositoryEntity)) return false;
    RepositoryEntity that = (RepositoryEntity) o;
    return Objects.equals(githubId, that.githubId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(githubId);
  }

  @Override
  public String toString() {
    return "RepositoryEntity{" +
        "id=" + id +
        ", contact=" + contact.getName() +
        ", githubId=" + githubId +
        ", node_id='" + node_id + '\'' +
        ", name='" + name + '\'' +
        ", full_name='" + full_name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
