package librarydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookDAO {
    
        private static final String dbURL = "jdbc:derby://localhost:1527/Library;create=true;user=student;password=1234";
        private static final String tableName = "books";
        private Connection conn = null;
        private Statement stmt = null;
        
        private static BookDAO instance;
        
        public BookDAO(){
            conn = createConnection();
        }
        
        public static BookDAO getInstance(){
            if(instance==null){
                instance = new BookDAO();
            }
            return instance;
        }
    
        public void insertBook(Book book){
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" +
                    book.getId() + ",'" + book.getTitle() + "','" + book.getAuthor() + "','" +
                    book.getYear() + "','" + book.getIsbn() + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public void selectAllBooks(){
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
        public ArrayList<Book> getAllBooks(){
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ArrayList<Book> books = new ArrayList<Book>();

            while(results.next())
            {
                Book book = new Book();            
                book.setId(results.getInt(1));
                book.setTitle(results.getString(2));
                book.setAuthor(results.getString(3));
                book.setYear(results.getInt(4));
                book.setIsbn(results.getInt(5));
                books.add(book);
            }
            results.close();
            stmt.close();
            return books;
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return null;
    }
    
    
        
    public Book getBook(int id){
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName + " where id = '" + id +"'");
            Book book = new Book();            
                book.setId(results.getInt(1));
                book.setTitle(results.getString(2));
                book.setAuthor(results.getString(3));
                book.setYear(results.getInt(4));
                book.setIsbn(results.getInt(5));
            results.close();
            stmt.close();
            return book;
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            return null;
        }
    }
    
    private Connection createConnection(){
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
            return conn;
        }
        catch (Exception except)
        {
            except.printStackTrace();
            return null;
        }
    }
    
    public void shutdown(){
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }
    }
  
}
