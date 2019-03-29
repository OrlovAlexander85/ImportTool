/*******************************************************************************
 * Copyright (c) 2019.
 * Dmitry Fedorov
 * https://github.com/dimon40001
 ******************************************************************************/

package com.dfedorov.importtool;

import java.util.Objects;

public class Address implements Comparable<Address> {

    public static final String ERROR_MESSAGE = "%d - %s";
    public static final String OUTPUT_SEPARATOR = "-";

    private static final String REGEX_FIRSTNAME = "[a-zA-ZÄÖÜäöüß]*";
    private static final String REGEX_LASTNAME = REGEX_FIRSTNAME;
    private static final String REGEX_STREET = "[ a-zA-Z0-9ÄÖÜäöüß\\Q:;+*/.-\\E]*";
    private static final String REGEX_STREETNUMBER = "[ a-zA-ZÄÖÜäöüß0-9\\.-\\/]*";
    private static final String REGEX_POSTALCODE = "\\d{4,5}";
    private static final String REGEX_TOWN = REGEX_STREET;
    private static final String REGEX_PHONE = "[ 0-9,\\Q+.-\\E]{0,20}";

    private static final String WRONG_FIRSTNAME = "Wrong firstname!";
    private static final String WRONG_LASTNAME = "Wrong lastname!";
    private static final String WRONG_STREET = "Wrong street!";
    private static final String WRONG_STREETNUMBER = "Wrong street number!";
    private static final String WRONG_POSTALCODE = "Wrong postal code (ZIP)!";
    private static final String WRONG_TOWN = "Wrong town!";
    private static final String WRONG_PHONE = "Wrong phone!";

    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String town;
    private String phone;

    public Address(int id,
                   String firstName,
                   String lastName,
                   String street,
                   String streetNumber,
                   String postalCode,
                   String town,
                   String phone) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.town = town;
        this.phone = phone;
    }

    private boolean validateField(String value, String regex, String errorMessage) {

        boolean valid = true;
        if (!value.matches(regex)) {
            valid = false;
            System.out.printf(
                    Address.ERROR_MESSAGE + System.lineSeparator(),
                    id,
                    errorMessage
            );
        }

        return valid;
    }

    public boolean isValid() {

        return validateField(firstName, REGEX_FIRSTNAME, WRONG_FIRSTNAME) &
                validateField(lastName, REGEX_LASTNAME, WRONG_LASTNAME) &
                validateField(street, REGEX_STREET, WRONG_STREET) &
                validateField(streetNumber, REGEX_STREETNUMBER, WRONG_STREETNUMBER) &
                validateField(postalCode, REGEX_POSTALCODE, WRONG_POSTALCODE) &
                validateField(town, REGEX_TOWN, WRONG_TOWN) &
                validateField(phone, REGEX_PHONE, WRONG_PHONE);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, street, streetNumber, postalCode, town);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;

        return this.firstName.equals(address.firstName) &&
                this.lastName.equals(address.lastName) &&
                this.street.equals(address.street) &&
                this.streetNumber.equals(address.streetNumber) &&
                this.postalCode.equals(address.postalCode) &&
                this.town.equals(address.town);
    }

    @Override
    public String toString() {

        return String.join(OUTPUT_SEPARATOR,
                String.valueOf(id),
                firstName,
                lastName,
                street,
                streetNumber,
                postalCode,
                town,
                phone);
    }

    @Override
    public int compareTo(Address address) {

        if (this.equals(address)) return 0;

        int diff = this.lastName.compareTo(address.lastName);

        // if last name is the same then the order is not important
        return diff == 0 ? 1 : diff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
