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
    "/showPageForReturnProduct",
    "/giveProduct",
    "/returnProduct",
    
    
    
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
                request.setAttribute("info", "showListBuyers");
                request.getRequestDispatcher("/page/seller/showListBuyers.jsp").forward(request, response);
                break;
                
            case "/showPageForGiveProduct":
                List<Product> listProducts = productFacade.findAll();
                listBuyers = buyerFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/page/seller/showPageForGiveProduct.jsp").forward(request, response);
                break;
            case "/giveProduct":
                String productId = request.getParameter("productId");
                String buyerId = request.getParameter("buyerId");
                String count = request.getParameter("count");
                Product product = productFacade.find(new Long(productId));
                Buyer buyer = buyerFacade.find(new Long(buyerId));
                if((product.getCount()- new Integer(count))>= 0){
                    if((buyer.getMoney()- (product.getPrice() * new Integer(count)))>=0 ){
                        buyer.setMoney(buyer.getMoney()- new Integer(count)* product.getPrice());
                    }else{
                        request.setAttribute("info", "Не хватает денег!");
                    }
                    product.setCount(product.getCount()-1);
                    productFacade.edit(product);
                    History history = new History(product, buyer, c.getTime());
                    historyFacade.create(history);
                    request.setAttribute("info", "Продукт " + product.getName() + " продан");
                }else{
                    request.setAttribute("info", "Все продукты проданы");
                }       
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/showPageForReturnBook":
                List<History> listHistories = historyFacade.findGivenBooks();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher(PagePathLoader.getPagePath("showReturnBook")).forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("returnHistoryId");
                History history = null;
                if(historyId != null){
                    history = historyFacade.find(new Long(historyId));
                }
                if(history == null){
                    request.setAttribute("info", "Такого товара не выдавалось");
                    request.getRequestDispatcher(PagePathLoader.getPagePath("managerIndex")).forward(request, response);
                    return;
                }       
                product = history.getProduct();
                if(product.getQuantity()>product.getCount()){
                    product.setCount(product.getCount()+1);
                    productFacade.edit(product);
                    history.setDateEnd(c.getTime());
                    historyFacade.edit(history);
                    request.setAttribute("info", "Товар "+product.getName()+" возвращен");
                }else{
                    request.setAttribute("info", "Все товары уже возвращены");
                }       
                request.getRequestDispatcher(PagePathLoader.getPagePath("managerIndex")).forward(request, response);
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