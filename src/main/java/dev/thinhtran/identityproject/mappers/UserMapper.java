package dev.thinhtran.identityproject.mappers;

import dev.thinhtran.identityproject.dto.request.UserCreationRequest;
import dev.thinhtran.identityproject.dto.request.UserUpdateRequest;
import dev.thinhtran.identityproject.dto.response.UserResponse;
import dev.thinhtran.identityproject.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

// chi dinh dung trong spring de su dung DI
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    List<UserResponse> toUserResponseList(List<User> users);
}
