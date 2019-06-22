public class Client {
    private String familyName;
    private String givenName;
    private String email;
    private String phone;

    public Client(String familyName, String givenName, String email, String phone){
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.phone = phone;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

	@Override
	public String toString() {
		return "Client{" +
				"familyName='" + familyName + '\'' +
				", givenName='" + givenName + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}