package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sponsor")
public class Sponsor extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    @OneToMany
    @JoinColumn(name = "sponsor")
    List<Prize> prizeList;

    @OneToOne(targetEntity = User.class)
    User owner;

    public Sponsor(Competition competition, User owner, String name) {
        this.competition = competition;
        this.owner = owner;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        this.prizeList = prizeList;
    }
}
