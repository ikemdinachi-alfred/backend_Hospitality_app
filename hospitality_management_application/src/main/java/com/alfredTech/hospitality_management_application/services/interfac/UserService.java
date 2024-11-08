package com.alfredTech.hospitality_management_application.services.interfac;

import com.alfredTech.hospitality_management_application.data.models.User;
import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.LoginRequest;

public interface UserService {
    Response registerUser(User user);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUserBookingHistory(String userId);
    Response deleteUser(String userId);
    Response getUserById(String userId);
    Response getMyInfo(String email);

}
