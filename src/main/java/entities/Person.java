package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class Person {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String initials;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String username;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
