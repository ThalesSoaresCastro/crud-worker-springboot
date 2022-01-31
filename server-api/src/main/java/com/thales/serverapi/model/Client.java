package com.thales.serverapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String document;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="event_client",
        joinColumns = @JoinColumn(name="event_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="client_id",referencedColumnName = "id"))
    private List<Event> events;
}
