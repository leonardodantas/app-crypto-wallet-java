package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.FileReader;
import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class GetMockJson {

    private static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .serializationInclusion(NON_NULL)
            .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
            .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .build();

    public static <T> T execute(final String fileName, final Class<T> klass) {
        try {
            final var pathFile = String.format("src/test/resources/mocks/%s.json", fileName);
            final var jsonObject = (JsonObject) JsonParser.parseReader(new FileReader(pathFile));
            return objectMapper.readValue(jsonObject.toString(), klass);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T execute(final String fileName, final TypeReference<T> klass) {
        try {
            final var pathFile = String.format("src/test/resources/mocks/%s.json", fileName);
            final var jsonArray = (JsonArray) JsonParser.parseReader(new FileReader(pathFile));
            return objectMapper.readValue(jsonArray.toString(), klass);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
