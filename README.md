#BEAT ANDROID API 시작하기
BEAT 안드로이드 어플리케이션 연동을 위한 공개 API입니다.


## API 요구사항
* Minimum Android SDK : 11 (Honeycomb, 3.0)
* Target Android SDK : 19 (KitKat, 4.4)

## 연동 방법
 1. 연동하고자 하는 프로젝트에 ```IBeatApiService.java``` 를 ```import``` 합니다.
  * (```IBeatApiService.java``` 는 본 저장소에서 제공하는 프로젝트의 ```com.beatpacking.beat.services``` 폴더에 있습니다.)
 2. BEAT 어플리케이션과 통신하기 위해 ```ServiceConnection``` 을 열고 ```IBeatApiService``` 객체를 생성합니다. 
  * 생성된 ```IBeatApiService``` 로 BEAT ANDROID API를 이용할 수 있습니다.
  ```java
  IBeatApiService apiService;

  public void onCreate(Bundle savedInstanceState) {
      Intent intent = new Intent();
      intent.setClassName("com.beatpacking.beat", "com.beatpacking.beat.services.PlayHeadService");
      bindService(intent, connection, Context.BIND_AUTO_CREATE);
      
      // 플레이헤드 표시여부 확인
      boolean visible = apiService.isHeadVisible();
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
### ```public class IBeatPlayService```
| Public Methods | Description |
| -------------- | --------------- |
| ```void setHeadVisible(boolean visibility)``` | 플레이헤드 표시/숨김 |
| ```boolean isHeadVisible()``` | 플레이헤드 표시여부 확인 |
| ```void setHeadLocation(int x, int y)``` | 스크린의 X(가로), Y(세로) 좌표로 플레이헤드를 이동시킴 |
| ```int getHeadX()``` | 플레이헤드의 스크린상 X 좌표를 구함 |
| ```int getHeadY()``` | 플레이헤드의 스크린상 Y 좌표를 구함 |
| ```void pause()``` | 음악을 일시정지 시킴 |
| ```void resume()``` | 음악을 재생시킴 |
| ```boolean isAuthenticated()``` | 로그인 되어있는지의 여부를 구함 |
| ```void setMovable(boolean movable)``` | 플레이헤드를 드래그하여 이동 가능/불가능하게 함 |
| ```boolean isMovable()``` | 플레이헤드의 드래그 가능 여부를 구함 |
| ```void setWidthRatio(float widthRatio)``` | 플레이헤드가 화면을 차지하는 비율 설정 |
| ```float getWidthRatio()``` | 플레이헤드가 화면을 차지하는 비율을 구함 |


## 라이센스 

beat-android-api is released under the [MIT License](http://www.opensource.org/licenses/MIT).

