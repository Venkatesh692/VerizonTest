
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class TestApp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        Double ds = 0.0;
        int counter = 0;
        try {
            String tmpdir = System.getProperty("java.io.tmpdir");
            File inputFile = new File(tmpdir + File.separator + "input.txt");
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            String equation = args[0];
            // for each line.
            while(null != line) {
                // ignore first line
                if (counter == 0) {
                    counter = counter + 1;
                    line = reader.readLine();
                    continue;
                }
                // Split the input values
                String[] inputs = line.split(",");
                // perform the operation as per equation
                Double value = performOperation(equation, inputs[0]);
                // get delta square value
                ds = ds + (value - Double.parseDouble(inputs[1])) * (value - Double.parseDouble(inputs[1]));
                // track the number of records
                counter = counter + 1;
                // Process next line
                line = reader.readLine();
            }
            // get the mean
            Double result = ds/counter;
            DecimalFormat df = new DecimalFormat("0.00");
            // print the out put
            System.out.println(df.format(result));

        } catch (Exception e) {
            System.out.print("Exception :: " +e.getMessage());
        } finally {
            if (reader != null)
                reader.close();
        }

    }

    private static Double performOperation(String equation, String inputX) {
        // Parse the equation
        String[] equations = equation.split(" ");
        // get the first value
        String firstVal = equations[0];
        // get the operation to be performed
        String op = equations[1];
        // get the second value
        String secondVal = equations[2];
        // replace character x with empty value
        String cleanVal = secondVal.replace("x", "");
        switch(op) {
            case "+":
                return Double.parseDouble(firstVal) + (Double.parseDouble(cleanVal) * Double.parseDouble(inputX));
            case "-":
                return Double.parseDouble(firstVal) - (Double.parseDouble(cleanVal) * Double.parseDouble(inputX));
        }
        return 0.0;
    }

}
