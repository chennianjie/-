package test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 12/1/2021
 */
public class GetAndSetObject {

    private BigDecimal iqm_id;

    private BigDecimal iqm_quo_permid;

    private BigDecimal iqm_ins_permid;

    private String message;

    private BigDecimal ciqm_status_code;

    private BigDecimal proc_status;

    private String update_by;

    private String update_date;

    private String create_by;

    private Date create_date;

    public BigDecimal getIqm_id() {
        return iqm_id;
    }

    public void setIqm_id(BigDecimal iqm_id) {
        this.iqm_id = iqm_id;
    }

    public BigDecimal getIqm_quo_permid() {
        return iqm_quo_permid;
    }

    public void setIqm_quo_permid(BigDecimal iqm_quo_permid) {
        this.iqm_quo_permid = iqm_quo_permid;
    }

    public BigDecimal getIqm_ins_permid() {
        return iqm_ins_permid;
    }

    public void setIqm_ins_permid(BigDecimal iqm_ins_permid) {
        this.iqm_ins_permid = iqm_ins_permid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getCiqm_status_code() {
        return ciqm_status_code;
    }

    public void setCiqm_status_code(BigDecimal ciqm_status_code) {
        this.ciqm_status_code = ciqm_status_code;
    }

    public BigDecimal getProc_status() {
        return proc_status;
    }

    public void setProc_status(BigDecimal proc_status) {
        this.proc_status = proc_status;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
