package cz.czechitas.webapp;

public class Contact {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(Long id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newValue) {
        phoneNumber = newValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    @Override
    public String toString() {
        return "Contact " +
                "id=" + id + ", " +
                "name=\"" + name + "\"" + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "email=\"" + email + "\"";
    }
}
