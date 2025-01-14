package com.yaans.bangrang.user.service;

import com.yaans.bangrang.common.dto.PageableDTO;
import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.exception.DuplicatedNicknameException;
import com.yaans.bangrang.user.repository.UserRepository;
import com.yaans.bangrang.user.service.dto.UserCreateRequestDTO;
import com.yaans.bangrang.user.service.dto.UserUpdateRequestDTO;
import jakarta.transaction.Transactional;
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

    public Page<User> getList(PageableDTO dto) {
        PageRequest pageable = PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Direction.DESC, "createdAt"));
        return userRepository.findAll(pageable);
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Transactional
    public User update(UUID userId, UserUpdateRequestDTO dto) {
        if (userRepository.findByNickname(dto.getNickname()).isPresent()) {
            throw new DuplicatedNicknameException();
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        user.updateNickname(dto.getNickname());
        user.updateUserStatus(dto.getUserStatus());
        return user;
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }
}
