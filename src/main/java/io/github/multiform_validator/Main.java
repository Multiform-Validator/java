/**
 * The Main class is the entry point of the application.
 * It provides a main method that prints a message to the console.
 * This class cannot be instantiated directly.
 * Please use the classes in the io.multiform_validator package.
 * This is a test version and should not be used in production.
 */
package io.github.multiform_validator;

import java.util.logging.Logger;
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.info("This class cannot be instantiated.");
        logger.info("Please use the classes in the io.multiform_validator package.");
        logger.info("READ the documentation: https://github.com/Multiform-Validator/java/tree/main/docs");
        logger.info("Multiform Validator: Version `0.0.5`");
        logger.info("Library for validating multiple types of documents.");
        logger.info("Created by: Gabriel Logan");
        logger.info("Github: https://github.com/gabriel-logan");
    }
}