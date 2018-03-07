package com.technicaltests.n26.transaction;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/transactions")
    public ResponseEntity<Void> transactions(@RequestBody Transaction transaction) {
        final HttpStatus status;
        if (transaction == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (!transaction.expired()) {
                status = HttpStatus.OK;

                //TODO: After finished, the following line shall be moved to an aspect or filter.
                transactionService.save(transaction);
            } else {
                status = HttpStatus.NO_CONTENT;
            }
        }
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/dump")
    public ResponseEntity<List<Transaction>> dump() {
        final List<Transaction> transactions = transactionService.get();
        final HttpStatus status = (!transactions.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(transactions, status);
    }
}
