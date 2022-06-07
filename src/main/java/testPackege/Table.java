package testPackege;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Table {
    public void createCsvFile(int numberOfEmployee) throws IOException {
        Employee employee = new Employee();
        String csv = "Employee_list.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        String[] header = "firstname,middlename,lastname,email,group_name".split(",");
        writer.writeNext(header);
        for (int i = 0; i < numberOfEmployee; i++) {
            writer.writeNext(employee.getEmployee().toArray(new String[0]));
        }
        writer.close();
    }

    public String getInfoFromTable() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("Employee_list.csv"), ',', '"', 1);
        String[] nextLine = reader.readNext();
        String firstRow = Arrays.toString(nextLine);
        System.out.println(firstRow);
        return firstRow;
    }
}
