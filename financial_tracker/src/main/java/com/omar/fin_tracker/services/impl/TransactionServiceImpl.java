package com.omar.fin_tracker.services.impl;

import com.omar.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.fin_tracker.DTOs.TransactionDTO;
import com.omar.fin_tracker.DTOs.UserTransactionsDTO;
import com.omar.fin_tracker.mappers.TransactionMapper;
import com.omar.fin_tracker.models.Transaction;
import com.omar.fin_tracker.repositories.TransactionRepository;
import com.omar.fin_tracker.services.TransactionService;
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
        Transaction trx = transactionMapper.toEntity(transactionCreationDTO, userId);

        Transaction savedTrx = transactionRepository.save(trx);
        return transactionMapper.toDTO(savedTrx);
    }

    @Override
    public void delete(Long UserId, Long transactionId) throws IllegalAccessException {

        Transaction trx = transactionRepository.findById(transactionId).orElseThrow(
                () -> new IllegalArgumentException("Transaction not found")
        );

        if(trx.getUserId() != UserId)
            throw new IllegalAccessException("Transaction doesn't belong to this user");

        transactionRepository.delete(trx);
    }

}
