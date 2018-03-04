package org.govhack.portal.security;

import java.util.List;

public interface IGovhackUser {
    String getUsername();

    String getPassword();

    String getFirstName();

    void setFirstName(String firstName);

    String getEmail();

    void setEmail(String email);

    String getLastName();

    void setLastName(String lastName);

    List<UserRoles> getRoles();

    void setRoles(List<UserRoles> roles);
}
