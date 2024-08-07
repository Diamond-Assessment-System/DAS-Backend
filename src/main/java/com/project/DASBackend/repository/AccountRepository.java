package com.project.DASBackend.repository;

import com.project.DASBackend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUid(String uid);

    List<Account> findByRole(int role);

    //    Optional<Account> findByPhoneAndPassword(String phone, String password);
    Optional<Account> findByPhone(String phone);

}
