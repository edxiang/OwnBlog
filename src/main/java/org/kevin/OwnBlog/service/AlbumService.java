package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.AlbumRepository;
import org.kevin.OwnBlog.model.Album;
import org.kevin.OwnBlog.model.AlbumCriteria;
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
import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public void save(Album album){
        albumRepository.save(album);
    }

    public void delete(Album album){
        albumRepository.delete(album);
    }

    public List<Album> findAll(){
        return albumRepository.findAll();
    }

    public Album findById(Long id){
        return albumRepository.findById(id);
    }

    public Page<Album> findAlbumNoCriteria(int page, int size){
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        return albumRepository.findAll(pageable);
    }

    public Page<Album> findAlbumCriteria(int page, int size, final AlbumCriteria albumCriteria){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Album> albumPage = albumRepository.findAll(new Specification<Album>() {
            @Override
            public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(albumCriteria.getTag()!=null && !albumCriteria.getTag().equals("")){
                    list.add(criteriaBuilder.equal(root.get("tag").as(String.class), albumCriteria.getTag()));
                }
                if(albumCriteria.getTitle()!=null && !albumCriteria.getTitle().equals("")){
                    list.add(criteriaBuilder.equal(root.get("title").as(String.class), albumCriteria.getTitle()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return albumPage;
    }

}
