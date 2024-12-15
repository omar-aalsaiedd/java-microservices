package com.omar.gateway.fin_tracker.services.impl;

import com.omar.gateway.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.gateway.fin_tracker.DTOs.TransactionDTO;
import com.omar.gateway.fin_tracker.DTOs.UserTransactionsDTO;
import com.omar.gateway.fin_tracker.mappers.TransactionMapper;
import com.omar.gateway.fin_tracker.models.Transaction;
import com.omar.gateway.fin_tracker.repositories.TransactionRepository;
import com.omar.gateway.fin_tracker.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public UserTransactionsDTO findUserTransaction(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new IllegalArgumentException("User not found")
//        );

        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double totalBalance = transactions.stream().mapToDouble(Transaction::getSignedAmount).sum();

        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());

        return UserTransactionsDTO.builder()
                .transactions(transactionDTOs)
                .totalBalance(totalBalance)
                .build();
    }

    @Override
    @Transactional
    public TransactionDTO save(Long userId, TransactionCreationDTO transactionCreationDTO) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new IllegalArgumentException("User not found")
//        );


        Transaction trx = transactionMapper.toEntity(transactionCreationDTO);

        Transaction savedTrx = transactionRepository.save(trx);
        return transactionMapper.toDTO(savedTrx);
    }

    @Override
    public void delete(Long transactionId) {
        Transaction trx = transactionRepository.findById(transactionId).orElseThrow(
                () -> new IllegalArgumentException("Transaction not found")
        );
        transactionRepository.delete(trx);
    }


//    private boolean isValidToken(String token) {
//
//    }
}
