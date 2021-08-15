package com.ngocdt.tttn.security.service;

import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Account account = accountRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

		return UserDetailsImpl.build(account);
	}
}
