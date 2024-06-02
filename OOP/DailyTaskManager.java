package OOP;

public class DailyTaskManager extends BaseTaskManager {
    private String name;

    public DailyTaskManager(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
