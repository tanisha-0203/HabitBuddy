package com.habitbuddy;

import java.util.*;

public class HabitService {
    private List<Habit> habits = new ArrayList<>();
    private int nextId = 1;

    public HabitService() {
        List<Habit> loaded = HabitStorage.load();
        if (loaded != null) {
            habits = loaded;
            if (!habits.isEmpty()) {
                nextId = habits.get(habits.size() - 1).getId() + 1;
            }
        }
    }

    // CREATE
    public void addHabit(String name) {
        habits.add(new Habit(nextId++, name));
        HabitStorage.save(habits);
        System.out.println("Habit added successfully!");
    }

    // READ
    public void viewHabits() {
        if (habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }
        for (Habit h : habits) {
            System.out.println(h);
        }
    }

    // UPDATE
    public void markHabitDone(int id) {
        for (Habit h : habits) {
            if (h.getId() == id) {
                h.incrementStreak();
                HabitStorage.save(habits);
                System.out.println("Habit updated! Streak = " + h.getStreak() + " days");
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    // DELETE
    public void deleteHabit(int id) {
        Iterator<Habit> it = habits.iterator();
        while (it.hasNext()) {
            Habit h = it.next();
            if (h.getId() == id) {
                it.remove();
                HabitStorage.save(habits);
                System.out.println("Habit deleted successfully!");
                return;
            }
        }
        System.out.println("Habit not found!");
    }
}
