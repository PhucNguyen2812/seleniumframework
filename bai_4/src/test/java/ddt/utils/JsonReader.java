package ddt.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ddt.model.UserData;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class JsonReader {

    private JsonReader() {
    }

    public static List<UserData> readUsers(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), new TypeReference<List<UserData>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Cannot read JSON file: " + filePath, e);
        }
    }
}
