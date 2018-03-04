package org.govhack.portal.service.view;

import org.govhack.portal.data.model.User;
import org.govhack.portal.service.model.UserRoles;

import java.util.List;
import java.util.UUID;

public class UserView {

    private final UUID id;
    private final String username;
    private final List<UserRoles> roles;
    private final String firstName;
    private final String lastName;
    private final String email;

    private UserView() {
        id = null;
        username = null;
        roles = null;
        email = null;
        lastName = null;
        firstName = null;
    }

    public UserView(User x) {
        id = x.getId();
        username = x.getUsername();
        firstName = x.getFirstName();
        lastName = x.getLastName();
        email = x.getEmail();
        roles = x.getRoles();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

}
