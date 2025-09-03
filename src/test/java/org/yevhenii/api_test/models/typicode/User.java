package org.yevhenii.api_test.models.typicode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Data - Provides getters, setters, toString, equals, hashCode
 * @Builder - Enables builder pattern: User.builder().name("John").build()
 * @NoArgsConstructor - Creates empty constructor: new User()
 * @AllArgsConstructor - Creates constructor with all fields: new User(name, age, email)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String title;
    private String body;
    private Long userId;
    private String name;
    private int age;
    private String email;
    private boolean active;
    private AddressModel address;
    private List<OrderModel> orders;
}
