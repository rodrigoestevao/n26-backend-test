package com.technicaltests.n26.transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);

    List<Transaction> get();
}
