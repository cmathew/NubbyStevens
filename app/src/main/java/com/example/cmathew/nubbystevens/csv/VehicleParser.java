package com.example.cmathew.nubbystevens.csv;

import android.arch.persistence.db.SupportSQLiteDatabase;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class VehicleParser {
    private DealershipDatabase database;

    public VehicleParser(DealershipDatabase database) {
        this.database = database;
    }

    public List<Vehicle> parseCsvStream(InputStream csvStream) throws IOException {
        List<Vehicle> vehicleList = new ArrayList<>();

        CSVParser csvParser = CSVParser.parse(
                csvStream,
                Charset.forName("UTF-8"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
        );

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            String makeName = csvRecord.get("Make");
            String modelName = csvRecord.get("Model");
            String productionYearString = csvRecord.get("Year");
            int productionYear = Integer.parseInt(productionYearString);

            VehicleMake make = database.makeDao().findByName(makeName);
            if (make == null) {
                make = new VehicleMake(makeName);
                long makeId = database.makeDao().insertMake(make);
                make.setDatabaseId(makeId);
            }

            VehicleModel model = database.modelDao().findBy(modelName, make.getDatabaseId());
            if (model == null) {
                model = new VehicleModel(modelName, make.getDatabaseId());
                long modelId = database.modelDao().insertModel(model);
                model.setDatabaseId(modelId);
            }

            Vehicle vehicle = new Vehicle(productionYear, model.getDatabaseId());
            vehicleList.add(vehicle);
        }

        return vehicleList;
    }
}
