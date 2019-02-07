package com.tracy.businessCard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessCardParser {

    public static ContactInfo getContactInfo(String document) {

        String name = parseName(document);
        String number = parseNumber(document);
        String email = parseEmail(document);

        ContactInfo parsedContactInfo = new ContactInfo(name, number, email);

        return parsedContactInfo;
    }

    // Personally, I would make these auxiliary methods private and just write a single test for getContactInfo().
    // For the sake of demonstrating fine-grained testing though, I've made them public.

    // Named Entity Problem - NLP
    // brute-force: use dictionary look-up for most common names?
    public static String parseName(String document) {
        return "a";
    }

    // use regex to find 3 + 3 + 4 or 1 + 3 + 3 + 4 numbers
    // Can be confused with fax - if "fax" in line, then skip.
    public static String parseNumber(String document) {
        return "b";
    }

    // use regex to find @ in the line and validate email
    public static String parseEmail(String document) {

        // Assumes business card input is correct without any invalid email formats.
        String simpleRegex = "^(.+)@(.+)$";

        // Catches double dots, leading dots, and trailing dots.
        // Standard OWASP regex for emails.
        String strictRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(strictRegex);

        // Covers Windows and Mac/Linux new lines.
        String documentLines[] = document.split("\\r?\\n");

        for(String email: documentLines) {
            Matcher matcher = pattern.matcher(email);

            // successful match found - exit
            if(matcher.matches()) {
                return email;
            }
        }

        // Error Case - email not found.
        return "EMAIL_NOT_FOUND";
    }
}
