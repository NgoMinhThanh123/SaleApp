package com.nmt.saleapp.service.Impl;

import com.nmt.saleapp.model.*;
import com.nmt.saleapp.repository.ProductRepository;
import com.nmt.saleapp.repository.UserRepository;
import com.nmt.saleapp.service.ReceiptService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            SaleOrder order = new SaleOrder();
            order.setUserId(this.userRepo.getUserByUsername(authentication.getName()));
            order.setCreatedDate(new Date());

            entityManager.persist(order);

            for (Cart c : carts.values()) {
                OrderDetail d = new OrderDetail();
                d.setNum(c.getQuantity());
                d.setUnitPrice(c.getUnitPrice());
                d.setOrderId(order);
                Optional<Product> productOptional = this.productRepo.findById(Integer.parseInt(c.getId().toString()));
                Product product = productOptional.get();
                d.setProductId(product);
                entityManager.persist(d);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
