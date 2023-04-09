package com.aruna.service;

import com.aruna.model.User;
import com.aruna.utilities.CustomerException;

public interface UserMaintainService {

    User createUser(User user) throws IllegalArgumentException, CustomerException;
}
