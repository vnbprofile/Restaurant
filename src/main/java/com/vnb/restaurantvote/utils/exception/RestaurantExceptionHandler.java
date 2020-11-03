package com.vnb.restaurantvote.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestaurantExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VoteException.class)
    protected ResponseEntity<VotieException> handleDontCanVoteException() {
        return new ResponseEntity<>(new VotieException("Voting ends 11"), HttpStatus.NOT_ACCEPTABLE);
    }

    private static class VotieException {
        private String message;

        public VotieException(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
