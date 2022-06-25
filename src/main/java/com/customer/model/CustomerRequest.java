package com.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

import static com.customer.util.FormatUtil.BASE_DATE_FORMAT;

@Data
public class CustomerRequest implements Serializable {

    String customerId;

    @JsonFormat(pattern = BASE_DATE_FORMAT)
    String createdAt;

}
