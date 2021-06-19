package entities;

public class CycleTimeDetail {
    private String kind;
    private int total_cycle_time;
    private int started_time;
    private int started_count;
    private int finished_time;
    private int finished_count;
    private int delivered_time;
    private int delivered_count;
    private int rejected_time;
    private int rejected_count;
    private Long story_id;

    /**
     * Gets CycleTimeDetail kind
     * @return string with kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets CycleTimeDetail kind
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets CycleTimeDetail Total_cycle_time
     * @return int with Total_cycle_time
     */
    public int getTotal_cycle_time() {
        return total_cycle_time;
    }

    /**
     * Sets CycleTimeDetail Total_cycle_time
     * @param total_cycle_time
     */
    public void setTotal_cycle_time(int total_cycle_time) {
        this.total_cycle_time = total_cycle_time;
    }

    /**
     * Gets CycleTimeDetail Started_time
     * @return int with Started_time
     */
    public int getStarted_time() {
        return started_time;
    }

    /**
     * Sets CycleTimeDetail started_time
     * @param started_time
     */
    public void setStarted_time(int started_time) {
        this.started_time = started_time;
    }

    /**
     * Gets CycleTimeDetail Started_count
     * @return int with Started_count
     */
    public int getStarted_count() {
        return started_count;
    }

    /**
     * Sets CycleTimeDetail Started_count
     * @param started_count
     */
    public void setStarted_count(int started_count) {
        this.started_count = started_count;
    }

    /**
     * Gets CycleTimeDetail Finished_time
     * @return int with Finished_time
     */
    public int getFinished_time() {
        return finished_time;
    }

    /**
     * Sets CycleTimeDetail Finished_time
     * @param finished_time
     */
    public void setFinished_time(int finished_time) {
        this.finished_time = finished_time;
    }

    /**
     * Gets CycleTimeDetail Finished_count
     * @return int with Finished_count
     */
    public int getFinished_count() {
        return finished_count;
    }

    /**
     * Sets CycleTimeDetail Finished_count
     * @param finished_count
     */
    public void setFinished_count(int finished_count) {
        this.finished_count = finished_count;
    }

    /**
     * Gets CycleTimeDetail Delivered_time
     * @return int with Delivered_time
     */
    public int getDelivered_time() {
        return delivered_time;
    }

    /**
     * Sets CycleTimeDetail delivered_time
     * @param delivered_time
     */
    public void setDelivered_time(int delivered_time) {
        this.delivered_time = delivered_time;
    }

    /**
     * Gets CycleTimeDetail delivered_count
     * @return int with delivered_count
     */
    public int getDelivered_count() {
        return delivered_count;
    }

    /**
     * Sets CycleTimeDetail delivered_count
     * @param delivered_count
     */
    public void setDelivered_count(int delivered_count) {
        this.delivered_count = delivered_count;
    }

    /**
     * Gets CycleTimeDetail rejected_time
     * @return int with rejected_time
     */
    public int getRejected_time() {
        return rejected_time;
    }

    /**
     * Sets CycleTimeDetail rejected_time
     * @param rejected_time
     */
    public void setRejected_time(int rejected_time) {
        this.rejected_time = rejected_time;
    }

    /**
     * Gets CycleTimeDetail rejected_count
     * @return int with rejected_count
     */
    public int getRejected_count() {
        return rejected_count;
    }

    /**
     * Sets CycleTimeDetail rejected_count
     * @param rejected_count
     */
    public void setRejected_count(int rejected_count) {
        this.rejected_count = rejected_count;
    }

    /**
     * Gets CycleTimeDetail story_id
     * @return int with story_id
     */
    public Long getStory_id() {
        return story_id;
    }

    /**
     * Sets CycleTimeDetail story_id
     * @param story_id
     */
    public void setStory_id(Long story_id) {
        this.story_id = story_id;
    }
}
