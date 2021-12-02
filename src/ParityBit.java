import java.util.Scanner;

public class ParityBit {

    Scanner sc;
    int epochs;
    int expectedOutput;
    float[] layerOne;
    float[] layerTwo;
    float[] layerThree;
    float[] dHiddenToInput;
    float[] dOutputToHidden;
    float[] aLayerOne;
    float[] aLayerTwo;
    float[] aLayerThree;
    float[][] layerOneWeights;
    float[][] layerTwoWeights;
    float learningRate;

    public ParityBit() {

        sc = new Scanner(System.in);
        System.out.println("What is the learning rate that you would like to use:");
        learningRate = sc.nextFloat();
        System.out.println("How many neurons would you like in the input layer:");
        layerOne = new float[sc.nextInt()];
        System.out.println("How many neurons would you like in the hidden layer:");
        layerTwo = new float[sc.nextInt()];
        System.out.println("How many neurons would you like in the output layer:");
        layerThree = new float[sc.nextInt()];
        System.out.println("Enter the number of epochs you would like to use:");
        epochs = sc.nextInt();
        dHiddenToInput = new float[layerTwo.length];
        dOutputToHidden = new float[layerThree.length];
        //Initialize arrays that hold the weights and generates the weights
        layerOneWeights = new float[layerTwo.length][layerOne.length];
        layerTwoWeights = new float[layerThree.length][layerTwo.length];
        generateWeights(layerOneWeights);
        generateWeights(layerTwoWeights);

        //feeds forward all layers
        aLayerOne = sigmoidFunction(layerOne);
        layerTwo = nextLayer(layerOneWeights, layerOne, layerTwo);
        aLayerTwo = sigmoidFunction(layerTwo);
        layerThree = nextLayer(layerTwoWeights, layerTwo, layerThree);
        aLayerThree = sigmoidFunction(layerThree);

        //performs the backprop on the layers
        dOutput();
        dHidden();
        reWeightOne();
        reWeightTwo();
    }

    private void reWeightOne() {
        float newWeight = 0;
        for (int i = 0; i < dHiddenToInput.length; i++) {
            newWeight = dHiddenToInput[i] * aLayerTwo[i] * learningRate;
            for (int j = 0; j < layerOneWeights[i].length; j++) {
                layerOneWeights[i][j] -= newWeight;
            }
        }

    }

    private void reWeightTwo() {
        float newWeight = 0;
        for (int i = 0; i < dOutputToHidden.length; i++) {
            newWeight = dOutputToHidden[i] * aLayerThree[i] * learningRate;
            for (int j = 0; j < layerTwoWeights[i].length; j++) {
                layerTwoWeights[i][j] -= newWeight;
            }
        }
    }

    private void dHidden() {
        for (int i = 0; i < dOutputToHidden.length; i++) {
            for (int j = 0; j < layerTwoWeights[i].length; j++) {
                dHiddenToInput[i] = dOutputToHidden[i] * layerTwoWeights[i][j];
                dHiddenToInput[i] = sigmoidPrime(dHiddenToInput[i]);
            }
        }

    }

    private void dOutput() {
        for (int i = 0; i < layerThree.length; i++) {
            dOutputToHidden[i] = (layerThree[i] - expectedOutput);
            dOutputToHidden[i] = (dOutputToHidden[i] * sigmoidPrime(layerThree[i]));
        }
    }

    private float[] nextLayer(float[][] currWeights, float[] currLayer, float[] nextLayer) {
        float temp = 0;
        for (int i = 0; i < currWeights.length; i++) {
            for (int j = 0; j < currWeights[i].length; j++) {
                temp += currWeights[i][j] * currLayer[j];
            }
            nextLayer[i] = temp;
        }
        return nextLayer;
    }

    private float costFunction(float x) {
        return (float) Math.pow((x - expectedOutput), 2);
    }

    private float[] sigmoidFunction(float[] currLayer) {
        for (int i = 0; i < currLayer.length; i++) {
            currLayer[i] = (float) (1 / (1 + Math.exp(-currLayer[i])));
        }
        return currLayer;
    }

    private float sigmoidPrime(float value) {
        return value * (1 - value);
    }

    private float[][] generateWeights(float[][] currentWeights) {
        for (int i = 0; i < currentWeights.length; i++) {
            for (int j = 0; j < currentWeights[i].length; j++) {
                currentWeights[i][j] = (float) (1 - Math.random() * ((1 - (-1))));
            }
        }
        return currentWeights;
    }

    public static void main(String[] args) {
        new ParityBit();
    }
}
