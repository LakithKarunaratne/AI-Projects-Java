package Crossval;

import au.edu.ecu.is.neural.*;

import java.util.*;

/**
 * Demo program showing a cross-validation testing for a MLP.
 * 
 * @author phi 
 * @version 2006/2
 */
public class MLPExample
{
    public static void main(String[] args) throws Exception
    {
        MLPExample mlpExample = new MLPExample();
        
        mlpExample.go();
    }
    
    public void go() throws Exception
    {
        Random random = new Random();
        
        // read the problem to solve from file
        ClassificationProblem problem = (ClassificationProblem)ClassificationProblem.readProblem(problems[PROBLEM_NUMBER]);
        int nInputs = problem.getNInputs();
        int nOutputs = problem.getNOutputs();
        
        // construct a mlp to try to solve it
        int[] layerSizes = new int[]{nInputs, HIDDEN, nOutputs};
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(layerSizes, random, LEARNING_RATE, MOMENTUM, ADAPTIVE);
        
        // set the training parameters
        mlp.setMinError(MIN_ERROR_PER_OUTPUT*problem.getNOutputs());
        mlp.setMaxEpochs(MAX_EPOCHS);
         
        mlp.setTrainingProblem(problem);
        mlp.setTestProblem(problem);

         mlp.showLearning();
        
        // perform cross-validation testing
//        CrossValidator cross = new CrossValidator(mlp, problem, random);
//        cross.validate(FOLDS, REPS);
        
        // *** While the example is running: What do MOMENTUM and ADAPTIVE do? *** //
    }
    
    private static final int FOLDS = 10;
    private static final int REPS = 3;
    
    private static final int HIDDEN = 5;
    private static final double LEARNING_RATE = 0.1;
    private static final double MOMENTUM = 0.95;
    private static final boolean ADAPTIVE = false;
    
    private static final double MIN_ERROR_PER_OUTPUT = 0.05;
    private static final int MAX_EPOCHS = 100;
    
    private static final int PROBLEM_NUMBER = 0;  // determines which problem to use
    
    private static final String[] problems = // names of files containing the problems
        {
            "D:\\PhD\\IS - AI - ACBT\\EclipseJavaProjects\\W5 - Neural Examples\\src\\Crossval\\crx.problem"
        };
}
