/*******************************************************************************
 * Copyright (c) 2019.
 * Dmitry Fedorov
 * https://github.com/dimon40001
 ******************************************************************************/

package com.dfedorov.importtool;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ImportTool {

    private static final boolean DEBUG = false;
    private static final String SPLITTER = "\\|";

    public static final String INPUT_FILE_NAME = "addresses.txt";
    public static final String OUTPUT_FILE_NAME = "output.txt";

    public static final String WRONG_PARAMETERS = "Error! Input file name must " +
            "be specified!\nUsing default value: " +
            INPUT_FILE_NAME;


    public static void main(String[] args) {

        ImportTool importTool = new ImportTool();
        importTool.run(args);
    }

    private void run(String[] args) {

        String inputFileName = getInputFileName(args);
        List<String> inputLines = readInputFile(inputFileName);
        Set<Address> addresses = parseAddresses(inputLines);
        writeOutputFile(addresses);

        if (DEBUG) {
            System.out.println("---------- Original lines:");
            for (String line : inputLines) System.out.println(line);
            System.out.println("---------- Processed lines:");
            for (Address address : addresses) System.out.println(address);
        }

    }

    private void writeOutputFile(Set<Address> addresses) {

        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_NAME)) {
            for (Address address : addresses) {
                fileWriter.write(address.toString() + System.lineSeparator());
            }
        } catch (IOException e) {

        }
    }

    private SortedSet<Address> parseAddresses(List<String> inputLines) {

        SortedSet<Address> addresses = new TreeSet<Address>();

        for (String line : inputLines) {
            String[] tokens = line.split(SPLITTER);
            int id = Integer.parseInt(tokens[0]);
            String firstName = tokens[1];
            String lastName = tokens[2];
            String street = tokens[3];
            String streetNumber = tokens[4];
            String postalCode = tokens[5];
            String town = tokens[6];
            String companyName = tokens[7];
            String phone = tokens[8];
            Address address;
            if ("".equals(companyName)) {
                address = new Address(id, firstName, lastName, street, streetNumber, postalCode, town, phone);
            } else {
                address = new CompanyAddress(id, firstName, lastName, street, streetNumber, postalCode, town, phone, companyName);
            }
            if (address.isValid()) {
                addresses.add(address);
            }
        }

        return addresses;
    }

    private List<String> readInputFile(String inputFileName) {

        List<String> inputLines = new ArrayList<>();

        try (FileReader fileReader = new FileReader(inputFileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (IOException e) {
        }

        int headerLineNumber = 0;
        inputLines.remove(headerLineNumber);

        return inputLines;
    }

    private String getInputFileName(String[] args) {

        if (args.length != 1) {
            System.out.println(WRONG_PARAMETERS);
            return INPUT_FILE_NAME;
        } else {
            return args[0];
        }
    }
}
