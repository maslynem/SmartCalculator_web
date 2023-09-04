package ru.s21school.calculatorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.s21school.calculatorapi.entity.History;


public interface HistoryRepository extends JpaRepository<History, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM history USING users " +
                    "WHERE history.user_id = users.id AND users.uuid = ?",
            nativeQuery = true)
    void deleteByUser(String userUUID);

}