package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

       @Override
    public User getUserByCarModelAndCarSeries(String model, int series) {
        User user = new User();
        try {
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User" +
                    " where car.model= :model and car.series = :series");
            query.setParameter("model", model);
            query.setParameter("series", series);
            query.setMaxResults(1);
            user = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Данные параметры не подходят");
        }
        return user;
    }

}
