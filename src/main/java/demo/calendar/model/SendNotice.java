package demo.calendar.model;

import java.util.Date;

public class SendNotice {
    private Long id;
    private String service;
    private String message;
    private String receiver;
    private String email;
    private Date creationDate;

    public SendNotice() {
    }

    public SendNotice(Long id, String service, String message, String receiver, String email, Date creationDate) {
        this.id = id;
        this.service = service;
        this.message = message;
        this.receiver = receiver;
        this.email = email;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
