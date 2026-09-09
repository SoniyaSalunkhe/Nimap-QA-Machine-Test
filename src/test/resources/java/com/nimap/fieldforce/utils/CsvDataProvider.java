package com.nimap.fieldforce.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider {

    public static Object[][] readCsv(String resourcePath) {

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(resourcePath))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                rows.add(line.split(",", -1));
            }

        } catch (IOException e) {
            throw new RuntimeException(
                "Failed to read test data file: " + resourcePath, e
            );
        }

        return rows.toArray(new Object[0][]);
    }
}