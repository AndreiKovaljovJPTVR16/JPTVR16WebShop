package servlets;

import SecurityLogic.ROLE;
import SecurityLogic.RoleLogic;
import entity.Buyer;
import entity.Role;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import utils.Encription;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "AdminController", loadOnStartup = 1, urlPatterns = {
    "/showAddMoney",
    "/addMoney",

})
public class AdminController extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private BuyerFacade buyerFacade;
    @EJB private UserRolesFacade userRolesFacade;
    
    @Override
    public void init() throws ServletException {
        List<User> listUsers = userFacade.findAll();
        if(!listUsers.isEmpty()){
            return;
        }
        Buyer buyer = new Buyer("armaggedonhead34@gmail.com", "Andrei", "Kovaljov");
        buyerFacade.create(buyer);
        Encription encription = new Encription();
        String password = encription.getEncriptionPass("admin");
        User user = new User("admin", password, true, buyer);
        userFacade.create(user);
        Role role = new Role(ROLE.ADMINISTRATOR.toString());
        roleFacade.create(role);
        role.setName(ROLE.DIRECTOR.toString());
        roleFacade.create(role);
        role.setName(ROLE.MANAGER.toString());
        roleFacade.create(role);
        role.setName(ROLE.SELLER.toString());
        roleFacade.create(role);
        role.setName(ROLE.CUSTOMER.toString());
        roleFacade.create(role);
        RoleLogic rl = new RoleLogic();
        role = rl.getRole(ROLE.ADMINISTRATOR.toString());
        rl.setRole(role, user);
    }
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
        RoleLogic rl = new RoleLogic();
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
            return;
        }
        Boolean isRole = userRolesFacade.isRole("ADMINISTRATOR", regUser);
        if(!isRole){
            request.setAttribute("info", "Вы должны быть директором!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        switch (path) {
            
            case "/showAddMoney":
                request.getRequestDispatcher("/page/director/addMoney.jsp").forward(request, response);
            break;   
            case "/addMoney":
                String money=request.getParameter("money");
                regUser.getBuyer().setMoney(regUser.getBuyer().getMoney()+new Integer(money));
                userFacade.edit(regUser);
                request.setAttribute("info","Денег у покупателя: " + regUser.getBuyer().getMoney());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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