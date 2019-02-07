package com.tracy.businessCard;

import com.google.common.base.CharMatcher;

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
    // brute-force: use dictionary look-up for most common names
    // Open source NER solutions - is this allowed?
    public static String parseName(String document) {
        return "a";
    }

    // use regex to find 3 + 3 + 4 or 1 + 3 + 3 + 4 numbers
    // can be confused with fax - if "fax" in line, then skip.
    // there are so many different phone formats internationally - feasible with regex? Better option?
    // assuming american numbers only and that people wouldn't put over 10+ digits on one line unless it was a phone
    public static String parseNumber(String document) {

        // This pre-compiled regex pattern does add more lines to the code vs a string.matches("regex").
        // However, it is significantly faster - meaning it is more optimized and will scale better.
//        String tenDigitRegex = "\\d{10}";
//        String dashesDotsRegex = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
//        String areaBracesRegex = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
//
//        Pattern tenDigitsPattern = Pattern.compile(tenDigitRegex);
//        Pattern dashesDotsPattern = Pattern.compile(dashesDotsRegex);
//        Pattern areaBracesPattern = Pattern.compile(areaBracesRegex);

        String documentLines[] = document.split("\\r?\\n");

        for(String line: documentLines) {

//            Matcher tenDigitMatcher = tenDigitsPattern.matcher(line);
//            Matcher dashesDotsMatcher = dashesDotsPattern.matcher(line);
//            Matcher areaBracesMatcher = areaBracesPattern.matcher(line);

            if(line.toLowerCase().contains("fax")) {
                continue;
            }

            String extractedNumbers = CharMatcher.inRange('0', '9').retainFrom(line);

            if (extractedNumbers.length() == 10 || extractedNumbers.length() == 11) {
                return extractedNumbers;
            }
//            else if (tenDigitMatcher.matches()) {
//                return CharMatcher.inRange('0', '9').retainFrom(line);
//            }
//            else if (dashesDotsMatcher.matches()) {
//                return CharMatcher.inRange('0', '9').retainFrom(line);
//            }
//            else if (areaBracesMatcher.matches()) {
//                return CharMatcher.inRange('0', '9').retainFrom(line);
//            }

        }
        return "PHONE_NOT_FOUND";
    }

    // use regex to find @ in the line and validate email
    public static String parseEmail(String document) {

        // Assumes business card input is correct without any invalid email formats.
        // String simpleRegex = "^(.+)@(.+)$";

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
