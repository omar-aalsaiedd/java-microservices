package com.omar.gateway.fin_tracker.controllers;

import com.omar.gateway.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.gateway.fin_tracker.DTOs.TransactionDTO;
import com.omar.gateway.fin_tracker.DTOs.UserTransactionsDTO;
import com.omar.gateway.fin_tracker.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping("/")
    ResponseEntity<UserTransactionsDTO> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findUserTransaction(1L));
    }

    @PostMapping("/save")
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionCreationDTO dto) {
        return ResponseEntity.ok(transactionService.save(1L, dto));
    }

    @DeleteMapping("/{trx_id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long trx_id) {
        transactionService.delete(trx_id);
        return ResponseEntity.ok().build();
    }
}
