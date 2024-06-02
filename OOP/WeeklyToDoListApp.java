package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeeklyToDoListApp {
    private static Map<String, TaskManager> week = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeWeek();
        while (true) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    addTask();
                } else if (choice == 2) {
                    markTaskAsCompleted();
                } else if (choice == 3) {
                    removeTask();
                } else if (choice == 4) {
                    listTasksForDay();
                } else if (choice == 5) {
                    editTask();
                } else if (choice == 6) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Invalid choice. Please choose again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void initializeWeek() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            week.put(day, new DailyTaskManager(day));
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Task");
        System.out.println("2. Mark Task as Completed");
        System.out.println("3. Remove Task");
        System.out.println("4. List Tasks for a Day");
        System.out.println("5. Edit Task");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addTask() {
        TaskManager day = getDayFromUser();
        if (day != null) {
            System.out.print("Enter the task description: ");
            String description = scanner.nextLine();
            day.addTask(new Task(description));
            System.out.println("Task added.");
        }
    }

    private static void markTaskAsCompleted() {
        TaskManager day = getDayFromUser();
        if (day != null) {
            if (dayHasNoTasks(day)) {
                return;
            }
            day.listTasks();
            try {
                System.out.print("Enter the task number to mark as completed: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                day.markTaskAsCompleted(taskNumber - 1);
                System.out.println("Task marked as completed.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid task number.");
            }
        }
    }

    private static void removeTask() {
        TaskManager day = getDayFromUser();
        if (day != null) {
            if (dayHasNoTasks(day)) {
                return;
            }
            day.listTasks();
            try {
                System.out.print("Enter the task number to remove: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                day.removeTask(taskNumber - 1);
                System.out.println("Task removed.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid task number.");
            }
        }
    }

    private static void listTasksForDay() {
        TaskManager day = getDayFromUser();
        if (day != null) {
            day.listTasks();
        }
    }

    private static void editTask() {
        TaskManager day = getDayFromUser();
        if (day != null) {
            if (dayHasNoTasks(day)) {
                return;
            }
            day.listTasks();
            try {
                System.out.print("Enter the task number to edit: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the new task description: ");
                String newDescription = scanner.nextLine();
                day.editTask(taskNumber - 1, newDescription);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid task number.");
            }
        }
    }

    private static TaskManager getDayFromUser() {
        System.out.print("Enter the day (Monday, Tuesday, etc.): ");
        String dayName = scanner.nextLine();
        TaskManager day = week.get(dayName);
        if (day == null) {
            System.out.println("Invalid day.");
        }
        return day;
    }

    private static boolean dayHasNoTasks(TaskManager day) {
        if (day instanceof BaseTaskManager) {
            BaseTaskManager baseDay = (BaseTaskManager) day;
            if (baseDay.tasks.isEmpty()) {
                System.out.println("No tasks for this day.");
                return true;
            }
        }
        return false;
    }
}
