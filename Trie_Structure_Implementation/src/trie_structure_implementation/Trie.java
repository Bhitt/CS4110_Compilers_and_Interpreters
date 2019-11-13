/*
 *      Insert: O(log52n)    O(log52n)
 * 
 *      Example: absolute, abs, ...
 *                          A   B   C  ...    Z   a  ...    z
 *      Switch:  array = { -1, -1, -1, ... , -1,  0, ... , -1}
 *
 *                          0   1   2   3   4   5   6   7   8
 *      Symbol:  array = {  b,  s,  o,  l,  u,  t,  e,  @,  @}
 *      Next  :  array = {   ,   ,  8, ...                   } 
 */
package trie_structure_implementation;

/**
 * 
 * @author bhitt
 */
public class Trie {
    //Properties
    private int maxTransition = 20;
    private final int SIZE = 52;
    private final char delimeter = '@';
    private final int switchDef = -1;
    private final char symbolDef = '#';
    private final int nextDef = -1;
    // arrays for trie table
    private int[]  switchA;
    private char[] symbolA;
    private int[]  nextA;
    //indexing
    private int switchIndex;  //index of char after translated to int
    private int symbolIndex;  //next available slot in the symbol table
    
    //default constructor
    public Trie(){
        //initialize arrays
        switchA = new int[SIZE];
        symbolA = new char[maxTransition];
        nextA   = new int[maxTransition];
        //initialize all arrays
        initArrays();
        //intialize index values
        switchIndex = 0;
        symbolIndex = 0;
    }
    
    // Insert method to insert a keyword/identifier into the table
    public void insert(String key){
        //check to see if arrays should be larger
        if(symbolIndex+key.length()>maxTransition-1){
            resize();
        }
        //temporary index
        int tempIndex = symbolIndex;
        //boolean for following
        boolean following = false;
        //get the index for the switch array
        switchIndex = charToInt(key);
        //check to see if first letter has been used yet
        if(switchA[switchIndex] == switchDef){ //first letter not used yet
            // point at the next available slot in the symbol table
            switchA[switchIndex] = symbolIndex;
            // follow that point and store the remaining symbols
            for(int i=1;i<key.length();i++){
                symbolA[symbolIndex] = key.charAt(i);
                symbolIndex++;
            }
            //add a delimeteer
            symbolA[symbolIndex] = delimeter;
            symbolIndex++;
        }else{ //first letter has already been used, follow the value at index
            tempIndex = switchA[switchIndex];
            for(int i=1;i<key.length();i++){
                //check if the letters match
                if(symbolA[tempIndex] == key.charAt(i)){
                    tempIndex++;
                }else if(symbolA[tempIndex] == symbolDef){
                    symbolA[symbolIndex] = key.charAt(i);
                    tempIndex++;
                    symbolIndex++;
                }else{  //the letters don't match
                    following = true;
                    //follow path to next available slot
                    while(following){
                        //check if there is aleady a path in the next array
                        if(nextA[tempIndex] == nextDef){ //no path to follow, add new
                            nextA[tempIndex] = symbolIndex;
                            tempIndex = symbolIndex;
                            following = false;
                        }else{  //follow the path
                            tempIndex = nextA[tempIndex];
                        }
                    }
                    //put next letter in the slot
                    symbolA[tempIndex] = key.charAt(i);
                    tempIndex++;
                    symbolIndex++;
                }
            }
            //add new delimeter
            if(symbolA[tempIndex] == symbolDef){
                    symbolA[symbolIndex] = delimeter;
                    symbolIndex++;
            } else {
                following = true;
                while(following){
                    //check if there is aleady a path in the next array
                    if(nextA[tempIndex] == nextDef){ //no path to follow, add new
                        nextA[tempIndex] = symbolIndex;
                        following = false;
                    }else{  //follow the path
                        tempIndex = nextA[tempIndex];
                    }
                }
                //space is empty, add new delimeter
                symbolA[symbolIndex] = delimeter;
                symbolIndex++;
            }
        }
    }
    
    // Print out the contents of the Trie table
    public void printTrie(){
        //print switch
        printSwitch();
        //print symbol and next arrays
        printSymbolNext();
    }
    
    public void printSwitch(){
        //print the table using beautiful formatting
        for(int i=0;i<52;i++){
            if(i == 0) {
                System.out.print("        ");
                for(char ch='A';ch<='T';ch++) {
                    //System.out.print(String.format("%3d",ch));
                    System.out.print(String.format("%4c",ch));
                }
                System.out.println();
                System.out.print("switch: ");
            }
            if(i == 20){
               System.out.print("        ");
                for(char ch='U';ch<='Z';ch++) {
                    //System.out.print(String.format("%3d",ch));
                    System.out.print(String.format("%4c",ch));
                }
                for(char ch='a';ch<='n';ch++) {
                    //System.out.print(String.format("%3d",ch));
                    System.out.print(String.format("%4c",ch));
                }
                System.out.println();
                System.out.print("switch: ");
            }
            if(i == 40) {
                System.out.print("        ");
                for(char ch='o';ch<='z';ch++) {
                    //System.out.print(String.format("%3d",ch));
                    System.out.print(String.format("%4c",ch));
                }
                System.out.println();
                System.out.print("switch: ");
            }
            System.out.print(String.format("%4d",switchA[i]));
            if((i+1)%20 == 0) {
                System.out.println();
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }
    
    public void printSymbolNext(){
        //print the other table with even more beautiful formatting
        boolean stop = false;
        int start = 0;
        int end = 20;
        while(!stop){
            if(end > symbolIndex){
                end = (symbolIndex%20)+start;
                stop = true;
            }
            System.out.print("        ");
            for(int i=start;i<end;i++){
                System.out.print(String.format("%4d",i));
            }
            System.out.println();
            System.out.print("symbol: ");
            for(int i=start;i<end;i++){
                System.out.print(String.format("%4c",symbolA[i]));
            }
            System.out.println();
            System.out.print("next:   ");
            for(int i=start;i<end;i++){
                if(nextA[i]==nextDef) System.out.print(String.format("%4c",' '));
                else System.out.print(String.format("%4d",nextA[i]));
            }
            System.out.println();
            System.out.println();
            start+=20;
            end+=20;
        }
    }
    
    // initialize array values to default
    public void initArrays(){
        for(int i=0;i<52;i++){
            switchA[i] = switchDef;
        }
        for(int i=0;i<symbolA.length;i++){
            symbolA[i] = symbolDef;
        }
        for(int i=0;i<nextA.length;i++){
            nextA[i] = nextDef;
        }
    }
    
    public void resize(){
        //create new arrays that are double the current size
        char[] temp = new char[2*maxTransition];
        int[] temp2 = new int[2*maxTransition];
        //copy the old ones over
        for(int i=0;i<maxTransition;i++){
            temp[i] = symbolA[i];
            temp2[i] = nextA[i];
        }
        //set the remaining values to the default
        for(int i=maxTransition;i<2*maxTransition;i++){
            temp[i] = symbolDef;
            temp2[i] = nextDef;
        }
        //point old pointers at new pointers
        symbolA = temp;
        nextA = temp2;
        //adjust the size
        maxTransition = 2*maxTransition;
        System.out.println("new size:"+maxTransition);
    }
    
    // translate a char to its integer equivalent in the trie table
    public int charToInt(String k){
        //get the first character in string
        int num = k.charAt(0);
        //check if char is lowercase
        if(num >= 97 && num <= 122) num -= 71;      //lower
        else num-= 65;                              //upper 
        //return integer after translating to table
        return num;
    }
    
}
