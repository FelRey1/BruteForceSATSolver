package Sat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Felix Reyes
 * @date 2/27/22
 * a class that solves a boolean formula from a cnf formatted txt file
 */
public class Solver {

    /**
     * the file to be read and tested
     */
    File file;

    /**
     * the formula of the file
     */
     Formula formula = new Formula();

    /**
     * the constructor for the Solver class
     * @param file to be read and tested
     */
    public Solver(File file){
        this.file = file;
    }

    /**
     * reads the file of the Solver class and adds all variables to corresponding clauses, and adds the clauses to the formula
     * adds a false to the assignmentList for the formula for each variable read
     * @throws FileNotFoundException
     */
    public void readFile() throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        Clause c = new Clause();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(!line.toLowerCase().startsWith("p") && !line.toLowerCase().startsWith("c")) {
                String[] lits = line.split(" ");
                for(String lit : lits){
                    if(!lit.equalsIgnoreCase("")) {
                        int num = Integer.parseInt(lit);
                        if (num != 0) {
                            c.addVariable(num);
                        }
                        if (num == 0) {
                            formula.addClause(c);
                            c = new Clause();
                        }
                    }
                }
            }
            else if(line.toLowerCase().startsWith("p")){
                    String[] l = line.split(" ");
                    int n = Integer.parseInt(l[2]);
                    for(int i = 0; i < n; i++){
                        formula.addVal();
                    }
            }
        }
    }

    /**
     * runs the formula isSat method
     * @return the result of the formula isSat method
     */
    private boolean isSat(){
        return formula.isSat();
    }


    /**
     * prints the formula and "is satisfiable" iff the isSat method returns true
     * or "is not satisfiable" iff the isSat method returns false
     */
    public void printFormula(){
        System.out.print("the formula: " + formula + "\n");
        if(isSat()){
            System.out.println("is satisfiable");
        }
        else{
            System.out.println("is not satisfiable");
        }
    }

}
