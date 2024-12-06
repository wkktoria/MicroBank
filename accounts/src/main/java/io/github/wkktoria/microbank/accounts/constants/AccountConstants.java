package io.github.wkktoria.microbank.accounts.constants;

public class AccountConstants {
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request has been processed successfully.";

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account has been created successfully.";

    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Updated operation failed. Please try again or contact an administrator.";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact an administrator.";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact an administrator.";

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "123 Main Street, New York";

    private AccountConstants() {
    }
}
