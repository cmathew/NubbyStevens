package com.example.cmathew.nubbystevens.csv;

import com.example.cmathew.nubbystevens.Vehicle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class VehicleParser {
    public List<Vehicle> parseCsvStream(InputStream csvStream) throws IOException {
        CSVParser csvParser = CSVParser.parse(
                csvStream,
                Charset.forName("UTF-8"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
        );

        List<Vehicle> vehicleList = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            String makeName = csvRecord.get("Make");
            String modelName = csvRecord.get("Model");
            String productionYearString = csvRecord.get("Year");
            int productionYear = Integer.parseInt(productionYearString);



            for (String record : csvRecord) {
                nodeConnectionList.add(value);
            }

            graphConnectionList.add(nodeConnectionList);
        }

        boolean[][] graphArray = new boolean[graphConnectionList.size()][graphConnectionList.size()];
        for (int i = 0; i < graphConnectionList.size(); i++) {
            List<Boolean> nodeConnectionList = graphConnectionList.get(i);
            for (int j = 0; j < nodeConnectionList.size(); j++) {
                graphArray[i][j] = nodeConnectionList.get(j);
            }
        }

        return graphArray;
    }
}
