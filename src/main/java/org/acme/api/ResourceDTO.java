package org.acme.api;

public class ResourceDTO {

    public ResourceDTO() {
    }

    public ResourceDTO(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
