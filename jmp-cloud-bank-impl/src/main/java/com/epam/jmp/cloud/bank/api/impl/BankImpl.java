package com.epam.jmp.cloud.bank.api.impl;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

public class BankImpl implements Bank {
    private Map<BankCardType, BiFunction<String, User, BankCard>> creators;

    public BankImpl() {
        creators = new HashMap<>();
        creators.put(BankCardType.CREDIT, CreditBankCard::new);
        creators.put(BankCardType.DEBIT, CreditBankCard::new);
    }

    private BankCard trowIfTypeIsUnknown(String cardNumber, User user) {
        throw new IllegalArgumentException("Unknown card type");
    }

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        String cartNumber = UUID.randomUUID().toString();
        return creators.getOrDefault(cardType, this::trowIfTypeIsUnknown).apply(cartNumber, user);

        /*if (cardType == null) {
            throw new IllegalArgumentException("Unknown card type cannot be null");
        }
        switch (cardType) {
            case DEBIT -> new DebitBankCard(cartNumber, user);
            case CREDIT -> new CreditBankCard(cartNumber,user);
            default -> throw new IllegalArgumentException("Unknown card type=" + cardType);
        }*/
    }
}
