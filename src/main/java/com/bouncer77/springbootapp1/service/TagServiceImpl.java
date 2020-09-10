package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Tag;
import com.bouncer77.springbootapp1.form.TagForm;
import com.bouncer77.springbootapp1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> readAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag read(long id) {
        Optional<Tag> authorRepOptional = tagRepository.findById(id);
        return authorRepOptional.orElse(null);
    }

    @Override
    public boolean update(long id, TagForm tagForm) {

        Optional<Tag> tagRepOptional = tagRepository.findById(id);
        if (tagRepOptional.isPresent()) {
            Tag tag = tagRepOptional.get();
            tag.setName(tagForm.getName());
            tag.setDescription(tagForm.getDescription());
            tagRepository.save(tag);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {

        Optional<Tag> tagRepOptional = tagRepository.findById(id);
        if (tagRepOptional.isPresent()) {
            tagRepository.delete(tagRepOptional.get());
            return true;
        }

        return false;
    }
}
