package com.wsda.CreditCard.repository;

import com.wsda.CreditCard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    @Modifying
    @Query(value = "update User u set u.state=?2 where u.id=?1")
    void updateStateById(Long id, String state);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name='SHOP'")
    List<User> findAllShoopkeepers();
}
