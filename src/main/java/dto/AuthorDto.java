package dto;

public class AuthorDto {
    private String name;
    private String secondname;
    private String surname;

    public AuthorDto() {
    }

    public AuthorDto(String name, String secondname, String surname) {
        this.name = name;
        this.secondname = secondname;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
