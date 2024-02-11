package DTO;

public class LoginDTO {
    String header;
    String userName;
    String password;

    public LoginDTO(String header, String userName, String password) {
        this.header = header;
        this.userName = userName;
        this.password = password;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
