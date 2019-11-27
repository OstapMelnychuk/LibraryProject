package models;

public class Journal {
  private Integer id;
  private Integer bookId;
  private Integer userId;
  private String dateOfOutput;
  private String dateOfInput;

  public Journal(){

  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getBookId() {
    return bookId;
  }

  public void setBookId(Integer bookId) {
    this.bookId = bookId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getDateOfOutput() {
    return dateOfOutput;
  }

  public void setDateOfOutput(String dateOfOutput) {
    this.dateOfOutput = dateOfOutput;
  }

  public String getDateOfInput() {
    return dateOfInput;
  }

  public void setDateOfInput(String dateOfInput) {
    this.dateOfInput = dateOfInput;
  }
}
