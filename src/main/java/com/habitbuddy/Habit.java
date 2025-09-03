package com.habitbuddy;

public class Habit {
    private int id;
    private String name;
    private int streak;
    private int totalCompletions;
    private int longestStreak;

    public Habit(int id, String name) {
        this.id = id;
        this.name = name;
        this.streak = 0;
        this.totalCompletions = 0;
        this.longestStreak = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getStreak() { return streak; }
    public int getTotalCompletions() { return totalCompletions; }
    public int getLongestStreak() { return longestStreak; }

    public void setName(String name) { this.name = name; }

    public void incrementStreak() {
        streak++;
        totalCompletions++;
        if (streak > longestStreak) {
            longestStreak = streak;
        }
    }

    public void resetStreak() {
        streak = 0;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - Streak: " + streak + " days"
             + " | Total Completions: " + totalCompletions
             + " | Longest Streak: " + longestStreak;
    }
}
