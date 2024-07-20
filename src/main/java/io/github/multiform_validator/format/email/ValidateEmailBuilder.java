package io.github.multiform_validator.format.email;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** The ValidateEmailBuilder class represents the options for email validation. */
@Setter
@Getter
@AllArgsConstructor
public class ValidateEmailBuilder {
  private int maxLength;
  private String country;
  private boolean validDomains;
  private List<String> validDomainsList;

  public ValidateEmailBuilder() {
    this.maxLength = 400;
    this.country = "";
    this.validDomains = false;
    this.validDomainsList = new ArrayList<>();
  }
}
