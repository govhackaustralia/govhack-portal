package org.govhack.portal.data.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.govhack.portal.service.model.UserRoles;
import org.govhack.portal.utils.ArrayType;
import org.govhack.portal.utils.JSONBUserType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "govhack_user")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JSONBUserType.class),
        @TypeDef(name = "text[]", typeClass = ArrayType.class, parameters = {
                @Parameter(name = "type", value = "java.lang.String")
        })})
public class User extends BaseEntity implements Serializable {

    @Column(name = "username", nullable = false)
    private final String username;

    @Column(name = "password", nullable = false)
    private final String password;

    @Column(name = "email", nullable = false)
    private final String email;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "roles", nullable = false)
    @Type(type = "text[]")
    private String[] roles;

    @OneToOne(targetEntity = Team.class)
    private Team team;

    protected User() {
        username = null;
        password = null;
        email = null;
        firstName = null;
        lastName = null;
    }

    public User(String username, String displayName, List<UserRoles> roles) {
        this.username = username;
        this.password = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.roles = roles.stream().map(UserRoles::getNiceName).toArray(String[]::new);
    }

    public User(String username, String password, String email, String firstName, String lastName, UserRoles userRoles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = Arrays.asList(userRoles.getNiceName()).toArray(new String[0]);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public List<UserRoles> getRoles() {
        return Arrays.stream(roles)
                .map(UserRoles::fromString).collect(Collectors.toList());
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles.stream().map(UserRoles::getNiceName).toArray(String[]::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Arrays.equals(roles, user.roles) &&
                Objects.equals(team, user.team);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(super.hashCode(), username, password, email, firstName, lastName, team);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", team=" + team +
                '}';
    }
}
