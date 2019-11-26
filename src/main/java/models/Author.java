package models;

public class Author {
  private Integer id;
  private String firstname;
  private String secondname;
  private String surname;

  public Author() {
  }

  public Author(Integer id, String firstname, String secondname, String surname) {
    this.id = id;
    this.firstname = firstname;
    this.secondname = secondname;
    this.surname = surname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Author author = (Author) o;

    if (!id.equals(author.id)) return false;
    if (!firstname.equals(author.firstname)) return false;
    if (!secondname.equals(author.secondname)) return false;
    return surname.equals(author.surname);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + firstname.hashCode();
    result = 31 * result + secondname.hashCode();
    result = 31 * result + surname.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Author{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", secondname='" + secondname + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }
}
