/*
 * Program purpose: convert BNF specification to CFG
 */
package bnftocfg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Branden Hitt
 */
public class BNFtoCFG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //variable declaration
        int productionCount=0;
        try{
            File jobFile = new File("bnf.txt");
            //open file of bnf productions to be converted
            try (Scanner reader = new Scanner(jobFile)) {
                //parse one production line at a time
                while (reader.hasNextLine()) {
                    //first line is just the job name  "Job 3"
                    //second line is the burst time    "12"
                    parseProductions(reader.nextLine());
                    productionCount++;
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("An error occurred");
        }
    }

    private static void parseProductions(String productionString) {
        String[] tokens = productionString.split(" ");
        String nonterminal = tokens[0];
        for(String token: tokens){
            System.out.print(token);
            if(token.equals(nonterminal)){
                //do nothing
            }else if(token.equals("::=")){
                //do nothing
            }else{
                
            }
        }
    }
    
}
