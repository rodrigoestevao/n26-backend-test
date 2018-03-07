package com.technicaltests.n26.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private static final List<Transaction> transactions = new ArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
        return transaction;
    }

    @Override
    public List<Transaction> get() {
        return transactions
            .stream()
            .filter(transaction -> !transaction.expired())
            .collect(Collectors.toList());
    }

    @Override
    public boolean evictExpired() {
        return transactions.removeIf(Transaction::expired);
    }
}
