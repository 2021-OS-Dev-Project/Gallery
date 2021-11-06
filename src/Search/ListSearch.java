package Search;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


class ExcelIO {
    private int rows;
    private int cells;
    private String Painting[][];
    private String list[][];

    public ExcelIO() {
        rows=0;
        cells=0;
    }

    public String[][] getPainting(){
        return Painting;
    }
    public int getRows() {
        return rows;
    }
    public int getCells() {
        return cells;
    }

    // 엑셀 파일 읽어오기
    public void excelRead(){
        try {
            FileInputStream file = new FileInputStream("src/Search/team_project(db information).xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int rowindex = 0;
            int columnindex = 0;
            // 시트 수 (첫번째에만 존재하므로 0을 준다)
            // 만약 각 시트를 읽기위해서는 FOR 문을 한번 더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();  // 행의 수
            list = new String[rows][9];

            for (rowindex = 0; rowindex < rows; rowindex++) {
                XSSFRow row = sheet.getRow(rowindex);  // 행을읽는다
                if (row != null) {   // 셀의 수
                    cells = row.getPhysicalNumberOfCells();
                    for (columnindex = 0; columnindex <= cells; columnindex++) {
                        XSSFCell cell = row.getCell(columnindex);    // 셀 값을 읽는다
                        String value = "";
                        if (cell == null) {   //셀이 빈 값일경우를 위한 널체크
                            continue;
                        } else {  //타입별로 내용 읽기
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }
                        list[rowindex][columnindex] = value;
                    }
                }
            }
            Sorting();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 전시회를 전시기간이 빠른 기준으로 정렬하여 Painting[][]에 저장
    public void Sorting() {
        Painting = new String[rows][cells];

        int[][] split = new int[rows][4];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            if (!list[i][5].contains("~")) {
                Painting[index++] = list[i];
                continue;
            }
            String[] str = list[i][5].split("~");
            String[] str2 = str[0].split("-");
            float fl = Float.parseFloat(list[i][0]);
            split[i][0] = (int) fl;  // 식별번호
            split[i][1] = Integer.parseInt(str2[0]);
            split[i][2] = Integer.parseInt(str2[1]);
            split[i][3] = Integer.parseInt(str2[2]);
        }

        // 정렬하는 함수
        Arrays.sort(split, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
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

        for(int i=index;i<rows;i++){
            Painting[i] = list[split[i][0]];
        }
    }

    // 전시회 출력하는 메소드
    public void print() {
        for(int i=0;i<rows;i++){
            for(int j=0;j<cells;j++){
                System.out.print(Painting[i][j] + "\t\t");
            }
            System.out.println();
        }
    }
}

public class ListSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExcelIO ex = new ExcelIO();

        ex.excelRead();
        ex.print();  // 전시회 출력

        while(true) {
            System.out.println("보고싶은 전시회명을 입력하세요.");
            String name = scanner.nextLine();
            if(name.equals("그만")){
                break;
            }
            for (int i = 1; i < ex.getRows(); i++) {
                String[][] Painting = ex.getPainting();
                if (Painting[i][3].contains(name)) {
                    for (int j = 0; j < ex.getCells(); j++) {
                        System.out.print(Painting[i][j] + "\t");
                    }
                    System.out.println();
                }
            }
        }
        scanner.close();
    }
}