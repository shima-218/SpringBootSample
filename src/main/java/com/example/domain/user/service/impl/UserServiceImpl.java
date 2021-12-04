package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	//ユーザー登録
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		user.setPassword(encoder.encode(user.getPassword()));
		mapper.insertOne(user);
	}

	//ユーザー取得
	@Override
	public List<MUser> getUsers(MUser user) {
		return mapper.findMany(user);
	}

	@Override
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}

	//ユーザー更新
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		mapper.updateOne(userId, encoder.encode(password), userName);
	}

	//ユーザー削除
	@Override
	public void deleteUserOne(String userId) {
		mapper.deleteOne(userId);
	}

	//ログインユーザー取得
	public MUser getLoginUser(String userId) {
		return mapper.findLoginUser(userId);
	}
}
