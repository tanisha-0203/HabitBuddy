package com.habitbuddy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Habit {
    private final int id;
    private String name;
    private final Frequency frequency;
    private final LocalDate startDate;
    private Set<LocalDate> completions;

    public Habit(int id, String name, Frequency frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
        this.startDate = LocalDate.now();
        this.completions = new HashSet<>();
    }

    // No-arg constructor for Gson (initialize id to a default or placeholder, e.g., 0)
    public Habit() {
        this.id = 0;
        this.name = "";
        this.frequency = Frequency.DAILY;
        this.startDate = LocalDate.now();
        this.completions = new HashSet<>();
    }

   

    // other methods remain unchanged...



   public int getId() { return id; }
   public String getName() { return name; }
   public Frequency getFrequency() { return frequency; }
   public LocalDate getStartDate() { return startDate; }
   public Set<LocalDate> getCompletions() { return completions; }

    public void setName(String name) { this.name = name; }

    public boolean markComplete(LocalDate date) {
        if (completions == null) completions = new HashSet<>();
        return completions.add(date);
    }

    public boolean isCompletedOn(LocalDate date) {
        return completions != null && completions.contains(date);
    }

    @Override
    public String toString() {
        int count = completions == null ? 0 : completions.size();
        return String.format("[%d] %s (%s) started %s — %d check-ins",
                id, name, frequency, startDate, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habit)) return false;
        Habit habit = (Habit) o;
        return Objects.equals(id, habit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
