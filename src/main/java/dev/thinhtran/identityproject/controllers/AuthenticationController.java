package dev.thinhtran.identityproject.controllers;

import com.nimbusds.jose.JOSEException;
import dev.thinhtran.identityproject.dto.request.AuthenticationRequest;
import dev.thinhtran.identityproject.dto.request.IntrospectRequest;
import dev.thinhtran.identityproject.dto.response.ApiResponse;
import dev.thinhtran.identityproject.dto.response.AuthenticationResponse;
import dev.thinhtran.identityproject.dto.response.IntrospectResponse;
import dev.thinhtran.identityproject.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    final AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(result);

        return apiResponse;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(result);

        return apiResponse;
    }
}
