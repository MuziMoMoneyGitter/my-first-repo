// Code is stored as 13template.java

//Muzi Seloisa stud no. 4569906
import java.io.File;
import java.text.*;
import java.util.Scanner;

class Node{
    int key;
    String data;

    public Node(int key, String data){
        this.key = key;
        this.data = data;
    }
}

public class timemethods {
    public static int N; //N = .....
    public static void main(String args[]){

        Node [] keys = readFile();//new

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0, time;
        double totalTime = 0.0;
        int n = N;
        int repetition, repetitions = 30;

        runTime = 0;
        for(repetition = 0; repetition < repetitions; repetition++) {

            int [] lookupkeys = randomValue();

            start = System.currentTimeMillis();

            // call the procedures to time here:
            linearsearch(keys, N);
            binarysearch (keys, N);
            // Figure out how to alter this guideline here,

            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTime += time;
            runTime2 += (time*time); }

        double aveRuntime = runTime/repetitions;
        double stdDeviation =
                Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

        System.out.printf("\n\n\fStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTime/1000 + "s.");
        System.out.println("Total time\u00b2  =           " + runTime2);
        System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000)
                + '\u00B5' + "s. ");

        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println(); }


    public static int linearsearch(Node [] array, int item){
        for (int i = 0; i < array.length; i++) {
            if(item == array[i].key){
                return i;
            }
        }
        return -1;
    }

    public static int binarysearch(Node[] arr, int item){
        int left = 1;
        int right = arr.length;

        while(right >= left){
            int mid = (left + right) / 2;

            if(arr[mid].key == item){
                return mid;
            } else if (item < arr[mid].key) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return - left;//if the item was not found
    }

    //A method to generate 30 random keys and place them in an array
    public static int [] randomValue(){
        int [] array = new int [30];
        for (int i = 0; i < 30; i++) {
            int random = (int)(Math.random()*32654) + 00001;
            array[i] = random;
        }
        return array;
    }

    //A method to sort for binary search
    public static void sort(int [] arr){
        for (int a = 0; a < arr.length - 1; a++) {
            for (int b = 0; b < arr.length - 1 - a; b++) {

                if(arr[b] > arr[b + 1]){
                    int temp = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = temp;
                }
            }
        }
    }

    public static Node [] readFile(){
        Node [] list = new Node[32654];
        int count  = 0;
        try{
            Scanner file = new Scanner(new File("ulysses-1.numbered"));

            while(file.hasNextLine()){
                String line = file.nextLine();

                if(line.length() >= 5){
                    int key = Integer.parseInt(line.substring(0, 5));
                    String data = "";

                    if(line.length() > 6){
                        data = line.substring(6);
                    }
                    list[count] = new Node(key, data);
                    count++;
                }
            }

            file.close();
        }catch(Exception e){
            System.out.println("The file could not be found");
        }

        Node [] keys = new Node[count];

        for (int i = 0; i < count; i++) {
            keys[i] = list[i];
        }
        return keys;

    }

}





