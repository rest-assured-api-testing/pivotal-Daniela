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

    /**
     * Gets iteration number
     * @return Long with number
     */
    public Long getNumber() {
        return number;
    }

    /**
     * Sets iteration number
     * @param number
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * Gets iteration project_id
     * @return Long with project_id
     */
    public Long getProject_id() {
        return project_id;
    }

    /**
     * Sets iteration project_id
     * @param project_id
     */
    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    /**
     * Gets iteration length
     * @return int with length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets iteration length
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets iteration team_strength
     * @return int with team_strength
     */
    public int getTeam_strength() {
        return team_strength;
    }

    /**
     * Sets iteration team_strength
     * @param team_strength
     */
    public void setTeam_strength(int team_strength) {
        this.team_strength = team_strength;
    }

    /**
     * Gets iteration stories
     * @return List with stories
     */
    public List<Story> getStories() {
        return stories;
    }

    /**
     * Sets iteration stories
     * @param stories
     */
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     * Gets iteration start
     * @return date with start
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets iteration start
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Gets iteration finish
     * @return date with finish
     */
    public Date getFinish() {
        return finish;
    }

    /**
     * Sets iteration finish
     * @param finish
     */
    public void setFinish(Date finish) {
        this.finish = finish;
    }

    /**
     * Gets iteration kind
     * @return String with kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets iteration kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }
}
