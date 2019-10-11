package ds.exception;

@SuppressWarnings("serial")
public class FeedAbortException extends FeedControlException {

	public FeedAbortException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public FeedAbortException(String message, Exception fce) {
		super(message,fce);
	}

}
