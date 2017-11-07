package ru.intodayer.altarixrestapitask.models;

import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;


public enum Gender {
    MAN("M"), WOMAN("W");

    private String shortName;

    Gender(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Gender stringToEnum(String shortName) {
        switch (shortName) {
            case "M":
                return Gender.MAN;
            case "W":
                return Gender.WOMAN;
            case "MAN":
                return Gender.MAN;
            case "WOMAN":
                return Gender.WOMAN;
            default:
                throw new Service400Exception(
                    "ShortName [" + shortName + "] not supported."
                );
        }
    }
}
