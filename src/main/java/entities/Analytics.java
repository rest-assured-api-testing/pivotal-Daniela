package entities;

public class Analytics {
    private String kind;
    private int stories_accepted;
    private int bugs_created;
    private int cycle_time;
    private double rejection_rate;

    /**
     * Gets analytics kind
     * @return String with kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets analytics kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets analytics stories_accepted
     * @return int with stories_accepted
     */
    public int getStories_accepted() {
        return stories_accepted;
    }

    /**
     * Sets analytics stories_accepted
     * @param stories_accepted
     */
    public void setStories_accepted(int stories_accepted) {
        this.stories_accepted = stories_accepted;
    }

    /**
     * Gets analytics bugs_created
     * @return int with bug_created
     */
    public int getBugs_created() {
        return bugs_created;
    }

    /**
     * Sets analytics bugs_created
     * @param bugs_created
     */
    public void setBugs_created(int bugs_created) {
        this.bugs_created = bugs_created;
    }

    /**
     * Gets analytics cycle_time
     * @return int with cycle_time
     */
    public int getCycle_time() {
        return cycle_time;
    }

    /**
     * Sets analytics cycle_time
     * @param cycle_time
     */
    public void setCycle_time(int cycle_time) {
        this.cycle_time = cycle_time;
    }

    /**
     * Gets analytics rejection_rate
     * @return doucle with rejection_rate
     */
    public double getRejection_rate() {
        return rejection_rate;
    }

    /**
     * Sets analytics rejection_rate
     * @param rejection_rate
     */
    public void setRejection_rate(double rejection_rate) {
        this.rejection_rate = rejection_rate;
    }
}
