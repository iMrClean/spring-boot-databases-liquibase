package ru.mos.springboot.databases.liquibase.district;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
	return this.userService.save(user);
    }

    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "10", required = false) Integer size) {
	return this.userService.findAll(PageRequest.of(page, size));
    }

}
