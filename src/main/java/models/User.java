package models;

public class User {
  private Integer id;
  private String nickName;
  private String login;
  private String password;
  private Integer roleId;
  private String email;
  private Integer age;
  private String startDay;

  public User(){

  }

  public User(Integer id, String nickName, String login, String password,
              Integer roleId, String email, Integer age, String startDay) {
    this.id = id;
    this.nickName = nickName;
    this.login = login;
    this.password = password;
    this.roleId = roleId;
    this.age = age;
    this.startDay = startDay;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getStartDay() {
    return startDay;
  }

  public void setStartDay(String startDay) {
    this.startDay = startDay;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNick_name() {
    return nickName;
  }

  public void setNick_name(String nickName) {
    this.nickName = nickName;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()){
      return false;
    }

    User user = (User) o;

    if (!id.equals(user.id)) {
      return false;
    }
    if (!nickName.equals(user.nickName)) {
      return false;
    }
    if (!login.equals(user.login)) {
      return false;
    }
    if (!password.equals(user.password)) {
      return false;
    }
    if (!age.equals(user.age)) {
      return false;
    }
    if(!startDay.equals(user.startDay)){
      return false;
    }
    return roleId.equals(user.roleId);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + nickName.hashCode();
    result = 31 * result + login.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + roleId.hashCode();
    result = 31 * result + age.hashCode();
    result = 31 * result + startDay.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", nickName='" + nickName + '\'' +
      ", login='" + login + '\'' +
      ", password='" + password + '\'' +
      ", roleId=" + roleId +
      ", age=" + age +
      ", startDay='" + startDay + '\'' +
      '}';
  }
}
