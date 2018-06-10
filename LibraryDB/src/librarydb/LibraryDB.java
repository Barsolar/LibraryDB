package librarydb;

import java.util.Scanner;

public class LibraryDB {

    public static void main(String[] args) {
        
        System.out.println("Choose an option:");
        System.out.println("1 - Add new book");
        System.out.println("2 - Display all books");
        System.out.println("3 - Remove book");
        System.out.println("4 - Modify book");
        
        Scanner keyboard = new Scanner(System.in);
        
        int mode = Integer.parseInt(keyboard.next());
        
        if(mode==1){
            Book book = new Book();
            System.out.println("Input title");
            book.setTitle(keyboard.next());
            System.out.println("Input Author");
            book.setAuthor(keyboard.next());
            System.out.println("Input year");
            book.setYear(Integer.parseInt(keyboard.next()));
            System.out.println("Input ISBN");
            book.setIsbn(Integer.parseInt(keyboard.next()));
            BookDAO dao = BookDAO.getInstance();
            dao.insertBook(book);
        }else if(mode==2){
            BookDAO dao = BookDAO.getInstance();
            dao.selectAllBooks();
        }else if(mode==3){
            BookDAO dao = BookDAO.getInstance();
        }else if(mode==4){
            BookDAO dao = BookDAO.getInstance();
        }
    } 
}
