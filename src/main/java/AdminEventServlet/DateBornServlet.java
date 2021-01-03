package AdminEventServlet;

import Tasks.NewJobDateBorn;
import org.quartz.SchedulerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DateBornServlet", urlPatterns = "/DateBornStartStop")
public class DateBornServlet extends HttpServlet {
    private static final NewJobDateBorn newJobDateBorn = new NewJobDateBorn();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = Boolean.parseBoolean(request.getParameter("radio"));
        if (flag){
            try {
                newJobDateBorn.creatNewJob();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }else{
            try {
                newJobDateBorn.removeJob();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("adminview/job.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
