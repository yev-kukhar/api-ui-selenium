package org.yevhenii.api_test.models.typicode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private String id;
    private String status;
    private double amount;
}
