#BEAT ANDROID API 시작하기
## API 요구사항
* Minimum Android SDK : 11 (Honeycomb, 3.0)
* Target Android SDK : 19 (KitKat, 4.4)

## 연결 방법
```java
IBeatApiService apiService;

public void onCreate(Bundle savedInstanceState) {
    Intent intent = new Intent();
    intent.setClassName("com.beatpacking.beat", "com.beatpacking.beat.services.PlayHeadService");
    bindService(intent, connection, Context.BIND_AUTO_CREATE);
}

private ServiceConnection connection = new ServiceConnection() {
    public void onServiceConnected(ComponentName name, IBinder service) 
        apiService = IBeatApiService.Stub.asInterface(service);
        messageHandler.sendMessageDelayed(Message.obtain(messageHandler, MSG_CONNECTED), 1000);
    }

    public void onServiceDisconnected(ComponentName name) {
        messageHandler.sendMessageDelayed(Message.obtain(messageHandler, MSG_DISCONNECTED), 500);
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
};
```

## 제공 기능

### public class IBeatPlayService

| Public Methods | Description |
| -------------- | --------------- |
| void setHeadVisible(boolean visibility) | 플레이헤드의 화면 등장 여부 설정 |
| boolean isHeadVisible() | 플레이헤드의 화면 등장 여부 체크 |
| void setHeadLocation(int x, int y) | 스크린의 X(가로), Y(세로) 좌표로 플레이헤드를 이동시킴 |
| int getHeadX() | 플레이헤드의 스크린상 X 좌표를 구함 |
| int getHeadY() | 플레이헤드의 스크린상 Y 좌표를 구함 |
| void pause() | 음악을 일시정지 시킴 |
| void resume() | 음악을 재생시킴 |
| boolean isAuthenticated() | 로그인 되어있는지의 여부를 구함 |



