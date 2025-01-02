package com.yaans.bangrang.user.presentation;

import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.service.UserService;
import com.yaans.bangrang.user.service.dto.UserCreateRequestDTO;
import com.yaans.bangrang.user.service.dto.UserListResponseDTO;
import com.yaans.bangrang.user.service.dto.UserUpdateRequestDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid final UserCreateRequestDTO dto) {
        User user = userService.create(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserListResponseDTO> getUserList(Pageable pageable) {
        Page<User> userList = userService.getList(pageable);

        return ResponseEntity.ok(UserListResponseDTO.builder()
                .userList(userList.getContent())
                .totalPages(userList.getTotalPages())
                .totalSize(userList.getTotalElements())
                .currentPage(userList.getNumber())
            .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(name = "userId") final UUID userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "userId") final UUID userId, @RequestBody UserUpdateRequestDTO dto) {
        User user;
        user = userService.update(userId, dto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") final UUID userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }


}
