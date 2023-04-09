package com.epam.jmp.cloud.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.*;

public class ServiceImpl implements Service {
    private final Map<User, List<Subscription>> storage = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {
        User user = bankCard.getUser();
        String number = bankCard.getNumber();
        Subscription subscription = new Subscription(number, LocalDate.now());
        storage.getOrDefault(user, new LinkedList<>()).add(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return storage.values().stream()
                .flatMap(Collection::stream)
                .filter(subscription -> subscription.bankcard().equals(number))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.keySet());
    }
}
