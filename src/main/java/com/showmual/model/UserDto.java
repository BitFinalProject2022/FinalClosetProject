package com.showmual.model;

import javax.validation.constraints.NotBlank;

import com.showmual.entity.user.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	private String nickname;
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	
    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
    
    @Builder
    public UserDto(Long id, String username, String password, String nickname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
	
}
