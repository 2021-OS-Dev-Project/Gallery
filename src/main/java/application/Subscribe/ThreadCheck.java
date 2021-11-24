package application.Subscribe;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

class ThreadCheck implements Runnable {
    private int oldRows;
    private int rows;
    private int cells;
    private ArrayList<ArtInfo> list;
    private boolean update;
    private int gap;
    private FXMLLoader fxmlLoader;
    private Parent root2;
    private Stage stage2;
    private ArrayList<ArtInfo> art;

    public ThreadCheck() throws IOException {  // 생성자
        ExcelIO ex = new ExcelIO();
        ex.fileRead();

        oldRows = art.size();
        rows = 0;
        cells = 6;
        update = false;
        gap=0;
    }

    private void read() throws IOException, InterruptedException {
        ExcelIO ex = new ExcelIO();
        ex.fileRead();
        rows = art.size();

        gap=rows-oldRows;

        if(gap!=0){
            list = new ArrayList<ArtInfo>();
            for(int i=oldRows; i<rows; i++){
                list.add(art.get(i));
            }
            update=true;
            oldRows=rows;
        }
        else{
            Thread.sleep(1000);
        }
    }

    private void secondWindow(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    fxmlLoader = new FXMLLoader(getClass().getResource("Second_Window.fxml"));
                    root2 = (Parent) fxmlLoader.load();
                    stage2 = new Stage();
                    stage2.initStyle(StageStyle.DECORATED);
                    stage2.setTitle("Second Window");
                    stage2.setScene(new Scene(root2));
                    stage2.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(int i=0; i<list.size(); i++){
                    System.out.print(list.get(i).getMuseumName() + "  ");
                    System.out.print(list.get(i).getArtName() + "  ");
                    System.out.println(list.get(i).getArtist());
                }
                gap=0;
            }
        });
    }

    // 엑셀 파일 읽어오기
    @Override
    public void run() {
        while(true) {
            try {
                read();  // 엑셀 파일 열어서 개수 체크 -> 개수 늘어나면 늘어난 만큼 읽어옴
                if (update) {  // 개수 늘어나면 update가 true
                    secondWindow();  // 생성된 전시회 출력 및 second window 생성
                    update=false;  // 다시 update를 false로 해서 생성되는지 체크
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ExcelIO {
        public ExcelIO() {
            art = new ArrayList<ArtInfo>();
        }

        public void fileRead() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/application/Subscribe/exhibition.txt", Charset.forName("UTF-8")));
                String fileRead;
                String info[][] = new String[100][6];

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
                    art.add(new ArtInfo(j, info[j][1], info[j][2], info[j][3], period[j][0], period[j][1], info[j][4], info[j][5]));
                }

                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}