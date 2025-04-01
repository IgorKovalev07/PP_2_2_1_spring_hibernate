package hiber.dao;

import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public User getUserHasCar(String model, int series) {
        String hql = "SELECT user FROM User user WHERE user.car.model = :model AND user.car.series = :series";
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        typedQuery.setParameter("model", model);
        typedQuery.setParameter("series", series);
        return typedQuery.getSingleResult();
    }

}
