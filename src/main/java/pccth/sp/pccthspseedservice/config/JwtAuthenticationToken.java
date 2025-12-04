//package pccth.sp.pccthspseedservice.config;
//
//import org.keycloak.representations.AccessToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
//
//    //private String token;
//    private AccessToken token;
//
//    public AccessToken getToken() {
//		return token;
//	}
//
//	public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, AccessToken token) {
//        super(principal, credentials, authorities);
//        this.token = token;
//    }
//
//}