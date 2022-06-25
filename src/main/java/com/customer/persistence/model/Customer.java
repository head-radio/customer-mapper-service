package com.customer.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Customer {

    @Id
    private String customerId;
    private String externalId;
    private Date createdAt;

}