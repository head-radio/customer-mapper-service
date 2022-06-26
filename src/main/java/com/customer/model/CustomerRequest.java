package com.customer.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

import static com.customer.util.FormatUtil.BASE_DATE_FORMAT;


@Data
@NotNull
public class CustomerRequest implements Serializable {

    @NotBlank(message = "customer is mandatory")
    String customerId;

    @NotNull(message = "createdAt is mandatory")
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "invalid format data - not matches " + BASE_DATE_FORMAT)
    String createdAt;

}
