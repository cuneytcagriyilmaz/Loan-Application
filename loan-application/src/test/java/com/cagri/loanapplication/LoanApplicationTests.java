package com.cagri.loanapplication;

import com.cagri.loanapplication.entity.CreditScore;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.repository.CreditScoreRepository;
import com.cagri.loanapplication.repository.UserRepository;
import com.cagri.loanapplication.test.TestApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanApplicationTests implements TestApp {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CreditScoreRepository creditScoreRepository;

	@org.junit.jupiter.api.Test
	void contextLoads() {
	}

	@org.junit.jupiter.api.Test
	@Override
	public void register() {
		User user = User
				.builder()
				.userName("Cagri")
				.userSurname("Yilmaz")
				.userTc("123456")
				.phoneNumber("506106")
				.monthlyIncome(5000L)
				.birthdate("1998")
				.guarentee(1000)
				.password("asdfg")
				.build();
		userRepository.save(user);
		assertNotNull(userRepository.findById(1L).get());
	}

	@org.junit.jupiter.api.Test
	@Override
	public void login() {
		User user = userRepository.findUserByUserNameAndPassword("Cagri", "asdfg");
		assertEquals("Cagri",user.getUserName());
		assertEquals("asdfg", user.getPassword());
	}

	@org.junit.jupiter.api.Test
	@Override
	public void update() {
		User user = userRepository.findById(1L).get();
		user.setPhoneNumber("5061068464");
		userRepository.save(user);
		//if it's not equal it won't be a problem, but if it is equal then throw an exception
		assertNotEquals("506106", userRepository.findById(1L).get().getPhoneNumber());
	}

	@org.junit.jupiter.api.Test
	@Override
	public void delete() {
		userRepository.deleteById(1L);
		//isFalse
		assertThat(userRepository.existsById(1L)).isFalse();
	}

	@Test
	@Override
	public void addCredit() {
		CreditScore creditScore = CreditScore
				.builder()
				.creditScore(200)
				.status(0)
				.totalBorrowAmount(1000D)
				.build();
		creditScoreRepository.save(creditScore);
		assertNotNull(creditScoreRepository.findById(1L).get());
	}



}
