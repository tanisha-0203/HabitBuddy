package com.habitbuddy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public class HabitStorage {
    private static final String FILE = "habits.json";

    public static void save(List<Habit> habits) {
        try (Writer writer = new FileWriter(FILE)) {
            new Gson().toJson(habits, writer);
        } catch (IOException e) {
            System.out.println("Failed to save habits.");
        }
    }

    public static List<Habit> load() {
    try (Reader reader = new FileReader(FILE)) {
        return new Gson().fromJson(reader, new TypeToken<List<Habit>>(){}.getType());
    } catch (IOException e) {
        return null;
    }
}
}