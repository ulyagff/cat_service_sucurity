package org.example.controllers;

import org.example.repositories.RoleRepository;
import org.example.securityEntity.Owner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.securityEntity.Role;
import org.example.services.OwnerService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Operation(summary = "Добавление владельца")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелец добавлен")
    )
    @PostMapping(path = "/add/{name}/{birthday}/{password}")
    public ResponseEntity<String> addOwner (@PathVariable @Parameter(description = "имя владельца") String name,
                                            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") @Parameter(description = "дата рождения владельца") LocalDate birthday,
                                            @PathVariable @Parameter(description = "пароль для входа в аккаунт владельца") String password) {
        Owner owner = ownerService.addOwner(name, birthday);
        List<Role> listRole = new ArrayList<>();
        Role userRole = roleRepository.findRoleByName(Role.RoleType.ROLE_USER);
        listRole.add(userRole);
        userService.addUser(owner, passwordEncoder.encode(password), listRole);
        return ResponseEntity.ok("Saved");
    }

    @Operation(summary = "Удалить владельца по id")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелец получен")
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteOwner (@PathVariable @Parameter(description = "id владельца") int id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok("Delete");
    }

    @Operation(summary = "Получить владельца по id")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелец получен")
    )
    @GetMapping(path = "/getOwner/byId/{id}")
    public ResponseEntity<Owner> getOwnerById (@PathVariable @Parameter(description = "id владельца") int id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @Operation(summary = "Получить владельца по имени")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелец получен")
    )
    @GetMapping(path = "/getOwner/byName/{name}")
    public ResponseEntity<List<Owner>> getOwnerByName (@PathVariable @Parameter(description = "имя владельца") String name) {
        return ResponseEntity.ok(ownerService.getAllByName(name));
        }

    @Operation(summary = "Получить владельца по дате рождения")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелец получен")
    )
    @GetMapping(path = "/getOwner/byBirthday/{birthday}")
    public ResponseEntity<List<Owner>> getOwnerByName (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") @Parameter(description = "дата рождения владельца") LocalDate birthday) {
        return ResponseEntity.ok(ownerService.getAllByBirthday(birthday));
    }

    @Operation(summary = "Удалить всех владельцев")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владелецы удалены")
    )
    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        userService.deleteAll();
        return ResponseEntity.ok("Delete all");

    }

    @Operation(summary = "Получить всех владельцев")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Владельцы получены")
    )
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Owner>> getAll() {
        return ResponseEntity.ok(ownerService.getAll());
        }
}
