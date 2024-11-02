package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves a list of all users.
     *
     * @return a list of UserDto objects representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    /**
     * Retrieves basic information about all users.
     *
     * @return a list of UserSimpleDto objects with basic user information
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicInformationAboutUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the UserDto representing the user
     * @throws IllegalArgumentException if no user is found with the given ID
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
    /**
     * Adds a new user.
     *
     * @param userDto the UserDto object containing user information
     * @return the created UserDto
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return userMapper.toDto(savedUser);
    }
    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    /**
     * Updates an existing user by their ID.
     *
     * @param id the ID of the user to update
     * @param userDto the UserDto object containing updated user information
     * @return the updated UserDto
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(id, user);
        return userMapper.toDto(updatedUser);
    }
    /**
     * Searches for users by email.
     *
     * @param email the email address to search for
     * @return a list of UserDto objects matching the email
     * @throws ResponseStatusException if no email parameter is provided
     */
    @GetMapping("/email")
    public List<UserDto> searchUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            return userService.findUsersByEmail(email)
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters");
        }
    }
    /**
     * Searches for users older than a specified cutoff date.
     *
     * @param cutoffDate the cutoff date to filter users
     * @return a list of UserDto objects representing users older than the cutoff date
     */
    @GetMapping("/older/{time}")
    public List<UserDto> searchOlderUsers(@PathVariable("time") LocalDate cutoffDate) {
        return userService.findUsersOlderThan(cutoffDate)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

}