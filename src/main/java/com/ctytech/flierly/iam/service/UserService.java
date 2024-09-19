package com.ctytech.flierly.iam.service;

import com.ctytech.flierly.genric.repository.GenericRepository;
import com.ctytech.flierly.genric.service.GenericServiceImpl;
import com.ctytech.flierly.iam.entity.User;
import com.ctytech.flierly.iam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericServiceImpl<User, Long> {

    public UserService(UserRepository userRepository) {
        super((GenericRepository<User, Long>) userRepository);
    }
//

}

