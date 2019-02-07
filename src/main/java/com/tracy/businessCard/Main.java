package com.tracy.businessCard;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    final static Logger log = Logger.getLogger(Main.class);

    public static void main(String [] args) {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder documentBuilder = new StringBuilder();
        String line;

        System.out.println("Please enter your business card text. \n");

        // Reading it in and building the document string like this so that in the future, implementing batch processing
        // of business cards will be easy as well as any necessary formatting editing to the document for optimizations.
        try {
            while ((line = input.readLine()) != null && line.length() != 0) {
                documentBuilder.append(line);
                documentBuilder.append("\n");
            }
        } catch (IOException e) {
            log.error("Incorrect input.", e);
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                log.error("Input stream not closed correctly.", e);
            }
        }

        String document = documentBuilder.toString();

        ContactInfo contactInfo = BusinessCardParser.getContactInfo(document);

        System.out.println("Name: " + contactInfo.getName());
        System.out.println("Phone: " + contactInfo.getPhoneNumber());
        System.out.println("Email: " + contactInfo.getEmailAddress());

    }
}
