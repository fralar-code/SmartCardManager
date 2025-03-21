package com.wsda.CreditCard.repository;


import com.wsda.CreditCard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Card searchCardById(Integer id);

    @Modifying
    @Query(value = "update Card c set c.state=?2 where c.id=?1")
    void updateStateById(Integer id, String state);

    @Modifying
    @Query(value = "update Card c set c.credit=?2 where c.id=?1")
    void updateCreditById(Integer id, Integer credit);

    @Query("SELECT c FROM Card c WHERE CONCAT(c.id,' ',c.credit,' ',c.state) LIKE %?1%")
    List<Card> search(String keyword);

}
