/**
 * Created by qiangzhou on 2017-11-22.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.print("The bin packing results of input ");
        ArrayList<Float> input = new ArrayList<Float>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("testFile1.txt")));

        try {
            String s = br.readLine();
            while (s != null) {
                float read = Float.parseFloat(s);
                input.add(read);
                System.out.print(read + " ");
                s = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        System.out.println("are:");
        System.out.println();

        FirstFitMethod firstFit = new FirstFitMethod(input);
        ArrayList<ArrayList<Float>> firstFitBins = firstFit.fit();
        int i;
        int j;
        System.out.println("First Fit Method:");
        for (i = 0; i < firstFitBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < firstFitBins.get(i).size(); j++) {
                System.out.print(firstFitBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        NextFitMethod nextFit = new NextFitMethod(input);
        ArrayList<ArrayList<Float>> nextFitBins = nextFit.fit();
        System.out.println("Next Fit Method:");
        for (i = 0; i < nextFitBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < nextFitBins.get(i).size(); j++) {
                System.out.print(nextFitBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        BestFitMethod bestFit = new BestFitMethod(input);
        ArrayList<ArrayList<Float>> bestFitBins = bestFit.fit();
        System.out.println("Best Fit Method:");
        for (i = 0; i < bestFitBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < bestFitBins.get(i).size(); j++) {
                System.out.print(bestFitBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        WorstFitMethod worstFit = new WorstFitMethod(input);
        ArrayList<ArrayList<Float>> worstFitBins = worstFit.fit();
        System.out.println("Worst Fit Method:");
        for (i = 0; i < worstFitBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < worstFitBins.get(i).size(); j++) {
                System.out.print(worstFitBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        FirstFitDecreasingMethod firstFitDecreasing = new FirstFitDecreasingMethod(input);
        ArrayList<ArrayList<Float>> firstFitDecreasingBins = firstFitDecreasing.fit();
        System.out.println("First Fit Decreasing Method:");
        for (i = 0; i < firstFitDecreasingBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < firstFitDecreasingBins.get(i).size(); j++) {
                System.out.print(firstFitDecreasingBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        NextFitDecreasingMethod nextFitDecreasing = new NextFitDecreasingMethod(input);
        ArrayList<ArrayList<Float>> nextFitDecreasingBins = nextFitDecreasing.fit();
        System.out.println("Next Fit Decreasing Method:");
        for (i = 0; i < nextFitDecreasingBins.size(); i++) {
            System.out.print("Bin" + (i + 1) + ": ");
            for (j = 0; j < nextFitDecreasingBins.get(i).size(); j++) {
                System.out.print(nextFitDecreasingBins.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}