package com.ctytech.flierly.iam.controller;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.iam.entity.User;
import com.ctytech.flierly.iam.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    // Additional endpoints specific to User can be added here

    @PostMapping("/page")
    public ResponseEntity<Page<User>> page(@RequestBody Map<String, Object> filters, @RequestParam(name = "pageNo", defaultValue = "0") @Pattern(regexp = "^[0-9]*$", message = "{page.pageno.invalid}") String pageNo, @RequestParam(name = "limit", defaultValue = "10") @Pattern(regexp = "^[0-9]*$", message = "{page.limit.invalid}") @Min(value = 1, message = "{page.limit.min}") String limit) throws FlierlyException {

       Page<User> pageRes =  userService.page(filters, Integer.parseInt(pageNo), Integer.parseInt(limit));

        return new ResponseEntity<>(pageRes, HttpStatus.OK);
    }
}
