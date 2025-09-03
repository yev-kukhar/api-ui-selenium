package org.yevhenii.api_test.models.typicode;

import lombok.Data;
import org.junit.jupiter.api.Order;

import java.util.List;

@Data
public class PostRecordModel {
    private String name;
    private int age;
    private String email;
    private boolean active;
    private AddressModel address;
    private List<OrderModel> orders;
}

