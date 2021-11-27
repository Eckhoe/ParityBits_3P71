import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    Name: David Bailey
    ID: 6675482
    Date: November 19, 2021
 */

public class ParityBit {
    Scanner sc;
    int epochs;
    double learningRate;
    double meanSquareError;
    double[] input; //represents four neurons which take the input
    double[] hidden;//holds neurons used for the hidden layer
    double output;//holds the output value

    public ParityBit() {

        input = new double[4];
        sc = new Scanner(System.in);
        System.out.println("How many neurons would you like in the hidden layer:");
        hidden = new double[sc.nextInt()];
        System.out.println("Enter the number of epochs you would like to use:");
        epochs = sc.nextInt();
        try {
            sc = new Scanner(new File("data.txt"));
            for (int i = 0; i < epochs; i++) {
                //read the line from file

                if (i % 500 == 0)
                    iterationInfo(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sc.close();
    }

    private double generateWeights(){
        return (1 - Math.random() * ((1 - (-1))));
    }

    private double forwardPass(int index){
        return 0;
    }

    //multiply a1 with w2 (weights)
    private double multiplyMatrices(){
        double x = 0;

        return x;
    }

    private double[] activationFunction(){
        double [] array = new double[input.length];
        for(int i = 0; i < array.length; i++){
            array[i] = sigmoidFunction(input[i]);
        }
        return array;
    }

    //this is the sigmoid function
    private double sigmoidFunction(double x){
        return (1 /(1+ Math.exp(-x)));
    }

    private void iterationInfo(int epoch){
        System.out.println("Epoch: " + epoch +" Hidden Nodes: " + hidden.length + " Learning Rate: " + learningRate + " Mean Square Error: " + meanSquareError);
    }

    public static void main(String[] args) {
        new ParityBit();
    }

}

/*
        1. Even number of ones, parity bit is one
        2. Odd number of ones, parity bit is zero
        3. Should maintain an odd number of ones from the original 8 bits when applying the parity bit (odd parity)
            -Standard PC memory is odd parity
        4. If there are an odd number of ones and parity bit is set to one, system will be looking for even parity


        IMPLMENTATION: Train feed-forward neural net with back prop. to act as a parity checking system
     */
