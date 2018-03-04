package org.govhack.portal.security;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class GovhackUser<T extends IGovhackUser> extends User {

    private final T user;

    /**
     * Note we're just generating a random UUID as we don't authenticate via passwords
     */
    public GovhackUser(T user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), UUID.randomUUID().toString(), authorities);
        this.user = user;
    }

    public T getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GovhackUser)) return false;
        if (!super.equals(o)) return false;
        GovhackUser govhackUser = (GovhackUser) o;
        return Objects.equals(user, govhackUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .toString();
    }
}
