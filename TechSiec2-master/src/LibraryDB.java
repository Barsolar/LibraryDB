
import java.util.Scanner;

public class LibraryDB {

    public static void main(String[] args) {

        Scanner klawiatura = new Scanner(System.in);
        BookDao bookDao = BookDao.getInstance();

        System.out.println("Choose an option:");
        System.out.println("1 - Add a new book");
        System.out.println("2 - Display a book");
        System.out.println("3 - Remove a book");
        System.out.println("4 - Modify a book");
        System.out.println("5 - Display all books");

        Scanner keyboard = new Scanner(System.in);

        int mode = Integer.parseInt(keyboard.next());

            if (mode == 1) {
                Book book = new Book();
                System.out.println("Input title");
                book.setTitle(keyboard.next());
                System.out.println("Input Author");
                book.setAuthor(keyboard.next());
                System.out.println("Input year");
                book.setYear(Integer.parseInt(keyboard.next()));
                System.out.println("Input ISBN");
                book.setIsbn(keyboard.next());
                bookDao.create(book);
            } else if (mode == 2) {
                System.out.printf("Type id to display\n");
                int id = klawiatura.nextInt();
                Book book = bookDao.read(id);
                System.out.print(book.toString());
            } else if (mode == 3) {
                System.out.printf("Type id to modify\n");
                int id = klawiatura.nextInt();
                Book book = new Book();
                System.out.println("Input new title");
                book.setTitle(keyboard.next());
                System.out.println("Input new Author");
                book.setAuthor(keyboard.next());
                System.out.println("Input new year");
                book.setYear(Integer.parseInt(keyboard.next()));
                System.out.println("Input new ISBN");
                book.setIsbn(keyboard.next());
                bookDao.update(book);
            } else if (mode == 4) {
                System.out.printf("Type id to remove \n");
                int id = klawiatura.nextInt();
                try {
                    bookDao.delete(id);
                } catch (NullPointerException e) {
                    System.out.printf("No such record");
                }
            }else if(mode==5){
                for(Book b: bookDao.getAllBooks()){
                    System.out.println(b.toString());
                }
            }
            else {
                System.out.printf("Invalid input");
            }
    }
}
