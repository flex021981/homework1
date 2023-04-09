package com.epam;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.cloud.bank.api.impl.BankImpl;
import com.epam.jmp.cloud.service.impl.ServiceImpl;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        User userJohn = new User("John", "Smith", LocalDate.of(2000, 12, 12));
        User userAnn = new User("Ann", "Smith", LocalDate.of(2002, 10, 20));

        Bank bank = new BankImpl();
        Service service = new ServiceImpl();

        BankCard creditCard = bank.createBankCard(userJohn, BankCardType.CREDIT);
        BankCard debitCard = bank.createBankCard(userAnn, BankCardType.DEBIT);
        service.subscribe(creditCard);

        Optional<Subscription> subscriptionByBankCardNumber = service.getSubscriptionByBankCardNumber(creditCard.getNumber());

        subscriptionByBankCardNumber.orElseThrow(() -> new RuntimeException("No subscription"));
        subscriptionByBankCardNumber.ifPresent(System.out::println);
        double averageUsersAge = service.getAverageUsersAge();
        System.out.println("Average age is" + averageUsersAge);
    }
}