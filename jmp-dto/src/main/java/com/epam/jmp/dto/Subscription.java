package com.epam.jmp.dto;

import java.time.LocalDate;

public record Subscription(String bankcard, LocalDate startDate) {
    @Override
    public String toString() {
        return "Subscription{" +
                "bankcard='" + bankcard + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
