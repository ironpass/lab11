package tasktimer;

import static java.lang.System.out;
import java.util.Scanner;
import java.io.*;
import java.util.function.IntConsumer;
import java.util.function.Consumer;
import java.util.concurrent.atomic.*;  // hack, using AtomicInteger as accumulator

/**
 * Time how long it takes to perform some tasks
 * using different programming constructs.
 * 
 * TODO Improve this code by restructuring it to eliminate duplicate code.
 */
public class TaskTimer
{
    /**
     * Process all the words in a file using Scanner to read and parse input.
     * Display summary statistics and elapsed time.
     */
	static Scanner in = new Scanner(Dictionary.getWordsAsStream());
  
    
    /** 
     * Define a customer Consumer class that computes <b>both</b> the average 
     * and count of values.
     * An IntConsumer is a special Consumer interface the has an 'int' parameter 
     * in accept().
     */
    static class IntCounter implements IntConsumer {
        // count the values
        public int count = 0;
        // total of the values
        private long total = 0;
        /** accept consumes an int. In this method, count the value and add it to total. */
        public void accept(int value) { count++; total += value; }
        /** Get the average of all the values consumed. */
        public double average() { 
            return (count>0) ? ((double)total)/count : 0.0;
        }
        public int getCount() { return count; }
    }
    
    /**
     * Process all the words in a file (one word per line) using BufferedReader
     * and the lines() method which creates a Stream of Strings (one item per line).  
     * Then use the stream to compute summary statistics.
     * This is same as task3, except we use a Collector instead of Consumer.
     */
        
    static final int MAXCOUNT = 50_000; 
    
    /** Run all the tasks. */
    public static void main(String [] args) {
        execAndPrint(new Task1());
        execAndPrint(new Task2());
        execAndPrint(new Task3());
        execAndPrint(new Task4());
        execAndPrint(new Task5());
        execAndPrint(new Task6());
    }
    
    public static void execAndPrint(Runnable Task) {
    	out.println(Task.toString());
    	long starttime = System.nanoTime();
    	Task.run();
    	long stoptime = System.nanoTime();
    	out.printf("Elapsed time is %f sec\n",(stoptime - starttime)*1.0E-9 );
    }
    
}
