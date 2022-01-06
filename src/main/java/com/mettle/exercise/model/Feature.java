package com.mettle.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "FEATURE")
@AllArgsConstructor
@NoArgsConstructor
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feature_id")
    private Long id;
    @Column(name = "featureName")
    private String name;
    private Boolean globallyEnabled;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<User> users;
}
