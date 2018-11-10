package server.services;

import org.springframework.stereotype.Service;
import server.entities.Role;
import server.entities.User;
import server.entities.dto.RequestDTO;
import server.entities.dto.ResponseDTO;
import server.entities.dto.request.UserRequest;
import server.entities.dto.response.RegisterResponse;
import server.entities.dto.response.StatusResponse;
import server.entities.dto.response.UserResponse;
import server.entities.repositories.RoleRepository;
import server.entities.repositories.UserRepository;
import server.services.register.CheckEntries;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseDTO checkUsername(String name) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (CheckEntries.isUserNameTaken(userRepository, name)) {
            responseDTO.setStatusResponse(StatusResponse.notOk());
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setMessageUsername("Username exists.");
            responseDTO.setRegisterResponse(registerResponse);
        } else {
            responseDTO.setStatusResponse(StatusResponse.ok());
        }


        return responseDTO;
    }

    public ResponseDTO addUser(RequestDTO requestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusResponse(StatusResponse.ok());

        RegisterResponse registerResponse = new RegisterResponse();
        responseDTO.setRegisterResponse(registerResponse);

        try {
            UserRequest userRequest = requestDTO.getRegisterRequest().getUserRequest();

            if (CheckEntries.isUserNameTaken(userRepository, userRequest.getUsername())) {
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessageUsername("Username already exists.");
            }

            if (CheckEntries.isEmailTaken(userRepository, userRequest.getEmail())) {
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessageEmail("Email already exists.");
            }

            if(userRequest.getUsername() == null || "".equals(userRequest.getUsername())){
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessageUsername("Username is required.");
            }

            if(userRequest.getUsername() != null && userRequest.getUsername().length() < 6){
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessageUsername("Username must be at least 6 characters.");
            }

            if(userRequest.getEmail() == null || "".equals(userRequest.getEmail())){
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessageEmail("Email is required.");
            }

            if(userRequest.getPassword() == null || "".equals(userRequest.getPassword())){
                responseDTO.setStatusResponse(StatusResponse.notOk());
                responseDTO.getRegisterResponse().setMessagePassword("Password is required.");
            }

            if (responseDTO.getStatusResponse().getMessage().equals(StatusResponse.ok().getMessage())) {
                User user = new User();
                user.insertDTOData(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
                if (roleRepository.findById(1).isPresent()) {
                    Role role = roleRepository.findById(1).get();
                    user.setRole(role);
                }
                userRepository.save(user);
                responseDTO.getRegisterResponse().setUserResponse(new UserResponse(userRequest));
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setStatusResponse(StatusResponse.formatError());
            return responseDTO;
        }

        return responseDTO;
    }
}
