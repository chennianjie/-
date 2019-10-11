package ds.exception;

@SuppressWarnings("serial")
public abstract class FatalFeedException extends FeedException {
	 public FatalFeedException(int code, String msg)
	  {
	    super(code, msg);
	  }
	 public FatalFeedException(String msg)
	  {
	    super(msg);
	  }
}
