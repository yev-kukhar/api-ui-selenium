package org.yevhenii.api.models.typicode;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * POJO (Plain Old Java Object)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    private Integer id;
    private String title;
    private String body;
    private Integer userId;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}