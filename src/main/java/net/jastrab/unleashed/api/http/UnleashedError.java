package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UnleashedError {
    public static class FieldError {
        private final String fieldName;
        private final String description;
        private final int errorCode;

        public FieldError(@JsonProperty("Field") String fieldName,
                          @JsonProperty("Description") String description,
                          @JsonProperty("ErrorCode") int errorCode) {
            this.fieldName = fieldName;
            this.description = description;
            this.errorCode = errorCode;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getDescription() {
            return description;
        }

        public int getErrorCode() {
            return errorCode;
        }

        @Override
        public String toString() {
            return "FieldError{" +
                    "fieldName='" + fieldName + '\'' +
                    ", description='" + description + '\'' +
                    ", errorCode=" + errorCode +
                    '}';
        }
    }

    private final List<FieldError> errors = new ArrayList<>();

    public UnleashedError(@JsonProperty("Items") List<FieldError> errors) {
        this.errors.addAll(errors);
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "UnleashedError{" +
                "errors=" + errors +
                '}';
    }
}
