package com.omar.fin_tracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ExpenseType> expenseTypes;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;

    public boolean isIncome() {
        return "Income".equals(this.name);
    }
}
