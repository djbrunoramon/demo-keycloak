package br.com.brsystem.demokeycloak.services;

import br.com.brsystem.demokeycloak.dto.UserNameUpdate;
import br.com.brsystem.demokeycloak.dto.UserResetPassword;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {
    void resetPasswordUser(UserResetPassword userResetPassword);

    void updateNameUser(String idUser, UserNameUpdate userNameUpdate);

    UserRepresentation getUserRepresentation(String idUser);

    UserRepresentation getUserRepresentationByUsername(String username);

    boolean checkUserCredentials(String username, String password);
}
