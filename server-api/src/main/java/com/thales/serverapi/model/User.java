package com.thales.serverapi.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String document;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="event_user",
        joinColumns = @JoinColumn(name="event_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"))
    private List<Event> events;
}
