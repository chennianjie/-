package test.humantask;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 1/7/2021
 */
public class HumantaskExceptionInfo {

    //iqm_humantask.error表中的id（必填）
    private String exceptionNumber;
    //交易所名字 iqm_humantask.exchange表中,eg:'ESMA'（必填）
    private String exchange;

    //feed名字，eg:"NDA_NIRD"
    private String feed;
    //feed名字
    private String createBy;
    //异常描述（必填）
    private String description = "";
    private String fileType;

    private String symbol;
    private String issueEvent;
    //对应ASC_XXX_IV view中的ID值,用于追溯异常来源（必填）
    private String INC_ID;
    private String companyName;
    private String CUSIP;
    private String effectiveDate;
    private String marketCategory;
    private String subMarket;

    public String getExceptionNumber() {
        return exceptionNumber;
    }

    public void setExceptionNumber(String exceptionNumber) {
        this.exceptionNumber = exceptionNumber;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 3000) {
            this.description = description.substring(0, 3000) + "...";
        }else {
            this.description = description;
        }
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIssueEvent() {
        return issueEvent;
    }

    public void setIssueEvent(String issueEvent) {
        this.issueEvent = issueEvent;
    }

    @JSONField(name = "INC_ID")
    public String getINC_ID() {
        return INC_ID;
    }

    public void setINC_ID(String INC_ID) {
        this.INC_ID = INC_ID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JSONField(name = "CUSIP")
    public String getCUSIP() {
        return CUSIP;
    }

    public void setCUSIP(String CUSIP) {
        this.CUSIP = CUSIP;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getMarketCategory() {
        return marketCategory;
    }

    public void setMarketCategory(String marketCategory) {
        this.marketCategory = marketCategory;
    }

    public String getSubMarket() {
        return subMarket;
    }

    public void setSubMarket(String subMarket) {
        this.subMarket = subMarket;
    }

    @Override
    public String toString() {
        return "HumantaskExceptionInfo{" +
                "exceptionNumber='" + exceptionNumber + '\'' +
                ", exchange='" + exchange + '\'' +
                ", feed='" + feed + '\'' +
                ", createBy='" + createBy + '\'' +
                ", description='" + description + '\'' +
                ", fileType='" + fileType + '\'' +
                ", symbol='" + symbol + '\'' +
                ", issueEvent='" + issueEvent + '\'' +
                ", INC_ID='" + INC_ID + '\'' +
                ", companyName='" + companyName + '\'' +
                ", CUSIP='" + CUSIP + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", marketCategory='" + marketCategory + '\'' +
                ", subMarket='" + subMarket + '\'' +
                '}';
    }

    public static void main(String[] args) {
        HumantaskExceptionInfo humantaskExceptionInfo = new HumantaskExceptionInfo();
        humantaskExceptionInfo.setINC_ID("500559948");
        humantaskExceptionInfo.setFeed("");
        humantaskExceptionInfo.setDescription("...........................");
        humantaskExceptionInfo.setCompanyName("");
        humantaskExceptionInfo.setIssueEvent("");
        humantaskExceptionInfo.setCreateBy("");
        humantaskExceptionInfo.setSymbol("");
        humantaskExceptionInfo.setEffectiveDate("");
        humantaskExceptionInfo.setExceptionNumber("1000377");
        humantaskExceptionInfo.setExchange("CHEN");
        humantaskExceptionInfo.setFileType("");
        String json = JSONObject.toJSONString(humantaskExceptionInfo);
        System.out.println(json);

//        HumanTaskUtil instance = HumanTaskUtil.getInstance();
//        boolean b = instance.insertExceptionToHM(humantaskExceptionInfo);
//        if (b){
//            System.out.println("success");
//        }else {
//            System.out.println("fail");
//        }
    }
}
