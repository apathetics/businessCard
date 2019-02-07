# Business Card Parser [![Build Status](https://travis-ci.com/apathetics/businessCard.svg?branch=master)](https://travis-ci.com/apathetics/businessCard)
This is a simple Java application that takes in a business card as a multi-line text.
Using a combination of regular expressions and name list, it parses the input to isolate the name, email, and phone number.
<br>

## Purpose (Why I built it)
The goal of this component is to parse the results of an optical character recognition (OCR) component and extract the name, phone number, and email address.
<br>

## How To Use
To clone and run this application, you will need [Git](https://git-scm.com) and [Maven](https://maven.apache.org)
installed on your computer.

From your command line:
```bash
# Clone this repository
$ git clone https://github.com/apathetics/businessCard.git

# Navigate to directory
$ cd businessCard

# Install dependencies using Maven
$ mvn clean install

# Run the application
$ mvn exec:java

```

If you are starting from an IDE, then you can run Main from the IDE instead.
<br>

## Example of Usage
An example of test case #3 given as an input to the command line interface.
<br>

[![Image from Gyazo](https://i.gyazo.com/3a559f076ab0db0d4bc3785e788db648.png)](https://gyazo.com/3a559f076ab0db0d4bc3785e788db648)
<br>

## Goals for the Future

1. Classifier training for the Stanford CoreNLP to reliably use for name parse solution when name list fails.
2. Expand phone number solution to include international numbers and non-US formats using regex or framework.
3. Write more test cases for edge cases and exceptions that may not currently be dealt with.

## Feedback
All in all, a very fun project to do. There's always more to expand on, 

I am always happy to receive feedback and suggestions on how to improve as an engineer, so please don't hesitate to
send me an email or leave an issue.

Thank you for stopping by!

