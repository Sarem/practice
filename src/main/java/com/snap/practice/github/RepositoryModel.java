package com.snap.practice.github;

import java.util.Objects;

public class RepositoryModel {

    private Long id;

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
        if (!(o instanceof RepositoryModel)) return false;
        RepositoryModel that = (RepositoryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(node_id, that.node_id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(full_name, that.full_name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, node_id, name, full_name, description);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", node_id='" + node_id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
