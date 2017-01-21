package ine.wmd.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ine.wmd.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByLogin(String login);

}