package persistence;

import model.Goal;
import model.GoalTracker;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code modelled from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads goalTracker from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads goalTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GoalTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGoalTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses goalTracker from JSON object and returns it
    private GoalTracker parseGoalTracker(JSONObject jsonObject) {
        String username = jsonObject.getString("user");
        GoalTracker goalTracker = new GoalTracker(username);
        addGoals(goalTracker, jsonObject);
        return goalTracker;
    }

    // MODIFIES: goalTracker
    // EFFECTS: parses goals from JSON object and adds them to goalTracker
    private void addGoals(GoalTracker goalTracker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("goals");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(goalTracker, nextGoal);
        }
    }

    // MODIFIES: goal
    // EFFECTS: parses goal from JSON object and adds it to goalTracker
    private void addGoal(GoalTracker goalTracker, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int progress = Integer.parseInt(jsonObject.getString("progress"));

        Goal newGoal = new Goal(name, progress);
        goalTracker.addGoal(newGoal);

    }
}
