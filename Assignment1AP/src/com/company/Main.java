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
    String VaccineName;
    String HospitalName;
    int hospitalID;
    int dayNumber;
    int quantity;

    AddSlotForVaccination(String VaccineName, int hospitalID, int dayNumber, int quantity, String HospitalName) {
        this.HospitalName = HospitalName;
        this.VaccineName = VaccineName;
        this.hospitalID = hospitalID;
        this.dayNumber = dayNumber;
        this.quantity = quantity;
    }
}
class checkVaccinationStatus{
    long UniqueId;
    String VaccineName;
    int NumberOfDose;
    int NextDue;
    checkVaccinationStatus(long UniqueId,String VaccineName,int NumberOfDose,int NextDue){
        this.UniqueId=UniqueId;
        this.VaccineName=VaccineName;
        this.NextDue=NextDue;
        this.NumberOfDose=NumberOfDose;
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
        ArrayList<AddSlotForVaccination> Vaccination = new ArrayList<>();
        ArrayList<checkVaccinationStatus> status = new ArrayList<>();
        while (true) {
            System.out.print("Enter query Number:");
            int query = input.nextInt();
            if (query == 1) {
                System.out.print("Vaccine Name: ");
                String vaccineName = input.next();
                System.out.print("Number of Dose: ");
                int NumberOfDose = input.nextInt();
                int Gap;
                if (NumberOfDose > 1) {
                    System.out.print("Gap between Doses: ");
                    Gap = input.nextInt();
                } else {
                    Gap = 0;
                }
                System.out.println("Vaccine Name: " + vaccineName + ", " + "Number of Doses: " + NumberOfDose + ", " + "Gap Between Doses: " + Gap);
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
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                } else {
                    System.out.println("Citizen Name:" + citizen + ", " + "Age" + age + ", " + "Unique ID:" + ID);
                    Citizen.add(new RegisterCitizen(citizen, age, ID));
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                }
            }


            if (query == 2) {
                System.out.print("Hospital Name: ");
                String hospitalName = input.next();
                System.out.print("PinCode: ");
                int code = input.nextInt();
                if (count(code) != 6) {
                    System.out.println("Invalid pinCode!");
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    continue;
                }
                int uniqueID = generateRandom();
                System.out.println("Hospital Name: " + hospitalName + ", " + "PinCode: " + code + ", " + "Unique ID: " + uniqueID);
                Hospital.add(new RegisterHospital(hospitalName, code, uniqueID));
                System.out.println("---------------------------------\n" +
                        "{Menu Options}");
            }
            if (query == 4) {
                System.out.print("Enter Hospital ID: ");
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

                System.out.print("Enter the number of slots to be added: ");
                int slotNumber = input.nextInt();
                for (int i = 0; i < slotNumber; i++) {
                    System.out.print("Enter Day Number: ");
                    int dayNumber = input.nextInt();
                    System.out.print("Enter Quantity: ");
                    int Quantity = input.nextInt();
                    System.out.println("Select Vaccine");
                    if (Vaccine.size() > 0) {
                        for (int j = 0; j < Vaccine.size(); j++) {
                            System.out.println(j + "." + Vaccine.get(j).VaccineName);
                        }
                    }
                    int a = input.nextInt();
                    int c;
                    c = 0;
                    System.out.println("Slot added by Hospital " + hospitalID + " for Day: " + dayNumber + ", Available Quantity: " + Quantity + " of Vaccine " + Vaccine.get(a).VaccineName);
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    for (int j = 0; j < Hospital.size(); j++) {
                        if (hospitalID == Hospital.get(j).UniqueID) {
                            c = j;
                            break;
                        }
                    }
                    Vaccination.add(new AddSlotForVaccination(Vaccine.get(a).VaccineName, hospitalID, dayNumber, Quantity, Hospital.get(c).hospitalName));
                }
            }
            if (query == 5) {
                System.out.print("Enter patient Unique ID:");
                long UniqueId = input.nextLong();
                boolean flag1;
                flag1 = false;
                for (int i = 0; i < Citizen.size(); i++) {
                    if (UniqueId == Citizen.get(i).ID) {
                        flag1 = true;
                        break;
                    }
                }
                if (!flag1) {
                    System.out.println("Unique ID doesn't exist");
                    continue;
                }
                System.out.println("""
                        1. Search by area
                        2. Search by Vaccine
                        3. Exit""");
                System.out.print("Enter option: ");
                int option = input.nextInt();
                if (option == 1) {
                    System.out.print("Enter PinCode: ");
                    int PinCode = input.nextInt();
                    for (int i = 0; i < Hospital.size(); i++) {
                        if (PinCode == Hospital.get(i).code) {
                            System.out.println(Hospital.get(i).UniqueID + " " + Hospital.get(i).hospitalName);
                        }
                    }
                    System.out.print("Enter Hospital id: ");
                    int HID = input.nextInt();
                    boolean flag;
                    flag = false;
                    for (int i = 0; i < Hospital.size(); i++) {
                        if (HID == Hospital.get(i).UniqueID) {
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
                    boolean flag2;
                    flag2 = false;
                    for (int i = 0; i < Vaccination.size(); i++) {
                        if (Vaccination.get(i).hospitalID == HID) {
                            flag2 = true;
                            System.out.println(i + "-> Day: " + Vaccination.get(i).dayNumber + " Available Qty:" + Vaccination.get(i).quantity + " Vaccine:" + Vaccination.get(i).VaccineName);
                        }

                    }
                    if (!flag2) {
                        System.out.println("No slots available");
                        System.out.println("---------------------------------\n" +
                                "{Menu Options}");
                        continue;
                    }
                    System.out.println("Choose Slot");
                    int slot = input.nextInt();
                    int point;
                    point = 0;
                    for (int i = 0; i < Citizen.size(); i++) {
                        if (UniqueId == Citizen.get(i).ID) {
                            point = i;
                            break;
                        }
                    }
                    System.out.println(Citizen.get(point).citizen + " vaccinated with " + Vaccination.get(slot).VaccineName);
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");

                }
                if (option == 2) {
                    System.out.print("Enter Vaccine name: ");
                    String VaccineName1 = input.next();
                    boolean flag4;
                    flag4 = false;
                    for (int i = 0; i < Vaccination.size(); i++) {
                        if (VaccineName1.equals(Vaccination.get(i).VaccineName)) {
                            flag4 = true;
                            System.out.println(Vaccination.get(i).hospitalID + " " + Vaccination.get(i).HospitalName);
                        }
                    }
                    if (!flag4) {
                        System.out.println("No such vaccine available at given hospital");
                        continue;
                    }
                    System.out.print("Enter hospital id: ");
                    int HID1 = input.nextInt();
                    boolean flag;
                    flag = false;
                    for (int i = 0; i < Hospital.size(); i++) {
                        if (HID1 == Hospital.get(i).UniqueID) {
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
                    boolean flag2;
                    flag2 = false;
                    for (int i = 0; i < Vaccination.size(); i++) {
                        if (Vaccination.get(i).hospitalID == HID1) {
                            flag2 = true;
                            System.out.println(i + "-> Day: " + Vaccination.get(i).dayNumber + " Available Qty:" + Vaccination.get(i).quantity + " Vaccine:" + Vaccination.get(i).VaccineName);
                        }

                    }
                    if (!flag2) {
                        System.out.println("No slots available");
                        System.out.println("---------------------------------\n" +
                                "{Menu Options}");
                        continue;
                    }
                    System.out.println("Choose Slot");
                    int slot = input.nextInt();
                    int point;
                    point = 0;
                    for (int i = 0; i < Citizen.size(); i++) {
                        if (UniqueId == Citizen.get(i).ID) {
                            point = i;
                            break;
                        }
                    }
                    System.out.println(Citizen.get(point).citizen + " vaccinated with " + Vaccination.get(slot).VaccineName);
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");


                }
                if (option == 3) {
                    continue;
                }
            }
            if (query == 6) {
                System.out.print("Enter Hospital Id: ");
                int id = input.nextInt();
                boolean flag;
                flag = false;
                for (int i = 0; i < Hospital.size(); i++) {
                    if (id == Hospital.get(i).UniqueID) {
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
                boolean flag3;
                flag3 = false;
                for (int i = 0; i < Vaccination.size(); i++) {
                    if (Vaccination.get(i).hospitalID == id) {
                        flag3 = true;
                        System.out.println("Day: " + Vaccination.get(i).dayNumber + " Available Qty: " + Vaccination.get(i).quantity + " Vaccine: " + Vaccination.get(i).VaccineName);
                    }

                }
                if (!flag3) {
                    System.out.println("No slots available");
                    System.out.println("---------------------------------\n" +
                            "{Menu Options}");
                    continue;
                }

            }
            if(query==7){
                System.out.print("Enter Patient ID: ");
                long AdID= input.nextLong();

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
