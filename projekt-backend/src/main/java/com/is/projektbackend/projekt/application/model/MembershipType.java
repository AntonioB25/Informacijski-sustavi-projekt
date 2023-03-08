package com.is.projektbackend.projekt.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membership_type")
public class MembershipType {
    public MembershipType() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_type_id", nullable = false)
    private Integer id;

    @Column(name = "membership_type_name", length = 50)
    private String membershipTypeName;

    @Column(name = "iznos")
    private Integer iznos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public Integer getIznos() {
        return iznos;
    }

    public void setIznos(Integer iznos) {
        this.iznos = iznos;
    }

}