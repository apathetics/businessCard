package com.tracy.businessCard;

import com.google.common.base.CharMatcher;
import edu.stanford.nlp.simple.Sentence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessCardParser {

    public static ContactInfo getContactInfo(String document) {

        String name = parseName(document);
        String number = parseNumber(document);
        String email = parseEmail(document);

        return new ContactInfo(name, number, email);
    }

    // Personally, I would make these auxiliary methods private and just write a single test for getContactInfo().
    // For the sake of demonstrating fine-grained testing though, I've made them public.

    // Named Entity Problem - NLP hard problem
    // brute-force: use dictionary look-up for most common names
    // open source NER solutions - is this allowed?
    // using Stanford CoreNLP - it thinks analytic developer is a PERSON...could train the classifier. Out of scope for this challenge.
    public static String parseName(String document) {

        HashSet<String> nameSet = new HashSet<String>();

        // Read in most common name list.
        // We can always go back and expand on this list, it is only 3,500 most common US-English names right now.
        // HashSet is O(1) search and add, so it performs well.
        // If scaling, look into using InputBuffer and delimiting the bytes ourselves instead of storing String objects.

        try {
            Scanner file = new Scanner(new File(BusinessCardParser.class.getClassLoader().getResource("names.txt").getFile()));
            while(file.hasNext()) {
                nameSet.add(file.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid name file path.");
        }

        String documentLines[] = document.split("\\r?\\n");

        // Brute force - List Lookup Method
        // If line is 2 or 3 words long (length of a name), then see if it matches dictionary.
        // Optimize this somehow? - many things on business card are 2-3 words long. HashSet is O(1) search and add.
        for(String line : documentLines) {
            String[] lineSplit = line.split("\\s+");

            if (lineSplit.length == 2) {
                if(nameSet.contains(lineSplit[0]) || nameSet.contains(lineSplit[1])) {
                    return line;
                }
            }

            if (lineSplit.length == 3) {
                if(nameSet.contains(lineSplit[0]) || nameSet.contains(lineSplit[1]) || nameSet.contains(lineSplit[2])) {
                    return line;
                }
            }
        }

        // If all else fails, use Stanford CoreNLP NER detection to identify person.
        // Not always accurate and very slow (15 seconds for a name). Use as a last resort for accuracy.
        // VERY often identifies titles/positions/creatures as "PERSON", so I'm commenting it out until it can be trained.
        /**
            for(String line: documentLines) {
                Sentence sentence = new Sentence(line);
                if(sentence.nerTags().contains("PERSON")) {
                    return line;
                }
            }
         **/

        return "NAME_NOT_FOUND";
    }

    // use regex to find 3 + 3 + 4 or 1 + 3 + 3 + 4 numbers
    // can be confused with fax - if "fax" in line, then skip.
    // there are so many different phone formats internationally - feasible with regex? Better option?
    // cheeky brute-force: assume american numbers only and that 10+ digits would never occur on one line unless it was a phone
    public static String parseNumber(String document) {

        String documentLines[] = document.split("\\r?\\n");

        for(String line: documentLines) {

            // Add blacklist for anything that might contain 10+ numbers on a business card.
            if(line.toLowerCase().contains("fax")) {
                continue;
            }

            String extractedNumbers = CharMatcher.inRange('0', '9').retainFrom(line);

            if (extractedNumbers.length() == 10 || extractedNumbers.length() == 11) {
                return extractedNumbers;
            }
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
