package com.technicaltests.n26.stats;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.technicaltests.n26.transaction.Transaction;
import com.technicaltests.n26.transaction.TransactionService;

@RestController
public class StatsController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatsService statsService;

    @GetMapping(value = "/statistics")
    public ResponseEntity<Stats> statistics() {
        final List<Transaction> transactions = transactionService.get();
        final Stats stats = statsService.computeAndGet(transactions);
        final HttpStatus status = (stats.getCount() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(stats, status);
    }
}
