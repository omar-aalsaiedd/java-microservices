package com.omar.fin_tracker.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserTransactionsDTO {
    private List<TransactionDTO> transactions;

    private Double totalBalance;
}
