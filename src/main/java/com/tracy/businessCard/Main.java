package com.tracy.businessCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String [] args) {

        System.out.println("Please enter your business card text. \n");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder documentBuilder = new StringBuilder();
        String line;

        try {
            while ((line = input.readLine()) != null && line.length() != 0) {
                documentBuilder.append(line);
                documentBuilder.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Incorrect input.");
        }

        String document = documentBuilder.toString();

        System.out.println(document);

        ContactInfo contactInfo = BusinessCardParser.getContactInfo(document);

        System.out.println("Name: " + contactInfo.getName());
        System.out.println("Phone: " + contactInfo.getPhoneNumber());
        System.out.println("Email: " + contactInfo.getEmailAddress());
    }
}
