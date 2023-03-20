/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.*;
import helper.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DAO;

/**
 *
 * @author User
 */
@WebServlet(name = "ManagerCartServlet", urlPatterns = {"/managercart"})
public class ManagerCartServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           String index = request.getParameter("index");
         String indexx = (String) request.getAttribute("indexx");
         String oid =  request.getParameter("oid");
         
        if (index == null) {
            index = "1";
        }
        if(indexx==null){
            indexx=index;
        }
        int indexpage = Integer.parseInt(indexx);
        DAO dao= new DAO();
        List<OrderDetail> list= dao.pagingProductCart(indexpage,Integer.parseInt(oid) );
     
         int endPage = Helper.getEndp(dao.getTotalProductCart(Integer.parseInt(oid)));
         if(!list.isEmpty()){
              if(indexpage==endPage){
                 request.setAttribute("begin", dao.getTotalProductCart(Integer.parseInt(oid)));
             }else{
             int begin= list.size()*indexpage;
             request.setAttribute("begin", begin);}
             request.setAttribute("total", dao.getTotalProductCart(Integer.parseInt(oid)));
         }
        request.setAttribute("listC", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", indexpage);
        request.getRequestDispatcher("managerCart.jsp").forward(request, response);
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
