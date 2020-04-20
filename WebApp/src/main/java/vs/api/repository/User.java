package vs.api.repository;

public class User implements Comparable<User> {
    private int _id;
    private String _name;
    private String _surname;
    private String _email;
    private String _password;
    private int _dataFreeAmount;
    private int _userType;

    public User() {
        _name = "";
        _surname = "";
        _email = "";
        _password = "";
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        if (name != null && name.length() <= 32) {
            _name = name;
        }
    }

    public String getSurname() {
        return _surname;
    }

    public void setSurname(String surname) {
        if (surname != null && surname.length() <= 32) {
            _surname = surname;
        }
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() <= 32) {
            _email = email;
        }
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() <= 32) {
            _password = password;
        }
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getDataFreeAmount() {
        return _dataFreeAmount;
    }

    public void setDataFreeAmount(int dataFreeAmount) {
        _dataFreeAmount = dataFreeAmount;
    }

    public int getUserType() {
        return _userType;
    }

    public void setUserType(int userType) {
        this._userType = userType;
    }

    @Override
    public int compareTo(User o) {
        return this.getSurname().compareTo(o.getSurname());
    }
}
