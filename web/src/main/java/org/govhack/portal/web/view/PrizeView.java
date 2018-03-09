package org.govhack.portal.web.view;

import org.govhack.portal.data.model.Prize;

import java.util.UUID;

public class PrizeView {

    private final UUID id;
    private final String name;

    private PrizeView() {
        id = null;
        name = null;
    }

    public PrizeView(Prize x) {
        id = x.getId();
        name = x.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
