package com.alfredTech.hospitality_management_application.controllers;
import com.alfredTech.hospitality_management_application.data.models.User;
import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.LoginRequest;
import com.alfredTech.hospitality_management_application.services.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
       Response response = userService.registerUser(user);
       return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        Response response = userService.loginUser(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
