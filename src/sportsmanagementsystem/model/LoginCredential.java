package sportsmanagementsystem.model;

public final class LoginCredential {

    public final String userId;
    private final String password;

    public LoginCredential(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean isValidPassword(String password){
        return this.password.equals(password);
    }


}
