package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.TwitterRepository;
import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.model.TwitterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin.Z on 2018/2/28.
 */
@Service
public class TwitterService {
    @Autowired
    private TwitterRepository twitterRepository;

    public void save(Twitter twitter) {
        twitterRepository.save(twitter);
    }

    public List<Twitter> findAll() {
        return twitterRepository.findAll();
    }

    public Twitter findById(Long id) {
        return twitterRepository.findById(id);
    }

    public void delete(Twitter twitter) {
        twitterRepository.delete(twitter);
    }

    public long count() {
        return twitterRepository.count();
    }

    public Page<Twitter> findTwitterCriteria(int page, int size, final TwitterCriteria twitterCriteria) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Twitter> twitterPage = twitterRepository.findAll(new Specification<Twitter>() {
            @Override
            public Predicate toPredicate(Root<Twitter> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (twitterCriteria.getFromDate() != null) {
                    //list.add(criteriaBuilder.equal(root.get("createTime").as(Date.class), twitterCriteria.getFromDate()));
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), twitterCriteria.getFromDate());
                }
                if (twitterCriteria.getToDate() != null) {
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), twitterCriteria.getToDate()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return twitterPage;
    }
}
