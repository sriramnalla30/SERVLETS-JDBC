import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Create_acc_servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profiles", "root", "ram123");
            stmt = con.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS Employee_Profile (e_name VARCHAR(100), e_pass VARCHAR(100), e_numb VARCHAR(15))");

            String e_name = request.getParameter("e_name");
            String e_pass = request.getParameter("e_pass");
            String e_numb = request.getParameter("e_numb");

            stmt.executeUpdate("INSERT INTO Employee_Profile (e_name, e_pass, e_numb) VALUES ('" + e_name + "', '" + e_pass + "', '" + e_numb + "')");
            
            out.println("<html><body>");
            out.println("<h1>Data inserted</h1>");
            
            rs = stmt.executeQuery("SELECT * FROM Employee_Profile");
            
            out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td> UserName</td>");
                out.println("<td> Password</td>");
                out.println("<td> PhoneNumber</td>");
		out.println("</tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception ex) {
            out.println("Failed to execute: " + ex.getMessage());
        }     }
}
