package servlets;

import entity.Product;
import entity.History;
import entity.Buyer;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ProductFacade;
import session.HistoryFacade;
import session.BuyerFacade;
import session.UserRolesFacade;
import utils.Encription;
import utils.PagePathLoader;


@WebServlet(name = "SellerController", urlPatterns = {
    "/showListBuyers",
    "/showPageForGiveProduct",
    "/giveProduct",
    
    
    
})
public class SellerController extends HttpServlet {
    @EJB private ProductFacade productFacade;
    @EJB private BuyerFacade buyerFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private UserRolesFacade userRolesFacade;
    
    
    
    
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
        request.setCharacterEncoding("UTF-8");
        Encription encription = new Encription();
        Calendar c = new GregorianCalendar();
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        Boolean isRole = userRolesFacade.isRole("SELLER", regUser);
        if(!isRole){
            request.setAttribute("info", "Вы должны быть продавцом!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(null != path) switch (path) {
            case "/showListBuyers":
                List<Buyer> listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.setAttribute("info", "showListBuyers,привет из сервлета!");
                request.getRequestDispatcher(PagePathLoader.getPagePath("showListBuyers")).forward(request, response);
                break;
                
            case "/showPageForGiveProduct":
                List<Product> listProducts = productFacade.findAll();
                listBuyers = buyerFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher(PagePathLoader.getPagePath("showPageForGiveProduct")).forward(request, response);
                break;
            case "/giveProduct":
                String productId = request.getParameter("productId");
                String buyerId = request.getParameter("buyerId");
                if(productId == null || productId.isEmpty() || buyerId == null || buyerId.isEmpty()){
                    request.setAttribute("info", "Вы не выбрали товар или покупателя");
                    request.getRequestDispatcher("/showPageForGiveProduct").forward(request, response);
                    break;
                }
                Product product = productFacade.find(new Long(productId));
                Buyer reader = buyerFacade.find(new Long(buyerId));
                if(product.getCount()>0){
                    product.setCount(product.getCount()-1);
                    productFacade.edit(product);
                    History history = new History(product, reader, c.getTime());
                    historyFacade.create(history);
                    request.setAttribute("info", "Товар " + product.getName() + " продан");
                }else{
                    request.setAttribute("info", "Все товары проданы");
                }       
                request.getRequestDispatcher(PagePathLoader.getPagePath("sellerIndex")).forward(request, response);
                break;
        }
            
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