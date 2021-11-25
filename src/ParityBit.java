import java.util.Scanner;

/*
    Name: David Bailey
    ID: 6675482
    Date: November 19, 2021
 */

public class ParityBit {
    Scanner sc;
    int epochs;
    float learningRate;
    float meanSquareError;
    float[] input; //represents four neurons which take the input
    float[] hidden;//holds neurons used for the hidden layer
    float output;//holds the output value

    public ParityBit() {

        input = new float[4];
        sc = new Scanner(System.in);
        System.out.println("How many neurons would you like in the hidden layer: \n");
        hidden = new float[sc.nextInt()];
        System.out.println("Enter the number of epochs you would like to use: \n");
        epochs = sc.nextInt();
        //read data from file
        for(int i = 0; i < epochs; i++){
            //do computation
            if(i%500 == 0)
                iterationInfo(i);
        }



        sc.close();
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
