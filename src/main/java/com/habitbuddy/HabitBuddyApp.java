package com.habitbuddy;

import java.util.Scanner;

public class HabitBuddyApp {
    public static void main(String[] args) {
        HabitService service = new HabitService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== HabitBuddy Menu =====");
            System.out.println("1. Add Habit");
            System.out.println("2. View Habits");
            System.out.println("3. Mark Habit Done");
            System.out.println("4. Delete Habit");
            System.out.println("5. Help");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter habit name: ");
                    String name = sc.nextLine();
                    if (name.isBlank()) {
                        System.out.println("Habit name cannot be empty.");
                        break;
                    }
                    service.addHabit(name);
                    break;
                case 2:
                    service.viewHabits();
                    break;
                case 3:
                    System.out.print("Enter habit ID to mark done: ");
                    try {
                        int idUpdate = Integer.parseInt(sc.nextLine().trim());
                        service.markHabitDone(idUpdate);
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;
                case 4:
                    System.out.print("Enter habit ID to delete: ");
                    try {
                        int idDelete = Integer.parseInt(sc.nextLine().trim());
                        service.deleteHabit(idDelete);
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;
                case 5:
                    System.out.println("Help:");
                    System.out.println(" - Add Habit: Creates a new habit with a name.");
                    System.out.println(" - View Habits: Shows all habits with streaks and stats.");
                    System.out.println(" - Mark Habit Done: Increases your streak for the chosen habit.");
                    System.out.println(" - Delete Habit: Removes the habit forever.");
                    System.out.println(" - Streak = consecutive days marked done.");
                    System.out.println(" - Total Completions = all times marked done.");
                    System.out.println(" - Longest Streak = highest consecutive streak for a habit.");
                    break;
                case 6:
                    System.out.println("Goodbye! Stay consistent with your habits ✨");
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}
