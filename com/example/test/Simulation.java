package com.example.test;
import java.util.ArrayList;
import java.util.Random;

// this code is generated under the help of github copilot chat.
// it's used to simulate the property value change of 100 people in 40 years.
// the property value is affected by the lucky and unlucky events.
//  the lucky event is affected by the skill capability of the person.
//  the unlucky event is not affected by the skill capability of the person.
//  the skill capability of the person is a random number between 0.64 and 0.84.
//  the property value of the person is 10 at the beginning.
//  the property value of the person will be doubled if the person has a lucky event and this person's skill capability is greater than a random number between 0 and 1.
//  the property value of the person will be halved if the person has an unlucky event.
//  the property value of the person will not change if the person has a lucky event but this person's skill capability is less than a random number between 0 and 1.
//  the property value of the person will not change if the person has no lucky event and no unlucky event.
// at the end of simulation after 40 years, the program will print the skill capability and property value of each person.
// and print out the richest and poorest person's information.
//  the richest person is the person who has the highest property value.
//  the poorest person is the person who has the lowest property value.
//  if there are more than one person who has the highest property value, the program will print out all of them.
//  if there are more than one person who has the lowest property value, the program will print out all of them.
//  if there is no person who has the highest property value, the program will print out "no richest person".

import com.example.test.EventItem;

public class Simulation {
    public static void main(String[] args) {
        int peopleCount = 100;
        int age = 20;
        int maxAge = 60;
        double[] skillCapability = new double[peopleCount];
        double[] propertyValue = new double[peopleCount];

        ArrayList<EventItem>[] arrayOfEventList = new ArrayList[peopleCount];

        for (int i = 0; i < arrayOfEventList.length; i ++){
            arrayOfEventList[i] = new ArrayList<EventItem>();
        }
        //print a prompt to inform user that program starts
        System.out.println("Program starts");
        // Initialize skillCapability and propertyValue arrays
        Random random = new Random();
        double mean = 0.74;
        double stdDeviation = 0.1;
        for (int i = 0; i < peopleCount; i++) {
            skillCapability[i] = random.nextGaussian() * stdDeviation + mean;
            propertyValue[i] = 10;
        }
        // Run simulation for 40 years
        for (int year = 1; year <= 40; year++) {
            for (int i = 0; i < peopleCount; i++) {
                // Check if person has reached max age
                if (age >= maxAge) {
                    break;
                }

                // Generate 2 random events for the person
                boolean[] events = new boolean[2];
                for (int j = 0; j < 2; j++) {
                    events[j] = random.nextBoolean();
                }

                // Update property value based on events and record events in arrayOfEventList
                for (int j = 0; j < 2; j++) {
                    boolean isLuckyEvent = events[j];
                    boolean isUnluckyEvent = !isLuckyEvent;

                    if (isLuckyEvent) {
                        if (random.nextDouble() < skillCapability[i]) {
                            propertyValue[i] *= 2;
                            arrayOfEventList[i].add(new EventItem("G", age));
                        } else {
                            arrayOfEventList[i].add(new EventItem("M", age));
                        }
                    } else if (isUnluckyEvent) {
                        propertyValue[i] /= 2;
                        arrayOfEventList[i].add(new EventItem("B", age));
                    }
                }
            }
             // Increment age by 1
            age++;
        }

        // Print final skill capability and property value of each person
        System.out.println("Final skill capability and property value of each person:");
        for (int i = 0; i < peopleCount; i++) {
            System.out.println("\n========================================================================");
            System.out.println("Person " + i + ": Skill capability - " + skillCapability[i] +
                    ", Property value - " + propertyValue[i]);
            // Print event list
            System.out.println("Event list for Person " + i +" :");
            int luckyEventCatchedCount=0,unluckyEventCount=0,luckyEventMissed = 0;
            for (EventItem event : arrayOfEventList[i] ) {
                if (event.getEventType().equals("G"))
                    luckyEventCatchedCount+=1;
                else if(event.getEventType().equals("B"))
                    unluckyEventCount += 1;
                else
                    luckyEventMissed += 1;
                System.out.print(event.getEventType() + ":" + event.getEventAge()+ " ");
            }
            System.out.print("|total event count:" + (luckyEventCatchedCount+unluckyEventCount+luckyEventMissed) + ",lucky catched count:" + luckyEventCatchedCount + ",lucky missed count: " + luckyEventMissed + ",unlucky event count: "+ unluckyEventCount);
        }
        
        // Find richest and poorest person
        int richestPersonIndex = 0;
        int poorestPersonIndex = 0;
        for (int i = 1; i < peopleCount; i++) {
            if (propertyValue[i] > propertyValue[richestPersonIndex]) {
                richestPersonIndex = i;
            }
            if (propertyValue[i] < propertyValue[poorestPersonIndex]) {
                poorestPersonIndex = i;
            }
        }

        // Print richest person's information
        // print one seperate line consisting of 80 '-' characters
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Richest person:");
        System.out.println("ID: " + richestPersonIndex);
        System.out.println("Property value: " + propertyValue[richestPersonIndex]);
        System.out.println("Skill capability: " + skillCapability[richestPersonIndex]);
        System.out.println("Event list:");
        // calculate total event count, lucky event catched count, lucky event missed count and unlucky event count for the richest person
        int luckyEventCatchedCount=0,unluckyEventCount=0,luckyEventMissed = 0;
        for (EventItem event : arrayOfEventList[richestPersonIndex]) {
            if (event.getEventType().equals("G"))
                luckyEventCatchedCount+=1;
            else if(event.getEventType().equals("B"))
                unluckyEventCount += 1;
            else
                luckyEventMissed += 1;
            System.out.print(event.getEventType() + ":" + event.getEventAge()+ " ");
        }
        //print out the total event count, lucky event catched count, lucky event missed count and unlucky event count for the richest person
        System.out.print("|total event count:" + (luckyEventCatchedCount+unluckyEventCount+luckyEventMissed) + ",lucky catched count:" + luckyEventCatchedCount + ",lucky missed count: " + luckyEventMissed + ",unlucky event count: "+ unluckyEventCount);
        
        // print the poorest person's information in the same format as the richest person
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Poorest person:");
        System.out.println("ID: " + poorestPersonIndex);
        System.out.println("Property value: " + propertyValue[poorestPersonIndex]);
        System.out.println("Skill capability: " + skillCapability[poorestPersonIndex]);
        System.out.println("Event list:");
        // calculate total event count, lucky event catched count, lucky event missed count and unlucky event count for the poorest person
        luckyEventCatchedCount=0;unluckyEventCount=0;luckyEventMissed = 0;
        for (EventItem event : arrayOfEventList[poorestPersonIndex]) {
            if (event.getEventType().equals("G"))
                luckyEventCatchedCount+=1;
            else if(event.getEventType().equals("B"))
                unluckyEventCount += 1;
            else
                luckyEventMissed += 1;
            System.out.print(event.getEventType() + ":" + event.getEventAge()+ " ");
        }   
        //print out the total event count, lucky event catched count, lucky event missed count and unlucky event count for the poorest person
        System.out.print("|total event count:" + (luckyEventCatchedCount+unluckyEventCount+luckyEventMissed) + ",lucky catched count:" + luckyEventCatchedCount + ",lucky missed count: " + luckyEventMissed + ",unlucky event count: "+ unluckyEventCount);
    }
}