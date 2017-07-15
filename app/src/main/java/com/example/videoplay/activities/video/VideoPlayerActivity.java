package com.example.videoplay.activities.video;

//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.example.videoplay.BaseActivity;
//import com.example.videoplay.R;
//import com.example.videoplay.http.HttpUrls;
//import com.example.videoplay.http.okHttps.RequestParam;
//import com.example.videoplay.model.UserVideoInfo;
//import com.example.videoplay.utils.LogUtil;
//
//import io.vov.vitamio.MediaPlayer;
//import io.vov.vitamio.Vitamio;
//import io.vov.vitamio.widget.MediaController;
//import io.vov.vitamio.widget.VideoView;
//import okhttp3.HttpUrl;
//import okhttp3.MediaType;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//
//
//public class VideoPlayerActivity extends BaseActivity {
//
//    private static final String TAG = LogUtil.makeTag(VideoPlayerActivity.class);
//
//    private VideoView mVideoView;
//    private TextView mSubtitleView;
//    private long mPosition = 0;
//    private int mVideoLayout = 0;
//    private UserVideoInfo userVideoInfo;
//    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Vitamio.isInitialized(this);
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
////                WindowManager.LayoutParams.FLAG_FULLSCREEN);
////        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
////            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setContentView(R.layout.activity_video_player);
//        initView();
////        initData();
////        playfunction();
//    }
//
//    private void initView() {
//        userVideoInfo = (UserVideoInfo) getIntent().getSerializableExtra("userVideoInfo");
//        mVideoView = (VideoView) findViewById(R.id.surface_view);
//        mSubtitleView = (TextView) findViewById(R.id.subtitle_view);
//    }
//
//    private void initData() {
//        initStream();
//    }
//
//    private void loadM3u8(String streamJson) {
//        RequestBody body = RequestBody.create(JSON,json);
////        RequestBody body = new FormBody.Builder().add("info", json).build();
//        RequestParam param = new RequestParam(HttpUrls.MAX);
//        param.put("game_type", "dota2");
//        param.put("link", userVideoInfo.getLink());
//        Request request = new Request.Builder()
//                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.MAX_VIDEO_DETAIL, param)))
//                .post(body)
//                .build();
//        handlerNet(request, HttpUrls.REQ_CODE_POST);
//    }
//
//    private void initStream() {
//        RequestParam param = new RequestParam();
//        param.put("Referer", userVideoInfo.getReferer());
//        param.put("User-Agent", userVideoInfo.getUser_Agent());
//        Request request = new Request.Builder()
//                .url(HttpUrl.parse(userVideoInfo.getCt() +"&"+ HttpUrls.trimUrl(param.getParams())))
//                .build();
//        handlerNet(request, HttpUrls.REQ_CODE_GET);
//    }
//
//    @Override
//    public void onHttpSuccess(int reqcode, String data) {
//        super.onHttpSuccess(reqcode, data);
//        switch (reqcode) {
//            case HttpUrls.REQ_CODE_GET:
//                loadM3u8(data);
//                break;
//            case HttpUrls.REQ_CODE_POST:
//
//                break;
//        }
//    }
//
//    private void playfunction() {
//        String path = "http://player.youku.com/player.php/sid/XMTUxMTkyODEwMA==/partnerid/0edbfd2e4fc91b72/v.swf";
////        path = "http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4";
////        path = "http://pl.youku.com/playlist/m3u8?vid=377913953&time=1458908730&ts=1458908730&ctype=12&token=2939&keyframe=0&sid=645890873069712a2d7be&ev=1&type=hd2&ep=dCaREkCNXskD5SXWjz8bNi62cyVaXP0N8RaEgttnA9QmQeu2&oip=2096831764";
//        if (path == "") {
//            // Tell the user to provide a media file URL/path.
//            return;
//        } else {
//            /*
//             * Alternatively,for streaming media you can use
//			 * mVideoView.setVideoURI(Uri.parse(URLstring));
//			 */
//            mVideoView.setVideoPath(path);
//            mVideoView.setMediaController(new MediaController(this));
//            mVideoView.requestFocus();
//            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mediaPlayer.setPlaybackSpeed(1.0f);
//                    mVideoView.addTimedTextSource("情书dota");
//                    mVideoView.setTimedTextShown(true);
//
//                }
//            });
//
//            mVideoView.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
//
//                @Override
//                public void onTimedText(String text) {
//                    mSubtitleView.setText(text);
//                }
//
//                @Override
//                public void onTimedTextUpdate(byte[] pixels, int width, int height) {
//
//                }
//            });
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        mPosition = mVideoView.getCurrentPosition();
//        mVideoView.stopPlayback();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        if (mPosition > 0) {
//            mVideoView.seekTo(mPosition);
//            mPosition = 0;
//        }
//        super.onResume();
//        mVideoView.start();
//    }
//
//    public void changeLayout(View view) {
//        mVideoLayout++;
//        if (mVideoLayout == 4) {
//            mVideoLayout = 0;
//        }
//        switch (mVideoLayout) {
//            case 0:
//                mVideoLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
//                view.setBackgroundResource(R.drawable.mediacontroller_sreen_size_100);
//                break;
//            case 1:
//                mVideoLayout = VideoView.VIDEO_LAYOUT_SCALE;
//                view.setBackgroundResource(R.drawable.mediacontroller_screen_fit);
//                break;
//            case 2:
//                mVideoLayout = VideoView.VIDEO_LAYOUT_STRETCH;
//                view.setBackgroundResource(R.drawable.mediacontroller_screen_size);
//                break;
//            case 3:
//                mVideoLayout = VideoView.VIDEO_LAYOUT_ZOOM;
//                view.setBackgroundResource(R.drawable.mediacontroller_sreen_size_crop);
//
//                break;
//        }
//        mVideoView.setVideoLayout(mVideoLayout, 0);
//    }
//    String json = "{\"e\":{\"desc\":\"\",\"provider\":\"play\",\"code\":0},\"data\":{\"id\":376383626,\"stream\":[{\"logo\":\"youku\",\"audio_lang\":\"default\",\"height\":420,\"subtitle_lang\":\"default\",\"segs\":[{\"total_milliseconds_audio\":\"2963772\",\"total_milliseconds_video\":\"2963533\",\"key\":\"80085981f8fd716a261eeb66\",\"size\":\"111863570\"}],\"stream_type\":\"3gphd\",\"width\":560,\"milliseconds_video\":2963533,\"drm_type\":\"default\",\"transfer_mode\":\"http\",\"milliseconds_audio\":2963772,\"stream_fileid\":\"030020010056EDA2F1A19B03D59F586D58A3AF-5944-4FB8-B2C3-20FE5A31FDBC\",\"size\":111863570},{\"logo\":\"youku\",\"audio_lang\":\"default\",\"height\":420,\"subtitle_lang\":\"default\",\"segs\":[{\"total_milliseconds_audio\":\"386798\",\"total_milliseconds_video\":\"386800\",\"key\":\"46e645b44da20c30261eeb66\",\"size\":\"11818016\"},{\"total_milliseconds_audio\":\"410668\",\"total_milliseconds_video\":\"410667\",\"key\":\"409a38f7763ce28124128d3d\",\"size\":\"23969054\"},{\"total_milliseconds_audio\":\"377603\",\"total_milliseconds_video\":\"377600\",\"key\":\"2c33271a59ff9b67282b4990\",\"size\":\"20856848\"},{\"total_milliseconds_audio\":\"414198\",\"total_milliseconds_video\":\"414200\",\"key\":\"259405147bfff2d6282b4990\",\"size\":\"22418296\"},{\"total_milliseconds_audio\":\"414197\",\"total_milliseconds_video\":\"414200\",\"key\":\"8715f91d0484749a282b4990\",\"size\":\"24398069\"},{\"total_milliseconds_audio\":\"391071\",\"total_milliseconds_video\":\"391066\",\"key\":\"fbf3263d82b505d7261eeb66\",\"size\":\"24515174\"},{\"total_milliseconds_audio\":\"299862\",\"total_milliseconds_video\":\"299867\",\"key\":\"9708301a21b2c3b2261eeb66\",\"size\":\"17884548\"},{\"total_milliseconds_audio\":\"269444\",\"total_milliseconds_video\":\"269133\",\"key\":\"d870e24dbe7f9c1e261eeb66\",\"size\":\"9483204\"}],\"stream_type\":\"flvhd\",\"width\":560,\"milliseconds_video\":2963533,\"drm_type\":\"default\",\"transfer_mode\":\"http\",\"milliseconds_audio\":2963841,\"stream_fileid\":\"030002080056EDAA77A19B03D59F586D58A3AF-5944-4FB8-B2C3-20FE5A31FDBC\",\"size\":155343209},{\"logo\":\"youku\",\"audio_lang\":\"default\",\"height\":624,\"subtitle_lang\":\"default\",\"segs\":[{\"total_milliseconds_audio\":\"386798\",\"total_milliseconds_video\":\"386800\",\"key\":\"860ab1cc2805c6e224128d3d\",\"size\":\"22810903\"},{\"total_milliseconds_audio\":\"396922\",\"total_milliseconds_video\":\"396920\",\"key\":\"a949c3fefcae810b24128d3d\",\"size\":\"48196304\"},{\"total_milliseconds_audio\":\"369244\",\"total_milliseconds_video\":\"369240\",\"key\":\"6d5bc05055681428282b4990\",\"size\":\"40895433\"},{\"total_milliseconds_audio\":\"370079\",\"total_milliseconds_video\":\"370080\",\"key\":\"50703aee44432225282b4990\",\"size\":\"39200172\"},{\"total_milliseconds_audio\":\"406396\",\"total_milliseconds_video\":\"406400\",\"key\":\"cef4a79ee628fa8424128d3d\",\"size\":\"49709725\"},{\"total_milliseconds_audio\":\"412479\",\"total_milliseconds_video\":\"412480\",\"key\":\"9f0bb8ceb900e891282b4990\",\"size\":\"52691982\"},{\"total_milliseconds_audio\":\"313841\",\"total_milliseconds_video\":\"313840\",\"key\":\"60335f01171aba65261eeb66\",\"size\":\"38877901\"},{\"total_milliseconds_audio\":\"308082\",\"total_milliseconds_video\":\"307800\",\"key\":\"f5b879274664a34b282b4990\",\"size\":\"22777447\"}],\"stream_type\":\"mp4hd\",\"width\":832,\"milliseconds_video\":2963560,\"drm_type\":\"default\",\"transfer_mode\":\"http\",\"milliseconds_audio\":2963841,\"stream_fileid\":\"030008080056EDACBBA19B03D59F586D58A3AF-5944-4FB8-B2C3-20FE5A31FDBC\",\"size\":315159867},{\"logo\":\"youku\",\"audio_lang\":\"default\",\"height\":768,\"subtitle_lang\":\"default\",\"segs\":[{\"total_milliseconds_audio\":\"195280\",\"total_milliseconds_video\":\"195280\",\"key\":\"c5d0eaf0ad303465282b4990\",\"size\":\"8008264\"},{\"total_milliseconds_audio\":\"191518\",\"total_milliseconds_video\":\"191520\",\"key\":\"3a09f53cf648934924128d3d\",\"size\":\"31268783\"},{\"total_milliseconds_audio\":\"205125\",\"total_milliseconds_video\":\"205120\",\"key\":\"14231c9ebd3407b2261eeb66\",\"size\":\"47843650\"},{\"total_milliseconds_audio\":\"191797\",\"total_milliseconds_video\":\"191800\",\"key\":\"71f8bf196fa15eda24128d3d\",\"size\":\"40833287\"},{\"total_milliseconds_audio\":\"184041\",\"total_milliseconds_video\":\"184040\",\"key\":\"6d41fafdcd4b0044282b4990\",\"size\":\"37068442\"},{\"total_milliseconds_audio\":\"185203\",\"total_milliseconds_video\":\"185200\",\"key\":\"68cfae3f5a00002e24128d3d\",\"size\":\"39703602\"},{\"total_milliseconds_audio\":\"185991\",\"total_milliseconds_video\":\"186000\",\"key\":\"5517f9390412f2db24128d3d\",\"size\":\"31921623\"},{\"total_milliseconds_audio\":\"195977\",\"total_milliseconds_video\":\"195960\",\"key\":\"81721fda5d7061be24128d3d\",\"size\":\"41266440\"},{\"total_milliseconds_audio\":\"184506\",\"total_milliseconds_video\":\"184520\",\"key\":\"032f04d11754f214282b4990\",\"size\":\"43545849\"},{\"total_milliseconds_audio\":\"210001\",\"total_milliseconds_video\":\"210000\",\"key\":\"62a8a724761918e824128d3d\",\"size\":\"45825151\"},{\"total_milliseconds_audio\":\"190404\",\"total_milliseconds_video\":\"190400\",\"key\":\"e8280dab852e192224128d3d\",\"size\":\"41661187\"},{\"total_milliseconds_audio\":\"180001\",\"total_milliseconds_video\":\"180000\",\"key\":\"989687b68b0cac44261eeb66\",\"size\":\"44546765\"},{\"total_milliseconds_audio\":\"207075\",\"total_milliseconds_video\":\"207080\",\"key\":\"e70fbcef2eef4c63261eeb66\",\"size\":\"49366926\"},{\"total_milliseconds_audio\":\"180001\",\"total_milliseconds_video\":\"180000\",\"key\":\"8ac731622081059224128d3d\",\"size\":\"40450360\"},{\"total_milliseconds_audio\":\"146797\",\"total_milliseconds_video\":\"146800\",\"key\":\"21d4970ce411ee70282b4990\",\"size\":\"19167872\"},{\"total_milliseconds_audio\":\"130078\",\"total_milliseconds_video\":\"129840\",\"key\":\"90d9420c435bbb2c282b4990\",\"size\":\"11905964\"}],\"stream_type\":\"mp4hd2\",\"width\":1024,\"milliseconds_video\":2963560,\"drm_type\":\"default\",\"transfer_mode\":\"http\",\"milliseconds_audio\":2963795,\"stream_fileid\":\"030001100056EDAEADA19B03D59F586D58A3AF-5944-4FB8-B2C3-20FE5A31FDBC\",\"size\":574384165}],\"preview\":{\"timespan\":\"6000\",\"thumb\":[\"http://g1.ykimg.com/0521000556EDAEF86C1E076A8501D7E4\",\"http://g3.ykimg.com/0521010556EDAEF86C1E076A8501D7E4\",\"http://g2.ykimg.com/0521020556EDAEF86C1E076A8501D7E4\",\"http://g2.ykimg.com/0521030556EDAEF86C1E076A8501D7E4\",\"http://g4.ykimg.com/0521040556EDAEF86C1E076A8501D7E4\"]},\"dvd\":{\"notsharing\":\"0\"},\"uploader\":{\"certification\":true,\"fan_count\":101470,\"reason\":\"优酷光合计划合作伙伴，优酷游戏达人，原创游戏视频解说\",\"show_brand\":1,\"avatar\":{\"big\":\"http://g1.ykimg.com/0130391F45552112C4A6E603D59F587FAD8FE3-A4CB-FA7A-ADBA-F5737D01A316\",\"small\":\"http://g2.ykimg.com/0130391F45552112C5EA1403D59F581DF1DCB0-B772-C74B-64CA-361562D23EC1\",\"middle\":\"http://g1.ykimg.com/0130391F45552112C4BD0803D59F58438FB563-2D98-44C3-4C83-C23C1DCA76E7\",\"large\":\"http://g4.ykimg.com/0130391F45552112C35A8503D59F58DF115716-DDA8-9F44-FEB5-8E56BA662E0A\"},\"homepage\":\"http://i.youku.com/u/UMjU3MzI2NDMy\"},\"controller\":{\"html5_disable\":false,\"continuous\":false,\"video_capture\":true,\"stream_mode\":2,\"download_disable\":false,\"share_disable\":false,\"circle\":false,\"play_mode\":1},\"security\":{\"encrypt_string\":\"NwXVRgQYILPT2fLG8eJxBoenvhNp1wvJUR8=\",\"ip\":2070959805},\"user\":{\"uid\":\"\"},\"network\":{\"dma_code\":\"4808\",\"area_code\":\"110000\"},\"video\":{\"tags\":[\"原创\",\"第一视角\",\"DOTA\"],\"logo\":\"http://r3.ykimg.com/0541040856EDAEC46A0A4E04E598B1FD\",\"userid\":64331608,\"privacy\":\"anybody\",\"category_id\":99,\"type\":[\"dvd\",\"share\",\"interact\"],\"upload\":\"normal\",\"restrict\":0,\"share\":true,\"title\":\"【小满dota】5杀暴走小火枪！大写的帅！！\",\"username\":\"满楼都素我的人\",\"source\":10020,\"seconds\":\"2963.84\",\"encodeid\":\"XMTUwNTUzNDUwNA==\",\"category_letter_id\":\"g\",\"subcategories\":[{\"id\":\"2221\",\"name\":\"网络游戏\"}]}},\"cost\":0.006000000052154064}";
//}
