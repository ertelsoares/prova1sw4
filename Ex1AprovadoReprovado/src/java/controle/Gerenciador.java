/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controle;

import dados.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ertel
 */
@WebServlet(name = "Gerenciador", urlPatterns = {"/aprovadosereprovados"})
public class Gerenciador extends HttpServlet {

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
       HttpSession sessao = request.getSession();
        ArrayList<Aluno> listaAprovados = (ArrayList<Aluno>) sessao.getAttribute("listaAprovados");
        ArrayList<Aluno> listaReprovados = (ArrayList<Aluno>) sessao.getAttribute("listaReprovados");
        String acao = request.getParameter("acao");
        if (listaAprovados == null) {
            listaAprovados = new ArrayList<Aluno>();
            sessao.setAttribute("listaAprovados", listaAprovados);
        }
        if(listaReprovados == null){
           listaReprovados = new ArrayList<Aluno>();
            sessao.setAttribute("listaReprovados", listaReprovados); 
        }
        if ("limpar".equals(acao)) {
            listaAprovados.clear();
            listaReprovados.clear();
            sessao.invalidate(); 
            response.sendRedirect("index.jsp");
            return;
          }
        try {
            String nome = request.getParameter("nome");
            String reqnota1 = request.getParameter("nota1");
            String reqnota2 = request.getParameter("nota2");
            double nota1 = reqnota1.length() <= 4 && reqnota1.contains(".") || reqnota1.contains(",")? Double.parseDouble(reqnota1.replace(",",".")) : -1;  
            double nota2 = reqnota2.length() <= 4 && reqnota1.contains(".") || reqnota2.contains(",") ? Double.parseDouble(reqnota2.replace(",",".")) : -1;
            Aluno aluno = new Aluno(nome,nota1,nota2);
           
           if(nota1 == -1 || nota2 == -1){
               throw new Exception("Formato numero de casa decimais errado formato experado 0.0 ou 0,0");
           }else{
             if(aluno.getMedia() >= 7.0){
             listaAprovados.add(aluno);
             }else{
                listaReprovados.add(aluno);  
             }
           }
           

        }catch (Exception ex) {
            sessao.setAttribute("msgErro", ex.getMessage());
        }
        response.sendRedirect("index.jsp");
        
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