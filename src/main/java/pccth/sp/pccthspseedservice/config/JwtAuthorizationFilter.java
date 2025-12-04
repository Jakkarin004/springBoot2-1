//package pccth.sp.pccthspseedservice.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.keycloak.TokenVerifier;
//import org.keycloak.common.VerificationException;
//import org.keycloak.representations.AccessToken;
//import org.keycloak.representations.AccessToken.Access;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URL;
//import java.security.KeyFactory;
//import java.security.PublicKey;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.*;
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//    public static final String TOKEN_HEADER = "Authorization";
//    public static final String TOKEN_PREFIX = "Bearer ";
//    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws IOException, ServletException {
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        if (authentication == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//
//    private static PublicKey toPublicKey(String publicKeyString) {
//        try {
//            byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return keyFactory.generatePublic(keySpec);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private static PublicKey retrieveActivePublicKeyFromPublicRealmEndpoint(String issuer) {
//        log.info("issuer: {}", issuer);
//        try {
//            ObjectMapper om = new ObjectMapper();
//            @SuppressWarnings("unchecked")
//            Map<String, Object> realmInfo = om.readValue(new URL(issuer).openStream(), Map.class);
//            return toPublicKey((String) realmInfo.get("public_key"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage(), e);
//        }
//
//        return null;
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(TOKEN_HEADER);
//        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
//            try {
//                TokenVerifier<AccessToken> verifier = TokenVerifier.create(token.replace("Bearer ", ""), AccessToken.class);
//                String issuer = verifier.getToken().getIssuer();
//                PublicKey pub = retrieveActivePublicKeyFromPublicRealmEndpoint(issuer);
//                verifier.publicKey(pub);
//                verifier.withChecks(TokenVerifier.IS_ACTIVE).verify();
//                AccessToken accessToken = verifier.getToken();
////                String[] paths = verifier.getToken().getIssuer().split("/");
////            	  String realm = paths[paths.length-1];
////                verifier.getToken().getIssuer().substring(verifier.getToken().getIssuer().lastIndexOf(47) + 1);
//                Access access = accessToken.getRealmAccess();
//                Set<String> roles = access.getRoles();
//                List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
//                for (String role : roles) {
//                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//                    updatedAuthorities.add(authority);
//                }
//
//                String username = accessToken.getPreferredUsername();
//                if (StringUtils.isNotEmpty(username)) {
//                    return new JwtAuthenticationToken(username, null, updatedAuthorities, accessToken);
//                }
//            } catch (IllegalArgumentException exception) {
//                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
//            } catch (VerificationException exception) {
//                log.warn("Request to parse Verify or null JWT : {} failed : {}", token, exception.getMessage());
//            }
//        }
//        return null;
//    }
//}
