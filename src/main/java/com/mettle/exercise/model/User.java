package com.mettle.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    //    Roles is a comma separated list
    private String authorities;

/*    TODO:
Make roles as a set of RoleType and authority on each role type
@ManyToMany
private Set<Role> roles;
 */

}