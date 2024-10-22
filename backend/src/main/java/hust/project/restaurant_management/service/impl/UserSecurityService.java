package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.service.IUserSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSecurityService implements IUserSecurityService {
    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User id: {}", authentication.getName());
        return Long.parseLong(authentication.getName());
    }
}
