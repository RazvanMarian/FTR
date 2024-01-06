package com.unibuc.FTR.exception;

import com.unibuc.FTR.constants.Constants;

public class DataAlreadyExistsException extends RuntimeException{

    public DataAlreadyExistsException(){
        super(Constants.DATA_ALREADY_EXISTS);
    }

}
