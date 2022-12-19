package com.showmual.entity.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;




public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	// username으로 id(Long) 찾기
	@Query(value = "select id from users_tbl where username = :username", nativeQuery = true)
	String findIdByUsername(String username);
	
	// username으로 nickname 찾기
    @Query(value = "select nickname from users_tbl where username = :username", nativeQuery = true)
    String findNicknameByUsername(String username);

    // username으로 email 찾기
    @Query(value = "select email from users_tbl where username = :username", nativeQuery = true)
    String findEmailByUsername(String username);
    
    // username으로 password 찾기
    @Query(value = "select password from users_tbl where username = :username", nativeQuery = true)
    String findPasswordByUsername(String username);
	
	// 동일한 username 있는지 확인하기
	Long countByUsername(String username);
	
	// 동일한 닉네임 있는지 확인하기
	Long countByNickname(String nickname);
	
	// Email로 ID 찾기
	@Query(value = "select username from users_tbl where email = :email", nativeQuery = true)
	String findUsernameByEmail(String email);
	
	// ID로 비밀번호 변경하기
	@Query(value = "update users_tbl set password = :password where id = :id", nativeQuery = true)
	@Transactional  // update, delete 쿼리 사용할땐 Transactional, Modifying 어노테이션 필요하다.
	@Modifying
	void updatePasswordById(String password, Long id);
	
	// ID로 회원탈퇴하기
//	@Query(value = "delete from users_tbl where id = :id and password = :password", nativeQuery = true)
//	@Transactional  // update, delete 쿼리 사용할땐 Transactional, Modifying 어노테이션 필요하다.
//	@Modifying
//	int deleteByIdAndPassword(UserDto userDto);
	
	// ID로 회원탈퇴하기
	@Query(value = "delete from users_tbl where id = :id", nativeQuery = true)
	@Transactional  // update, delete 쿼리 사용할땐 Transactional, Modifying 어노테이션 필요하다.
	@Modifying
	void deleteById(Long id);
}
