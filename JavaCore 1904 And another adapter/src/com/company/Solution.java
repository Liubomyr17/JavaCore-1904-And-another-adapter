package com.company;

/*
1904 And another adapter
Adapt Scanner to PersonScanner.
The adapter class is the PersonScannerAdapter.
Create a private final field Scanner fileScanner in the adapter class. Initialize the field in the constructor with one argument of the Scanner type.
The data in the file is stored as follows:
Ivanov Ivan Ivanovich 31 12 1950
Petrov Petr Petrovich 31 12 1957
A large number of people are stored in the file, the data of one person is on the same line. The read () method should only read data from one person.

Requirements:
1. PersonScanner must be an interface.
2. The PersonScannerAdapter class must implement the PersonScanner interface.
3. The PersonScannerAdapter class must contain a private fileScanner field of type Scanner.
4. The PersonScannerAdapter class must contain a constructor with the Scanner parameter.
5. The close () method of the PersonScannerAdapter class should delegate authority to the same fileScanner method.
6. The read () method of the PersonScannerAdapter class should read a line from a file, parse it, and return data of only one person, in the form of an object of the Person class.
*/

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;



public class Solution {

    public static void main(String[] args) {

    }
    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;
        public PersonScannerAdapter(Scanner scanner) {
            this.fileScanner = scanner;
        }
        @Override
        public Person read() throws IOException {
            Person person = null;
            if (fileScanner.hasNext()) {
                String s = fileScanner.nextLine();
                String[] parts = s.split(" ");

                String firstName = parts[1];
                String middleName = parts[2];
                String lastName = parts[0];

                Calendar calendar = new GregorianCalendar(Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[4])-1,
                        Integer.parseInt(parts[3]));

                person = new Person(firstName, middleName, lastName, calendar.getTime());
            }
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
    public static class Person {
        private String firstName;
        private String middleName;
        private String lastName;
        private Date birthDate;

        public Person(String firstName, String middleName, String lastName, Date birthDate) {
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }
        @Override
        public String toString() {
            return String.format("%s %s %s %s", lastName, firstName, middleName, birthDate.toString());
        }
    }
    public interface PersonScanner {
        Person read() throws IOException;
        void close() throws IOException;
    }
}




