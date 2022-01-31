package com.thales.serverapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;


@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String type;
    private Date registration;
    private Date eventRegister;

    @ManyToMany(mappedBy="events")
    private List<Client> clients;
}
