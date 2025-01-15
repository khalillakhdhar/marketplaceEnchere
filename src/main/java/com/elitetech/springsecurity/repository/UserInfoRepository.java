package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elitetech.springsecurity.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

	   @EntityGraph(attributePaths = {"roles"})
	    Optional<UserInfo> findById(Long id);

	    @EntityGraph(attributePaths = {"roles"})
	    Optional<UserInfo> findByEmail(String email);
	    @EntityGraph(attributePaths = {"roles"})

	    Optional<UserInfo> findByName(String name);

}
