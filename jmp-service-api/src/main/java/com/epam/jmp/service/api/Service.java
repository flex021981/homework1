package com.epam.jmp.service.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.util.List;
import java.util.Optional;

public interface Service {
    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();
    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToDouble(u -> u.birthday().toEpochDay())
                .average()
                .orElse(0);
    }
}
