package com.unibuc.FTR.exception;

import com.unibuc.FTR.constants.Constants;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){
        super(Constants.DATA_NOT_FOUND);
    }

}