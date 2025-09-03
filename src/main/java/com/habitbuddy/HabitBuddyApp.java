package com.habitbuddy;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class HabitBuddyApp {
    
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n=== HabitBuddy (CLI) ===\n");

        HabitService service = new HabitService();

        try {
            service.load();
        } catch (IOException e) {
            System.err.println("(No data yet)");
        }

        boolean running = true;

        while (running) {
            printMenu();
            String choice = in.nextLine().trim();

            switch (choice) {
    case "1" -> addHabit(service);
    case "2" -> listHabits(service);
    case "3" -> markToday(service);
    case "4" -> deleteHabit(service);
    case "5" -> saveNow(service);
    case "6" -> showHelp();
    case "7" -> {
        saveAndExit(service);
        running = false;
    }
    default -> System.out.println("Invalid choice\n");
}

        }
        System.out.println("Bye! ✅");
    }

   private static void printMenu() {
    System.out.println("===== HabitBuddy Menu =====");
    System.out.println("1) Add habit");
    System.out.println("2) List habits");
    System.out.println("3) Mark today done (by ID)");
    System.out.println("4) Delete habit (by ID)");
    System.out.println("5) Save now");
    System.out.println("6) Help");
    System.out.println("7) Save and Exit");
    System.out.print("Choose: ");
}


    private static void addHabit(HabitService service) {
        System.out.print("Name: ");
        String name = in.nextLine().trim();

        System.out.print("Frequency (DAILY/WEEKLY): ");
        String f = in.nextLine().trim().toUpperCase();

        Frequency freq = "WEEKLY".equals(f) ? Frequency.WEEKLY : Frequency.DAILY;

        var habit = service.addHabit(name, freq);
        System.out.println("Added: " + habit + "\n");
    }

   private static void listHabits(HabitService service) {
    List<Habit> list = service.listHabits();
    if (list.isEmpty()) {
        System.out.println("No habits yet.\n");
        return;
    }

    System.out.println("ID  Name (Frequency)            Started     Today Completed");
    System.out.println("-----------------------------------------------------------");

    int id = 1;
    for (Habit habit : list) {
        boolean completedToday = habit.isCompletedOn(java.time.LocalDate.now());
        String completedMark = completedToday ? "Yes" : "No";

        System.out.printf("%-3d %-25s %-11s %s%n",
                id++,
                habit.getName() + " (" + habit.getFrequency() + ")",
                habit.getStartDate(),
                completedMark);
    }
    System.out.println();
}


    private static void markToday(HabitService service) {
       System.out.print("Enter habit ID: ");
int id = Integer.parseInt(in.nextLine().trim());
// Use id to call service methods
       boolean ok = service.markToday(id);
       System.out.println(ok ? "Marked today ✅\n" : "Not found ❌\n");
    }

    private static void deleteHabit(HabitService service) {
        System.out.print("Enter habit ID: ");
        int id = Integer.parseInt(in.nextLine().trim());

        boolean ok = service.deleteHabit(id);
        System.out.println(ok ? "Deleted ✅\n" : "Not found ❌\n");
    }

    private static void saveNow(HabitService service) {
        try {
            service.save();
            System.out.println("Habits saved successfully!\n");
        } catch (IOException e) {
            System.out.println("Failed to save habits: " + e.getMessage());
        }
    }
    private static void showHelp() {
    System.out.println("\n=== HabitBuddy Help ===");
    System.out.println("1) Add habit - Add a new habit with name and frequency.");
    System.out.println("2) List habits - List all habits with details and today's completion.");
    System.out.println("3) Mark today done - Mark a habit done for today by numeric ID.");
    System.out.println("4) Delete habit - Delete a habit by numeric ID.");
    System.out.println("5) Save now - Save all habits and progress.");
    System.out.println("6) Help - Display this help message.");
    System.out.println("7) Save and Exit - Save progress and exit the app.\n");
}


    private static void saveAndExit(HabitService service) {
        try {
            service.save();
            System.out.println("Habits saved successfully! Exiting...");
        } catch (IOException e) {
            System.out.println("Failed to save habits: " + e.getMessage());
        }
    }
}
