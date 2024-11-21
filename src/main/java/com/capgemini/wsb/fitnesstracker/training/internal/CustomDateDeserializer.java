package com.capgemini.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Custom deserializer for {@link Date} objects, supporting multiple date formats.
 * Used for converting JSON date strings into {@link Date} objects during deserialization.
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    /**
     * List of supported date formats for deserialization.
     * Formats include:
     * <ul>
     *     <li>ISO 8601 with milliseconds and timezone (e.g., yyyy-MM-dd'T'HH:mm:ss.SSS+00:00)</li>
     *     <li>ISO 8601 without milliseconds (e.g., yyyy-MM-dd'T'HH:mm:ss)</li>
     * </ul>
     */
    private static final List<String> DATE_FORMATS = List.of(
            "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00",
            "yyyy-MM-dd'T'HH:mm:ss"
    );

    /**
     * Deserializes a JSON string into a {@link Date} object.
     *
     * @param parser  The {@link JsonParser} providing the JSON input.
     * @param context The {@link DeserializationContext} for the deserialization process.
     * @return A {@link Date} object parsed from the JSON string.
     * @throws IOException if there is an error reading the JSON input.
     * @throws IllegalArgumentException if none of the supported date formats match the input.
     */
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String date = parser.getText();
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format).parse(date);
            } catch (ParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + date);
    }
}
