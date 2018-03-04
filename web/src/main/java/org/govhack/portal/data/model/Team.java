package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    @OneToMany
    @JoinColumn(name = "team_id")
    private Set<Entry> entries;

    @OneToMany
    @JoinColumn(name = "team_id")
    private Set<User> members;

    public Team(Competition competition, String name) {
        this.name = name;
        this.competition = competition;
    }
}
