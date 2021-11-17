package app;

import java.util.ArrayList;
import java.util.List;

public class InputData {
    public int TaskCount;
    public int ProcessorsCount;
    public List<List<Integer>> TaskProcessorTimes;

    public InputData() {
        TaskProcessorTimes = new ArrayList<>();
    }
}
