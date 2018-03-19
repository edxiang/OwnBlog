package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Service
public interface MediaRepository extends JpaRepository<Media,Long> {
    List<Media> findAllByAlbumId(Long albumId);

}
