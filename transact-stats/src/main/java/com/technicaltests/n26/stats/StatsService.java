package com.technicaltests.n26.stats;

import java.util.List;
import com.technicaltests.n26.transaction.Transaction;

public interface StatsService {
    Stats computeAndGet(List<Transaction> transactions);
}
