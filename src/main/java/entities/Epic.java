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

    /**
     * Gets epic id
     * @return Long with the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets epic id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets epic kind
     * @return String with the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets epic kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets epic created_at
     * @return date with the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * Sets epic created_at
     * @param created_at
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * Gets epic updated_at
     * @return date with the updated_at
     */
    public Date getUpdated_at() {
        return updated_at;
    }

    /**
     * Sets epic updated_at
     * @param updated_at
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * Gets epic project_id
     * @return Long with the project_id
     */
    public Long getProject_id() {
        return project_id;
    }

    /**
     * Sets epic project_id
     * @param project_id
     */
    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    /**
     * Gets epic name
     * @return String with the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets epic name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets epic description
     * @return String with the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets epic description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets epic url
     * @return String with the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets epic url
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets epic label
     * @return Label with the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets epic label
     * @param label
     */
    public void setLabel(Label label) {
        this.label = label;
    }
}
