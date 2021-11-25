package application.Subscribe;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.*;

class ThreadCheck implements Runnable {
    private int oldRows;
    private int rows;
    private int cells;
    private ArrayList<ArtInfo> list;
    private boolean update;
    private int gap;
    boolean[] OnOff;
    private ArrayList<ArtInfo> art;
    private PopupController firstWindow;

    public ThreadCheck() {  // 생성자
        ExcelIO ex = new ExcelIO();
        ex.fileRead();

        oldRows = art.size();
        rows = 0;
        cells = 7;
        update = false;
        gap=0;

        firstWindow = new PopupController();
    }

    private void read() throws InterruptedException {
        ExcelIO ex = new ExcelIO();
        ex.fileRead();
        rows = art.size();

        gap=rows-oldRows;

        if(gap!=0){
            list = new ArrayList<ArtInfo>();

            OnOff = firstWindow.getWhichMuseum();
            if(OnOff[0] || OnOff[1] || OnOff[2]) {
                for(int i=oldRows; i<rows; i++){
                    switch (art.get(i).getMuseumName()) {
                        case "국립현대미술관":
                            if (OnOff[0]) {
                                list.add(art.get(i));
                                update = true;
                            }
                            break;
                        case "서울시립미술관":
                            if (OnOff[1]) {
                                list.add(art.get(i));
                                update = true;
                            }
                            break;
                        case "경남도립미술관":
                            if (OnOff[2]) {
                                list.add(art.get(i));
                                update = true;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            oldRows=rows;
        }
        else{
            Thread.sleep(1000);
        }
    }

    // 팝업 뜨게하는 함수
    private void secondWindow(String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String title = "New Exhibition!";
                TrayNotification tray = new TrayNotification();

                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndDismiss(Duration.seconds(2));

                // 팝업 이미지 사진 바꾸고 싶으면 여기에 url 링크 넣으면 됩니다! 개인 컴퓨터에 맞게 링크 조정 필요합니당
                Image whatsAppImg = new Image("file:///C:/Users/OhJiwoo/OpensourceJava/Gallery/src/main/resources/application/gallery/image/project_gallery.png");
                tray.setRectangleFill(Paint.valueOf("#d195ff"));  // 색상 변경
                tray.setImage(whatsAppImg);

                tray.setMessage(message);
            }
        });
    }

    // 엑셀 파일 읽어오기
    @Override
    public void run() {
        while(true) {
            try {
                read();    // 텍스트 파일 열어서 개수 체크 -> 개수 늘어나면 늘어난 만큼 읽어옴
                if (update) {   // 개수 늘어나면 update가 true
                    for(int i=0; i <list.size();i++) {
                        secondWindow(list.get(i).getMuseumName() + "에 새로운 전시회가 추가되었습니다!");   // 생성된 전시회 출력 및 팝업 생성
                        Thread.sleep(4000);
                    }
                    update = false;   // 다시 update를 false로 해서 전시회 생성되는지 체크
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 텍스트 파일 읽어오는 함수
    class ExcelIO {
        public ExcelIO() {
            art = new ArrayList<ArtInfo>();
        }

        public void fileRead() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/application/Subscribe/exhibition.txt", Charset.forName("UTF-8")));
                String fileRead;
                String info[][] = new String[100][cells];

                int i = 0;
                while ((fileRead = reader.readLine()) != null) {
                    info[i++] = fileRead.split("-");
                }

                int j = 0;
                String period[][] = new String[i][2];

                while (j < i) {
                    period[j] = info[j][4].split("~");
                    j++;
                }

                for (j = 0; j < i; j++) {
                    art.add(new ArtInfo(j, info[j][1], info[j][2], info[j][3], period[j][0], period[j][1], info[j][5], info[j][6]));
                }

                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}