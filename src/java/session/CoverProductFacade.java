/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.Cover;
import entity.CoverProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class CoverProductFacade extends AbstractFacade<CoverProduct> {

    @PersistenceContext(unitName = "Ptvr16WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoverProductFacade() {
        super(CoverProduct.class);
    }
    
    public Cover findCover(Product product) {
        try {
            CoverProduct coverProduct = (CoverProduct) em.createQuery("SELECT cb FROM CoverProduct cb WHERE cb.product = :product")
                    .setParameter("product", product)
                    .getSingleResult();
            return coverProduct.getCover();
        } catch (Exception e) {
            return null;
        }
    }
    
}