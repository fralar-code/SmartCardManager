package com.wsda.CreditCard.repository;

import com.wsda.CreditCard.entity.Transition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransitionRepository extends JpaRepository<Transition, Integer> {
    @Query("SELECT t FROM Transition t WHERE t.user.id=?1")
    List<Transition> findAllTransitionByUserId(Long id);

    @Query("SELECT t FROM Transition t WHERE t.card.id=?1")
    List<Transition> findAllTransitionByCardId(Integer id);

}
