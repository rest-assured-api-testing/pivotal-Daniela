package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class ProjectWebHooks {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String webhook_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date updated_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean enabled;

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

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getWebhook_url() {
        return webhook_url;
    }

    public void setWebhook_url(String webhook_url) {
        this.webhook_url = webhook_url;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
