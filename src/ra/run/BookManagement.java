package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    private static Book[] arrBook = new Book[100];
    private static Scanner scanner = new Scanner(System.in);
    private static int currentIndex = 0;
    public static void main(String[] args) {
        do {
            System.out.println("========MENU=======\n" +
                    "1.Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách \n" +
                    "2.Hiển thị tất cả sách đang lưu trữ\n" +
                    "3.Sắp xếp sách theo lợi nhuận tăng dần\t\n" +
                    "4.Xóa sách theo mã sách\n" +
                    "5.Tìm kiếm tương đối sách theo tên sách hoặc mô tả\n" +
                    "6.Thay đổi thông tin sách theo mã sách\n" +
                    "7.thoát");
            System.out.println(" Nhập yêu cầu thực thi");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                   //Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách
                    System.out.print("Nhập số lượng sách cần thêm mới: ");
                    int numBooksToAdd = Integer.parseInt(scanner.nextLine());
                    if(numBooksToAdd + currentIndex < 100){
                        for (int i = currentIndex; i < numBooksToAdd; i++) {
                            arrBook[currentIndex] = new Book();
                            arrBook[currentIndex].inputData(scanner);
                            currentIndex++;
                        }
                    }
                    break;

                case 2:
                    //Hiển thị thông tin tất cả sách trong thư viện
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                    }
                    break;
                case 3:
                    //Sắp xếp sách theo lợi nhuận tăng dần
                    sortInterest();
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                    }
                    break;
                case 4:
                    //Xóa sách theo mã sách
                    deleteBook();
                    currentIndex--;
                    break;
                case 5:
                  //tìm kiếm
                    search();
                    break;
                case 6:
                    // Đổi thông tin
                    updateBook();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("yêu cầu nhập từ 1--- 7");
            }
        }while (true);
    }
    public static void sortInterest(){
        for (int i = 0; i < currentIndex -1; i++) {
            for (int j = i+1; j < currentIndex; j++) {
                if(arrBook[i].getInterest() > arrBook[j].getInterest()){
                    Book temp = arrBook[j];
                    arrBook[j] = arrBook[i];
                    arrBook[i] = temp;
                }

            }

        }

    }

    public static void deleteBook(){
        System.out.println("Nhập mã sách cần xoá");
        int deleteId = Integer.parseInt(scanner.nextLine());
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if(arrBook[i].getBookId() == deleteId){
                index = i;
                break;
            }
        }
        if(index == -1 ){
            System.out.println("Mã sách cần xoá không tìm thấy");
        }else {
            for (int i = 0; i < currentIndex; i++) {
                for (int j = deleteId; j < currentIndex - 1; j++) {
                    arrBook[j] = arrBook[j + 1];
                }
            }

        }
    }


    public static void search(){
        System.out.println("Nhập từ cần tìm kiếm");
        String searchName = scanner.nextLine();
        for (int i = 0; i < currentIndex; i++) {
            if(arrBook[i].getBookName().toLowerCase().contains(searchName.toLowerCase()) || arrBook[i].getDescriptions().toLowerCase().contains(searchName.toLowerCase())){
                arrBook[i].displayData();

            }
        }

    }

    public static void updateBook(){
        System.out.println("Nhập mã sách cần sửa");
        int updateId = Integer.parseInt(scanner.nextLine());
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if(arrBook[i].getBookId() == updateId){
                index = i;
                break;
            }
        }
        if(index == -1){
            System.out.println("Mã sách cần sửa không tìm thấy");
        }else {

            System.out.println("Đổi tên sách");
            String newName = scanner.nextLine();
            arrBook[index].setBookName(newName);

            System.out.println("Đổi tên tác giả");
            String newAuthor = scanner.nextLine();
            arrBook[index].setAuthor(newAuthor);

            System.out.println("Đổi lại mô tả");
            String newDescriptions = scanner.nextLine();
            arrBook[index].setDescriptions(newDescriptions);

            System.out.println("Đổi lại giá nhập");
            double newImportPrice = Double.parseDouble(scanner.nextLine());
            arrBook[index].setImportPrice(newImportPrice);

            System.out.println("Đổi lại giá bán");
            double newExportPrice = Double.parseDouble(scanner.nextLine());
            arrBook[index].setExportPrice(newExportPrice);

            System.out.println("Đổi lại trạng thái");
            boolean newBookStatus = Boolean.parseBoolean(scanner.nextLine());
            arrBook[index].setBookStatus(newBookStatus);

            System.out.println(" đổi thành công");
        }


    }

}
