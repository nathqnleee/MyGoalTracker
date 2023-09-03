package persistence;

import org.json.JSONObject;

// Represents the shared interface of JsonReader and JsonWriter to return objects as JSON objects
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject sendToJson();
}
