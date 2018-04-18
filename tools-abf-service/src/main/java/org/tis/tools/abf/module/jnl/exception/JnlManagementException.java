package org.tis.tools.abf.module.jnl.exception;


import org.tis.tools.core.exception.ToolsRuntimeException;

public class JnlManagementException extends ToolsRuntimeException {
    private static final long serialVersionUID = 1L;

    public JnlManagementException() {super();}

    public JnlManagementException(String code) {
        super(code);
    }

    public JnlManagementException(String code, Object[] placeholders) {
        super(code,placeholders) ;
    }

    public JnlManagementException(String code, Object[] params, String message) {
        super(code,params,message) ;
    }

    public JnlManagementException(String code, String message) {
        super(code,message) ;
    }
}

