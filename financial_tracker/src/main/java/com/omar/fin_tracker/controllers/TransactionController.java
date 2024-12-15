package com.omar.fin_tracker.controllers;

import com.omar.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.fin_tracker.DTOs.TransactionDTO;
import com.omar.fin_tracker.DTOs.UserTransactionsDTO;
import com.omar.fin_tracker.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping("/")
    ResponseEntity<UserTransactionsDTO> getAllTransactions(@RequestHeader("X-User-Id") Long userId) {
        log.info("Getting all transactions for user: {}", userId);
        return ResponseEntity.ok(transactionService.findUserTransaction(userId.longValue()));
    }

    @PostMapping("/")
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionCreationDTO dto,
                                                            @RequestHeader("X-User-Id") Long userId) {
        log.info("Creating transaction: {}", dto);
        return ResponseEntity.ok(transactionService.save(userId.longValue(), dto));
    }

    @DeleteMapping("/{trx_id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long trx_id, @RequestHeader("X-User-Id") Long userId) throws IllegalAccessException {
        transactionService.delete(userId.longValue(), trx_id);
        return ResponseEntity.ok().build();
    }
}
