package cz.muni.fi.pa165.testclient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model,
                        @AuthenticationPrincipal OidcUser user) {
        if (Objects.isNull(user))
            return "redirect:/login";
        model.addAttribute("user", user);
        model.addAttribute("scopes", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return "index";
    }
}
