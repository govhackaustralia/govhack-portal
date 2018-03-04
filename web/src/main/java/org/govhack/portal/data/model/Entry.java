package org.govhack.portal.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "entry")
public class Entry extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = Competition.class)
    private Competition competition;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "entries_prizes",
            joinColumns = { @JoinColumn(name = "entry_id") },
            inverseJoinColumns = { @JoinColumn(name = "prize_id") }
    )
    private Set<Prize> prizes = new HashSet<>();

    public String getName() {
        return name;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Entry(Competition competition, String name) {
        this.name = name;
        this.competition = competition;
    }

    public void setPrizes(Set<Prize> prizes) {
        this.prizes = prizes;
    }
}
