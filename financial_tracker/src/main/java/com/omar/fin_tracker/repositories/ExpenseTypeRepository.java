package com.omar.fin_tracker.repositories;

import com.omar.fin_tracker.models.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    public ExpenseType findByName(String name);
}
