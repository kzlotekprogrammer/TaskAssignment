package app;

import com.softtechdesign.ga.GAException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskAssignmentManager {
    InputData inputData;

    public TaskAssignmentManager() {
        inputData = new InputData();
    }

    public boolean LoadData(String path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(path));

            inputData.TaskCount = Integer.parseInt(lines.get(0));
            inputData.ProcessorsCount = Integer.parseInt(lines.get(1));

            for (int i = 2; i < lines.size(); i++) {
                String[] values = lines.get(i).split(" ");
                int taskNo = Integer.parseInt(values[0]);

                List<Integer> times = new ArrayList<>();
                for (int j = 1; j < values.length; j++) {
                    times.add(Integer.parseInt(values[j]));
                }

                inputData.TaskProcessorTimes.add(times);
            }

        } catch (IOException e) {
            System.out.println("Error loading data from " + path);
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void Start() {
        try
        {
            GATaskAssignment gaTaskAssignment = new GATaskAssignment(inputData);
            gaTaskAssignment.run();
        }
        catch (GAException gae)
        {
            System.out.println(gae.getMessage());
        }
    }
}
