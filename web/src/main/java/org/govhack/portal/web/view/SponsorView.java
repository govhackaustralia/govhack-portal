package org.govhack.portal.web.view;

import org.govhack.portal.data.model.Prize;
import org.govhack.portal.data.model.Sponsor;

import java.util.List;
import java.util.UUID;

public class SponsorView {

    private final UUID id;
    private final String name;
    private final List<Prize> prizeList;

    private SponsorView() {
        id = null;
        name = null;
        prizeList = null;
    }

    public SponsorView(Sponsor x) {
        id = x.getId();
        name = x.getName();
        prizeList = x.getPrizeList();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }
}
