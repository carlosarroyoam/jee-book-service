package com.carlosarroyoam.book.service.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.book.service.constants.AppMessages;
import com.carlosarroyoam.book.service.dao.AuthorDao;
import com.carlosarroyoam.book.service.dto.AuthorResponse;
import com.carlosarroyoam.book.service.entity.Author;
import com.carlosarroyoam.book.service.mapper.AuthorMapper;

@ApplicationScoped
public class AuthorService {

	@Inject
	private Logger logger;

	@Inject
	private AuthorDao authorDao;

	@Inject
	private AuthorMapper authorMapper;

	public List<AuthorResponse> findAll() {
		List<Author> authors = authorDao.findAll();
		return authorMapper.toDtos(authors);
	}

	public AuthorResponse findById(Long authorId) {
		Author findById = authorDao.findById(authorId).orElseThrow(() -> {
			logger.warning(AppMessages.AUTHOR_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.AUTHOR_NOT_FOUND_WITH_ID, authorId));
		});

		return authorMapper.toDto(findById);
	}

}
