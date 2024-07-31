package com.rememberme.rememberMe.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TypeSituationUser {

    ACTIVE ("A", "Active"),
    INACTIVE ("I", "Inactive"),
    PENDING ("P", "Pending");

    private String code;
    private String description;

    private TypeSituationUser(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode(String code) {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonCreator
    public static TypeSituationUser toValue(String code) {
        return switch (code) {
            case "A" -> ACTIVE;
            case "I" -> INACTIVE;
            case "P" -> PENDING;
            default -> null;
        };
    }
}
