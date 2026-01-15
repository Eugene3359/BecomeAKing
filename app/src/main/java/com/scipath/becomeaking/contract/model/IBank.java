package com.scipath.becomeaking.contract.model;

public interface IBank {

    double getLoanInterestRate();

    double getDepositInterestRate();

    int getLoanBalance();

    int getDepositBalance();

    int getLoanLimit();
    
    int getDepositLimit();

    void takeLoan(int value);

    void repayLoan(int value);

    void putDeposit(int value);

    void withdrawDeposit(int value);

    void setLoanLimit(int limit);

    void setDepositLimit(int limit);

    void chargeInterests();
}
