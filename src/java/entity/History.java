package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class History  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @OneToOne
    private Product product;
    @OneToOne
    private Buyer buyer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBegin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    public History() {
    }

    public History(Product product, Buyer buyer, Date dateBegin) {
        this.product = product;
        this.buyer = buyer;
        this.dateBegin = dateBegin;
    }
    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product prduct) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        if(dateEnd == null){
          return "History{" + "product=" + product.getName() + ", buyer=" + buyer.getName()+" "+buyer.getSurname() + ", dateBegin=" + dateBegin.toString()+ '}';  
        }else{
          return "History{" + "product=" + product.getName() + ", buyer=" + buyer.getName()+" "+buyer.getSurname() + ", dateBegin=" + dateBegin.toString()+ ", dateEnd=" + dateEnd.toString()+ '}';
        }
            
    }
    
    
}