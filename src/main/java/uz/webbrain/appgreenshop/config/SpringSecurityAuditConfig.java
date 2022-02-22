package uz.webbrain.appgreenshop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.webbrain.appgreenshop.entity.User;

import java.util.Optional;


public class SpringSecurityAuditConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            User principial = (User) authentication.getPrincipal();
            return Optional.of(principial.getId());
        }
        return Optional.empty();
    }
}
