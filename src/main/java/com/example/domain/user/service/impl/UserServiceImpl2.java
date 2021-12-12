package com.example.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	//ユーザー登録
	@Transactional
	@Override
	public void signup(MUser user) {
		if(repository.existsById(user.getUserId())) {
			throw new DataAccessException("ユーザーが既に存在しています") {};
		}
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	};

	//ユーザー取得
	@Override
	public List<MUser> getUsers(MUser user){
		//検索条件
		ExampleMatcher matcher = ExampleMatcher
				.matching()//and条件
				.withStringMatcher(StringMatcher.CONTAINING)//LIKE句
				.withIgnoreCase();//大文字小文字の両方
		return repository.findAll(Example.of(user,matcher));
	};

	@Override
	public MUser getUserOne(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	};

	//ユーザー更新
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		repository.updateUser(userId,encoder.encode(password),userName);
	};

	//ユーザー削除
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		repository.deleteById(userId);
	};

	//ログインユーザー取得
	@Override
	public MUser getLoginUser(String userId) {
		return repository.findLoginUser(userId);
	};


}
