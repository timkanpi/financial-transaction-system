package com.example.financialtransactionsystemapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Tag(name = "personal-data")
@RequestMapping(path = "/personal-data/update")
public interface PersonalDataController {

    @Operation(summary = "Добавление нового телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @PostMapping(path = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createPhone(@RequestBody Map<String, Object> requestMap);

    @Operation(summary = "Изменение телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @PutMapping(path = "/phone/{phoneId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updatePhone(@PathVariable Long phoneId,
                     @RequestBody Map<String, Object> requestMap);

    @Operation(summary = "Удаление телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @DeleteMapping(path = "/phone/{phoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhone(@PathVariable Long phoneId);

    @Operation(summary = "Изменение email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @PutMapping(path = "/email/{emailId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateEmail(@PathVariable Long emailId,
                     @RequestBody Map<String, Object> requestMap);

    @Operation(summary = "Удаление email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @DeleteMapping(path = "/email/{emailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmail(@PathVariable Long emailId);
}
