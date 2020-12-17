package room;

import androidx.room.TypeConverter;

import java.util.UUID;

public class Converters {
    @TypeConverter
    public UUID stringToUuid(String value) {
        return value == null ? null : UUID.fromString(value);
    }

    @TypeConverter
    public String uuidToString(UUID uuid) {
        if (uuid == null) {
            return null;
        } else {
            return uuid.toString();
        }
    }
}