package com.aruna.service;

import com.aruna.model.AuthenticationRequest;
import com.aruna.model.User;
import com.aruna.utilities.AuthenticationResponse;
import com.aruna.utilities.CustomerException;

public interface UserMaintainService {

    User createUser(User user) throws IllegalArgumentException, CustomerException;

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
