package edu.miu.service;

import edu.miu.domain.Account;
import edu.miu.domain.AccountTier;
import edu.miu.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccountsSortedByBalanceDesc() {
        return accountRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .toList();
    }

    public List<Account> getPlatinumAccountsSortedByBalanceDesc() {
        return accountRepository.findAll()
                .stream()
                .filter(account -> account.getTier() == AccountTier.PLATINUM)
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .toList();
    }

    public BigDecimal calculateLiquidityPosition() {
        return accountRepository.findAll()
                .stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}