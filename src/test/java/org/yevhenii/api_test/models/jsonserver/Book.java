package org.yevhenii.api_test.models.jsonserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yevhenii.api_test.models.typicode.AddressModel;
import org.yevhenii.api_test.models.typicode.OrderModel;

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
public class Book {
    private String id;
    private String title;
    private String author;
}
