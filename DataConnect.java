import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataConnect {
    private Connection con;
    public DataConnect() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/bookstore?useSSL=false&allowPublicKeyRetrieval=true";
			this.con = DriverManager.getConnection(url,"root","kid1412365471");
		}
		catch(Exception e) {
			System.out.println("Error "+e);
		}
    }
    public List<Book> getBookList() {
        try {
            Statement stmt = con.createStatement();
			String sql = "SELECT * FROM book";
			ResultSet rs= stmt.executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();
            List<Book> bookList = new ArrayList<Book>();
			while(rs.next()) {
                Book book = new Book(Integer.parseInt(rs.getObject(1).toString()),rs.getObject(2).toString(),rs.getObject(3).toString(),rs.getObject(4).toString(),rs.getObject(5).toString(),Integer.parseInt(rs.getObject(6).toString()),Integer.parseInt(rs.getObject(7).toString()));
                bookList.add(book);
                System.out.println(Integer.parseInt(""+rs.getObject(1).toString())+rs.getObject(2).toString()+rs.getObject(3).toString()+rs.getObject(4).toString()+rs.getObject(5).toString()+Integer.parseInt(rs.getObject(6).toString())+Integer.parseInt(rs.getObject(7).toString()));
			}
			rs.close();
			stmt.close();
            return bookList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean createBook(Book book) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO book (name, author, category, url, price, amount) VALUES ('" + book.get_name() + "', '" + book.get_author() + "', '" + book.get_category() + "', '" + book.get_url() + "', " + book.get_price() + ", " + book.get_amount() + ")";
            int result = stmt.executeUpdate(sql);
            stmt.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Book getBook(int id) {
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM book WHERE id=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("category"), rs.getString("url"), rs.getInt("price"), rs.getInt("amount"));
                rs.close();
                stmt.close();
                return book;
            } else {
                rs.close();
                stmt.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean updateBook(Book book) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE book SET name='" + book.get_name() + "', author='" + book.get_author() + "', category='" + book.get_category() + "', url='" + book.get_url() + "', price=" + book.get_price() + ", amount=" + book.get_amount() + " WHERE id=" + book.get_id();
            int result = stmt.executeUpdate(sql);
            stmt.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteBook(int id) {
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM book WHERE id=" + id;
            int rows = stmt.executeUpdate(sql);
            stmt.close();
            if(rows == 0) {
                return false; // Không tìm thấy cuốn sách cần xóa
            } else {
                return true; // Đã xóa thành công cuốn sách
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xóa không thành công
        }
    }
    public boolean updateBookSoldAmount(int id, int n) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE book SET amount = amount - " + n + " WHERE id = " + id;
            int rows = stmt.executeUpdate(sql);
            stmt.close();
            if(rows == 0) {
                return false; // Không tìm thấy cuốn sách cần cập nhật
            } else {
                return true; // Đã cập nhật thành công số lượng sách
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Cập nhật không thành công
        }
    }        
}
