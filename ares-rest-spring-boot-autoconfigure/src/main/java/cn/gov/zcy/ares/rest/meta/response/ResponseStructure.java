package cn.gov.zcy.ares.rest.meta.response;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 15:39:05
 */
public class ResponseStructure<T> {
    private int repCode;

    private String repMsg;

    public int getRepCode() {
        return repCode;
    }

    public void setRepCode(int repCode) {
        this.repCode = repCode;
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public T getRepBody() {
        return repBody;
    }

    public void setRepBody(T repBody) {
        this.repBody = repBody;
    }

    private T repBody;
}
