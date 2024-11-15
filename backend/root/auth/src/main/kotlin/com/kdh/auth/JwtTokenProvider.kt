package com.kdh.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}") private val secretKey: String,
    @Value("\${jwt.access-token-expire-time}") private val accessExpiration: Int,
    @Value("\${jwt.refresh-token-expire-time}") private val refreshExpiration: Int
) {

    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val parser = Jwts.parserBuilder().setSigningKey(key).build()

    fun generateToken(email: String, name: String, picture: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("name", name)
            .claim("picture", picture)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + accessExpiration.toLong() * 1000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = parser.parseClaimsJws(token).body
            !claims.expiration.before(Date()) // 토큰이 만료되지 않았는지 확인
        } catch (e: Exception) {
            false // 파싱 오류 또는 만료된 경우 false 반환
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = parser.parseClaimsJws(token).body
        val email = claims.subject
        return UsernamePasswordAuthenticationToken(email, null, emptyList())
    }

    fun getEmailFromToken(token: String): String? {
        return parser.parseClaimsJws(token).body.subject
    }
}