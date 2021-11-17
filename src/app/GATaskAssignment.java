package app;

import com.softtechdesign.ga.Crossover;
import com.softtechdesign.ga.GAException;
import com.softtechdesign.ga.GAString;

public class GATaskAssignment extends GAString {
    private InputData inputData;

    private static int geneToProcessNo(char gene) {
        return ((int)gene - 'A');
    }

    private static char processNoToGene(int processorNo) {
        return (char)(processorNo + 'A');
    }

    private static String genGeneSpace(int processorCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < processorCount; i++) {
            sb.append(processNoToGene(i));
        }
        return sb.toString();
    }

    public GATaskAssignment(InputData inpDat) throws GAException {
        super(inpDat.TaskCount,
                100,
                0.7,
                10,
                200,
                0,
                20,
                0.06,
                0,
                genGeneSpace(inpDat.ProcessorsCount),
                Crossover.ctTwoPoint,
                true);

        this.inputData = inpDat;
        super.initPopulation();
    }

    @Override
    protected void initPopulation() {
        //Super constructor calls this method and its needs assigned value of inputData (in getFitness), so call was moved to constructor of GATaskAssignment
    }

    @Override
    protected double getFitness(int i) {
        String chromosome = getChromosome(i).getGenesAsStr();

        double max = 0;
        for (int processorNo = 0; processorNo < inputData.ProcessorsCount; processorNo++) {
            char processorChar = processNoToGene(processorNo);
            double now = 0;

            for (int index = 0; index < chromosome.length(); index++) {
                if (chromosome.charAt(index) == processorChar) {
                    now += inputData.TaskProcessorTimes.get(index).get(processorNo);
                }
            }

            if (now > max)
                max = now;
        }

        return -max;
    }
}
