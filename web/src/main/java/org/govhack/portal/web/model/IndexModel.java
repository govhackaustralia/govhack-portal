package org.govhack.portal.web.model;

import org.govhack.portal.data.view.UserView;

public class IndexModel {

    private UserView user = null;

    public IndexModel() {
    }

    public IndexModel(UserView user) {
        this.user = user;
    }
}
