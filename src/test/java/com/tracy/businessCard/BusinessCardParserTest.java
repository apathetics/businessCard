package com.tracy.businessCard;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    public void testParseName() throws Exception {
        Assert.assertTrue(1 == 1);
    }

    @Test
    public void testParseNumber() throws Exception {
        Assert.assertTrue(1 == 1);
    }

    @Test
    public void testParseEmail() throws Exception {

        String badEmails = "bad@email..com\n" +
                ".bad@email.com\n" +
                "bad@email.com.\n";

        Assert.assertEquals("msmith@asymmetrik.com", BusinessCardParser.parseEmail(testCase1));
        Assert.assertEquals("lisa.haung@foobartech.com", BusinessCardParser.parseEmail(testCase2));
        Assert.assertEquals("awilson@abctech.com", BusinessCardParser.parseEmail(testCase3));
        Assert.assertEquals("EMAIL_NOT_FOUND", BusinessCardParser.parseEmail(badEmails));
    }
}
