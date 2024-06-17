package globals;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.ConfigProvider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class JwtService {

    public String generateToken(String username, Integer id, String email) {
        String signingKey = ConfigProvider.getConfig().getValue("smallrye.jwt.sign.key", String.class);
        return Jwt.issuer("https://jhonata.pro/my-finance-api")
                .upn("jhonata@jhonata.pro")
                .claim("username", username)
                .claim("id", id)
                .claim("email", email)
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .expiresAt(currentTimePlusOneHour())
                .sign(signingKey);
    }

    private Instant currentTimePlusOneHour() {
        return Instant.now().plus(1, ChronoUnit.HOURS);
    }
}
