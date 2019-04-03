package servlets;

import entity.Cover;
import entity.Product;
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
import session.CoverFacade;
import session.CoverProductFacade;
import session.UserRolesFacade;
import utils.Encription;
import utils.PagePathLoader;

/**
 *
 * @author jvm
 */
@WebServlet(name = "ManagerController", urlPatterns = {
    "/showAddNewProduct",
    "/addNewProduct",
    "/showAddNewBuyer",
    "/addNewBuyer",
    "/showUploadFile",
    
    
    
    
})
public class ManagerController extends HttpServlet {
    @EJB private ProductFacade productFacade;
    @EJB private BuyerFacade buyerFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private CoverFacade coverFacade;
    @EJB private CoverProductFacade coverProductFacade;
    
    
    
    
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
        Boolean isRole = userRolesFacade.isRole("MANAGER", regUser);
        if(!isRole){
            request.setAttribute("info", "Вы должны быть менеджером!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(null != path) switch (path) {
            case "/showAddNewProduct":
                List<Cover> listCovers = coverFacade.findAll();
                request.setAttribute("listCovers", listCovers);
                request.getRequestDispatcher(PagePathLoader.getPagePath("showAddNewProduct")).forward(request, response);
                break;
            case "/addNewProduct":
                String name = request.getParameter("name");
                String count = request.getParameter("count");
                String price = request.getParameter("price");
                Product product = new Product(new Integer(price), name, new Integer(count));
                productFacade.create(product);
                request.setAttribute("info", "Продукт \""+product.getName()+"\"добавлен");
                request.getRequestDispatcher("/showAddNewProduct").forward(request, response);
                break;
            case "/showAddNewBuyer":
                request.getRequestDispatcher(PagePathLoader.getPagePath("showAddNewBuyer")).forward(request, response);
                break;
            case "/showUploadFile":
                request.getRequestDispatcher(PagePathLoader.getPagePath("showUploadFile")).forward(request, response);
                break;
            default:   
                request.setAttribute("info", "Нет такой странички");
                request.getRequestDispatcher(PagePathLoader.getPagePath("managerIndex")).forward(request, response);         
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