package com.example.videoplay.utils.eventBus;

/**
 * @author Huangwy
 * @TIME 2016/4/14 20:34
 */
public class EventUtil {
    private int status;

    private Object result;

    public EventUtil(int status, Object result) {
        this.status = status;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

}
