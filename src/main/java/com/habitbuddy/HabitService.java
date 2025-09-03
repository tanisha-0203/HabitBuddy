package com.habitbuddy;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HabitService {
    private final List<Habit> habits = new ArrayList<>();
    private final Path dataDir = Path.of("data");
    private final Path dataFile = dataDir.resolve("habits.json");
    private final Gson gson;
    private int nextId = 1;


    public HabitService() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public Habit addHabit(String name, Frequency freq) {
    Habit h = new Habit(nextId++, name, freq);
    habits.add(h);
    return h;
}


    public boolean deleteHabit(int id) {
    return habits.removeIf(h -> h.getId() == id);
}

public boolean markToday(int id) {
    Habit h = findById(id);
    return h != null && h.markComplete(LocalDate.now());
}
    public List<Habit> listHabits() {
        return Collections.unmodifiableList(habits);
    }

    

   public Habit findById(int id) {
    for (Habit h : habits) {
        if (h.getId() == id) return h;
    }
    return null;
}


    public void save() throws IOException {
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        String json = gson.toJson(habits);
        Files.writeString(dataFile, json);
    }

    public void load() throws IOException {
        if (!Files.exists(dataFile)) return;
        String json = Files.readString(dataFile);
        Type listType = new TypeToken<List<Habit>>(){}.getType();
        List<Habit> loaded = gson.fromJson(json, listType);
        habits.clear();
        if (loaded != null) habits.addAll(loaded);
    }
}
