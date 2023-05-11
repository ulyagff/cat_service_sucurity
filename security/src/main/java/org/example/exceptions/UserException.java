package org.example.exceptions;

public class UserException extends RuntimeException{
    private UserException(String message) {
        super(message);
    }

    public static UserException OwnerHasNotAuthorityCat()
    {
        return new UserException("the owner hasn't rights on this cat");
    }

    public static UserException ResultIsEmpty()
    {
        return new UserException("List of Cats is empty");
    }
}
