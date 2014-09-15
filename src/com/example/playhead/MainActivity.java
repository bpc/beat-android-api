package com.example.playhead;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.beatpacking.beat.services.IBeatApiService;

public class MainActivity extends Activity {
    private static final String TAG = "beat.sample.MainActivity";

    private static final int MSG_CONNECTED = 1;
    private static final int MSG_DISCONNECTED = 2;
    private static final int MSG_NOT_AUTHENTICATED = 3;
    private static final int MSG_TRY_CONNECT = 4;

    IBeatApiService apiService;
    TestDialog dialog;
    boolean bound = false;

    TextView btnConnect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "?");

        btnConnect = (TextView) findViewById(R.id.btn_connect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.beatpacking.beat");
                    startActivity(intent);
                    bindService(5000);
                } catch (NullPointerException e){
                    try {
                        startActivity(
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=com.beatpacking.beat")
                                )
                        );
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://play.google.com/store/apps/details?id=com.beatpacking.beat")
                                )
                        );
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }

    private void bindService(int ms) {
        bindService();
        messageHandler.sendMessageDelayed(Message.obtain(messageHandler, MSG_NOT_AUTHENTICATED), ms);
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setClassName("com.beatpacking.beat", "com.beatpacking.beat.services.PlayHeadService");

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        if (dialog != null)
            dialog.initView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            apiService = IBeatApiService.Stub.asInterface(service);
            messageHandler.sendMessageDelayed(Message.obtain(messageHandler, MSG_CONNECTED), 1000);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messageHandler.sendMessageDelayed(Message.obtain(messageHandler, MSG_DISCONNECTED), 500);
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    };

    public Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CONNECTED:
                    if (dialog == null) {
                        try {
//                            if (apiService.isAuthenticated()) {
                                dialog = new TestDialog(MainActivity.this);
                                dialog.show();
                            /*} else {
                                unbindService(connection);
                                apiService = null;
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                            unbindService(connection);
                            apiService = null;
                        }
                    }
                    break;
                case MSG_DISCONNECTED:
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog = null;
                    }
                    break;
                case MSG_NOT_AUTHENTICATED:
                    if (apiService == null)
                        bindService(5000);
                    break;
                case MSG_TRY_CONNECT:
                    if (apiService == null)
                        bindService(5000);
                    break;
            }
        }
    };

    class TestDialog extends Dialog {

        TextView refresh;
        TextView userStatus;
        TextView hideStatus;
        CheckBox hideState;
        TextView dragStatus;
        CheckBox dragState;
        EditText editWidth;
        TextView btnDecide;
        TextView headStatus;
        CheckBox toggleState;
        EditText editX;
        EditText editY;
        TextView btnMove;
        TextView btnPlay;
        TextView btnPause;

        public TestDialog(Context context) {
            super(context, android.R.style.Theme_Translucent_NoTitleBar);
        }

        @Override
        public void onBackPressed() {
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
            lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            lpWindow.dimAmount = 0.8f;
            getWindow().setAttributes(lpWindow);

            setContentView(R.layout.test_dialog_layout);

            refresh = (TextView) findViewById(R.id.refresh);
            userStatus = (TextView) findViewById(R.id.user_status);
            headStatus = (TextView) findViewById(R.id.playhead_status);
            toggleState = (CheckBox) findViewById(R.id.playhead_toggle);
            hideStatus = (TextView) findViewById(R.id.playhead_hide_status);
            hideState = (CheckBox) findViewById(R.id.playhead_hide_toggle);
            dragStatus = (TextView) findViewById(R.id.playhead_drag_status);
            dragState = (CheckBox) findViewById(R.id.playhead_drag_toggle);
            editWidth = (EditText) findViewById(R.id.edit_width);
            btnDecide = (TextView) findViewById(R.id.btn_decide);
            editX = (EditText) findViewById(R.id.edit_x);
            editY = (EditText) findViewById(R.id.edit_y);
            btnMove = (TextView) findViewById(R.id.btn_move);
            btnPlay = (TextView) findViewById(R.id.btn_play);
            btnPause = (TextView) findViewById(R.id.btn_pause);

            initView();
            initEvent();
        }


        public void initView() {
            try {
                userStatus.setText(apiService.isAuthenticated() ? "AUTHENTICATED" : "NOT AUTHENTICATED");
                headStatus.setText(apiService.isHeadVisible() ? "VISIBLE" : "INVISIBLE");
                editWidth.setText(Integer.toString((int)(100 * apiService.getWidthRatio())));
                hideStatus.setText(apiService.isHideable() ? "AVAILABLE" : "NOT AVAILABLE");
                hideState.setChecked(apiService.isHideable() ? true : false);
                dragStatus.setText(apiService.isMovable() ? "AVAILABLE" : "NOT AVAILABLE");
                dragState.setChecked(apiService.isMovable() ? true : false);
                toggleState.setChecked(apiService.isHeadVisible() ? true : false);

                editX.setText("" + Integer.toString(apiService.getHeadX()));
                editY.setText("" + Integer.toString(apiService.getHeadY()));

            } catch (RemoteException ex) {
                Log.e(TAG, "initView()", ex);
            }
        }


        private void initEvent() {
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initView();
                }
            });

            hideState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try{
                        apiService.setHideable(isChecked);
                    } catch (RemoteException e){
                        e.printStackTrace();
                    }

                    messageHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                hideStatus.setText(apiService.isHideable() ? "AVAILABLE" : "NOT AVAILABLE");
                            } catch (RemoteException e){
                                e.printStackTrace();
                            }
                        }
                    }, 500);
                }
            });

            dragState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try{
                        apiService.setMovable(isChecked);
                    } catch (RemoteException e){
                        e.printStackTrace();
                    }

                    messageHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                          try{
                              dragStatus.setText(apiService.isMovable() ? "AVAILABLE" : "NOT AVAILABLE");
                          } catch (RemoteException e){
                              e.printStackTrace();
                          }
                        }
                    }, 500);
                }
            });

            btnDecide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float width = Float.parseFloat(editWidth.getText().toString())/100;
                    try {
                        apiService.setWidthRatio(width);

                        Toast.makeText(MainActivity.this, "width : " + width, Toast.LENGTH_SHORT);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

            toggleState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        apiService.setHeadVisible(isChecked);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    messageHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                headStatus.setText(apiService.isHeadVisible() ? "VISIBLE" : "INVISIBLE");
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500);
                }
            });

            btnMove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = Integer.parseInt(editX.getText().toString());
                    int y = Integer.parseInt(editY.getText().toString());
                    try {
                        apiService.setHeadLocation(x, y);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        apiService.resume();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        apiService.pause();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
