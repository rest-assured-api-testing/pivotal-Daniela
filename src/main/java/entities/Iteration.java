package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class Iteration {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long number;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int length;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int team_strength;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Story> stories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date finish;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTeam_strength() {
        return team_strength;
    }

    public void setTeam_strength(int team_strength) {
        this.team_strength = team_strength;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
