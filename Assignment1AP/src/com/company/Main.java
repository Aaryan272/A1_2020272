package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class AddVaccine {
    String VaccineName;
    int NumberOfDoses;
    int Gap;

    AddVaccine(String VaccineName, int NumberOfDoses, int Gap) {
        this.VaccineName = VaccineName;
        this.NumberOfDoses = NumberOfDoses;
        this.Gap = Gap;
    }

}

class RegisterCitizen {
    String citizen;
    int age;
    long ID;

    RegisterCitizen(String citizen, int age, long ID) {
        this.citizen = citizen;
        this.age = age;
        this.ID = ID;
    }
}

class RegisterHospital {
    String hospitalName;
    int code;
    int UniqueID;

    RegisterHospital(String hospitalName, int code, int UniqueID) {
        this.hospitalName = hospitalName;
        this.code = code;
        this.UniqueID = UniqueID;
    }
}

class AddSlotForVaccination {
    int hospitalID;
    int slotNumber;
    int dayNumber;
    int quantity;

    AddSlotForVaccination(int hospitalID, int slotNumber, int dayNumber, int quantity) {
        this.hospitalID = hospitalID;
        this.slotNumber = slotNumber;
        this.dayNumber = dayNumber;
        this.quantity = quantity;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("""
                CoWin Portal initialized....
                ---------------------------------
                1. Add Vaccine
                2. Register Hospital
                3. Register Citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Check Vaccination Status
                8. Exit
                ---------------------------------""");


        ArrayList<AddVaccine> Vaccine = new ArrayList<>();
        ArrayList<RegisterCitizen> Citizen = new ArrayList<>();
        ArrayList<RegisterHospital> Hospital = new ArrayList<>();
        while (true) {
            System.out.print("Enter query Number:");
            int query = input.nextInt();
            if (query == 1) {
                System.out.print("Vaccine Name:");
                String vaccineName = input.next();
                System.out.print("Number of Dose:");
                int NumberOfDose = input.nextInt();
                System.out.print("Gap between Doses:");
                int Gap = input.nextInt();
                System.out.println("Vaccine Name:" + vaccineName + ", " + "Number of Doses:" + NumberOfDose + ", " + "Gap Between Doses:" + Gap);
                Vaccine.add(new AddVaccine(vaccineName, NumberOfDose, Gap));
                System.out.println("---------------------------------\n" +
                        "{Menu Options}");
            }
            if (query == 3) {
                System.out.print("Citizen Name:");
                String citizen = input.next();
                System.out.print("Age:");
                int age = input.nextInt();
                System.out.print("Unique ID:");
                long ID = input.nextLong();
                if (count(ID) != 12) {
                    System.out.println("Registration failed! Unique ID must be of 12 digit");
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    continue;
                }
                if (age < 18) {
                    System.out.println("Citizen Name:" + citizen + ", " + "Age" + age + ", " + "Unique ID:" + ID);
                    System.out.println("Only above 18 are allowed");
                } else {
                    System.out.println("Citizen Name:" + citizen + ", " + "Age" + age + ", " + "Unique ID:" + ID);
                    Citizen.add(new RegisterCitizen(citizen, age, ID));
                }
            }


            if (query == 2) {
                System.out.print("Hospital Name:");
                String hospitalName = input.next();
                System.out.print("PinCode:");
                int code = input.nextInt();
                if (count(code) != 6) {
                    System.out.println("Invalid pinCode!");
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    continue;
                }
                int uniqueID = generateRandom();
                System.out.println("Hospital Name:" + hospitalName + ", " + "PinCode:" + code + ", " + "Unique ID:" + uniqueID);
                Hospital.add(new RegisterHospital(hospitalName, code, uniqueID));
                System.out.println("---------------------------------\n" +
                        "{Menu Options}");
            }
            if (query == 4) {
                System.out.print("Enter Hospital ID:");
                int hospitalID = input.nextInt();
                boolean flag;
                flag = false;
                for (int i = 0; i < Hospital.size(); i++) {
                    if (hospitalID == Hospital.get(i).UniqueID) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Invalid HospitalID!");
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    continue;
                }

                System.out.print("Enter the number of slots to be added:");
                int slotNumber = input.nextInt();
                for (int i = 0; i < slotNumber; i++) {
                    System.out.print("Enter Day Number:");
                    int dayNumber = input.nextInt();
                    System.out.print("Enter Quantity:");
                    int Quantity = input.nextInt();
                    System.out.println("Select Vaccine");
                    if (Vaccine.size() > 0) {
                        for (int j = 0; j < Vaccine.size(); j++) {
                            System.out.println(j + "." + Vaccine.get(j).VaccineName);
                        }
                    }
                    int a = input.nextInt();
                    System.out.println("slot added by Hospital " + hospitalID + "for Day: " + (i + 1) + " Available Quantity: " + Quantity + " of Vaccine " + Vaccine.get(a).VaccineName);
                }
            }
            if (query == 8) {
                break;
            }
        }
    }

    static int count(long num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;

        }
        return count;
    }

    static int generateRandom() {
        int min = 100000;
        int max = 999999;
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
