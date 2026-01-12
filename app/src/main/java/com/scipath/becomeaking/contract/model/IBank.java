package com.scipath.becomeaking.contract.model;

public interface IBank {

    int getLoanBalance();

    int getDepositBalance();

    int getLoanLimit();

    void takeLoan(int value);

    void repayLoan(int value);

    void putDeposit(int value);

    void withdrawDeposit(int value);

    void setLoanLimit(int limit);

    void chargeInterests();
}
