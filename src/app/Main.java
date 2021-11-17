package app;

public class Main {

    public static String InPath = "in.txt";

    public static void main(String[] args) {
        TaskAssignmentManager taskAssignmentManager = new TaskAssignmentManager();
        taskAssignmentManager.LoadData(InPath);
        taskAssignmentManager.Start();
    }
}
