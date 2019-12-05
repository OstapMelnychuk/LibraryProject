package dto;

import models.Author;

public class BookDto {
  private String title;
  private String description;
  private String dateOfPublishment;
  private Author author;

  public BookDto() {
  }

  public BookDto(String title, String description, String dateOfPublishment) {
    this.title = title;
    this.description = description;
    this.dateOfPublishment = dateOfPublishment;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDateOfPublishment() {
    return dateOfPublishment;
  }

  public void setDateOfPublishment(String dateOfPublishment) {
    this.dateOfPublishment = dateOfPublishment;
  }

  @Override
  public String toString() {
    return "Book{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", dateOfPublishment='" + dateOfPublishment + '\'' +
            '}';
  }

}
