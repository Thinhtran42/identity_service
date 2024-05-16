package dev.thinhtran.identityproject.services;

import dev.thinhtran.identityproject.dto.request.UserCreationRequest;
import dev.thinhtran.identityproject.dto.request.UserUpdateRequest;
import dev.thinhtran.identityproject.dto.response.UserResponse;
import dev.thinhtran.identityproject.entities.User;
import dev.thinhtran.identityproject.exceptions.AppException;
import dev.thinhtran.identityproject.exceptions.ErrorCode;
import dev.thinhtran.identityproject.mappers.UserMapper;
import dev.thinhtran.identityproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseList(users);
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    private User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = findUserById(userId);
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.delete(findUserById(userId));
    }
}
