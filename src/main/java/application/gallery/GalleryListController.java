package application.gallery;

import application.model.Exhibitions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import com.jfoenix.controls.JFXDrawer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class GalleryListController implements Initializable {
    @FXML
    private Label LocationSet;
    @FXML
    private HBox galleryList;
    @FXML
    private Label MenuName;
    @FXML
    private JFXDrawer bar;
    @FXML
    private Button barbutton;
    @FXML
    private VBox content;

    private String location;
    List<Exhibitions> exhibitionsList;
    EachGalleryController eachGalleryController;
    private ArrayList<ArtInfo> art;
    private ArrayList<ExhibitionInfo> exhibition;
    static String n1, n2, n3;
    static boolean flag;

    public String getN1(){
        return n1;
    }
    public String getN2(){
        return n2;
    }
    public String getN3(){
        return n3;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (barbutton != null) {
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {
                        VBox Box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        bar.setSidePane(Box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        ExcelIO ex = new ExcelIO();
        ex.fileRead();
        exhibitionsList = new ArrayList<>(ex.getGalleryList());
        location = "서울특별시";
        if (LocationSet != null)
            LocationSet.setText("'" + location + "'" + " ");
        try {
            for (Exhibitions exhibitions : exhibitionsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EachGallery.fxml"));

                VBox vBox = fxmlLoader.load();
                eachGalleryController = fxmlLoader.getController();
                eachGalleryController.setData(exhibitions);
                if (galleryList != null)
                    galleryList.getChildren().add(vBox);
            }

            // Thread 돌리면서 텍스트 파일 체크
            Thread thread = new Thread(new SelectController());
            thread.setDaemon(true);  // 데몬 Thread로 설정(main Thread가 종료되면 자동으로 종료)
            thread.start();  // Thread 가동

            n1="ddddd";
            n2="";
            n3="";
            flag=false;

            if (galleryList != null) {
                for (Node child : galleryList.getChildren()) {
                    VBox vb = (VBox) child;
                    // when vbox is clicked focus on it
                    vb.setOnMouseClicked((e) -> {
                        for (int i = 0; i < art.size(); i++) {
                            if (art.get(i).getArtName().equals(vb.getChildren().get(1).toString().split("'")[1])) {
                                n1 = "전시회명 : " + art.get(i).getArtName();
                                n2 = "전시 미술관 : " + exhibition.get(art.get(i).getExhibitionNum()).getExhibitionName();
                                n3 = "작가 : " + art.get(i).getArtist() + "\n" +
                                        "전시기간 : " + art.get(i).getStartPeriod() + "~" + art.get(i).getEndPeriod() + "\n" +
                                        "가격 : " + art.get(i).getPrice();

                                try {
                                    Parent select = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Select.fxml")));
                                    Scene scene = new Scene(select);
                                    Stage stage = (Stage) vb.getScene().getWindow();
                                    stage.setScene(scene);
                                } catch (IOException exc) {
                                    exc.printStackTrace();
                                }
                                flag=true;
                                break;
                            }
                        }
                    });
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    class ExcelIO {
        int[][] split;
        private HashSet<String> tmp;
        private BidiMap<String, Integer> set;

        public ExcelIO() {
            art=new ArrayList<ArtInfo>();
            exhibition = new ArrayList<ExhibitionInfo>();
            set = new TreeBidiMap<>();
            tmp = new HashSet<String>();
        }

        // 엑셀 파일 읽어오기
        public void fileRead(){
            try {
                BufferedReader reader= new BufferedReader(new FileReader("src/main/java/application/gallery/exhibition.txt", Charset.forName("UTF-8")));
                String fileRead;
                String info[][]=new String[10][7];

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

                for(int s=0; s<i; s++){
                    tmp.add(info[s][1]);  // 미술관의 중복을 제거
                }
                List<String> sortedList = new ArrayList<>(tmp);
                Collections.sort(sortedList);

                int k=0;
                for(String name : sortedList){
                    set.put(name, k);
                    k++;
                }

                for(j=0;j<i;j++) {
                    art.add(new ArtInfo(j, info[j][2], info[j][3], period[j][0], period[j][1], info[j][5], info[j][6], set.get(info[j][1])));
                }
                for(BidiMap.Entry<String, Integer> entry : set.entrySet()){   // 저장된 key값 확인
                    exhibition.add(new ExhibitionInfo(entry.getValue(), entry.getKey()));
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

        public List<Exhibitions> getGalleryList() {
            List<Exhibitions> ls = new ArrayList<>();

            for(ArtInfo tmp:art) {
                Exhibitions exhibitions = new Exhibitions();

                exhibitions.setCover("src/main/java/application/gallery/img/연속적인 언어.jpg");
                exhibitions.setExplanation(tmp.getArtist() + "\n" +tmp.getStartPeriod() + "~" + tmp.getEndPeriod()+ "\n" +tmp.getPrice());
                exhibitions.setName(tmp.getArtName());
//                System.out.println(tmp.PrintArt());
                ls.add(exhibitions);
            }
            return ls;

        }
    }
}
