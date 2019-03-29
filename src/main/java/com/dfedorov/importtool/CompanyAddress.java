/*******************************************************************************
 * Copyright (c) 2019.
 * Dmitry Fedorov
 * https://github.com/dimon40001
 ******************************************************************************/

package com.dfedorov.importtool;

import java.util.Objects;

public class CompanyAddress extends Address {

    public static final String REGEX_COMPANY_NAME = "[ a-zA-Z0-9ÄÖÜäöüß\\Q:;+*/.-\\E]*";
    private static final String WRONG_COMPANY_NAME = "Wrong company name!";

    private String companyName;

    public CompanyAddress(int id,
                          String firstName,
                          String lastName,
                          String street,
                          String streetNumber,
                          String postalCode,
                          String town,
                          String phone,
                          String companyName) {
        super(id, firstName, lastName, street, streetNumber, postalCode, town, phone);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return super.toString() + Address.OUTPUT_SEPARATOR + companyName;
    }

    @Override
    public boolean isValid() {
        boolean valid = true;
        if (!companyName.matches(REGEX_COMPANY_NAME)) {
            valid = false;
            System.out.printf(
                    Address.ERROR_MESSAGE + System.lineSeparator(),
                    getId(),
                    WRONG_COMPANY_NAME
            );
        }

        return valid && super.isValid();
    }
}
