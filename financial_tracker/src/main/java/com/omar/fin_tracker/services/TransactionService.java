package com.omar.gateway.fin_tracker.services;

import com.omar.gateway.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.gateway.fin_tracker.DTOs.TransactionDTO;
import com.omar.gateway.fin_tracker.DTOs.UserTransactionsDTO;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    UserTransactionsDTO findUserTransaction(Long userId);

    TransactionDTO save(Long userId, TransactionCreationDTO transactionCreationDTO);

    void delete(Long transactionId);

}
