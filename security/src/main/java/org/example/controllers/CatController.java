package org.example.controllers;

import org.example.securityEntity.Cat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path="/cat")
public class CatController {
    @Autowired
    CatService catService;
    @Operation(summary = "Добавление кота")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Кот добавлен")
    )
    @PostMapping (path = "/add/{name}/{birthday}/{breed}/{color}/{ownerId}/{tailLength}")
    public ResponseEntity<String> addCat(@PathVariable @Parameter(description = "имя кота") String name,
                                         @PathVariable @Parameter(description = "дата рождения кота") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                                         @PathVariable @Parameter(description = "порода кота") String breed,
                                         @PathVariable @Parameter(description = "цвет кота, доступны white, black, gray, multicolor") String color,
                                         @PathVariable @Parameter(description = "id владельца") int ownerId,
                                         @PathVariable @Parameter(description = "длина хвоста") int tailLength,
                                         Principal principal

    ) throws Exception {
        catService.addCat(name, birthday, breed, color, ownerId, tailLength, principal.getName());
        return ResponseEntity.ok("Saved");
    }

    @Operation(summary = "Удаление кота")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Кот удален")
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteCat (@PathVariable @Parameter(description = "id кота") int id,
                                             Principal principal) {
        catService.deleteCat(id, principal.getName());
        return ResponseEntity.ok("Delete");
    }

    @Operation(summary = "Получить кота по id")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Кот получен")
    )
    @GetMapping(path = "/getCat/byId/{id}")
    public ResponseEntity<Cat> getCatById (@PathVariable @Parameter(description = "id кота") int id,
                                           Principal principal) {
        return ResponseEntity.ok(catService.getCatById(id, principal.getName()));
    }

    @Operation(summary = "Получить котов по имени")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getCat/byName/{name}")
    public ResponseEntity<List<Cat>> getCatByName (@PathVariable @Parameter(description = "имя кота") String name,
                                                   Principal principal) {
        return ResponseEntity.ok(catService.getAllByName(name, principal.getName()));
    }

    @Operation(summary = "Получить котов по дате рождения")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getCat/byBirthday/{birthday}")
    public ResponseEntity<List<Cat>> getCatByBirthday (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                           @Parameter(description = "дата рождения кота") LocalDate birthday,
                                                           Principal principal) {
        return ResponseEntity.ok(catService.getAllByBirthday(birthday, principal.getName()));
    }

    @Operation(summary = "Получить котов по породе")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getCat/byBreed/{breed}")
    public ResponseEntity<List<Cat>> getCatByBreed (@PathVariable @Parameter(description = "порода кота") String breed,
                                                    Principal principal) {
        return ResponseEntity.ok(catService.getAllByBreed(breed, principal.getName()));
    }

    @Operation(summary = "Получить котов по цвету")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getCat/byColor/{color}")
    public ResponseEntity<List<Cat>> getCatByColor (@PathVariable @Parameter(description = "цвет кота") String color,
                                                    Principal principal) {
        return ResponseEntity.ok(catService.getAllByColor(color, principal.getName()));
    }

    @Operation(summary = "Получить котов по id владельца")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getCat/byOwnerId/{ownerId}")
    public ResponseEntity<List<Cat>> getCatByOwnerId (@PathVariable @Parameter(description = "id владельца кота") int ownerId,
                                                      Principal principal) {
        return ResponseEntity.ok(catService.getAllByOwnerId(ownerId, principal.getName()));
    }

    @Operation(summary = "удалить всех котов")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты удалены")
    )
    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll(Principal principal) {
        catService.deleteAll(principal.getName());
        return ResponseEntity.ok("Delete");
    }

    @Operation(summary = "Получить всех котов")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Коты получены")
    )
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Cat>> getAll(Principal principal) {
        return ResponseEntity.ok(catService.getAll(principal.getName()));
    }
}
