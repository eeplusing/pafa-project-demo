package com.pafa.exceptions;

/**
 * Title:        PAFA usermanagementge
 * Description:  PAFA User Management SDK
 * Copyright:    Copyright (c) 2002
 * Company:      Ping An Insurance China Ltd
 * @author       Gu Lifu
 * @author       Qiao,Hua
 * @version      2.0
 *
 * Update History
 * --------------
 *  franklin (07/07/04)- Add new attribute and methods from "PAFAException"
 */

import java.io.*;

// --- import PAFAException classes

import com.paic.pafa.exceptions2.exception.PAFAExceptionStatus;
import com.paic.pafa.exceptions2.utillities.ExceptionUtils;
import com.paic.pafa.util.PFStackTraceElement;
import com.paic.pafa.util.PFStackTraceParser;
import com.paic.pafa.exceptions2.errormessage.PAFAError;

/**
 * pafa�쳣�Ļ���
 * <li>���д��������</li>
 * <li>Ϊ������������쳣��Ϣ���쳣����ϣ����������ⲿʵ�֡�ԭ����صķ�������deprecated���?</li>
 * <li>��Ƕ���쳣</li>
 *
 */
public class PafaException
    extends Exception {

  /**
   * ԭ��
   */
  private Throwable cause;

  /**
   * ����δ֪�Ĵ������
   */
  public final static int UNKNOWN_ERROR = 0;

  /**
   * �����ϰ汾��pafaexcpetion
   */
  private static final long serialVersionUID = 4928763958558228413L;

  /**
   *�쳣�Ĵ������
   *
   */
  private int errorCode = UNKNOWN_ERROR;

  /**
   * frank lin 06/29/04: add PAFAExceptionStatus
   */
  protected PAFAExceptionStatus oPEStatus = null;

  protected PAFAError _oPAFAError = null ;

  /**
   * Ĭ�Ϲ��캯��
   */
  public PafaException() {
    this(UNKNOWN_ERROR, null, null);
  }

  /*
   *���г�ʼ���Ĵ������Ĺ��캯��
   *@deprecated PAFA version 2.0 ��֧�ָ÷��� ��PAFA���ʵ��
   */
  public PafaException(int code) {
    this(code, null, null);
  }

  /*
   *���г�ʼ���Ĵ�������Ƕ���쳣�Ĺ��캯��
   *@deprecated PAFA version 2.0 ��֧�ָ÷��� ��PAFA���ʵ��
   */
  public PafaException(int code, Throwable cause) {
    this(code, null, cause);
  }

  /**
   * @param cause ԭ��
   * ����Ƕ���쳣�Ĺ��캯��
   */
  public PafaException(Throwable cause) {
    this(UNKNOWN_ERROR, null, cause);
  }

  /*
   *���г�ʼ���Ĵ��������쳣��Ϣ�Ĺ��캯��
   *@deprecated PAFA version 2.0 ��֧�ָ÷��� ��PAFA���ʵ��
   */
  public PafaException(int code, String message) {
    this(code, message, null);
  }

  /**
   * @param message �쳣��Ϣ
   *�����쳣��Ϣ�Ĺ��캯��
   */
  public PafaException(String message) {
    this(UNKNOWN_ERROR, message, null);
  }

  /*
   *���г�ʼ���Ĵ��������쳣��Ϣ��Ƕ���쳣�Ĺ��캯��
   *@deprecated PAFA version 2.0 ��֧�ָ÷��� ��PAFA���ʵ��
   *
   * setup a PAFAExceptoin for PAFAExceptoin Handling
   */
  public PafaException(int inCode, String inMsg, Throwable inCause) {

    this("", inMsg,0,"",0,null,inCause ,PAFAExceptionStatus.WARNING);
    errorCode = inCode;

    // --- set PAFAExceptionStatus
/*
    if ( (Exception) cause instanceof PafaException &&
        (oPEStatus = ( (PafaException) cause).getPAFAExceptionStatus()) != null) {
    }
    else {
      oPEStatus = new PAFAExceptionStatus();
    }
    PAFAError oError = new PAFAError();
    oError.setThrowable(cause);
    oPEStatus.addError(oError);
*/
  }

  /**
   * @param message �쳣��Ϣ
   * @param cause   ԭ��
   *�����쳣��Ϣ��Ƕ���쳣�Ĺ��캯��
   */
  public PafaException(String message, Throwable cause) {
    this(UNKNOWN_ERROR, message, cause);
  }

  /**
   * constructor for Handle Non PafaException
   * @param inMsg - detailed exception message
   * @param inComponetID - component ID
   * @param inErrType - error type
   * @param inErrType - error type
   * @param inErrMsgType - error reason
   * @param inParams - message parameters
   * @param inCause - cause exception
   * @param inStatus - exception status
   * @param inLogIt - log the exception (Y/N)
   *
   * NOTE: This is based on JDK1.4 or above.
   * If you use JDK1.3, you need to block these two
   * 		constructors.
   */
  /*
  public PafaException(String inMsg, long inComponentID, String inErrType,
                       long inErrMsgType, String[] inParams, Throwable inCause,
                       long inStatus) {
    this(inMsg, inComponentID, inErrType, inErrMsgType, inParams,
         inCause, inStatus, true);
  }
*/

  /**
   * ����Non-PafaException
   * @param inUser - �û���
   * @param inMsg - ������Ϣ
   * @param inComponentType - �������������
   * @param inErrorTypeValue - �������ͣ����£�
   *                                   SYSTEM_ERROR
   *                                   FIELD_VALIDATION_ERROR
   *                                   DATA_INVALID_ERROR
   *                                   SQL_ERROR
   * @param inErrorMsgType - ����ԭ��
   * @param inErrorParams - ����ϸ��
   * @param inStatus - ���󼶱𣬼��£�
   *                   SUCCESS
   *                   WARNING
   *                   FATAL
   */
  public PafaException(String inUser ,
                       String inMsg,
                      long inComponentType,
                      String inErrorTypeValue,
                      long inErrorMsgType,
                      String[] inErrorParams,
                      long inStatus)
 {
                     this( inUser,
                           inMsg,
                           inComponentType,
                           inErrorTypeValue,
                           inErrorMsgType,
                           inErrorParams,
                           null,
                           inStatus);
 }

 /**
  * ����Non-PafaException
  * @param inUser - �û���
   * @param inMsg - ������Ϣ
   * @param inComponentType - �������������
   * @param inErrorTypeValue - �������ͣ����£�
   *                                    SYSTEM_ERROR
   *                                    FIELD_VALIDATION_ERROR
   *                                    DATA_INVALID_ERROR
   *                                    SQL_ERROR
   * @param inErrorMsgType - ����ԭ��
   * @param inErrorParams - ����ϸ��
   * @param inTh - Throwable
   * @param inStatus - ���󼶱𣬼��£�
   *                   SUCCESS
   *                   WARNING
   *                   FATAL
  */
 public PafaException(String inUser ,
                       String inMsg,
                       long inComponentType,
                       String inErrorTypeValue,
                       long inErrorMsgType,
                       String[] inErrorParams,
                       Throwable inTh,
                       long inStatus) {
    super( ""+inMsg,inTh);
    this.cause = inTh ;

    // --- set PAFAExceptionStatus here

    PFStackTraceElement pfse = PFStackTraceParser.parseFirst( this );
    String sClassName = "";
    String sMethodName ="" ;

    if ( pfse != null )
    {
      sClassName = pfse.getClassName();
      sMethodName = pfse.getMethodName();
    }

   _oPAFAError = ExceptionUtils.createPAFAError( inUser,
                                sClassName,
                                sMethodName,
                                inComponentType,
                                inErrorTypeValue,
                                inErrorMsgType,
                                inErrorParams,
                                inMsg,
                                inTh);

    if (inTh!=null &&  inTh instanceof PafaException && (oPEStatus = ((PafaException )inTh).getPAFAExceptionStatus())!=null)
    {
       oPEStatus.addError( _oPAFAError );
    }
    else
    {
       oPEStatus = new PAFAExceptionStatus();
       oPEStatus.addError( _oPAFAError );
       oPEStatus.setStatus( inStatus );
    }
  }

  /**
   * constructor for Handle Boundary PafaException
   * @param inMsg - message for the exception
   * @param inCause - catch another system's PafaException
   * @param inLogIt - log the exception (Y/N, detault is Y)
   *
   * NOTE: This is based on JDK1.4 or above.
   * If you use JDK1.3, you need to block these two
   * 		constructors.
   */
  /*
  public PafaException(String inMsg, PafaException inCause) {
    this(inMsg, inCause, true);
  }
*/
  /*
  public PafaException(String inMsg,
                       PafaException inCause) {
    this( inMsg, (Exception)inCause);
    // --- set PAFAException here
    PAFAExpCode oPECode = new PAFAExpCode();

    oPEStatus = ExceptionUtils.handleBoundaryPAFAException(
        oPECode.getUserName(),
        getPafaStackTrace(inCause)[1].getClassName(),
        getPafaStackTrace(inCause)[1].getMethodName(),
        inCause);
  }
*/
  /**
   * constructor for Handle Non PAFAException
   * @param inMsg - detailed exception message
   * @param inComponetID - component ID
   * @param inErrType - error type
   * @param inErrType - error type
   * @param inErrMsgType - error reason
   * @param inParams - message parameters
   * @param inCause - cause exception
   * @param inStatus - exception status
   * @param inObj - current object
   * @param inMethodName - current method name
   * @param inLogIt - log the exception (Y/N)
   *
   * NOTE: This is based on JDK1.3 or below.
   * If you use JDK1.4 or above, you may leave it alone, or block these two
   * 		constructors out.
   */
  /*
  public PafaException(String inMsg, long inComponentID, String inErrType,
                       long inErrMsgType, String[] inParams, Throwable inCause,
                       long inStatus, Object inObj, String inMethod) {
    this(inMsg, inComponentID, inErrType, inErrMsgType, inParams,
         inCause, inStatus, inObj, inMethod, true);
  }
*/
  /*
  public PafaException(String inMsg,
                       long inComponentID,
                       String inErrType,
                       long inErrMsgType,
                       String[] inParams,
                       Throwable inCause,
                       long inStatus,
                       String inClassName,
                       String inMethod) {
    this(inMsg, null);
    // --- set PAFAExceptionStatus here
    PAFAExpCode oPECode = new PAFAExpCode();
    oPEStatus = ExceptionUtils.handleNonPAFAException(
        oPECode.getUserName(),
        inClassName,
        inMethod,
        inComponentID,
        inErrType,
        inErrMsgType,
        inParams,
        inMsg,
        inCause,
        inStatus);
  }
*/
  /**
   * constructor for PafaException and PAFAException compatible
   * 					Handle Boundary PafaException
   * @param inMsg - message for the exception
   * @param inCause - catch another system's PafaException
   * @param inObj - current object
   * @param inMethodName - current method name
   * @param inLogIt - log the exception (Y/N, detault is Y)
   *
   * NOTE: This is based on JDK1.3 or below.
   * If you use JDK1.4 or above, you may leave it alone, or block these two
   * 		constructors out.
   */
  /*
  public PafaException(String inMsg, PafaException inCause, Object inObj,
                       String inMethodName) {
    this(inMsg, inCause, inObj, inMethodName, true);
  }
*/
  /*
  public PafaException(String inMsg,
                       PafaException inCause,
                       String inClassName,
                       String inMethodName) {
    this( inMsg, inCause);
    // --- set PAFAException here
    PAFAExpCode oPECode = new PAFAExpCode();

    oPEStatus = ExceptionUtils.handleBoundaryPAFAException(
        oPECode.getUserName(),
        inClassName,
        inMethodName,
        inCause);
  }
*/
  /**
   * Prints the stack backtrace.
   * ��ӡ��ջ��Ϣ��System.err
   * If an exception occurred during class loading it prints that
   * exception's stack trace, or else prints the stack backtrace of
   * this exception.
   */
  public void printStackTrace() {
    printStackTrace(System.err);
  }

  /**
   * Prints the stack backtrace to the specified print stream.
   * ��ӡ��ջ��Ϣ��PrintStream
   * If an exception occurred during class loading it prints that
   * exception's stack trace, or else prints the stack backtrace of
   * this exception.
   * @param inPS PrintStream
   */
  public void printStackTrace(PrintStream inPS) {
    if ( inPS == null) {
      throw new NullPointerException("PrintStream can't be NULL");
    }
/*
    synchronized (ps) {
      if (cause != null) {
        ps.println(this);
        cause.printStackTrace(ps);
      }
      else {
        super.printStackTrace(ps);
      }
    }
 */
synchronized ( inPS ) {
      super.printStackTrace( inPS );
      if (cause != null) {
        inPS.println("caused by:");
        cause.printStackTrace( inPS );
      }
    }

  }

  /**
   * Prints the stack backtrace to the specified print writer.
   * ��ӡ��ջ��Ϣ��PrintWriter
   * If an exception occurred during class loading it prints that
   * exception's stack trace, or else prints the stack backtrace of
   * this exception.
   * @param inPW PrintWriter
   */
  public void printStackTrace(PrintWriter inPW) {
    if ( inPW == null) {
      throw new NullPointerException("PrintWriter can't be NULL");
    }
/*
    synchronized (pw) {
      if (cause != null) {
        pw.println(this);
        cause.printStackTrace(pw);
      }
      else {
        super.printStackTrace(pw);
      }
    }
 */
synchronized ( inPW) {
      super.printStackTrace( inPW );
      if (cause != null) {
        inPW.println("caused by:");
        cause.printStackTrace(inPW);
      }
    }

 }

  /*
   *
   *@deprecated PAFA version 2.0
   *@see getCause()
   */
  public Exception getBaseException() {
    return (Exception) cause;
  }

  /**
   * Get Cause
   * ��ȡǶ�׵��쳣
   * @return cause throwable
   * added by Qiao,Hua
   * referenced by jdk1.4
   */
  public Throwable getCause() {
    return cause;
  }

  /**
   * Get Initial Cause
   * ��ȡ�쳣����������쳣.
   * @return cause throwable
   * added by Qiao,Hua
   *
   */
  public Throwable getInitialCause() {
    if (cause == null) {
      //�Լ��Ǵ����ԭ��
      return this;
    }
    else {
      if (cause instanceof PafaException) {
        //�ݹ���Ҵ����ԭ��
        return ( (PafaException) cause).getInitialCause();
      }
      else {
        //������쳣�Ǵ����ԭ��
        return cause;
      }
    }

  }

  /**
   * get error code
   * ��ȡ�������
   * @return �������
   *@deprecated ����PAFA���ʵ��
   */
  public int getErrorCode() {
    return this.errorCode;
  }

  /**
   * set error code
   * ���ô������
   * @param code �������
   *@deprecated ����PAFA���ʵ��
   */
  public void setErrorCode(int code) {
    this.errorCode = code;
  }

  // --------------------------------------------------------------------
  // New attributes & methods from "PAFAException"
  //
  /* the status of processes or transactions that generates this exception */
  // protected PAFAExceptionStatus pafaStatus = null;

  /**
   * default constructor
   */
  /*public PafaException() {
          super();
           }
   */

  /**
   * Constructs exception with exception status
   * @param inPafaStatus PAFAExceptionStatus
   */
  public PafaException(PAFAExceptionStatus inPafaStatus) {
    this();
    if ( inPafaStatus != null )
    {
      this.oPEStatus = inPafaStatus;
    }
  }

  /**
   * Returns the exception status object of this exception.
   *
   * @return PAFAExceptionStatus the status object of this exception
   */
  public PAFAExceptionStatus getPAFAExceptionStatus() {
    return oPEStatus;
  }

  /**
   * ��ȡerror
   * @return PAFAError
   */
  public PAFAError getPAFAError()
  {
    return _oPAFAError;
  }
public static void main(String[] args){
	PafaException pe=new PafaException("test");
	
	pe.printStackTrace();
}
}
