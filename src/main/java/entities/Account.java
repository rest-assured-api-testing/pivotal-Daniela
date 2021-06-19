package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Account {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String plan;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int days_left;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean over_the_limit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updated_at;

    /**
     * Gets account's kind
     * @return String with kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets account's kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets account's id
     * @return Long with id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets account's id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets account's name
     * @return String with name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets account's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets account's plan
     * @return String with plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets account's plan
     * @param plan
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * Gets account's status
     * @return String with status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets account's status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets account's days_left
     * @return int with days_left
     */
    public int getDays_left() {
        return days_left;
    }

    /**
     * Sets account's days_left
     * @param days_left
     */
    public void setDays_left(int days_left) {
        this.days_left = days_left;
    }

    /**
     * Gets if a account is Over_the_limit
     * @return boolean with Over_the_limit
     */
    public Boolean getOver_the_limit() {
        return over_the_limit;
    }

    /**
     * Sets a account's Over_the_limit
     * @param over_the_limit
     */
    public void setOver_the_limit(Boolean over_the_limit) {
        this.over_the_limit = over_the_limit;
    }

    /**
     * Gets account's created date
     * @return
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Sets account's created date
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * Gets account's updated date
     * @return
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     * Sets account's updated date
     * @return
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
