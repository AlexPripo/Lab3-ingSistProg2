package ro.ulbs.ingprog.lab3ingsistprog2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer counterGet = (Integer)
                request.getSession().getAttribute("CounterGetName");
        if (counterGet == null) {
            counterGet = 0;
        }

        String increment = request.getParameter("inc");
        if (increment != null && !increment.isEmpty()) {
            counterGet += Integer.parseInt(increment);
            if(counterGet < 0) { counterGet = 0; }
        }

        String decrement = request.getParameter("dec");
        if (decrement != null && !decrement.isEmpty()) {
            counterGet -= Integer.parseInt(decrement);
            if(counterGet < 0) { counterGet = 0; }
        }

        request.getSession().setAttribute("CounterGetName", counterGet);
        if(counterGet == 0) {
            request.getSession().setAttribute("WarningGet", true);
        } else {
            request.getSession().setAttribute("WarningGet", false);
        }
        status(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer counterPost = (Integer)
                request.getSession().getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
        }

        String increment = request.getParameter("inc");
        if (increment != null && !increment.isEmpty()) {
            counterPost += Integer.parseInt(increment);
            if(counterPost < 0) { counterPost = 0; }
        }

        String decrement = request.getParameter("dec");
        if (decrement != null && !decrement.isEmpty()) {
            counterPost -= Integer.parseInt(decrement);
            if(counterPost < 0) { counterPost = 0; }
        }

        request.getSession().setAttribute("CounterPostName", counterPost);
        if(counterPost == 0) {
            request.getSession().setAttribute("WarningPost", true);
        } else {
            request.getSession().setAttribute("WarningPost", false);
        }
        status(request, response);
    }

    private void status(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h2>Welcome to Counter Servlet</h2>");

        Integer counterGet = (Integer)
                request.getSession().getAttribute("CounterGetName");
        if (counterGet == null) {
            counterGet = 0;
            request.getSession().setAttribute("WarningGet", true);
        }
        printWriter.println("<br> Counter for GET: " + counterGet);

        Integer counterPost = (Integer)
                request.getSession().getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
            request.getSession().setAttribute("WarningPost", true);
        }
        printWriter.println("<br> Counter for POST: " + counterPost + "<br>");

        Boolean warningGet = (Boolean)request.getSession().getAttribute("WarningGet");
        if(warningGet) {
            printWriter.println("<span style=\"color:red\">WARNING GET</span><br>");
        }

        Boolean warningPost = (Boolean)request.getSession().getAttribute("WarningPost");
        if(warningPost) {
            printWriter.println("<span style=\"color:red\">WARNING POST</span><br>");
        }
    }
}