package com.epam.jmp.dto;

public enum BankCardType {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");
    private final String cardType;

    BankCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardType;
    }
}
