package org.govhack.portal.web.model;

import java.util.UUID;

public class SponsorUpdateModel {

    private String name;
    private UUID user;

    public SponsorUpdateModel() {
    }

    public SponsorUpdateModel(String name, UUID user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public UUID getUser() {
        return user;
    }
}
