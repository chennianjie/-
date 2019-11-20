package ds.fileparse;

import ds.common.FeedInfo;
import ds.exception.FeedException;


public interface IFeedFileProcessor {

	  public void process() throws FeedException;
	  public void setFeedInfo(FeedInfo feedInfo);
	  public FeedInfo getFeedInfo();
}
