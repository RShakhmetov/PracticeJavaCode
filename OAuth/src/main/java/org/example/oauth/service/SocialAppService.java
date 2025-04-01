package org.example.oauth.service;

import lombok.RequiredArgsConstructor;
import org.example.oauth.model.CustomOAuth2User;
import org.example.oauth.model.UserEntity;
import org.example.oauth.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialAppService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(SocialAppService.class);

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String name = oAuth2User.getAttribute("login");
        Optional<UserEntity> optUser = Optional.ofNullable(userRepository.findByName(name));
        if (optUser.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setName(name);
            userRepository.save(newUser);
            log.info("New user created: {}", name);
        } else {
            log.info("User {} authenticated", name);
        }
        return new CustomOAuth2User(oAuth2User);
    }
}
