package org.govhack.portal.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "event")
public class Event extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = Region.class)
    private Region region;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    @OneToOne(targetEntity = User.class)
    private User owner;

    public Event(Competition competition, User owner, Region region, String name) {
        this.competition = competition;
        this.owner = owner;
        this.region = region;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

    public Competition getCompetition() {
        return competition;
    }

    public User getOwner() {
        return owner;
    }
}
