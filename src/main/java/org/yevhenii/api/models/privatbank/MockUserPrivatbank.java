package org.yevhenii.api.models.privatbank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MockUserPrivatbank {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
