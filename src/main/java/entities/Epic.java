package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class Epic {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date updated_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Label label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
