package server.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import server.entities.dto.request.RegisterRequest;
import server.entities.dto.request.UserRequest;

@Getter
public class RequestDTO {
    @JsonProperty("register")
    RegisterRequest registerRequest;
    @JsonProperty("user")
    UserRequest userRequest;

}
