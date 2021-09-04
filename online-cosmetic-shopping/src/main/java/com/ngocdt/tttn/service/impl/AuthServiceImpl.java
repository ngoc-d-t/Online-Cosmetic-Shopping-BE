package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.dto.AuthDTO;
import com.ngocdt.tttn.dto.RegisterDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Address;
import com.ngocdt.tttn.entity.Customer;
import com.ngocdt.tttn.entity.Role;
import com.ngocdt.tttn.enums.ROLE;
import com.ngocdt.tttn.exception.ConflictException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.AddressRepository;
import com.ngocdt.tttn.repository.CustomerRepository;
import com.ngocdt.tttn.repository.RoleRepository;
import com.ngocdt.tttn.security.jwt.JwtUtils;
import com.ngocdt.tttn.security.service.UserDetailsImpl;
import com.ngocdt.tttn.service.AddressService;
import com.ngocdt.tttn.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final AddressRepository addressRepo;
    private final AddressService addressService;
    @Override
    public AuthDTO signIn(AccountDTO dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(jwtService.generateJwtToken(authentication));
        authDTO.setAccount(AccountDTO.toDTO(accountRepo.findByEmail(dto.getEmail()).get()));
        return authDTO;
    }

    @Override
    @Transactional
    public RegisterDTO register(RegisterDTO dto) {
        Customer customer = new Customer();
        customer.setFullname(dto.getFullname());
        customer.setBirthday(dto.getBirthday());
        customer.setSex(dto.getSex());
        customer.setCustomerID(0);
        customer = customerRepo.save(customer);

        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setAddressID(0);
        address.setAddress(dto.getAddress());
        address.setName(dto.getFullname());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setCustomer(customer);
        address = addressRepo.save(address);
        addresses.add(address);
        customer.setAddresses(addresses);

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountID(0);
        account.setEmail(dto.getEmail());
        Role role = roleRepo.findByRoleName(ROLE.ROLE_USER).orElseThrow(
                () -> new ConflictException("ROLE not found!"));
        account.setRole(role);
        account.setPassword(encoder.encode(dto.getPassword()));
        Account acc = accountRepo.save(account);
        return RegisterDTO.toDTO(customer, acc);
    }

    @Override
    public RegisterDTO update(RegisterDTO dto, HttpServletRequest request) {
        return null;
    }

}
