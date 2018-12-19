package net.crawl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.crawl.quartz.QuartzManager;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(urlPatterns= "/QuartzServlet",loadOnStartup = 1)
public class QuartzServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public QuartzServlet() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out =response.getWriter();
        out.append("Served at: ").append(request.getContextPath());

        boolean isr =  QuartzManager.isStarted();
        out.append("Quartz staus: ").append(isr +".").append("<br/>");

        if(isr ==false){
            QuartzManager.start();
            out.append("Restart Quartz, then staus: ").append(QuartzManager.isStarted() +".").append("<br/>");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        QuartzManager.start();
    }

    @Override
    public void destroy() {
        QuartzManager.shutdown();
        super.destroy();
    }

}
