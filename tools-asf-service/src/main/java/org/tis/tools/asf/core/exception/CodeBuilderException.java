package org.tis.tools.asf.core.exception;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/8
 **/
public class CodeBuilderException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CodeBuilderException(){

    };

    public CodeBuilderException(String message) {
        super(message);
    }
    public CodeBuilderException(String message, Throwable throwable) {
        super(message, throwable);
    }

//    public CodeBuilderException(String code, Object[] placeholders) {
//        super(code,placeholders) ;
//    }
//
//    public CodeBuilderException(String code, Object[] params, String message) {
//        super(code,params,message) ;
//    }
//
//    public CodeBuilderException(String code, String message) {
//        super(code,message) ;
//    }
}