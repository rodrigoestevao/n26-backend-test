package com.technicaltests.n26.transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    List<Transaction> get();

    boolean evictExpired();
}
