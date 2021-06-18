package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class AccountMembership {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int id;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isProject_creator() {
        return project_creator;
    }

    public void setProject_creator(boolean project_creator) {
        this.project_creator = project_creator;
    }

}
