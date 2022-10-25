package com.itbootcamplogger;

public enum LogTypeEnum {
    INFO(1),
    WARNING(2),
    ERROR(3);

    private final Integer value;

    LogTypeEnum(Integer value) {
        this.value = value;
    }
}
