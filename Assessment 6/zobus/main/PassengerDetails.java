package zobus.main;
public class PassengerDetails {
    public int id;
    public String name;
    public String password;
    int age;
    char gender;
    public PassengerDetails(int id, String name, String password, int age, char gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }
}
