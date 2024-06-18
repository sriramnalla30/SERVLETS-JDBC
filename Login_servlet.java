import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Login_servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profiles", "root", "ram123");
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Employee_Profile");
            
            String e_name = request.getParameter("e_name");
            String e_pass = request.getParameter("e_pass");
            
            boolean loggedIn = false;
            
            while (rs.next()) {
                if (e_name.equalsIgnoreCase(rs.getString(1)) && e_pass.equals(rs.getString(2))) {
                    out.println("<h1> You Are Logged In </h1>");
                    loggedIn = true;
                    break;
                }
            }
            
            if (!loggedIn) {
                out.println("<h1> No Account Yet </h1>");
            }
        } catch (Exception ex) {
            out.println("failed: " + ex.getMessage());
        }        out.println("</body></html>");
    }
}
