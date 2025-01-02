package com.yaans.bangrang.user.service;

import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.repository.UserRepository;
import com.yaans.bangrang.user.service.dto.UserCreateRequestDTO;
import com.yaans.bangrang.user.service.dto.UserUpdateRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(UserCreateRequestDTO dto) {
        return userRepository.save(dto.toEntity());
    }

    public Page<User> getList(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC));
        return userRepository.findAll(pageRequest);
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Transactional
    public User update(UUID userId, UserUpdateRequestDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
            user.updateNickname(dto.getNickname());         // TODO : 이미 존재하는 닉네임(unique)을 입력한 경우 예외처리 필요
            user.updateUserStatus(dto.getUserStatus());
        return user;
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }
}
