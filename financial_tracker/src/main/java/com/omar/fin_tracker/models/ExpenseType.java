package com.omar.gateway.fin_tracker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expense_types")
@Getter
@Setter
@NoArgsConstructor
@Data
public class ExpenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public boolean isIncomeType() {
        return category != null && category.isIncome();
    }

}
