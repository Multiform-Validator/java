/**
 * The Main class is the entry point of the application. It provides a main method that prints a
 * message to the console. This class cannot be instantiated directly. Please use the classes in the
 * io.multiform_validator package. This is a test version and should not be used in production.
 */
package io.github.multiform_validator;

import java.util.logging.Logger;

public class Main {
  public static final String REPOSITORY_URL = "https://github.com/Multiform-Validator/java";
  public static final String BRANCH_NAME = "main";
  public static final String VERSION = "0.0.5";
  public static final String GITHUB_USERNAME = "gabriel-logan";
  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    logger.info("This class must not be instantiated.");
    logger.info("Please use the classes in the io.multiform_validator package.");
    logger.info(
        "READ the documentation: " + REPOSITORY_URL + "/blob/" + BRANCH_NAME + "/README.md");
    logger.info("Multiform Validator: Version " + VERSION);
    logger.info("Library for validating multiple types of documents.");
    logger.info("Created by: " + GITHUB_USERNAME);
    logger.info("Github: https://github.com/" + GITHUB_USERNAME);
  }
}
