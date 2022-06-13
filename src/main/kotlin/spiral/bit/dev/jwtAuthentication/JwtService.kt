package spiral.bit.dev.jwtAuthentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import spiral.bit.dev.data.models.User
import java.util.*

class JwtService {

    private val issuer = System.getenv("JWT_ISSUER")
    private val jwtSecret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String {
        return JWT.create()
            .withSubject("CyberNoteAuthentication")
            .withIssuer(issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .withClaim("email", user.email)
            .sign(algorithm)
    }
}