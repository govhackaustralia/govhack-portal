package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "competition")
public class Competition extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Event> eventList;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Prize> prizeList;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Region> regionList;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Sponsor> sponsorList;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Team> teamList;

    @OneToMany
    @JoinColumn(name = "competition")
    List<Entry> entryList;

    public Competition() {
    }

    public Competition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public List<Sponsor> getSponsorList() {
        return sponsorList;
    }
}
