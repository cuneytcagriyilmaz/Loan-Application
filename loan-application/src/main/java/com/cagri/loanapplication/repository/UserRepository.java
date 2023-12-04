package com.cagri.loanapplication.repository;
import com.cagri.loanapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long userId);
    User findUserByUserTcAndBirthdate(String birthday, String userTc);
    User findUserByUserNameAndPassword(String userName, String password);
    User findUserByUserTcAndPassword(String userTc, String password);



}