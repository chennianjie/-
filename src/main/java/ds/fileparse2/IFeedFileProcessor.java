package ds.fileparse2;

import ds.common.FeedInfo;
import ds.exception.FeedException;


public interface IFeedFileProcessor{

	  public void process() throws FeedException;
	  public void setFeedInfo(FeedInfo feedInfo);
	  public FeedInfo getFeedInfo();
	  public int getExecutionOrder();
}
