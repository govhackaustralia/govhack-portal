package org.govhack.portal.web.view;

import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.service.model.UserRoles;

import java.util.List;
import java.util.UUID;

public class SponsorView {

    private final UUID id;
    private final String name;

    private SponsorView() {
        id = null;
        name = null;
    }

    public SponsorView(Sponsor x) {
        id = x.getId();
        name = x.getName();
    }

}
