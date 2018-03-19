package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
public interface AlbumRepository extends JpaRepository<Album,Long>, JpaSpecificationExecutor<Album> {
    Album findAlbumByTitle(String title);
    Album findByTagLike(String tag);
    Album findById(Long id);
}
