package org.govhack.portal.web.model;

import java.util.UUID;

public class PrizeCreateModel {

    private String name;
    private UUID sponsor;

    public PrizeCreateModel() {
    }

    public PrizeCreateModel(String name, UUID user, UUID sponsor) {
        this.name = name;
        this.sponsor = sponsor;
    }

    public String getName() {
        return name;
    }

    public UUID getSponsor() {
        return sponsor;
    }
}
