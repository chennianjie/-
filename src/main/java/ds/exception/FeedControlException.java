/*
 * FeedControlException.java
 *
 * Copyright Reuters Ltd. 2000
 */

package ds.exception;


/**
 * FeedControlException is the parent of {@link FeedShutdownException} and
 * {@link FeedResetException} and is provided so that both can be caught
 * together where required.
 */
public class FeedControlException extends RuntimeException {
	final Exception exception;
	/**
	 * 
	 */
	private static final long serialVersionUID = -209863026302612367L;
	/**
	   * Construct a new FeedException with a (possibly) different error message
	   * from that contained in the wrapped exception.
	   * @param msg       the error message
	   * @param e         the wrapped exception
	   */
	  public FeedControlException(String msg, Exception e) {
	    super(msg);
	    exception = e;
	  }

	  /**
	   * Construct a new FeedException with the same message as that
	   * contained in the wrapped exception.
	   * @param e         the wrapped exception
	   */
	  public FeedControlException(Exception e) {
	    this(e.getMessage(), e);
	  }

	  /**
	   * Construct a new FeedException with a message but no wrapped exception.
	   * @param msg       the error message
	   */
	  public FeedControlException(String msg) {
	    this(msg, null);
	  }

	

	public Exception getException() {
		// TODO Auto-generated method stub
		return exception;
	}

}
