package ds.exception;

@SuppressWarnings("serial")
public abstract class FeedException extends RuntimeException {
	private int msgId;
	
	protected FeedException(int id, String msg) {
		super(msg);
		msgId = id;
	}
	protected FeedException(String msg) {
		super(msg);
	}
	protected FeedException(String msg, Throwable t) {
		super(msg,t);
	}
	public int getMsgId() {
		return msgId;
	}
}
