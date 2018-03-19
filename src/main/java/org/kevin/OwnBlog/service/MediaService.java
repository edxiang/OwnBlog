package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.MediaRepository;
import org.kevin.OwnBlog.model.Media;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kevin.Z on 2018/1/26.
 */
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    public void save(Media media){
        mediaRepository.save(media);
    }

    public List<Media> findByAlbumId(Long id){
        return mediaRepository.findAllByAlbumId(id);
    }
}
