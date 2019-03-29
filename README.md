# Import Tool Command-line Application 

---

## Scenario

A text file contains a list of customers and/or company addresses separated by a "pipe" symbol. 
Those addresses should be checked for the validity and correctly imported into the program.
The resulting data must be exported to another text-file for further processing.

## Task

According the the details specification below write a console program which imports data from the file, validates fields and builds an internal registry of addresses and exports it to a file.
The sample file is attached to this exercise and the name `addresses.txt`. 

## Details Specification

1. Your program should read the input file name from the command-line parameters and display a warning or error message in the other case.

1. Assume that file is encoded in UTF-8 and data is separated by "pipe" sign.

1. Implement Address and Company classes, where Company extends the Address.

1. Field validation must be implemented as follows:
    1. First name must contain only alphabetical characters;
    1. Last name must contain only alphabetical characters;
    1. Street only has the following characters: alpabetical, space, numbers and also `.:;+-*`;
    1. Street number only has the following characters: alpabetical, space, numbers and `.-/`;
    1. Only 4 or 5 digits are allowed in the ZIP code;
    1. Town only has the following characters: alpabetical, space, numbers and also `.:;+-*`;
    1. Permitted phone numbers contain numbers, spaces and characters: `+-.,` and should not be longer 20 symbols.

    In case of the incorrect field the error message should be written to the standard output in the format:
    `<id> - <explanation>`. Where:
    * `<id>` is record `id`
    * `<explanation>` is the reason why the record can not be processed. 

1. Ensure that duplicate records are not included in the final list.
    
    The duplicate record are those which have the same first name, last name, street, streetnumber, postalcode and town.

1. The resulting list should contain both personal addresses and companies.

1. Implement the method which prints the imported objects as a string with all fields separated by dash. 

1. The output should be sorted by the last name.

## Additional Requirements

* The code must be properly formatted and commented;
* Keep "Clean Code" in mind.