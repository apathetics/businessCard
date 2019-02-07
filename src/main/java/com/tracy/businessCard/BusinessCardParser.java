package com.tracy.businessCard;

public class BusinessCardParser {

    public static ContactInfo getContactInfo(String document) {

        String name = parseName(document);
        String number = parseNumber(document);
        String email = parseEmail(document);

        ContactInfo parsedContactInfo = new ContactInfo(name, number, email);

        return parsedContactInfo;
    }

    // Named Entity Problem - NLP
    // brute-force: use dictionary look-up for most common names?
    private static String parseName(String document) {
        return "a";
    }

    // use regex to find 3 + 3 + 4 or 1 + 3 + 3 + 4 numbers
    // Can be confused with fax - if "fax" in line, then skip.
    private static String parseNumber(String document) {
        return "b";
    }

    // use regex to find @ in the line
    private static String parseEmail(String document) {
        return "c";
    }
}
