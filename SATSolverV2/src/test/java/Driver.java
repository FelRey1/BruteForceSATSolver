import java.io.File;
import java.io.IOException;

import Sat.*;


public class Driver {

    public static void main(String[] args) throws IOException {
    File file = new File("s20.cnf");

    Solver solver = new Solver(file);

    solver.readFile();

     solver.printFormula();

    }
}
