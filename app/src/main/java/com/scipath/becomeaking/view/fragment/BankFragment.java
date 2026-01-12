package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IBank;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.dialogue.DialogueFragment;


public class BankFragment extends BaseFragment {

    // Models
    IPersonage personage;
    IBank bank;

    // Views
    TextView textLoan1;
    TextView textLoan2;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bank;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showInDevelopmentNotification();

        personage = BecomeAKing.getInstance().getPersonage();
        bank = BecomeAKing.getInstance().getBank();

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        textLoan1 = view.findViewById(R.id.text_loan1);
        textLoan2 = view.findViewById(R.id.text_loan2);
        updateLoanValue();

        Button buttonTakeLoan = view.findViewById(R.id.button_take_loan);
        buttonTakeLoan.setOnClickListener(v -> {
            int maxLoan = bank.getLoanLimit() - bank.getLoanBalance();
            DialogueFragment.Builder builder = new DialogueFragment.Builder()
                    .addHeader(R.string.contract)
                    .addMessage(String.format(
                            getString(R.string.your_limit),
                            bank.getLoanBalance(),
                            bank.getLoanLimit()))
                    .addInput(maxLoan)
                    .addMessage(R.string.loan_contract_terms);
            if (bank.getLoanBalance() >= bank.getLoanLimit()) {
                builder.addButton(R.string.got_it, null);
            } else {
                builder.addButton(R.string.cancel, null);
                builder.addButton(R.string.sign, d -> {
                    int value = d.getInputValue();
                    bank.takeLoan(value);
                    personage.affectMoney(value);
                    ((GameActivity) getActivity()).updateMoney();
                    updateLoanValue();
                });
            }
            showDialogue(builder.getDialogue());
        });

        Button buttonReturnLoan = view.findViewById(R.id.button_return_loan);
        buttonReturnLoan.setOnClickListener(v -> {
            int maxRepayment = Math.min(personage.getMoney(), bank.getLoanBalance());
            DialogueFragment.Builder builder = new DialogueFragment.Builder()
                    .addHeader(R.string.loan_repayment)
                    .addMessage(String.format(
                            getString(R.string.loan_amount),
                            bank.getLoanBalance()))
                    .addInput(maxRepayment)
                    .addMessage(R.string.loan_repayment_instructions);
            if (bank.getLoanBalance() == 0) {
                builder.addButton(R.string.got_it, null);
            } else {
                builder.addButton(R.string.cancel, null);
                builder.addButton(R.string.repay, d -> {
                    int value = d.getInputValue();
                    personage.affectMoney(-value);
                    bank.repayLoan(value);
                    ((GameActivity) getActivity()).updateMoney();
                    updateLoanValue();
                });
            }
            showDialogue(builder.getDialogue());
        });
    }

    private void updateLoanValue() {
        textLoan1.setText(String.valueOf(bank.getLoanBalance()));
        textLoan2.setText(String.valueOf(bank.getLoanBalance()));
    }
}
