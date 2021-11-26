package application.gallery;

import application.model.Exhibitions;

import application.model.Exhibitions;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;


public class searchController implements Initializable {
    @FXML
    private Button barbutton;

    @FXML
    private Label MenuName;

    @FXML
    private ScrollPane List;

    @FXML
    private JFXDrawer bar;

    @FXML
    private VBox searchList;

    List<Exhibitions> exhibitionsList;

    private String location;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(barbutton!=null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if(bar.isOpened()){
                    bar.close();
                }

                else{
                    try {
                        VBox Box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        bar.setSidePane(Box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });


        searchController.ExcelIO ex = new searchController.ExcelIO();
        ex.fileRead();
        exhibitionsList = new ArrayList<>(ex.getGalleryList());

        try{
            for (Exhibitions exhibitions : exhibitionsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EachSearch.fxml"));

                HBox hBox = fxmlLoader.load();
                EachGalleryController eachGalleryController = fxmlLoader.getController();
                if(eachGalleryController != null){
                    eachGalleryController.setData(exhibitions);
                    if(searchList != null)
                        searchList.getChildren().add(hBox);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    class ExcelIO {

        private ArrayList<ArtInfo> art;
        int[][] split;

        public ExcelIO() {
            art=new ArrayList<ArtInfo>();
        }

        // 엑셀 파일 읽어오기
        public void fileRead(){
            try {
                BufferedReader reader= new BufferedReader(new FileReader("src/main/java/application/gallery/exhibition.txt", Charset.forName("UTF-8")));
                String fileRead;
                String info[][]=new String[10][6];

                int i=0;
                while ((fileRead = reader.readLine()) != null) {
                    info[i++]=fileRead.split("-");
                }

                int j=0;
                String period[][]=new String[i][2];

                while(j<i){
                    period[j]=info[j][4].split("~");
                    j++;
                }

                for(j=0;j<i;j++) {
                    art.add(new ArtInfo(j,info[j][2],info[j][3],period[j][0],period[j][1],info[j][4],info[j][5]));
                }

                Sorting();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        // 전시회를 전시기간이 빠른 기준으로 정렬하여 Painting[][]에 저장
        public void Sorting() {
            split=new int[art.size()][4];

            for (int i = 0; i < art.size(); i++) {
                String[] str2 = art.get(i).getStartPeriod().split(" ");
                split[i][0]=art.get(i).getArtNum(); //식별자 변경
                split[i][1] = Integer.parseInt(str2[0]);
                split[i][2] = Integer.parseInt(str2[1]);
                split[i][3] = Integer.parseInt(str2[2]);
            }

            Arrays.sort(split,new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    // TODO Auto-generated method stub
                    if(o1[1]==o2[1]) {
                        if(o1[2]==o2[2]) {
                            return o1[3]-o2[3];
                        }
                        return o1[2]-o2[2];
                    }else {
                        return o1[1]-o2[1];
                    }
                }
            });

        }


        public java.util.List<Exhibitions> getGalleryList() {
            List<Exhibitions> ls = new ArrayList<>();

            for(ArtInfo tmp:art) {
                Exhibitions exhibitions = new Exhibitions();
                exhibitions.setName(tmp.getArtName());
                exhibitions.setCover("src/main/java/application/gallery/img/쉬어가다.jpg");
                exhibitions.setExplanation(tmp.getArtsit() + "\n" +tmp.getStartPeriod() + "~" + tmp.getEndPeriod()+ "\n" +tmp.getPrice());
                System.out.println(tmp.PrintArt());
                ls.add(exhibitions);
            }
            return ls;

        }
    }
}
