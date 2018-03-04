package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "region")
public class Region extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "region")
    List<Event> eventList;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    public Region(Competition competition, String name) {
        this.competition = competition;
        this.name = name;
    }
}
