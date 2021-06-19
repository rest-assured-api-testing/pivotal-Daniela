package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Workspaces {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long person_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Object> project_ids;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

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

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public List<Object> getProject_ids() {
        return project_ids;
    }

    public void setProject_ids(List<Object> project_ids) {
        this.project_ids = project_ids;
    }
}
