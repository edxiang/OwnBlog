package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.DiaryRepository;
import org.kevin.OwnBlog.model.Diary;
import org.kevin.OwnBlog.model.DiaryCriteria;
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
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    public void save(Diary diary) {
        diaryRepository.save(diary);
    }

    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    public Diary findById(Long id) {
        return diaryRepository.findById(id);
    }

    public void delete(Diary diary) {
        diaryRepository.delete(diary);
    }

    public long count() {
        return diaryRepository.count();
    }

    public Page<Diary> findDiaryCriteria(int page, int size, final DiaryCriteria diaryCriteria) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Diary> diaryPage = diaryRepository.findAll(new Specification<Diary>() {
            @Override
            public Predicate toPredicate(Root<Diary> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (diaryCriteria.getBeginTimeOfDay() != null) {
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), diaryCriteria.getBeginTimeOfDay());
                }
                if (diaryCriteria.getBeginTimeOfDay() != null) {
                    criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), diaryCriteria.getEndTimeOfDay());
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return diaryPage;
    }
}
