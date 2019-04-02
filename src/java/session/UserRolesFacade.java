package session;

import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @PersistenceContext(unitName = "Ptvr16WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public Boolean isRole(String roleName, User user) {
        try {
            UserRoles ur = (UserRoles) em.createQuery("SELECT ur FROM UserRoles ur WHERE ur.user = :user AND ur.role.name=:roleName")
                    .setParameter("roleName", roleName)
                    .setParameter("user", user)
                    .getSingleResult();
            if(ur != null){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<UserRoles> findUserRoles(User user) {
        try {
            return em.createQuery("SELECT ur FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}