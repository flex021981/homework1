package com.epam.jmp.cloud.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class ServiceImpl implements Service {
    private final Map<User, List<Subscription>> storage = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {
        User user = bankCard.getUser();
        String number = bankCard.getNumber();
        Subscription subscription = new Subscription(number, LocalDate.now());
        storage.computeIfAbsent(user, u -> new LinkedList<>()).add(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return getSubscriptionByBankCardNumber(s -> s.bankcard().equals(number));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(Predicate<Subscription> filter) {
        return storage.values().stream()
                .flatMap(Collection::stream)
                .filter(filter)
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.keySet());
    }
}
