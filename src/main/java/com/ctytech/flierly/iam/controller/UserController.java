package com.ctytech.flierly.iam.controller;

import com.ctytech.flierly.genric.controller.GenericController;
import com.ctytech.flierly.iam.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<User, Long> {
    // Additional endpoints specific to User can be added here
}
