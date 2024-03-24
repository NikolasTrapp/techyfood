package br.techyfood.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collection;
import java.util.Objects;

import static java.util.Objects.isNull;

@UtilityClass
public class Utils {

    private static ObjectMapper mapper = new ObjectMapper()
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public static String format(String format, Object... args) {
        if (isNull(format) || isNull(args) || notEmpty(format)) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    public static String objectAsJsonString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static boolean notEmpty(Collection<?> obj) {
        return obj != null && !obj.isEmpty();
    }

    public static boolean isEmpty(String obj) {
        return obj == null || obj.isBlank();
    }

    public static boolean notEmpty(String obj) {
        return obj != null && !isEmpty(obj);
    }

    public static void sleep(Long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String getRootCauseMessage(Throwable throwable) {
        Objects.requireNonNull(throwable, "Throwable must not be null");

        var rootCause = getRootCause(throwable);
        return rootCause.getMessage();
    }

    public static Throwable getRootCause(Throwable throwable) {
        var rootCause = throwable;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    public static <T, J> J convert(T from, Class<J> to) {
        if (isNull(from)) {
            throw new IllegalArgumentException("Entity is null");
        }

        return mapper.convertValue(from, to);
    }

}
