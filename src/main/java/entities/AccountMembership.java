package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class AccountMembership {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Person person;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int account_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date updated_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean owner;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean admin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean project_creator;

    /**
     * Gets account membership kind
     * @return String with kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets account membership kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets account membership id
     * @return Long with the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets account membership id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets account membership person
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets account membership person
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets account id
     * @return int with thw account id
     */
    public int getAccount_id() {
        return account_id;
    }

    /**
     * Sets account id
     * @param account_id
     */
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    /**
     * Gets account membership created_date
     * @return the date
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * Sets account membership created_date
     * @param created_at
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * Gets account membership updated_date
     * @return the date
     */
    public Date getUpdated_at() {
        return updated_at;
    }

    /**
     * Sets account membership updated_date
     * @return the date
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * Gets if it's the owner
     * @return boolean true if it's the owner
     */
    public boolean isOwner() {
        return owner;
    }

    /**
     * Sets the account owner
     * @param owner
     */
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    /**
     * Gets if it's admin
     * @return boolean true if it's the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the account admin
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets if it's project_creator
     * @return boolean true if it's the project_creator
     */
    public boolean isProject_creator() {
        return project_creator;
    }

    /**
     * Sets the account project_creator
     * @param project_creator
     */
    public void setProject_creator(boolean project_creator) {
        this.project_creator = project_creator;
    }

}
