package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.IBank;


public class Bank implements IBank {

    private static Bank instance;
    protected static double loanInterestRate = 1.02;
    protected static double depositInterestRate = 1.01;
    protected double loanBalance;
    protected double depositBalance;
    protected int loanLimit = 1000;
    protected int depositLimit = 100000;


    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }


    @Override
    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    @Override
    public double getDepositInterestRate() {
        return depositInterestRate;
    }

    @Override
    public int getLoanBalance() {
        return (int)Math.ceil(loanBalance);
    }

    @Override
    public int getDepositBalance() {
        return (int)Math.ceil(depositBalance);
    }

    @Override
    public int getLoanLimit() {
        return loanLimit;
    }

    @Override
    public int getDepositLimit() {
        return depositLimit;
    }

    @Override
    public void takeLoan(int value) {
        loanBalance += Math.abs(value);
    }

    @Override
    public void repayLoan(int value) {
        loanBalance -= Math.abs(value);
        if (loanBalance < 0) loanBalance = 0;
    }

    @Override
    public void putDeposit(int value) {
        depositBalance += Math.abs(value);
    }

    @Override
    public void withdrawDeposit(int value) {
        depositBalance -= Math.abs(value);
        if (depositBalance < 0) depositBalance = 0;
    }

    @Override
    public void setLoanLimit(int limit) {
        if (limit >= 0) loanLimit = limit;
    }

    @Override
    public void setDepositLimit(int limit) {
        if (limit >= 0) depositLimit = limit;
    }

    @Override
    public void chargeInterests() {
        loanBalance *= loanInterestRate;
        depositBalance *= depositInterestRate;
    }
}