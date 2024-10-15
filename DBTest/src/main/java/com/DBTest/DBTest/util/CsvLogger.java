package com.DBTest.DBTest.util;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvLogger {

    private static final String CSV_FILE_PATH = "QueryDoc.csv";

    public static void logQuery(String queryType, int rowsAffected, long timeTaken, String database, String queryId) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String[] record = {queryType, String.valueOf(rowsAffected), String.valueOf(timeTaken), database, queryId};
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}