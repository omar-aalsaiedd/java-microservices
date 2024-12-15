package com.omar.gateway.fin_tracker.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {
    private Long id;
    private Double amount;
    private String description;
    private String typeName;
    private String categoryName;
    private LocalDateTime createdAt;
    private boolean isIncome;

}
