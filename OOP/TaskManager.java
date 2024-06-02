package OOP;

public interface TaskManager {
    void addTask(Task task);
    void removeTask(int index);
    void listTasks();
    void markTaskAsCompleted(int index);
    void editTask(int index, String newDescription);
}
