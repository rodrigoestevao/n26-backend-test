package com.technicaltests.n26.stats;

import java.util.List;
import org.springframework.stereotype.Service;
import com.technicaltests.n26.transaction.Transaction;

@Service("statsService")
public class StatsServiceImpl implements StatsService {
    @Override
    public Stats computeAndGet(List<Transaction> transactions) {
        final Stats stats = new Stats();
        transactions.stream().forEach(transaction -> stats.update(transaction));
        return stats;
    }
}
