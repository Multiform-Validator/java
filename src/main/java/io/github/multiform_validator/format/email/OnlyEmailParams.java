package io.github.multiform_validator.format.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/** Options for the getOnlyEmail method. */
@Setter
@Getter
@AllArgsConstructor
@NonNull
public class OnlyEmailParams {
  @NonNull private Boolean multiple;
  @NonNull private Object cleanDomain;
  @NonNull private Boolean repeatEmail;

  public OnlyEmailParams() {
    this.multiple = false;
    this.cleanDomain = false;
    this.repeatEmail = false;
  }
}
