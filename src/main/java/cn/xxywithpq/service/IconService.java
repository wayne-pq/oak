package cn.xxywithpq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxywithpq.domain.Icon;
import cn.xxywithpq.repository.IconRepository;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;

	@Transactional(readOnly = true)
	public Icon findOne(String id) {
		return iconRepository.findOne(id);
	}
	
	@Transactional
	public Icon save(Icon icon) {
		return iconRepository.save(icon);
	}

}