package application;

import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.Initializable;

public class SearchController implements Initializable {

	int[][] split;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void Sorting() {
		split=new int[Object.art.size()][4]; 

		for (int i = 0; i < Object.art.size(); i++) {
            String[] str2 = Object.art.get(i).getStartPeriod().split("-");
            split[i][0]=Object.art.get(i).getArtNum(); //식별자 변경
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
	
	void SortPrint() {
		for(int i=0;i<Object.art.size();i++) {
        	for(int j=0;j<Object.art.size();j++)
        		if(Object.art.get(j).getArtNum()==split[i][0])
        			System.out.println(Object.art.get(j).PrintArt());
        }
	}
	

	void SearchArtName() {
			Scanner scanner = new Scanner(System.in);
			
	        System.out.println("보고싶은 전시회명을 입력하세요.");
	        String name = scanner.nextLine();
	            
	        for(ArtInfo tmp:Object.art) {
	            if(tmp.getArtName().contains(name)) {
	            	System.out.println(tmp.PrintArt());
	            }
	        }
	        scanner.close();
	    }
}
