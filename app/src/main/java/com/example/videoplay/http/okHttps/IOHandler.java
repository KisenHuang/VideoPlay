package com.example.videoplay.http.okHttps;
/* 此类无用 */
import com.example.videoplay.utils.LogUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.FrameReader;
import okhttp3.internal.framed.Header;
import okhttp3.internal.framed.HeadersMode;
import okhttp3.internal.framed.Settings;
import okio.BufferedSource;
import okio.ByteString;

/**
 * @author Huangwy
 * @TIME 2016/3/12 10:09
 */
public class IOHandler implements FrameReader.Handler {
    private static final String TAG = LogUtil.makeTag(IOHandler.class);

    @Override
    public void data(boolean inFinished, int streamId, BufferedSource source, int length) throws IOException {
        LogUtil.e(TAG, "data:" + source.readByte());
    }

    @Override
    public void headers(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock, HeadersMode headersMode) {
        LogUtil.e(TAG, "headers:" + headerBlock.size());
    }

    @Override
    public void rstStream(int streamId, ErrorCode errorCode) {
        LogUtil.e(TAG, "rstStream:" + errorCode);
    }

    @Override
    public void settings(boolean clearPrevious, Settings settings) {
        LogUtil.e(TAG, "settings:" + settings.toString());
    }

    @Override
    public void ackSettings() {
        LogUtil.e(TAG, "ackSettings:");
    }

    @Override
    public void ping(boolean ack, int payload1, int payload2) {
        LogUtil.e(TAG, "ping:" + ack);
    }

    @Override
    public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
        LogUtil.e(TAG, "goAway:" + errorCode + "ByteString" + debugData.size());
    }

    @Override
    public void windowUpdate(int streamId, long windowSizeIncrement) {
        LogUtil.e(TAG, "windowUpdate:");
    }

    @Override
    public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        LogUtil.e(TAG, "priority:");
    }

    @Override
    public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
        LogUtil.e(TAG, "pushPromise:");

    }

    @Override
    public void alternateService(int streamId, String origin, ByteString protocol, String host, int port, long maxAge) {
        LogUtil.e(TAG, "alternateService:");
    }
}