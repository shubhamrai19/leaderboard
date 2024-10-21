////package com.technix.business.book.service.impl;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import io.jsonwebtoken.io.Decoders;
////import io.jsonwebtoken.security.Keys;
////import lombok.extern.slf4j.Slf4j;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////import java.security.Key;
////import java.util.Collection;
////import java.util.Date;
////import java.util.HashSet;
////import java.util.Set;
////import java.util.function.Function;
////
////@Service
////@Slf4j
////public class JwtServiceImpl {
////
////    @Value("${jwt.secret}")
////    private String SECRET;
////
////    @Value("${jwt.expiration}")
////    private long expiration;
////
////    private final long resetTokenValidity = 15 * 60 * 1000; // 15 minutes
////
////
////    public String generateToken(UserDetails userDetails) {
////        return Jwts.builder()
////                .setSubject(userDetails.getUsername())
////                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + expiration * 10000))
////                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
////
////    public String generateResetToken(Long userId) {
////        return Jwts.builder()
////                .setSubject(userId.toString())
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + resetTokenValidity))
////                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
////
////    private Key getSigningKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
////        return Keys.hmacShaKeyFor(keyBytes);
////    }
////
////    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
////        Set<String> authoritiesSet = new HashSet<>();
////        for (GrantedAuthority authority : authorities) {
////            authoritiesSet.add(authority.getAuthority());
////        }
////        return String.join(",", authoritiesSet);
////    }
////
////    public Claims extractAllClaims(String idToken) {
////        return Jwts
////                .parserBuilder()
////                .setSigningKey(getSigningKey())
////                .build()
////                .parseClaimsJws(idToken)
////                .getBody();
////
////
////    }
////
////    public String extractUsername(String idToken) {
////        return extractClaim(idToken, Claims::getSubject);
////    }
////
////
////    public <T> T extractClaim(String idToken, Function<Claims, T> claimsResolver) {
////        final Claims claims = extractAllClaims(idToken);
////        return claimsResolver.apply(claims);
////    }
////
////
////}
//
//package com.technix.business.book.service.impl;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Function;
//
//@Service
//@Slf4j
//public class JwtServiceImpl {
//
//    @Value("${jwt.secret}")
//    private String SECRET;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//
//    private final long resetTokenValidity = 15 * 60 * 1000;//15 min
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String generateResetToken(Integer userId) {
//        return Jwts.builder()
//                .setSubject(userId.toString())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + resetTokenValidity))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Integer extractUserIdFromToken(String token) {
//        Claims claims = extractAllClaims(token);
//        return Integer.parseInt(claims.getSubject());
//    }
//
//    public Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        Set<String> authoritiesSet = new HashSet<>();
//        for (GrantedAuthority authority : authorities) {
//            authoritiesSet.add(authority.getAuthority());
//        }
//        return String.join(",", authoritiesSet);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    private Date extractExpiration(String token) {
//        return extractAllClaims(token).getExpiration();
//    }
//}
//

package com.shubham.leaderboard.Service.impl.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long expiration;

    private final long resetTokenValidity = 15 * 60 * 1000; // 15 minutes

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateResetToken(Integer userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + resetTokenValidity))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Integer extractUserIdFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return Integer.parseInt(claims.getSubject());
    }

    public Boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate != null && expirationDate.before(new Date());
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }
}

