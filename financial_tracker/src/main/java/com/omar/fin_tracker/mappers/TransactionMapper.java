package com.omar.fin_tracker.mappers;

import com.omar.fin_tracker.DTOs.TransactionCreationDTO;
import com.omar.fin_tracker.DTOs.TransactionDTO;
import com.omar.fin_tracker.models.ExpenseType;
import com.omar.fin_tracker.models.Transaction;
import com.omar.fin_tracker.repositories.CategoryRepository;
import com.omar.fin_tracker.repositories.ExpenseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final ExpenseTypeRepository expenseTypeRepository;
    private final CategoryRepository categoryRepository;

    // this will be used for retrievals
    public TransactionDTO toDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .typeName(transaction.getExpenseType().getName())
                .categoryName(transaction.getCategory().getName())
                .createdAt(transaction.getCreatedAt())
                .isIncome(transaction.isIncome())
                .build();
    }

    // this will be used for saving
    public Transaction toEntity(TransactionCreationDTO transactionDTO, Long userId) {

        Optional<ExpenseType> type = expenseTypeRepository.findById(transactionDTO.getTypeId());

        Transaction.TransactionBuilder trx = Transaction.builder();
        // todo: to add the user from the auth service
        trx.userId(userId);
        if (type.isPresent()) {
            trx.expenseType(type.get());
            trx.category(type.get().getCategory());
        } else {
            throw new IllegalArgumentException("Invalid expense type");
        }

        trx
                .amount(transactionDTO.getAmount())
                .description(transactionDTO.getDescription());


        return trx.build();
    }
}
