package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prize")
public class Prize extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    @OneToOne(targetEntity = Sponsor.class)
    private Sponsor sponsor;

    @ManyToMany(mappedBy = "prizes")
    private Set<Entry> entries = new HashSet<>();

    public Prize(String name, Competition competition, Sponsor sponsor) {
        this.name = name;
        this.competition = competition;
        this.sponsor = sponsor;
    }
}
