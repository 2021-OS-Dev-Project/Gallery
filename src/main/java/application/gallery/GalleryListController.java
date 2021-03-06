package application.gallery;

import application.model.Exhibitions;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.jfoenix.controls.JFXDrawer;
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

    private JFXButton Click;
    private ArrayList<ArtInfo> art;
    private ArrayList<ExhibitionInfo> exhibition;
    static String n1, n2, n3;
    ArrayList<VBox> vbox = new ArrayList<VBox>();
    static int countI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (barbutton != null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {
                        VBox box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        bar.setSidePane(box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });


        ExcelIO ex = new ExcelIO();
        ex.fileRead();
        exhibitionsList = new ArrayList<>(ex.getGalleryList());
        location = "???????????????";
        if (LocationSet != null)
            LocationSet.setText("'" + location + "'" + " ");
        try {
            for (Exhibitions exhibitions : exhibitionsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EachGallery.fxml"));

                VBox vBox = fxmlLoader.load();
                vbox.add(vBox);
                EachGalleryController eachGalleryController = fxmlLoader.getController();
                eachGalleryController.setData(exhibitions);
                if (galleryList != null)
                    galleryList.getChildren().add(vBox);

                if (galleryList != null) {
                    for(int i=0;i<vbox.size();i++){
                        int finalI = i;
                        vbox.get(i).setOnMouseClicked((e) -> {
                            countI=finalI;
                            System.out.println(countI);
                            try {
                                Parent select = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Select.fxml")));
                                Scene scene = new Scene(select);
                                Stage stage = (Stage) vbox.get(countI).getScene().getWindow();
                                stage.setScene(scene);
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ExcelIO {
        int[][] split;
        private HashSet<String> tmp;
        private BidiMap<String, Integer> set;

        public ExcelIO() {
            art = new ArrayList<ArtInfo>();
            exhibition = new ArrayList<ExhibitionInfo>();
            set = new TreeBidiMap<>();
            tmp = new HashSet<String>();
        }

        // ?????? ?????? ????????????
        public void fileRead() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/application/gallery/exhibition.txt", Charset.forName("UTF-8")));
                String fileRead;
                String info[][] = new String[10][7];

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

                for (int s = 0; s < i; s++) {
                    tmp.add(info[s][1]);  // ???????????? ????????? ??????
                }
                List<String> sortedList = new ArrayList<>(tmp);
                Collections.sort(sortedList);

                int k = 0;
                for (String name : sortedList) {
                    set.put(name, k);
                    k++;
                }

                for (j = 0; j < i; j++) {
                    art.add(new ArtInfo(j, info[j][2], info[j][3], period[j][0], period[j][1], info[j][5], info[j][6], set.get(info[j][1])));
                }
                for (BidiMap.Entry<String, Integer> entry : set.entrySet()) {   // ????????? key??? ??????
                    exhibition.add(new ExhibitionInfo(entry.getValue(), entry.getKey()));
                }

                Sorting();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ???????????? ??????????????? ?????? ???????????? ???????????? Painting[][]??? ??????
        public void Sorting() {
            split = new int[art.size()][4];

            for (int i = 0; i < art.size(); i++) {
                String[] str2 = art.get(i).getStartPeriod().split(" ");
                split[i][0] = art.get(i).getArtNum(); //????????? ??????
                split[i][1] = Integer.parseInt(str2[0]);
                split[i][2] = Integer.parseInt(str2[1]);
                split[i][3] = Integer.parseInt(str2[2]);
            }

            Arrays.sort(split, new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    // TODO Auto-generated method stub
                    if (o1[1] == o2[1]) {
                        if (o1[2] == o2[2]) {
                            return o1[3] - o2[3];
                        }
                        return o1[2] - o2[2];
                    } else {
                        return o1[1] - o2[1];
                    }
                }
            });

        }


        public List<Exhibitions> getGalleryList() {
            List<Exhibitions> ls = new ArrayList<>();

            for (ArtInfo tmp : art) {
                Exhibitions exhibitions = new Exhibitions();

                exhibitions.setCover("src/main/java/application/gallery/img/???????????? ??????.jpg");
                exhibitions.setExplanation(tmp.getArtist() + "\n" + tmp.getStartPeriod() + "~" + tmp.getEndPeriod() + "\n" + tmp.getPrice());
                exhibitions.setName(tmp.getArtName());
                System.out.println(tmp.PrintArt());
                ls.add(exhibitions);
            }
            return ls;
        }
    }
}
