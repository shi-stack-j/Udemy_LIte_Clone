package UdemyLite.Udemy.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JWTService {
    String key="";
    public JWTService()throws Exception{
        try{
            KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey=keyGenerator.generateKey();
            key= Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String generateToken(String username){
        Map<String,Object> claim=new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(username)
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(getKey())
                .compact();
    }
    public SecretKey getKey(){
        byte[] bytes= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())//Sign ko verify karo
                .build()
                .parseSignedClaims(token)//Parse karo
                .getPayload();//Actual claims
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public boolean isTokenValid(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenValid(token));
    }
}
