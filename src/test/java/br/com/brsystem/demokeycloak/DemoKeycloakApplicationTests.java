package br.com.brsystem.demokeycloak;

import br.com.brsystem.demokeycloak.dto.UserNameUpdate;
import br.com.brsystem.demokeycloak.dto.UserResetPassword;
import br.com.brsystem.demokeycloak.services.KeycloakUserService;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoKeycloakApplicationTests {

    private static final String ID_USER = "27a409da-15f6-4ff4-914f-9ccd33fa5f49";

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Test
    void changerPasswordUserByIdOnKeycloak() {
        UserResetPassword userResetPassword = new UserResetPassword();
        userResetPassword.setIdUser(ID_USER);
        userResetPassword.setPassword("123");

        keycloakUserService.resetPasswordUser(userResetPassword);
    }

    @Test
    void getUserByUsername() {
        UserRepresentation userRepresentation = keycloakUserService.getUserRepresentationByUsername("user");

        assertThat(userRepresentation.getFirstName()).isEqualTo("Bruno");
        assertThat(userRepresentation.getLastName()).isEqualTo("Ramon");
    }

    @Test
    void changerFirstNameAndLastName() {
        UserNameUpdate userNameUpdate = new UserNameUpdate();
        userNameUpdate.setFirstName("Bruno");
        userNameUpdate.setLastName("Ramon");

        keycloakUserService.updateNameUser(ID_USER, userNameUpdate);

        UserRepresentation userRepresentation = keycloakUserService.getUserRepresentation(ID_USER);

        assertThat(userRepresentation.getFirstName()).isEqualTo(userNameUpdate.getFirstName());
        assertThat(userRepresentation.getLastName()).isEqualTo(userNameUpdate.getLastName());
    }

    @Test
    void testForUsernameAndPassword() {
        String username = "user";
        String password = "123";

        boolean isCredentialsOk = keycloakUserService.checkUserCredentials(username, password);

        assertThat(isCredentialsOk).isTrue();
    }
}
