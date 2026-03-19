package com.abbys.bms.service;

import com.abbys.bms.data.user.dto.CreateCeoRequest;
import com.abbys.bms.data.user.dto.CreateCusManagerRequest;
import com.abbys.bms.data.user.dto.CreateUserRequest;
import com.abbys.bms.data.user.dto.UserResponse;
import com.abbys.bms.data.user.entity.*;
import com.abbys.bms.data.user.repository.UserRepo;
import com.abbys.bms.exception.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo _repo;

    public UserResponse createUser(CreateUserRequest request) {
        User user = UserFactory.createUser(request);
        return UserMapper.toDto(_repo.save(user));
    }

    public UserResponse updateUser(Long userId,CreateUserRequest request) {
        User user = _repo.findById(userId)
                .orElseThrow(() -> new NotFound("Invalid user for the given ID"));

        user.setBankName(request.getBankName());
        user.setBankNumber(request.getBankNumber());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setContactNumber(request.getContactNumber());
        user.setPassword(request.getPassword());

        if(user instanceof Ceo ceo && request instanceof CreateCeoRequest ceoRequest) {
            ceo.setCeoNic(ceoRequest.getCeoNic());
            ceo.setCeoAddress(ceoRequest.getCeoAddress());
        } else if(user instanceof CustomerManager cuMng && request instanceof CreateCusManagerRequest cusManagerRequest) {
            cuMng.setCustomerManagerCode(cusManagerRequest.getCustomerManagerCode());
            cuMng.setShift(UserFactory.fromStringToEnum(Shift.class, cusManagerRequest.getShift()));
            cuMng.setOnDuty(cusManagerRequest.isOnDuty());
            cuMng.setWorkingHours(cusManagerRequest.getWorkingHours());
        } else {
            throw new IllegalArgumentException("Invalid type for User");
        }
        return UserMapper.toDto(_repo.save(user));
    }

    public boolean deleteUserById(Long userId) {
        User user = _repo.findById(userId)
                .orElseThrow(() -> new NotFound("Invalid user for the given ID"));
        _repo.delete(user);
        return true;
    }

    public List<UserResponse> getUsersByRole(String roleString) {
        Role role = UserFactory.fromStringToEnum(Role.class,roleString);
        List<User> users = _repo.findByRole(role);
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserResponse getUserById(Long userId) {
        User user = _repo.findById(userId)
                .orElseThrow(() -> new NotFound("Invalid user for the given ID"));
        return UserMapper.toDto(user);
    }
}
