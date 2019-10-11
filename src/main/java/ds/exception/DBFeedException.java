package ds.exception;

@SuppressWarnings("serial")
public class DBFeedException extends FatalFeedException {

	public DBFeedException(int code, String msg) {
		super(code, msg);
	}

	public DBFeedException(String msg) {
		super(msg);
	}

}
