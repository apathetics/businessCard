package com.tracy.businessCard;

import org.junit.Assert;
import org.junit.Test;

public class BusinessCardParserTest {

    String testCase1 = "ASYMMETRIK LTD\n" +
            "Mike Smith\n" +
            "Senior Software Engineer\n" +
            "(410)555-1234\n" +
            "msmith@asymmetrik.com";

    String testCase2 = "Foobar Technologies\n" +
            "Analytic Developer\n" +
            "Lisa Haung\n" +
            "1234 Sentry Road\n" +
            "Columbia, MD 12345\n" +
            "Phone: 410-555-1234\n" +
            "Fax: 410-555-4321\n" +
            "lisa.haung@foobartech.com";

    String testCase3 = "Arthur Wilson\n" +
            "Software Engineer\n" +
            "Decision & Security Technologies\n" +
            "ABC Technologies\n" +
            "123 North 11th Street\n" +
            "Suite 229\n" +
            "Arlington, VA 22209\n" +
            "Tel: +1 (703) 555-1259\n" +
            "Fax: +1 (703) 555-1200\n" +
            "awilson@abctech.com";

    @Test
    public void testParseName() {
        String badNames ="Alien Booper\n" +
                "The Big Evil Lord\n" +
                "Boopityboppity Bo\n";

        Assert.assertEquals("Mike Smith", BusinessCardParser.parseName(testCase1));
        Assert.assertEquals("Lisa Haung", BusinessCardParser.parseName(testCase2));
        Assert.assertEquals("Arthur Wilson", BusinessCardParser.parseName(testCase3));
        Assert.assertEquals("NAME_NOT_FOUND", BusinessCardParser.parseName(badNames));

    }

    @Test
    public void testParseNumber() {

        String badNumbers = "Fax: 111-111-1111\n" +
                "1234567bobby@gmail.com\n" +
                "137 West Anderson Ln. 78705 Austin, TX";

        Assert.assertEquals("4105551234", BusinessCardParser.parseNumber(testCase1));
        Assert.assertEquals("4105551234", BusinessCardParser.parseNumber(testCase2));
        Assert.assertEquals("17035551259", BusinessCardParser.parseNumber(testCase3));
        Assert.assertEquals("PHONE_NOT_FOUND", BusinessCardParser.parseNumber(badNumbers));
    }

    @Test
    public void testParseEmail() {

        String badEmails = "bad@email..com\n" +
                ".bad@email.com\n" +
                "bad@email.com.\n";

        Assert.assertEquals("msmith@asymmetrik.com", BusinessCardParser.parseEmail(testCase1));
        Assert.assertEquals("lisa.haung@foobartech.com", BusinessCardParser.parseEmail(testCase2));
        Assert.assertEquals("awilson@abctech.com", BusinessCardParser.parseEmail(testCase3));
        Assert.assertEquals("EMAIL_NOT_FOUND", BusinessCardParser.parseEmail(badEmails));
    }

    // A bit redundant with all the fine-grained unit tests already done on the auxiliary methods.
    // This will validate that ContactInfo works correctly though and is on a higher level (if we want to limit tests).
    @Test
    public void testGetContactInfo() {

        ContactInfo testInfo1 = BusinessCardParser.getContactInfo(testCase1);
        ContactInfo testInfo2 = BusinessCardParser.getContactInfo(testCase2);
        ContactInfo testInfo3 = BusinessCardParser.getContactInfo(testCase3);

        Assert.assertEquals("msmith@asymmetrik.com", testInfo1.getEmailAddress());
        Assert.assertEquals("lisa.haung@foobartech.com", testInfo2.getEmailAddress());
        Assert.assertEquals("awilson@abctech.com", testInfo3.getEmailAddress());

        Assert.assertEquals("4105551234", testInfo1.getPhoneNumber());
        Assert.assertEquals("4105551234", testInfo2.getPhoneNumber());
        Assert.assertEquals("17035551259", testInfo3.getPhoneNumber());

        Assert.assertEquals("Mike Smith", testInfo1.getName());
        Assert.assertEquals("Lisa Haung", testInfo2.getName());
        Assert.assertEquals("Arthur Wilson", testInfo3.getName());
    }
}
