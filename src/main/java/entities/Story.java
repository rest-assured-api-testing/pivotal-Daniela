package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class Story {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date updated_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String story_type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String current_state;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long requested_by_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Object> owner_ids;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Object> labels;

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

    public String getStory_type() {
        return story_type;
    }

    public void setStory_type(String story_type) {
        this.story_type = story_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public Long getRequested_by_id() {
        return requested_by_id;
    }

    public void setRequested_by_id(Long requested_by_id) {
        this.requested_by_id = requested_by_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public List<Object> getOwner_ids() {
        return owner_ids;
    }

    public void setOwner_ids(List<Object> owner_ids) {
        this.owner_ids = owner_ids;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }
}
