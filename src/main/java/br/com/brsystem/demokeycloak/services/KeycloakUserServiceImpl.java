package br.com.brsystem.demokeycloak.services;

import br.com.brsystem.demokeycloak.config.KeycloakConfig;
import br.com.brsystem.demokeycloak.dto.UserNameUpdate;
import br.com.brsystem.demokeycloak.dto.UserResetPassword;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Autowired
    private KeycloakConfig keycloakConfig;

    @Override
    public void resetPasswordUser(UserResetPassword userResetPassword) {
        UserResource userResource = keycloakConfig.getKeycloakRealmInstance().users().get(userResetPassword.getIdUser());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userResetPassword.getPassword());
        credentialRepresentation.setTemporary(false);

        userResource.resetPassword(credentialRepresentation);
    }

    @Override
    public void updateNameUser(String idUser, UserNameUpdate userNameUpdate) {
        UserResource userResource = keycloakConfig.getKeycloakRealmInstance().users().get(idUser);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.setFirstName(userNameUpdate.getFirstName());
        userRepresentation.setLastName(userNameUpdate.getLastName());

        userResource.update(userRepresentation);
    }

    @Override
    public UserRepresentation getUserRepresentation(String idUser) {
        UserResource userResource = keycloakConfig.getKeycloakRealmInstance().users().get(idUser);
        return userResource.toRepresentation();
    }

    @Override
    public UserRepresentation getUserRepresentationByUsername(String username) {
        List<UserRepresentation> userRepresentations = keycloakConfig.getKeycloakRealmInstance().users().search(username);

        return userRepresentations.get(0);
    }

    @Override
    public boolean checkUserCredentials(String username, String password) {
        return keycloakConfig.checkUserCredentials(username, password);
    }
}
