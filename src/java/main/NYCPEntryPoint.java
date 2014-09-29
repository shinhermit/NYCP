/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import entity.Prisoner;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.IncarcerateService;

/**
 *
 * @author josuah
 */
@WebServlet(name = "NYCPEntryPoint",
        urlPatterns = {"/NYCPEntryPoint", "/incarcerate", "/incarcerate/new"})
public class NYCPEntryPoint extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        javax.naming.Context jndi_context = null;
        service.remote.IncarcerateRemote incarcerateService = null;
        
        try
        {
            jndi_context = new javax.naming.InitialContext();
            incarcerateService =
                (service.remote.IncarcerateRemote) jndi_context.lookup("ejb/IncarcerateService");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(NYCPEntryPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assert(incarcerateService != null);
        
        String pathInfo = request.getServletPath();
        if(pathInfo.equals("/incarcerate"))
        {
            String nextJSP = "/incarcerate.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
        
        else if(pathInfo.equals("/incarcerate/new"))
        {
            // Call session bean
            String fileNumber = request.getParameter("fileNumber");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String placeOfBirth = request.getParameter("placeOfBirth");
            String dateOfBirthString = request.getParameter("dateOfBirth");
            String dateOfIncarcerationString = request.getParameter("dateOfIncarceration");
            
            Date dateOfBirth = null, dateOfIncarceration = null;
            try
            {
                dateOfBirth = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(dateOfBirthString);
                dateOfIncarceration = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(dateOfIncarcerationString);
            }
            catch (ParseException ex)
            {
                Logger.getLogger(NYCPEntryPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Prisoner incarcerated = incarcerateService.incarcerate(fileNumber,
                    name, surname, dateOfBirth, placeOfBirth, dateOfIncarceration);
            
            // forward to JSP
            String nextJSP = "/incarcerate_success.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            request.setAttribute("incarcerated", incarcerated);
            dispatcher.forward(request, response);
        }
        
        if (jndi_context != null)
        {
            try
            {
                jndi_context.close();
            }
            catch (NamingException ex)
            {
                Logger.getLogger(NYCPEntryPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void showPathComponents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //
        // Getting servlet request URL
        //
        String url = request.getRequestURL().toString();
 
        //
        // Getting servlet request query string.
        //
        String queryString = request.getQueryString();
 
        //
        // Getting request information without the hostname.
        //
        String uri = request.getRequestURI();
 
        //
        // Below we extract information about the request object path
        // information.
        //
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int portNumber = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
 
        response.setContentType("text/html");
        java.io.PrintWriter pw = response.getWriter();
        pw.print("Url: " + url + "<br/>");
        pw.print("Uri: " + uri + "<br/>");
        pw.print("Scheme: " + scheme + "<br/>");
        pw.print("Server Name: " + serverName + "<br/>");
        pw.print("Port: " + portNumber + "<br/>");
        pw.print("Context Path: " + contextPath + "<br/>");
        pw.print("Servlet Path: " + servletPath + "<br/>");
        pw.print("Path Info: " + pathInfo + "<br/>");
        pw.print("Query string: " + queryString);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
