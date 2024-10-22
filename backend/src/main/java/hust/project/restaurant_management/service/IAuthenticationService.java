package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.dto.request.AuthenticationRequest;
import hust.project.restaurant_management.entity.dto.request.IntrospectRequest;
import hust.project.restaurant_management.entity.dto.response.AuthenticationResponse;
import hust.project.restaurant_management.entity.dto.response.IntrospectResponse;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
